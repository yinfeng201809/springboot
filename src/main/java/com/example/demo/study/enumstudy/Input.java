package com.example.demo.study.enumstudy;

import java.util.Map;
import java.util.Random;

public enum Input {

    NICKEL(5), DIME(10),QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORD_TRANSACTION {
        public int amount() {
            throw new RuntimeException("abord.amount()");
        }
    },
    STOP{
        public int amount() {
            throw new RuntimeException("STOP.amount()");
        }
    };
    int value;

    static int count=0;

    Input(int value) {
        this.value=value;
    }

    Input() {

    }

    int amount() {
        return value;
    }

    static Random random = new Random();

    public static Input randomSelection() {
        count++;
        if (count > 11) {
            return STOP;
        }
        return values()[random.nextInt(values().length-1)];
    }

}
