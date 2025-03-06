package com.example.demo.controllers;

import com.example.demo.services.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReportController {
    @Autowired
    private AsyncService asyncService;
    @GetMapping("/report")
    public Map<String, Object> report() {
        Map<String, Object> map = new HashMap<>();
        System.out.println("The request received thread name: " + Thread.currentThread().getName());
        asyncService.mimicLongRunningTask();
        System.out.println("Response returned without waiting for long task result.");
        map.put("name", "report");
        return map;
    }
}
