package org.upyog.employee.dasboard.web.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.egov.common.contract.request.RequestInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * Request model for role-based dashboard data retrieval
 * Accepts multiple roles and returns dashboard data for corresponding modules
 */

@Data
public class RoleBasedDashboardRequest {

    @Valid
    @NotNull
    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    @NotNull
    @JsonProperty("roles")
    private List<Role> roles;

    @NotNull
    @JsonProperty("tenantId")
    private String tenantId;
}
