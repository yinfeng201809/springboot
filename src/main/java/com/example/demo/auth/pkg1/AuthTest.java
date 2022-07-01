package com.example.demo.auth.pkg1;

import com.example.demo.auth.pkg2.SubClass;

public class AuthTest {

    public static void main(String[] args) {
        Clazz1 clazz1=new Clazz1();
        clazz1.pubM2();
        clazz1.defaultM1();
        clazz1.protectM3();

        SubClass subClass = new SubClass();
        subClass.protectM3();
        subClass.pubM2();

    }
}
