package org.egov.edcr.feature;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.egov.common.entity.edcr.Block;
import org.egov.edcr.entity.blackbox.PlanDetail;
import org.egov.edcr.service.LayerNames;
import org.egov.edcr.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RampSlopeExtract extends FeatureExtract {
	private static final Logger LOG = LogManager.getLogger(RampSlopeExtract.class);

    @Autowired
    private LayerNames layerNames;



    @Override
    public PlanDetail extract(PlanDetail pl) {
        if (pl != null && !pl.getBlocks().isEmpty()) {
            for (Block block : pl.getBlocks()) {
                String slopeLayerName = String.format(
                        layerNames.getLayerName("LAYER_NAME_ROOF_SLOPE"),
                        block.getNumber(), "+\\d"
                );

                List<String> slopeLayers = Util.getLayerNamesLike(pl.getDoc(), slopeLayerName);

                if (!slopeLayers.isEmpty()) {
                    for (String layer : slopeLayers) {
                        String slopeText = Util.getMtextByLayerName(pl.getDoc(), layer);

                        if (slopeText != null && slopeText.contains("ROOF_SLOPE=")) {
                            try {
                                BigDecimal slope = BigDecimal.valueOf(
                                        Double.valueOf(slopeText.replaceAll("ROOF_SLOPE=", "").trim())
                                );

                                block.addRoofSlope(slope);

                                LOG.debug("Extracted slope {} for block {}", slope, block.getNumber());

                            } catch (NumberFormatException e) {
                                LOG.error("Invalid roof slope format in layer {}: {}", layer, slopeText);
                            }
                        }
                    }
                }
            }
        }
        return pl;
    }



	@Override
	public PlanDetail validate(PlanDetail pl) {
		// TODO Auto-generated method stub
		return null;
	}

}
