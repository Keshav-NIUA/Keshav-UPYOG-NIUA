# GIS Service

A module-independent GIS service that provides spatial data search capabilities for various eGov modules. Currently supports Property service with plans to extend to other modules.

## Features

- **Module-Independent API**: Single endpoint for all modules
- **Multiple Response Formats**: GeoJSON, WMS, WFS
- **GeoServer Integration**: Ready for WMS/WFS layer generation
- **Property Service Integration**: Fetches approved properties with geometry data

## API Endpoints

### Search Spatial Data
**POST** `/gis-service/gis/v1/{module}/_search`

Search spatial data for any supported module.

#### Request Body
```json
{
  "RequestInfo": {
    "apiId": "gis-search",
    "ver": "1.0",
    "ts": 1691234567890,
    "action": "search"
  },
  "tenantId": "pb.amritsar",
  "module": "property",
  "searchCriteria": {
    "propertyType": "RESIDENTIAL",
    "locality": "Sector-1"
  },
  "geometryRequired": true,
  "responseFormat": "geojson"
}
```

#### Response Formats
- `"geojson"`: Returns GeoJSON FeatureCollection
- `"wms"`: Returns WMS layer URL for GeoServer
- `"wfs"`: Returns WFS layer URL for GeoServer

#### Response Example (GeoJSON)
```json
{
  "ResponseInfo": {
    "apiId": "gis-search",
    "ver": "1.0",
    "ts": 1691234567890,
    "status": "successful"
  },
  "module": "property",
  "tenantId": "pb.amritsar",
  "totalCount": 5,
  "geoJsonData": {
    "type": "FeatureCollection",
    "features": [
      {
        "type": "Feature",
        "id": "property-123",
        "geometry": {
          "type": "Point",
          "coordinates": [75.8577, 30.9010]
        },
        "properties": {
          "propertyId": "property-123",
          "propertyType": "RESIDENTIAL",
          "locality": "Sector-1"
        }
      }
    ]
  }
}
```

## Development

### Prerequisites
- Java 8
- Spring Boot 2.2.6
- Property Service running (for property module integration)

### Running the Service
```bash
mvn spring-boot:run
```
 