package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.stock.Stock;
import com.example.demo.repository.StockRepository;

@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> findAll() {
		List<Stock> stocks = stockRepository.findAll();
		return stocks;
	}
	
}
