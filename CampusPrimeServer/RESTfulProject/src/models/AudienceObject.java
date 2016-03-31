package models;

public class AudienceObject {

	private String targetId;
	private String year;
	private String department;
	private int isTeacher;
	private String classNum;
	
	public AudienceObject(){
		
	}
	public AudienceObject(String year, int isTeacher, String classNum){
		this.year = year;
		this.isTeacher = isTeacher;
		this.classNum = classNum;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getIsTeacher() {
		return isTeacher;
	}
	public void setIsTeacher(int isTeacher) {
		this.isTeacher = isTeacher;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	public String convertToString(){
		return "'"+this.year+"', '"+ this.department+"', '"+this.isTeacher+"', '"+ this.classNum;
	}
}
