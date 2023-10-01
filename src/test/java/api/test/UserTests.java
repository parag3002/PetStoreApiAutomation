package api.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;
import com.google.gson.JsonObject;

import dev.failsafe.internal.util.Assert;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
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
import api.utilities.Data;
import api.endpoints.*;

public class UserTests 
{

	UserPojo userPayLoad;
	
	UserTests()
	{
		userPayLoad = new UserPojo(); // now after seting all data this variable have all data
		
	}
	
	
	public Logger logger;
	
	
	@BeforeClass
	public void setupData()
	{
        
//		userPayLoad.setId(fake.idNumber().hashCode());
        userPayLoad.setId(Data.randomIntGenerator());
		userPayLoad.setUsername(Data.fakeData().name().username());
		userPayLoad.setFirstName(Data.fakeData().name().firstName());
		userPayLoad.setLastName(Data.fakeData().name().lastName());
		userPayLoad.setEmail(Data.fakeData().internet().safeEmailAddress());
		userPayLoad.setPassword(Data.fakeData().internet().password());
		userPayLoad.setPhone(Data.fakeData().phoneNumber().cellPhone());
		userPayLoad.setUserStatus(Data.fakeData().options().option(1,0,2,3));
		
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
		
		System.out.println("Created user----->"+userPayLoad.getUsername());
		
		
		logger.debug("debugging.....");
		
	}
	
	
	
	
	
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*********************POST || Initiating Create User*******************************");
		System.out.println("POST | Created user ----->");
		Response response = UserEndPoints.createUser(userPayLoad);
		response.then().log().all();
		assertEquals(response.getStatusCode(),200);
		logger.info("*********************POST || Create User Success*******************************");

		
	}
	
	
	
	
	
	
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		System.out.println("pojo user name--->"+userPayLoad.getUsername());
		System.out.println("GET | Get User --->");
		Response response = UserEndPoints.readUser(this.userPayLoad.getUsername());
		
		response.then().log().all();
		assertEquals(response.getStatusCode(),200);
	}
	
	
	
	
	
	
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{

		userPayLoad.setFirstName(Data.fakeData().name().firstName());
		userPayLoad.setLastName(Data.fakeData().name().lastName());
		userPayLoad.setEmail(Data.fakeData().internet().safeEmailAddress());
		userPayLoad.setPassword(Data.fakeData().internet().password());
		userPayLoad.setPhone(Data.fakeData().phoneNumber().cellPhone());
		userPayLoad.setUserStatus(Data.fakeData().options().option(1,0,2,3));
		
//		System.out.println("Updated user----->"+userPayLoad.getUsername());
		System.out.println("PUT | Update User ---->");
		
		Response response = UserEndPoints.updateUser(userPayLoad, userPayLoad.getUsername());
		
		assertEquals(response.getStatusCode(),200);
		response.then().log().all();
		
		System.out.println("UPDATED user -------->");
		
		// validating response after update is done ---
		
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayLoad.getUsername());
		
		responseAfterUpdate.then().log().all();
		assertEquals(responseAfterUpdate.getStatusCode(),200);
		
		
	}
	
	
	
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		System.out.println("DELETE | Delete User ----> ");
		Response response = UserEndPoints.deleteUser(userPayLoad.getUsername());
		
		response.then().log().body().statusCode(200);
		//response.then().log().all();
		System.out.println("Deletion Success for user---->"+userPayLoad.getUsername());
	
		
		//Validating after deletion that user is not found ---
		
		Response responseAfterDelete = UserEndPoints.readUser(this.userPayLoad.getUsername());
		System.out.println("Validating that user has been DELETED successfully--->"+this.userPayLoad.getUsername());
		responseAfterDelete.then().log().body().statusCode(404);
		responseAfterDelete.then().log().all();
	
	}
}






//"id": 0,
//"username": "string",
//"firstName": "string",
//"lastName": "string",
//"email": "string",
//"password": "string",
//"phone": "string",
//"userStatus": 0