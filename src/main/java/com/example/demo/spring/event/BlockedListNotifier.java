package com.example.demo.spring.event;

import com.example.demo.spring.event.BlockedListEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BlockedListNotifier  implements ApplicationListener<BlockedListEvent> {

    private String notificationAddress;

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }

    public void onApplicationEvent(BlockedListEvent event) {
        System.out.println(Thread.currentThread().getName()+"  myevent");
        // notify appropriate parties via notificationAddress...
    }
}