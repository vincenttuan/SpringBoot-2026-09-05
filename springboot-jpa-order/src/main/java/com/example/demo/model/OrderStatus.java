package com.example.demo.model;

/**
 * 訂單狀態
 * */
public enum OrderStatus {
	
	PENDING_PAYMENT, // 待付款, 在此狀態中可以修改訂單細目, 不可開立發票
	PAID,            // 已付款, 在此狀態中不可以修改訂單細目, 可開立發票
	CANCELLED,       // 已取消, 保留訂單(但不可修改細目)與保留已開立的發票(若有) 
}
