package com.kenny.service.logistics.service.order;

import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.mapper.order.OrderCustomerMapper;
import com.kenny.service.logistics.mapper.order.OrderMapper;
import com.kenny.service.logistics.mapper.order.OrderStatusMapper;
import com.kenny.service.logistics.model.system.Defind;
import com.kenny.service.logistics.model.user.UserSet;
import com.kenny.service.logistics.service.user.UserManagerService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Kenny on 2017/8/7.
 */
@Service
public class OrderCountService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderStatusMapper orderStatusMapper;
    @Autowired
    UserManagerService userManagerService;

    public int getPlaceCount(){
        return orderMapper.countByStatus("ORDER_PLACE");
    }
    public int getTakingCount(){
        return orderMapper.countByStatus("ORDER_TAKING");
    }
    public int getSignCount(){
        return orderMapper.countByStatus("ORDER_SIGN");
    }
    public int getRefuseCount(){
        return orderMapper.countByStatus("ORDER_REFUSE");
    }
    public int getAllCount(){
        return orderMapper.count();
    }

    public Map<String,Object> getOrderCompanyMap(Integer offset,Integer pageSize){
        String type = "company";
        List<UserSet> userSets = userManagerService.selectPageByTypeEx(offset,pageSize,type).getItem();
        List<String> userCompany = new ArrayList<>();
        List<Integer> refuseOrder = new ArrayList<>();
        List<Integer> placeOrder = new ArrayList<>();
        List<Integer> takingOrder = new ArrayList<>();
        List<Integer> signOrder = new ArrayList<>();
        for(UserSet userSet : userSets){
            userCompany.add(userSet.getUserInfo().getCompany());
            refuseOrder.add(orderMapper.countByCompanyAndStatus(userSet.getUser().getId(),Defind.ORDER_REFUSE));
            placeOrder.add(orderMapper.countByCompanyAndStatus(userSet.getUser().getId(),Defind.ORDER_PLACE));
            takingOrder.add(orderMapper.countByCompanyAndStatus(userSet.getUser().getId(),Defind.ORDER_TAKING));
            signOrder.add(orderMapper.countByCompanyAndStatus(userSet.getUser().getId(),Defind.ORDER_SIGN));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userCompany",userCompany);
        map.put("placeOrder",placeOrder);
        map.put("refuseOrder",refuseOrder);
        map.put("takingOrder",takingOrder);
        map.put("signOrder",signOrder);
        return map;
    }

    public Map getOrderCrateDay(Date begin,Date end) {
        List<String> timeList = new ArrayList<>();
        List<Integer> timeCount = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(begin);
        int day1 = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(end);
        int day2 = cal.get(Calendar.DAY_OF_YEAR);
        int dayCount = day2 - day1;
        if(dayCount < 0)
            return null;

        cal.setTime(begin);
        for (int i=0; i<=dayCount; i++){
            timeList.add(sdf.format(cal.getTime()));
            timeCount.add(orderMapper.countByDay(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("timeList",timeList);
        map.put("timeCount",timeCount);
        return map;
    }

    //-----------------客户统计

    public int getCustomerPlaceCount(int user_id){
        return orderMapper.countByCustomerAndStatus(user_id,"ORDER_PLACE");
    }
    public int getCustomerTakingCount(int user_id){
        return orderMapper.countByCustomerAndStatus(user_id,"ORDER_TAKING");
    }
    public int getCustomerSignCount(int user_id){
        return orderMapper.countByCustomerAndStatus(user_id,"ORDER_SIGN");
    }
    public int getCustomerAllCount(int user_id){
        return orderMapper.countByCustomer(user_id);
    }
    public int getCustomerRefuseCount(int user_id){
        return orderMapper.countByCustomerAndStatus(user_id,"ORDER_REFUSE");
    }

    public Map getOrderCrateDayByUserId(int user_id,Date begin,Date end) {
        List<String> timeList = new ArrayList<>();
        List<Integer> timeCount = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(begin);
        int day1 = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(end);
        int day2 = cal.get(Calendar.DAY_OF_YEAR);
        int dayCount = day2 - day1;
        if(dayCount < 0)
            return null;

        cal.setTime(begin);
        for (int i=0; i<=dayCount; i++){
            timeList.add(sdf.format(cal.getTime()));
            timeCount.add(orderMapper.countByDayAndCustomer(cal.getTime(),user_id));
            cal.add(Calendar.DATE, 1);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("timeList",timeList);
        map.put("timeCount",timeCount);
        return map;
    }

    //------------物流公司统计
    public int getCompanyPlaceCount(int user_id){
        return orderMapper.countByCompanyAndStatus(user_id,"ORDER_PLACE");
    }
    public int getCompanyTakingCount(int user_id){
        return orderMapper.countByCompanyAndStatus(user_id,"ORDER_TAKING");
    }
    public int getCompanySignCount(int user_id){
        return orderMapper.countByCompanyAndStatus(user_id,"ORDER_SIGN");
    }
    public int getCompanyAllCount(int user_id){
        return orderMapper.countByCompany(user_id);
    }
    public int getCompanyRefuseCount(int user_id){
        return orderMapper.countByCompanyAndStatus(user_id,"ORDER_REFUSE");
    }

    public Map getOrderTakingDayByUserId(int user_id,Date begin,Date end) {
        List<String> timeList = new ArrayList<>();
        List<Integer> placeCount = new ArrayList<>();
        List<Integer> takingCount = new ArrayList<>();
        List<Integer> signCount = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(begin);
        int day1 = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(end);
        int day2 = cal.get(Calendar.DAY_OF_YEAR);
        int dayCount = day2 - day1;
        if(dayCount < 0)
            return null;

        cal.setTime(begin);
        for (int i=0; i<=dayCount; i++){
            timeList.add(sdf.format(cal.getTime()));
            placeCount.add(orderMapper.countByDayAndWantCompany(cal.getTime(),user_id));
            takingCount.add(orderStatusMapper.countByDayAndUserAndStatus(cal.getTime(),user_id, Defind.ORDER_TAKING));
            signCount.add(orderStatusMapper.countByDayAndUserAndStatus(cal.getTime(),user_id, Defind.ORDER_SIGN));
            cal.add(Calendar.DATE, 1);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("timeList",timeList);
        map.put("placeCount",placeCount);
        map.put("takingCount",takingCount);
        map.put("signCount",signCount);
        return map;
    }
}
