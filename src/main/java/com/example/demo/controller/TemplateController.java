package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {




    @RequestMapping("/tem")
    public String index()  throws  Exception{
        return "hello";
    }

}













