package org.egov.common.entity.edcr;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParkingRequirement extends MdmsFeatureRule {

    @JsonProperty("noOfParking")
    private BigDecimal noOfParking;

    @JsonProperty("perArea")
    private BigDecimal perArea;

    @JsonProperty("permissibleCar")
    private BigDecimal permissibleCar;
    
    @JsonProperty("permissibleCarOpen")
    private BigDecimal permissibleCarOpen;
    
    @JsonProperty("permissibleCarStilt")
    private BigDecimal permissibleCarStilt;
    
    @JsonProperty("permissibleCarBasement")
    private BigDecimal permissibleCarBasement;

    public BigDecimal getPermissibleCarOpen() {
		return permissibleCarOpen;
	}
	public void setPermissibleCarOpen(BigDecimal permissibleCarOpen) {
		this.permissibleCarOpen = permissibleCarOpen;
	}
	public BigDecimal getPermissibleCarStilt() {
		return permissibleCarStilt;
	}
	public void setPermissibleCarStilt(BigDecimal permissibleCarStilt) {
		this.permissibleCarStilt = permissibleCarStilt;
	}
	public BigDecimal getPermissibleCarBasement() {
		return permissibleCarBasement;
	}
	public void setPermissibleCarBasement(BigDecimal permissibleCarBasement) {
		this.permissibleCarBasement = permissibleCarBasement;
	}

	@JsonProperty("permissibleTwoWheeler")
    private BigDecimal permissibleTwoWheeler;

    @JsonProperty("permissibleVisitor")
    private BigDecimal permissibleVisitor;

    @JsonProperty("perAreaCar")
    private BigDecimal perAreaCar;

    @JsonProperty("perAreaTwoWheeler")
    private BigDecimal perAreaTwoWheeler;

    @JsonProperty("perAreaVisitor")
    private BigDecimal perAreaVisitor;

    // ----- Hotels -----
    @JsonProperty("perAreaHotelWithoutBanquetCar")
    private BigDecimal perAreaHotelWithoutBanquetCar;

    @JsonProperty("perRoomHotelWithoutBanquetCar")
    private BigDecimal perRoomHotelWithoutBanquetCar;

    @JsonProperty("perAreaHotelBanquetCar")
    private BigDecimal perAreaHotelBanquetCar;

    @JsonProperty("perRoomHotelBanquetCar")
    private BigDecimal perRoomHotelBanquetCar;

    @JsonProperty("perRoomHotelsCar")
    private BigDecimal perRoomHotelsCar;

    // ----- Commercial -----
    @JsonProperty("perAreaCommercialBusinessCar")
    private BigDecimal perAreaCommercialBusinessCar;

    @JsonProperty("perAreaCommercialShopsCar")
    private BigDecimal perAreaCommercialShopsCar;

    // ----- Institutional -----
    @JsonProperty("perAreaInstitutionalPSPCar")
    private BigDecimal perAreaInstitutionalPSPCar;

    @JsonProperty("perAreaInstitutionalMedicalCar")
    private BigDecimal perAreaInstitutionalMedicalCar;

    @JsonProperty("perBedInstitutionalMedicalCar")
    private BigDecimal perBedInstitutionalMedicalCar;

    // ----- Educational -----
    @JsonProperty("perAreaEducationalNurseryCar")
    private BigDecimal perAreaEducationalNurseryCar;

    @JsonProperty("perAreaEducationalSchoolsCar")
    private BigDecimal perAreaEducationalSchoolsCar;

    // ----- Assembly -----
    @JsonProperty("perSeatAssemblyCinemaCar")
    private BigDecimal perSeatAssemblyCinemaCar;

    @JsonProperty("perPlotAreaAssemblyCommunityCar")
    private BigDecimal perPlotAreaAssemblyCommunityCar;

    @JsonProperty("perSeatAssemblyStadiumCar")
    private BigDecimal perSeatAssemblyStadiumCar;

    // ----- Industrial -----
    @JsonProperty("perAreaIndustrialCar")
    private BigDecimal perAreaIndustrialCar;

    // ----- Wholesale -----
    @JsonProperty("perAreaWholesaleCar")
    private BigDecimal perAreaWholesaleCar;
    
    @JsonProperty("perAreaIndustrialTwoWheeler")
    private BigDecimal perAreaIndustrialTwoWheeler;

    @JsonProperty("perAreaInstitutionalPSPTwoWheeler")
    private BigDecimal perAreaInstitutionalPSPTwoWheeler;

    @JsonProperty("perBedInstitutionalMedicalTwoWheeler")
    private BigDecimal perBedInstitutionalMedicalTwoWheeler;

    @JsonProperty("perSeatAssemblyCinemaTwoWheeler")
    private BigDecimal perSeatAssemblyCinemaTwoWheeler;

    @JsonProperty("perAreaInstitutionalMedicalVisitor")
    private BigDecimal perAreaInstitutionalMedicalVisitor;

    @JsonProperty("perAreaInstitutionalPSPVisitor")
    private BigDecimal perAreaInstitutionalPSPVisitor;


    public BigDecimal getPerAreaIndustrialTwoWheeler() {
		return perAreaIndustrialTwoWheeler;
	}
	public void setPerAreaIndustrialTwoWheeler(BigDecimal perAreaIndustrialTwoWheeler) {
		this.perAreaIndustrialTwoWheeler = perAreaIndustrialTwoWheeler;
	}
	public BigDecimal getPerAreaInstitutionalPSPTwoWheeler() {
		return perAreaInstitutionalPSPTwoWheeler;
	}
	public void setPerAreaInstitutionalPSPTwoWheeler(BigDecimal perAreaInstitutionalPSPTwoWheeler) {
		this.perAreaInstitutionalPSPTwoWheeler = perAreaInstitutionalPSPTwoWheeler;
	}
	public BigDecimal getPerBedInstitutionalMedicalTwoWheeler() {
		return perBedInstitutionalMedicalTwoWheeler;
	}
	public void setPerBedInstitutionalMedicalTwoWheeler(BigDecimal perBedInstitutionalMedicalTwoWheeler) {
		this.perBedInstitutionalMedicalTwoWheeler = perBedInstitutionalMedicalTwoWheeler;
	}
	public BigDecimal getPerSeatAssemblyCinemaTwoWheeler() {
		return perSeatAssemblyCinemaTwoWheeler;
	}
	public void setPerSeatAssemblyCinemaTwoWheeler(BigDecimal perSeatAssemblyCinemaTwoWheeler) {
		this.perSeatAssemblyCinemaTwoWheeler = perSeatAssemblyCinemaTwoWheeler;
	}
	public BigDecimal getPerAreaInstitutionalMedicalVisitor() {
		return perAreaInstitutionalMedicalVisitor;
	}
	public void setPerAreaInstitutionalMedicalVisitor(BigDecimal perAreaInstitutionalMedicalVisitor) {
		this.perAreaInstitutionalMedicalVisitor = perAreaInstitutionalMedicalVisitor;
	}
	public BigDecimal getPerAreaInstitutionalPSPVisitor() {
		return perAreaInstitutionalPSPVisitor;
	}
	public void setPerAreaInstitutionalPSPVisitor(BigDecimal perAreaInstitutionalPSPVisitor) {
		this.perAreaInstitutionalPSPVisitor = perAreaInstitutionalPSPVisitor;
	}
	// ---------- Getters ----------
    public BigDecimal getNoOfParking() { return noOfParking; }
    public BigDecimal getPerArea() { return perArea; }

    public BigDecimal getPermissibleCar() { return permissibleCar; }
    public BigDecimal getPermissibleTwoWheeler() { return permissibleTwoWheeler; }
    public BigDecimal getPermissibleVisitor() { return permissibleVisitor; }

    public BigDecimal getPerAreaCar() { return perAreaCar; }
    public BigDecimal getPerAreaTwoWheeler() { return perAreaTwoWheeler; }
    public BigDecimal getPerAreaVisitor() { return perAreaVisitor; }

    public BigDecimal getPerAreaHotelWithoutBanquetCar() { return perAreaHotelWithoutBanquetCar; }
    public BigDecimal getPerRoomHotelWithoutBanquetCar() { return perRoomHotelWithoutBanquetCar; }
    public BigDecimal getPerAreaHotelBanquetCar() { return perAreaHotelBanquetCar; }
    public BigDecimal getPerRoomHotelBanquetCar() { return perRoomHotelBanquetCar; }
    public BigDecimal getPerRoomHotelsCar() { return perRoomHotelsCar; }

    public BigDecimal getPerAreaCommercialBusinessCar() { return perAreaCommercialBusinessCar; }
    public BigDecimal getPerAreaCommercialShopsCar() { return perAreaCommercialShopsCar; }

    public BigDecimal getPerAreaInstitutionalPSPCar() { return perAreaInstitutionalPSPCar; }
    public BigDecimal getPerAreaInstitutionalMedicalCar() { return perAreaInstitutionalMedicalCar; }
    public BigDecimal getPerBedInstitutionalMedicalCar() { return perBedInstitutionalMedicalCar; }

    public BigDecimal getPerAreaEducationalNurseryCar() { return perAreaEducationalNurseryCar; }
    public BigDecimal getPerAreaEducationalSchoolsCar() { return perAreaEducationalSchoolsCar; }

    public BigDecimal getPerSeatAssemblyCinemaCar() { return perSeatAssemblyCinemaCar; }
    public BigDecimal getPerPlotAreaAssemblyCommunityCar() { return perPlotAreaAssemblyCommunityCar; }
    public BigDecimal getPerSeatAssemblyStadiumCar() { return perSeatAssemblyStadiumCar; }

    public BigDecimal getPerAreaIndustrialCar() { return perAreaIndustrialCar; }
    public BigDecimal getPerAreaWholesaleCar() { return perAreaWholesaleCar; }

    // ---------- Setters ----------
    public void setNoOfParking(BigDecimal noOfParking) { this.noOfParking = noOfParking; }
    public void setPerArea(BigDecimal perArea) { this.perArea = perArea; }

    public void setPermissibleCar(BigDecimal permissibleCar) { this.permissibleCar = permissibleCar; }
    public void setPermissibleTwoWheeler(BigDecimal permissibleTwoWheeler) { this.permissibleTwoWheeler = permissibleTwoWheeler; }
    public void setPermissibleVisitor(BigDecimal permissibleVisitor) { this.permissibleVisitor = permissibleVisitor; }

    public void setPerAreaCar(BigDecimal perAreaCar) { this.perAreaCar = perAreaCar; }
    public void setPerAreaTwoWheeler(BigDecimal perAreaTwoWheeler) { this.perAreaTwoWheeler = perAreaTwoWheeler; }
    public void setPerAreaVisitor(BigDecimal perAreaVisitor) { this.perAreaVisitor = perAreaVisitor; }

    public void setPerAreaHotelWithoutBanquetCar(BigDecimal perAreaHotelWithoutBanquetCar) { this.perAreaHotelWithoutBanquetCar = perAreaHotelWithoutBanquetCar; }
    public void setPerRoomHotelWithoutBanquetCar(BigDecimal perRoomHotelWithoutBanquetCar) { this.perRoomHotelWithoutBanquetCar = perRoomHotelWithoutBanquetCar; }
    public void setPerAreaHotelBanquetCar(BigDecimal perAreaHotelBanquetCar) { this.perAreaHotelBanquetCar = perAreaHotelBanquetCar; }
    public void setPerRoomHotelBanquetCar(BigDecimal perRoomHotelBanquetCar) { this.perRoomHotelBanquetCar = perRoomHotelBanquetCar; }
    public void setPerRoomHotelsCar(BigDecimal perRoomHotelsCar) { this.perRoomHotelsCar = perRoomHotelsCar; }

    public void setPerAreaCommercialBusinessCar(BigDecimal perAreaCommercialBusinessCar) { this.perAreaCommercialBusinessCar = perAreaCommercialBusinessCar; }
    public void setPerAreaCommercialShopsCar(BigDecimal perAreaCommercialShopsCar) { this.perAreaCommercialShopsCar = perAreaCommercialShopsCar; }

    public void setPerAreaInstitutionalPSPCar(BigDecimal perAreaInstitutionalPSPCar) { this.perAreaInstitutionalPSPCar = perAreaInstitutionalPSPCar; }
    public void setPerAreaInstitutionalMedicalCar(BigDecimal perAreaInstitutionalMedicalCar) { this.perAreaInstitutionalMedicalCar = perAreaInstitutionalMedicalCar; }
    public void setPerBedInstitutionalMedicalCar(BigDecimal perBedInstitutionalMedicalCar) { this.perBedInstitutionalMedicalCar = perBedInstitutionalMedicalCar; }

    public void setPerAreaEducationalNurseryCar(BigDecimal perAreaEducationalNurseryCar) { this.perAreaEducationalNurseryCar = perAreaEducationalNurseryCar; }
    public void setPerAreaEducationalSchoolsCar(BigDecimal perAreaEducationalSchoolsCar) { this.perAreaEducationalSchoolsCar = perAreaEducationalSchoolsCar; }

    public void setPerSeatAssemblyCinemaCar(BigDecimal perSeatAssemblyCinemaCar) { this.perSeatAssemblyCinemaCar = perSeatAssemblyCinemaCar; }
    public void setPerPlotAreaAssemblyCommunityCar(BigDecimal perPlotAreaAssemblyCommunityCar) { this.perPlotAreaAssemblyCommunityCar = perPlotAreaAssemblyCommunityCar; }
    public void setPerSeatAssemblyStadiumCar(BigDecimal perSeatAssemblyStadiumCar) { this.perSeatAssemblyStadiumCar = perSeatAssemblyStadiumCar; }

    public void setPerAreaIndustrialCar(BigDecimal perAreaIndustrialCar) { this.perAreaIndustrialCar = perAreaIndustrialCar; }
    public void setPerAreaWholesaleCar(BigDecimal perAreaWholesaleCar) { this.perAreaWholesaleCar = perAreaWholesaleCar; }

    @Override
	public String toString() {
		return "ParkingRequirement [noOfParking=" + noOfParking + ", perArea=" + perArea + ", permissibleCar="
				+ permissibleCar + ", permissibleCarOpen=" + permissibleCarOpen + ", permissibleCarStilt="
				+ permissibleCarStilt + ", permissibleCarBasement=" + permissibleCarBasement
				+ ", permissibleTwoWheeler=" + permissibleTwoWheeler + ", permissibleVisitor=" + permissibleVisitor
				+ ", perAreaCar=" + perAreaCar + ", perAreaTwoWheeler=" + perAreaTwoWheeler + ", perAreaVisitor="
				+ perAreaVisitor + ", perAreaHotelWithoutBanquetCar=" + perAreaHotelWithoutBanquetCar
				+ ", perRoomHotelWithoutBanquetCar=" + perRoomHotelWithoutBanquetCar + ", perAreaHotelBanquetCar="
				+ perAreaHotelBanquetCar + ", perRoomHotelBanquetCar=" + perRoomHotelBanquetCar + ", perRoomHotelsCar="
				+ perRoomHotelsCar + ", perAreaCommercialBusinessCar=" + perAreaCommercialBusinessCar
				+ ", perAreaCommercialShopsCar=" + perAreaCommercialShopsCar + ", perAreaInstitutionalPSPCar="
				+ perAreaInstitutionalPSPCar + ", perAreaInstitutionalMedicalCar=" + perAreaInstitutionalMedicalCar
				+ ", perBedInstitutionalMedicalCar=" + perBedInstitutionalMedicalCar + ", perAreaEducationalNurseryCar="
				+ perAreaEducationalNurseryCar + ", perAreaEducationalSchoolsCar=" + perAreaEducationalSchoolsCar
				+ ", perSeatAssemblyCinemaCar=" + perSeatAssemblyCinemaCar + ", perPlotAreaAssemblyCommunityCar="
				+ perPlotAreaAssemblyCommunityCar + ", perSeatAssemblyStadiumCar=" + perSeatAssemblyStadiumCar
				+ ", perAreaIndustrialCar=" + perAreaIndustrialCar + ", perAreaWholesaleCar=" + perAreaWholesaleCar
				+ ", perAreaIndustrialTwoWheeler=" + perAreaIndustrialTwoWheeler
				+ ", perAreaInstitutionalPSPTwoWheeler=" + perAreaInstitutionalPSPTwoWheeler
				+ ", perBedInstitutionalMedicalTwoWheeler=" + perBedInstitutionalMedicalTwoWheeler
				+ ", perSeatAssemblyCinemaTwoWheeler=" + perSeatAssemblyCinemaTwoWheeler
				+ ", perAreaInstitutionalMedicalVisitor=" + perAreaInstitutionalMedicalVisitor
				+ ", perAreaInstitutionalPSPVisitor=" + perAreaInstitutionalPSPVisitor + "]";
	}
}
