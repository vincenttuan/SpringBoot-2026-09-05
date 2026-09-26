package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

/**
 * BookServiceImpl 專門負責實現 "書籍服務介面" 的元件, 
 * 加入 @Service 表示 Spring 會自動建立該物件並管理
 */
@Service 
public class BookServiceImpl implements BookService {
	
	@Autowired
	@Qualifier("bookRepositoryInMemory") // 指定實現類
	//@Qualifier("bookRepositoryJdbc") // 指定實現類
	private BookRepository bookRepository;
	
	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAllBooks();
	}

	@Override
	public Book getBookById(Integer id) throws BookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBook(Book book) throws BookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(Integer id, Book book) throws BookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBookName(Integer id, String bookName) throws BookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBookPrice(Integer id, Double bookPrice) throws BookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBookNameAndPrice(Integer id, String bookName, Double bookPrice) throws BookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(Integer id) throws BookException {
		// TODO Auto-generated method stub
		
	}

}
