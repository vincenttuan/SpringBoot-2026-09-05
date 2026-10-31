package com.example.demo.stock;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.stock.Stock;
import com.example.demo.repository.StockRepository;

@SpringBootTest
public class TestReadStock {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Test
	public void read() {
		List<Stock> stocks = stockRepository.findAll();
		
		stocks.forEach(System.out::println);
		
	}
	
}
