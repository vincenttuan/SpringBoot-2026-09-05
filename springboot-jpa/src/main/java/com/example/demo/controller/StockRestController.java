package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
