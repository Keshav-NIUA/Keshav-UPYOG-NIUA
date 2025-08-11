package org.upyog.gis.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDetails {

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("createdTime")
    private Long createdTime;

    @JsonProperty("lastModifiedBy")
    private String lastModifiedBy;

    @JsonProperty("lastModifiedTime")
    private Long lastModifiedTime;
} 