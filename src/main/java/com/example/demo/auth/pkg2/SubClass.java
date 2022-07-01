package com.example.demo.auth.pkg2;

import com.example.demo.auth.pkg1.Clazz1;

public class SubClass extends Clazz1 {

    public SubClass() {
        super.protectM3();
    }
    public static void main(String[] args) {
        SubClass clazz1 = new SubClass();
        clazz1.pubM2();
        clazz1.protectM3();
    }
}
