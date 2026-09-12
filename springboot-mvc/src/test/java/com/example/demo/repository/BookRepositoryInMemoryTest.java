package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookRepositoryInMemoryTest {
	
	/* 
	 * @Autowired 自動綁定
	 * 會自動找到實現 BookRepository 介面的實現類
	 * 相當於 BookRepository bookRepository = new BookRepositoryInMemory();
	*/
	@Autowired
	private BookRepository bookRepository;
	
	
}
