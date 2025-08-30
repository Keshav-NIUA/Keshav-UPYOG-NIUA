package org.egov.bpa.web.model.landInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egov.bpa.web.model.AuditDetails;
import org.egov.bpa.web.model.Document;
import org.egov.common.contract.request.Role;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * OwnerInfo
 */
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerInfoV2 {

	/** Tenant ID of the owner */
	@SafeHtml
	@JsonProperty("tenantId")
	private String tenantId = null;

	/** Name of the owner */
	@SafeHtml
	@JsonProperty("name")
	private String name = null;

	/** Unique ID of the owner */
	@SafeHtml
	@JsonProperty("ownerId")
	private String ownerId = null;

	/** Mobile number of the owner */
	@SafeHtml
	@JsonProperty("mobileNumber")
	private String mobileNumber = null;

	/** Gender of the owner */
	@SafeHtml
	@JsonProperty("gender")
	private String motherName = null;

	/** Father's or husband's name of the owner */
	@SafeHtml
	@JsonProperty("fatherOrHusbandName")
	private String fatherOrHusbandName = null;

	/** Email ID of the owner */
	@Size(max = 128)
	@SafeHtml
	@JsonProperty("emailId")
	private String emailId;

	/** Alternate contact number of the owner */
	@Size(max = 50)
	@SafeHtml
	@JsonProperty("altContactNumber")
	private String altContactNumber;

	/** PAN number of the owner */
	@Size(max = 10)
	@SafeHtml
	@JsonProperty("pan")
	private String pan;

	/** Aadhaar number of the owner */
	@Pattern(regexp = "^[0-9]{12}$", message = "AdharNumber should be 12 digit number")
	@SafeHtml
	@JsonProperty("aadhaarNumber")
	private String aadhaarNumber;

	/** Permanent address of the owner */
	@SafeHtml
	@JsonProperty("permanentAddress")
	private Address permanentAddress;

	/** Correspondence address of the owner */
	@SafeHtml
	@JsonProperty("correspondenceAddress")
	private Address correspondenceAddress = null;

	/** Indicates if the owner is the primary owner */
	@JsonProperty("isPrimaryOwner")
	private Boolean isPrimaryOwner = null;

	/** Ownership percentage of the owner */
	@JsonProperty("ownerShipPercentage")
	private BigDecimal ownerShipPercentage = null;

	/** Type of the owner */
	@SafeHtml
	@JsonProperty("ownerType")
	private String ownerType = null;

	/** Status of the owner */
	@JsonProperty("status")
	private Boolean status = null;

	/** Institution ID associated with the owner */
	@SafeHtml
	@JsonProperty("institutionId")
	private String institutionId = null;

	/** List of documents associated with the owner */
	@JsonProperty("documents")
	@Valid
	private List<Document> documents = null;

	/** Relationship details of the owner */
	@JsonProperty("relationship")
	private Relationship relationship = null;

	/** Additional details about the owner */
	@JsonProperty("additionalDetails")
	private Object additionalDetails = null;

	/** Created by user ID */
	@Size(max = 64)
	@JsonProperty("createdBy")
	private String createdBy;

	/** Creation timestamp */
	@JsonProperty("createdDate")
	private Long createdDate;

	/** Last modified by user ID */
	@Size(max = 64)
	@JsonProperty("lastModifiedBy")
	private String lastModifiedBy;

	/** Last modification timestamp */
	@JsonProperty("lastModifiedDate")
	private Long lastModifiedDate;

	/** OTP reference for the owner */
	@SafeHtml
	@JsonProperty("otpReference")
	private String otpReference;

	/** Audit details of the owner */
	@JsonProperty("auditDetails")
	private AuditDetails auditDetails;
}