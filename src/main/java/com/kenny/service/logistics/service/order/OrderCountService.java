package com.kenny.service.logistics.service.order;

import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.mapper.order.OrderCustomerMapper;
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
    OrderCustomerMapper orderCustomerMapper;
    @Autowired
    UserManagerService userManagerService;

    public int getPlaceCount(){
        return orderCustomerMapper.countByStatus("ORDER_PLACE");
    }
    public int getTakingCount(){
        return orderCustomerMapper.countByStatus("ORDER_TAKING");
    }
    public int getSignCount(){
        return orderCustomerMapper.countByStatus("ORDER_SIGN");
    }
    public int getRefuseCount(){
        return orderCustomerMapper.countByStatus("ORDER_REFUSE");
    }
    public int getAllCount(){
        return orderCustomerMapper.count();
    }
    public Map<String,Object> getOrderCustomerMap(Integer offset,Integer pageSize,String type){
        List<UserSet> userSets = userManagerService.selectPageByTypeEx(offset,pageSize,type).getItem();
        List<String> userCompany = new ArrayList<>();
        List<Integer> refuseOrder = new ArrayList<>();
        List<Integer> placeOrder = new ArrayList<>();
        List<Integer> takingOrder = new ArrayList<>();
        List<Integer> signOrder = new ArrayList<>();
        for(UserSet userSet : userSets){
            userCompany.add(userSet.getUserInfo().getCompany());
            refuseOrder.add(orderCustomerMapper.countByUserAndStatus(userSet.getUser().getId(),"ORDER_REFUSE"));
            placeOrder.add(orderCustomerMapper.countByUserAndStatus(userSet.getUser().getId(),"ORDER_PLACE"));
            takingOrder.add(orderCustomerMapper.countByUserAndStatus(userSet.getUser().getId(),"ORDER_TAKING"));
            signOrder.add(orderCustomerMapper.countByUserAndStatus(userSet.getUser().getId(),"ORDER_SIGN"));
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
            timeCount.add(orderCustomerMapper.countByDay(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("timeList",timeList);
        map.put("timeCount",timeCount);
        return map;
    }

    //-----------------客户统计

    public int getCustomerPlaceCount(int user_id){
        return orderCustomerMapper.countByUserAndStatus(user_id,"ORDER_PLACE");
    }
    public int getCustomerTakingCount(int user_id){
        return orderCustomerMapper.countByUserAndStatus(user_id,"ORDER_TAKING");
    }
    public int getCustomerSignCount(int user_id){
        return orderCustomerMapper.countByUserAndStatus(user_id,"ORDER_SIGN");
    }
    public int getCustomerAllCount(int user_id){
        return orderCustomerMapper.countByUser(user_id);
    }
    public int getCustomerRefuseCount(int user_id){
        return orderCustomerMapper.countByUserAndStatus(user_id,"ORDER_REFUSE");
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
            timeCount.add(orderCustomerMapper.countByDayAndUser(cal.getTime(),user_id));
            cal.add(Calendar.DATE, 1);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("timeList",timeList);
        map.put("timeCount",timeCount);
        return map;
    }
}
