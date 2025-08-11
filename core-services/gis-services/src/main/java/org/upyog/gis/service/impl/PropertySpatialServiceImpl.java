package org.upyog.gis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.upyog.gis.service.ModuleSpatialService;
import org.upyog.gis.util.GISConstants;
import org.upyog.gis.web.contracts.GISSearchRequest;
import org.upyog.gis.web.contracts.GISSearchResponse;

import java.util.*;

/**
 * Implementation of ModuleSpatialService for property module
 * Handles fetching and transforming property spatial data
 */
@Service
@Slf4j
public class PropertySpatialServiceImpl implements ModuleSpatialService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${property.service.host}")
    private String propertyServiceHost;

    @Value("${property.service.search.path}")
    private String propertySearchPath;

    private static final List<String> SUPPORTED_SEARCH_PARAMS = Arrays.asList(
            // Property IDs and references
            "propertyIds", "propertyDetailids", "oldpropertyids", "acknowledgementIds", "uuids", "ownerIds",
            // Owner information
            "mobileNumber", "name",
            // Property details
            "propertyType", "ownershipCategory", "usageCategory", "oldPropertyId",
            // Location details
            "locality", "city", "district", "state", "pincode",
            // Status and workflow
            "status", "approvalStatus", "creationReason",
            // Date ranges
            "fromDate", "toDate",
            // Pagination
            "offset", "limit",
            // Account and financial
            "accountId",
            // Geometry
            "includeGeometry"
    );

    @Override
    public String getModuleName() {
        return GISConstants.MODULE_PROPERTY;
    }

    /**
     * Fetches spatial data for property module by calling property service
     * 
     * @param request GIS search request with criteria
     * @return List of spatial entities with geometry and properties
     * @throws RuntimeException if unable to fetch data from property service
     */
    @Override
    public List<Map<String, Object>> fetchSpatialData(GISSearchRequest request) {
        log.info("Property spatial data fetch started. RequestId: {}, Criteria: {}", 
                request.getRequestInfo().getMsgId(), request.getSearchCriteria());

        try {
            Map<String, Object> requestInfoWrapper = buildRequestInfoWrapper(request);
            UriComponentsBuilder builder = buildPropertyServiceUrl(request);
            HttpEntity<Map<String, Object>> httpEntity = buildHttpEntity(requestInfoWrapper);
            
            String url = builder.toUriString();
            log.debug("Calling property service. RequestId: {}, URL: {}", 
                    request.getRequestInfo().getMsgId(), url);
            
            Map<String, Object> response = callPropertyService(url, httpEntity);
            log.debug("Property service response received. RequestId: {}, ResponseSize: {}", 
                    request.getRequestInfo().getMsgId(), response != null ? response.size() : 0);
            return transformPropertyResponse(response);

        } catch (Exception e) {
            log.error("Failed to fetch spatial data from property service. RequestId: {}, Error: {}", 
                    request.getRequestInfo().getMsgId(), e.getMessage());
            throw new RuntimeException("Failed to fetch spatial data from property service: " + e.getMessage(), e);
        }
    }

    /**
     * Builds the request info wrapper for property service call
     * 
     * @param request GIS search request
     * @return Request info wrapper map
     */
    private Map<String, Object> buildRequestInfoWrapper(GISSearchRequest request) {
        Map<String, Object> requestInfoWrapper = new HashMap<>();
        requestInfoWrapper.put("RequestInfo", request.getRequestInfo());
        return requestInfoWrapper;
    }

    /**
     * Builds the property service URL with query parameters
     * 
     * @param request GIS search request
     * @return UriComponentsBuilder with all query parameters
     */
    private UriComponentsBuilder buildPropertyServiceUrl(GISSearchRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(propertyServiceHost + propertySearchPath);
        builder.queryParam(GISConstants.QUERY_PARAM_TENANT_ID, request.getTenantId());
        builder.queryParam(GISConstants.QUERY_PARAM_INCLUDE_GEOMETRY, GISConstants.QUERY_PARAM_INCLUDE_GEOMETRY_VALUE);
        
        if (request.getSearchCriteria() != null) {
            for (Map.Entry<String, Object> entry : request.getSearchCriteria().entrySet()) {
                if (SUPPORTED_SEARCH_PARAMS.contains(entry.getKey()) && entry.getValue() != null) {
                    builder.queryParam(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        
        return builder;
    }

    /**
     * Builds HTTP entity for property service call
     * 
     * @param requestInfoWrapper Request info wrapper
     * @return HttpEntity with proper headers
     */
    private HttpEntity<Map<String, Object>> buildHttpEntity(Map<String, Object> requestInfoWrapper) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(GISConstants.HTTP_MEDIA_TYPE_JSON));
        return new HttpEntity<>(requestInfoWrapper, headers);
    }

    /**
     * Calls the property service and returns response
     * 
     * @param url Service URL
     * @param httpEntity HTTP entity with request
     * @return Property service response
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> callPropertyService(String url, HttpEntity<Map<String, Object>> httpEntity) {
        return (Map<String, Object>) restTemplate.exchange(
                url, 
                HttpMethod.POST, 
                httpEntity, 
                Map.class
        ).getBody();
    }

    @Override
    public Long getTotalCount(GISSearchRequest request) {
        // For now, return the size of fetched data
        // In production, this should be a separate count API call
        return (long) fetchSpatialData(request).size();
    }

    @Override
    public void validateSearchCriteria(Map<String, Object> searchCriteria) {
        if (searchCriteria == null || searchCriteria.isEmpty()) {
            log.info("Search Criteria is Empty");
            return; // Allow empty criteria to fetch all properties
        }

        for (String key : searchCriteria.keySet()) {
            if (!SUPPORTED_SEARCH_PARAMS.contains(key)) {
                log.warn("Unsupported search parameter for property module: {}", key);
            }
        }
    }

    @Override
    public List<String> getSupportedSearchParameters() {
        return new ArrayList<>(SUPPORTED_SEARCH_PARAMS);
    }



    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> transformPropertyResponse(Map<String, Object> response) {
        List<Map<String, Object>> spatialData = new ArrayList<>();

        if (response == null || !response.containsKey("Properties")) {
            log.warn("No properties found in response");
            return spatialData;
        }

        List<Map<String, Object>> properties = (List<Map<String, Object>>) response.get("Properties");
        
        log.debug("Processing {} properties for spatial data transformation", properties.size());
        
        for (Map<String, Object> property : properties) {
            try {
                Map<String, Object> transformedProperty = transformSingleProperty(property);
                if (transformedProperty != null) {
                    spatialData.add(transformedProperty);
                }
            } catch (Exception e) {
                log.error("Failed to transform property. PropertyId: {}, Error: {}", 
                        property.get("propertyId"), e.getMessage());
            }
        }

        log.debug("Successfully transformed {} properties to spatial data", spatialData.size());
        return spatialData;
    }

    private Map<String, Object> transformSingleProperty(Map<String, Object> property) {
        String propertyId = (String) property.get("propertyId");
        String tenantId = (String) property.get("tenantId");

        // Build properties map
        Map<String, Object> transformedProperty = new HashMap<>();
        transformedProperty.put("propertyId", propertyId);
        transformedProperty.put("tenantId", tenantId);
        transformedProperty.put("propertyType", property.get("propertyType"));
        transformedProperty.put("ownershipCategory", property.get("ownershipCategory"));
        transformedProperty.put("usageCategory", property.get("usageCategory"));
        transformedProperty.put("landArea", property.get("landArea"));
        transformedProperty.put("superBuiltUpArea", property.get("superBuiltUpArea"));
        
        // Add address information
        if (property.containsKey("address")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> address = (Map<String, Object>) property.get("address");
            transformedProperty.put("address", address);
            transformedProperty.put("locality", address.get("locality"));
            transformedProperty.put("city", address.get("city"));
            transformedProperty.put("district", address.get("district"));
            transformedProperty.put("state", address.get("state"));
            transformedProperty.put("pincode", address.get("pincode"));
            
            // Extract coordinates if available
            if (address.containsKey("geoLocation")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> geoLocation = (Map<String, Object>) address.get("geoLocation");
                Double latitude = (Double) geoLocation.get("latitude");
                Double longitude = (Double) geoLocation.get("longitude");
                
                if (latitude != null && longitude != null) {
                    Map<String, Object> coordinates = new HashMap<>();
                    coordinates.put("latitude", latitude);
                    coordinates.put("longitude", longitude);
                    transformedProperty.put("coordinates", coordinates);
                }
            }
        }

        // Add owner information
        if (property.containsKey("owners")) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> owners = (List<Map<String, Object>>) property.get("owners");
            if (!owners.isEmpty()) {
                Map<String, Object> primaryOwner = owners.get(0);
                transformedProperty.put("ownerName", primaryOwner.get("name"));
                transformedProperty.put("ownerMobileNumber", primaryOwner.get("mobileNumber"));
            }
        }

        // Add geometry if available
        if (property.containsKey("geometry")) {
            transformedProperty.put("geometry", property.get("geometry"));
        }

        return transformedProperty;
    }
}
