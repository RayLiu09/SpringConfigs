package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ConfigurationProperties(prefix = "spring.rabbitmq")
@PropertySource("classpath:application.rabbitmq.properties")
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.queue.msg}")
    private String queueMsg;

    @Bean
    public Queue initQueue() {
        System.out.println("RabbitMQConfig.initQueue()");
        return new Queue(queueMsg, true);
    }
}
