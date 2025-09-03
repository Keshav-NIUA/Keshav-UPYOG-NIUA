-- ========================================================
-- SELECT QUERIES FOR BPA TABLES
-- ========================================================

--- ========================================================
 -- SELECT QUERIES FOR BPA TABLES
 -- ========================================================
 -- Building Plans
 SELECT * FROM ug_bpa_buildingplans;
 SELECT * FROM ug_bpa_buildingplans_audit;

 -- Documents
 SELECT * FROM ug_bpa_documents;

 -- RTP Details
 SELECT * FROM ug_bpa_rtp_detail;
 SELECT * FROM ug_bpa_rtp_detail_audit;

 -- Area Mapping
 SELECT * FROM ug_bpa_area_mapping_detail;


 -- Flyway Migration History (system table)
 SELECT * FROM public.public;

 ---workflow tables ----


 select * from eg_wf_processinstance_v2;

 select * from eg_wf_businessservice_v2;

 select * from eg_wf_state_v2;

 select * from eg_wf_action_v2;

 select * from public.eg_wf_businessservice_v2;


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
-- First drop dependent tables
DROP TABLE IF EXISTS ug_bpa_area_mapping_detail CASCADE;
DROP TABLE IF EXISTS ug_bpa_rtp_detail_audit CASCADE;
DROP TABLE IF EXISTS ug_bpa_rtp_detail CASCADE;
DROP TABLE IF EXISTS ug_bpa_documents CASCADE;
DROP TABLE IF EXISTS ug_bpa_buildingplans_audit CASCADE;
DROP TABLE IF EXISTS ug_bpa_buildingplans CASCADE;

-- Drop ENUM types last (only if no longer needed anywhere)
--DROP TYPE IF EXISTS planning_permit_authority_enum CASCADE;
--DROP TYPE IF EXISTS building_permit_authority_enum CASCADE;

-- ====================================
-- Truncate Flyway history (reset migration tracking)
-- ====================================
TRUNCATE TABLE public.public RESTART IDENTITY CASCADE;
