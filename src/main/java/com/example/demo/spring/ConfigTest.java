package com.example.demo.spring;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.demo.spring.event.EmailService;
import org.springframework.context.annotation.*;

import java.util.Properties;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = "com.example.demo.spring")
@PropertySource("classpath:application.properties")
@PropertySource("classpath:mycutson.properties")
public class ConfigTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConfigTest.class);
        context.refresh();
//        BeanTest user = context.getBean("beanTest", BeanTest.class);
//        testEnvironment(context);
        EmailService emailService = context.getBean(EmailService.class);
        emailService.sendEmail("ab,", "ddd");
    }

    private static void testEnvironment(AnnotationConfigApplicationContext context) {
        //        for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
//            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
//        }
        Properties properties = System.getProperties();
        Set set = properties.keySet();
        System.out.println("======================================================");
        for (Object key : set) {
            System.out.println(key+"        "+properties.getProperty(key.toString()));
        }
        System.out.println(context.getEnvironment().containsProperty("sun.cpu.endian"));
        System.out.println(context.getEnvironment().containsProperty("HOMEPATH"));
    }
}


