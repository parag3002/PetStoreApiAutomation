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
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;
import net.sf.saxon.exslt.Math;

import api.payload.*;
import api.endpoints.*;
import api.utilities.*;

public class DataDrivenTest 
{
	
	@Test(priority = 1, dataProvider = "AllData" , dataProviderClass = DataProviders.class)
	public void dataDrivenPostUser(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus)
	{
		System.out.println("POST | Created user ----->");
		UserPojo payLoad = new UserPojo(); // creating pojo class object
		
		payLoad.setId(Integer.parseInt(id));
		payLoad.setUsername(username);
		payLoad.setFirstName(firstName);
		payLoad.setLastName(lastName);
		payLoad.setEmail(email);
		payLoad.setPassword(password);
		payLoad.setPhone(phone);
		payLoad.setUserStatus(Integer.parseInt(userStatus));
		
		Response response = UserEndPoints.createUser(payLoad);
		response.then().log().all();
		assertEquals(response.getStatusCode(),200);
	}
	
	
	@Test(priority = 2, dataProvider = "UserNamesData" , dataProviderClass = DataProviders.class)
	public void testGetUserByName(String username)
	{
		System.out.println("GET | Get User --->");
		UserPojo payLoad = new UserPojo(); // creating pojo class object
		
		payLoad.setUsername(username);
	
		Response response = UserEndPoints.readUser(payLoad.getUsername());
		
		response.then().log().all();
		assertEquals(response.getStatusCode(),200);
	}
	
	
//	@Test
//	public void dataDrivenDeleteByname()
//	{
//		System.out.println("DELETE | Delete User ----> ");
//		Response response = UserEndPoints.deleteUser(userPayLoad.getUsername());
//		
//		response.then().log().body().statusCode(200);
//		//response.then().log().all();
//		System.out.println("Deletion Success for user---->"+userPayLoad.getUsername());
//	
//		
//		//Validating after deletion that user is not found ---
//		
//		Response responseAfterDelete = UserEndPoints.readUser(this.userPayLoad.getUsername());
//		System.out.println("Validating that user has been DELETED successfully--->"+this.userPayLoad.getUsername());
//		responseAfterDelete.then().log().body().statusCode(404);
//		responseAfterDelete.then().log().all();
//	}
}







//"id": 0,
//"username": "string",
//"firstName": "string",
//"lastName": "string",
//"email": "string",
//"password": "string",
//"phone": "string",
//"userStatus": 0


