package org.upyog.gis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upyog.gis.util.GISConstants;
import org.upyog.gis.web.contracts.GISSearchRequest;
import org.upyog.gis.web.contracts.GISSearchResponse;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GISSpatialService {

    @Autowired
    private List<ModuleSpatialService> moduleSpatialServices;

    @Autowired
    private GeoJsonBuilderService geoJsonBuilderService;

    public GISSearchResponse searchSpatialData(GISSearchRequest request) {
        log.info("Searching spatial data Module: {}, RequestId: {}",
                request.getModule(), request.getRequestInfo().getMsgId());

        // Find the appropriate module service
        ModuleSpatialService moduleService = getModuleService(request.getModule());
        
        // Validate search criteria
        moduleService.validateSearchCriteria(request.getSearchCriteria());

        // Fetch spatial data
        List<Map<String, Object>> spatialData = 
                moduleService.fetchSpatialData(request);

        // Get total count
        Long totalCount = moduleService.getTotalCount(request);

        // Build response
        GISSearchResponse.GISSearchResponseBuilder responseBuilder = GISSearchResponse.builder()
                .module(request.getModule())
                .tenantId(request.getTenantId())
                .totalCount(totalCount);

        // Build response based on format
        if (request.getGeometryRequired() && !spatialData.isEmpty()) {
            if ("geojson".equals(request.getResponseFormat())) {
                GISSearchResponse.GeoJsonResponse geoJsonData = 
                        geoJsonBuilderService.buildGeoJsonResponse(spatialData);
                responseBuilder.geoJsonData(geoJsonData);
            } else if ("wms".equals(request.getResponseFormat())) {
                String wmsUrl = generateWMSLayerUrl(request.getModule(), request.getTenantId(), request.getSearchCriteria());
                responseBuilder.wmsLayerUrl(wmsUrl);
            } else if ("wfs".equals(request.getResponseFormat())) {
                String wfsUrl = generateWFSLayerUrl(request.getModule(), request.getTenantId(), request.getSearchCriteria());
                responseBuilder.wfsLayerUrl(wfsUrl);
            }
        }

        GISSearchResponse response = responseBuilder.build();
        log.info("=== GIS SPATIAL SERVICE SUCCESS === Module: {}, RequestId: {}, TotalCount: {}", 
                request.getModule(), request.getRequestInfo().getMsgId(), response.getTotalCount());
        return response;
    }

    // GeoServer integration methods
    public String generateWMSLayerUrl(String module, String tenantId, Map<String, Object> searchCriteria) {
        // TODO: Implement GeoServer WMS layer generation
        // This would create a temporary layer in GeoServer with the search results
        // and return the WMS URL for the frontend to consume
        return "http://localhost:8080/geoserver/wms?service=WMS&version=1.1.0&request=GetMap&layers=" + 
               module + "_" + tenantId + "&bbox=-180,-90,180,90&width=768&height=384&srs=EPSG:4326";
    }

    public String generateWFSLayerUrl(String module, String tenantId, Map<String, Object> searchCriteria) {
        // TODO: Implement GeoServer WFS layer generation
        // This would create a temporary layer in GeoServer with the search results
        // and return the WFS URL for the frontend to consume
        return "http://localhost:8080/geoserver/wfs?service=WFS&version=1.0.0&request=GetFeature&typeName=" + 
               module + "_" + tenantId + "&maxFeatures=1000&outputFormat=application/json";
    }

    private ModuleSpatialService getModuleService(String module) {
        return moduleSpatialServices.stream()
                .filter(service -> service.getModuleName().equals(module))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Module not supported: " + module + 
                        ". Supported modules: " + moduleSpatialServices.stream()
                                .map(ModuleSpatialService::getModuleName)
                                .collect(Collectors.toList())));
    }
}