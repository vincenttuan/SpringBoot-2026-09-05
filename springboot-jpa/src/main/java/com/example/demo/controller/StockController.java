package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.stock.Stock;
import com.example.demo.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@GetMapping
	public String findAll(Model model) {
		List<Stock> stocks = stockService.findAll();
		
		// 指向: src/main/resources/templates/stock/list.html
		//       ┌資料夾 ┌html 檔名
		return "stock/list";
		
	}
	
	
}
