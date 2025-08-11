package org.upyog.gis.exception;

/**
 * Custom exception for GIS service operations
 */
public class GISException extends RuntimeException {

    private final String errorCode;

    public GISException(String message) {
        super(message);
        this.errorCode = "GIS_ERROR";
    }

    public GISException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "GIS_ERROR";
    }

    public GISException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public GISException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
