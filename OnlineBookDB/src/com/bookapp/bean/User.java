package com.bookapp.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bookapp.dao.ModelDAO;
import com.bookapp.exception.UserNotFound;

public class User {
	String username;
	String password;
	
	public void addUser(String username,String password) {
		String sql="insert into users values (?,?)";
		
		try(Connection connection = ModelDAO.openConnection();				//try-with-resource
			PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setString(1, username);
			statement.setString(2,password);
			statement.execute();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public boolean login(String username,String password) throws UserNotFound {
		boolean res=false;
		String sql="select * from users";
		
		try(Connection connection = ModelDAO.openConnection();		
				PreparedStatement statement = connection.prepareStatement(sql);){
			ResultSet resultset=statement.executeQuery();
			
			while(resultset.next())
			{
				String user=resultset.getString(1);
				String pass=resultset.getString(2);
				if(user.equals(username) && pass.equals(password))
				{
					res=true;
					return res;
				}
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		if(res==false)
			throw new UserNotFound("User has not Registered!!!");
		return res;
	}
}
