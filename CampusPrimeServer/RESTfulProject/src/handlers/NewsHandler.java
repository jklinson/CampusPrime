package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.NewsObjects;
import models.WriteUpObjects;

public class NewsHandler {

	public String tableName = "news";
	public String coloumnNames = "newsId,title,description,publishedBy,publishedDate,"
			+ "audienceId,fileId,isAproved";
	public String insertStmnt = "INSERT INTO "+tableName+" (title,description,publishedBy,publishedDate,"
			+ "fileId,isAproved,audienceId) VALUES ";
	
	public ArrayList<NewsObjects> GetNews(Connection connection) throws Exception
	{
		ArrayList<NewsObjects> newsData = new ArrayList<NewsObjects>();
		try
		{			
			String sql = "select campus_prime.news.newsId,campus_prime.news.title,campus_prime.news.description,"
					+ "campus_prime.news.publishedBy,campus_prime.news.publishedDate,campus_prime.news.audienceId,"
					+ "campus_prime.news.fileId,campus_prime.news.isAproved,campus_prime.users.name,campus_prime.target_audience.year,"
					+ "campus_prime.target_audience.classNum, campus_prime.target_audience.isTeacher from campus_prime.news "
					+ "inner join campus_prime.users on campus_prime.news.publishedBy = campus_prime.users.userId "
					+ "inner join campus_prime.target_audience on campus_prime.news.audienceId = campus_prime.target_audience.targetId";
			System.out.println(sql);
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
				news.setPublishedUser(rs.getString("name"));
				news.setYear(rs.getString("year"));
				news.setClassNum(rs.getString("classNum"));
				news.setIsTeacher(rs.getInt("isTeacher"));
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
	
	public boolean saveNews(NewsObjects obj,Connection connection) throws Exception
	{
		//{"email":"tester123@gmail.com","name":"myName","password":"qwerty","mobileNum":"9896888778","year":"4","department":"CS","uniqueId":"3434","classOrSRoom":"CS_B","isActive":0,"isTeacher":0,"isEmailVerified":0}
		
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
	public boolean updateNews(NewsObjects obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "update "+tableName+" set title = '"+obj.getTitle()+"', description = '"+obj.getDescription()
			+"', isAproved = "+obj.getIsApproved()
			+ ", fileId = "+obj.getFileId()+", publishedDate = '"+obj.getPublishedDate()+"', publishedBy = "+obj.getPublishedBy()
			+ ", audienceId = "+AudienceHandler.createSelectQuerry(obj.getYear(), obj.getClassNum(), obj.getIsTeacher())
			+ " where newsId ="+obj.getNewsId();
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
	
	public boolean deleteNews(NewsObjects obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "DELETE FROM "+tableName+" WHERE newsId ="+obj.getNewsId();
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