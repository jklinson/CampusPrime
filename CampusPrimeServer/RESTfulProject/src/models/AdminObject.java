package models;

import com.google.gson.JsonObject;

public class AdminObject {
	
	private int adminId;
	private int userId;
	private int audienceId;
	private String adminYear;
	private String adminClass;
	
	public AdminObject(){
		
	}
	public AdminObject(JsonObject json){
		this.setAdminId(json.has("adminId")? json.get("adminId").getAsInt():-1);
		this.setUserId(json.has("userId")? json.get("userId").getAsInt():-1);
		this.setAudienceId(json.has("adminTargetId")? json.get("adminTargetId").getAsInt():-1);
		this.setAdminYear(json.has("adminOfYear")? json.get("adminOfYear").getAsString():"");
		this.setAdminClass(json.has("adminOfClass")? json.get("adminOfClass").getAsString():"");
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAudienceId() {
		return audienceId;
	}
	public void setAudienceId(int audienceId) {
		this.audienceId = audienceId;
	}
	public String getAdminYear() {
		return adminYear;
	}
	public void setAdminYear(String adminYear) {
		this.adminYear = adminYear;
	}
	public String getAdminClass() {
		return adminClass;
	}
	public void setAdminClass(String adminClass) {
		this.adminClass = adminClass;
	}
	public String convertToString() {
			//userId, class, year, audienceId
		return this.userId + ", '"+this.adminClass+"', '"+this.adminYear+"', "+this.audienceId;
	}
	
	
 }
