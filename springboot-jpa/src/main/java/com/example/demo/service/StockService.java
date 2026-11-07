package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StockForm;
import com.example.demo.model.stock.Stock;
import com.example.demo.repository.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> findAll() {
		List<Stock> stocks = stockRepository.findAll();
		return stocks;
	}
	
	@Transactional
	public void save(StockForm stockForm) {
		// StockForm 轉 Stock
		Stock stock = new Stock();
		stock.setSymbol(stockForm.symbol());
		stock.setName(stockForm.name());
		stock.setPrice(stockForm.price());
		
		// 存入 repository
		stockRepository.save(stock);
	}
	
	@Transactional
	public void update(Integer id, StockForm stockForm) {
		// 根據 id 到資料庫找出 stock
		Stock stock = stockRepository.findById(id).orElseThrow(() -> new NoSuchElementException("修改失敗: 查無資料"));	
		// stock 在此指的是資料庫紀錄
		// stockForm 在此指的是表單資料
		// 將 stockForm 的資料回寫給 stock
		stock.setSymbol(stockForm.symbol());
		stock.setName(stockForm.name());
		stock.setPrice(stockForm.price());
		
		// 回存, 但是因為此方法有 @Transactional 所以 save() 調用可以省略 
		//stockRepository.save(stock);
	}
	
	@Transactional
	public void delete(Integer id) {
		// 根據 id 到資料庫找出 stock
		stockRepository.findById(id).orElseThrow(() -> new NoSuchElementException("刪除失敗: 查無資料"));	
		
		stockRepository.deleteById(id);
	}
	
	public Stock getById(Integer id) {
		return stockRepository.findById(id).orElseThrow(() -> new NoSuchElementException("查無資料"));
	}
	
}
