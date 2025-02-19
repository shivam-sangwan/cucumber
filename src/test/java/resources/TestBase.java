package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
          //contains reusable code
	public static RequestSpecification req;
	//req is made static bcoz it will be reused again and again in code
	public RequestSpecification requestSpecification() throws IOException
	{
		
		
	    if(req==null) //code inside if block should only run once bcoz we are setting up req variable
	   //..here(setting up only happen once)..so if 'req==not null' then it means req is already setup
	   //so we won't go in if block
	    {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		//FileOutputStream will create "logging.txt" file at project level during runtime 
		//'log' object of 'PrintStream' take all request data using logRequestTo(log)
		//..add that to 'logging.txt' file 
		//benefit of logs: we can find out from logs that at which point our test case is failing
		
		 req = new RequestSpecBuilder().setBaseUri(getGlobalvalue())
	    .addQueryParam("key", "qaclick123")
	    .addFilter(RequestLoggingFilter.logRequestTo(log)) //logging request data
	    .addFilter(ResponseLoggingFilter.logResponseTo(log)) //logging response  data
	    .setContentType(ContentType.JSON).build();
		return req;
	    }
	    return req;
	}
	
	public String getGlobalvalue() throws IOException
	{
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//resources//global.properties");
		Properties propobj = new Properties();
		propobj.load(file);
		String baseurl = propobj.getProperty("baseurl");
		return baseurl;
	}
	
	//to extract any value from any response
	public String getJsonPath(Response response, String key) //key contains the jsonpath of value which we require
	{
		String s = response.asString();
		JsonPath js = new JsonPath(s);
		String value = js.get(key).toString();
		return value;
	}
	
	
}
