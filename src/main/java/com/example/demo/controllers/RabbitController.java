package com.example.demo.controllers;

import com.example.demo.services.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    private RabbitMQService rabbitMQService;

    @RequestMapping("/send")
    public Map<String, Object> send() {
        rabbitMQService.sendMsg("Hello RabbitMQ!");
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "success");
        return result;
    }
}
