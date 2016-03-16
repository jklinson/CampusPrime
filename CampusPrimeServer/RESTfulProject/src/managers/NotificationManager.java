package managers;

import java.sql.Connection;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import connector.Database;
import handlers.NewsHandler;
import handlers.NotificationHandler;
import models.NotificationObjects;

public class NotificationManager {

	public ArrayList<NotificationObjects> GetNotifications()throws Exception {
		ArrayList<NotificationObjects> notificationDetails = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				NotificationHandler notificationsHandler= new NotificationHandler();
				notificationDetails=notificationsHandler.GetNotifications(connection);
				
				
		} catch (Exception e) {
			throw e;
		}
		return notificationDetails;
	}
	
	public boolean saveNotifications(String notificationDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(notificationDetails);
				System.out.println("2. "+jsonObject.toString());
				NotificationObjects notificaiton= new NotificationObjects(jsonObject);
				NotificationHandler handler= new NotificationHandler();
				response=handler.saveNotifications(notificaiton, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
