package managers;

import java.sql.Connection;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import connector.Database;
import handlers.WriteUpHandler;
import models.WriteUpObjects;

public class WriteUpManager {

	public ArrayList<WriteUpObjects> GetWriteUps(int userId)throws Exception {
		ArrayList<WriteUpObjects> writeUpDetails = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				WriteUpHandler writeUpHandler= new WriteUpHandler();
				writeUpDetails=writeUpHandler.GetWriteUps(connection, userId);
				
		} catch (Exception e) {
			throw e;
		}
		return writeUpDetails;
	}
	
	public boolean saveWriteUps(String writeUpDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(writeUpDetails);
				System.out.println("2. "+jsonObject.toString());
				WriteUpObjects writeUp = new WriteUpObjects(jsonObject);
				WriteUpHandler handler= new WriteUpHandler();
				response=handler.saveWriteUps(writeUp, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	public boolean updateWriteUps(String writeUpDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(writeUpDetails);
				System.out.println("2. "+jsonObject.toString());
				WriteUpObjects writeUp = new WriteUpObjects(jsonObject);
				WriteUpHandler handler= new WriteUpHandler();
				response=handler.updateWriteUps(writeUp, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	public boolean deleteWriteUps(String writeUpDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(writeUpDetails);
				System.out.println("2. "+jsonObject.toString());
				WriteUpObjects writeUp = new WriteUpObjects(jsonObject);
				WriteUpHandler handler= new WriteUpHandler();
				response=handler.deleteWriteUps(writeUp, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
