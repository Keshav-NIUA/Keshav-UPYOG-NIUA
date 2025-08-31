package org.egov.edcr.feature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.egov.common.entity.edcr.*;
import org.egov.edcr.entity.blackbox.FloorDetail;
import org.egov.edcr.entity.blackbox.OccupancyDetail;
import org.egov.edcr.entity.blackbox.PlanDetail;
import org.egov.edcr.entity.blackbox.ServiceFloorDetail;
import org.egov.edcr.service.LayerNames;
import org.egov.edcr.utility.Util;
import org.kabeja.dxf.DXFLWPolyline;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.egov.edcr.utility.DcrConstants.*;

public class ServiceFloorExtract extends FeatureExtract {
    private static final Logger LOG = LogManager.getLogger(ServiceFloorExtract.class);

    private static final String VALIDATION_WRONG_COLORCODE_FLOORAREA = "msg.error.wrong.colourcode.floorarea";

    @Autowired
    private LayerNames layerNames;

    @Override
    public PlanDetail extract(PlanDetail pl) {
        if (LOG.isDebugEnabled())
            LOG.debug("Starting of ServiceFloor room Extract......");

        String farDeductByFloor = layerNames.getLayerName("LAYER_NAME_BLOCK_NAME_PREFIX") + "%s" + "_"
                + layerNames.getLayerName("LAYER_NAME_FLOOR_NAME_PREFIX") + "%s" + "_"
                + layerNames.getLayerName("LAYER_NAME_BUILT_UP_AREA_DEDUCT");

        if (pl != null && !pl.getBlocks().isEmpty())
            for (Block block : pl.getBlocks()) {
                List<String> typicals = new ArrayList<>();
                List<DXFLWPolyline> polyLinesByLayer;

                String layerRegEx = layerNames.getLayerName("LAYER_NAME_BLOCK_NAME_PREFIX") + block.getNumber() + "_"
                        + layerNames.getLayerName("LAYER_NAME_FLOOR_NAME_PREFIX") + "-?\\d+_"
                        + layerNames.getLayerName("LAYER_NAME_SERVICE_FLOOR");
                List<String> layerNames = Util.getLayerNamesLike(pl.getDoc(), layerRegEx);

                int serviceFloorNo;
                ServiceFloorDetail serviceFloor;
                for (String s : layerNames) {
                    String typical = "";
                    LOG.error("Working on Block  " + block.getNumber() + " For layer Name " + s);
                    polyLinesByLayer = Util.getPolyLinesByLayer(pl.getDoc(), s);

                    if (polyLinesByLayer.isEmpty())
                        continue;

                    String typicalStr = Util.getMtextByLayerName(pl.getDoc(), s);

                    if (typicalStr != null) {
                        LOG.error(
                                "Typical found in  " + block.getNumber() + " in layer" + s + "with Details " + typicalStr);
                        if (typical.isEmpty()) {
                            typical = typicalStr;
                            typicals.add(typical);
                        } else {
                            LOG.info("multiple typical service floors defined in block " + block.getNumber() + " in layer" + s);
                            pl.addError("multiple typical service floors defined",
                                    "multiple typical service floors defined. Considering First one");
                        }
                    }

                    serviceFloorNo = Integer.valueOf(s.split("_")[3]);
                    if (block.getBuilding().getServiceFloorNumber(serviceFloorNo) == null) {
                        serviceFloor = new ServiceFloorDetail();
                        serviceFloor.setNumber(serviceFloorNo);
                        extractFloorHeight(pl, block, serviceFloor);
                    } else
                        serviceFloor = (ServiceFloorDetail) block.getBuilding().getServiceFloorNumber(serviceFloorNo);
                    // find builtup area
                    for (DXFLWPolyline pline : polyLinesByLayer) {

                        BigDecimal occupancyArea = Util.getPolyLineArea(pline);
                        LOG.error(" occupancyArea *************** " + occupancyArea);
                        OccupancyDetail occupancy = new OccupancyDetail();
                        occupancy.setPolyLine(pline);
                        occupancy.setBuiltUpArea(occupancyArea == null ? BigDecimal.ZERO : occupancyArea);
                        occupancy.setExistingBuiltUpArea(BigDecimal.ZERO);
                        occupancy.setType(Util.findOccupancyType(pline));
                        occupancy.setTypeHelper(Util.findOccupancyType(pline, pl));
                        LOG.error(" occupancy type " + occupancy.getType());
                        if (occupancy.getTypeHelper() == null)
                            pl.addError(VALIDATION_WRONG_COLORCODE_FLOORAREA, getLocaleMessage(
                                    VALIDATION_WRONG_COLORCODE_FLOORAREA, String.valueOf(pline.getColor()), s));
                        else
                            serviceFloor.addBuiltUpArea(occupancy);
                    }
                    if (block.getBuilding().getServiceFloorNumber(serviceFloorNo) == null)
                        block.getBuilding().getServiceFloors().add(serviceFloor);
                    // find deductions
                    String deductLayerName = String.format(farDeductByFloor, block.getNumber(), serviceFloor.getNumber());

                    LOG.error("Working on Block deduction  " + deductLayerName);

                    List<DXFLWPolyline> bldDeduct = Util.getPolyLinesByLayer(pl.getDoc(), deductLayerName);
                    for (DXFLWPolyline pline : bldDeduct) {
                        BigDecimal deductionArea = Util.getPolyLineArea(pline);
                        LOG.error(" deductionArea *************** " + deductionArea);

                        Occupancy occupancy = new Occupancy();
                        occupancy.setDeduction(deductionArea == null ? BigDecimal.ZERO : deductionArea);
                        occupancy.setExistingDeduction(BigDecimal.ZERO);
                        occupancy.setType(Util.findOccupancyType(pline));
                        occupancy.setTypeHelper(Util.findOccupancyType(pline, pl));
                        LOG.error(" occupancy type deduction " + occupancy.getType());

                        if (occupancy.getTypeHelper() == null
                                || (occupancy.getTypeHelper() != null && occupancy.getTypeHelper().getType() == null))
                            pl.addError(VALIDATION_WRONG_COLORCODE_FLOORAREA,
                                    getLocaleMessage(VALIDATION_WRONG_COLORCODE_FLOORAREA, String.valueOf(pline.getColor()),
                                            deductLayerName));
                        else
                            serviceFloor.addDeductionArea(occupancy);
                    }
                }
                if (!typicals.isEmpty()) {
                    LOG.info("Adding typical:" + block.getNumber());
                    List<TypicalFloor> typicalFloors = new ArrayList<>();
                    for (String typical : typicals) {
                        TypicalFloor tpf = new TypicalFloor(typical);
                        typicalFloors.add(tpf);
                    }
                    block.setTypicalFloor(typicalFloors);
                }


            }
        if (LOG.isDebugEnabled())
            LOG.debug("End of ServiceFloor Extract......");
        return pl;
    }

    private void extractFloorHeight(PlanDetail pl, Block block, ServiceFloor floor) {
        String floorHeightLayerName = layerNames.getLayerName("LAYER_NAME_BLOCK_NAME_PREFIX") + block.getNumber() + "_"
                + layerNames.getLayerName("LAYER_NAME_FLOOR_NAME_PREFIX") + floor.getNumber() + "_"
                + layerNames.getLayerName("LAYER_NAME_SERVICE_FLOOR_HEIGHT_PREFIX");
        List<BigDecimal> flrHeights = Util.getListOfDimensionValueByLayer(pl, floorHeightLayerName);
        if (!flrHeights.isEmpty()) {
            floor.setFloorHeights(flrHeights);
        } else {
            pl.addError(SERVICE_FLOOR_HEIGHT_DESC, getLocaleMessage(OBJECTNOTDEFINED, FLOOR_HEIGHT_DESC + floor.getNumber()));
        }
    }

    @Override
    public PlanDetail validate(PlanDetail pl) {
        return pl;
    }
}
