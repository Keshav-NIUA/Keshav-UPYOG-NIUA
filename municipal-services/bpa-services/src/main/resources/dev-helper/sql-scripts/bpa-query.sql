-- ========================================================
-- SELECT QUERIES FOR BPA TABLES
-- ========================================================

-- Main Building Plan table
SELECT * FROM eg_bpa_buildingplans;

-- Building Plan Audit table
SELECT * FROM eg_bpa_buildingplans_audit;

-- BPA Documents table
SELECT * FROM eg_bpa_documents;

-- BPA RTP details table
SELECT * FROM ug_bpa_rtp_details;

-- Flyway Migration History (system table)
SELECT * FROM public.public;

---workflow tables ----


select * from eg_wf_processinstance_v2;

select * from eg_wf_businessservice_v2;

select * from eg_wf_state_v2;

select * from eg_wf_action_v2;


--Drop tables -----


SELECT version(); -- show postgres version


-- ========================================================
-- CLEANUP SCRIPT: Drop BPA-related tables + Flyway history
-- ========================================================

-- ====================================
-- First drop child tables with FKs
-- ====================================
DROP TABLE IF EXISTS eg_bpa_documents CASCADE;
DROP TABLE IF EXISTS ug_bpa_rtp_details CASCADE;

-- ====================================
-- Then drop audit tables
-- ====================================
DROP TABLE IF EXISTS eg_bpa_buildingplans_audit;

-- ====================================
-- Finally drop the main building plan table
-- ====================================
DROP TABLE IF EXISTS eg_bpa_buildingplans CASCADE;

-- ====================================
-- Truncate Flyway history (reset migration tracking)
-- ====================================
TRUNCATE TABLE public.public RESTART IDENTITY CASCADE;
