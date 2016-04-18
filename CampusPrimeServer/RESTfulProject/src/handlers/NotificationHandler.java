package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.NewsObjects;
import models.NotificationObjects;

public class NotificationHandler {

	public String tableName = "notifications";
	public String coloumnNames = "notificationId,title,description,publishedBy,publishedDate,"
			+ "audienceId,fileId,isAproved";
	public String insertStmnt = "INSERT INTO "+tableName+" (title,description,publishedBy,publishedDate,"
			+ "fileId,isAproved,audienceId) VALUES ";
	
	public ArrayList<NotificationObjects> GetNotifications(Connection connection) throws Exception
	{
		ArrayList<NotificationObjects> notData = new ArrayList<NotificationObjects>();
		try
		{			
			String sql = "select notifications.notificationId,notifications.title,notifications.description,notifications.publishedBy,notifications.publishedDate,"
			+ "notifications.audienceId,notifications.fileId,notifications.isAproved,users.name, "
			+ "campus_prime.target_audience.classNum, campus_prime.target_audience.isTeacher, campus_prime.target_audience.year"
			+ " from notifications "
			+ "inner join users on notifications.publishedBy = users.userId "
			+ "inner join campus_prime.target_audience on campus_prime.notifications.audienceId = campus_prime.target_audience.targetId";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				NotificationObjects not = new NotificationObjects();
				not.setNotificationId(rs.getInt("notificationId"));
				not.setTitle(rs.getString("title"));
				not.setDescription(rs.getString("description"));
				not.setPublishedBy(rs.getInt("publishedBy"));
				not.setPublishedDate(rs.getString("publishedDate"));
				not.setAudienceId(rs.getInt("audienceId"));
				not.setFileId(rs.getInt("fileId"));
				not.setIsApproved(rs.getInt("isAproved"));
				not.setPublishedUser(rs.getString("name"));
				not.setClassNum(rs.getString("classNum"));
				not.setYear(rs.getString("year"));
				not.setIsTeacher(rs.getInt("isTeacher"));
				notData.add(not);
			}
			System.out.println(notData);
			return notData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public boolean saveNotifications(NotificationObjects obj,Connection connection) throws Exception
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
	
	public boolean updateNotifications(NotificationObjects obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "update "+tableName+" set title = '"+obj.getTitle()+"', description = '"+obj.getDescription()
			+"', isAproved = "+obj.getIsApproved()
			+ ", fileId = "+obj.getFileId()+", publishedDate = '"+obj.getPublishedDate()+"', publishedBy = "+obj.getPublishedBy()
			+", audienceId = "+AudienceHandler.createSelectQuerry(obj.getYear(), obj.getClassNum(), obj.getIsTeacher())
			+ " where notificationId ="+obj.getNotificationId();
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
	public boolean deleteNotification(NotificationObjects obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "DELETE FROM "+tableName+" WHERE notificationId ="+obj.getNotificationId();
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