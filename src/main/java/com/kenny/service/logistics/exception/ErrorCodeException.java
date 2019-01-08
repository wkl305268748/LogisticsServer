package com.kenny.service.logistics.exception;

/**
 * 用户自定义异常
 * Created by AutoCode on 2017/7/8
 */
public class ErrorCodeException extends RuntimeException{

    ErrorCode errorCode;
    public static final ErrorCode PARAM_ERROR = new ErrorCode(101, "参数错误");
    public static final ErrorCode DATA_NO_ERROR = new ErrorCode(102, "数据不存在");

    public ErrorCodeException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
