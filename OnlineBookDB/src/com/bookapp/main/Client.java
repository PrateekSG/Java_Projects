package com.bookapp.main;

import java.util.List;
import java.util.*;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.bookapp.service.BookService;
import com.bookapp.service.BookServiceImpl;

public class Client {

	public static void main(String[] args) throws BookNotFoundException {

		Scanner scan = new Scanner(System.in);
		int choice;
		BookService bookservice = new BookServiceImpl();

		System.out.println("**********WELCOME TO ONLINE BOOK STORE**********");
		System.out.print("\nLogin as:\n1. Admin\n2. User\n3. Exit\n==>");
		choice = scan.nextInt();

		while (true) {
			switch (choice) {
			case 1:
				while (true) {
					System.out.print(
							"\nSelect Your Choice:\n1. Add Book \n2. Delete Book\n3. Update Book\n4. Diaplay All Books \n5. Search Book by ID\n6. Search Book by Author\n7. Search Book by Category\n8. Exit\n==>");
					choice = scan.nextInt();

					switch (choice) {
					case 1:
						System.out.print("Enter Book Title: ");
						String title = scan.next();
						System.out.print("Enter Author Name: ");
						String author = scan.next();
						System.out.print("Enter Category: ");
						String category = scan.next();
						System.out.print("Enter BookID: ");
						int bookid = scan.nextInt();
						System.out.print("Enter Price: ");
						int price = scan.nextInt();

						Book book = new Book(title, author, category, bookid, price);

						bookservice.addBook(book);
						System.out.println("Book added to Database Successfully.");
						break;

					case 2:
						System.out.print("Enter Bookid: ");
						bookid = scan.nextInt();
						try {
							if (bookservice.deleteBook(bookid))
								System.out.println("Deletion Failed!!!");
							else
								System.out.println("Deletion Success!!!");
						} catch (BookNotFoundException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}

						break;

					case 3:
						System.out.print("Enter Bookid: ");
						bookid = scan.nextInt();
						System.out.print("Enter price: ");
						price = scan.nextInt();

						try {
							if (bookservice.updateBook(bookid, price))
								System.out.println("Updation Failed!!!");
							else
								System.out.println("Updation Success!!!");
						} catch (BookNotFoundException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}

						break;

					case 4: 
						List<Book> books = bookservice.getAllBooks();
						System.out.println("Title\t\tAuthor\t\tCategory\tBookID\t\tPrice");
						System.out.println("----------------------------------------------------------------------------");
						for (int i = 0; i < books.size(); i++)
							System.out.println(books.get(i));
						break;

					case 5:
						System.out.print("Enter BookID: ");

						book = null;
						try {
							book = bookservice.getBookById(scan.nextInt());
							System.out.println("Title\t\tAuthor\t\tCategory\tBookID\t\tPrice");
							System.out.println("----------------------------------------------------------------------------");
							System.out.println(book);
						} catch (BookNotFoundException e) {
							System.out.println(e);
						}catch(Exception e) {
							System.out.println(e);
						}
						
						break;

					case 6:
						System.out.print("Enter Author Name: ");

						books = null;
						try {
							books = bookservice.getBookbyAuthor(scan.next());
						} catch (AuthorNotFoundException e) {
							System.out.println(e);
						}
						System.out.println("Title\t\tAuthor\t\tCategory\tBookID\t\tPrice");
						System.out.println("----------------------------------------------------------------------------");
						for (int i = 0; i < books.size(); i++)
							System.out.println(books.get(i));
						break;

					case 7:
						System.out.print("Enter Category Name: ");

						books = null;
						try {
							books = bookservice.getBookbycategory(scan.next());
						} catch (CategoryNotFoundException e) {
							System.out.println(e);
						}
						System.out.println("Title\t\tAuthor\t\tCategory\tBookID\t\tPrice");
						System.out.println("----------------------------------------------------------------------------");
						
						for (int i = 0; i < books.size(); i++)
							System.out.println(books.get(i));
						break;

					case 8:
						System.out.println("\nTHANK YOU!!! VISIT AGAIN!!!");
						System.exit(0);
						break;

					default:
						System.out.println("Invalid Choice!!!");
					}
				}
				// break;

			case 2:
				while (true) {
					System.out.print(
							"\nSelect Your Search:\n1. Diaplay All Books \n2. Search Book by ID\n3. Search Book by Author\n4. Search Book by Category\n5. Exit\n==>");
					choice = scan.nextInt();

					switch (choice) {
					case 1:
						List<Book> books = bookservice.getAllBooks();
						System.out.println("Title\t\tAuthor\t\tCategory\tBookID\t\tPrice");
						System.out.println("----------------------------------------------------------------------------");
						
						for (int i = 0; i < books.size(); i++)
							System.out.println(books.get(i));
						break;

					case 2:
						System.out.print("Enter BookID: ");

						Book book = null;
						try {
							book = bookservice.getBookById(scan.nextInt());
							System.out.println("Title\t\tAuthor\t\tCategory\tBookID\t\tPrice");
							System.out.println("----------------------------------------------------------------------------");
							System.out.println(book);
						} catch (BookNotFoundException e) {
							System.out.println(e);
						}
						
						break;

					case 3:
						System.out.print("Enter Author Name: ");

						books = null;
						try {
							books = bookservice.getBookbyAuthor(scan.next());
						} catch (AuthorNotFoundException e) {
							System.out.println(e);
						}
						System.out.println("Title\t\tAuthor\t\tCategory\tBookID\t\tPrice");
						System.out.println("----------------------------------------------------------------------------");
						for (int i = 0; i < books.size(); i++)
							System.out.println(books.get(i));
						break;

					case 4:
						System.out.print("Enter Category Name: ");

						books = null;
						try {
							books = bookservice.getBookbycategory(scan.next());
						} catch (CategoryNotFoundException e) {
							System.out.println(e);
						}
						System.out.println("Title\t\tAuthor\t\tCategory\tBookID\t\tPrice");
						System.out.println("----------------------------------------------------------------------------");
						
						for (int i = 0; i < books.size(); i++)
							System.out.println(books.get(i));
						break;

					case 5:
						System.out.println("\nTHANK YOU!!! VISIT AGAIN!!!");
						System.exit(0);
						break;

					default:
						System.out.println("Invalid Choice!!!");
					}
				}
				// break;

			case 3:
				System.exit(0);

			default:
				System.out.println("Invalid Choice!!!");
			}
		}
	}

}
