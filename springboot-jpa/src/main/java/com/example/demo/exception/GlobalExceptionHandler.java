package com.example.demo.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// 查無資料會拋出 => java.util.NoSuchElementException: 查無資料
	// 例如: 查詢不存在的股票 id
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotFound(NoSuchElementException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(ApiResponse.error(e.getMessage()));
	}
	
}
