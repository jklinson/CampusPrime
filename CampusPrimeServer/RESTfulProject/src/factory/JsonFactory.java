package factory;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.NewsObjects;
import models.WriteUpObjects;
import utility.Constants;

public class JsonFactory {

	public static JsonObject createFailureJson(JsonObject json, String message){
		json.addProperty(Constants.STATUS_KEY, Constants.STATUS_FAILURE);
		json.addProperty(Constants.MESSAGE_KEY, message);
		return json;
		
	}
	public static JsonObject createSuccesseJson(JsonObject json, String message){
		json.addProperty(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
		json.addProperty(Constants.MESSAGE_KEY, message);
		return json;
		
	}
	
	public static JsonObject fileUploadSuccessJSON(JsonObject json, int fileId){
		
		createSuccesseJson(json, "SuccesFully Uploaded the file to server");
		json.addProperty("fileId", fileId);
		return json;
		
	}
	
	public static String createWriteUpArray(ArrayList<WriteUpObjects> writeUpData){
		
		JsonObject json = new JsonObject();
		createSuccesseJson(json, "SuccesFully retrieved the writeup details.");
		Gson gson = new Gson();
		json.addProperty("writeups", gson.toJson(writeUpData));
		return json.toString();
		
	}
	public static String createNewsArray(ArrayList<NewsObjects> newsData){
		
		JsonObject json = new JsonObject();
		createSuccesseJson(json, "SuccesFully retrieved the news details.");
		Gson gson = new Gson();
		json.addProperty("news", gson.toJson(newsData));
		return json.toString();
		
	}
	

}
