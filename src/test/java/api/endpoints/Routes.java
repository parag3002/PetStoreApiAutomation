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
	
	
	// https://api.instantwebtools.net/v1/airlines/:id
	
	public static String instantWebToolsBaseUri=ReadProperties.readProperty("instantWebToolsBaseUri");
	public static String readAirlineById=instantWebToolsBaseUri+"/airlines/{id}";
	public static String createPpassanger=instantWebToolsBaseUri+"/passenger";
	public static String createAirlineByData=instantWebToolsBaseUri+"/airlines";
	
	
	//https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-UpdateBooking
	
	public static String bookingBaseUrl = ReadProperties.readProperty("bookingBaseUrl");
	public static String bookingCreateTokenUrl = bookingBaseUrl + "/auth";
	public static String getBookingIdsUrl = bookingBaseUrl + "/booking";
	public static String getBookingByIdUrl = bookingBaseUrl + "/booking/{booking_id}";
	public static String createBookingUrl = bookingBaseUrl + "/booking";
	public static String updateBookingByIdUrl = bookingBaseUrl + "/booking/{booking_id}";
	public static String deleteBookingByIdUrl = bookingBaseUrl + "/booking/{booking_id}";
	public static String bookingHealthCheckUrl = bookingBaseUrl + "/ping";
	
	
	
}
