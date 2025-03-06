package com.example.demo.services.impl;

import com.example.demo.services.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async
    public void mimicLongRunningTask() {
        System.out.println("长执行时间任务：" + "【" + Thread.currentThread().getName() + "】");
    }
}
