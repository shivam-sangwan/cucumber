package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
     //cucumber hooks: Code blocks that run automatically at specific points in the 
	//..Cucumber execution cycle, such as the beginning and end of test cases.
	//executed either just before the scenarios start or immediately after they are completed.
	//In testing terminology, such conditions are commonly referred to as 
	//test setup and test teardown.
	
	
	//this method/hook will run before 'scenario with @DeletePlace tag' to 
	//..provide placeid for deleteapi scenario
	//bocz if we run deletapi scenario alone without running addapi scenario
	//then it wont get any placeid(was provided by response of addapi scenario)
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
	    //write a code to get placeid
		//execute this code only if placeid is null i.e. not already
		//..provided by addplaceapi method
	StepDefination sd = new StepDefination();
	
	if(StepDefination.pid ==null)
	{
	sd.add_place_payload("shivam","shiv vihar", "hindi");
	sd.user_calls_using_post_http_method(); //addplaceapi will get hit and response generated
	
	//below function will extract place_id from response
	sd.verify_place_id_created_using_add_place_api_maps_to_using("shivam");
		
		
	}
	
	}
}
