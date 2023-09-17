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

	Pet petPayload;
	Category category;
	Tag tag;
	Faker fake;
	int randomInt;
	PetModel_Tests()
	{
		petPayload = new Pet();
		category = new Category();
		tag = new Tag();
		
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
        category.setId(randomInt+10);
        category.setName(fake.name().username());
        petPayload.setCategory(category);
        petPayload.setName(fake.name().firstName());
        List<String> photoUrls = List.of("https:://screenshot."+fake.internet().url());
        petPayload.setPhotoUrls(photoUrls);
        tag.setId(randomInt+20);
        tag.setName(fake.name().lastName());
        petPayload.setTags(List.of(tag));
        petPayload.setStatus(fake.options().option("available","unavailable","animal"));
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

