package models;

import com.google.gson.JsonObject;

public class ClapObject {
	
	private int clapId;
	private int parentId;
	private int userId;
	private int count;
	
	public ClapObject(){
		
	}
	
	public ClapObject(JsonObject json){
		
		//this.clapId = json.has("clapId")? json.get("clapId").getAsInt():null;
		this.parentId = json.has("parentId")? json.get("parentId").getAsInt():null;
		this.userId = json.has("userId")? json.get("userId").getAsInt():null;
		this.count = json.has("count")? json.get("count").getAsInt():null;
	}
	public int getClapId() {
		return clapId;
	}
	public void setClapId(int clapId) {
		this.clapId = clapId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String convertToString(){
		return this.count+", "+this.parentId+", "+this.userId;
	}
	
	
}
