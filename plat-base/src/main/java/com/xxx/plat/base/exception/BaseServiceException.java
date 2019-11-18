package com.xxx.plat.base.exception;

public class BaseServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorCode;

    public BaseServiceException() {
        this.errorCode = "E00001";
    }

    public BaseServiceException(Throwable cause) {
        super(cause);
    }

    public BaseServiceException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BaseServiceException(String errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
    }

    public BaseServiceException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BaseServiceException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        String s = this.getClass().getName();
        String errorCodeMsg = this.errorCode == null ? "" : this.errorCode;
        String message = this.getLocalizedMessage();
        message = message == null ? errorCodeMsg : errorCodeMsg + message;
        return "".equals(message) ? s : s + ": " + message;
    }
}