package com.example.demo.spring.event;

import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class EmailService implements ApplicationEventPublisherAware {

    private List<String> blockedList;
    private ApplicationEventPublisher publisher;

    @Value("classpath:application.properties")
    private Resource resource;

    public void setBlockedList(List<String> blockedList) {
        this.blockedList = blockedList;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void sendEmail(String address, String content) {
        publisher.publishEvent(new BlockedListEvent(this, address, content));
        System.out.println(Thread.currentThread().getName()+" sendEmail");
        publisher.publishEvent(new EntityCreatedEvent<>(new User()));
        System.out.println(resource == null);
        System.out.println(resource.isFile());
        try (InputStream inputStream = resource.getInputStream()) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            System.out.println(reader.read());;
        } catch (IOException e) {
            e.printStackTrace();
        }
        // send email...
    }
}
