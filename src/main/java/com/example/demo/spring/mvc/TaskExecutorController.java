package com.example.demo.spring.mvc;


import cn.hutool.core.date.DateUtil;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/executor")
public class TaskExecutorController {

    @Autowired
    private TaskExecutor taskExecutor;

    @PostMapping("/task")
    public User user(int count) {

        taskExecutor.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(5L);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        DateUtil.parse("");
        return new User();
    }

    private class MessagePrinterTask implements Runnable {

        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + message);
        }
    }
}
