package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders 
{

	@DataProvider(name="AllData")
	public String[][] getAllData() throws Exception
	{

		int rowCount , colCount;
//		String path = ".\\src\\test\\resources\\Userdata.xlsx";
		String path = ReadProperties.readProperty("excelUtility_path");
		ExcelUtility excelFile = new ExcelUtility(path);
		
		rowCount = excelFile.getRowCount("data1");
		colCount = excelFile.getCellCount("data1", 1);
		
		String apiData[][]=new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) 
		{
			for(int j=0; j<colCount;j++)
			{
				apiData[i-1][j]=excelFile.getCellData("data1", i, j);
			}
		}
		
//////////////////////Additional part starts
		System.out.println("");
		System.out.println("================PRINTING ALL DATA FROM EXCEL============================");
		System.out.println(rowCount+"<--row-|-col-->"+colCount);
		
		for(int i=1;i<=rowCount;i++) 
		{
			for(int j=0; j<colCount;j++)
			{
				System.out.print(apiData[i-1][j]+" | ");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("=====================END=======================");
//////////////////////Additional part end
		
		return apiData;
		
	}

/////////////for debug purpose
	public static void main(String args[]) throws Exception
	{
		DataProviders r = new DataProviders();
		r.getAllData();
		r.getUserName();
	}
	
//-----------------------------------------
	
	
	@DataProvider(name="UserNamesData")
	public String[] getUserName() throws Exception
	{
		String path = ReadProperties.readProperty("excelUtility_path");
		ExcelUtility excelFile = new ExcelUtility(path);
		int rowCount = excelFile.getRowCount("data1");
		String apiData[]= new String[rowCount];
		
		for(int i=1;i<=rowCount;i++)
		{
			apiData[i-1] = excelFile.getCellData("data1", i, 1);
					
		}
//////////////////////Additional part starts
		System.out.println("");
		System.out.println("================PRINTING User Name Only from Excel============================");
		for(int i=0;i<rowCount;i++)
		{
			System.out.println(apiData[i]);
					
		}
		System.out.println("");
		System.out.println("===========================END=================");
//////////////////////Additional part end	
		
		return apiData;
	}
	
	
}
