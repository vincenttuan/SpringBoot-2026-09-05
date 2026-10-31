package com.example.demo.stock;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.demo.model.stock.Stock;
import com.example.demo.repository.StockRepository;

@SpringBootTest
public class Test_ReadStock {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Test
	public void read() {
		//List<Stock> stocks = stockRepository.findAll();
		//List<Stock> stocks = stockRepository.findAll(Sort.by("symbol").ascending());
		//List<Stock> stocks = stockRepository.findAll_useTSQL();
		List<Stock> stocks = stockRepository.findAll_usePSQL();
		
		stocks.forEach(System.out::println);
		
	}
	
}
