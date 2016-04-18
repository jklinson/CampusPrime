package managers;

import java.sql.Connection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import connector.Database;
import handlers.AdminHandler;
import handlers.ClapsHandler;
import models.AdminObject;
import models.ClapObject;

public class AdminManager {

	public boolean saveAdmins(String adminDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(adminDetails);
				System.out.println("2. "+jsonObject.toString());
				AdminObject obj= new AdminObject(jsonObject);
				AdminHandler handler= new AdminHandler();
				response=handler.saveAdmins(obj, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	public boolean updateAdmins(String adminDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(adminDetails);
				System.out.println("2. "+jsonObject.toString());
				AdminObject obj= new AdminObject(jsonObject);
				AdminHandler handler= new AdminHandler();
				response=handler.updateAdmins(obj, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	public boolean deleteAdmin(String adminDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(adminDetails);
				System.out.println("2. "+jsonObject.toString());
				AdminObject obj= new AdminObject(jsonObject);
				AdminHandler handler= new AdminHandler();
				response=handler.deleteAdmin(obj, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
