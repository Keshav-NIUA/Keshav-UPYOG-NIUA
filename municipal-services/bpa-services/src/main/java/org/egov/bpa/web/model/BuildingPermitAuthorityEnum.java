package org.egov.bpa.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BuildingPermitAuthorityEnum {
    MB("MUNICIPAL_BOARD"),
    GP("GAON_PANCHAYAT"),
    GMC("GUWAHATI_MUNICIPAL_CORPORATION"),
    NGMB("NORTH_GUWAHATI_MUNICIPAL_BOARD");

    private final String value;
}
