package com.example.demo.stock;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
		//                             頁數(0表示第1頁)  每頁筆數
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Stock::getId)); // 預設小 -> 大排序
		//Pageable pageable = PageRequest.of(0, 10, Sort.by(Stock::getId).ascending()); // 小 -> 大排序
		//Pageable pageable = PageRequest.of(0, 10, Sort.by(Stock::getId).descending()); // 大 -> 小排序
		
		// Page<Stock> 會根據 pageable 的定義來取得本頁實際資料
		Page<Stock> stockPage = stockRepository.findAll(pageable);
		
		// getContent() 取得這一頁的內容資料
		// 將 Page 的內容資料轉 List
		List<Stock> stocks = stockPage.getContent();
		
		// 顯示與分析
		stocks.forEach(System.out::println);
		
		
		
		
		
	}
	
}
