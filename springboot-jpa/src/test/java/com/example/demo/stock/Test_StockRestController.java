package com.example.demo.stock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.StockRestController;
import com.example.demo.service.StockService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(StockRestController.class)
public class Test_StockRestController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private StockService stockService;
	
	// 1. 測試 GET /rest/stock 分頁查詢
	@Test
	void testFindPage() throws Exception {
		
		mockMvc.perform(get("/rest/stock")
				.param("page", "1")
				.param("size", "10")
				.param("sort", "id")
				.param("dir", "asc"))
				.andDo(print())
				.andExpect(status().isOk());
		
	}
	
}
