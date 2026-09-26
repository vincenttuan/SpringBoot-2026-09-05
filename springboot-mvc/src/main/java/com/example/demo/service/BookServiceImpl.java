package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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
	//@Qualifier("bookRepositoryInMemory") // 指定實現類
	@Qualifier("bookRepositoryJdbc") // 指定實現類
	private BookRepository bookRepository;
	
	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAllBooks();
	}

	@Override
	public Book getBookById(Integer id) throws BookException {
		Optional<Book> optBook = bookRepository.getBookById(id);
		
		if(optBook.isEmpty()) {
			throw new BookException("查無此書: id=%d".formatted(id));
		}
		
		return optBook.get();
	}

	@Override
	public void addBook(Book book) throws BookException {
		if(!bookRepository.addBook(book)) {
			throw new BookException("書籍新增失敗: %s".formatted(book));
		}
	}

	@Override
	public void updateBook(Integer id, Book book) throws BookException {
		if(!bookRepository.updateBook(id, book)) {
			throw new BookException("書籍修改失敗: id=%d %s".formatted(id, book));
		}
		
	}

	@Override
	public void updateBookName(Integer id, String bookName) throws BookException {
		// 先透過 id 取得要修改的書籍
		Book uptBook = getBookById(id);
		// 將要修改的名字設定進去
		uptBook.setName(bookName);
		// 將 id 與 uptBook 送入修改方法
		updateBook(id, uptBook);
	}

	@Override
	public void updateBookPrice(Integer id, Double bookPrice) throws BookException {
		// 先透過 id 取得要修改的書籍
		Book uptBook = getBookById(id);
		// 將要修改的價格設定進去
		uptBook.setPrice(bookPrice);
		// 將 id 與 uptBook 送入修改方法
		updateBook(id, uptBook);
	}

	@Override
	public void updateBookNameAndPrice(Integer id, String bookName, Double bookPrice) throws BookException {
		// 先透過 id 取得要修改的書籍
		Book uptBook = getBookById(id);
		// 將要修改的名字設定進去
		uptBook.setName(bookName);
		// 將要修改的價格設定進去
		uptBook.setPrice(bookPrice);
		// 將 id 與 uptBook 送入修改方法
		updateBook(id, uptBook);
	}

	@Override
	public void deleteBook(Integer id) throws BookException {
		if(!bookRepository.deleteBookById(id)) {
			throw new BookException("刪除失敗: id=%d".formatted(id));
		}
	}

}
