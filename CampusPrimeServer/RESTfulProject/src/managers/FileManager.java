package managers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Date;

import com.google.gson.JsonObject;

import connector.Database;
import factory.JsonFactory;
import handlers.FileHandler;
import models.FileObjects;
import utility.Constants;

public class FileManager {

	
	// save uploaded file to new location
		public JsonObject saveFile(InputStream uploadedInputStream,
				FileObjects fileObject) throws Throwable {
				Date date = new Date();
				String uploadedFileLocation = Constants.FILE_UPLOAP_PATH + date.getTime()+"_"+fileObject.getFileName();
			
				OutputStream out = new FileOutputStream(new File(
						uploadedFileLocation));
				int read = 0;
				byte[] bytes = new byte[1024];

				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
				fileObject.setFilePath(uploadedFileLocation);
				return saveFilePath(fileObject);
		}
		
		// save file location into db
		public JsonObject saveFilePath(FileObjects fileObject) throws Exception{
			JsonObject jsonObject = new JsonObject();
	    	Database database= new Database();
			Connection connection = database.Get_Connection();
			FileHandler fileHandler = new FileHandler();
			int id =fileHandler.savePath(fileObject, connection);
			jsonObject = JsonFactory.fileUploadSuccessJSON(jsonObject, id);
			return jsonObject;
			
		}
		
		public FileObjects getFile(int fileId) throws Exception{
	    	Database database= new Database();
			Connection connection = database.Get_Connection();
			FileHandler fileHandler = new FileHandler();
			FileObjects file =fileHandler.getFileWithId(fileId, connection);
			return file;
		}
}
