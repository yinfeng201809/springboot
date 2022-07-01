package com.example.demo.spring.validation;

import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class HelloServiceImpl implements HelloService {
    @Override
    @GetMapping(value = "",params = {"","b"})
    public Object hello(@NotNull @Min(10) Integer id, @NotNull String name) {
        System.out.println("hello");
        return null;
    }
}
