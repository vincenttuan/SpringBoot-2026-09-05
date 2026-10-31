package com.example.demo.stock;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.stock.Stock;
import com.example.demo.repository.StockRepository;

@SpringBootTest
public class Test_UpdateStock {
	
	@Autowired
	private StockRepository stockRepository;
	
	/**
	 * 找到 "台泥" 並把名稱改為 "台灣水泥"
	 * */
	@Test
	public void update() {
		// 根據 id 找到 stock 
		//Integer id = 2; // 已知條件
		//Optional<Stock> optStock = stockRepository.findById(id);
		
		// 根據 symbol 找到 stock
		String symbol = "1101";
		Optional<Stock> optStock = stockRepository.findBySymbol(symbol);
		
		if(optStock.isEmpty()) {
			System.out.println("查無資料");
			return;
		}
		
		Stock stock = optStock.get();
		stock.setName("台泥");
		
		stockRepository.save(stock);
		
		System.out.println("股票名稱修改完成");
	}
	
}
