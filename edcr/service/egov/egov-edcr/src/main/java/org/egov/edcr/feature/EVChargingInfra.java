/*
 * UPYOG  SmartCity eGovernance suite aims to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) <2019>  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *      Further, all user interfaces, including but not limited to citizen facing interfaces,
 *         Urban Local Bodies interfaces, dashboards, mobile applications, of the program and any
 *         derived works should carry eGovernments Foundation logo on the top right corner.
 *
 *      For the logo, please refer http://egovernments.org/html/logo/egov_logo.png.
 *      For any further queries on attribution, including queries on brand guidelines,
 *         please contact contact@egovernments.org
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.edcr.feature;


import static org.egov.edcr.constants.CommonFeatureConstants.UNDERSCORE;
import static org.egov.edcr.constants.EdcrReportConstants.FOUR_WHEELER_FAST_CHARGER;
import static org.egov.edcr.constants.EdcrReportConstants.FOUR_WHEELER_SLOW_CHARGER;
import static org.egov.edcr.constants.EdcrReportConstants.PASSENGER_VEHICLE_FAST_CHARGER;
import static org.egov.edcr.constants.EdcrReportConstants.PASSENGER_VEHICLE_SLOW_CHARGER;
import static org.egov.edcr.constants.EdcrReportConstants.RESIDENTIAL_SLOW_CHARGER;
import static org.egov.edcr.constants.EdcrReportConstants.RULE117;
import static org.egov.edcr.constants.EdcrReportConstants.RULE117B;
import static org.egov.edcr.constants.EdcrReportConstants.THREE_WHEELER_FAST_CHARGER;
import static org.egov.edcr.constants.EdcrReportConstants.THREE_WHEELER_SLOW_CHARGER;
import static org.egov.edcr.constants.EdcrReportConstants.TWO_WHEELER_FAST_CHARGER;
import static org.egov.edcr.constants.EdcrReportConstants.TWO_WHEELER_SLOW_CHARGER;
import static org.egov.edcr.service.FeatureUtil.mapReportDetails;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.egov.common.entity.edcr.EVChargingRequirement;
import org.egov.common.entity.edcr.FeatureEnum;
import org.egov.common.entity.edcr.Plan;
import org.egov.common.entity.edcr.ReportScrutinyDetail;
import org.egov.common.entity.edcr.Result;
import org.egov.common.entity.edcr.ScrutinyDetail;
import org.egov.edcr.service.MDMSCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EVChargingInfra extends FeatureProcess {

	private static final Logger LOGGER = LogManager.getLogger(EVChargingInfra.class);
	
	@Autowired
	MDMSCacheManager cache;

	@Override
	public Plan validate(Plan plan) {
	    return plan;
	}

	/**
	 * Processes the given {@link Plan} by validating balcony widths block-wise.
	 * <p>
	 * For each block in the plan, if the block contains a building, it invokes
	 * {@code processBlockBalconies} to validate balconies floor-wise, gather
	 * scrutiny details, and append them to the plan's report output.
	 * </p>
	 *
	 * @param plan the {@link Plan} object containing building blocks and their details
	 * @return the modified {@link Plan} object with updated scrutiny report
	 */
	
	@Override
	public Plan process(Plan plan) {
	    
		 ScrutinyDetail scrutinyDetail = createScrutinyDetail(" " + UNDERSCORE + FeatureEnum.EV_CHARGING,
		            RULE_NO, DESCRIPTION, PERMISSIBLE, PROVIDED, STATUS);

		processEVChargingInfra(plan, scrutinyDetail);

	    return plan;
	}


	/**
	 * Processes the EV Charging Infrastructure validation for the given plan.
	 * <p>
	 * This method fetches EV charging rules from MDMS cache and validates the
	 * provided charging infrastructure against the required norms for different
	 * vehicle categories, including a special rule for residential slow chargers
	 * that enforces a minimum of 1 charger plus additional provisions by the owner.
	 * </p>
	 *
	 * @param plan           The {@link Plan} object containing plan details for validation.
	 * @param scrutinyDetail The {@link ScrutinyDetail} object to which validation results 
	 *                       will be appended.
	 */
	private void processEVChargingInfra(Plan plan, ScrutinyDetail scrutinyDetail) {

		
	    LOGGER.info("Starting EV Charging Infra validation for Plan");

	    List<Object> rules = cache.getFeatureRules(plan, FeatureEnum.EV_CHARGING.getValue(), false);

	    if (rules == null || rules.isEmpty()) {
	        LOGGER.warn("No EV_CHARGING feature rules found in MDMS for Plan");
	        return;
	    }

	    for (Object ruleObj : rules) {
	        if (ruleObj instanceof EVChargingRequirement) {
	            EVChargingRequirement requirement = (EVChargingRequirement) ruleObj;
	            LOGGER.debug("Fetched EVChargingRequirement: {}", requirement);

	            // ===== Get Inputs from Plan =====
	            BigDecimal prov4Fast  = defaultIfNull(plan.getPlanInformation().getFourWheelerFastCharger());
	            BigDecimal prov4Slow  = defaultIfNull(plan.getPlanInformation().getFourWheelerSlowCharger());
	            BigDecimal prov3Fast  = defaultIfNull(plan.getPlanInformation().getThreeWheelerFastCharger());
	            BigDecimal prov3Slow  = defaultIfNull(plan.getPlanInformation().getThreeWheelerSlowCharger());
	            BigDecimal prov2Fast  = defaultIfNull(plan.getPlanInformation().getTwoWheelerFastCharger());
	            BigDecimal prov2Slow  = defaultIfNull(plan.getPlanInformation().getTwoWheelerSlowCharger());
	            BigDecimal provPVFast = defaultIfNull(plan.getPlanInformation().getPVfastCharger());
	            BigDecimal provPVSlow = defaultIfNull(plan.getPlanInformation().getPVslowCharger());

	            // ===== Ratios from Requirement (vehicles per charger) =====
	            BigDecimal vPer4Fast = defaultIfNull(requirement.getNoOfFourWheelerForfastCharger());
	            BigDecimal vPer4Slow = defaultIfNull(requirement.getNoOfFourWheelerForSlowCharger());
	            BigDecimal vPer3Fast = defaultIfNull(requirement.getNoOfThreeWheelerForFastCharger());
	            BigDecimal vPer3Slow = defaultIfNull(requirement.getNoOfThreeWheelerForSlowCharger());
	            BigDecimal vPer2Fast = defaultIfNull(requirement.getNoOfTwoWheelerForFastCharger());
	            BigDecimal vPer2Slow = defaultIfNull(requirement.getNoOfTwoWheelerForSlowCharger());
	            BigDecimal vPerPVFast= defaultIfNull(requirement.getNoOfPVForFastCharger());
	            BigDecimal vPerPVSlow= defaultIfNull(requirement.getNoOfPVForSlowCharger());

	            // ===== Total vehicles themselves (assumed from plan, else define) =====
	            BigDecimal total4Fast = defaultIfNull(plan.getPlanInformation().getNoOfFourWheelerForfastCharger()); 
	            BigDecimal total4Slow = defaultIfNull(plan.getPlanInformation().getNoOfFourWheelerForSlowCharger());
	            BigDecimal total3Fast = defaultIfNull(plan.getPlanInformation().getNoOfThreeWheelerForFastCharger());
	            BigDecimal total3Slow = defaultIfNull(plan.getPlanInformation().getNoOfThreeWheelerForSlowCharger());
	            BigDecimal total2Fast = defaultIfNull(plan.getPlanInformation().getNoOfTwoWheelerForFastCharger());
	            BigDecimal total2Slow = defaultIfNull(plan.getPlanInformation().getNoOfTwoWheelerForSlowCharger());
	            BigDecimal totalPVFast= defaultIfNull(plan.getPlanInformation().getNoOfPVForFastCharger());
	            BigDecimal totalPVSlow= defaultIfNull(plan.getPlanInformation().getNoOfPVForSlowCharger());

	            // ===== Calculate required chargers =====
	            BigDecimal req4Fast = calculateRequired(total4Fast, vPer4Fast);
	            BigDecimal req4Slow = calculateRequired(total4Slow, vPer4Slow);
	            BigDecimal req3Fast = calculateRequired(total3Fast, vPer3Fast);
	            BigDecimal req3Slow = calculateRequired(total3Slow, vPer3Slow);
	            BigDecimal req2Fast = calculateRequired(total2Fast, vPer2Fast);
	            BigDecimal req2Slow = calculateRequired(total2Slow, vPer2Slow);
	            BigDecimal reqPVFast = calculateRequired(totalPVFast, vPerPVFast);
	            BigDecimal reqPVSlow = calculateRequired(totalPVSlow, vPerPVSlow);
	            
	            // Fetch inputs from Plan for residential slow chargers 
	            BigDecimal provSlowResidential = defaultIfNull(plan.getPlanInformation().getChargerResidential());           
	            BigDecimal vehiclesPerSlowCharger = defaultIfNull(requirement.getnoOfWheelerResidential());
	            BigDecimal totalVehiclesResidential = defaultIfNull(plan.getPlanInformation().getNoOfWheelerResidential());
	            BigDecimal calculatedRequired = calculateRequired(totalVehiclesResidential, vehiclesPerSlowCharger);

	            // Residential minimum slow charger rule: at least 1 charger
	            BigDecimal requiredResidentialSlowCharger = calculatedRequired.max(BigDecimal.ONE);

	            // Validate with this required number
	            validateCharger(scrutinyDetail, RESIDENTIAL_SLOW_CHARGER, RULE117B, requiredResidentialSlowCharger,
	                provSlowResidential, totalVehiclesResidential, vehiclesPerSlowCharger);

	            // ===== Validation =====
	            validateCharger(scrutinyDetail, FOUR_WHEELER_FAST_CHARGER, RULE117, req4Fast, prov4Fast, total4Fast, vPer4Fast);
	            validateCharger(scrutinyDetail, FOUR_WHEELER_SLOW_CHARGER, RULE117, req4Slow, prov4Slow, total4Slow, vPer4Slow);
	            validateCharger(scrutinyDetail, THREE_WHEELER_FAST_CHARGER, RULE117, req3Fast, prov3Fast, total3Fast, vPer3Fast);
	            validateCharger(scrutinyDetail, THREE_WHEELER_SLOW_CHARGER, RULE117, req3Slow, prov3Slow, total3Slow, vPer3Slow);
	            validateCharger(scrutinyDetail, TWO_WHEELER_FAST_CHARGER, RULE117, req2Fast, prov2Fast, total2Fast, vPer2Fast);
	            validateCharger(scrutinyDetail, TWO_WHEELER_SLOW_CHARGER, RULE117, req2Slow, prov2Slow, total2Slow, vPer2Slow);
	            validateCharger(scrutinyDetail, PASSENGER_VEHICLE_FAST_CHARGER, RULE117, reqPVFast, provPVFast, totalPVFast, vPerPVFast);
	            validateCharger(scrutinyDetail, PASSENGER_VEHICLE_SLOW_CHARGER, RULE117, reqPVSlow, provPVSlow, totalPVSlow, vPerPVSlow);
	        }
	    }
	    LOGGER.info("Completed EV Charging Infra validation for Plan");
	}
	/**
	 * Calculates the required number of chargers based on the total number of vehicles 
	 * and the number of vehicles each charger can serve.
	 * <p>
	 * The calculation uses ceiling division to ensure that any fractional charger 
	 * requirement results in an additional charger.
	 * </p>
	 *
	 * @param totalVehicles      The total number of vehicles requiring charging.
	 * @param vehiclesPerCharger The number of vehicles that one charger can serve.
	 * @return The required number of chargers as a {@link BigDecimal}. Returns BigDecimal.ZERO 
	 *         if the inputs are null or non-positive.
	 */
	private BigDecimal calculateRequired(BigDecimal totalVehicles, BigDecimal vehiclesPerCharger) {
	    if (totalVehicles == null || totalVehicles.compareTo(BigDecimal.ZERO) <= 0) {
	        return BigDecimal.ZERO;
	    }
	    if (vehiclesPerCharger == null || vehiclesPerCharger.compareTo(BigDecimal.ZERO) <= 0) {
	        return BigDecimal.ZERO;
	    }
	    return new BigDecimal(
	        (int) Math.ceil(totalVehicles.doubleValue() / vehiclesPerCharger.doubleValue())
	    );
	}



	/**
	 * Validates the provided number of chargers against the required number and records
	 * the result in the given {@link ScrutinyDetail}.
	 * <p>
	 * Checks if the provided chargers meet or exceed the required chargers count.
	 * Adds a detailed scrutiny report entry describing the validation basis and status.
	 * </p>
	 *
	 * @param scrutinyDetail      The scrutiny detail object to which the validation result will be added.
	 * @param chargerType         The type of charger being validated (e.g., "Four Wheeler Fast Charger").
	 * @param ruleNo              The rule number or code associated with this validation.
	 * @param required            The number of chargers required, as calculated.
	 * @param provided            The number of chargers provided in the plan.
	 * @param totalVehicles       The total number of vehicles applicable for this charger type.
	 * @param vehiclesPerCharger  The ratio of vehicles per charger from the requirement.
	 */
	private void validateCharger(ScrutinyDetail scrutinyDetail,
	        String chargerType, String ruleNo,
	        BigDecimal required, BigDecimal provided,
	        BigDecimal totalVehicles, BigDecimal vehiclesPerCharger) {

	    if (required == null) required = BigDecimal.ZERO;
	    if (provided == null) provided = BigDecimal.ZERO;

	    boolean isAccepted = provided.compareTo(required) >= 0;

	    String basisExplanation = String.format(
	        "Total Vehicles = %s, Vehicles/Charger = %s, Calculated Requirement = %s",
	        totalVehicles.toPlainString(),
	        (vehiclesPerCharger == null ? "N/A" : vehiclesPerCharger.toPlainString()),
	        required.toPlainString()
	    );

	    LOGGER.debug("Validation Result [{}]: Required={}, Provided={}, Status={}, Basis={}",
	        chargerType, required, provided, (isAccepted ? "Accepted" : "Not Accepted"), basisExplanation);

	    ReportScrutinyDetail detail = new ReportScrutinyDetail();
	    detail.setRuleNo(ruleNo);
	    detail.setDescription("Validation of " + chargerType + " (" + basisExplanation + ")");
	    detail.setPermissible(required.toString());
	    detail.setProvided(provided.toString());
	    detail.setStatus(isAccepted ? Result.Accepted.getResultVal() : Result.Not_Accepted.getResultVal());
	   

	    Map<String, String> details = mapReportDetails(detail);
	    scrutinyDetail.getDetail().add(details);
	}


	// Method to create ScrutinyDetail
	private ScrutinyDetail createScrutinyDetail(String key, String... headings) {
	    ScrutinyDetail detail = new ScrutinyDetail();
	    detail.setKey(key);
	    for (int i = 0; i < headings.length; i++) {
	        detail.addColumnHeading(i + 1, headings[i]);
	    }
	    return detail;
	}

	@Override
	public Map<String, Date> getAmendments() {
	    return new LinkedHashMap<>();
	}

	private BigDecimal defaultIfNull(BigDecimal value) {
	    return value == null ? BigDecimal.ZERO : value;
	}

}