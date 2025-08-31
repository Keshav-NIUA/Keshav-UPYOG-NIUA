-- ========================================================
-- Building Plan Table
-- ========================================================
CREATE TABLE IF NOT EXISTS eg_bpa_buildingplans (
    /** Unique Identifier(UUID) of the BPA application for internal reference. */
    id VARCHAR(64) PRIMARY KEY,

    /** Formatted unique identifier of the building permit application. */
    application_no VARCHAR(64),

    /** Unique ULB identifier. */
    tenant_id VARCHAR(256) NOT NULL,

    /** Unique identifier of the scrutinized EDCR number. */
    edcr_number VARCHAR(64),

    /** Status of the application. */
    status VARCHAR(64),

    /** Application submission date. */
    application_date BIGINT,

    /** Approval number based on workflow status. */
    approval_no VARCHAR(64),

    /** Approval date based on workflow status. */
    approval_date BIGINT,

    /** Business service associated with the application. */
    business_service VARCHAR(64),

    /** Initiator user UUID. */
    account_id VARCHAR(64),

    /** Type of application. */
    application_type VARCHAR(64),

    /** Risk type derived from MDMS configuration. */
    risk_type VARCHAR(64),

    /** Unique Identifier(UUID) of the land for internal reference. */
    land_id VARCHAR(64),

    /** Audit Fields */
    created_by VARCHAR(64),
    last_modified_by VARCHAR(64),
    created_time BIGINT,
    last_modified_time BIGINT,

    /** Additional details JSON for extensibility */
    additional_details JSONB
);

-- ========================================================
-- Building Plan Audit Table
-- ========================================================
CREATE TABLE IF NOT EXISTS eg_bpa_buildingplans_audit (
    /** Unique Identifier(UUID) of the BPA application for internal reference. */
    id VARCHAR(64) NOT NULL,

    /** Formatted unique identifier of the building permit application. */
    application_no VARCHAR(64),

    /** Unique ULB identifier. */
    tenant_id VARCHAR(256) NOT NULL,

    /** Unique identifier of the scrutinized EDCR number. */
    edcr_number VARCHAR(64),

    /** Status of the application. */
    status VARCHAR(64),

    /** Application submission date. */
    application_date BIGINT,

    /** Approval number based on workflow status. */
    approval_no VARCHAR(64),

    /** Approval date based on workflow status. */
    approval_date BIGINT,

    /** Business service associated with the application. */
    business_service VARCHAR(64),

    /** Initiator user UUID. */
    account_id VARCHAR(64),

    /** Type of application. */
    application_type VARCHAR(64),

    /** Risk type derived from MDMS configuration. */
    risk_type VARCHAR(64),

    /** Unique Identifier(UUID) of the land for internal reference. */
    land_id VARCHAR(64),

    /** Audit Fields */
    created_by VARCHAR(64),
    last_modified_by VARCHAR(64),
    created_time BIGINT,
    last_modified_time BIGINT,

    /** Additional details JSON for extensibility */
    additional_details JSONB
);

-- ========================================================
-- BPA Document Table
-- ========================================================
CREATE TABLE IF NOT EXISTS eg_bpa_documents (
    /** Unique Identifier(UUID) for the document. */
    id VARCHAR(64) PRIMARY KEY,

    /** Type of the document (ownership proof, NOC, etc.). */
    document_type VARCHAR(64),

    /** Filestore reference ID. */
    filestore_id VARCHAR(64),

    /** Unique document identifier. */
    document_uid VARCHAR(64),

    /** Foreign key reference to the building plan. */
    buildingplan_id VARCHAR(64),

    /** Audit Fields */
    created_by VARCHAR(64),
    last_modified_by VARCHAR(64),
    created_time BIGINT,
    last_modified_time BIGINT,

    /** Additional details JSON for extensibility */
    additional_details JSONB,

    CONSTRAINT fk_eg_bpa_documents_buildingplans FOREIGN KEY (buildingplan_id)
        REFERENCES eg_bpa_buildingplans (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- ========================================================
-- BPA RTP details
-- ========================================================

create table ug_bpa_rtp_details (
    /** Unique Identifier(UUID) for the RTP details. */
    id VARCHAR(64) PRIMARY KEY,

    /** Foreign key reference to the building plan. */
    buildingplan_id VARCHAR(64),

    /** RTP category */
    rtp_category VARCHAR(100),

    /** RTP id */
    rtp_id varchar(64),

    /** RTP expiry date */
    rtp_name varchar(200),

    assignment_status VARCHAR(64),

    assignment_date BIGINT,
    changed_date BIGINT,

    remarks VARCHAR(1000),

    /** Audit Fields */
    created_by VARCHAR(64),
    last_modified_by VARCHAR(64),
    created_time BIGINT, --assignment_date
    last_modified_time BIGINT,

    /** Additional details JSON for extensibility */
    additional_details JSONB,

    CONSTRAINT fk_ug_bpa_rtp_details_buildingplans FOREIGN KEY (buildingplan_id)
        REFERENCES eg_bpa_buildingplans (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
