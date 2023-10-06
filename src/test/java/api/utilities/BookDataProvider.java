package api.utilities;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookDataProvider 
{

	@DataProvider(name="bookData")
	public  String[][] getTokenData() throws Exception
	{

		int rowCount , colCount;
		String path = ReadProperties.readProperty("excelUtility_path");
		ExcelUtility excelFile = new ExcelUtility(path);
		
		rowCount = excelFile.getRowCount("Book");
		colCount = excelFile.getCellCount("Book", 1);
		
		String apiData[][]=new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) 
		{
			for(int j=0; j<colCount;j++)
			{
				apiData[i-1][j]=excelFile.getCellData("Book", i, j);
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
