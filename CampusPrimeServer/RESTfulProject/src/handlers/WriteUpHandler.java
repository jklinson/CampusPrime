package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import managers.AudienceManager;
import models.WriteUpObjects;

public class WriteUpHandler {

	public String tableName = "writeups";
	public String coloumnNames = "writeUpId,title,description,publishedBy,publishedDate,"
			+ "audienceId,fileId,isAproved, type";
	public String insertStmnt = "INSERT INTO "+tableName+" (title,description,publishedBy,publishedDate,"
			+ "fileId,isAproved, type, audienceId) VALUES ";
	
	public ArrayList<WriteUpObjects> GetWriteUps(Connection connection, int userId) throws Exception
	{
		ArrayList<WriteUpObjects> writeupData = new ArrayList<WriteUpObjects>();
		try
		{	String sql = "select campus_prime.writeups.writeUpId,campus_prime.writeups.title,campus_prime.writeups.description, "
				+"campus_prime.writeups.publishedBy,campus_prime.writeups.publishedDate,campus_prime.writeups.audienceId, "
				+"campus_prime.writeups.type,campus_prime.writeups.fileId,campus_prime.writeups.isAproved, "
				+"campus_prime.users.name,campus_prime.target_audience.year,campus_prime.files.fileType, "
				+"campus_prime.target_audience.classNum, campus_prime.target_audience.isTeacher, "
				+ "sum(campus_prime.claps.count) as clapCount, "
				+"(select ifnull((select campus_prime.claps.count from campus_prime.claps where campus_prime.claps.userId = "+userId
				+" and campus_prime.writeups.writeUpId = campus_prime.claps.parentId),0)) as myClapCount "
				+"from campus_prime.writeups "
				+"inner join campus_prime.files "
				+"on campus_prime.writeups.fileId = campus_prime.files.fileId " 
				+"inner join campus_prime.users "
				+"on campus_prime.writeups.publishedBy = campus_prime.users.userId "
				+"inner join campus_prime.target_audience "
				+"on campus_prime.writeups.audienceId = campus_prime.target_audience.targetId "
				+"left outer join campus_prime.claps "
				+"on campus_prime.writeups.writeUpId = campus_prime.claps.parentId "
				+"group by writeUpId";		
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
				writeUp.setPublishedUser(rs.getString("name"));
				writeUp.setYear(rs.getString("year"));
				writeUp.setClassNum(rs.getString("classNum"));
				writeUp.setClapCount(rs.getInt("clapCount"));
				writeUp.setMyClapCount(rs.getInt("myClapCOunt"));
				writeUp.setFileType(rs.getString("fileType"));
				writeUp.setIsTeacher(rs.getInt("isTeacher"));
				writeupData.add(writeUp);
			}
			return writeupData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public boolean saveWriteUps(WriteUpObjects obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = insertStmnt +"("+obj.convertToString()+
					AudienceHandler.createSelectQuerry(obj.getYear(), obj.getClassNum(), obj.getIsTeacher())+")";
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
			+ "', type = '"+obj.getType()+"', isAproved = "+obj.getIsApproved()
			+ ", fileId = "+obj.getFileId()+", publishedDate = '"+obj.getPublishedDate()+"', publishedBy = "+obj.getPublishedBy()
			+ ", audienceId = "+AudienceHandler.createSelectQuerry(obj.getYear(), obj.getClassNum(), obj.getIsTeacher())
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