package com.bookapp.main;

import java.util.List;

import java.util.*;

import com.bookapp.bean.Book;
import com.bookapp.bean.User;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.bookapp.exception.UserNotFound;
import com.bookapp.service.BookService;
import com.bookapp.service.BookServiceImpl;

public class Client {

	public static void main(String[] args) throws BookNotFoundException {

		Scanner scan = new Scanner(System.in);
		int choice;
		BookService bookservice = new BookServiceImpl();
		String username, password;
		try {

			while (true) {
				System.out.println("\n**********WELCOME TO ONLINE BOOK STORE**********");
				System.out.print("\nLogin as:\n1. Admin\n2. Existing User\n3. New User\n4. Exit\n==>");
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					System.out.print("\nUsername: ");
					username = scan.next();
					System.out.print("Password: ");
					password = scan.next();

					if (!username.equalsIgnoreCase("admin")) {
						System.out.println("\nInvalid Username!!!");
						break;
					} else if (!password.equalsIgnoreCase("admin")) {
						System.out.println("\nInvalid Password!!!");
						break;
					}

					while (true) {
						System.out.print(
								"\nSelect Your Choice:\n1. Add Book \n2. Delete Book\n3. Update Book\n4. Diaplay All Books \n5. Search Book by ID\n6. Search Book by Author\n7. Search Book by Category\n8. Exit\n==>");
						choice = scan.nextInt();

						switch (choice) {
						case 1:
							System.out.print("\nEnter Book Title: ");
							scan.nextLine();
							String title = scan.nextLine().toLowerCase();
							System.out.print("Enter Author Name: ");
							String author = scan.nextLine().toLowerCase();
							System.out.print("Enter Category: ");
							String category = scan.nextLine().toLowerCase();
							System.out.print("Enter BookID: ");
							int bookid = scan.nextInt();
							System.out.print("Enter Price: ");
							int price = scan.nextInt();

							Book book = new Book(title, author, category, bookid, price);

							bookservice.addBook(book);
							System.out.println("Book added to Database Successfully.");
							break;

						case 2:
							System.out.print("\nEnter the Bookid you want to Delete: ");
							bookid = scan.nextInt();
							try {
								if (bookservice.deleteBook(bookid))
									System.out.println("Deletion Failed!!!");
								else
									System.out.println("Deletion Success!!!");
							} catch (BookNotFoundException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

							break;

						case 3:
							System.out.print("\nEnter the Bookid: ");
							bookid = scan.nextInt();
							System.out.print("Enter New price: ");
							price = scan.nextInt();

							try {
								if (bookservice.updateBook(bookid, price))
									System.out.println("Updation Failed!!!");
								else
									System.out.println("Updation Success!!!");
							} catch (BookNotFoundException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

							break;

						case 4:
							List<Book> books = bookservice.getAllBooks();
							for (int i = 0; i < books.size(); i++)
								System.out.println(books.get(i));
							break;

						case 5:
							System.out.print("\nEnter BookID: ");

							book = null;
							try {
								book = bookservice.getBookById(scan.nextInt());
								System.out.println(book);
							} catch (BookNotFoundException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

							break;

						case 6:
							System.out.print("\nEnter Author Name: ");
							scan.nextLine();
							books = null;
							try {
								books = bookservice.getBookbyAuthor(scan.nextLine().toLowerCase());
								for (int i = 0; i < books.size(); i++)
									System.out.println(books.get(i));
							} catch (AuthorNotFoundException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

							break;

						case 7:
							System.out.print("\nEnter Category Name: ");
							scan.nextLine();
							books = null;
							try {
								books = bookservice.getBookbycategory(scan.nextLine().toLowerCase());

								for (int i = 0; i < books.size(); i++)
									System.out.println(books.get(i));
							} catch (CategoryNotFoundException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;

						case 8:
							System.out.println("\nTHANK YOU!!! VISIT AGAIN!!!");
							System.exit(0);
							break;

						default:
							System.out.println("Invalid Choice!!!");
						}
					}

				case 2:
					System.out.print("\nUsername: ");
					username = scan.next();
					System.out.print("Password: ");
					password = scan.next();
					User user = new User();

					try {
						if (user.login(username, password)) {
							while (true) {
								System.out.print(
										"\nSelect Your Search:\n1. Diaplay All Books \n2. Search Book by ID\n3. Search Book by Author\n4. Search Book by Category\n5. Exit\n==>");
								choice = scan.nextInt();

								switch (choice) {
								case 1:
									List<Book> books = bookservice.getAllBooks();

									for (int i = 0; i < books.size(); i++)
										System.out.println(books.get(i));
									break;

								case 2:
									System.out.print("\nEnter BookID: ");

									Book book = null;
									try {
										book = bookservice.getBookById(scan.nextInt());
										System.out.println(book);
									} catch (BookNotFoundException e) {
										System.out.println(e.getMessage());
									} catch (Exception e) {
										System.out.println(e.getMessage());
									}
									break;

								case 3:
									System.out.print("\nEnter Author Name: ");
									scan.nextLine();
									books = null;
									try {
										books = bookservice.getBookbyAuthor(scan.nextLine().toLowerCase());
										for (int i = 0; i < books.size(); i++)
											System.out.println(books.get(i));
									} catch (AuthorNotFoundException e) {
										System.out.println(e.getMessage());
									} catch (Exception e) {
										System.out.println(e.getMessage());
									}
									break;

								case 4:
									System.out.print("\nEnter Category Name: ");
									scan.nextLine();
									books = null;
									try {
										books = bookservice.getBookbycategory(scan.nextLine().toLowerCase());
										for (int i = 0; i < books.size(); i++)
											System.out.println(books.get(i));
									} catch (CategoryNotFoundException e) {
										System.out.println(e.getMessage());
									} catch (Exception e) {
										System.out.println(e.getMessage());
									}
									break;

								case 5:
									System.out.println("\nTHANK YOU!!! VISIT AGAIN!!!");
									System.exit(0);
									break;

								default:
									System.out.println("Invalid Choice!!!");
								}
							}
						} else {
							System.out.println("\nInvalid Username or Password!!!");
						}
					} catch (UserNotFound e) {
						System.out.println(e.getMessage());
					}
					break;

				case 3:
					System.out.print("\nEnter New Username: ");
					username = scan.next();
					System.out.print("Enter New Password: ");
					password = scan.next();
					User user1 = new User();
					user1.addUser(username, password);
					System.out.println("User registration Successfull.");
					break;

				case 4:
					System.out.println("\nTHANK YOU!!! VISIT AGAIN!!!");
					System.exit(0);
					break;

				default:
					System.out.println("\nInvalid Choice!!!");

				}
			}
		} catch (InputMismatchException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			scan.close();
		}
	}

}
