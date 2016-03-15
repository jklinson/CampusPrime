package models;

import java.util.Date;

import com.google.gson.JsonObject;

import utility.Utility;

public class FileObjects {

	private int fileId;
	private String fileName;
	private String filePath;
	private String fileType;
	
	
	public FileObjects(JsonObject jsonObject) {
	}
	public FileObjects() {
	}
	public FileObjects(String fileName) {
		this.fileName	= fileName;
		this.fileType	= Utility.getFileExtension(this.fileName);
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
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String convertToString(){
		return "'"+this.fileType+"', '"+ this.filePath+"', '"+ this.fileName;
	}
	
	
}
