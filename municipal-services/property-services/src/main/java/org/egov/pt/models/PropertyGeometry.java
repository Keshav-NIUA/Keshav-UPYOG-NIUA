package org.egov.pt.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PropertyGeometry {

    @JsonProperty("id")
    private String id;

    @JsonProperty("propertyId")
    private String propertyId;

    @JsonProperty("tenantId")
    private String tenantId;

    @JsonProperty("geometry")
    private String geometry; // WKT format: POINT(longitude latitude)

    @JsonProperty("auditDetails")
    private AuditDetails auditDetails;
}