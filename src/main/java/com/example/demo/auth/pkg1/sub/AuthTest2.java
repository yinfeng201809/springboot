package com.example.demo.auth.pkg1.sub;

import com.example.demo.auth.pkg1.Clazz1;
import com.example.demo.auth.pkg2.SubClass;
import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.*;

public class AuthTest2 {
    public static void main(String[] args) {
        Clazz1 clazz1 = new Clazz1();
        clazz1.pubM2();

        SubClass subClass = new SubClass();
//        subClass.protectM3();
        subClass.pubM2();

        SubClass subClass2 = new SubClass();
        System.out.println(subClass2 == subClass2);

        String s = "2";
        String s2 = "2";


        System.out.println(s == s2);
        String s3 = new String("2");
//        s3.intern();
        String s4 = new String("2");
        System.out.println("s3==s4:"+(s3==s4));


        String s1 = new String("二哥三妹");
        String s22 = s1.intern();
        System.out.println(s1 == s22);

        String s123 = new String("二哥") + new String("三妹");
        String s223 = s1.intern();
        System.out.println(s123 == s223);


        try (FileOutputStream fos = new FileOutputStream("tempFile");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(clazz1);
        } catch (IOException ioException) {

        }
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.DAYS,
                new LinkedBlockingDeque<>());



    }
}
