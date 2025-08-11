package org.upyog.gis.util;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.response.ResponseInfo;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Factory for creating ResponseInfo objects
 * Handles conversion from RequestInfo to ResponseInfo with proper timestamps
 */
@Component
public class ResponseInfoFactory {

    /**
     * Creates a ResponseInfo object from RequestInfo
     * 
     * @param requestInfo The original request info
     * @param success Whether the operation was successful
     * @return ResponseInfo with current timestamp and status
     */
    public ResponseInfo createResponseInfoFromRequestInfo(RequestInfo requestInfo, boolean success) {
        String apiId = "";
        String ver = "";
        Long ts = Instant.now().toEpochMilli();
        String resMsgId = "";
        String msgId = "";
        
        if (requestInfo != null) {
            apiId = requestInfo.getApiId() != null ? requestInfo.getApiId() : "";
            ver = requestInfo.getVer() != null ? requestInfo.getVer() : "";
            ts = requestInfo.getTs() != null ? requestInfo.getTs() : Instant.now().toEpochMilli();
            resMsgId = requestInfo.getMsgId() != null ? requestInfo.getMsgId() : "";
            msgId = requestInfo.getMsgId() != null ? requestInfo.getMsgId() : "";
        }
        
        String responseStatus = success ? "successful" : "failed";

        return ResponseInfo.builder()
                .apiId(apiId)
                .ver(ver)
                .ts(ts)
                .resMsgId(resMsgId)
                .msgId(msgId)
                .status(responseStatus)
                .build();
    }

    /**
     * Creates a ResponseInfo object from RequestInfo with custom message
     * 
     * @param requestInfo The original request info
     * @param success Whether the operation was successful
     * @param customMessage Custom response message
     * @return ResponseInfo with custom message
     */
    public ResponseInfo createResponseInfoFromRequestInfo(RequestInfo requestInfo, boolean success, String customMessage) {
        ResponseInfo responseInfo = createResponseInfoFromRequestInfo(requestInfo, success);
        responseInfo.setResMsgId(customMessage);
        return responseInfo;
    }
} 