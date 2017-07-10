package com.kenny.service.logistics.controller_web;


import com.kenny.service.logistics.json.response.WebMenuResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WKL on 2016-12-20.
 */
@RequestMapping("/web/driver")
@Controller
public class WebDriverController {

    @RequestMapping("/add")
    public String add() {
        return "driver/add";
    }

    @RequestMapping("/add_submit")
    public String add_submit() {
        return "driver/add_submit";
    }

    @RequestMapping("/all")
    public String all() {
        return "driver/all";
    }
}
