package com.example.demo.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.model.stock.Stock;
import com.example.demo.repository.StockRepository;

@SpringBootTest
public class Test_ReadStockPage {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Test
	public void readPage() {
		// Pageable 查詢分頁規格 (interface)
		// PageRequest 查詢分頁實現 (class)
		// 方法定義: PageRequest.of(int pageNumber, int pageSize, Sort sort) 
		//                              ^             ^
		//                            offset         limit
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Stock::getId));
		
	}
	
}
