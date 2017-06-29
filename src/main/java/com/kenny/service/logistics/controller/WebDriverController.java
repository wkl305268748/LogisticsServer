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
@RequestMapping("/web/driver")
@Controller
public class WebDriverController {

    @RequestMapping("/mydriver")
    public String mydriver() {
        return "driver/mydriver";
    }
}
