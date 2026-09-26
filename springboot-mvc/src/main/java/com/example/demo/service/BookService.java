package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Book;

public interface BookService {
	
	List<Book> findAllBooks();
	Book getBookById(Integer id);
	
	void addBook(Book book);
	
	void updateBook(Integer id, Book book);
	void updateBookName(Integer id, String bookName);
	void updateBookPrice(Integer id, Double bookPrice);
	void updateBookNameAndPrice(Integer id, String bookName, Double bookPrice);
	
	void deleteBook(Integer id);
	
	
}
