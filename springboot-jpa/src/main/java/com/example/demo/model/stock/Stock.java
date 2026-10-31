package com.example.demo.model.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Stock 指的是股票資料
 * 
 * 實際企業的資料表: FSK001 股票資料表
 * fsk_01: 序號
 * fsk_03: 股票代號
 * fsk_12: 股票名稱
 * ...
 * */

@Data
@Entity(name = "FSK001")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fsk_01")
	private Integer id; // 序號
	
	@Column(name = "fsk_03")
	private String symbol; // 股票代號
	
	@Column(name = "fsk_12")
	private String name; // 股票名稱
	
}
