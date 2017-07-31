package com.kenny.service.logistics.exception;


/**
 * Created by Administrator on 2016/10/21.
 */
public class UserErrorCode extends ErrorCode {

    public static final UserErrorCode TOKEN_OVERTIME = new UserErrorCode(101,"TOKEN失效，请重新登陆");
    public static final UserErrorCode TOKEN_ERROR = new UserErrorCode(102,"TOKEN错误，请重新登陆");

    public static final UserErrorCode USER_PASS_ERROR  = new UserErrorCode(201,"用户名或密码错误");
    public static final UserErrorCode PASS_ERROR  = new UserErrorCode(202,"密码错误");
    public static final UserErrorCode USER_NO_EXISTS = new UserErrorCode(203,"用户不存在");
    public static final UserErrorCode USER_EXISTS = new UserErrorCode(204,"用户已存在");
    public static final UserErrorCode USER_BLOCKED = new UserErrorCode(205,"用户被封禁");
    public static final UserErrorCode USER_PASS_NULL = new UserErrorCode(206,"用户未设定密码");
    public static final UserErrorCode USER_PHONE_ERROR = new UserErrorCode(207, "必须为11位手机号");

    public static final UserErrorCode CODE_ERROR = new UserErrorCode(301,"验证码错误");
    public static final UserErrorCode CODE_FAST = new UserErrorCode(302,"验证码请求过快");
    public static final UserErrorCode CODE_OVERTIME = new UserErrorCode(303,"验证码超时");
    public static final UserErrorCode CODE_TYPE_ERROR = new UserErrorCode(304,"验证码类型错误");
    public static final UserErrorCode SMS_SERVER_ERROR = new UserErrorCode(305,"短信服务器错误");
    public static final UserErrorCode COOKIE_ERROR = new UserErrorCode(306,"cookie错误");

    public static final UserErrorCode USER_INOF_NO_EXISTS = new UserErrorCode(401,"用户信息不存在");

    public static final UserErrorCode MONEY_NO_FULL = new UserErrorCode(501,"用户余额不足");
    public UserErrorCode(int code, String msg) {
        super(code, msg);
    }
}
