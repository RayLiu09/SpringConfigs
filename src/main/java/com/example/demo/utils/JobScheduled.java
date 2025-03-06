package com.example.demo.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduled {
//    @Scheduled(fixedRate = 1000)
    @Scheduled(cron = "0 59 23 ? * *") // 每天23:59:00执行一次
    @Async
    public void scheduled() {
        System.out.println("Scheduled job: 每天23:59:00运行一次，执行线程名称" + "【" + Thread.currentThread().getName() + "】");
    }
}
