package com.example.demo.book;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.book.Publisher;
import com.example.demo.repository.PublisherRepository;

@SpringBootTest
public class Test_ReadPublisher {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Test
	public void read() {
		// 讀取出版社資料
		List<Publisher> publishers = publisherRepository.findAll();
		System.out.printf("出版社筆數: %d%n", publishers.size());
		publishers.forEach(pub -> {
			System.out.printf("id: %d name:%s%n", pub.getId(), pub.getName());
		});
		
	}
	
}
