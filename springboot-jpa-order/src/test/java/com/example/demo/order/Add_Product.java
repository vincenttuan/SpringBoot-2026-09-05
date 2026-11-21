package com.example.demo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class Add_Product {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@Transactional
	@Commit
	public void add() {
		// 新增 2 個商品
		// 1. USB線 100 元
		// 2. HDMI線 200 元
		
		Product product1 = new Product();
		product1.setName("USB線");
		product1.setPrice(100);
		
		Product product2 = new Product();
		product2.setName("HDMI線");
		product2.setPrice(200);
		
		productRepository.save(product1);
		productRepository.save(product2);
		
		System.out.println("product add ok !");
		
	}
	
}
