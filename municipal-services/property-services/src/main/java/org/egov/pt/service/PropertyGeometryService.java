package org.egov.pt.service;

import lombok.extern.slf4j.Slf4j;
import org.egov.pt.config.PropertyConfiguration;
import org.egov.pt.models.Property;
import org.egov.pt.models.PropertyGeometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for handling property geometry data
 * Responsible for creating and managing geometry information for properties
 */
@Service
@Slf4j
public class PropertyGeometryService {

    @Autowired
    private PropertyConfiguration config;

    /**
     * Creates and adds geometry data to property if latitude and longitude are available
     * 
     * @param property The property object containing address with geo-location
     * @return true if geometry was added, false otherwise
     */
    public boolean addGeometryToProperty(Property property) {
        // Check if geometry feature is enabled
        if (!config.getIsGeometryEnabled()) {
            log.debug("Geometry feature is disabled, skipping geometry creation for property: {}", 
                    property.getPropertyId());
            return false;
        }

        // Check null values first to avoid null pointer exceptions
        if (property.getAddress() == null || property.getAddress().getGeoLocation() == null) {
            log.debug("No address or geo-location found for property: {}", property.getPropertyId());
            return false;
        }
        
        if (property.getAddress().getGeoLocation().getLatitude() == null ||
                property.getAddress().getGeoLocation().getLongitude() == null) {
            log.debug("No latitude/longitude found for property: {}", property.getPropertyId());
            return false;
        }

        try {
            Double longitude = property.getAddress().getGeoLocation().getLongitude();
            Double latitude = property.getAddress().getGeoLocation().getLatitude();

            String wktGeometry = String.format("POINT(%f %f)", latitude, longitude);
            
            PropertyGeometry propertyGeometry = PropertyGeometry.builder()
                    .id(UUID.randomUUID().toString())
                    .propertyId(property.getPropertyId())
                    .tenantId(property.getTenantId())
                    .geometry(wktGeometry)
                    .auditDetails(property.getAuditDetails())
                    .build();

            property.setGeometry(propertyGeometry);
            
            log.info("Geometry data added to property: {}, WKT: {}", 
                    property.getPropertyId(), wktGeometry);
            return true;

        } catch (Exception e) {
            log.error("Error creating geometry for property: {}, Error: {}", 
                    property.getPropertyId(), e.getMessage());
            return false;
        }
    }
}
