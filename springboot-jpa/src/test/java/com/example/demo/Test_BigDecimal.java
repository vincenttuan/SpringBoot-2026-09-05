package com.example.demo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class Test_BigDecimal {
	
	@Test
	public void test() {
		// 浮點數四則運算
		System.out.println(0.1 + 0.1); // 0.2 (近似值)
		System.out.println(0.1 + 0.1 == 0.2); // true
		
		System.out.println(0.1 + 0.1 + 0.1); // 0.30000000000000004 (近似值)
		System.out.println(0.1 + 0.1 + 0.1 == 0.3); // false
		
		BigDecimal b1 = BigDecimal.valueOf(0.1);
		BigDecimal b2 = BigDecimal.valueOf(0.1);
		BigDecimal b3 = BigDecimal.valueOf(0.1);
		System.out.println(b1.add(b2).add(b3)); // 0.3 (實際值)
		System.out.println(b1.add(b2).add(b3).equals(BigDecimal.valueOf(0.3))); // true
		
	}
	
}
