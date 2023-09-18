package api.endpoints;

import api.utilities.*;

//in this class we will have only URLs

public class Routes 
{
	
	public static String base_url;
	
	
	static
	{
		base_url = ReadProperties.readProperty("petstoreGetUserUrl");
	}
	

	
//	public static String base_url = "https://petstore.swagger.io/v2";

	
	
	//user model 
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	
	//store model
	
	public static String findOrderByIdUrl = base_url+"/store/order/{id}";
	public static String findPetInventoryByStatusUrl = base_url+"/store/inventory";
	public static String placeOrderForPetUrl = base_url+"/store/order";
	public static String deleteOrderByIdUrl = base_url+"/store/order/{id}";
	
	
	
	// pet model
		
	public static String findPetByStatusUrl = base_url+"/pet/findByStatus";
	public static String findPetByIdUrl = base_url+"/pet/{petID}"; 
	
	// reqres model
	
	public static String reqresBseUrl = ReadProperties.readProperty("reqresBaseURL");
	
	
}
