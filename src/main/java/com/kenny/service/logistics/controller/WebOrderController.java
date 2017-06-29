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
@RequestMapping("/web/order")
@Controller
public class WebOrderController {

    @RequestMapping("/all")
    public String all() {
        return "order/all";
    }

    @RequestMapping("/create")
    public String create() {
        return "order/create";
    }

    @RequestMapping("/pending")
    public String pending() {
        return "order/pending";
    }
}
