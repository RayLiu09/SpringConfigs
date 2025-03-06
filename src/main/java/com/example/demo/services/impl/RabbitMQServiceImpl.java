package com.example.demo.services.impl;

import com.example.demo.services.RabbitMQService;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQServiceImpl implements RabbitMQService, RabbitTemplate.ConfirmCallback {
    @Value("${spring.rabbitmq.queue.msg}")
    private String msgRouting;

    /**
     * 注入由Spring Boot自动配置的RabbitTemplate
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public void sendMsg(String msg) {
        System.out.println("发送RabbitMQ消息：" + msg);
        // 设置回调函数，用于消息发送到RabbitMQ交换机确认
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(msgRouting, msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送到RabbitMQ交换机成功");
        } else {
            System.out.println("消息发送到RabbitMQ交换机失败，原因：" + cause);
        }
    }
}
