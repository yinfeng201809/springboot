package com.example.demo.guava;

import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.demo.domain.User;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import org.checkerframework.checker.regex.RegexUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MapTest {

    public static void main(String[] args) {
        BiMap<String, Integer> map = HashBiMap.create();
        map.put("a", 1);
        map.put("b", 2);
        System.out.println(map.get("a"));
        System.out.println(map.inverse().get(1));

        User user = new User();
        user.setId(1L);
        user.setUserName("abc");
        user.setPhoneList(Arrays.asList("15153170365"));
        user.setDateTest(new Date());
        String j1 = JSONUtil.toJsonStr(user);

        String s2 = JSON.toJSONString(user);
        Console.log(j1);
        Console.log(s2);
        Console.log(JSONUtil.toXmlStr(JSONUtil.parse(user)));

        User user1 = JSONUtil.toBean(j1, User.class);
        System.out.println(user1.getDateTest());
        s2 = DateUtil.formatDateTime(new Date());

//        httpTest();

//        s = HttpUtil.get("http://21av.cc/vod-detail-id-41711.html");

        Date birthDay = DateUtil.parse("1985-07-18");
        Date now = new Date();
        long betweenDay = DateUtil.betweenDay(birthDay, now, false);
        String formatInterval = DateUtil.formatBetween(birthDay, now, BetweenFormatter.Level.HOUR);
        System.out.println(formatInterval);
        System.out.println(DateUtil.ageOfNow(birthDay));
        System.out.println(DateUtil.getChineseZodiac(1985));

        FileUtil.file("");

    }

    private static void httpTest() {
        String s = HttpUtil.get("http://21av.cc/vod-type-id-1-pg-1.htm");

        List<String> list = ReUtil.findAll("<a href=\"/(.*?)\" title=\"(.*?)\">(.*?)</a>", s, 1);
        for (String s1 : list) {
            System.out.println(s1);
        }
    }
}
