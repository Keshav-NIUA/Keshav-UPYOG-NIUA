package org.upyog.employee.dasboard.web.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.egov.common.contract.request.RequestInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Simplified request model for role-based dashboard
 * Extracts roles and tenantId from RequestInfo.userInfo
 */
@Data
public class RoleBasedDashboardRequest {

    @Valid
    @NotNull
    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;
}
