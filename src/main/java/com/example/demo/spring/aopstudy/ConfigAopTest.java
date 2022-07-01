package com.example.demo.spring.aopstudy;

import com.example.demo.domain.User;
import com.example.demo.spring.event.EmailService;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Properties;
import java.util.Set;

@Configuration
public class ConfigAopTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConfigAopTest.class, AppAopConfig.class);
        context.refresh();
        User user = context.getBean(User.class);
        User newUser = new User();
        newUser.setId(333L);
        RequestContextUtils.findWebApplicationContext(null);
        user.annoUser(newUser);
    }

    @Bean
    @Scope()
    public User user() {
        User user = new User();
        return user;
    }
}


