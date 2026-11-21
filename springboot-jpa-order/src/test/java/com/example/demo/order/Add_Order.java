package com.example.demo.order;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class Add_Order {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	@Transactional
	@Commit
	public void add() {
		// 取得商品
		Product usb = productRepository.findByName("USB線").get();
		Product hdmi = productRepository.findByName("HDMI線").get();
		
		// 建立細目
		OrderItem item1 = new OrderItem();
		item1.setProduct(usb); // 設定關聯(如此才有正確的 product_id)
		item1.setQuantity(1);
		item1.setUnitPrice(usb.getPrice());
		
		OrderItem item2 = new OrderItem();
		item2.setProduct(hdmi); // 設定關聯(如此才有正確的 product_id)
		item2.setQuantity(2);
		item2.setUnitPrice(hdmi.getPrice());
		
		// 建立訂單
		Order order = new Order();
		order.setStatus(OrderStatus.PENDING_PAYMENT);
		order.setItems(List.of(item1, item2));
		
		// 設定關聯(如此才有正確的 order_id)
		item1.setOrder(order);
		item2.setOrder(order);
		
		// 儲存(有設定聯集儲存)
		orderRepository.save(order);
		
		System.out.println("order & items add ok");
	}
	
	
}
