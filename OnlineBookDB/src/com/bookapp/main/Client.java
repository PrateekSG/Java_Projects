package com.bookapp.main;

import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.service.BookService;
import com.bookapp.service.BookServiceImpl;

public class Client {

	public static void main(String[] args) {
		Book b1=new Book("Gate 2018","Amitabh","Academy",101,800);
		BookService bs=new BookServiceImpl();
		
		//bs.addBook(b1);
		
		try {
			if(bs.deleteBook(102))
				System.out.println("Deletion Failed!!!");
			else
				System.out.println("Deletion Success!!!");
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Book> books=bs.getAllBooks();
		System.out.println(books);
	}

}
