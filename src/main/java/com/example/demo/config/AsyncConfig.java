package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    /**
     * 配置异步线程池
     */
    @Override
    public Executor getAsyncExecutor() {
        // 设置线程池核心线程数参数
        int corePoolSize = 10;
        // 设置线程池最大线程数参数
        int maxPoolSize = 20;
        // 设置线程池队列容量参数
        int queueCapacity = 100;
        // 设置线程池超时时间
        int keepAliveTime = 60;
        // 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueCapacity));

        return executor;
    }
}
