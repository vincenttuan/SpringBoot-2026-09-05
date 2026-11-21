package com.example.demo.model;

import java.beans.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 訂單明細檔
 * */

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(comment = "明細編號")
	private Integer id;
	
	@Column(comment = "購買數量", nullable = false)
	private Integer quantity;
	
	@Column(comment = "單位價格（元）", name = "unit_price", nullable = false)
	private Integer unitPrice;
	
	
	// 計算小計
	@Transient // 此欄位或方法只是暫時性的, 不要保存到資料表裡
	public Integer getSubTotal() {
		return unitPrice * quantity; // 小計 = 單位價格 * 數量
	}
	
}
