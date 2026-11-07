package com.example.demo.dto;

import java.math.BigDecimal;

// Java 16 開始可以利用 record 來取代一些 Lombok 的功能
public record StockForm(String symbol, String name, BigDecimal price) {}
