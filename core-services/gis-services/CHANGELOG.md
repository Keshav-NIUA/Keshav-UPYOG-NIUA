# Changelog

All notable changes to the GIS Service will be documented in this file.

## [1.0.0] - 2025-08-05

### Added
- **Module-Independent GIS API**: Single endpoint for all modules
- **Multiple Response Formats**: Support for GeoJSON, WMS, WFS
- **Property Service Integration**: Fetches approved properties with geometry data

### Technical Details

#### API Changes
- **Single Endpoint**: `POST /gis-service/gis/v1/{module}/_search`
- **Response Formats**: 
  - `"geojson"`: Returns GeoJSON FeatureCollection
  - `"wms"`: Returns WMS layer URL (placeholder)
  - `"wfs"`: Returns WFS layer URL (placeholder)

#### Architecture
- **Transformation Logic**: Kept in GIS service for separation of concerns
- **GeoServer Integration**: Framework ready for future implementation
- **Module Independence**: Designed to support multiple modules

