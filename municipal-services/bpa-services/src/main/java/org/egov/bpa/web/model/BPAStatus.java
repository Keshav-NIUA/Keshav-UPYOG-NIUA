package org.egov.bpa.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BPAStatus {
    APPLICATION_CREATED("Application Created"),
    RTP_ASSIGNED("RTP Assigned"),
    RTP_ACCEPTED("RTP Accepted"),
    FORWARDED_TO_GMDA("Forwarded to GMDA");

    private final String status;

}
