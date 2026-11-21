package com.example.demo.order;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class Read_Product {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Test
	public void read() {
		// 查找全部
		List<Product> staffs = productRepository.findAll();
		staffs.forEach(System.out::println);
		
		// 根據 name 查找
		Optional<Product> optProduct = productRepository.findByName("USE線");
		if(optProduct.isPresent()) {
			System.out.print("找到了 => ");
			Product product = optProduct.get();
			System.out.println(product);
		} else {
			System.out.println("查無此商品");
		}
		
		
	}
	
}
