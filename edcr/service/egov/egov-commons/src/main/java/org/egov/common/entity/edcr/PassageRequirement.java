package org.egov.common.entity.edcr;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PassageRequirement extends MdmsFeatureRule {

    @JsonProperty("passageServiceValueOne")
    private BigDecimal passageServiceValueOne;
    @JsonProperty("passageServiceValueTwo")
    private BigDecimal passageServiceValueTwo;
    @JsonProperty("passageServiceValueA")
    private BigDecimal passageServiceValueA;
    @JsonProperty("passageServiceValueBD")
    private BigDecimal passageServiceValueBD;
    @JsonProperty("passageServiceValueDefault")
    private BigDecimal passageServiceValueDefault;
    @JsonProperty("passageServiceValueFH")
    private BigDecimal passageServiceValueFH;
    @JsonProperty("passageServiceValueFlow")
    private BigDecimal passageServiceValueFlow;
    @JsonProperty("passageServiceValueFhigh")
    private BigDecimal passageServiceValueFhigh;
    @JsonProperty("passageServicePassageLength")
    private BigDecimal passageServicePassageLength;


    public BigDecimal getPassageServiceValueOne() {
        return passageServiceValueOne;
    }

    public void setPassageServiceValueOne(BigDecimal passageServiceValueOne) {
        this.passageServiceValueOne = passageServiceValueOne;
    }

    public BigDecimal getPassageServiceValueA() {
        return passageServiceValueA;
    }

    public void setPassageServiceValueA(BigDecimal passageServiceValueA) {
        this.passageServiceValueA = passageServiceValueA;
    }

    public BigDecimal getPassageServiceValueBD() {
        return passageServiceValueBD;
    }

    public void setPassageServiceValueBD(BigDecimal passageServiceValueBD) {
        this.passageServiceValueBD = passageServiceValueBD;
    }

    public BigDecimal getPassageServiceValueDefault() {
        return passageServiceValueDefault;
    }

    public void setPassageServiceValueDefault(BigDecimal passageServiceValueDefault) {
        this.passageServiceValueDefault = passageServiceValueDefault;
    }

    public BigDecimal getPassageServiceValueFH() {
        return passageServiceValueFH;
    }

    public void setPassageServiceValueFH(BigDecimal passageServiceValueFH) {
        this.passageServiceValueFH = passageServiceValueFH;
    }

    public BigDecimal getPassageServiceValueFlow() {
        return passageServiceValueFlow;
    }

    public void setPassageServiceValueFlow(BigDecimal passageServiceValueFlow) {
        this.passageServiceValueFlow = passageServiceValueFlow;
    }

    public BigDecimal getPassageServiceValueFhigh() {
        return passageServiceValueFhigh;
    }

    public void setPassageServiceValueFhigh(BigDecimal passageServiceValueFhigh) {
        this.passageServiceValueFhigh = passageServiceValueFhigh;
    }

    public BigDecimal getPassageServicePassageLength() {
        return passageServicePassageLength;
    }

    public void setPassageServicePassageLength(BigDecimal passageServicePassageLength) {
        this.passageServicePassageLength = passageServicePassageLength;
    }

    public BigDecimal getPassageServiceValueTwo() {
        return passageServiceValueTwo;
    }

    public void setPassageServiceValueTwo(BigDecimal passageServiceValueTwo) {
        this.passageServiceValueTwo = passageServiceValueTwo;
    }

    @Override
    public String toString() {
        return "PassageRequirement [passageServiceValueOne=" + passageServiceValueOne
                + ", passageServiceValueA=" + passageServiceValueA
                + ", passageServiceValueBD=" + passageServiceValueBD
                + ", passageServiceValueDefault=" + passageServiceValueDefault
                + ", passageServiceValueFH=" + passageServiceValueFH
                + ", passageServiceValueFlow=" + passageServiceValueFlow
                + ", passageServiceValueFhigh=" + passageServiceValueFhigh
                + ", passageServicePassageLength=" + passageServicePassageLength + "]";
    }

}
