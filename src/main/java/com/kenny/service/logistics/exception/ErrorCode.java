package com.kenny.service.logistics.exception;

/**
 * Created by Administrator on 2016/10/21.
 */
public class ErrorCode{

    public static final ErrorCode SUCCESS = new ErrorCode(0, "成功", true);
    public static final ErrorCode PARAM_ERROR = new ErrorCode(1, "参数错误");
    public static final ErrorCode DATA_NULL = new ErrorCode(2, "数据不存在");
    public static final ErrorCode DB_ERROR = new ErrorCode(3, "服务器数据库操作失败");
    public static final ErrorCode FILE_ERROR = new ErrorCode(4, "文件存写失败");

    private boolean success;
    private int code;
    private String msg;

    public ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = false;
    }

    public ErrorCode(int code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }
}
