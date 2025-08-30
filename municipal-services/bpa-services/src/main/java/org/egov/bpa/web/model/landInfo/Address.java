package org.egov.bpa.web.model.landInfo;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.egov.bpa.web.model.AuditDetails;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representation of an address. Individual APIs may choose to extend from this using allOf if more details need to be added in their case.
 */
@ApiModel(description = "Representation of an address. Individual APIs may choose to extend from this using allOf if more details need to be added in their case.")
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

  /** Unique Identifier of the tenant to which the user primarily belongs */
  @SafeHtml
  @JsonProperty("tenantId")
  private String tenantId;

  /** House number or door number */
  @SafeHtml
  @JsonProperty("houseNo")
  private String houseNo;

  /** Address line 1 */
  @SafeHtml
  @JsonProperty("addressLine1")
  private String addressLine1;

  /** Address line 2 */
  @SafeHtml
  @JsonProperty("addressLine2")
  private String addressLine2;

  /** Additional landmark to help locate the address */
  @SafeHtml
  @JsonProperty("landmark")
  private String landmark;

  /** The district in which the property is located */
  @SafeHtml
  @JsonProperty("district")
  private String district;

  /** The region in which the property is located */
  @SafeHtml
  @JsonProperty("region")
  private String region;

  /** The state in which the property is located */
  @SafeHtml
  @JsonProperty("state")
  private String state;

  /** The country in which the property is located */
  @SafeHtml
  @JsonProperty("country")
  private String country;

  /** PIN code of the address. Indian pincodes will usually be all numbers. */
  @SafeHtml
  @JsonProperty("pincode")
  private String pincode;

  /** More address details as may be needed */
  @SafeHtml
  @JsonProperty("additionDetails")
  private String additionDetails;

  /** Geo-location details of the address */
  @JsonProperty("geoLocation")
  @Valid
  private GeoLocation geoLocation;

  /** Audit details of the address */
  @JsonProperty("auditDetails")
  @Valid
  private AuditDetails auditDetails;
}