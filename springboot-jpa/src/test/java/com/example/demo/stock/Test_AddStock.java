package com.example.demo.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.stock.Stock;
import com.example.demo.repository.StockRepository;

@SpringBootTest
public class Test_AddStock {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Test
	public void add() {
		Stock stock1 = new Stock();
		stock1.setSymbol("2330");
		stock1.setName("台積電");
		
		Stock stock2 = new Stock();
		stock2.setSymbol("1101");
		stock2.setName("台泥");
		
		Stock stock3 = new Stock();
		stock3.setSymbol("1234");
		stock3.setName("黑松");
		
		stockRepository.save(stock1);
		stockRepository.save(stock2);
		stockRepository.save(stock3);
		
		System.out.println("股票資料新稱成功");
		
	}
	
	
}
