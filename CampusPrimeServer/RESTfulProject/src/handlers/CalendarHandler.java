package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.CalendarObject;
import models.NewsObjects;

public class CalendarHandler {

	public String tableName = "calendar";
	public String coloumnNames = "calendarId,title,description,publishedBy,publishedDate,"
			+ "audienceId,isAproved,startsAt,endsAt,type";
	public String insertStmnt = "INSERT INTO "+tableName+" (title,description,publishedBy,publishedDate,"
			+ "startsAt,endsAt,type,audienceId,isAproved) VALUES ";
	
	public ArrayList<CalendarObject> GetCalendarEvents(Connection connection) throws Exception
	{
		ArrayList<CalendarObject> calendarData = new ArrayList<CalendarObject>();
		try
		{			
			String sql = "select calendar.calendarId,calendar.title,calendar.description,calendar.publishedBy,calendar.publishedDate,"
			+ "calendar.audienceId,calendar.isAproved,calendar.startsAt,calendar.endsAt,calendar.type,users.name from calendar inner join users on calendar.publishedBy = users.userId";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				CalendarObject calendar = new CalendarObject();
				calendar.setCalendarId(rs.getInt("calendarId"));
				calendar.setTitle(rs.getString("title"));
				calendar.setDescription(rs.getString("description"));
				calendar.setPublishedBy(rs.getInt("publishedBy"));
				calendar.setPublishedDate(rs.getString("publishedDate"));
				calendar.setStartsAt(rs.getString("startsAt"));
				calendar.setEndsAt(rs.getString("endsAt"));
				calendar.setType(rs.getString("type"));
				calendar.setAudienceId(rs.getInt("audienceId"));
				calendar.setIsApproved(rs.getInt("isAproved"));
				calendar.setPublishedUser(rs.getString("name"));
				calendarData.add(calendar);
			}
			System.out.println(calendarData);
			return calendarData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public boolean saveCalendar(CalendarObject calendarObject,Connection connection) throws Exception
	{
		//{"email":"tester123@gmail.com","name":"myName","password":"qwerty","mobileNum":"9896888778","year":"4","department":"CS","uniqueId":"3434","classOrSRoom":"CS_B","isActive":0,"isTeacher":0,"isEmailVerified":0}
		
		try
		{	
			String sql = insertStmnt +"("+calendarObject.convertToString()+")";
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
	public boolean updateCalendarEvents(CalendarObject obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "update "+tableName+" set title = '"+obj.getTitle()+"', description = '"+obj.getDescription()
			+"', audienceId = "+obj.getAudienceId()+ ", isAproved = "+obj.getIsApproved()+ ", startsAt = '"+obj.getStartsAt()
			+ "', endsAt = '"+obj.getEndsAt()+"', publishedDate = '"+obj.getPublishedDate()+"', publishedBy = "+obj.getPublishedBy()
			+ " where calendarId ="+obj.getCalendarId();
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
	public boolean deleteCalendarEvents(CalendarObject obj,Connection connection) throws Exception
	{
		
		try
		{	
			String sql = "DELETE FROM "+tableName+" WHERE calendarId ="+obj.getCalendarId();
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