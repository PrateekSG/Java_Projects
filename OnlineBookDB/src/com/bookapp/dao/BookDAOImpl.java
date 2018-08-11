package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookDAOImpl implements BookDAO{

	@Override
	public void addBook(Book book) {
		String sql="insert into book values (?,?,?,?,?)";
		
		try(Connection connection = ModelDAO.openConnection();				//try-with-resource
			PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setString(1, book.getTitle());
			statement.setString(2,book.getAuthor());
			statement.setString(3,book.getCategory());
			statement.setInt(4,book.getBookid());
			statement.setInt(5,book.getPrice());
			statement.execute();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
	
		String sql="delete from book where bookid=?";
		
		boolean flag=true;
		try(Connection connection = ModelDAO.openConnection();				//try-with-resource
			PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1,bookid);
			int value=statement.executeUpdate();
					
			if(value==0)
				flag=false;
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return flag;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		
		String sql="select * from book where bookid=?";
		Book book=null;
		
		try(Connection connection = ModelDAO.openConnection();				//try-with-resource
			PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1,bookid);
			ResultSet resultset=statement.executeQuery();
			book=new Book(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getInt(4),resultset.getInt(5));
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return book;	
	}

	@Override
	public boolean updateBook(int bookid, int price) throws BookNotFoundException {
		
		String sql="update book set price=? where bookid=?";
		
		boolean flag=true;
		
		try(Connection connection = ModelDAO.openConnection();				//try-with-resource
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1,price);
			statement.setInt(2,bookid);
			int value=statement.executeUpdate();
			
			if(value==0)
				flag=false;
			
		}catch(SQLException e) {
			System.out.println(e);
		}
	
		return flag;
	}

	@Override
	public List<Book> getAllBooks() {
		
		String sql="select * from book";
		
		Connection connection = ModelDAO.openConnection();		
		PreparedStatement statement = null;
		List<Book> booklist=null;
		try{
			statement = connection.prepareStatement(sql);
			ResultSet resultset=statement.executeQuery();
			booklist=new ArrayList<>();
			
			while(resultset.next())
			{
				Book book=new Book(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getInt(4),resultset.getInt(5));
				booklist.add(book);
			}
		
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			try {
				if(statement!=null)
					statement.close();
				if(connection!=null)
					ModelDAO.closeConnection();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return booklist;
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException{
		
		String sql="select * from book where author=?";
		
		Connection connection = ModelDAO.openConnection();		
		PreparedStatement statement = null;
		List<Book> booklist=null;
		try{
			statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, author);
			ResultSet resultset=statement.executeQuery();
			booklist=new ArrayList<>();
			
			if(!resultset.next())
				throw new AuthorNotFoundException("Author Not Found!!!");
			
			resultset.beforeFirst();
			while(resultset.next())
			{
				Book book=new Book(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getInt(4),resultset.getInt(5));
				booklist.add(book);
			}
		
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			try {
				if(statement!=null)
					statement.close();
				if(connection!=null)
					ModelDAO.closeConnection();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return booklist;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException{
		
		String sql="select * from book where category=?";
		
		Connection connection = ModelDAO.openConnection();		
		PreparedStatement statement = null;
		List<Book> booklist=null;
		try{
			statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, category);
			ResultSet resultset=statement.executeQuery();
			booklist=new ArrayList<>();
			
			if(!resultset.next())
				throw new CategoryNotFoundException("Category Not Found!!!");
			
			resultset.beforeFirst();
			while(resultset.next())
			{
				Book book=new Book(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getInt(4),resultset.getInt(5));
				booklist.add(book);
			}
		
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			try {
				if(statement!=null)
					statement.close();
				if(connection!=null)
					ModelDAO.closeConnection();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return booklist;
	}

	

	

}
