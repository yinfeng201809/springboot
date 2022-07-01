package com.example.demo.other;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

public class Md5Test {
    public static void main(String[] args) {
        String str = "abd1e";
        System.out.println(SecureUtil.md5(str));
        MD5 md5 = new MD5();
        md5.setSalt("aaa".getBytes(StandardCharsets.UTF_8));
        md5.setSaltPosition(211);
        System.out.println(md5.digestHex(str));

        System.out.println(DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8)));


        Date date = new Date();
        Date date1 = DateUtil.offset(date, DateField.DAY_OF_MONTH, -1);

        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        System.out.println(DateUtil.formatDateTime(date1));


    }
}
