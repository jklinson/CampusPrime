package models;

import com.google.gson.JsonObject;

public class WriteUpObjects {

	private int writeUpId;
	private String title;
	private String description;
	private int publishedBy;
	private String publishedDate;
	private int audienceId;
	private int fileId;
	private int isApproved;
	private String type;
	
	
	
	
	

	public WriteUpObjects(JsonObject json) {
		
		this.setWriteUpId(json.has("writeUpId")? json.get("writeUpId").getAsInt():-1);
		this.setTitle(json.has("title")? json.get("title").getAsString():"");
		this.setDescription(json.has("description")? json.get("description").getAsString():"");
		this.setPublishedBy(json.has("publishedBy")? json.get("publishedBy").getAsInt():-1);
		this.setPublishedDate(json.has("publishedDate")? json.get("publishedDate").getAsString():"");
		this.setAudienceId(json.has("audienceId")? json.get("audienceId").getAsInt():-1);
		this.setFileId(json.has("fileId")? json.get("fileId").getAsInt():-1);
		this.setIsApproved(json.has("isApproved")? json.get("isApproved").getAsInt():-1);
		this.setType(json.has("type")? json.get("type").getAsString():"");
		
	}
	public WriteUpObjects() {
	}
	/**
	 * @return the id
	 */
	public int getWriteUpId() {
		return writeUpId;
	}
	/**
	 * @param id the id to set
	 */
	public void setWriteUpId(int id) {
		this.writeUpId = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the publishedBy
	 */
	public int getPublishedBy() {
		return publishedBy;
	}
	/**
	 * @param i the publishedBy to set
	 */
	public void setPublishedBy(int i) {
		this.publishedBy = i;
	}
	/**
	 * @return the publishedDate
	 */
	public String getPublishedDate() {
		return publishedDate;
	}
	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	/**
	 * @return the audienceId
	 */
	public int getAudienceId() {
		return audienceId;
	}
	/**
	 * @param audienceId the audienceId to set
	 */
	public void setAudienceId(int audienceId) {
		this.audienceId = audienceId;
	}
	/**
	 * @return the fileId
	 */
	public int getFileId() {
		return fileId;
	}
	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	/**
	 * @return the isApproved
	 */
	public int getIsApproved() {
		return isApproved;
	}
	/**
	 * @param isApproved the isApproved to set
	 */
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public String convertToString() {
		return "'"+this.title+"', '"+ this.description+"', "+ this.publishedBy+", '" +this.publishedDate+"', "+this.audienceId+", "+this.fileId+", "+this.isApproved+" ,'"+this.type+"'";
	}

	
}
