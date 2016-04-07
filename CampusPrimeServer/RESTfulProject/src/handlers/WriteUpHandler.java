package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.WriteUpObjects;

public class WriteUpHandler {

	public String tableName = "writeups";
	public String coloumnNames = "writeUpId,title,description,publishedBy,publishedDate,"
			+ "audienceId,fileId,isAproved, type";
	public String insertStmnt = "INSERT INTO "+tableName+" (title,description,publishedBy,publishedDate,"
			+ "audienceId,fileId,isAproved, type) VALUES ";
	
	public ArrayList<WriteUpObjects> GetWriteUps(Connection connection) throws Exception
	{
		ArrayList<WriteUpObjects> writeupData = new ArrayList<WriteUpObjects>();
		try
		{			
			String sql = "SELECT "+coloumnNames+" FROM "+tableName;
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				WriteUpObjects writeUp = new WriteUpObjects();
				writeUp.setWriteUpId(rs.getInt("writeUpId"));
				writeUp.setTitle(rs.getString("title"));
				writeUp.setDescription(rs.getString("description"));
				writeUp.setPublishedBy(rs.getInt("publishedBy"));
				writeUp.setPublishedDate(rs.getString("publishedDate"));
				writeUp.setAudienceId(rs.getInt("audienceId"));
				writeUp.setFileId(rs.getInt("fileId"));
				writeUp.setIsApproved(rs.getInt("isAproved"));
				writeUp.setType(rs.getString("type"));
				writeupData.add(writeUp);
			}
			return writeupData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public boolean saveWriteUps(WriteUpObjects writeUpObjects,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = insertStmnt +"("+writeUpObjects.convertToString()+")";
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
	public boolean updateWriteUps(WriteUpObjects obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "update "+tableName+" set title = '"+obj.getTitle()+"', description = '"+obj.getDescription()
			+ "', type = '"+obj.getType()+"', audienceId = "+obj.getAudienceId()+ ", isAproved = "+obj.getIsApproved()
			+ ", fileId = "+obj.getFileId()+", publishedDate = '"+obj.getPublishedDate()+"', publishedBy = "+obj.getPublishedBy()
			+ " where writeUpId ="+obj.getWriteUpId();
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
	public boolean deleteWriteUps(WriteUpObjects obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "DELETE FROM "+tableName+" WHERE writeUpId ="+obj.getWriteUpId();
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