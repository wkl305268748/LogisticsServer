package com.kenny.service.logistics.service;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.mapper.fleet.CarMapper;
import com.kenny.service.logistics.mapper.ProfitMapper;
import com.kenny.service.logistics.model.fleet.Car;
import com.kenny.service.logistics.model.Profit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by WKL on 2017-6-30.
 */
@Service
public class ProfitService {
    @Autowired
    ProfitMapper profitMapper;

    //获取所有的
    public PageResponse<Profit> getProfitAll(int pageSize, int offset){
        List<Profit> drivers = profitMapper.selectPage(offset,pageSize);
        int count = profitMapper.count();
        PageResponse<Profit> response = new PageResponse();
        response.setItem(drivers);
        response.setTotal(count);
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public Profit getProfit(int id){
        return profitMapper.selectById(id);
    }

    public void addProfit(String order_number,int order_taking_id,int pay,int recive){
        Profit profit = new Profit();
        profit.setOrder_number(order_number);
        profit.setFk_order_taking_id(order_taking_id);
        profit.setPay(pay);
        profit.setRecive(recive);
        profit.setIs_pay(false);
        profit.setIs_recive(false);
        profit.setProfit(recive-pay);
        profitMapper.insert(profit);
    }

    public void editProfitRecive(int id){
        Profit profit = profitMapper.selectById(id);
        profit.setIs_recive(true);
        profitMapper.update(profit);
    }
    public void editProfitPay(int id){
        Profit profit = profitMapper.selectById(id);
        profit.setIs_pay(true);
        profitMapper.update(profit);
    }

    public void editProfit(int id,boolean is_recive,boolean is_pay){
        Profit profit = profitMapper.selectById(id);
        profit.setIs_recive(is_recive);
        profit.setIs_pay(is_pay);
        profitMapper.update(profit);
    }

}
