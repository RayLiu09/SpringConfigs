package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * SpringBootTest启动时不会启动Web服务器,故如果应用中集成了WebSocket功能,为避免WebSocket报错，
 * 需要使用@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
