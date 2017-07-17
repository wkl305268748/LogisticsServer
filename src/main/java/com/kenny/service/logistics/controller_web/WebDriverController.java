package com.kenny.service.logistics.controller_web;


import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.json.response.WebMenuResponse;
import com.kenny.service.logistics.service.fleet.DriverService;
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
        driverService.insert(name,sex,phone,0,false,driver_license,null,null,remark);
        return "redirect:all";
    }

    @RequestMapping("/edit")
    public String edit(ModelMap map,@RequestParam(value = "id") int id) throws ErrorCodeException {
        map.addAttribute("data",driverService.selectByPrimaryKey(id));
        return "driver/edit";
    }

    @RequestMapping("/delete")
    public String delete(ModelMap map,@RequestParam(value = "id") int id) {
        driverService.deleteByPrimaryKey(id);
        return "redirect:all";
    }

    @RequestMapping("/edit_submit")
    public String add_submit(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "sex") String sex,
                             @RequestParam(value = "phone") String phone,
                             @RequestParam(value = "driver_license") String driver_license,
                             @RequestParam(value = "remark") String remark) throws ErrorCodeException {
        driverService.update(id,name,sex,phone,0,false,driver_license,null,null,remark);
        return "redirect:all";
    }

    @RequestMapping("/all")
    public String all(ModelMap map,
                      @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data",driverService.selectPage((page - 1)*PAGESIZE,PAGESIZE));
        return "driver/all";
    }
}
