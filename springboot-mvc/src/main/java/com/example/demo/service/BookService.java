package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Book;

public interface BookService {
	
	List<Book> findAllBooks();
	Book getBookById(Integer id) throws BookException;
	
	void addBook(Book book) throws BookException;
	
	void updateBook(Integer id, Book book) throws BookException;
	void updateBookName(Integer id, String bookName) throws BookException;
	void updateBookPrice(Integer id, Double bookPrice) throws BookException;
	void updateBookNameAndPrice(Integer id, String bookName, Double bookPrice) throws BookException;
	
	void deleteBook(Integer id) throws BookException;
	
	
}
