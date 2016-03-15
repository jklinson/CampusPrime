package managers;

import java.sql.Connection;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import connector.Database;
import handlers.NewsHandler;
import models.NewsObjects;

public class NewsManager {

	public ArrayList<NewsObjects> GetNews()throws Exception {
		ArrayList<NewsObjects> newsDetails = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				NewsHandler newsHandler= new NewsHandler();
				newsDetails=newsHandler.GetNews(connection);
				
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
				NewsObjects news= new NewsObjects(jsonObject);
				NewsHandler handler= new NewsHandler();
				response=handler.saveNews(news, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
