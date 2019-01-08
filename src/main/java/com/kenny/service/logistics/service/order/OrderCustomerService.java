package com.kenny.service.logistics.service.order;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kenny.service.logistics.mapper.order.OrderGoodsMapper;
import com.kenny.service.logistics.model.po.order.OrderGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.po.order.OrderCustomer;
import com.kenny.service.logistics.mapper.order.OrderCustomerMapper;

@Service
public class OrderCustomerService {
    @Autowired
    private OrderCustomerMapper orderCustomerMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    //创建订单
    public OrderCustomer insert(Integer order_id,
                                String order_number,
                                String send_name,
                                String send_phone,
                                String send_addr,
                                String send_addr_info,
                                String recive_name,
                                String recive_phone,
                                String recive_addr,
                                String recive_addr_info,
                                String dispatching_type,
                                Date send_time,
                                Date recive_time,
                                String goods) {
        OrderCustomer orderCustomer = new OrderCustomer();
        //单号
        orderCustomer.setFk_order_id(order_id);
        orderCustomer.setOrder_number(order_number);
        orderCustomer.setSend_name(send_name);
        orderCustomer.setSend_addr(send_addr);
        orderCustomer.setSend_addr_info(send_addr_info);
        orderCustomer.setSend_phone(send_phone);
        orderCustomer.setSend_time(send_time);
        orderCustomer.setRecive_name(recive_name);
        orderCustomer.setRecive_phone(recive_phone);
        orderCustomer.setRecive_addr(recive_addr);
        orderCustomer.setRecive_addr_info(recive_addr_info);
        orderCustomer.setRecive_time(recive_time);
        orderCustomer.setDispatching_type(dispatching_type);
        orderCustomer.setTime(new Date());
        orderCustomerMapper.insert(orderCustomer);

        try {
            if (goods != null) {
                goods = "[" + goods + "]";
                Gson gson = new Gson();
                List<OrderGoods> goodsList = gson.fromJson(goods, new TypeToken<List<OrderGoods>>() {
                }.getType());
                //添加货物
                for (OrderGoods good : goodsList) {
                    good.setFk_order_id(orderCustomer.getFk_order_id());
                    orderGoodsMapper.insert(good);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(goods);
        }
        return orderCustomer;
    }

    //修改订单
    public OrderCustomer update(Integer id,
                                String send_name,
                                String send_phone,
                                String send_addr,
                                String send_addr_info,
                                String recive_name,
                                String recive_phone,
                                String recive_addr,
                                String recive_addr_info,
                                String dispatching_type,
                                Date send_time,
                                Date recive_time,
                                String goods) throws ErrorCodeException {
        OrderCustomer orderCustomer = orderCustomerMapper.selectByPrimaryKey(id);
        if (orderCustomer == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        orderCustomer.setSend_name(send_name);
        orderCustomer.setSend_phone(send_phone);
        orderCustomer.setSend_addr(send_addr);
        orderCustomer.setSend_addr_info(send_addr_info);
        orderCustomer.setRecive_name(recive_name);
        orderCustomer.setRecive_phone(recive_phone);
        orderCustomer.setRecive_addr(recive_addr);
        orderCustomer.setRecive_addr_info(recive_addr_info);
        orderCustomer.setSend_time(send_time);
        orderCustomer.setRecive_time(recive_time);
        orderCustomer.setDispatching_type(dispatching_type);
        orderCustomerMapper.update(orderCustomer);

        //货物修改
        //删除所有这个订单的货物
        orderGoodsMapper.deleteByOrderId(id);
        if (goods != null) {
            Gson gson = new Gson();
            List<OrderGoods> goodsList = gson.fromJson(goods, new TypeToken<List<OrderGoods>>() {
            }.getType());
            for (OrderGoods good : goodsList) {
                good.setFk_order_id(orderCustomer.getFk_order_id());
                orderGoodsMapper.insert(good);
            }
        }
        return orderCustomer;
    }


    public OrderCustomer selectByPrimaryKey(Integer id) throws ErrorCodeException {
        OrderCustomer orderCustomer = orderCustomerMapper.selectByPrimaryKey(id);
        if (orderCustomer == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return orderCustomer;
    }

    public PageResponse<OrderCustomer> selectPage(Integer offset, Integer pageSize) {
        PageResponse<OrderCustomer> response = new PageResponse();
        response.setItem(orderCustomerMapper.selectPage(offset, pageSize));
        response.setTotal(orderCustomerMapper.count());
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public int deleteByPrimaryKey(Integer id){
        return orderCustomerMapper.deleteByPrimaryKey(id);
    }
}
