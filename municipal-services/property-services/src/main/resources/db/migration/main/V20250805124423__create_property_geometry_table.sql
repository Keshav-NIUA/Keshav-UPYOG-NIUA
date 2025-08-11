-- Enable PostGIS extension for geometry support
-- This must be executed by a superuser or database owner
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_extension WHERE extname = 'postgis') THEN
        RAISE EXCEPTION 'PostGIS extension is not available. Please install PostGIS extension before running this migration.';
    END IF;
END $$;

-- Add unique constraint to propertyId column in eg_pt_property table
-- This is required for the foreign key constraint in the geometry table
ALTER TABLE eg_pt_property ADD CONSTRAINT uk_eg_pt_property_property_id UNIQUE (propertyId);

-- Create property geometry table only if PostGIS extension is available
CREATE TABLE IF NOT EXISTS eg_pt_property_geometry (
    id VARCHAR(64) PRIMARY KEY,
    propertyId VARCHAR(64) NOT NULL,
    tenantId VARCHAR(256) NOT NULL,
    geometry GEOMETRY(POINT, 4326),
    createdBy VARCHAR(64) NOT NULL,
    createdTime BIGINT NOT NULL,
    lastModifiedBy VARCHAR(64),
    lastModifiedTime BIGINT,

    CONSTRAINT fk_property_geometry_property FOREIGN KEY (propertyId) REFERENCES eg_pt_property(propertyId)
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_property_geometry_property_id ON eg_pt_property_geometry(propertyId);
CREATE INDEX IF NOT EXISTS idx_property_geometry_tenant_id ON eg_pt_property_geometry(tenantId);
