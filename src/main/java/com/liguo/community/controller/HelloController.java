package com.liguo.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dogbro on 2019-10-24 10:42
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }
}
