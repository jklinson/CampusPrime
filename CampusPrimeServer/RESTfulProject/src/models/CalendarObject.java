package models;

import com.google.gson.JsonObject;

public class CalendarObject {

	private int calendarId;
	private String title;
	private String description;
	private int publishedBy;
	private String publishedUser;
	private String publishedDate;
	private int audienceId;
	private int isApproved;
	private String startsAt;
	private String endsAt;
	private String type;
	
	public CalendarObject(JsonObject json) {
		
		this.setCalendarId(json.has("calendarId")? json.get("calendarId").getAsInt():-1);
		this.setTitle(json.has("title")? json.get("title").getAsString():"");
		this.setDescription(json.has("description")? json.get("description").getAsString():"");
		this.setPublishedBy(json.has("publishedBy")? json.get("publishedBy").getAsInt():-1);
		this.setPublishedDate(json.has("publishedDate")? json.get("publishedDate").getAsString():"");
		this.setStartsAt(json.has("startsAt")? json.get("startsAt").getAsString():"");
		this.setEndsAt(json.has("endsAt")? json.get("endsAt").getAsString():"");
		this.setType(json.has("type")? json.get("type").getAsString():"");
		this.setAudienceId(json.has("audienceId")? json.get("audienceId").getAsInt():-1);
		this.setIsApproved(json.has("isApproved")? json.get("isApproved").getAsInt():-1);
		
	}
	public CalendarObject() {
	}
	/**
	 * @return the calendarId
	 */
	public int getCalendarId() {
		return calendarId;
	}
	/**
	 * @param calendarId the calendarId to set
	 */
	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
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
	
	public String getPublishedUser() {
		return publishedUser;
	}
	public void setPublishedUser(String publishedUser) {
		this.publishedUser = publishedUser;
	}
	/**
	 * @return the startsAt
	 */
	public String getStartsAt() {
		return startsAt;
	}
	/**
	 * @param startsAt the startsAt to set
	 */
	public void setStartsAt(String startsAt) {
		this.startsAt = startsAt;
	}
	/**
	 * @return the endsAt
	 */
	public String getEndsAt() {
		return endsAt;
	}
	/**
	 * @param endsAt the endsAt to set
	 */
	public void setEndsAt(String endsAt) {
		this.endsAt = endsAt;
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
		return "'"+this.title+"', '"+ this.description+"', "+ this.publishedBy+", '" +this.publishedDate+"', '"+this.startsAt+"', '"+this.endsAt+"', '"+this.type+"', "+this.audienceId+", "+this.isApproved;
	}

	
}
