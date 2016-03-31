package webService;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import utility.Constants;
import utility.SendEmail;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import factory.JsonFactory;
import managers.AudienceManager;
import managers.CalendarManager;
import managers.FileManager;
import managers.NewsManager;
import managers.NotificationManager;
import managers.UserManager;
import managers.WriteUpManager;
import models.CalendarObject;
import models.FileObjects;
import models.NewsObjects;
import models.NotificationObjects;
import models.UsersObjects;
import models.WriteUpObjects;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/WebService")
public class CampusPrimeService {
	
	
	@GET
	@Path("/GetUsers")
	@Produces("application/json")
	public String user()
	{
		String userDetails  = null;
		try 
		{
			ArrayList<UsersObjects> userData = null;
			UserManager userManager= new UserManager();
			userData = userManager.GetUser();
			Gson gson = new Gson();
			System.out.println(gson.toJson(userData));
			userDetails = gson.toJson(userData);

		} catch (Exception e)
		{
			System.out.println("error in Users "+ e);
		}
		return userDetails;
	}
	
	
	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerUser(final String userDetails)
	{
		JsonObject response  = new JsonObject();
		System.out.println("registerUser 1. "+userDetails);
		try 
		{
			UserManager userManager= new UserManager();
			UsersObjects usersObjects = userManager.registerUser(userDetails);
			response.addProperty(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
			response.addProperty(Constants.MESSAGE_KEY, "Succesfully registered. Please wait for the approval from admin.");
			response.addProperty("activateLink", Constants.MAIL_LINK+usersObjects.getUserId());
			System.out.println(response);

		} catch (Exception e)
		{
			System.out.println("error in Users "+ e);
			response.addProperty(Constants.STATUS_KEY, Constants.STATUS_FAILURE);
			response.addProperty(Constants.MESSAGE_KEY, e.getMessage());
		}
		return response.toString();
	}
	
	@Path("/checkLogin")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String checkLogin(final String userDetails)
	{
		JsonObject response  = new JsonObject();
		System.out.println("checkLogin 1. "+userDetails);
		try 
		{
			UserManager userManager= new UserManager();
			response = userManager.checkLogin(userDetails);
			
			System.out.println(response);

		} catch (Exception e)
		{
			System.out.println("error in Users "+ e);
			response.addProperty(Constants.STATUS_KEY, Constants.STATUS_FAILURE);
			response.addProperty(Constants.MESSAGE_KEY, e.getMessage());
		}
		return response.toString();
	}
	@Path("/verifyEmail/{userId}")
	public String verifyEmail(@PathParam("userId") int userId)
	{
		System.out.println("activate 1. "+userId);
		String response = "Succesfully verified the email of the user. You can continue with the using of Campus Prime.";
		try 
		{
			UserManager userManager= new UserManager();
			userManager.verifyEmail(userId);
			
			System.out.println(response);

		} catch (Exception e)
		{
			System.out.println("error in Users "+ e);
			response = "Unable to verify the user email address.";
		}
		return response;
	}
	@GET
    @Path("/download/{fileId}")
    public Response downloadFile(@PathParam("fileId") int fileId) {
 
        // set file (and path) to be download
		System.out.println("fileID = "+fileId);
		FileManager fileManager = new FileManager();
		String fileName = "noImage.jpg";
		String filePath = Constants.FILE_UPLOAP_PATH+"noImage.jpg";
		try {
			FileObjects fileObject  = fileManager.getFile(fileId);
			fileName = fileObject.getFileName();
			filePath = fileObject.getFilePath();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(filePath);
		System.out.println(fileName);
        File file = new File(filePath);
 
        ResponseBuilder responseBuilder = Response.ok((Object) file);
        responseBuilder.header("Content-Disposition", "attachment; filename=\""+fileName+"\"");
        return responseBuilder.build();
    }
	@POST
	@Path("/uploadFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		
		JsonObject response  = new JsonObject();
		// save it
		FileManager fileManager = new FileManager();
		try {
			FileObjects fileObject = new FileObjects(fileDetail.getFileName());
			response = fileManager.saveFile(uploadedInputStream,fileObject);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = JsonFactory.createFailureJson(response, "Unable to upload the file, Please try again later. \nDetails : "+e.getMessage());
		}

		return response.toString();

	}
	
	@GET
	@Path("/GetNews")
	@Produces("application/json")
	public String News()
	{
		String newsDetails  = null;
		try 
		{
			ArrayList<NewsObjects> newsData = null;
			NewsManager newsrManager= new NewsManager();
			newsData = newsrManager.GetNews();
			newsDetails = JsonFactory.createNewsArray(newsData);

		} catch (Exception e)
		{
			System.out.println("error in News "+ e);
		}
		return newsDetails;
	}
	@GET
	@Path("/GetWriteUps")
	@Produces("application/json")
	public String WriteUps()
	{
		String writeUpDetails  = null;
		try 
		{
			ArrayList<WriteUpObjects> writeUpData = null;
			WriteUpManager writeUpManager= new WriteUpManager();
			writeUpData = writeUpManager.GetWriteUps();
			writeUpDetails = JsonFactory.createWriteUpArray(writeUpData);

		} catch (Exception e)
		{
			System.out.println("error in Writeups "+ e);
		}
		return writeUpDetails;
	}
	@GET
	@Path("/GetNotifications")
	@Produces("application/json")
	public String Notifications()
	{
		String notificationDetails  = null;
		try 
		{
			ArrayList<NotificationObjects> notificationData = null;
			NotificationManager notificationManager= new NotificationManager();
			notificationData = notificationManager.GetNotifications();
			notificationDetails = JsonFactory.createNotificationArray(notificationData);

		} catch (Exception e)
		{
			System.out.println("error in Notifications "+ e);
		}
		return notificationDetails;
	}
	@GET
	@Path("/GetCalendarEvents")
	@Produces("application/json")
	public String GetCalendarEvents()
	{
		String calendarDetails  = null;
		try 
		{
			ArrayList<CalendarObject> calendarData = null;
			CalendarManager calendarManager= new CalendarManager();
			calendarData = calendarManager.GetCalendarEvents();
			calendarDetails = JsonFactory.createCalendarEventsArray(calendarData);

		} catch (Exception e)
		{
			System.out.println("error in News "+ e);
		}
		return calendarDetails;
	}
	@Path("/saveNews")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveNews(final String newsDetails)
	{
		JsonObject response  = new JsonObject();
		try 
		{
			NewsManager newsManager= new NewsManager();
			boolean status = newsManager.saveNews(newsDetails);
			if(status){
				response.addProperty(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
				response.addProperty(Constants.MESSAGE_KEY, "Your news have been uploaded to CampusPrime.");
			}
			System.out.println(response);

		} catch (Exception e)
		{
			System.out.println("error in saveNews "+ e);
			response.addProperty(Constants.STATUS_KEY, Constants.STATUS_FAILURE);
			response.addProperty(Constants.MESSAGE_KEY, e.getMessage());
		}
		return response.toString();
	}
	@Path("/saveWriteUps")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveWriteUps(final String writeUpsDetails)
	{
		JsonObject response  = new JsonObject();
		try 
		{
			WriteUpManager writeUpManager= new WriteUpManager();
			boolean status = writeUpManager.saveWriteUps(writeUpsDetails);
			if(status){
				response.addProperty(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
				response.addProperty(Constants.MESSAGE_KEY, "Your write up have been uploaded to CampusPrime.");
			}
			System.out.println(response);

		} catch (Exception e)
		{
			System.out.println("error in saveNews "+ e);
			response.addProperty(Constants.STATUS_KEY, Constants.STATUS_FAILURE);
			response.addProperty(Constants.MESSAGE_KEY, e.getMessage());
		}
		return response.toString();
	}
	
	@Path("/saveNotifications")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveNotifications(final String notificationDetails)
	{
		JsonObject response  = new JsonObject();
		try 
		{
			NotificationManager notificationManager= new NotificationManager();
			boolean status = notificationManager.saveNotifications(notificationDetails);
			if(status){
				response.addProperty(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
				response.addProperty(Constants.MESSAGE_KEY, "Your notification have been uploaded to CampusPrime.");
			}
			System.out.println(response);

		} catch (Exception e)
		{
			System.out.println("error in saveNews "+ e);
			response.addProperty(Constants.STATUS_KEY, Constants.STATUS_FAILURE);
			response.addProperty(Constants.MESSAGE_KEY, e.getMessage());
		}
		return response.toString();
	}
	@Path("/saveCalendarEvents")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveCalendarEvents(final String calendarDetails)
	{
		JsonObject response  = new JsonObject();
		try 
		{
			CalendarManager calendarMgr= new CalendarManager();
			boolean status = calendarMgr.saveNews(calendarDetails);
			if(status){
				response.addProperty(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
				response.addProperty(Constants.MESSAGE_KEY, "Your event have been saved to CampusPrime.");
			}
			System.out.println(response);

		} catch (Exception e)
		{
			System.out.println("error in save calendar events "+ e);
			response.addProperty(Constants.STATUS_KEY, Constants.STATUS_FAILURE);
			response.addProperty(Constants.MESSAGE_KEY, e.getMessage());
		}
		return response.toString();
	}
	
	@GET
	@Path("/GetYearAndClass")
	@Produces("application/json")
	public String getYearAndClass()
	{
		AudienceManager manager = new AudienceManager();
		JsonObject response = manager.getYearAndClass();
		return response.toString();
	}

}
