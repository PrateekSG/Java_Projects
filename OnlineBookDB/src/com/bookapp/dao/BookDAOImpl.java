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
			PreparedStatement st = connection.prepareStatement(sql);){
			st.setString(1, book.getTitle());
			st.setString(2,book.getAuthor());
			st.setString(3,book.getCategory());
			st.setInt(4,book.getBookid());
			st.setInt(5,book.getPrice());
			st.execute();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		// TODO Auto-generated method stub
		String sql="delete from book where bookid=?";
		boolean f=true;
		try(Connection connection = ModelDAO.openConnection();				//try-with-resource
			PreparedStatement st = connection.prepareStatement(sql);){
			st.setInt(1,bookid);
			int val=st.executeUpdate();
			System.out.println(val);
			if(val==0)
				f=false;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return f;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBook(int bookid, int price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		String sql="select * from book";
		
		Connection connection = ModelDAO.openConnection();		
		PreparedStatement st = null;
		List<Book> booklist=null;
		try{
			st = connection.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			booklist=new ArrayList<>();
			
			while(rs.next())
			{
				Book b=new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
				booklist.add(b);
			}
		
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			try {
				if(st!=null)
					st.close();
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
		// TODO Auto-generated method stub
		String sql="select * from book where author=?";
		
		Connection connection = ModelDAO.openConnection();		
		PreparedStatement st = null;
		List<Book> booklist=null;
		try{
			st = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.setString(1, author);
			ResultSet rs=st.executeQuery();
			booklist=new ArrayList<>();
			
			if(!rs.next())
				throw new AuthorNotFoundException("Author Not Found!!!");
			
			rs.beforeFirst();
			while(rs.next())
			{
				Book b=new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
				booklist.add(b);
			}
		
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			try {
				if(st!=null)
					st.close();
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
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
