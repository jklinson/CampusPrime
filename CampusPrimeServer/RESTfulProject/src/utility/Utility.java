package utility;

public class Utility {

	public static String getFileExtension(String name) {
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
}
