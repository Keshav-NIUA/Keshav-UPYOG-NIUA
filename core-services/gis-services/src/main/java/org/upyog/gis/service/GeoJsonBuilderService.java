package org.upyog.gis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.upyog.gis.util.GISConstants;
import org.upyog.gis.web.contracts.GISSearchResponse;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for building GeoJSON responses from spatial data
 */
@Service
@Slf4j
public class GeoJsonBuilderService {

    private static final Pattern POINT_PATTERN = Pattern.compile(GISConstants.WKT_POINT_PATTERN);

    /**
     * Builds a GeoJSON response from spatial data
     * 
     * @param spatialData List of spatial entities with geometry and properties
     * @return GeoJSON FeatureCollection response
     */
    public GISSearchResponse.GeoJsonResponse buildGeoJsonResponse(List<Map<String, Object>> spatialData) {
        log.debug("Building GeoJSON response for {} spatial entities", spatialData.size());

        List<GISSearchResponse.Feature> features = new ArrayList<>();

        for (Map<String, Object> entity : spatialData) {
            try {
                GISSearchResponse.Feature feature = buildFeature(entity);
                if (feature != null) {
                    features.add(feature);
                }
            } catch (Exception e) {
                String propertyId = (String) entity.get("propertyId");
                log.error("Failed to build GeoJSON feature for property: {}, Error: {}", propertyId, e.getMessage());
            }
        }

        GISSearchResponse.GeoJsonResponse response = GISSearchResponse.GeoJsonResponse.builder()
                .type(GISConstants.GEOJSON_TYPE_FEATURE_COLLECTION)
                .features(features)
                .build();
        
        log.debug("GeoJSON response built successfully with {} features", features.size());
        return response;
    }

    /**
     * Builds a GeoJSON feature from a spatial entity
     * 
     * @param entity Spatial entity with geometry and properties
     * @return GeoJSON Feature or null if no geometry available
     */
    private GISSearchResponse.Feature buildFeature(Map<String, Object> entity) {
        GISSearchResponse.Geometry geometry = buildGeometry(entity);
        
        if (geometry == null) {
            String propertyId = (String) entity.get("propertyId");
            log.warn(GISConstants.LOG_NO_GEOMETRY_AVAILABLE, propertyId);
            return null;
        }

        return GISSearchResponse.Feature.builder()
                .type(GISConstants.GEOJSON_TYPE_FEATURE)
                .id((String) entity.get("propertyId"))
                .geometry(geometry)
                .properties(entity)
                .build();
    }

    private GISSearchResponse.Geometry buildGeometry(Map<String, Object> entity) {
        // Try to build from WKT geometry first
        if (entity.containsKey("geometry") && entity.get("geometry") != null) {
            return parseWKTGeometry((String) entity.get("geometry"));
        }

        // Fallback to coordinates if available
        if (entity.containsKey("coordinates")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> coordinates = (Map<String, Object>) entity.get("coordinates");
            Double latitude = (Double) coordinates.get("latitude");
            Double longitude = (Double) coordinates.get("longitude");
            
            if (latitude != null && longitude != null) {
                return GISSearchResponse.Geometry.builder()
                        .type(GISConstants.GEOMETRY_TYPE_POINT)
                        .coordinates(Arrays.asList(longitude, latitude))
                        .build();
            }
        }

        return null;
    }

    /**
     * Parses WKT (Well-Known Text) geometry string into GeoJSON geometry
     * 
     * @param wktGeometry WKT geometry string
     * @return GeoJSON geometry or null if parsing fails
     */
    private GISSearchResponse.Geometry parseWKTGeometry(String wktGeometry) {
        if (wktGeometry == null || wktGeometry.trim().isEmpty()) {
            return null;
        }

        try {
            String wkt = wktGeometry.trim().toUpperCase();

            if (wkt.startsWith("POINT")) {
                return parsePointWKT(wkt);
            } else if (wkt.startsWith("POLYGON")) {
                return parsePolygonWKT(wkt);
            } else {
                log.warn(GISConstants.LOG_UNSUPPORTED_GEOMETRY_TYPE, wkt);
                return null;
            }
        } catch (Exception e) {
            log.error("Failed to parse WKT geometry: {}, Error: {}", wktGeometry, e.getMessage());
            return null;
        }
    }

    private GISSearchResponse.Geometry parsePointWKT(String pointWKT) {
        Matcher matcher = POINT_PATTERN.matcher(pointWKT);
        if (matcher.find()) {
            double x = Double.parseDouble(matcher.group(1));
            double y = Double.parseDouble(matcher.group(2));
            
            return GISSearchResponse.Geometry.builder()
                    .type(GISConstants.GEOMETRY_TYPE_POINT)
                    .coordinates(Arrays.asList(x, y))
                    .build();
        }
        return null;
    }

    private GISSearchResponse.Geometry parsePolygonWKT(String polygonWKT) {
        // Basic polygon parsing - can be enhanced for complex polygons
        try {
            // Extract coordinates from POLYGON((x1 y1, x2 y2, ...))
            String coordsStr = polygonWKT.substring(polygonWKT.indexOf("((") + 2, polygonWKT.lastIndexOf("))"));
            String[] coordPairs = coordsStr.split(",");
            
            List<List<Double>> coordinates = new ArrayList<>();
            for (String coordPair : coordPairs) {
                String[] coords = coordPair.trim().split("\\s+");
                if (coords.length >= 2) {
                    coordinates.add(Arrays.asList(
                            Double.parseDouble(coords[0]),
                            Double.parseDouble(coords[1])
                    ));
                }
            }
            
            return GISSearchResponse.Geometry.builder()
                    .type(GISConstants.GEOMETRY_TYPE_POLYGON)
                    .coordinates(Collections.singletonList(coordinates))
                    .build();
        } catch (Exception e) {
            log.error("Error parsing polygon WKT: {}", polygonWKT, e);
            return null;
        }
    }


}