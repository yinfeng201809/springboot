package com.example.demo.study.jwt;


import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.MD5;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class JwtToken {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();


        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 获取token
        String jwtToken = Jwts.builder().claim("a","aaaaaa").setId("id").setExpiration(getExpireDate())
                        .signWith(privateKey).compact();

        System.out.println(jwtToken);

        // 解密
        RSA rsa = new RSA(null, Base64.encode(keyPair.getPublic().getEncoded()));
        MD5.create().digest("", "");
        Jws<Claims> result = Jwts.parser().setSigningKey(rsa.getPublicKey()).parseClaimsJws(jwtToken);

        System.out.println(result.getBody().get("a"));
    }

    private static Date getExpireDate() {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        Date expireDate=calendar.getTime();
        return expireDate;
    }


    /**
     * javaJwt 非对称加密实现
     * @throws NoSuchAlgorithmException
     */
    private static void javaJwtRsaImpl() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String privateKeyStr = Base64.encode(privateKey.getEncoded());
        String publicKeyStr = Base64.encode(publicKey.getEncoded());
        RSA rsa = new RSA(privateKeyStr, publicKeyStr);

        JWTCreator.Builder jwtCreator = JWT.create();
        jwtCreator.withClaim("userId", "uuuid");
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        String token = jwtCreator.withExpiresAt(calendar.getTime()).sign(Algorithm.RSA256(publicKey, privateKey));
        System.out.println(token);

        JWTVerifier verifier = JWT.require(Algorithm.RSA256(publicKey, privateKey)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        System.out.println(decodedJWT.getClaim("userId").asString());

    }

    /**
     * 利用javaJwt 生成jwtToken
     */
    private static void javaJWT() {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        String token = JWT.create().withHeader(new HashMap<>())
                .withClaim("userId","abc")
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256("abc"));
        System.out.println(token);

        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("abc")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String header = decodedJWT.getHeader();
        Claim claim = decodedJWT.getClaim("userId");
        String payload = decodedJWT.getPayload();
        System.out.println(header);
        System.out.println(claim.asString());
        System.out.println(payload);
    }
}
