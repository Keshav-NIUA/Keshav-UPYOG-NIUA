package org.egov.edcr.feature;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.egov.common.entity.edcr.*;
import org.egov.edcr.constants.DxfFileConstants;
import org.egov.edcr.service.MDMSCacheManager;
import org.egov.edcr.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.egov.common.entity.edcr.Passage;

import static org.egov.edcr.constants.CommonKeyConstants.*;
import static org.egov.edcr.constants.EdcrReportConstants.*;
import static org.egov.edcr.service.FeatureUtil.addScrutinyDetailtoPlan;
import static org.egov.edcr.service.FeatureUtil.mapReportDetails;

public class PassageService_Assam extends FeatureProcess {
    private static final Logger LOG = LogManager.getLogger(PassageService_Assam.class);

    @Autowired
    MDMSCacheManager cache;

    @Override
    public Plan validate(Plan plan) {
        return plan;
    }

    @Override
    public Plan process(Plan plan) {
        BigDecimal passageStairMinimumWidth = BigDecimal.ZERO;

        List<Object> rules = cache.getFeatureRules(plan, FeatureEnum.PASSAGE_SERVICE.getValue(), false);
        for (Object obj : rules) {
            PassageRequirement rule = (PassageRequirement) obj;
            passageStairMinimumWidth = rule.getPassageServiceValueOne();
            break;
        }

        for (Block block : plan.getBlocks()) {
            if (block.getBuilding() == null || block.getBuilding().getPassage() == null) {
                continue;
            }

            ScrutinyDetail passageDetail = createScrutinyDetail(BLOCK + block.getNumber() + PASSAGE_SUFFIX);
            ScrutinyDetail passageStairDetail = createScrutinyDetail(BLOCK + block.getNumber() + PASSAGE_STAIR_SUFFIX);

            Passage passage = block.getBuilding().getPassage();
            BigDecimal passageLength = passage.getPassageLength();
            BigDecimal requiredPassageWidth = getBuildingTypeBasedWidth(block.getBuilding(), passageLength);

            validatePassageDimension(
                    passage.getPassageDimensions(),
                    requiredPassageWidth,
                    RULE41,
                    RULE_41_DESCRIPTION,
                    passageDetail,
                    plan
            );

            validatePassageDimension(
                    passage.getPassageStairDimensions(),
                    passageStairMinimumWidth,
                    RULE39_6,
                    RULE39_6_DESCRIPTION,
                    passageStairDetail,
                    plan
            );
        }

        return plan;
    }

    private BigDecimal getBuildingTypeBasedWidth(Building building, BigDecimal passageLength) {
        String occupancy = building.getMostRestrictiveFarHelper().getType() != null
                ? building.getMostRestrictiveFarHelper().getType().getCode() : null;
        String subOccupancy = building.getMostRestrictiveFarHelper().getSubtype() != null
                ? building.getMostRestrictiveFarHelper().getSubtype().getCode() : null;

        // passageLength to be setted and then passed here
//        List<BigDecimal> passageLength;

        if (occupancy != null) {
            switch (occupancy) {
                case DxfFileConstants.A:
                    return BigDecimal.valueOf(1.0);
                case DxfFileConstants.F:
                    return getShoppingComplexWidth(passageLength);
                case DxfFileConstants.B:
                case DxfFileConstants.D:
                    return BigDecimal.valueOf(2.5);
                default:
                    return BigDecimal.valueOf(1.5);
            }
        }
        if (subOccupancy != null) {
            switch (subOccupancy) {
                case DxfFileConstants.F_H:
                    return BigDecimal.valueOf(1.5);
                default:
                    return BigDecimal.valueOf(1.5);
            }
        }

        return BigDecimal.valueOf(1.5);
    }

    private BigDecimal getShoppingComplexWidth(BigDecimal passageLength) {
        if (passageLength != null) {
//            BigDecimal maxLength = passageLength.stream().reduce(BigDecimal::max).orElse(BigDecimal.ZERO);
            return passageLength.compareTo(BigDecimal.valueOf(15.0)) > 0 ? BigDecimal.valueOf(2.1) : BigDecimal.valueOf(1.8);
        }
        return BigDecimal.valueOf(1.8);
    }

//    private String getBuildingTypeDescription(Building building) {
//        OccupancyType occupancy = building.getMostRestrictiveOccupancy();
//        if (occupancy == null) return "Minimum width of passages for other buildings";
//
//        switch (occupancy) {
//            case OCCUPANCY_A1:
//            case OCCUPANCY_A4:
//                return "Minimum width of passages for residential house";
//            case OCCUPANCY_F3:
//                return "Minimum width of passages for hotel";
//            case OCCUPANCY_F:
//                return "Minimum width of passages for shopping complex";
//            case OCCUPANCY_D:
//            case OCCUPANCY_D1:
//            case OCCUPANCY_D2:
//                return "Minimum width of passages for assembly building";
//            case OCCUPANCY_B1:
//            case OCCUPANCY_B2:
//            case OCCUPANCY_B3:
//                return "Minimum width of passages for educational building";
//            default:
//                return "Minimum width of passages for other buildings";
//        }
//    }

    private void validatePassageDimension(List<BigDecimal> dimensions, BigDecimal permissibleWidth,
                                          String ruleNo, String ruleDesc, ScrutinyDetail detail, Plan plan) {
        if (dimensions != null && !dimensions.isEmpty()) {
            BigDecimal minWidth = Util.roundOffTwoDecimal(dimensions.stream().reduce(BigDecimal::min).get());
            String result = minWidth.compareTo(permissibleWidth) >= 0
                    ? Result.Accepted.getResultVal()
                    : Result.Not_Accepted.getResultVal();

            setReportOutputDetails(plan, ruleNo, ruleDesc, permissibleWidth.toString(),
                    String.valueOf(minWidth), result, detail);
        }
    }

    private ScrutinyDetail createScrutinyDetail(String key) {
        ScrutinyDetail detail = new ScrutinyDetail();
        detail.addColumnHeading(1, RULE_NO);
        detail.addColumnHeading(2, REQUIRED);
        detail.addColumnHeading(3, PROVIDED);
        detail.addColumnHeading(4, STATUS);
        detail.setKey(key);
        return detail;
    }

    private void setReportOutputDetails(Plan pl, String ruleNo, String ruleDesc, String expected, String actual,
                                        String status, ScrutinyDetail scrutinyDetail) {
        ReportScrutinyDetail detail = new ReportScrutinyDetail();
        detail.setRuleNo(ruleNo);
        detail.setDescription(ruleDesc);
        detail.setRequired(expected);
        detail.setProvided(actual);
        detail.setStatus(status);

        Map<String, String> details = mapReportDetails(detail);
        addScrutinyDetailtoPlan(scrutinyDetail, pl, details);
    }

    @Override
    public Map<String, Date> getAmendments() {
        return new LinkedHashMap<>();
    }
}