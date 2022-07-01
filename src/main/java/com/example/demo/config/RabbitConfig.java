package com.example.demo.config;

import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@SpringBootConfiguration
public class RabbitConfig {

    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }
}
