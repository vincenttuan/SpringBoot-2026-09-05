package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.stock.Stock;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.StockService;

/**
 * 查詢全部股票
 * GET /rest/stock?page=1&size=10&sort=id&dir=asc
 * 查詢單一股票
 * GET /rest/stock/1
 * 新增股票
 * POST /rest/stock
 * 修改股票
 * PUT /rest/stock/1
 * 刪除股票
 * DELETE /rest/stock/1
 * */

@RestController
@RequestMapping("/rest/stock")
public class StockRestController {
	
	@Autowired
	private StockService stockService;
	
	/*
	 * 查詢全部股票
	 * GET /rest/stock?page=1&size=10&sort=id&dir=asc
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<Page<Stock>>> findPage(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "id") String sort,
			@RequestParam(required = false, defaultValue = "asc") String dir){
		
		Sort.Direction direction = dir.equals("asc") ? Sort.Direction.ASC :Sort.Direction.DESC;
		Sort stockSort = Sort.by(direction, sort);
		
		Page<Stock> stockPage = stockService.findPage(page, size, stockSort);
		
		return ResponseEntity.ok(ApiResponse.success("查詢成功", stockPage));
		
	}
	
	/* 
	 * 查詢單一股票
	 * GET /rest/stock/1
	 */
	
	
	
	/*
	 * 新增股票
	 * POST /rest/stock
	 */
	
	
	
	/*
	 * 修改股票
	 * PUT /rest/stock/1
	 */
	
	
	
	/*
	 * 刪除股票
	 * DELETE /rest/stock/1
	 */
}
