package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 宣告 ApiController 是一個 Controller, Rest 是表示支援的路徑風格
@RequestMapping("/api") // 資源分組, 相當於 @WebServlet("/api")
public class ApiController {
	
}
