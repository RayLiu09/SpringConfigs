package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:application.jdbc.properties"),
		@PropertySource("classpath:application.redis.properties"),
		@PropertySource("classpath:application-mybatis.properties"),
		@PropertySource("classpath:application-logging.properties"),
		@PropertySource("classpath:application.rabbitmq.properties")
})
@MapperScan(value = "com.example.demo.repositories", sqlSessionFactoryRef = "sqlSessionFactory", annotationClass = Repository.class)
@EnableWebSecurity
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private PlatformTransactionManager transactionManager;

	@PostConstruct
	public void init() {
		System.out.println("transactionManager: " + transactionManager.getClass().getName());
	}

}
