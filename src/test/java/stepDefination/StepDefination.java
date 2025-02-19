package stepDefination;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.TestDataBuild;
import resources.Payload;
import resources.TestBase;

public class StepDefination extends TestBase {
	
	//we are making these methods as global bcoz these are required in diff. methods
	RequestSpecification res;
	Response response;
	static String pid;
	
	
	//all our project related data will be kept in TestDataBuild class
	//we use object of TestDataBuild class to access data
	TestDataBuild tdb = new TestDataBuild(); 
	
	//with use payload
	Payload py = new Payload();
	
	@Given("add place payload {string} {string} {string}")  //'@Given' annotation will link "add place payload" step of "feature file" with add_place_payload()..
	//..method. so, when "add place payload" line of feature file is executed..add_place_payload() method also got executed
	public void add_place_payload(String name, String address, String language) throws IOException {
       	//feature file "examples" data will be taken in above 3 variables
		
		res = given().spec(requestSpecification()) //calling method of parent class to achieve reusability
		.body(tdb.addPlacePayload(name,address,language));  //restAssured will automaticaly convert this object into request payload(serialization)
		//calling addPlacePayload() of 'testdatabuild' using 3 variables and then passing..
		//..that data as payload
	    
	}

	@When("user calls AddPlaceAPI using post http method")
	public void user_calls_using_post_http_method() {
		
		response = res.when().post("/maps/api/place/add/json")
		.then().extract().response();
	    
	}

	@Then("user should get response with statuscode {int}")
	public void user_should_get_response_with_statuscode(int stcode) {
		Assert.assertEquals(response.getStatusCode(),stcode);
		
	}

	@Then("{string} of response should be {string}")
	public void of_response_should_be(String key, String Expectedvalue) { 
	      String status = getJsonPath(response,key); //key can take status/scope from feature file depending upon requirement.
	      Assert.assertEquals(status, Expectedvalue); //so this method is linked with 2 steps of feature file 
	}
	      
	  //hit getPlaceApi
	  @Then("verify place_id created using AddPlaceAPI maps to {string} using getPlaceApi")
	  public void verify_place_id_created_using_add_place_api_maps_to_using(String expectedname) throws IOException 
	  {
		  
		  pid = getJsonPath(response,"place_id"); //extracting place_id, will be used in get
		  Response response2 = given().spec(requestSpecification())
		  .queryParam("place_id", pid)
		  .when().get("/maps/api/place/get/json")
		  .then().extract().response();
		  
		  String name = getJsonPath(response2,"name");
		  Assert.assertEquals(name, expectedname); //expectedname: stores name provided by examples of feature file
	         
	   }
	  
	  //deletePlace api
	  @Given("deletePlace payload")
	  public void delete_place_payload() throws IOException {
		  res = given().spec(requestSpecification()) 
			.body(py.deletePlace(pid));
	  }

	  @When("user calls DeletePlace Api using delete http method")
	  public void user_calls_delete_place_api_using_delete_http_method() {
	        response = res.when().delete("/maps/api/place/delete/json")
	        		.then().extract().response();
	  }
    
	  //further steps of deleteapi code i.e. validations are
	  //performed by methods in line 63,69 to achieve reusability

}
