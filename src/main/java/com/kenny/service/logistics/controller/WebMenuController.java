package com.kenny.service.logistics.controller;


import com.kenny.service.logistics.json.response.WebMenuResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WKL on 2016-12-20.
 */
@RequestMapping("/web")
@Controller
public class WebMenuController {

    @RequestMapping("")
    public String index(ModelMap map) {
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
                        new WebMenuResponse("/web/finance/receive", "应收账款"),
                        new WebMenuResponse("/web/finance/pay", "应付账款"),
                        new WebMenuResponse("/web/finance/profit", "利润表"))
        );
        menus.add(
                new WebMenuResponse("", "设置中心",0,"",true)
        );
        map.addAttribute("menus", menus);

        return "index";
    }


    @RequestMapping("/home")
    public String home() {
        return "web/home";
    }
}
