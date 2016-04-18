package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;

import models.CalendarObject;
import models.ClapObject;

public class ClapsHandler {

	private String tableName = "claps";
	public String insertStmnt = "INSERT INTO "+tableName+" (count, parentId, userId) VALUES ";
	
	public boolean saveClapCount(ClapObject obj,Connection connection) throws Exception
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
	public boolean updateClapCount(ClapObject obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "update "+tableName+" set count = "+obj.getCount()+" where userId ="+obj.getUserId()
			+" && parentId = "+obj.getParentId();
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
