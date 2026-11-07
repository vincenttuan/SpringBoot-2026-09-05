package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.StockForm;
import com.example.demo.model.stock.Stock;
import com.example.demo.service.StockService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@GetMapping
	public String findAll(Model model) {
		List<Stock> stocks = stockService.findAll();
		model.addAttribute("stocks", stocks);
		// 指向: src/main/resources/templates/stock/list.html
		//       ┌資料夾 ┌html 檔名
		return "stock/list";
	}
	
	@GetMapping("/new")
	public String newForm(Model model) {
		
		StockForm stockForm = new StockForm("", "", BigDecimal.ZERO);
		model.addAttribute("stockForm", stockForm);
		
		return "stock/form";
	}
	
	@PostMapping
	public String addStock(StockForm stockForm) {
		stockService.save(stockForm);
		
		return "redirect:/stock";
	}
	
	@DeleteMapping("/{id}")
	public String deleteStock(@PathVariable Integer id) {
		stockService.delete(id);
		
		return "redirect:/stock";
	}
	
	@GetMapping("/{id}")
	public String editForm(@PathVariable Integer id, Model model) {
		Stock stock = stockService.getById(id);
		StockForm stockForm = new StockForm(stock.getSymbol(), stock.getName(), stock.getPrice());
		
		model.addAttribute("id", id);
		model.addAttribute("stockForm", stockForm);
		
		return "stock/form";
	}
	
	@PutMapping("/{id}")
	public String updateStock(@PathVariable Integer id, StockForm stockForm) {
		stockService.update(id, stockForm);
		
		return "redirect:/stock";
	}
	
}
