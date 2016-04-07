package managers;

import java.sql.Connection;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import connector.Database;
import handlers.CalendarHandler;
import handlers.NewsHandler;
import models.CalendarObject;
import models.NewsObjects;

public class CalendarManager {

	public ArrayList<CalendarObject> GetCalendarEvents()throws Exception {
		ArrayList<CalendarObject> newsDetails = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				CalendarHandler calendarHandler= new CalendarHandler();
				newsDetails=calendarHandler.GetCalendarEvents(connection);
				
		} catch (Exception e) {
			throw e;
		}
		return newsDetails;
	}
	
	public boolean saveNews(String newsDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(newsDetails);
				System.out.println("2. "+jsonObject.toString());
				CalendarObject news= new CalendarObject(jsonObject);
				CalendarHandler handler= new CalendarHandler();
				response=handler.saveCalendar(news, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	public boolean updateCalendarEvents(String eventDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(eventDetails);
				System.out.println("2. "+jsonObject.toString());
				CalendarObject obj = new CalendarObject(jsonObject);
				CalendarHandler handler= new CalendarHandler();
				response=handler.updateCalendarEvents(obj, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	public boolean deleteCalendarEvents(String eventDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(eventDetails);
				System.out.println("2. "+jsonObject.toString());
				CalendarObject obj = new CalendarObject(jsonObject);
				CalendarHandler handler= new CalendarHandler();
				response=handler.deleteCalendarEvents(obj, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
