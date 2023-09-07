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

public class UserTests 
{

	Faker fake;
	UserPojo userPayLoad;
	
	@BeforeClass
	public void setupData()
	{
		fake = new Faker();
		userPayLoad = new UserPojo(); // now after seting all data this variable have all data
		
		userPayLoad.setId(fake.idNumber().hashCode());
		userPayLoad.setUsername(fake.name().username());
		userPayLoad.setFirstName(fake.name().firstName());
		userPayLoad.setLastName(fake.name().lastName());
		userPayLoad.setEmail(fake.internet().safeEmailAddress());
		userPayLoad.setPassword(fake.internet().password());
		userPayLoad.setPhone(fake.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		Response response = UserEndPoints.createUser(userPayLoad);
		response.then().log().all();
		assertEquals(response.getStatusCode(),200);
		
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