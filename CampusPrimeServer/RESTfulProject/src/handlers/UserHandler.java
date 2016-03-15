package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import models.UsersObjects;


public class UserHandler {

	private String tableName	= "users";
	private String coloumnNames	= "userId, email, password, name, mobileNum, year, department, uniqueId, classOrSRoom, isActive, isTeacher, isEmailVerified";
	private String insertStmnt 	= "INSERT INTO "+tableName+"(`email`, `name`, `password`, `mobileNum`, `year`, `department`, `uniqueId`, `classOrSRoom`, `isActive`, `isTeacher`, `isEmailVerified`) VALUES ";
	
	public ArrayList<UsersObjects> GetUser(Connection connection) throws Exception
	{
		ArrayList<UsersObjects> userData = new ArrayList<UsersObjects>();
		try
		{			
			String sql = "SELECT "+coloumnNames+" FROM "+tableName;
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			
			//ps.setString(1,uname);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				UsersObjects user = new UsersObjects();
				user.setUserId(rs.getInt("userId"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setMobileNum(rs.getLong("mobileNum"));
				user.setYear(rs.getString("year"));
				user.setDepartment(rs.getString("department"));
				user.setUniqueId(rs.getString("uniqueId"));
				user.setClassOrSRoom(rs.getString("classOrSRoom"));
				user.setActive(rs.getBoolean("isActive"));
				user.setTeacher(rs.getBoolean("isTeacher"));
				user.setEmailVerified(rs.getBoolean("isTeacher"));
				userData.add(user);
			}
			return userData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public boolean register(UsersObjects usersObjects,Connection connection) throws Exception
	{
		//{"email":"tester123@gmail.com","name":"myName","password":"qwerty","mobileNum":"9896888778","year":"4","department":"CS","uniqueId":"3434","classOrSRoom":"CS_B","isActive":0,"isTeacher":0,"isEmailVerified":0}
		
		try
		{	
			String sql = insertStmnt +"("+usersObjects.convertToString()+")";
			System.out.println("sql "+sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();			
			return true;
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}

	public UsersObjects checkLogin(UsersObjects userOb, Connection connection) throws Exception {
		try
		{
			UsersObjects user = new UsersObjects();
			String sql = "SELECT "+coloumnNames+" FROM "+tableName+" where email='"+userOb.getEmail()+"';";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				user.setUserId(rs.getInt("userId"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setMobileNum(rs.getLong("mobileNum"));
				user.setYear(rs.getString("year"));
				user.setDepartment(rs.getString("department"));
				user.setUniqueId(rs.getString("uniqueId"));
				user.setClassOrSRoom(rs.getString("classOrSRoom"));
				user.setActive(rs.getBoolean("isActive"));
				user.setTeacher(rs.getBoolean("isTeacher"));
				user.setEmailVerified(rs.getBoolean("isTeacher"));
			}
			return user;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
