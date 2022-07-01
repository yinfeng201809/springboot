package com.example.demo.study.FutureTest;

import com.google.common.util.concurrent.FutureCallback;
import org.checkerframework.checker.nullness.qual.Nullable;

public class FutureCallbackImpl implements FutureCallback<String> {
    @Override
    public void onSuccess(@Nullable String s) {
        System.out.println("success");
    }

    @Override
    public void onFailure(Throwable throwable) {
        System.out.println("fail");
    }
}
