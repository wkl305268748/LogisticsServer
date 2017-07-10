package com.kenny.service.logistics.controller_web;


import com.kenny.service.logistics.json.response.WebMenuResponse;
import com.kenny.service.logistics.service.CarService;
import com.kenny.service.logistics.service.DriverService;
import com.kenny.service.logistics.service.OrderCustomerService;
import com.kenny.service.logistics.service.OrderTakingService;
import com.kenny.service.logistics.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by WKL on 2016-12-20.
 */
@RequestMapping("/web")
@Controller
public class WebMenuController {
    @Autowired
    CarService carService;
    @Autowired
    DriverService driverService;
    @Autowired
    OrderCustomerService orderCustomerService;
    @Autowired
    OrderTakingService orderTakingService;

    @RequestMapping("")
    public String index(ModelMap map,
                        HttpSession session) {
        if(session.getAttribute("username") == null){
            return "redirect:web/login";
        }

        List<WebMenuResponse> menus = new ArrayList<>();

        menus.add(
                new WebMenuResponse("", "业务中心",0,"",true)
        );
        menus.add(
                new WebMenuResponse("", "下单管理",0,"icon-social-dropbox",false,
                        new WebMenuResponse("/web/order/create", "开始下单"),
                        new WebMenuResponse("/web/order/all", "所有下单"))
        );
        menus.add(
                new WebMenuResponse("", "运单管理",0,"icon-layers",false,
                        new WebMenuResponse("/web/order/pending", "待处理运单"),
                        new WebMenuResponse("/web/order/taking_all", "所有运单"))
        );
        menus.add(
                new WebMenuResponse("", "司机管理",0,"icon-user",false,
                        new WebMenuResponse("/web/driver/add", "添加司机"),
                        new WebMenuResponse("/web/driver/all", "我的司机"))
        );
        menus.add(
                new WebMenuResponse("", "车辆管理",0,"icon-shuffle",false,
                        new WebMenuResponse("/web/car/add", "添加车辆"),
                        new WebMenuResponse("/web/car/all", "我的车辆"))
        );
        menus.add(
                new WebMenuResponse("", "财务中心",0,"",true)
        );
        menus.add(
                new WebMenuResponse("", "财务管理",0,"icon-wallet",false,
                        new WebMenuResponse("/web/profit/recive_all", "应收账款"),
                        new WebMenuResponse("/web/profit/pay_all", "应付账款"),
                        new WebMenuResponse("/web/profit/profit_all", "利润表"))
        );
        menus.add(
                new WebMenuResponse("", "设置中心",0,"",true)
        );
        map.addAttribute("menus", menus);

        return "index";
    }

    @RequestMapping("/home")
    public String home(ModelMap map) {
        map.addAttribute("car",carService.getCarsAll(0,1).getTotal());
        map.addAttribute("driver",driverService.getDriversAll(0,1).getTotal());
        map.addAttribute("order_customer_wait",orderCustomerService.getOrderCustomerWait(0,1).getTotal());
        map.addAttribute("order_taking",orderTakingService.getOrderTakings(0,1).getTotal());
        return "web/home";
    }

    @RequestMapping("/login")
    public String login() {
        return "web/login";
    }

    @RequestMapping("/logout")
    public String login(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:login";
    }

    @RequestMapping("/login_submit")
    public String login_submit(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               HttpSession session) {
        if(username.trim().equals("admin") && password.trim().equals("admin123")){
            session.setAttribute("username",username);
            return "redirect:/web";
        }else{
            return "redirect:login";
        }
    }

    @RequestMapping("/img/{filename:.+}")
    public ResponseEntity<?> img(@PathVariable String filename) {
        return FileUtil.getImg(filename);
    }

}
