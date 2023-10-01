package api.utilities;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookingDataProvider 
{

	@DataProvider(name="tokenData")
	public  String[][] getTokenData() throws Exception
	{

		int rowCount , colCount;
		String path = ReadProperties.readProperty("excelUtility_path");
		ExcelUtility excelFile = new ExcelUtility(path);
		
		rowCount = excelFile.getRowCount("booking");
		colCount = excelFile.getCellCount("booking", 1);
		
		String apiData[][]=new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) 
		{
			for(int j=0; j<colCount;j++)
			{
				apiData[i-1][j]=excelFile.getCellData("booking", i, j);
			}
		}
		
		for(int i=1;i<=rowCount;i++) 
		{
			for(int j=0; j<colCount;j++)
			{
				System.out.print(apiData[i-1][j]+" | ");
			}
			System.out.println("");
		}
		System.out.println("");
		
		return apiData;
		
	}

	
	
	
	@DataProvider(name="bookingData")
	public  String[][] postBookingData() throws Exception
	{

		int rowCount , colCount;
		String path = ReadProperties.readProperty("excelUtility_path");
		ExcelUtility excelFile = new ExcelUtility(path);
		
		rowCount = excelFile.getRowCount("booking_data");
		colCount = excelFile.getCellCount("booking_data", 1);
		
		String apiData[][]=new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) 
		{
			for(int j=0; j<colCount;j++)
			{
				apiData[i-1][j]=excelFile.getCellData("booking_data", i, j);
			}
		}
		
		for(int i=1;i<=rowCount;i++) 
		{
			for(int j=0; j<colCount;j++)
			{
				System.out.print(apiData[i-1][j]+" | ");
			}
			System.out.println("");
		}
		System.out.println("");
		
		return apiData;
		
	}
	
	
	

	
	

	
}
