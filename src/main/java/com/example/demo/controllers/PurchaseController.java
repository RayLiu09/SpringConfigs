package com.example.demo.controllers;

import com.example.demo.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/purchase")
    public Map<Boolean, String> purchase(Long userId, Long productId, int quantity) {
        boolean result = purchaseService.purchase(userId, productId, quantity);
        HashMap<Boolean, String> map = new HashMap<>();
        map.put(result, result ? "购买成功" : "购买失败");
        return map;
    }
}
