package com.example.demo.spring.json;

import com.alibaba.fastjson.JSON;
import com.example.demo.spring.mvc.MyDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.Date;

@RestController
@RequestMapping("json")
public class JsonController {


    @InitBinder
    public void dateBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class,new MyDateEditor());
    }

    @RequestMapping("/seria")
    public RespUser test(@RequestBody RespUser respUserParam) {
        System.out.println(JSON.toJSONString(respUserParam));
        RespUser respUser = new RespUser();
        respUser.setBirthDay(new Date());
        respUser.setUpdateTime(new Date());
        return respUserParam;
    }
}
