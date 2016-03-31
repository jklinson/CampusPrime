package managers;

import java.sql.Connection;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import connector.Database;
import factory.JsonFactory;
import handlers.AudienceHandler;
import models.AudienceObject;

public class AudienceManager {

	public JsonObject getYearAndClass(){
		
		Database database= new Database();
		ArrayList<AudienceObject> classYearList = new ArrayList<AudienceObject>();
		JsonObject jsonObject = new JsonObject();
		try {
			Connection connection = database.Get_Connection();
			AudienceHandler handler = new AudienceHandler();
			classYearList = handler.getYearAndClass(connection);
			jsonObject = JsonFactory.createYearClassObj(classYearList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject = JsonFactory.createFailureJson(jsonObject, e.getMessage());
		}
		
		return jsonObject;
		
	}
}
