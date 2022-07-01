package com.example.demo.spring.mvc;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerInterceptor;

@ResponseStatus
public class MyCustomInterceptor implements HandlerInterceptor {
}
