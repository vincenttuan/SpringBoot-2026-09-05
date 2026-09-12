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
	
	//@Test
	void findById() {
		Integer id = 1;
		Optional<Book> optBook = bookRepository.getBookById(id);
		if(optBook.isEmpty()) {
			System.out.println("查無此書");
			return;
		}
		System.out.println(optBook.get());
	}
	
	//@Test
	void add() {
		Book book = new Book(null, "Java", 100.0, 120, true);
		boolean result = bookRepository.addBook(book);
		System.out.println("新增: " + result);
		
		findAll();
	}
	
	@Test
	void update() {
		Integer id = 1;
		Optional<Book> optBook = bookRepository.getBookById(id);
		if(optBook.isEmpty()) {
			System.out.println("查無此書");
			return;
		}
		
		System.out.println("修改前");
		findAll();
		
		System.out.println();
		Book originalBook = optBook.get();
		// 修改必要的資料
		originalBook.setAmount(77);
		originalBook.setPrice(6.5);
		
		System.out.println("修改後");
		findAll();
	}
	
	
}
