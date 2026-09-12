package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BMI;
import com.example.demo.model.Book;
import com.example.demo.response.ApiResponse;

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
	@GetMapping("/bmi")
	public String bmi(@RequestParam Double h, @RequestParam Double w) {
		Double bmi = w / Math.pow(h/100, 2);
		String diagnosis = (bmi <= 18) ? "過輕" : (bmi > 23) ? "過重" : "正常";
		String result = "身高: %.1f cm 體重: %.1f kg BMI: %.2f (%s)".formatted(h, w, bmi, diagnosis);
		return result;
	}
	
	/**
	 * 5. 同名多筆資料
	 * 路徑: /average/ages?age=17&age=21&age=20
	 * 印出年齡與平均
	 * */
	@GetMapping("/average/ages")
	public String averageOfAge(@RequestParam(name = "age") List<Integer> ages) {
		
		double avg = ages.stream()
						 //.mapToInt(age -> Integer.valueOf(age)) // Integer 轉 int
						 .mapToInt(Integer::valueOf) // Integer 轉 int
						 .average()
						 .orElse(0);
		
		String result = "年齡:%s 平均:%.1f".formatted(ages, avg);
		return result;
	}
	
	/**
	 * 6. Lab 練習: 得到多筆 score 資料
	 * 路徑: "/average/scores?score=80&score=100&score=50&score=70&score=30"
	 * 印出分數與平均, 總分, 最高分, 最低分
	 * */
	@GetMapping("/average/scores")
	public String averageOfScore(@RequestParam(name = "score") List<Integer> scores) {
		double avg = scores.stream().mapToInt(Integer::valueOf).average().orElse(0);
		int    sum = scores.stream().mapToInt(Integer::valueOf).sum();
		int    max = scores.stream().mapToInt(Integer::valueOf).max().orElse(0);
		int    min = scores.stream().mapToInt(Integer::valueOf).min().orElse(0);
		
		String result = "分數:%s 平均:%.1f 總分:%d 最高分:%d 最低分:%d".formatted(scores, avg, sum, max, min);
		return result; 
	}
	
	/**
	 * 7. 回傳 json 結構
	 * 路徑: /json/bmi?h=170&w=60
	 * 網址: http://localhost:8080/api/json/bmi?h=170&w=60
	 * 判斷: bmi <= 18 顯示過輕, bmi > 23 顯示過重
	 * 執行結果: 
	 * {
	 * 	"message": "BMI 執行結果",
	 *  "data": {
	 *  	"height": 170.0,
	 *  	"weight": 60.0,
	 *  	"bmi": 20.76
	 *  }
	 * } 
	 * 
	 * */
	@GetMapping(value = "/json/bmi", produces = "application/json;charset=utf8")
	public String calcBmi(@RequestParam(required = false) Double h, @RequestParam(required = false) Double w) {
		double bmiValue = w / Math.pow(h/100, 2);
		
		return """
				{
				 	"message": "BMI 執行結果",
					"data": {
				   		"height": %.1f,
				   		"weight": %.1f,
				   		"bmi": %.2f
				  	}
				}
				""".formatted(h, w, bmiValue);
		
	}
	
	@GetMapping(value = "/json/bmi2", produces = "application/json;charset=utf8")
	public BMI calcBmi2(@RequestParam(required = false) Double h, @RequestParam(required = false) Double w) {
		double bmiValue = w / Math.pow(h/100, 2);
		
		BMI bmi = new BMI(h, w, bmiValue);
		
		return bmi;
		
	}
	
	@GetMapping(value = "/json/bmi3", produces = "application/json;charset=utf8")
	public ApiResponse<BMI> calcBmi3(@RequestParam(required = false) Double h, @RequestParam(required = false) Double w) {
		// 參數個數檢查
		if(h == null || w == null) {
			return ApiResponse.error("缺少了身高或體重的參數");
		}
		
		// 參數內容檢查
		if(h <= 0 || w <= 0) {
			return ApiResponse.error("身高或體重的參數內容錯誤, 必須皆 > 0");
		}
		
		// 執行計算
		double bmiValue = w / Math.pow(h/100, 2);
		BMI bmi = new BMI(h, w, bmiValue);
		
		return ApiResponse.success("BMI 計算結果", bmi);
		
	}
	
	@GetMapping(value = "/json/bmi4", produces = "application/json;charset=utf8")
	public ResponseEntity<ApiResponse<BMI>> calcBmi4(@RequestParam(required = false) Double h, @RequestParam(required = false) Double w) {
		// 參數個數檢查
		if(h == null || w == null) {
			// bad request => HTTP 400
			return ResponseEntity.badRequest().body(ApiResponse.error("缺少了身高或體重的參數"));
		}
		
		// 參數內容檢查
		if(h <= 0 || w <= 0) {
			// bad request => HTTP 400
			return ResponseEntity.badRequest().body(ApiResponse.error("身高或體重的參數內容錯誤, 必須皆 > 0"));
		}
		
		// 執行計算
		double bmiValue = w / Math.pow(h/100, 2);
		BMI bmi = new BMI(h, w, bmiValue);
		// ok => HTTP 200
		return ResponseEntity.ok(ApiResponse.success("BMI 計算結果", bmi));
		
	}
	
	/**
	 * 8. 多筆參數轉 Map
	 * name 書名(String), price 價格(Double), amount 數量(Integer), pub 出刊/停刊(Boolean)
	 * 路徑: /json/book?name=Math&price=12.5&amount=10&pub=true
	 * 路徑: /json/book?name=English&price=10.5&amount=20&pub=false
	 * 讓參數自動轉成 key/value 的 Map 集合
	 * */
	@GetMapping(value = "/json/book", produces = "application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Object>> getBookInfo(@RequestParam Map<String, Object> bookMap) {
		System.out.printf("bookMap = %s%n", bookMap);
		return ResponseEntity.ok(ApiResponse.success("書籍資料", bookMap));
	}
	
	/**
	 * 9. 多筆參數轉 model
	 * name 書名(String), price 價格(Double), amount 數量(Integer), pub 出刊/停刊(Boolean)
	 * 路徑: /json/book2?name=Math&price=12.5&amount=10&pub=true
	 * 路徑: /json/book2?name=English&price=10.5&amount=20&pub=false
	 * 讓參數自動轉成指定 model 
	 * */
	@GetMapping(value = "/json/book2", produces = "application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Book>> getBookInfo2(Book book) {
		System.out.printf("book = %s%n", book);
		return ResponseEntity.ok(ApiResponse.success("書籍資料", book));
	}
	
}
