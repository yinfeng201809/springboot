package com.example.demo.rsa;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.*;
import java.util.Date;

public class RsaDemo {

    public static void main(String[] args) throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey= (RSAPrivateKey) keyPair.getPrivate();
        System.out.println("私钥：");
        System.out.println(Base64.encode(privateKey.getEncoded()));
        System.out.println("公钥：");
        System.out.println(Base64.encode(publicKey.getEncoded()));

        DateTime dateTime=DateTime.now();
        LocalDateTime localDateTime=LocalDateTime.now();
        localDateTime.plusDays(1);
        ZonedDateTime localDate = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault());
        Date date = Date.from(localDate.toInstant());
        Date date1 = DateUtil.offset(new Date(), DateField.MONTH, 1);
    }
}
