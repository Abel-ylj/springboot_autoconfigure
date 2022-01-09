package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : ylj
 * create at:  2022/1/9
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerTestController {

    @RequestMapping("/test")
    public String testFreemarker(ModelMap modelMap){
        modelMap.addAttribute("msg", "Hello ylj , this is freemarker");
        return "freemarker";
    }
}