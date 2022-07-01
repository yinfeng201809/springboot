package com.example.demo.spring.event;

import com.example.demo.domain.User;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BlockedListAnnoNotifier {

    @EventListener
    @Async
    public void processBlockedListEvent(BlockedListEvent event) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"    annotation event");
        // notify appropriate parties via notificationAddress...
    }

    @EventListener
    public void onPersonCreated(EntityCreatedEvent<User> user) {
        System.out.println("use created");
        // ...
    }

}
