package com.kenny.service.logistics.controller_web;


import com.kenny.service.logistics.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by WKL on 2016-12-20.
 */
@RequestMapping("/web/profit")
@Controller
public class WebProfitController {
    @Autowired
    ProfitService profitService;
    private static final int PAGESIZE = 10;

    @RequestMapping("/recive")
    public String recive(@RequestParam(value = "id") int id) {
        profitService.editProfitRecive(id);
        return "redirect:recive_all";
    }

    @RequestMapping("/pay")
    public String pay(@RequestParam(value = "id") int id) {
        profitService.editProfitPay(id);
        return "redirect:pay_all";
    }

    @RequestMapping("/recive_all")
    public String recive_all(ModelMap map,
                      @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data",profitService.getProfitAll(PAGESIZE,(page - 1)*PAGESIZE));
        return "profit/recive_all";
    }


    @RequestMapping("/profit_all")
    public String profit_all(ModelMap map,
                             @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data",profitService.getProfitAll(PAGESIZE,(page - 1)*PAGESIZE));
        return "profit/profit_all";
    }


    @RequestMapping("/pay_all")
    public String pay_all(ModelMap map,
                             @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data",profitService.getProfitAll(PAGESIZE,(page - 1)*PAGESIZE));
        return "profit/pay_all";
    }
}
