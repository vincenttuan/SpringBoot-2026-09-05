package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		
		// 進位法則
		// 1.四捨五入 HALF_UP
		// 2.五捨六入 HALF_DOWN
		// 3.四捨六入五成双 HALF_EVEN
		// setScale 設定小數位術與進位法則
		BigDecimal price = BigDecimal.valueOf(19.565);
		//price = price.setScale(2, RoundingMode.HALF_UP); // 19.565 -> 19.57
		//price = price.setScale(2, RoundingMode.HALF_DOWN); // 19.565 -> 19.56
		price = price.setScale(2, RoundingMode.HALF_EVEN); // 19.565 -> 19.57 (進位後因為7不是偶數,則不進位) ->  19.56
		System.out.println(price);
	}
	
}
