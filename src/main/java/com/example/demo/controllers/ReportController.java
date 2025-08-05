package com.example.demo.controllers;

import com.example.demo.services.AsyncService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@Tag(name = "ReportController", description = "报表接口")
@RequestMapping("/api/reports")
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
