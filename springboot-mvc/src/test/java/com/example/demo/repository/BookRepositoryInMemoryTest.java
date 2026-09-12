package com.example.demo.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Book;

@SpringBootTest
public class BookRepositoryInMemoryTest {
	
	/* 
	 * @Autowired 自動綁定
	 * 會自動找到實現 BookRepository 介面的實現類
	 * 相當於 BookRepository bookRepository = new BookRepositoryInMemory();
	*/
	@Autowired
	@Qualifier("bookRepositoryInMemory") // 指定實現類
	//@Qualifier("bookRepositoryJdbc") // 指定實現類
	private BookRepository bookRepository;
	
	//@Test
	void findAll() {
		bookRepository.findAllBooks().forEach(System.out::println);
	}
	
	@Test
	void findById() {
		Integer id = 1;
		Optional<Book> optBook = bookRepository.getBookById(id);
		if(optBook.isEmpty()) {
			System.out.println("查無此書");
			return;
		}
		System.out.println(optBook.get());
	}
	
	
}
