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
import api.utilities.ReadProperties;
import api.endpoints.*;

public class InstantWebToolsTests 
{
	
	AirlinePojo airlinePayload;
	PassangerPojo passangerPayload;
	
	public InstantWebToolsTests()
	{
		airlinePayload = new AirlinePojo();
		passangerPayload =new PassangerPojo();
	}
	
	
	@BeforeClass
	public void setupData()
	{
		airlinePayload.setId(Data.randomIntGenerator());
		airlinePayload.setName(Data.fakeData().name().firstName());
		airlinePayload.setCountry("India");
		airlinePayload.setLogo("www.google.com");
		airlinePayload.setSlogan("Srilanka");
		airlinePayload.setHead_quaters("Kolkata");
		airlinePayload.setWebsite("www.google.com");
		airlinePayload.setEstablished("1990");
		
		passangerPayload.setName(Data.fakeData().name().fullName());
		passangerPayload.setTrips(Data.randomIntGenerator());
		passangerPayload.setAirline(Data.randomIntGenerator()+2);
	}
	
	
	@Test(priority=1)
	public void createAirlineByDate()
	{
		Response response = AirlineEndpoints.createAirlineData(airlinePayload);
		
		response.then().log().all();
	}
	
	
	@Test(priority=2)
	public void readAirlineByDate()
	{
		Response response = AirlineEndpoints.readAirlineById(12433443);
		
		response.then().log().all();
	}
	
	
	@Test(priority=3)
	public void createPassanger()
	{
		Response response = AirlineEndpoints.createPassanger(passangerPayload);
		
		response.then().log().all();
				
		
	}

}
