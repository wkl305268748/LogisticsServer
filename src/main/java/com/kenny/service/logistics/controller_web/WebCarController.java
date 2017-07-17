package com.kenny.service.logistics.controller_web;


import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.service.fleet.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by WKL on 2016-12-20.
 */
@RequestMapping("/web/car")
@Controller
public class WebCarController {
    @Autowired
    CarService carService;
    private static final int PAGESIZE = 10;

    @RequestMapping("/add")
    public String add() {
        return "car/add";
    }

    @RequestMapping("/add_submit")
    public String add_submit(@RequestParam(value = "car_plate") String car_plate,
                             @RequestParam(value = "car_resource") String car_resource,
                             @RequestParam(value = "car_type") String car_type,
                             @RequestParam(value = "remark") String remark) {

        carService.insert(car_plate,car_type,car_resource,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,remark);
        return "redirect:all";
    }

    @RequestMapping("/edit")
    public String edit(ModelMap map,@RequestParam(value = "id") int id) throws ErrorCodeException {
        map.addAttribute("data",carService.selectByPrimaryKey(id));
        return "car/edit";
    }

    @RequestMapping("/delete")
    public String delete(ModelMap map,@RequestParam(value = "id") int id) {
        carService.deleteByPrimaryKey(id);
        return "redirect:all";
    }

    @RequestMapping("/edit_submit")
    public String add_submit(@RequestParam(value = "id") int id,
                             @RequestParam(value = "car_plate") String car_plate,
                             @RequestParam(value = "car_resource") String car_resource,
                             @RequestParam(value = "car_type") String car_type,
                             @RequestParam(value = "remark") String remark) throws ErrorCodeException {
        carService.update(id,car_plate,car_type,car_resource,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,remark);
        return "redirect:all";
    }

    @RequestMapping("/all")
    public String all(ModelMap map,
                      @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data",carService.selectPage((page - 1)*PAGESIZE,PAGESIZE));
        return "car/all";
    }
}
