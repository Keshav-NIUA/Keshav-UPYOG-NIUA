package org.upyog.gis.service;

import org.upyog.gis.web.contracts.GISSearchRequest;

import java.util.List;
import java.util.Map;

/**
 * Interface for module-specific spatial data services
 */
public interface ModuleSpatialService {

    /**
     * Get the module name this service handles
     */
    String getModuleName();

    /**
     * Fetch spatial data for the module based on search criteria
     */
    List<Map<String, Object>> fetchSpatialData(GISSearchRequest request);

    /**
     * Get the total count of entities matching search criteria
     */
    Long getTotalCount(GISSearchRequest request);

    /**
     * Validate the search criteria for this module
     */
    void validateSearchCriteria(Map<String, Object> searchCriteria);

    /**
     * Get supported search parameters for this module
     */
    List<String> getSupportedSearchParameters();
}