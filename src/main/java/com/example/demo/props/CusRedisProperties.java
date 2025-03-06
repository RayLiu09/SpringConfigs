package com.example.demo.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis")
public class CusRedisProperties {
    @Value("${host}")
    private String host;

    @Value("${port}")
    private int port;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${database}")
    private int database;
    @Value("${max-idle}")
    private int maxIdle;
    @Value("${max-total}")
    private int maxTotal;
    @Value("${max-wait-millis}")
    private int maxWaitMillis;

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getDatabase() {
        return database;
    }
    public void setDatabase(int database) {
        this.database = database;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    @Override
    public String toString() {
        return "RedisProperties [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password
                + ", database=" + database + "]";
    }
}

