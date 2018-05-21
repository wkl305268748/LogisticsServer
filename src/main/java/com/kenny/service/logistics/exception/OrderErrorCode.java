package com.kenny.service.logistics.exception;

/**
 * Created by Administrator on 2016/10/21.
 */
public class OrderErrorCode extends ErrorCode{

    public static final OrderErrorCode ORDER_NOT_DELETE = new OrderErrorCode(100, "订单无法删除");
    public static final OrderErrorCode ORDER_TARKING_STATUS = new OrderErrorCode(101, "订单已接单");

    public OrderErrorCode(int code, String msg) {
        super(code, msg);
    }
}
