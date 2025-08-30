package org.egov.common.entity.edcr;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the minimum required distances from various types of 
 * waterbodies (such as rivers, channels, ponds and notified waterbodies). 
 * <p>
 * This class extends {@link MdmsFeatureRule} and is generally used to 
 * validate or enforce planning/building regulations with respect to 
 * proximity to different kinds of waterbodies.
 * </p>
 *
 * <p>
 * Each field is mapped via {@link JsonProperty} to allow parsing from 
 * configuration sources such as MDMS (Master Data Management System).
 * </p>
 */

public class DistanceFromWaterBodiesRequirement extends MdmsFeatureRule {
	
	@JsonProperty("river")
    private BigDecimal river;
	
	@JsonProperty("bharaluMoraBondajan")
    private BigDecimal bharaluMoraBondajan;
	
	@JsonProperty("otherChannels")
    private BigDecimal otherChannels;
	
	@JsonProperty("notifiedWaterBodies")
    private BigDecimal notifiedWaterBodies;
	
	
	@JsonProperty("otherNotifiedWaterBodies")
    private BigDecimal otherNotifiedWaterBodies;
	
	@JsonProperty("otherLargePondsWaterBodies")
    private BigDecimal otherLargePondsWaterBodies;

	public BigDecimal getRiver() {
		return river;
	}

	public void setRiver(BigDecimal river) {
		this.river = river;
	}

	public BigDecimal getBharaluMoraBondajan() {
		return bharaluMoraBondajan;
	}

	public void setBharaluMoraBondajan(BigDecimal bharaluMoraBondajan) {
		this.bharaluMoraBondajan = bharaluMoraBondajan;
	}

	public BigDecimal getOtherChannels() {
		return otherChannels;
	}

	public void setOtherChannels(BigDecimal otherChannels) {
		this.otherChannels = otherChannels;
	}

	public BigDecimal getNotifiedWaterBodies() {
		return notifiedWaterBodies;
	}

	public void setNotifiedWaterBodies(BigDecimal notifiedWaterBodies) {
		this.notifiedWaterBodies = notifiedWaterBodies;
	}

	public BigDecimal getOtherNotifiedWaterBodies() {
		return otherNotifiedWaterBodies;
	}

	public void setOtherNotifiedWaterBodies(BigDecimal otherNotifiedWaterBodies) {
		this.otherNotifiedWaterBodies = otherNotifiedWaterBodies;
	}

	public BigDecimal getOtherLargePondsWaterBodies() {
		return otherLargePondsWaterBodies;
	}

	public void setOtherLargePondsWaterBodies(BigDecimal otherLargePondsWaterBodies) {
		this.otherLargePondsWaterBodies = otherLargePondsWaterBodies;
	}

	@Override
	public String toString() {
		return "DistanceFromWaterBodiesRequirement [river=" + river + ", bharaluMoraBondajan=" + bharaluMoraBondajan
				+ ", otherChannels=" + otherChannels + ", notifiedWaterBodies=" + notifiedWaterBodies
				+ ", otherNotifiedWaterBodies=" + otherNotifiedWaterBodies + ", otherLargePondsWaterBodies="
				+ otherLargePondsWaterBodies + "]";
	}
	
	
}

