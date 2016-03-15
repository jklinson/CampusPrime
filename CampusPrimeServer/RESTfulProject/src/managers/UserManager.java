/**
 * 
 */
package managers;

import java.sql.Connection;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import connector.Database;
import handlers.UserHandler;
import models.UsersObjects;
import utility.Constants;

/**
 * @author Linson Alfred
 *
 */
public class UserManager {

	
	public ArrayList<UsersObjects> GetUser()throws Exception {
		ArrayList<UsersObjects> userDetails = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				UserHandler user= new UserHandler();
				userDetails=user.GetUser(connection);
				
		} catch (Exception e) {
			throw e;
		}
		return userDetails;
	}
	
	public boolean registerUser(String userDetails)throws Exception {
		boolean response=false;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(userDetails);
				System.out.println("2. "+jsonObject.toString());
				UsersObjects user= new UsersObjects(jsonObject);
				UserHandler handler= new UserHandler();
				response=handler.register(user, connection);
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	public JsonObject checkLogin(String userDetails)throws Exception{
		JsonObject response = new JsonObject();
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = new JsonObject();
				jsonObject = (JsonObject)parser.parse(userDetails);
				System.out.println("2. "+jsonObject.toString());
				UsersObjects user= new UsersObjects(jsonObject);
				UserHandler handler= new UserHandler();
				UsersObjects existingUser=new UsersObjects();
				existingUser=handler.checkLogin(user, connection);
				if(existingUser !=null){
					if(user.getPassword().equals(existingUser.getPassword())){
						response.addProperty(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
						response.addProperty(Constants.MESSAGE_KEY, "Succesfully loged in.");
						response.add("user", new Gson().toJsonTree(existingUser));
					}
					else{
						response.addProperty(Constants.STATUS_KEY, Constants.STATUS_FAILURE);
						response.addProperty(Constants.MESSAGE_KEY, "Email or Password you have entered is incorrect.");
					}
						
				}
				else{
					response.addProperty(Constants.STATUS_KEY, Constants.STATUS_FAILURE);
					response.addProperty(Constants.MESSAGE_KEY, "Email or Password you have entered is incorrect. If you are a new user please register first.");
					
				}
				
				
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
