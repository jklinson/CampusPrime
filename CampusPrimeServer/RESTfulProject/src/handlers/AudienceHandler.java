package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import models.AudienceObject;
import models.FileObjects;
import utility.Constants;

public class AudienceHandler {
	
	private String tableName	= "target_audience"; 
	private String insertStmnt 	= "INSERT INTO "+tableName+" (`year`, `isTeacher`,'classNum') VALUES ";
	
	public ArrayList<AudienceObject> getYearAndClass(Connection connection) throws SQLException{
		
		ArrayList<AudienceObject> objectList = new ArrayList<AudienceObject>();
		String sql = "SELECT * FROM campus_prime.target_audience group by year, classNum";
		System.out.println("sql "+sql);
		PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = ps.executeQuery();		
		while(rs.next())
		{
			AudienceObject obj = new AudienceObject();
			obj.setYear(rs.getString("year"));
			obj.setClassNum(rs.getString("classNum"));
			obj.setTargetId(rs.getString("targetId"));
			obj.setIsTeacher(rs.getInt("isTeacher"));
			objectList.add(obj);
		}
		
		return objectList;
		
	}
	
	public static String createSelectQuerry(String year, String classNum, int isTeacher){
		return "(SELECT targetId FROM target_audience where isTeacher = "+isTeacher
				+ " and classNum = '"+classNum+"' and year = '"+year+"')";
	}
}
