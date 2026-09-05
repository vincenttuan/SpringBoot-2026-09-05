package com.example.demo.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 宣告 ApiController 是一個 Controller, Rest 是表示支援的路徑風格
@RequestMapping("/api") // 資源分組, 相當於 @WebServlet("/api")
public class ApiController {
	
	/**
	 * 1. Welcome
	 * 路徑: /welcome, /home
	 * 
	 * 網址: http://localhost:8080/api/welcome
	 * 網址: http://localhost:8080/api/home
	 * */
	
	@GetMapping(value = {"/welcome", "/home"}, produces = "text/plain;charset=UTF-8")
	public String welcome() {
		return "Welcome 歡迎光臨 !" + new Date();
	}
	
	
	
}
