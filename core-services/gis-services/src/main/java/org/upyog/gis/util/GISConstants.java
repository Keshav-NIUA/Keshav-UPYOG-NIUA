package org.upyog.gis.util;

/**
 * Constants used throughout the GIS service
 */
public class GISConstants {

    // Module names
    public static final String MODULE_PROPERTY = "property";

    // Geometry types
    public static final String GEOMETRY_TYPE_POINT = "Point";
    public static final String GEOMETRY_TYPE_POLYGON = "Polygon";

    public static final String HTTP_MEDIA_TYPE_JSON = "application/json";
    
    // Query Parameters
    public static final String QUERY_PARAM_TENANT_ID = "tenantId";
    public static final String QUERY_PARAM_INCLUDE_GEOMETRY = "includeGeometry";
    public static final String QUERY_PARAM_INCLUDE_GEOMETRY_VALUE = "true";
    
    // GeoJSON Constants
    public static final String GEOJSON_TYPE_FEATURE_COLLECTION = "FeatureCollection";
    public static final String GEOJSON_TYPE_FEATURE = "Feature";
    
    // WKT Geometry Patterns
    public static final String WKT_POINT_PATTERN = "POINT\\s*\\(\\s*([+-]?\\d*\\.?\\d+)\\s+([+-]?\\d*\\.?\\d+)\\s*\\)";

    public static final String LOG_NO_GEOMETRY_AVAILABLE = "No geometry available for entity: {}";
    public static final String LOG_UNSUPPORTED_GEOMETRY_TYPE = "Unsupported WKT geometry type: {}";

}