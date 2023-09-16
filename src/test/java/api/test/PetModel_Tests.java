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
import api.utilities.ReadProperties;
import api.endpoints.*;


public class PetModel_Tests 
{

	PetModel_Pojo petPayload;
	Faker fake;
	int randomInt;
	PetModel_Tests()
	{
		petPayload = new PetModel_Pojo();
		
	}
	
	@BeforeClass
	public void setupData()
	{
		fake = new Faker();
		
		java.util.Random random = new java.util.Random();
		int min = 100;
        int max = 900;
        randomInt = random.nextInt(max - min + 1) + min;
        
        petPayload.setId(randomInt);
//        petPayload.setCategory_id(4897);
//        petPayload.setCategory_name(fake.name().username());
        petPayload.setName(fake.name().firstName());
//        petPayload.setPhotoUrls(fake.internet().url());
//        petPayload.setTags_id(4897);
//        petPayload.setTags_name(fake.name().lastName());
        petPayload.setStatus("parag");
	}
	
	@Test(priority=1)
	public void testCreatePet()
	{
		Response response = PetModelEndPoints.createNewPet(petPayload);
		response.then().log().all();
	}
	
	
	@Test(priority=2)
	public void testFindPetByStatus()
	{
		Response response = PetModelEndPoints.findPetByStatus(ReadProperties.readProperty("petStatus2"));
		response.then().log().all();
	}
	
	
	@Test(priority=3)
	public void testFindPetById()
	{
		Response response = PetModelEndPoints.findPetById(randomInt);
		response.then().log().all();
	}
	
	
	
}


//private int id;
//private int category_id;
//private String category_name;
//private String name;
//private String photoUrls;
//private String tags_id;
//private String tags_name;
//private String status;

