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

public class AirlineEndpoints 
{

	public static Response createAirlineData(AirlinePojo payLoad)
	{
		Response response=
			given()
				.contentType(ContentType.JSON)
				.body(payLoad)
			.when()
				.post(Routes.createAirlineByData);
		return response;
	}
	
	
	public static Response readAirlineById(int id)
	{
		Response response = 
			given()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
			.when()
				.get(Routes.readAirlineById);
		return response;
	}
	
	
	public static Response createPassanger(PassangerPojo payLoad)
	{
		Response response =
			given()
				.contentType(ContentType.JSON)
				.body(payLoad)
			.when()
				.post(Routes.createPpassanger);
		
		return response;
	}
	
}
