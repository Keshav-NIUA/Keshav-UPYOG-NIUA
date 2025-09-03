package org.egov.common.entity.edcr;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EVChargingRequirement extends MdmsFeatureRule {
	
	    @JsonProperty("twoWheelerSlowCharger")
	    private BigDecimal twoWheelerSlowCharger;
	    
	    @JsonProperty( "twoWheelerFastCharger")
	    private BigDecimal twoWheelerFastCharger;
	    
	    @JsonProperty("fourWheelerSlowCharger")
	    private BigDecimal fourWheelerSlowCharger;
	    
	    @JsonProperty( "fourWheelerFastCharger")
	    private BigDecimal fourWheelerFastCharger;
	    
	    @JsonProperty( "noOfWheelerResidential")
	    private BigDecimal noOfWheelerResidential;
	    
	    @JsonProperty("chargerResidential")
	    private BigDecimal chargerResidential;
	    
		@JsonProperty("threeWheelerSlowCharger")
	    private BigDecimal threeWheelerSlowCharger;
	    
	    @JsonProperty( "threeWheelerFastCharger")
	    private BigDecimal threeWheelerFastCharger;
	    
	    @JsonProperty("PVslowCharger")
	    private BigDecimal PVslowCharger;
	    
	    @JsonProperty( "PVfastCharger")
	    private BigDecimal PVfastCharger;
	    
	    @JsonProperty("noOfFourWheelerForSlowCharger")
	    private BigDecimal noOfFourWheelerForSlowCharger;
	    
	    @JsonProperty("noOfThreeWheelerForSlowCharger")
	    private BigDecimal noOfThreeWheelerForSlowCharger;
	    
	    @JsonProperty("noOfTwoWheelerForSlowCharger")
	    private BigDecimal noOfTwoWheelerForSlowCharger;
	    
	    @JsonProperty("noOfPVForSlowCharger")
	    private BigDecimal noOfPVForSlowCharger;
	    
	    @JsonProperty("noOfFourWheelerForfastCharger")
	    private BigDecimal noOfFourWheelerForfastCharger;
	    
	    @JsonProperty("noOfThreeWheelerForFastCharger")
	    private BigDecimal noOfThreeWheelerForFastCharger;
	    
	    @JsonProperty("noOfTwoWheelerForFastCharger")
	    private BigDecimal noOfTwoWheelerForFastCharger;
	    
	    @JsonProperty("noOfPVForFastCharger")
	    private BigDecimal noOfPVForFastCharger;
	    
	    public BigDecimal getChargerResidential() {
			return chargerResidential;
		}

		public void setChargerResidential(BigDecimal chargerResidential) {
			this.chargerResidential = chargerResidential;
		}

		public BigDecimal getTwoWheelerSlowCharger() {
			return twoWheelerSlowCharger;
		}

		public void setTwoWheelerSlowCharger(BigDecimal twoWheelerSlowCharger) {
			this.twoWheelerSlowCharger = twoWheelerSlowCharger;
		}

		public BigDecimal getTwoWheelerFastCharger() {
			return twoWheelerFastCharger;
		}

		public void setTwoWheelerFastCharger(BigDecimal twoWheelerFastCharger) {
			this.twoWheelerFastCharger = twoWheelerFastCharger;
		}

		public BigDecimal getFourWheelerSlowCharger() {
			return fourWheelerSlowCharger;
		}

		public void setFourWheelerSlowCharger(BigDecimal fourWheelerSlowCharger) {
			this.fourWheelerSlowCharger = fourWheelerSlowCharger;
		}

		public BigDecimal getFourWheelerFastCharger() {
			return fourWheelerFastCharger;
		}

		public void setFourWheelerFastCharger(BigDecimal fourWheelerFastCharger) {
			this.fourWheelerFastCharger = fourWheelerFastCharger;
		}

		public BigDecimal getThreeWheelerSlowCharger() {
			return threeWheelerSlowCharger;
		}

		public void setThreeWheelerSlowCharger(BigDecimal threeWheelerSlowCharger) {
			this.threeWheelerSlowCharger = threeWheelerSlowCharger;
		}

		public BigDecimal getThreeWheelerFastCharger() {
			return threeWheelerFastCharger;
		}

		public void setThreeWheelerFastCharger(BigDecimal threeWheelerFastCharger) {
			this.threeWheelerFastCharger = threeWheelerFastCharger;
		}

		public BigDecimal getPVslowCharger() {
			return PVslowCharger;
		}

		public void setPVslowCharger(BigDecimal pVslowCharger) {
			PVslowCharger = pVslowCharger;
		}

		public BigDecimal getPVfastCharger() {
			return PVfastCharger;
		}

		public void setPVfastCharger(BigDecimal pVfastCharger) {
			PVfastCharger = pVfastCharger;
		}

		public BigDecimal getNoOfFourWheelerForSlowCharger() {
			return noOfFourWheelerForSlowCharger;
		}

		public void setNoOfFourWheelerForSlowCharger(BigDecimal noOfFourWheelerForSlowCharger) {
			this.noOfFourWheelerForSlowCharger = noOfFourWheelerForSlowCharger;
		}

		public BigDecimal getNoOfThreeWheelerForSlowCharger() {
			return noOfThreeWheelerForSlowCharger;
		}

		public void setNoOfThreeWheelerForSlowCharger(BigDecimal noOfThreeWheelerForSlowCharger) {
			this.noOfThreeWheelerForSlowCharger = noOfThreeWheelerForSlowCharger;
		}

		public BigDecimal getNoOfTwoWheelerForSlowCharger() {
			return noOfTwoWheelerForSlowCharger;
		}

		public void setNoOfTwoWheelerForSlowCharger(BigDecimal noOfTwoWheelerForSlowCharger) {
			this.noOfTwoWheelerForSlowCharger = noOfTwoWheelerForSlowCharger;
		}

		public BigDecimal getNoOfPVForSlowCharger() {
			return noOfPVForSlowCharger;
		}

		public void setNoOfPVForSlowCharger(BigDecimal noOfPVForSlowCharger) {
			this.noOfPVForSlowCharger = noOfPVForSlowCharger;
		}

		public BigDecimal getNoOfFourWheelerForfastCharger() {
			return noOfFourWheelerForfastCharger;
		}

		public void setNoOfFourWheelerForfastCharger(BigDecimal noOfFourWheelerForfastCharger) {
			this.noOfFourWheelerForfastCharger = noOfFourWheelerForfastCharger;
		}

		public BigDecimal getNoOfThreeWheelerForFastCharger() {
			return noOfThreeWheelerForFastCharger;
		}

		public void setNoOfThreeWheelerForFastCharger(BigDecimal noOfThreeWheelerForFastCharger) {
			this.noOfThreeWheelerForFastCharger = noOfThreeWheelerForFastCharger;
		}

		public BigDecimal getNoOfTwoWheelerForFastCharger() {
			return noOfTwoWheelerForFastCharger;
		}

		public void setNoOfTwoWheelerForFastCharger(BigDecimal noOfTwoWheelerForFastCharger) {
			this.noOfTwoWheelerForFastCharger = noOfTwoWheelerForFastCharger;
		}

		public BigDecimal getNoOfPVForFastCharger() {
			return noOfPVForFastCharger;
		}

		public void setNoOfPVForFastCharger(BigDecimal noOfPVForFastCharger) {
			this.noOfPVForFastCharger = noOfPVForFastCharger;
		}
		
		public BigDecimal getnoOfWheelerResidential() {
			return noOfWheelerResidential;
		}

		public void setnoOfWheelerResidential(BigDecimal noOfWheelerResidential) {
				this.noOfWheelerResidential = noOfWheelerResidential;
		}


		@Override
		public String toString() {
			return "EVChargingRequirement [twoWheelerSlowCharger=" + twoWheelerSlowCharger + ", twoWheelerFastCharger="
					+ twoWheelerFastCharger + ", fourWheelerSlowCharger=" + fourWheelerSlowCharger
					+ ", fourWheelerFastCharger=" + fourWheelerFastCharger + ", noOfWheelerResidential="
					+ noOfWheelerResidential + ", threeWheelerSlowCharger=" + threeWheelerSlowCharger
					+ ", threeWheelerFastCharger=" + threeWheelerFastCharger + ", PVslowCharger=" + PVslowCharger
					+ ", PVfastCharger=" + PVfastCharger + ", noOfFourWheelerForSlowCharger="
					+ noOfFourWheelerForSlowCharger + ", noOfThreeWheelerForSlowCharger="
					+ noOfThreeWheelerForSlowCharger + ", noOfTwoWheelerForSlowCharger=" + noOfTwoWheelerForSlowCharger
					+ ", noOfPVForSlowCharger=" + noOfPVForSlowCharger + ", noOfFourWheelerForfastCharger="
					+ noOfFourWheelerForfastCharger + ", noOfThreeWheelerForFastCharger="
					+ noOfThreeWheelerForFastCharger + ", noOfTwoWheelerForFastCharger=" + noOfTwoWheelerForFastCharger
					+ ", noOfPVForFastCharger=" + noOfPVForFastCharger + "]";
		}

		
}


