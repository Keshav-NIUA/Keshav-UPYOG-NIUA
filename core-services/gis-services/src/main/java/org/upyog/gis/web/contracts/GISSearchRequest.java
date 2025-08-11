package org.upyog.gis.web.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egov.common.contract.request.RequestInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GISSearchRequest {

    @JsonProperty("requestInfo")
    @Valid
    private RequestInfo requestInfo;

    @JsonProperty("tenantId")
    @NotNull
    private String tenantId;

    @JsonProperty("module")
    @NotNull
    private String module;

    @JsonProperty("searchCriteria")
    private Map<String, Object> searchCriteria; // Dynamic search criteria based on module

    @JsonProperty("geometryRequired")
    @Builder.Default
    private Boolean geometryRequired = true;

    @JsonProperty("responseFormat")
    @Builder.Default
    private String responseFormat = "geojson"; // "geojson", "wms", "wfs"
}