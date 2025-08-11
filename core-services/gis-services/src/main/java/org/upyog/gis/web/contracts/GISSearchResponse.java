package org.upyog.gis.web.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egov.common.contract.response.ResponseInfo;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GISSearchResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo responseInfo;

    @JsonProperty("module")
    private String module;

    @JsonProperty("tenantId")
    private String tenantId;

    @JsonProperty("totalCount")
    private Long totalCount;

    @JsonProperty("geoJsonData")
    private GeoJsonResponse geoJsonData;

    @JsonProperty("wmsLayerUrl")
    private String wmsLayerUrl;

    @JsonProperty("wfsLayerUrl")
    private String wfsLayerUrl;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GeoJsonResponse {
        @JsonProperty("type")
        @Builder.Default
        private String type = "FeatureCollection";

        @JsonProperty("features")
        private List<Feature> features;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Feature {
        @JsonProperty("type")
        @Builder.Default
        private String type = "Feature";

        @JsonProperty("id")
        private String id;

        @JsonProperty("geometry")
        private Geometry geometry;

        @JsonProperty("properties")
        private Map<String, Object> properties;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Geometry {
        @JsonProperty("type")
        private String type; // "Point", "Polygon", etc.

        @JsonProperty("coordinates")
        private Object coordinates; // Array format depends on geometry type
    }


}