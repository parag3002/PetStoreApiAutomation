package api.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import javax.validation.Payload;

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
import api.endpoints.*;

public class Store_Tests 
{

	Faker fake;
	StorePojo payLoad;
	private int id;
	
	@BeforeClass
	public void dataSetup()
	{
		fake = new Faker();
		payLoad = new StorePojo();
		java.util.Random random = new java.util.Random();
		int min = 100;
        int max = 900;
        int randomInt = random.nextInt(max - min + 1) + min;
        
        payLoad.setId(randomInt);
        payLoad.setPetId(randomInt+2);
        payLoad.setQuantity(1);
        payLoad.setShipDate("2023-09-18T09:18:10.197Z");
        payLoad.setStatus("available");
        payLoad.setComplete(true);
        
	}
	
	@Test(priority=1)
	public void test_Place_order_for_pet()
	{
		Response response = StoreEndPoints.placeOrderForPet(payLoad);
		System.out.println("=================Store Model started ==============");
		response.then().log().all();
		
		this.id = response.jsonPath().getInt("id");
		System.out.println("id--->"+id);
	}
	
	
	@Test(priority=2)
	public void test_Find_Purchase_order_By_ID()
	{
		Response response = StoreEndPoints.findOrderById(id);
		response.then().log().all();
	}
	
	
	@Test(priority=3)
	public void test_List_pet_inventory_by_status()
	{
		Response response = StoreEndPoints.getPetInventoryByStatus();
		response.then().log().all();
	}
	
	

	
	@Test(priority=4)
	public void test_Delete_purchase_order_by_ID()
	{
		Response response = StoreEndPoints.deletePurchaseOrderByID(id);
		response.then().log().all();
	}
	
	
}
