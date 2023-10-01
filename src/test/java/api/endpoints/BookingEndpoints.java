package api.endpoints;

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
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;
import net.sf.saxon.exslt.Math;

import api.payload.*;
import api.utilities.ReadProperties;

public class BookingEndpoints 
{
	private static String authorizationHeader = ReadProperties.readProperty("authorizationHeader");
	
	public static Response createToken(BookingCreateTokenPojo payload)
	{
		Response response =
				given()
					.contentType(ContentType.JSON)
					.body(payload)
				.when()
					.post(Routes.bookingCreateTokenUrl);
		
		return response;
	}
	
	
	public static Response getBookingIds() 
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
				.when()
					.get(Routes.getBookingIdsUrl);
		return response;
	}
	
	
	public static Response getBookingById(int booking_id)
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.pathParam("booking_id",booking_id)
				.when()
					.get(Routes.getBookingByIdUrl);
		return response;
	}
	
	public static Response createBooking(BookingCreatePOJO payload)
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.body(payload)
				.when()
					.post(Routes.createBookingUrl);
		
		return response;
	}
	
	
	
	public static Response updateBooking(BookingCreatePOJO payload, int bookingID)
	{
		//String authToken = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.body(payload)
					.header("Authorization",authorizationHeader)
					.pathParam("booking_id", bookingID)
					
				.when()
					.put(Routes.updateBookingByIdUrl);
		
		return response;
	}
	
	public static Response deleteBooking(int bookingID)
	{
		Response response =
				given()
					.header("Authorization",authorizationHeader)
					.contentType(ContentType.JSON)
					.pathParam("booking_id", bookingID)
				.when()
					.delete(Routes.deleteBookingByIdUrl);
		
		return response;
	}
	
	
	public static Response healthCheck()
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
				.when()
					.get(Routes.bookingHealthCheckUrl);
		
		return response;
	}
	
}
