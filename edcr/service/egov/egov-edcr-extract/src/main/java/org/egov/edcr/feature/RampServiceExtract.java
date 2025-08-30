package org.egov.edcr.feature;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.egov.common.entity.edcr.Block;
import org.egov.common.entity.edcr.DARamp;
import org.egov.common.entity.edcr.DARoom;
import org.egov.common.entity.edcr.Floor;
import org.egov.common.entity.edcr.Measurement;
import org.egov.common.entity.edcr.Ramp;
import org.egov.common.entity.edcr.RampLanding;
import org.egov.common.entity.edcr.TypicalFloor;
import org.egov.edcr.constants.DxfFileConstants;
import org.egov.edcr.entity.blackbox.MeasurementDetail;
import org.egov.edcr.entity.blackbox.PlanDetail;
import org.egov.edcr.service.LayerNames;
import org.egov.edcr.utility.Util;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLWPolyline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RampServiceExtract extends FeatureExtract {

    @Autowired
    private LayerNames layerNames;

    @Override
    public PlanDetail extract(PlanDetail pl) {
        if (pl != null && !pl.getBlocks().isEmpty())
            for (Block block : pl.getBlocks()) {
                String rampLayerNameRegex = String.format(layerNames.getLayerName("LAYER_NAME_DA_RAMP"), block.getNumber())
                        + "_+\\d";
                List<String> rampLayerNames = Util.getLayerNamesLike(pl.getDoc(), rampLayerNameRegex);
                for (String rampLayerName : rampLayerNames) {
                    List<DXFLWPolyline> polyLines = Util.getPolyLinesByLayer(pl.getDoc(), rampLayerName);
                    String[] layerArray = rampLayerName.split("_", 5);
                    BigDecimal slope = extractSlope(pl, rampLayerName);

                    List<Measurement> convertedPolyLines = polyLines.stream()
                            .map(polyLine -> new MeasurementDetail(polyLine, true)).collect(Collectors.toList());

                    if (!polyLines.isEmpty() && polyLines != null && !layerArray[4].isEmpty()
                            && layerArray[4] != null) {
                        DARamp daRamp = new DARamp();
                        daRamp.setNumber(Integer.valueOf(layerArray[4]));
                        daRamp.setMeasurements(convertedPolyLines);
                        daRamp.setPresentInDxf(true);
                        daRamp.setSlope(slope);
                        block.addDARamps(daRamp);
                    	String landingNamePattern = String.format(layerNames.getLayerName("LAYER_NAME_DA_RAMP_LANDING"),
    							block.getNumber(), "+\\d");

    					addRampLanding(pl, landingNamePattern, daRamp);
                        
                    }

                }
                if (block.getBuilding() != null && !block.getBuilding().getFloors().isEmpty()) {
                    outside: for (Floor floor : block.getBuilding().getFloors()) {
                        if (!block.getTypicalFloor().isEmpty())
                            for (TypicalFloor tp : block.getTypicalFloor())
                                if (tp.getRepetitiveFloorNos().contains(floor.getNumber()))
                                    for (Floor allFloors : block.getBuilding().getFloors())
                                        if (allFloors.getNumber().equals(tp.getModelFloorNo()))
                                            if (!allFloors.getDaRooms().isEmpty()) {
                                                floor.setDaRooms(allFloors.getDaRooms());
                                                continue outside;
                                            }
                        String daRoomLayerName = String.format(layerNames.getLayerName("LAYER_NAME_DA_ROOM"), block.getNumber(),
                                floor.getNumber());
                        List<DXFLWPolyline> polyLinesByLayer = Util.getPolyLinesByLayer(pl.getDoc(), daRoomLayerName);
                        if (!polyLinesByLayer.isEmpty() && polyLinesByLayer != null)
                            for (DXFLWPolyline polyline : polyLinesByLayer) {
                                DARoom daRoom = new DARoom();
                                daRoom.setPresentInDxf(true);
                                floor.addDaRoom(daRoom);
                            }
                    }
                    outside: for (Floor floor : block.getBuilding().getFloors()) {
                        if (!block.getTypicalFloor().isEmpty())
                            for (TypicalFloor tp : block.getTypicalFloor())
                                if (tp.getRepetitiveFloorNos().contains(floor.getNumber()))
                                    for (Floor allFloors : block.getBuilding().getFloors())
                                        if (allFloors.getNumber().equals(tp.getModelFloorNo()))
                                            if (!allFloors.getRamps().isEmpty()) {
                                                floor.setRamps(allFloors.getRamps());
                                                continue outside;
                                            }
                        String rampRegex = String.format(layerNames.getLayerName("LAYER_NAME_RAMP"), block.getNumber(),
                                floor.getNumber()) + "_+\\d";
                        List<String> rampLayer = Util.getLayerNamesLike(pl.getDoc(), rampRegex);
                        if (!rampLayer.isEmpty())
                            for (String rmpLayer : rampLayer) {
                                List<DXFLWPolyline> polylines = Util.getPolyLinesByLayer(pl.getDoc(), rmpLayer);
                                String[] splitLayer = rmpLayer.split("_", 6);
                                if (splitLayer[5] != null && !splitLayer[5].isEmpty() && !polylines.isEmpty()) {
                                    Ramp ramp = new Ramp();
                                    ramp.setNumber(Integer.valueOf(splitLayer[5]));
                                    boolean isClosed = polylines.stream()
                                            .allMatch(dxflwPolyline -> dxflwPolyline.isClosed());
                                    ramp.setRampClosed(isClosed);
                                    List<Measurement> rampPolyLine = polylines.stream()
                                            .map(dxflwPolyline -> new MeasurementDetail(dxflwPolyline, true))
                                            .collect(Collectors.toList());
                                    ramp.setRamps(rampPolyLine);
                                    String floorHeight = Util.getMtextByLayerName(pl.getDoc(), rmpLayer, "FLR_HT_M");

                                    if (!isBlank(floorHeight)) {
                                        if (floorHeight.contains("="))
                                            floorHeight = floorHeight.split("=")[1] != null
                                                    ? floorHeight.split("=")[1].replaceAll("[^\\d.]", "")
                                                    : "";
                                        else
                                            floorHeight = floorHeight.replaceAll("[^\\d.]", "");

                                        if (!isBlank(floorHeight)) {
                                            BigDecimal height = BigDecimal.valueOf(Double.parseDouble(floorHeight));
                                            ramp.setFloorHeight(height);
                                        }
                                        BigDecimal minEntranceHeight = extractMinEntranceHeight(pl, rmpLayer);
                                        ramp.setMinEntranceHeight(minEntranceHeight);
                                       
                                        floor.addRamps(ramp);
                                    }
                                }
                            }
                    }
                }
            }
        return pl;
    }

	private BigDecimal extractSlope(PlanDetail pl, String rampLayerName) {
		String text = Util.getMtextByLayerName(pl.getDoc(), rampLayerName);
		BigDecimal slope = BigDecimal.ZERO;
		if (text != null && !text.isEmpty() && text.contains("=")) {
		    String[] textArray = text.split("=", 2);
		    String slopeText = textArray[1];
		    if(slopeText!=null) {
				String[] slopeDividendAndDivisor = slopeText.toUpperCase().split("IN", 2);
				if (slopeDividendAndDivisor != null && slopeDividendAndDivisor.length == 2
						&& slopeDividendAndDivisor[0] != null && slopeDividendAndDivisor[1] != null) {
					slopeDividendAndDivisor[0] = slopeDividendAndDivisor[0].replaceAll("[^\\d.]", "");
					slopeDividendAndDivisor[1] = slopeDividendAndDivisor[1].replaceAll("[^\\d.]", "");
					slope = BigDecimal.valueOf(Double.valueOf(slopeDividendAndDivisor[0])).divide(
							BigDecimal.valueOf(Double.valueOf(slopeDividendAndDivisor[1])), 2, RoundingMode.HALF_UP);
				}
		    }
		}
		return slope;
	}
	
	private BigDecimal extractMinEntranceHeight(PlanDetail pl, String rampLayerName) {
	    String text = Util.getMtextByLayerName(pl.getDoc(), rampLayerName, "ENT_HT");
	    BigDecimal entranceHeight = BigDecimal.ZERO;
	    if (text != null && !text.isEmpty()) {
	        if (text.contains("=")) {
	            text = text.split("=")[1] != null ? text.split("=")[1].replaceAll("[^\\d.]", "") : "";
	        } else {
	            text = text.replaceAll("[^\\d.]", "");
	        }

	        if (!isBlank(text)) {
	            entranceHeight = new BigDecimal(text);
	        }
	    }
	    return entranceHeight;
	}
	
	/**
	 * Adds ramp landings to the given {@link DARamp} by extracting and processing
	 * layers from the DXF document within the given {@link PlanDetail}.
	 * <p>
	 * This method identifies all layers matching the specified landing name pattern,
	 * processes polylines on these layers to create {@link RampLanding} objects,
	 * and assigns these landings to the provided {@link DARamp} instance.
	 * </p>
	 *
	 * @param pl The {@link PlanDetail} containing the DXF document and related data.
	 * @param landingNamePattern The pattern used to identify landing layers within the DXF document.
	 * @param daRamp The {@link DARamp} instance to which the extracted ramp landings will be assigned.
	 */
	
	private void addRampLanding(PlanDetail pl, String landingNamePattern, DARamp daRamp) {
		DXFDocument doc = pl.getDoc();
	    List<String> landingLayerNames = Util.getLayerNamesLike(doc, landingNamePattern);
		List<RampLanding> landings = new ArrayList<>();

		for (String landingLayer : landingLayerNames) {

			RampLanding rampLanding = new RampLanding();

			String[] landingNo = landingLayer.split("_");

			rampLanding.setNumber(landingNo[7]);

			List<DXFLWPolyline> landingPolyLines = Util.getPolyLinesByLayer(doc, landingLayer);

			boolean isClosed = landingPolyLines.stream().allMatch(dxflwPolyline -> dxflwPolyline.isClosed());

			rampLanding.setLandingClosed(isClosed);

			List<Measurement> landingPolyLinesMeasurement = landingPolyLines.stream()
					.map(flightPolyLine -> new MeasurementDetail(flightPolyLine, true)).collect(Collectors.toList());

			rampLanding.setLandings(landingPolyLinesMeasurement);

			// set length of flight
			List<BigDecimal> landingLengths = Util.getListOfDimensionByColourCode(pl, landingLayer,
					DxfFileConstants.STAIR_FLIGHT_LENGTH_COLOR);

			rampLanding.setLengths(landingLengths);

			// set width of flight
			List<BigDecimal> landingWidths = Util.getListOfDimensionByColourCode(pl, landingLayer,
					DxfFileConstants.STAIR_FLIGHT_WIDTH_COLOR);

			rampLanding.setWidths(landingWidths);

			landings.add(rampLanding);
		}

		daRamp.setLandings(landings);
	}



    @Override
    public PlanDetail validate(PlanDetail pl) {
        return pl;
    }
}
