package com.kenny.service.logistics.service.order;

import com.kenny.service.logistics.mapper.order.OrderMapper;
import com.kenny.service.logistics.mapper.order.OrderStatusMapper;
import com.kenny.service.logistics.model.po.system.Defind;
import com.kenny.service.logistics.model.po.user.UserSet;
import com.kenny.service.logistics.service.user.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int getPlaceCount() {
        return orderMapper.countByStatus("ORDER_PLACE");
    }

    public int getTakingCount() {
        return orderMapper.countByStatus("ORDER_TAKING");
    }

    public int getSignCount() {
        return orderMapper.countByStatus("ORDER_SIGN");
    }

    public int getRefuseCount() {
        return orderMapper.countByStatus("ORDER_REFUSE");
    }

    public int getAllCount() {
        return orderMapper.count();
    }

    public float getAllOrderMoney() {
        float count = orderMapper.countAllMoney();
        count = (float) (Math.round(count * 100)) / 100;
        return count;
    }

    //获取公司订单统计
    public Map<String, Object> getOrderCompanyMap(Integer offset, Integer pageSize) {
        String type = "company";
        List<UserSet> userSets = userManagerService.selectPageByTypeEx(offset, pageSize, type).getItem();
        List<String> userCompany = new ArrayList<>();
        List<Integer> refuseOrder = new ArrayList<>();
        List<Integer> placeOrder = new ArrayList<>();
        List<Integer> takingOrder = new ArrayList<>();
        List<Integer> signOrder = new ArrayList<>();
        for (UserSet userSet : userSets) {
            userCompany.add(userSet.getUserInfo().getCompany());
            refuseOrder.add(orderMapper.countByCompanyAndStatus(userSet.getUser().getId(), Defind.ORDER_REFUSE));
            placeOrder.add(orderMapper.countByCompanyAndStatus(userSet.getUser().getId(), Defind.ORDER_PLACE));
            takingOrder.add(orderMapper.countByCompanyAndStatus(userSet.getUser().getId(), Defind.ORDER_TAKING));
            signOrder.add(orderMapper.countByCompanyAndStatus(userSet.getUser().getId(), Defind.ORDER_SIGN));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userCompany", userCompany);
        map.put("placeOrder", placeOrder);
        map.put("refuseOrder", refuseOrder);
        map.put("takingOrder", takingOrder);
        map.put("signOrder", signOrder);
        return map;
    }

    //获取Top10公司订单金额
    public Map<String, Object> getOrderCompanyMoneyMapTop10() {
        String type = "company";
        List<UserSet> userSets = userManagerService.selectPageByTypeEx(0, 1000, type).getItem();
        List<String> userCompany = new ArrayList<>();
        List<Float> orderMoney = new ArrayList<>();

        for (UserSet userSet : userSets) {
            userCompany.add(userSet.getUserInfo().getCompany());
            Float count = orderMapper.countMoneyByCompany(userSet.getUser().getId());
            if(count == null)
                count = 0f;
            count = (float) (Math.round(count * 100)) / 100;
            orderMoney.add(count);
        }

        //取前10
        List<String> userCompanyTop10 = new ArrayList<>();
        List<Float> orderMoneyTop10 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int index = 0;
            for (int j = 0; j < orderMoney.size(); j++) {
                if(orderMoney.get(j) >= orderMoney.get(index)){
                    index = j;
                }
            }
            userCompanyTop10.add(userCompany.get(index));
            orderMoneyTop10.add(orderMoney.get(index));

            userCompany.remove(index);
            orderMoney.remove(index);

            if(orderMoney.size() == 0)
                break;
        }


        Map<String, Object> map = new HashMap<>();
        map.put("userCompany", userCompanyTop10);
        map.put("orderMoney", orderMoneyTop10);
        return map;
    }


    public Map getOrderCrateDay(Date begin, Date end) {
        List<String> timeList = new ArrayList<>();
        List<Integer> timeCount = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(begin);
        int day1 = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(end);
        int day2 = cal.get(Calendar.DAY_OF_YEAR);
        int dayCount = day2 - day1;
        if (dayCount < 0)
            return null;

        cal.setTime(begin);
        for (int i = 0; i <= dayCount; i++) {
            timeList.add(sdf.format(cal.getTime()));
            timeCount.add(orderMapper.countByDay(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("timeList", timeList);
        map.put("timeCount", timeCount);
        return map;
    }

    //-----------------客户统计

    public int getCustomerPlaceCount(int user_id) {
        return orderMapper.countByCustomerAndStatus(user_id, "ORDER_PLACE");
    }

    public int getCustomerTakingCount(int user_id) {
        return orderMapper.countByCustomerAndStatus(user_id, "ORDER_TAKING");
    }

    public int getCustomerSignCount(int user_id) {
        return orderMapper.countByCustomerAndStatus(user_id, "ORDER_SIGN");
    }

    public int getCustomerAllCount(int user_id) {
        return orderMapper.countByCustomer(user_id);
    }

    public int getCustomerRefuseCount(int user_id) {
        return orderMapper.countByCustomerAndStatus(user_id, "ORDER_REFUSE");
    }

    public Map getOrderCrateDayByUserId(int user_id, Date begin, Date end) {
        List<String> timeList = new ArrayList<>();
        List<Integer> timeCount = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(begin);
        int day1 = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(end);
        int day2 = cal.get(Calendar.DAY_OF_YEAR);
        int dayCount = day2 - day1;
        if (dayCount < 0)
            return null;

        cal.setTime(begin);
        for (int i = 0; i <= dayCount; i++) {
            timeList.add(sdf.format(cal.getTime()));
            timeCount.add(orderMapper.countByDayAndCustomer(cal.getTime(), user_id));
            cal.add(Calendar.DATE, 1);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("timeList", timeList);
        map.put("timeCount", timeCount);
        return map;
    }

    //------------物流公司统计
    public int getCompanyPlaceCount(int user_id) {
        return orderMapper.countByCompanyAndStatus(user_id, "ORDER_PLACE");
    }

    public int getCompanyTakingCount(int user_id) {
        return orderMapper.countByCompanyAndStatus(user_id, "ORDER_TAKING");
    }

    public int getCompanySignCount(int user_id) {
        return orderMapper.countByCompanyAndStatus(user_id, "ORDER_SIGN");
    }

    public int getCompanyAllCount(int user_id) {
        return orderMapper.countByCompany(user_id);
    }

    public int getCompanyRefuseCount(int user_id) {
        return orderMapper.countByCompanyAndStatus(user_id, "ORDER_REFUSE");
    }

    public Map getOrderTakingDayByUserId(int user_id, Date begin, Date end) {
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
        if (dayCount < 0)
            return null;

        cal.setTime(begin);
        for (int i = 0; i <= dayCount; i++) {
            timeList.add(sdf.format(cal.getTime()));
            placeCount.add(orderMapper.countByDayAndWantCompany(cal.getTime(), user_id));
            takingCount.add(orderStatusMapper.countByDayAndUserAndStatus(cal.getTime(), user_id, Defind.ORDER_TAKING));
            signCount.add(orderStatusMapper.countByDayAndUserAndStatus(cal.getTime(), user_id, Defind.ORDER_SIGN));
            cal.add(Calendar.DATE, 1);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("timeList", timeList);
        map.put("placeCount", placeCount);
        map.put("takingCount", takingCount);
        map.put("signCount", signCount);
        return map;
    }

    //--------司机统计
    public int getDriverTakingCount(int user_id) {
        return orderMapper.countByDriverAndStatus(user_id, "ORDER_TAKING");
    }

    public int getDriverSignCount(int user_id) {
        return orderMapper.countByDriverAndStatus(user_id, "ORDER_SIGN");
    }

    public int getDriverAllCount(int user_id) {
        return orderMapper.countByDriver(user_id);
    }
}
