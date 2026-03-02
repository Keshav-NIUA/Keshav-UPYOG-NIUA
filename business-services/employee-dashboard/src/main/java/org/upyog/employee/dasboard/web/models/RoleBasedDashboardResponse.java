package org.upyog.employee.dasboard.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

/**
 * Response model for role-based dashboard data
 * Returns a map with module names as keys and dashboard details as values
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleBasedDashboardResponse {

    @JsonProperty("responseInfo")
    private ResponseInfo responseInfo;

    @JsonProperty("dashboardData")
    private Map<String, EmployeeDashboardDetails> dashboardData;
}
