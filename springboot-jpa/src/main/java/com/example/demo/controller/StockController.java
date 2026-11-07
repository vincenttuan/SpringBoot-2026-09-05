package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.StockForm;
import com.example.demo.model.stock.Stock;
import com.example.demo.service.StockService;

import jakarta.validation.Valid;

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
	
	/*
	 * URL:
	 * /stock
	 * /stock?sort=price&dir=asc
	 * /stock?sort=price&dir=desc
	 * ...
	 * 
	 * sort => 排序欄位
	 * dir  => 排序方向(desc, asc)
	 * */
	@GetMapping
	public String findAll(Model model, @RequestParam(required = false) String sort, @RequestParam(required = false) String dir) {
		List<Stock> stocks = null;
		
		if(sort != null && dir != null) {
			// desc 大到小, asc 小到大
			Sort.Direction direction = dir.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
			stocks = stockService.findAll(Sort.by(direction, sort));
		} else {
			stocks = stockService.findAll();
		}
		
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
	public String addStock(@Valid StockForm stockForm, BindingResult result) {
		// 如果驗證失敗
		if(result.hasErrors()) {
			// 返回原表單並顯示錯誤訊息
			return "stock/form";
		}
		
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
	public String updateStock(@PathVariable Integer id, @Valid StockForm stockForm, BindingResult result) {
		
		if(result.hasErrors()) {
			return "stock/form";
		}
		
		stockService.update(id, stockForm);
		
		return "redirect:/stock";
	}
	
}
