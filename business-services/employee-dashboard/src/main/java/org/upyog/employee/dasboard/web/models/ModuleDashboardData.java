package org.upyog.employee.dasboard.web.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ModuleDashboardData {

    @JsonProperty("moduleName")
    private String moduleName;

    @JsonProperty("applicationReceived")
    private Integer applicationReceived;

    @JsonProperty("applicationPending")
    private Integer applicationPending;

    @JsonProperty("applicationApproved")
    private Integer applicationApproved;

    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;
}
