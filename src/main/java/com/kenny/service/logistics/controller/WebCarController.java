package com.kenny.service.logistics.controller;


import com.kenny.service.logistics.service.CarService;
import com.kenny.service.logistics.service.DriverService;
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

        carService.addCar(car_plate,car_resource,car_type,remark);
        return "redirect:all";
    }

    @RequestMapping("/edit")
    public String edit(ModelMap map,@RequestParam(value = "id") int id) {
        map.addAttribute("data",carService.getCar(id));
        return "car/edit";
    }

    @RequestMapping("/delete")
    public String delete(ModelMap map,@RequestParam(value = "id") int id) {
        carService.deleteCar(id);
        return "redirect:all";
    }

    @RequestMapping("/edit_submit")
    public String add_submit(@RequestParam(value = "id") int id,
                             @RequestParam(value = "car_plate") String car_plate,
                             @RequestParam(value = "car_resource") String car_resource,
                             @RequestParam(value = "car_type") String car_type,
                             @RequestParam(value = "remark") String remark) {
        carService.editCar(id,car_plate,car_resource,car_type,remark);
        return "redirect:all";
    }

    @RequestMapping("/all")
    public String all(ModelMap map,
                      @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data",carService.getCarsAll(PAGESIZE,(page - 1)*PAGESIZE));
        return "car/all";
    }
}
