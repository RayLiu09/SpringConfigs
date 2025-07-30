package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseProperties {
    @Value("${driverClassName}")
    private String driverName;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username = null;
    @Value("${password}")
    private String password = null;

    @Bean
    public DataSource initDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
