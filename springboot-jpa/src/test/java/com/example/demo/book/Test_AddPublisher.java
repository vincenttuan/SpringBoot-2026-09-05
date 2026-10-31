package com.example.demo.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.book.Publisher;
import com.example.demo.repository.PublisherRepository;

@SpringBootTest
public class Test_AddPublisher {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Test
	public void add() {
		// 新增出版社
		Publisher pub1 = new Publisher();
		pub1.setName("第三波");
		
		Publisher pub2 = new Publisher();
		pub2.setName("華納");
		
		Publisher pub3 = new Publisher();
		pub3.setName("天下");
		
		publisherRepository.save(pub1);
		publisherRepository.save(pub2);
		publisherRepository.save(pub3);
		
		System.out.println("新增出版社(Publisher)成功");
	}
	
}
