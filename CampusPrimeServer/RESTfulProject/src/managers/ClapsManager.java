package managers;

import java.sql.Connection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import connector.Database;
import handlers.ClapsHandler;
import models.ClapObject;

public class ClapsManager {

	public boolean saveClaps(String newsDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(newsDetails);
				System.out.println("2. "+jsonObject.toString());
				ClapObject obj= new ClapObject(jsonObject);
				ClapsHandler handler= new ClapsHandler();
				response=handler.saveClapCount(obj, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	public boolean updateClapCount(String clapDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(clapDetails);
				System.out.println("2. "+jsonObject.toString());
				ClapObject obj = new ClapObject(jsonObject);
				ClapsHandler handler= new ClapsHandler();
				response=handler.updateClapCount(obj, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
