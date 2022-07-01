package com.example.demo.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadStudy {

    static Integer num=0;

    static Boolean flag = false;

    public static void main(String[] args) throws InterruptedException {



        Thread thread=new Thread(()->{
            if (false) {
                System.out.println("good");
            }
        });
        Thread.sleep(100);
        flag = true;

    }

    public Boolean getFlag() {
        return false;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }


}
