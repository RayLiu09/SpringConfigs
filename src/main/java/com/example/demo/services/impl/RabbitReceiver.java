package com.example.demo.services.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {
    @RabbitListener(queues = {"${spring.rabbitmq.queue.msg}"})
    public void receive(String msg) {
        System.out.println("Received: " + msg);
    }
}
