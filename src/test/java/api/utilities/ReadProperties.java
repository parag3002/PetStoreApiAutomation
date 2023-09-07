package api.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties 
{

	private static String fileData;
	
	public static String readProperty(String s)
	{
		try
		{
//		FileInputStream propertyFile = new FileInputStream("C:\\E\\workspaces\\workSpace_RestAssured_P1\\PetStoreApiAutomation\\src\\test\\resources\\routs.properties");
		FileInputStream propertyFile = new FileInputStream(".\\src\\test\\resources\\routs.properties");
			
		Properties property = new Properties();
		property.load(propertyFile);
		fileData = property.getProperty(s);
		
		}
		catch(Exception e) {e.printStackTrace();}
		
		return fileData.toString();
		
	}

	
	//For debug
//	public static void main(String args[])
//	{
//		System.out.println(readProperty("petstoreGetUserUrl"));
//	}
}
