package com.bookapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.bookapp.bean.Book;
import com.bookapp.dao.*;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookServiceImpl implements BookService{
	
	BookDAO bookDAO=new BookDAOImpl();
	
	@Override
	public void addBook(Book book) {
		bookDAO.addBook(book);
	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		// TODO Auto-generated method stub
		if(!bookDAO.deleteBook(bookid))
			throw new BookNotFoundException();
		return false;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		// TODO Auto-generated method stub
		Book book=bookDAO.getBookById(bookid);
		
		if(book==null)
			throw new BookNotFoundException("Book Not Found!!!");
		
		return book;
	}

	@Override
	public boolean updateBook(int bookid, int price) throws BookNotFoundException {
		if(!bookDAO.updateBook(bookid,price))
			throw new BookNotFoundException();
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
	
		List<Book> booklist=bookDAO.getAllBooks()
				.stream()
				.sorted((book1,book2)->{
					return book1.getTitle().compareTo(book2.getTitle());
				})
				.collect(Collectors.toList());
		
		return booklist;
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException{
		List<Book> booklist=bookDAO.getBookbyAuthor(author)
				.stream()
				.sorted((book1,book2)->{
					return book1.getTitle().compareTo(book2.getTitle());
				})
				.collect(Collectors.toList());
		
		return booklist;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException{
		List<Book> booklist=bookDAO.getBookbycategory(category)
				.stream()
				.sorted((book1,book2)->{
					return book1.getTitle().compareTo(book2.getTitle());
				})
				.collect(Collectors.toList());
		
		return booklist;
	}


}
