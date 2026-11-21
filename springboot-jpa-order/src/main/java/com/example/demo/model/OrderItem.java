package com.example.demo.model;

import java.beans.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	// -- 關聯配置 --------------------------------------------------------
	@ManyToOne(optional = false) // OrderItem 不可以沒有 Order
	@JoinColumn(name = "order_id", comment = "訂單編號")
	private Order order;
	
	
	// 計算小計
	@Transient // 此欄位或getXXX方法只是暫時性的, 不要保存資料表或到資料表去尋找該欄位
	public Integer getSubTotal() {
		return unitPrice * quantity; // 小計 = 單位價格 * 數量
	}
	
}
