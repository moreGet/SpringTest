package com.ensat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/papa")
    String index() {
    	System.out.println("111");
        return "index";
    }

}
