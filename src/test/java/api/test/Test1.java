package api.test;

import api.utilities.*;
import org.testng.annotations.Test;

public class Test1 
{

	@Test
	public void readValue()
	{
		System.out.println("--->"+ReadProperties.readProperty("petstoreGetUserUrl"));
		
	}
}
