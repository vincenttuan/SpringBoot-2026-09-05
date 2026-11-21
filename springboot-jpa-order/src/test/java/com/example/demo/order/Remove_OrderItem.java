package com.example.demo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class Remove_OrderItem {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	@Transactional
	@Commit
	public void remove() {
		// 移除 Order id=1 的訂單下的 OrderItem id=1 的這一筆紀錄
		Integer orderId = 1;
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("查無訂單"));
		
		Integer itemId = 1;
		OrderItem orderItem = order.getItems()
									.stream()
									.filter(item -> item.getId().equals(itemId))
									.findFirst()
									.orElseThrow(() -> new RuntimeException("查無訂單細目"));
		
		order.removeItem(orderItem);
		
		// 回存
		//orderRepository.save(order); // 此行可以不寫,因為方法上有加上 @Transactional
		
		System.out.println("orderItem remove ok");
		System.out.println("end 方法結束");
	}
	
}
