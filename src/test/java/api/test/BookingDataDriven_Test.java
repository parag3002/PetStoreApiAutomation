package api.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;
import com.google.gson.JsonObject;

import dev.failsafe.internal.util.Assert;
import groovyjarjarpicocli.CommandLine.IExitCodeExceptionMapper;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl.HamcrestAssertionClosure;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;
import net.sf.saxon.exslt.Math;

import api.payload.*;
import api.endpoints.*;
import api.utilities.*;

public class BookingDataDriven_Test 
{
	
	BookingCreateTokenPojo bookingToken;
	BookingCreatePOJO createBookingPayload;
	BookingDates bookingDatespayload;
	String token;
	int thirdBookingId;
	int bookingId;
	public BookingDataDriven_Test()
	{
		
	}
	
//	@BeforeClass
//	public void setup()
//	{
//		createBookingPayload = new BookingCreatePOJO();
//		bookingDatespayload = new BookingDates();
//		
//		createBookingPayload.setFirstname("Tomas");
//		createBookingPayload.setLastname("Jones");
//		createBookingPayload.setTotalprice(970);
//		createBookingPayload.setDepositpaid(true);
//			bookingDatespayload.setCheckin("2023-01-01");
//			bookingDatespayload.setCheckout("2023-02-02");
//		createBookingPayload.setBookingdates(bookingDatespayload);
//		createBookingPayload.setAdditionalneeds("Lunch");
//	}
	
	
	
	@Test(priority=1 , dataProvider = "tokenData", dataProviderClass = BookingDataProvider.class)
	public void test_createToken(String userName, String password)
	{
		bookingToken = new BookingCreateTokenPojo();
		bookingToken.setUsername(userName);
		bookingToken.setPassword(password);
		
		Response response = BookingEndpoints.createToken(bookingToken);
		response.then().log().all();
		token = response.jsonPath().getString("token");
		System.out.println("toekn----->"+token);
	}
	
	
	@Test(priority=2)
	public void test_getBookingIds()
	{
		Response response = BookingEndpoints.getBookingIds();
		response.then().log().all();
		

		//String jsonString = "[{\"bookingid\": 2271}, {\"bookingid\": 1615}, {\"bookingid\": 18}]";

		// Parse the JSON string
		//JsonPath jsonPath = new JsonPath(jsonString);

		JsonPath jsonPath = response.jsonPath();
		
		// Use JSONPath to fetch the 3rd bookingid
		List<Integer> bookingIds = jsonPath.getList("bookingid");

		// Check if the list contains at least 3 elements
		if (bookingIds.size() >= 3) {
			thirdBookingId = bookingIds.get(2); // Index 2 corresponds to the 3rd element
			System.out.println("3rd Booking ID: " + thirdBookingId);
		} else {
			System.out.println("There are not enough booking IDs in the JSON array to fetch the 3rd one.");
		}

		
	}
	

	
	@Test(priority=3)
	public void getBookingById()
	{
		Response response = BookingEndpoints.getBookingById(thirdBookingId);
		response.then().log().all();
		assertEquals(response.statusCode(), 200);
	}
	
	

	
	
	@Test(priority=4, dataProvider = "bookingData", dataProviderClass = BookingDataProvider.class)
	public void createBooking(String firstname, String lastname, String totalprice, String depositpaid, String checkin, String checkout, String additionalneeds)
	{
		createBookingPayload = new BookingCreatePOJO();
		bookingDatespayload = new BookingDates();
		
		createBookingPayload.setFirstname(firstname);
		createBookingPayload.setLastname(lastname);
		createBookingPayload.setTotalprice(Integer.parseInt(totalprice));
		createBookingPayload.setDepositpaid(Boolean.parseBoolean(depositpaid));
		bookingDatespayload.setCheckin(checkin);
		bookingDatespayload.setCheckout(checkout);
		createBookingPayload.setBookingdates(bookingDatespayload);
		createBookingPayload.setAdditionalneeds(additionalneeds);
		
		Response response = BookingEndpoints.createBooking(createBookingPayload);
		response.then().log().all();
		assertEquals(response.statusCode(), 200);
		bookingId = response.jsonPath().getInt("bookingid");
		System.out.println("Booking created for ID --> "+bookingId);
	}
	
	
	
	@Test(priority=5, dataProvider = "bookingData", dataProviderClass = BookingDataProvider.class)
	public void updateBooking(String firstname, String lastname, String totalprice, String depositpaid, String checkin, String checkout, String additionalneeds)
	{
		createBookingPayload = new BookingCreatePOJO();
		bookingDatespayload = new BookingDates();
		
		createBookingPayload.setFirstname(firstname+"Update1");
		createBookingPayload.setLastname(lastname);
		createBookingPayload.setTotalprice(Integer.parseInt(totalprice));
		createBookingPayload.setDepositpaid(Boolean.parseBoolean(depositpaid));
		bookingDatespayload.setCheckin(checkin);
		bookingDatespayload.setCheckout(checkout);
		createBookingPayload.setBookingdates(bookingDatespayload);
		createBookingPayload.setAdditionalneeds(additionalneeds+"Sleep");
		
		Response response = BookingEndpoints.updateBooking(createBookingPayload,bookingId);
		response.then().log().all();
		assertEquals(response.statusCode(), 200);
		//bookingId = response.jsonPath().getInt("bookingid");
		//System.out.println("Booking created for ID --> "+bookingId);
	}
	
	
	@Test(priority=6)
	public void deleteBooking()
	{
		Response response = BookingEndpoints.deleteBooking(bookingId);
		assertEquals(response.statusCode(), 201);
		System.out.println(">>>>>>>>>Deleted Succcessfully---------------->"+bookingId);
	}
	
	
	@Test(priority=7)
	public void healthCheck()
	{
		Response response = BookingEndpoints.healthCheck();
		response.then().log().all();
		assertEquals(response.statusCode(),201);
//		String responseBody = response.then().log().body().toString();
//		System.out.println("--------------> Healthcheck OK <---------"+responseBody);
		
	}
	
	
	
}
