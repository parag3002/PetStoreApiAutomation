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

//purpose -- Create | Read | Update | Delete requests in User API

public class UserEndPoints 
{
	
	public static Response createUser(UserPojo payload)
	{
		
		Response response = 
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(Routes.post_url);
		return response;
		
	}
	
	
	
	public static Response readUser(String userName)
	{
		Response response = 
				
				given()
					.pathParam("username", userName)
					
				.when()
					.get(Routes.get_url);
				
		return response;
	}
	
	
	
	
	public static Response updateUser(UserPojo payload,String userName)
	{
		
		Response response = 
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username",userName)
			
		.when()
			.put(Routes.update_url);
			
		return response;
		
	}
	
	
	
	public static Response deleteUser(String userName)
	{
		Response response = 
				
				given()
					.pathParam("username", userName)
					
				.when()
					.delete(Routes.delete_url);
				
		return response;
	}
	
}
