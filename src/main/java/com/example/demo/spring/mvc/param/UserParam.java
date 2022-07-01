package com.example.demo.spring.mvc.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserParam {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String name;

    private Date date;

}
