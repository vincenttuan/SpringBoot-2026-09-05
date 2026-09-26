package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Book;

@SpringBootTest
public class BookRepositoryJdbcTest {
	
	@Autowired
	@Qualifier("bookRepositoryJdbc") // 指定實現類
	private BookRepository bookRepository;
	
	@Test
	public void add() {
		Book book1 = new Book(0, "小叮噹", 12.5, 20, true);
		Book book2 = new Book(0, "老夫子", 10.5, 30, true);
		Book book3 = new Book(0, "好小子", 13.5, 40, true);
		Book book4 = new Book(0, "小甜甜", 14.5, 10, false);
		
		bookRepository.addBook(book1);
		bookRepository.addBook(book2);
		bookRepository.addBook(book3);
		bookRepository.addBook(book4);
		
	}
	
}
