package com.example.demo.stock;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.stock.Stock;
import com.example.demo.repository.StockRepository;

@SpringBootTest
public class Test_DeleteStock {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Test
	public void delete() {
		// 找到要刪除的物件資料, 可以用 id or symbol
		Optional<Stock> optStock = stockRepository.findById(2);
		//Optional<Stock> optStock = stockRepository.findBySymbol("1101");
		
		if(optStock.isEmpty()) {
			System.out.println("查無資料");
			return;
		}
		
		stockRepository.delete(optStock.get());
		System.out.println("刪除成功");
		
	}
	
}
