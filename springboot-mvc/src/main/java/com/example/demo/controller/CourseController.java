package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Controller -> SSR 架構
 * @RestController -> CSR 架構
 * */
@Controller
@RequestMapping("/course")
public class CourseController {
	
	// 查詢所有課程
	@GetMapping
	public String findAll(Model model) {
		// 準備一組課程
		List<String> courses = List.of("Java", "Spring Boot", "Thymeleaf");
		
		// 將資料放到 model 中給 html 使用(這裡的 html 指的是 thymeleaf 網頁)
		model.addAttribute("name", "小明");
		model.addAttribute("age", 20);
		model.addAttribute("isStudent", true);
		model.addAttribute("courses", courses);
		
		// 回傳 html 
		return "course"; // Spring Boot 會自己尋找 course.html
	}
	
}
