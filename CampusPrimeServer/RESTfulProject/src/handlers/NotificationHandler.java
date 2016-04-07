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
			+ "audienceId,fileId,isAproved) VALUES ";
	
	public ArrayList<NotificationObjects> GetNotifications(Connection connection) throws Exception
	{
		ArrayList<NotificationObjects> newsData = new ArrayList<NotificationObjects>();
		try
		{			
			String sql = "select notifications.notificationId,notifications.title,notifications.description,notifications.publishedBy,notifications.publishedDate,"
			+ "notifications.audienceId,notifications.fileId,notifications.isAproved,users.name from notifications inner join users on notifications.publishedBy = users.userId";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				NotificationObjects news = new NotificationObjects();
				news.setNotificationId(rs.getInt("notificationId"));
				news.setTitle(rs.getString("title"));
				news.setDescription(rs.getString("description"));
				news.setPublishedBy(rs.getInt("publishedBy"));
				news.setPublishedDate(rs.getString("publishedDate"));
				news.setAudienceId(rs.getInt("audienceId"));
				news.setFileId(rs.getInt("fileId"));
				news.setIsApproved(rs.getInt("isAproved"));
				news.setPublishedUser(rs.getString("name"));
				newsData.add(news);
			}
			System.out.println(newsData);
			return newsData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public boolean saveNotifications(NotificationObjects notificationObjects,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = insertStmnt +"("+notificationObjects.convertToString()+")";
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
			+"', audienceId = "+obj.getAudienceId()+ ", isAproved = "+obj.getIsApproved()
			+ ", fileId = "+obj.getFileId()+", publishedDate = '"+obj.getPublishedDate()+"', publishedBy = "+obj.getPublishedBy()
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