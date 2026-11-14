package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootJpaApplicationTests {

	@Test
	void contextLoads() {
		char c = '\u8A2D';
		System.out.println(c);
	}

}
