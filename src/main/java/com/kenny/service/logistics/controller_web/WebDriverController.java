package com.kenny.service.logistics.controller_web;


import com.kenny.service.logistics.json.response.WebMenuResponse;
import com.kenny.service.logistics.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WKL on 2016-12-20.
 */
@RequestMapping("/web/driver")
@Controller
public class WebDriverController {
    @Autowired
    DriverService driverService;
    private static final int PAGESIZE = 10;

    @RequestMapping("/add")
    public String add() {
        return "driver/add";
    }

    @RequestMapping("/add_submit")
    public String add_submit(@RequestParam(value = "name") String name,
                             @RequestParam(value = "sex") String sex,
                             @RequestParam(value = "phone") String phone,
                             @RequestParam(value = "driver_license") String driver_license,
                             @RequestParam(value = "remark") String remark) {
        driverService.addDriver(name,phone,sex,driver_license,remark);
        return "redirect:all";
    }

    @RequestMapping("/edit")
    public String edit(ModelMap map,@RequestParam(value = "id") int id) {
        map.addAttribute("data",driverService.getDriver(id));
        return "driver/edit";
    }

    @RequestMapping("/delete")
    public String delete(ModelMap map,@RequestParam(value = "id") int id) {
        driverService.deleteDriver(id);
        return "redirect:all";
    }

    @RequestMapping("/edit_submit")
    public String add_submit(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "sex") String sex,
                             @RequestParam(value = "phone") String phone,
                             @RequestParam(value = "driver_license") String driver_license,
                             @RequestParam(value = "remark") String remark) {
        driverService.editDriver(id,name,phone,sex,driver_license,remark);
        return "redirect:all";
    }

    @RequestMapping("/all")
    public String all(ModelMap map,
                      @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data",driverService.getDriversAll(PAGESIZE,(page - 1)*PAGESIZE));
        return "driver/all";
    }
}
