package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.NewsObjects;

public class NewsHandler {

	public String tableName = "news";
	public String coloumnNames = "newsId,title,description,publishedBy,publishedDate,"
			+ "audienceId,fileId,isAproved";
	public String insertStmnt = "INSERT INTO "+tableName+" (title,description,publishedBy,publishedDate,"
			+ "audienceId,fileId,isAproved) VALUES ";
	
	public ArrayList<NewsObjects> GetNews(Connection connection) throws Exception
	{
		ArrayList<NewsObjects> newsData = new ArrayList<NewsObjects>();
		try
		{			
			String sql = "SELECT "+coloumnNames+" FROM "+tableName;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				NewsObjects news = new NewsObjects();
				news.setNewsId(rs.getInt("newsId"));
				news.setTitle(rs.getString("title"));
				news.setDescription(rs.getString("description"));
				news.setPublishedBy(rs.getInt("publishedBy"));
				news.setPublishedDate(rs.getString("publishedDate"));
				news.setAudienceId(rs.getInt("audienceId"));
				news.setFileId(rs.getInt("fileId"));
				news.setIsApproved(rs.getInt("isAproved"));
				newsData.add(news);
			}
			return newsData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public boolean saveNews(NewsObjects newsObjects,Connection connection) throws Exception
	{
		//{"email":"tester123@gmail.com","name":"myName","password":"qwerty","mobileNum":"9896888778","year":"4","department":"CS","uniqueId":"3434","classOrSRoom":"CS_B","isActive":0,"isTeacher":0,"isEmailVerified":0}
		
		try
		{	
			String sql = insertStmnt +"("+newsObjects.convertToString()+")";
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