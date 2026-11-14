package com.example.demo.exception;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	// 資料上傳錯誤
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<ApiResponse<Void>> handleNotValid(MethodArgumentNotValidException e) {
		// 收集所有的 defaultMessage
		List<String> errorMessages = e.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage)
				.toList();
		
		return errorMessages.size() == 0 ? 
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()))
				:
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(errorMessages.toString()));	
	}
	
	// 沒有資料上傳
	@ExceptionHandler(HttpMessageNotReadableException.class) 
	public ResponseEntity<ApiResponse<Void>> handleNotReadable(HttpMessageNotReadableException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(ApiResponse.error("無資料或資料不完整, 資料須符合 json 格式"));
	}
}
