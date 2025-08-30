package org.egov.bpa.web.model.landInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.egov.bpa.web.model.AuditDetails;
import org.egov.bpa.web.model.Document;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * LandInfo
 */
@ApiModel(description = "Details of the land information")
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LandInfo {

  /** Unique Identifier(UUID) of the land for internal reference */
  @SafeHtml
  @JsonProperty("id")
  @Size(min = 1, max = 64)
  private String id;

  /** Unique formatted Identifier of the Land */
  @SafeHtml
  @JsonProperty("landUId")
  @Size(min = 1, max = 64)
  private String landUId;

  /** Unique Identifier of the Land in municipal department (e.g., registration no, survey no, etc.) */
  @SafeHtml
  @JsonProperty("landUniqueRegNo")
  @Size(min = 1, max = 64)
  private String landUniqueRegNo;

  /** Tenant ID of the Property */
  @SafeHtml
  @JsonProperty("tenantId")
  @NotNull
  @Size(min = 2, max = 256)
  private String tenantId;

  /** Status of the land */
  @JsonProperty("status")
  @Valid
  private Status status;

  /** Address details of the land */
  @JsonProperty("address")
  @NotNull
  @Valid
  private Address address;

  /** The type of ownership of the property */
  @SafeHtml
  @JsonProperty("ownershipCategory")
  @Size(max = 64)
  private String ownershipCategory;

  /** Property owners, these will be citizen users in the system */
  @JsonProperty("owners")
  @NotNull
  @Valid
  private List<OwnerInfo> owners = new ArrayList<>();

  /** Institution details associated with the land */
  @JsonProperty("institution")
  @Valid
  private Institution institution;

  /** Source of the land information */
  @JsonProperty("source")
  @Valid
  private Source source;

  /** Channel through which the land information was obtained */
  @JsonProperty("channel")
  @Valid
  private Channel channel;

  private String oldDagNumber;

  private String newDagNumber;

  private String oldPattaNumber;

  private String newPattaNumber;

  private BigDecimal totalPlotArea;

  /** Documents attached to the land */
  @JsonProperty("documents")
  @Valid
  private List<Document> documents;

  /** Unit details of the plot */
  @JsonProperty("unit")
  @Valid
  private List<Unit> unit;

  /** Additional details in JSON format */
  @JsonProperty("additionalDetails")
  private Object additionalDetails;

  /** Audit details of the land */
  @JsonProperty("auditDetails")
  @Valid
  private AuditDetails auditDetails;

}