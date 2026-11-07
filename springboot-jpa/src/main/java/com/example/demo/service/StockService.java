package com.example.demo.service;

import java.util.List;

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
	
}
