package com.example.demo.book;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.book.Publisher;
import com.example.demo.repository.PublisherRepository;

@SpringBootTest
public class Test_UpdatePublisher {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Test
	public void update() {
		
		Optional<Publisher> optPublisher = publisherRepository.findById(1);
		
		if(optPublisher.isEmpty()) {
			System.out.println("查無資料");
			return;
		}
		
		Publisher publisher = optPublisher.get();
		publisher.setName("第四波");
		
		publisherRepository.save(publisher);
		
		System.out.println("修改完畢");
		
	}
}
