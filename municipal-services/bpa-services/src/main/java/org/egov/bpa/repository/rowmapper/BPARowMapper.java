package org.egov.bpa.repository.rowmapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.egov.bpa.web.model.AuditDetails;
import org.egov.bpa.web.model.BPA;
import org.egov.bpa.web.model.Document;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Component
@Slf4j
public class BPARowMapper implements ResultSetExtractor<List<BPA>> {

	@Autowired
	private ObjectMapper mapper;

	/**
	 * extract the data from the resultset and prepare the BPA Object
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<BPA> extractData(ResultSet rs) throws SQLException, DataAccessException {

		Map<String, BPA> buildingMap = new LinkedHashMap<String, BPA>();

		while (rs.next()) {
			String id = rs.getString("bpa_id");
			String applicationNo = rs.getString("application_no");
			String approvalNo = rs.getString("approval_no");
			BPA currentbpa = buildingMap.get(id);
			String tenantId = rs.getString("bpa_tenant_id");
			if (currentbpa == null) {
				Long lastModifiedTime = rs.getLong("bpa_last_modified_time");
				if (rs.wasNull()) {
					lastModifiedTime = null;
				}

				Object additionalDetails = new Gson().fromJson(rs.getString("additional_details").equals("{}")
						|| rs.getString("additional_details").equals("null") ? null : rs.getString("additional_details"),
						Object.class);
				
				AuditDetails auditdetails = AuditDetails.builder().createdBy(rs.getString("bpa_created_by"))
						.createdTime(rs.getLong("bpa_created_time")).lastModifiedBy(rs.getString("bpa_last_modified_by"))
						.lastModifiedTime(lastModifiedTime).build();

				JsonObject jsonObject = new Gson().fromJson(
						rs.getString("additional_details").equals("{}") || rs.getString("additional_details").equals("null") ? "{}" : rs.getString("additional_details"),
					    JsonObject.class
					);

					String riskType = null;
					if (jsonObject.has("risk_type")) {
					    JsonElement riskTypeElement = jsonObject.get("risk_type");
					    if (riskTypeElement != null && !riskTypeElement.isJsonNull()) {
					        riskType = riskTypeElement.getAsString();
					    }
					}
				//String riskType = new Gson().fromJson(rs.getString("additionalDetails").equals("{}") || rs.getString("additionalDetails").equals("null") ? "{}" : rs.getString("additionalDetails"), JsonObject.class).get("riskType").getAsString();

				currentbpa = BPA.builder()
						.auditDetails(auditdetails)
						.applicationNo(applicationNo)
						.status(rs.getString("status"))
						.tenantId(tenantId)
						.approvalNo(approvalNo)
						.edcrNumber(rs.getString("edcr_number"))
						.approvalDate(rs.getLong("approval_date"))
						.accountId(rs.getString("account_id"))
						.landId(rs.getString("land_id"))
						.applicationDate(rs.getLong("application_date"))
						.id(id)
						.additionalDetails(additionalDetails)
						.businessService(rs.getString("business_service"))
						.riskType(riskType)
						.build();

				buildingMap.put(id, currentbpa);
			}
			addChildrenToProperty(rs, currentbpa);

		}

		return new ArrayList<>(buildingMap.values());

	}

	/**
	 * add child objects to the BPA fro the results set
	 * @param rs
	 * @param bpa
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private void addChildrenToProperty(ResultSet rs, BPA bpa) throws SQLException {

		String tenantId = bpa.getTenantId();
		AuditDetails auditdetails = AuditDetails.builder().createdBy(rs.getString("bpa_created_by"))
				.createdTime(rs.getLong("bpa_created_time")).lastModifiedBy(rs.getString("bpa_last_modified_by"))
				.lastModifiedTime(rs.getLong("bpa_last_modified_time")).build();

		if (bpa == null) {
			PGobject pgObj = (PGobject) rs.getObject("additional_detail");
			JsonNode additionalDetail = null;
			try {
				additionalDetail = mapper.readTree(pgObj.getValue());
			} catch (IOException e) {
				log.error("Failed to parse additionalDetails",e);
			}
			bpa.setAdditionalDetails(additionalDetail);
		}


		String documentId = rs.getString("bpa_doc_id");
		Object docDetails = null;
		if(rs.getString("doc_details") != null) {
			docDetails = new Gson().fromJson(rs.getString("doc_details").equals("{}")
					|| rs.getString("doc_details").equals("null") ? null : rs.getString("doc_details"),
					Object.class);
		}
		
		if (documentId != null) {
			Document document = Document.builder().documentType(rs.getString("bpa_doc_document_type"))
					.fileStoreId(rs.getString("bpa_doc_filestore"))
					.id(documentId)
					.additionalDetails(docDetails)
					.documentUid(rs.getString("document_uid")).build();
			bpa.addDocument(document);
		}
	}
}
