package com.kenny.service.logistics.exception;

/**
 * Created by Administrator on 2016/10/21.
 */
public class OrderErrorCode extends ErrorCode{

    public static final OrderErrorCode ORDER_NOT_DELETE = new OrderErrorCode(100, "订单无法删除");

    public OrderErrorCode(int code, String msg) {
        super(code, msg);
    }
}
