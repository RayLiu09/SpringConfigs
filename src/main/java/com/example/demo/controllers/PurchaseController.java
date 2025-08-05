package com.example.demo.controllers;

import com.example.demo.services.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@Slf4j
@Tag(name = "PurchaseController", description = "商品购买接口")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/ping")
    @Operation(summary = "服务可用性检测开放接口", description = "用于检测服务的运行状态")
    public String ping() {
        log.info("API服务运行状态良好，欢迎使用开放API服务.");
        return "pong";
    }

    @PostMapping("/purchase")
    @Operation(summary = "商品购买接口", description = "用户购买商品")
    @Parameters({
        @Parameter(name = "userId", description = "用户ID", required = true),
        @Parameter(name = "productId", description = "商品ID", required = true),
        @Parameter(name = "quantity", description = "商品数量", required = true)
    })
    public Map<Boolean, String> purchase(Long userId, Long productId, int quantity) {
        boolean result = purchaseService.purchase(userId, productId, quantity);
        HashMap<Boolean, String> map = new HashMap<>();
        map.put(result, result ? "购买成功" : "购买失败");
        return map;
    }
}
