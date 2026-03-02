package org.upyog.employee.dasboard.web.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Model representing a user role
 * Used in role-based dashboard requests
 */

@Data
public class Role {
    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("tenantId")
    private String tenantId;
}
