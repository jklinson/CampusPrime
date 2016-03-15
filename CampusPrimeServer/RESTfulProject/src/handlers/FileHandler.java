package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import models.FileObjects;

public class FileHandler {

	private String tableName	= "files"; 
	private String insertStmnt 	= "INSERT INTO "+tableName+" (`fileType`, `filePath`, `fileName`) VALUES ";
	public int savePath(FileObjects fileObjects,Connection connection) throws Exception
	{
		int value = -1;
		try
		{	
			String sql = insertStmnt +"("+ fileObjects.convertToString()+"')";
			System.out.println("sql "+sql);
			PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			value = getFileIdWithName(fileObjects, connection);
			return value;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	public int getFileIdWithName(FileObjects fileObjects, Connection connection) throws SQLException{
		
		int fileId = -1;
		String sql = "SELECT fileId from "+tableName+" where fileName = '"+fileObjects.getFileName()+"'";
		System.out.println("sql "+sql);
		PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = ps.executeQuery();		
		while(rs.next())
		{
			fileId = rs.getInt("fileId");
		}
		
		return fileId;
		
	}
	public FileObjects getFileWithId(int fileId, Connection connection) throws SQLException{
		
		FileObjects fileObject = new FileObjects();
		fileObject.setFilePath("/d/Linson/WorkSpace/WebApps/FileUpload/noImage.png");
		String sql = "SELECT filePath, fileName, fileType from "+tableName+" where fileId = "+fileId;
		System.out.println("sql "+sql);
		PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = ps.executeQuery();		
		while(rs.next())
		{
			fileObject.setFilePath(rs.getString("filePath"));
			fileObject.setFileName(rs.getString("fileName"));
			fileObject.setFileType(rs.getString("fileType"));
		}
		
		return fileObject;
		
	}
}
