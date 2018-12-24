package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserEntity;
import com.example.demo.domain.UserRepository;
import com.example.demo.mapper.UserMapper;
import com.example.demo.properties.NeoProperties;
import com.example.demo.service.MailServiceImpl;
import com.example.demo.support.UserSexEnum;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
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
    private UserMapper userMapper;

    @Autowired
    private MailServiceImpl mailService;

    @RequestMapping("/")
    public String index()  throws  Exception{

        stringRedisTemplate.opsForValue().set("aaa", "111");
        String value=stringRedisTemplate.opsForValue().get("aaa");
        if (value == null) {
            value = "nullvalue";
        }
        User user=new User("aa@126.com", "aa", "aa123456", "aa","123");
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
    @RequestMapping("/mybatis")
    String mybatis(HttpSession session) {
        userMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
        userMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));


        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
    @RequestMapping("/allusers")
    public String testQuery() throws Exception {
        List<UserEntity> users = userMapper.getAll();
        System.out.println(users.toString());
        return users.toString();
    }
    @RequestMapping("/update/user")
    public String testUpdate() throws Exception {
        UserEntity user = userMapper.getOne(3L);
        System.out.println(user.toString());
        user.setNickName("neo");
        user.setUserName("userName");
        userMapper.update2(user);
        return user.toString();
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
