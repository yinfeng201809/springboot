package com.example.demo.spring.mvc;


import com.example.demo.spring.mvc.param.UserParam;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/databinder")
public class DateBinderController {


    @InitBinder
    public void dateConver(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class,  new MyDateEditor());
//        webDataBinder.registerCustomEditor(Date.class,  new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
    }

    @PostMapping("/commondate")
    public Date date(@RequestParam("daTaskExecutorte") Date date) {
        return date;
    }
    @PostMapping("/commondate2")
    public Date date2(@RequestParam("date2")Date date2) {
        return date2;
    }

    @PostMapping("/objDate")
    public Date userParam(@RequestBody UserParam userParam) {
        return userParam.getDate();
    }

    @PostMapping("customDate")
    public Date customDate(@RequestParam Date date) {
        System.out.println(date);
        return date;
    }
}
