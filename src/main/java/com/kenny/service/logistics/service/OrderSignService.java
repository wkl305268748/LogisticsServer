package com.kenny.service.logistics.service;

import com.kenny.service.logistics.json.response.OrderTakingResponse;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.mapper.*;
import com.kenny.service.logistics.model.OrderSign;
import com.kenny.service.logistics.model.OrderTaking;
import com.kenny.service.logistics.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by WKL on 2017-6-30.
 */
@Service
public class OrderSignService {
    @Autowired
    OrderSignMapper orderSignMapper;

    //创建
    public void createOrderSign(int order_taking_id,MultipartFile order_img,HttpServletRequest request) throws IOException {
        String name = FileUtil.saveImg(order_img);
        OrderSign orderSign = new OrderSign();
        orderSign.setFk_order_taking_id(order_taking_id);
        orderSign.setOrder_img(request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/web/img/"+name);
        orderSign.setTime(new Date());
        orderSignMapper.insert(orderSign);
    }

    public OrderSign getByTakingid(int id){
        return orderSignMapper.selectByOrderTakingId(id);
    }
}
