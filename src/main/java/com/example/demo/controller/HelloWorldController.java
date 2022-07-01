package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloWorldController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailServiceImpl mailService;

    @RequestMapping("/")
    public String index()  throws  Exception{

        stringRedisTemplate.opsForValue().set("aaa", "111");
        String value=stringRedisTemplate.opsForValue().get("aaa");
        if (value == null) {
            value = "nullvalue";
        }
        User user=new User();
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user,11, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }

        return value;
    }

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
        User user=userRepository.findByUserName("aa1");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
    @RequestMapping("/mail/send")
    public String testUpdate2()  {
        mailService.sendSimpleMail("316133870@qq.com","haha","heihei");
        return "";
    }
    @RequestMapping("/mail/send2")
    public String testUpdate22()  {
        for (int i = 0; i < 20; i++) {
            mailService.sendSimpleMail("316133870@qq.com","haha","heihei");
        }
        return "";
    }

}
