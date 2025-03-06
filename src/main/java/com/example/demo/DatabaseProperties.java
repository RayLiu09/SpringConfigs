package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseProperties {
//    @Value("${driverClassName}")
    private String driverName;
//    @Value("${url}")
    private String url;
    private String username = null;
    private String password = null;

    public void setDriverName(String driverName) {
        System.out.println("setDriverName");
        this.driverName = driverName;
    }
    public void setUrl(String url) {
        System.out.println("setUrl");
        this.url = url;
    }
//    @Value("${username}")
    public void setUsername(String username) {
        System.out.println("setUsername");
        this.username = username;
    }
//    @Value("${password}")
    public void setPassword(String password) {
        System.out.println("setPassword");
        this.password = password;
    }
}
