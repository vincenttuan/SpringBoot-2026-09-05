package com.example.demo.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**
	 * 2. QueryString (? 網址帶參數)
	 * 路徑: /hello?name=John
	 * 路徑: /hello?name=John&age=20
	 * 路徑: /hello?name=Mary
	 * 
	 * 限制: name 為必要參數, age 為可選參數(初始值=18)
	 * */
	//@GetMapping(value = {"/hello"})
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", required = true) String username,
						@RequestParam(value = "age", required = false, defaultValue = "18") Integer userage) {
		
		String result = "Hello %s 年齡: %d".formatted(username, userage);
		
		return result;
	}
	
	/**
	 * 3. 上述 2 的精簡配置寫法
	 * 方法參數與請求參數同名
	 * 路徑: /hi?name=John
	 * 路徑: /hi?name=John&age=20
	 * 路徑: /hi?name=Mary
	 * */
	@GetMapping("/hi")
	public String hi(@RequestParam String name,
					 @RequestParam(required = false, defaultValue = "18") Integer age) {
		
		String result = "Hi %s 年齡: %d".formatted(name, age);
		
		return result;
	}
	
	/** 
	 * 4. Lab 練習 I
	 * 路徑: /bmi?h=170&w=60
	 * 網址: http://localhost:8080/api/bmi?h=170&w=60
	 * 判斷: bmi <= 18 顯示過輕, bmi > 23 顯示過重
	 * 執行結果: 身高:170cm 體重:60kg bmi=20.76(正常)
	*/
	public String bmi(@RequestParam Double h, @RequestParam Double w) {
		Double bmi = w / Math.pow(h/100, 2);
		String diagnosis = (bmi <= 18) ? "過輕" : (bmi > 23) ? "過重" : "正常";
		String result = "身高: %.1f cm 體重: %.1f kg BMI: %.2f (%s)".formatted(h, w, bmi, diagnosis);
		return result;
	}
	
	
	
	
}
