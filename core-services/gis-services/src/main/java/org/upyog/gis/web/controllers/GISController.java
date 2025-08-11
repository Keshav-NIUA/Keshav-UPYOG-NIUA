package org.upyog.gis.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.upyog.gis.service.GISSpatialService;
import org.upyog.gis.web.contracts.GISSearchRequest;
import org.upyog.gis.web.contracts.GISSearchResponse;
import org.egov.common.contract.response.ResponseInfo;

import javax.validation.Valid;

/**
 * Controller for GIS spatial data operations
 * Provides endpoints for searching spatial data across different modules
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class GISController {

    @Autowired
    private GISSpatialService gisSpatialService;

    /**
     * Dynamic GIS search endpoint for any module
     * 
     * <p>URL pattern: /gis/v1/_search</p>
     * 
     * <p>Response formats supported:</p>
     * <ul>
     *   <li>"geojson": Returns GeoJSON FeatureCollection</li>
     *   <li>"wms": Returns WMS layer URL for GeoServer</li>
     *   <li>"wfs": Returns WFS layer URL for GeoServer</li>
     * </ul>
     * 
     * @param request GIS search request with module, tenant, and search criteria
     * @return ResponseEntity with GIS search response
     */
    @PostMapping("/_search")
    public ResponseEntity<GISSearchResponse> searchSpatialData(
            @Valid @RequestBody GISSearchRequest request) {

        String module = request.getModule();
        log.info("GIS search request received. Module: {}, Tenant: {}, RequestId: {}", 
                module, request.getTenantId(), request.getRequestInfo().getMsgId());

        try {
            GISSearchResponse response = gisSpatialService.searchSpatialData(request);
            response.setResponseInfo(new ResponseInfo());

            log.info("GIS search completed successfully. Module: {}, Results: {}, RequestId: {}", 
                    module, response.getTotalCount(), request.getRequestInfo().getMsgId());
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            log.error("GIS search validation error. Module: {}, RequestId: {}, Error: {}", 
                    module, request.getRequestInfo().getMsgId(), e.getMessage());
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            log.error("GIS search unexpected error. Module: {}, RequestId: {}, Error: {}", 
                    module, request.getRequestInfo().getMsgId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
