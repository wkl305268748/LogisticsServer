
package com.kenny.service.logistics.exception;

/**
 * 错误状态码异常
 */
public class ErrorCodeException extends Exception{

    ErrorCode errorCode;

    public ErrorCodeException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
