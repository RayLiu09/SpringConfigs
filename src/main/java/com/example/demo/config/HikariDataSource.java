//package com.example.demo.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import org.springframework.context.annotation.Configuration;
//
//import java.sql.Connection;
////@Configuration
//public class HikariDataSource {
//    private static HikariConfig config = new HikariConfig();
////    private static HikariConfig config2 = new HikariConfig("application-hikari.yaml");
//    private static com.zaxxer.hikari.HikariDataSource dataSource;
//
//    static {
//        config.setJdbcUrl("jdbc_url");
//        config.setUsername("username");
//        config.setPassword("password");
//        config.setAutoCommit(true);
//        config.setConnectionTimeout(30000);
//        config.setIdleTimeout(60000);
//        config.setMaxLifetime(1800000);
//        config.setMaximumPoolSize(10);
//        config.setPoolName("HikariPool-0");
//        config.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
//        config.setLeakDetectionThreshold(0);
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        dataSource = new com.zaxxer.hikari.HikariDataSource(config);
//    }
//
//    private HikariDataSource() {
//    }
//
//    public static Connection getConnection() {
//        try {
//            return dataSource.getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
