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
			String sql = "SELECT campus_prime.users.userId, campus_prime.users.email, campus_prime.users.password, "
					+ "campus_prime.users.name,campus_prime.users.mobileNum, campus_prime.users.year, "
					+ "campus_prime.users.department, campus_prime.users.uniqueId, campus_prime.users.classOrSRoom,"
					+ " campus_prime.users.isActive, campus_prime.users.isTeacher, campus_prime.users.isEmailVerified"
					+ " ,(campus_prime.admins.userId is not null) isAdmin, campus_prime.admins.year as adminOfYear, "
					+ "campus_prime.admins.class as adminOfClass, campus_prime.admins.id as adminId, "
					+ "campus_prime.admins.audienceID as targetId FROM campus_prime.users left outer join campus_prime.admins "
					+ "on campus_prime.users.userId = campus_prime.admins.userId;";
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
				user.setEmailVerified(rs.getBoolean("isEmailVerified"));
				user.setAdmin(rs.getBoolean("isAdmin"));
				user.setAdminOfYear(rs.getString("adminOfYear"));
				user.setAdminOfClass(rs.getString("adminOfClass"));
				user.setAdminId(rs.getInt("adminId"));
				user.setAdminTargetId(rs.getInt("targetId"));
				userData.add(user);
			}
			return userData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public UsersObjects GetUserByEmail(Connection connection, String mailId) throws Exception
	{
		UsersObjects user = new UsersObjects();
		try
		{			
			String sql = "SELECT "+coloumnNames+" FROM "+tableName+" where email = '"+mailId+"'";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			
			//ps.setString(1,uname);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
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
				user.setEmailVerified(rs.getBoolean("isEmailVerified"));
				user.setPassword(rs.getString("password"));
				System.out.println("isEmailVerified " +rs.getBoolean("isEmailVerified"));
			}
			return user;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public UsersObjects register(UsersObjects usersObjects,Connection connection) throws Exception
	{
		//{"email":"tester123@gmail.com","name":"myName","password":"qwerty","mobileNum":"9896888778","year":"4","department":"CS","uniqueId":"3434","classOrSRoom":"CS_B","isActive":0,"isTeacher":0,"isEmailVerified":0}
		
		try
		{	
			String sql = insertStmnt +"("+usersObjects.convertToString()+")";
			System.out.println("sql "+sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();			
			return usersObjects;
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	public void verifyEmail(Connection connection, int userId) throws Exception
	{
		//{"email":"tester123@gmail.com","name":"myName","password":"qwerty","mobileNum":"9896888778","year":"4","department":"CS","uniqueId":"3434","classOrSRoom":"CS_B","isActive":0,"isTeacher":0,"isEmailVerified":0}
		
		try
		{	
			String sql = "update "+tableName+" set isEmailVerified = 1 where userId = "+userId;
			System.out.println("sql "+sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();			
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	public void updateUser(Connection connection, UsersObjects user) throws Exception
	{
		//{"email":"tester123@gmail.com","name":"myName","password":"qwerty","mobileNum":"9896888778","year":"4","department":"CS","uniqueId":"3434","classOrSRoom":"CS_B","isActive":0,"isTeacher":0,"isEmailVerified":0}
		
		try
		{	
			String sql = "update "+tableName+" set isActive ="+ user.isActive() +" where userId = "+user.getUserId();
			System.out.println("sql "+sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();			
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
			String sql = "SELECT campus_prime.users.userId, campus_prime.users.email, campus_prime.users.password, "
					+ "campus_prime.users.name,campus_prime.users.mobileNum, campus_prime.users.year, "
					+ "campus_prime.users.department, campus_prime.users.uniqueId, campus_prime.users.classOrSRoom,"
					+ " campus_prime.users.isActive, campus_prime.users.isTeacher, campus_prime.users.isEmailVerified"
					+ " ,(campus_prime.admins.userId is not null) isAdmin, campus_prime.admins.year as adminOfYear, "
					+ "campus_prime.admins.class as adminOfClass, campus_prime.admins.id as adminId, "
					+ "campus_prime.admins.audienceID as targetId FROM campus_prime.users left outer join campus_prime.admins "
					+ "on campus_prime.users.userId = campus_prime.admins.userId "
					+ "where campus_prime.users.email = '"+userOb.getEmail()+"';";
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
				user.setEmailVerified(rs.getBoolean("isEmailVerified"));
				user.setAdmin(rs.getBoolean("isAdmin"));
				user.setAdminOfYear(rs.getString("adminOfYear"));
				user.setAdminOfClass(rs.getString("adminOfClass"));
				user.setAdminId(rs.getInt("adminId"));
				user.setAdminTargetId(rs.getInt("targetId"));
			}
			return user;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
