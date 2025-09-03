package org.egov.common.entity.edcr;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class AdditionalFeatureRequirement extends MdmsFeatureRule {

    @JsonProperty("additionalFeatureMinRequiredFloorHeight")
    private BigDecimal additionalFeatureMinRequiredFloorHeight;
    @JsonProperty("additionalFeatureMaxPermissibleFloorHeight")
    private BigDecimal additionalFeatureMaxPermissibleFloorHeight;
    @JsonProperty("additionalFeatureStiltFloor")
    private  BigDecimal additionalFeatureStiltFloor;
    @JsonProperty("additionalFeatureRoadWidthA")
    private BigDecimal additionalFeatureRoadWidthA;
    @JsonProperty("additionalFeatureRoadWidthB")
    private BigDecimal additionalFeatureRoadWidthB;
    @JsonProperty("additionalFeatureRoadWidthC")
    private BigDecimal additionalFeatureRoadWidthC;
    @JsonProperty("additionalFeatureRoadWidthD")
    private BigDecimal additionalFeatureRoadWidthD;
    @JsonProperty("additionalFeatureRoadWidthE")
    private BigDecimal additionalFeatureRoadWidthE;
    @JsonProperty("additionalFeatureRoadWidthF")
    private BigDecimal additionalFeatureRoadWidthF;

    @JsonProperty("additionalFeatureNewRoadWidthA")
    private BigDecimal additionalFeatureNewRoadWidthA;
    @JsonProperty("additionalFeatureNewRoadWidthB")
    private BigDecimal additionalFeatureNewRoadWidthB;
    @JsonProperty("additionalFeatureNewRoadWidthC")
    private BigDecimal additionalFeatureNewRoadWidthC;

    @JsonProperty("additionalFeatureBuildingHeightA")
    private BigDecimal additionalFeatureBuildingHeightA;
    @JsonProperty("additionalFeatureBuildingHeightB")
    private BigDecimal additionalFeatureBuildingHeightB;
    @JsonProperty("additionalFeatureBuildingHeightServiceFloor")
    private BigDecimal additionalFeatureBuildingHeightServiceFloor;
    @JsonProperty("additionalFeatureTotalBuildingHeight")
    private BigDecimal additionalFeatureTotalBuildingHeight;
    @JsonProperty("additionalFeatureBuildingHeightStiltParking")
    private BigDecimal additionalFeatureBuildingHeightStiltParking;
    @JsonProperty("additionalFeatureBuildingHeightRoofTanks")
    private BigDecimal additionalFeatureBuildingHeightRoofTanks;

    @JsonProperty("additionalFeatureBuildingHeightChimney")
    private BigDecimal additionalFeatureBuildingHeightChimney;
    @JsonProperty("additionalFeatureBuildingHeightServiceRooms")
    private BigDecimal additionalFeatureBuildingHeightServiceRooms;
    @JsonProperty("additionalFeatureBuildingHeightStairCovers")
    private BigDecimal additionalFeatureBuildingHeightStairCovers;
    @JsonProperty("additionalFeatureBuildingHeightRoofArea")
    private BigDecimal additionalFeatureBuildingHeightRoofArea;
    @JsonProperty("additionalFeatureBuildingHeightRWH")
    private BigDecimal additionalFeatureBuildingHeightRWH;
    @JsonProperty("additionalFeatureBuildingHeightCappedPermitted")
    private BigDecimal additionalFeatureBuildingHeightCappedPermitted;
    @JsonProperty("additionalFeatureBuildingHeightMaxPermitted")
    private BigDecimal additionalFeatureBuildingHeightMaxPermitted;

    @JsonProperty("additionalFeatureFloorsAcceptedBC")
    private BigDecimal additionalFeatureFloorsAcceptedBC;
    @JsonProperty("additionalFeatureFloorsAcceptedCD")
    private BigDecimal additionalFeatureFloorsAcceptedCD;
    @JsonProperty("additionalFeatureFloorsAcceptedDE")
    private BigDecimal additionalFeatureFloorsAcceptedDE;
    @JsonProperty("additionalFeatureFloorsAcceptedEF")
    private BigDecimal additionalFeatureFloorsAcceptedEF;

    @JsonProperty("additionalFeatureFloorsNewAcceptedAB")
    private BigDecimal additionalFeatureFloorsNewAcceptedAB;
    @JsonProperty("additionalFeatureFloorsNewAcceptedBC")
    private BigDecimal additionalFeatureFloorsNewAcceptedBC;

    @JsonProperty("additionalFeatureBasementPlotArea")
    private BigDecimal additionalFeatureBasementPlotArea;
    @JsonProperty("additionalFeatureBasementAllowed")
    private BigDecimal additionalFeatureBasementAllowed;
    @JsonProperty("additionalFeatureBarrierValue")
    private BigDecimal additionalFeatureBarrierValue;

    @JsonProperty("afGreenBuildingValueA")
    private BigDecimal afGreenBuildingValueA;
    @JsonProperty("afGreenBuildingValueB")
    private BigDecimal afGreenBuildingValueB;
    @JsonProperty("afGreenBuildingValueC")
    private BigDecimal afGreenBuildingValueC;
    @JsonProperty("afGreenBuildingValueD")
    private BigDecimal afGreenBuildingValueD;


    public BigDecimal getAdditionalFeatureRoadWidthA() {
        return additionalFeatureRoadWidthA;
    }

    public void setAdditionalFeatureRoadWidthA(BigDecimal additionalFeatureRoadWidthA) {
        this.additionalFeatureRoadWidthA = additionalFeatureRoadWidthA;
    }

    public BigDecimal getAdditionalFeatureRoadWidthB() {
        return additionalFeatureRoadWidthB;
    }

    public void setAdditionalFeatureRoadWidthB(BigDecimal additionalFeatureRoadWidthB1) {
        this.additionalFeatureRoadWidthB = additionalFeatureRoadWidthB1;
    }

    public BigDecimal getAdditionalFeatureRoadWidthE() {
        return additionalFeatureRoadWidthE;
    }

    public void setAdditionalFeatureRoadWidthE(BigDecimal additionalFeatureRoadWidthE) {
        this.additionalFeatureRoadWidthE = additionalFeatureRoadWidthE;
    }

    public BigDecimal getAdditionalFeatureRoadWidthD() {
        return additionalFeatureRoadWidthD;
    }

    public void setAdditionalFeatureRoadWidthD(BigDecimal additionalFeatureRoadWidthD) {
        this.additionalFeatureRoadWidthD = additionalFeatureRoadWidthD;
    }

    public BigDecimal getAdditionalFeatureRoadWidthC() {
        return additionalFeatureRoadWidthC;
    }

    public void setAdditionalFeatureRoadWidthC(BigDecimal additionalFeatureRoadWidthC) {
        this.additionalFeatureRoadWidthC = additionalFeatureRoadWidthC;
    }

    public BigDecimal getAdditionalFeatureRoadWidthF() {
        return additionalFeatureRoadWidthF;
    }

    public void setAdditionalFeatureRoadWidthF(BigDecimal additionalFeatureRoadWidthF) {
        this.additionalFeatureRoadWidthF = additionalFeatureRoadWidthF;
    }

    public BigDecimal getAdditionalFeatureMinRequiredFloorHeight() {
        return additionalFeatureMinRequiredFloorHeight;
    }

    public void setAdditionalFeatureMinRequiredFloorHeight(BigDecimal additionalFeatureMinRequiredFloorHeight) {
        this.additionalFeatureMinRequiredFloorHeight = additionalFeatureMinRequiredFloorHeight;
    }

    public BigDecimal getAdditionalFeatureMaxPermissibleFloorHeight() {
        return additionalFeatureMaxPermissibleFloorHeight;
    }

    public void setAdditionalFeatureMaxPermissibleFloorHeight(BigDecimal additionalFeatureMaxPermissibleFloorHeight) {
        this.additionalFeatureMaxPermissibleFloorHeight = additionalFeatureMaxPermissibleFloorHeight;
    }

    public BigDecimal getAdditionalFeatureNewRoadWidthA() {
        return additionalFeatureNewRoadWidthA;
    }

    public void setAdditionalFeatureNewRoadWidthA(BigDecimal additionalFeatureNewRoadWidthA) {
        this.additionalFeatureNewRoadWidthA = additionalFeatureNewRoadWidthA;
    }

    public BigDecimal getAdditionalFeatureNewRoadWidthB() {
        return additionalFeatureNewRoadWidthB;
    }

    public void setAdditionalFeatureNewRoadWidthB(BigDecimal additionalFeatureNewRoadWidthB) {
        this.additionalFeatureNewRoadWidthB = additionalFeatureNewRoadWidthB;
    }

    public BigDecimal getAdditionalFeatureNewRoadWidthC() {
        return additionalFeatureNewRoadWidthC;
    }

    public void setAdditionalFeatureNewRoadWidthC(BigDecimal additionalFeatureNewRoadWidthC) {
        this.additionalFeatureNewRoadWidthC = additionalFeatureNewRoadWidthC;
    }

    public BigDecimal getAdditionalFeatureStiltFloor() {
        return additionalFeatureStiltFloor;
    }

    public void setAdditionalFeatureStiltFloor(BigDecimal additionalFeatureStiltFloor) {
        this.additionalFeatureStiltFloor = additionalFeatureStiltFloor;
    }

    public BigDecimal getAdditionalFeatureFloorsAcceptedBC() {
        return additionalFeatureFloorsAcceptedBC;
    }

    public void setAdditionalFeatureFloorsAcceptedBC(BigDecimal additionalFeatureFloorsAcceptedBC) {
        this.additionalFeatureFloorsAcceptedBC = additionalFeatureFloorsAcceptedBC;
    }

    public BigDecimal getAdditionalFeatureFloorsAcceptedCD() {
        return additionalFeatureFloorsAcceptedCD;
    }

    public void setAdditionalFeatureFloorsAcceptedCD(BigDecimal additionalFeatureFloorsAcceptedCD) {
        this.additionalFeatureFloorsAcceptedCD = additionalFeatureFloorsAcceptedCD;
    }

    public BigDecimal getAdditionalFeatureFloorsAcceptedDE() {
        return additionalFeatureFloorsAcceptedDE;
    }

    public void setAdditionalFeatureFloorsAcceptedDE(BigDecimal additionalFeatureFloorsAcceptedDE) {
        this.additionalFeatureFloorsAcceptedDE = additionalFeatureFloorsAcceptedDE;
    }

    public BigDecimal getAdditionalFeatureFloorsAcceptedEF() {
        return additionalFeatureFloorsAcceptedEF;
    }

    public void setAdditionalFeatureFloorsAcceptedEF(BigDecimal additionalFeatureFloorsAcceptedEF) {
        this.additionalFeatureFloorsAcceptedEF = additionalFeatureFloorsAcceptedEF;
    }

    public BigDecimal getAdditionalFeatureFloorsNewAcceptedAB() {
        return additionalFeatureFloorsNewAcceptedAB;
    }

    public void setAdditionalFeatureFloorsNewAcceptedAB(BigDecimal additionalFeatureFloorsNewAcceptedAB) {
        this.additionalFeatureFloorsNewAcceptedAB = additionalFeatureFloorsNewAcceptedAB;
    }

    public BigDecimal getAdditionalFeatureFloorsNewAcceptedBC() {
        return additionalFeatureFloorsNewAcceptedBC;
    }

    public void setAdditionalFeatureFloorsNewAcceptedBC(BigDecimal additionalFeatureFloorsNewAcceptedBC) {
        this.additionalFeatureFloorsNewAcceptedBC = additionalFeatureFloorsNewAcceptedBC;
    }

    public BigDecimal getAdditionalFeatureBasementPlotArea() {
        return additionalFeatureBasementPlotArea;
    }

    public void setAdditionalFeatureBasementPlotArea(BigDecimal additionalFeatureBasementPlotArea) {
        this.additionalFeatureBasementPlotArea = additionalFeatureBasementPlotArea;
    }

    public BigDecimal getAdditionalFeatureBasementAllowed() {
        return additionalFeatureBasementAllowed;
    }

    public void setAdditionalFeatureBasementAllowed(BigDecimal additionalFeatureBasementAllowed) {
        this.additionalFeatureBasementAllowed = additionalFeatureBasementAllowed;
    }

    public BigDecimal getAdditionalFeatureBarrierValue() {
        return additionalFeatureBarrierValue;
    }

    public void setAdditionalFeatureBarrierValue(BigDecimal additionalFeatureBarrierValue) {
        this.additionalFeatureBarrierValue = additionalFeatureBarrierValue;
    }

    public BigDecimal getAfGreenBuildingValueA() {
        return afGreenBuildingValueA;
    }

    public void setAfGreenBuildingValueA(BigDecimal afGreenBuildingValueA) {
        this.afGreenBuildingValueA = afGreenBuildingValueA;
    }

    public BigDecimal getAfGreenBuildingValueB() {
        return afGreenBuildingValueB;
    }

    public void setAfGreenBuildingValueB(BigDecimal afGreenBuildingValueB) {
        this.afGreenBuildingValueB = afGreenBuildingValueB;
    }

    public BigDecimal getAfGreenBuildingValueC() {
        return afGreenBuildingValueC;
    }

    public void setAfGreenBuildingValueC(BigDecimal afGreenBuildingValueC) {
        this.afGreenBuildingValueC = afGreenBuildingValueC;
    }

    public BigDecimal getAfGreenBuildingValueD() {
        return afGreenBuildingValueD;
    }

    public void setAfGreenBuildingValueD(BigDecimal afGreenBuildingValueD) {
        this.afGreenBuildingValueD = afGreenBuildingValueD;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightA() {
        return additionalFeatureBuildingHeightA;
    }

    public void setAdditionalFeatureBuildingHeightA(BigDecimal additionalFeatureBuildingHeightA) {
        this.additionalFeatureBuildingHeightA = additionalFeatureBuildingHeightA;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightB() {
        return additionalFeatureBuildingHeightB;
    }

    public void setAdditionalFeatureBuildingHeightB(BigDecimal additionalFeatureBuildingHeightB) {
        this.additionalFeatureBuildingHeightB = additionalFeatureBuildingHeightB;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightServiceFloor() {
        return additionalFeatureBuildingHeightServiceFloor;
    }

    public void setAdditionalFeatureBuildingHeightServiceFloor(BigDecimal additionalFeatureBuildingHeightServiceFloor) {
        this.additionalFeatureBuildingHeightServiceFloor = additionalFeatureBuildingHeightServiceFloor;
    }

    public BigDecimal getAdditionalFeatureTotalBuildingHeight() {
        return additionalFeatureTotalBuildingHeight;
    }

    public void setAdditionalFeatureTotalBuildingHeight(BigDecimal additionalFeatureTotalBuildingHeight) {
        this.additionalFeatureTotalBuildingHeight = additionalFeatureTotalBuildingHeight;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightStiltParking() {
        return additionalFeatureBuildingHeightStiltParking;
    }

    public void setAdditionalFeatureBuildingHeightStiltParking(BigDecimal additionalFeatureBuildingHeightStiltParking) {
        this.additionalFeatureBuildingHeightStiltParking = additionalFeatureBuildingHeightStiltParking;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightRoofTanks() {
        return additionalFeatureBuildingHeightRoofTanks;
    }

    public void setAdditionalFeatureBuildingHeightRoofTanks(BigDecimal additionalFeatureBuildingHeightRoofTanks) {
        this.additionalFeatureBuildingHeightRoofTanks = additionalFeatureBuildingHeightRoofTanks;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightChimney() {
        return additionalFeatureBuildingHeightChimney;
    }

    public void setAdditionalFeatureBuildingHeightChimney(BigDecimal additionalFeatureBuildingHeightChimney) {
        this.additionalFeatureBuildingHeightChimney = additionalFeatureBuildingHeightChimney;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightServiceRooms() {
        return additionalFeatureBuildingHeightServiceRooms;
    }

    public void setAdditionalFeatureBuildingHeightServiceRooms(BigDecimal additionalFeatureBuildingHeightServiceRooms) {
        this.additionalFeatureBuildingHeightServiceRooms = additionalFeatureBuildingHeightServiceRooms;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightStairCovers() {
        return additionalFeatureBuildingHeightStairCovers;
    }

    public void setAdditionalFeatureBuildingHeightStairCovers(BigDecimal additionalFeatureBuildingHeightStairCovers) {
        this.additionalFeatureBuildingHeightStairCovers = additionalFeatureBuildingHeightStairCovers;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightRoofArea() {
        return additionalFeatureBuildingHeightRoofArea;
    }

    public void setAdditionalFeatureBuildingHeightRoofArea(BigDecimal additionalFeatureBuildingHeightRoofArea) {
        this.additionalFeatureBuildingHeightRoofArea = additionalFeatureBuildingHeightRoofArea;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightRWH() {
        return additionalFeatureBuildingHeightRWH;
    }

    public void setAdditionalFeatureBuildingHeightRWH(BigDecimal additionalFeatureBuildingHeightRWH) {
        this.additionalFeatureBuildingHeightRWH = additionalFeatureBuildingHeightRWH;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightCappedPermitted() {
        return additionalFeatureBuildingHeightCappedPermitted;
    }

    public void setAdditionalFeatureBuildingHeightCappedPermitted(BigDecimal additionalFeatureBuildingHeightCappedPermitted) {
        this.additionalFeatureBuildingHeightCappedPermitted = additionalFeatureBuildingHeightCappedPermitted;
    }

    public BigDecimal getAdditionalFeatureBuildingHeightMaxPermitted() {
        return additionalFeatureBuildingHeightMaxPermitted;
    }

    public void setAdditionalFeatureBuildingHeightMaxPermitted(BigDecimal additionalFeatureBuildingHeightMaxPermitted) {
        this.additionalFeatureBuildingHeightMaxPermitted = additionalFeatureBuildingHeightMaxPermitted;
    }

    @Override
    public String toString() {
        return "AdditionalFeatureRequirement [additionalFeatureMinRequiredFloorHeight=" + additionalFeatureMinRequiredFloorHeight
                + ", additionalFeatureMaxPermissibleFloorHeight=" + additionalFeatureMaxPermissibleFloorHeight
                + ", additionalFeatureStiltFloor=" + additionalFeatureStiltFloor
                + ", additionalFeatureRoadWidthA=" + additionalFeatureRoadWidthA
                + ", additionalFeatureRoadWidthB=" + additionalFeatureRoadWidthB
                + ", additionalFeatureRoadWidthC=" + additionalFeatureRoadWidthC
                + ", additionalFeatureRoadWidthD=" + additionalFeatureRoadWidthD
                + ", additionalFeatureRoadWidthE=" + additionalFeatureRoadWidthE
                + ", additionalFeatureRoadWidthF=" + additionalFeatureRoadWidthF
                + ", additionalFeatureNewRoadWidthA=" + additionalFeatureNewRoadWidthA
                + ", additionalFeatureNewRoadWidthB=" + additionalFeatureNewRoadWidthB
                + ", additionalFeatureNewRoadWidthC=" + additionalFeatureNewRoadWidthC
                + ", additionalFeatureBuildingHeightA=" + additionalFeatureBuildingHeightA
                + ", additionalFeatureBuildingHeightB=" + additionalFeatureBuildingHeightB
                + ", additionalFeatureBuildingHeightServiceFloor=" + additionalFeatureBuildingHeightServiceFloor
                + ", additionalFeatureTotalBuildingHeight=" + additionalFeatureTotalBuildingHeight
                + ", additionalFeatureBuildingHeightStiltParking=" + additionalFeatureBuildingHeightStiltParking
                + ", additionalFeatureBuildingHeightRoofTanks=" + additionalFeatureBuildingHeightRoofTanks
                + ", additionalFeatureBuildingHeightChimney=" + additionalFeatureBuildingHeightChimney
                + ", additionalFeatureBuildingHeightServiceRooms=" + additionalFeatureBuildingHeightServiceRooms
                + ", additionalFeatureBuildingHeightStairCovers=" + additionalFeatureBuildingHeightStairCovers
                + ", additionalFeatureBuildingHeightRoofArea=" + additionalFeatureBuildingHeightRoofArea
                + ", additionalFeatureBuildingHeightRWH=" + additionalFeatureBuildingHeightRWH
                + ", additionalFeatureBuildingHeightCappedPermitted=" + additionalFeatureBuildingHeightCappedPermitted
                + ", additionalFeatureBuildingHeightMaxPermitted=" + additionalFeatureBuildingHeightMaxPermitted
                + ", additionalFeatureFloorsAcceptedBC=" + additionalFeatureFloorsAcceptedBC
                + ", additionalFeatureFloorsAcceptedCD=" + additionalFeatureFloorsAcceptedCD
                + ", additionalFeatureFloorsAcceptedDE=" + additionalFeatureFloorsAcceptedDE
                + ", additionalFeatureFloorsAcceptedEF=" + additionalFeatureFloorsAcceptedEF
                + ", additionalFeatureFloorsNewAcceptedAB=" + additionalFeatureFloorsNewAcceptedAB
                + ", additionalFeatureFloorsNewAcceptedBC=" + additionalFeatureFloorsNewAcceptedBC
                + ", additionalFeatureBasementPlotArea=" + additionalFeatureBasementPlotArea
                + ", additionalFeatureBasementAllowed=" + additionalFeatureBasementAllowed
                + ", additionalFeatureBarrierValue=" + additionalFeatureBarrierValue
                + ", afGreenBuildingValueA=" + afGreenBuildingValueA
                + ", afGreenBuildingValueB=" + afGreenBuildingValueB
                + ", afGreenBuildingValueC=" + afGreenBuildingValueC
                + ", afGreenBuildingValueD=" + afGreenBuildingValueD + "]";
    }

}
