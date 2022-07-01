package com.example.demo.spring.mvc;


import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 异步调用测试
 */
@RestController
@RequestMapping("mvc")
public class MvcController {

    @InitBinder
    public void dateBinder(WebDataBinder webDataBinder) {
        webDataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    @PostMapping("/databinder")
    public Date date(Date date) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        return date;
    }




}
