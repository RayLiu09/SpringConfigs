package com.example.demo;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@ImportResource(value = {"classpath:spring-others.xml"})
public class DataSourceConfig {
    @Bean(name = "dataSource")
    @Profile("dev")
    public DataSource getDataSource() {
        Properties properties = new Properties();
        properties.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://192.168.1.182:3306/sales");
        properties.setProperty("username", "mysql");
        properties.setProperty("password", "MySQL_2024");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
