package com.bookapp.main;

import java.util.List;
import java.util.*;

import com.bookapp.bean.Book;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.service.BookService;
import com.bookapp.service.BookServiceImpl;

public class Client {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		int choice;
		
		System.out.println("**********WELCOME TO ONLINEBOOKSTORE**********");
		System.out.println("\nLogin as:\n1. Admin\n2. User\n3. Exit==>");
		choice=scan.nextInt();
		
		while(true) {
			switch(choice) {
				case 1: System.out.println("Select Your Search:\n1. Add Book \n2. Delete Book\n3. Update Book\n4. Exit\n==>");
					choice=scan.nextInt();
					
					switch(choice) {
						case 1:
					}
					break;
					
				case 2:
					break;
					
				case 3: System.exit(0);
			}
		}
	
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
