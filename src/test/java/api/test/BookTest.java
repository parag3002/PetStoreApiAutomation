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
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.FileReader;
import jdk.internal.net.http.common.Log;
import net.sf.saxon.exslt.Math;

import api.payload.*;
import api.endpoints.*;
import api.utilities.*;

public class BookTest 
{

	BookPOJO payLoad;
	int forthBookId;
	
	@Test(priority=1)
	public void test_getBooks()
	{
		Response response = BookEndPoints.getBooks();
		response.then().log().all();
		
		JsonPath jsonpath = response.jsonPath();
		List<Integer> bookIds = jsonpath.getList("id");
		forthBookId = bookIds.get(3);
		
	}
	
	
	@Test(priority=2, dataProvider = "bookData", dataProviderClass = BookDataProvider.class)
	public void test_createBook(String id, String title, String description, String pagecount, String excerpt, String publishDate)
	{
		payLoad = new BookPOJO();
		payLoad.setId(Integer.parseInt(id));
		payLoad.setTitle(title);
		payLoad.setDescription(description);
		payLoad.setPageCount(Integer.parseInt(pagecount));
		payLoad.setExcerpt(excerpt);
		payLoad.setPublishDate(publishDate);
		
		Response response = BookEndPoints.createBook(payLoad);
		response.then().log().all();
		assertEquals(response.statusCode(), 200);
		
	}
	
	
	
	@Test(priority=3, dataProvider = "bookData", dataProviderClass = BookDataProvider.class)
	public void test_updateBook(String id, String title, String description, String pagecount, String excerpt, String publishDate)
	{
		payLoad = new BookPOJO();
		//payLoad.setId(Integer.parseInt(id));
		payLoad.setId(forthBookId);
		payLoad.setTitle(title+" James Bond");
		payLoad.setDescription(description);
		payLoad.setPageCount(Integer.parseInt(pagecount+3));
		payLoad.setExcerpt(excerpt);
		payLoad.setPublishDate(publishDate);
		
		Response response = BookEndPoints.updateBook(payLoad,forthBookId);
		response.then().log().all();
		assertEquals(response.statusCode(), 200);
		
		System.out.println("4th Book Id in the Response------> "+forthBookId);
	}
	
	@Test(priority=4)
	public void test_getBookById()
	{
		Response response = BookEndPoints.getBookById(5);
		response.then().log().all();
		
		JsonPath jsonpathResponse = response.jsonPath();
		String description = jsonpathResponse.getString("description");
		System.out.println("description--------->"+description);
		
		assertEquals(description,"Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\n");
		
		assertEquals(response.header("Content-Type"), "application/json; charset=utf-8; v=1.0");
		System.out.println(response.header("Content-Type")+"------------ header Content-Type >> match found");
		
		
	}
	
	
}
