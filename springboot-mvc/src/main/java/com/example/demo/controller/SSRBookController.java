package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@Controller
@RequestMapping("/ssr/book")
public class SSRBookController {
	
	@Autowired
	private BookService bookService;
	
	// 查詢所有書籍
	@GetMapping
	public String findAllBooks(Model model) {
		List<Book> books = bookService.findAllBooks();
		model.addAttribute("books", books);
		return "list";
	}
	
	@PostMapping
	public String addBook(Book book, Model model) {
		try {
			bookService.addBook(book);
		} catch (BookException e) {
			e.printStackTrace();
		}
		return "redirect:/ssr/book";
	}
	
	@GetMapping("/{id}")
	public String getBook(@PathVariable Integer id, Model model) {
		try {
			Book book = bookService.getBookById(id);
			model.addAttribute("book", book);
		} catch (BookException e) {
			e.printStackTrace();
		}
		return "edit";
	}
	
	
	
}
