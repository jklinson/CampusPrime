package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;

import models.AdminObject;

public class AdminHandler {

	private String tableName = "admins";
	public String insertStmnt = "INSERT INTO "+tableName+" (userId, class, year, audienceId) VALUES ";
	
	public boolean saveAdmins(AdminObject obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = insertStmnt +"("+obj.convertToString()+")";
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
	public boolean updateAdmins(AdminObject obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "update "+tableName+" set year = '"+obj.getAdminYear()+"', "
					+ "class = '"+ obj.getAdminClass()+"', audienceId ="+obj.getAudienceId()+" where userId ="+obj.getUserId()
			+" && id = "+obj.getAdminId();
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
	public boolean deleteAdmin(AdminObject obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "delete from "+tableName+ " where id = "+obj.getAdminId();
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
}
