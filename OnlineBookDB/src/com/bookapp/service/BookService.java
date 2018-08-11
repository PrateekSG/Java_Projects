package com.bookapp.service;

import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public interface BookService {
	
		//called by admin
		void addBook(Book book);
		boolean deleteBook(int bookid) throws BookNotFoundException;
		Book getBookById(int bookid) throws BookNotFoundException;
		boolean updateBook(int bookid,int price) throws BookNotFoundException;
		
		// called by customer
		List<Book> getAllBooks();
		List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException;;
		List<Book> getBookbycategory(String category)throws CategoryNotFoundException;;
	
	

}
