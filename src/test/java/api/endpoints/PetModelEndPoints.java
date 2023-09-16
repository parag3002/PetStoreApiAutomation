package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class PetModelEndPoints 
{

	
	public static Response findPetByStatus(String status)
	{
		
		Response response = given()
			.queryParam("status", status)
			
		.when()
			.get(Routes.findPetByStatusUrl);
		return response;
//		.then()
//			.statusCode(200)
//			.log().all();
		
	}
	
	
	public static Response findPetById(int petID)
	{
		
		Response response = 
		
		given()
			.pathParam("petID", petID)
		.when()
			.get(Routes.findPetByIdUrl);
			
		return response;
//		.then()
//			.log().all()
//			.statusCode(200)
//			.statusLine("HTTP/1.1 200 OK")
//			.assertThat().time(lessThan(1960L));
		
	}
	
	
	public static Response createNewPet(PetModel_Pojo payLoad)
	{
		Response response = given()
		
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payLoad)
		.when()
			.post("https://petstore.swagger.io/v2/pet");
		return response;
		
//		.then()
//			.log().all()
//			.statusCode(200);
	}
	
	
	
	public static void main(String args[])
	{
//		findPetByStatus("parag");
//		findPetById("9223372036854699529");
		//createNewPet();;
	}
	
}
