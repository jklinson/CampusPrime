package models;

import com.google.gson.JsonObject;

public class UsersObjects {

	private int userId;
	private String email;
	private String name;
	private String password;
	private long mobileNum;
	private String year;
	private String department;
	private String uniqueId;
	private String classOrSRoom;
	private boolean isActive;
	private boolean isTeacher;
	private boolean isEmailVerified;
	
	public UsersObjects(JsonObject jsonObject) {
		this.setEmail(jsonObject.has("email")?jsonObject.get("email").getAsString():"");
		this.setName(jsonObject.has("name")?jsonObject.get("name").getAsString():"");
		this.setPassword(jsonObject.has("password")?jsonObject.get("password").getAsString():"");
		this.setMobileNum(jsonObject.has("mobileNum")?jsonObject.get("mobileNum").getAsLong():0);
		this.setYear(jsonObject.has("year")?jsonObject.get("year").getAsString():"");
		this.setDepartment(jsonObject.has("department")?jsonObject.get("department").getAsString():"");
		this.setUniqueId(jsonObject.has("uniqueId")?jsonObject.get("uniqueId").getAsString():"");
		this.setClassOrSRoom(jsonObject.has("classOrSRoom")?jsonObject.get("classOrSRoom").getAsString():"");
		this.setActive(jsonObject.has("isActive")?jsonObject.get("isActive").getAsBoolean():false);
		this.setTeacher(jsonObject.has("isTeacher")?jsonObject.get("isTeacher").getAsBoolean():false);
		this.setEmailVerified(jsonObject.has("isEmailVerified")?jsonObject.get("isEmailVerified").getAsBoolean():false);
	}
	public UsersObjects() {
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the mobileNum
	 */
	public long getMobileNum() {
		return mobileNum;
	}
	/**
	 * @param mobileNum the mobileNum to set
	 */
	public void setMobileNum(long mobileNum) {
		this.mobileNum = mobileNum;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}
	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	/**
	 * @return the classOrSRoom
	 */
	public String getClassOrSRoom() {
		return classOrSRoom;
	}
	/**
	 * @param classOrSRoom the classOrSRoom to set
	 */
	public void setClassOrSRoom(String classOrSRoom) {
		this.classOrSRoom = classOrSRoom;
	}
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the isTeacher
	 */
	public boolean isTeacher() {
		return isTeacher;
	}
	/**
	 * @param isTeacher the isTeacher to set
	 */
	public void setTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
	}
	/**
	 * @return the isEmailVerified
	 */
	public boolean isEmailVerified() {
		return isEmailVerified;
	}
	/**
	 * @param isEmailVerified the isEmailVerified to set
	 */
	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
	
	public String convertToString() {
		
		return "'"+this.email+"', '"+ this.name+"', '"+ this.password+"', " +this.mobileNum+", '"+this.year+"', '"+this.department+"', '"+this.uniqueId+"', '"+this.classOrSRoom+"', "+this.isActive+", "+this.isTeacher+", "+this.isEmailVerified;
	}
	
	
	
}
