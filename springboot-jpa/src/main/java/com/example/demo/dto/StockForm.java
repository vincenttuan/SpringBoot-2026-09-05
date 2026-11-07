package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Java 16 開始可以利用 record 來取代一些 Lombok 的功能
public record StockForm(
		
		// 股票代號
		@NotBlank(message = "{stock.symbol.notBlank}") // 不可空白
		@Pattern(regexp = "^[A-Za-z0-9]+$", message = "{stock.symbol.pattern}") // 必須是英數字
		@Size(max = 10, message = "{stock.symbol.size}") // 有字數限制
		String symbol, 
		
		// 股票名稱
		@NotBlank(message = "{stock.name.notBlank}") // 不可空白
		@Size(max = 50, message = "{stock.name.size}") // 有字數限制
		String name, 
		
		// 股價價格
		@NotNull(message = "{stock.price.notNull}") // 不可是空值
		@DecimalMin(value = "0.01", message = "{stock.price.min}") // 最小數字限制
		BigDecimal price
		
) {}
