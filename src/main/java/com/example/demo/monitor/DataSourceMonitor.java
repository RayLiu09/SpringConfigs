package com.example.demo.monitor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
// 实现Spring Bean生命周期接口ApplicationContextAware
public class DataSourceMonitor implements ApplicationContextAware {
    ApplicationContext ctx = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
        DataSource dataSource = (DataSource) ctx.getBean(DataSource.class);
        System.out.println("****************DataSourceMonitor.setApplicationContext******************");
        System.out.println(dataSource.getClass().getName());
        System.out.println("****************DataSourceMonitor.setApplicationContext******************");
    }
}
