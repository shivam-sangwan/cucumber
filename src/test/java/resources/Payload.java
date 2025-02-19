package resources;

public class Payload {

	//payload class is used to keep all payoad(sample body msg) which is used in our test cases
	//..for which pojo classes are not made
	//this is done to make test cases look less complex

	
     public static String deletePlace(String pid)
     {
    	 String payload =  "{\r\n"
    	 		+ "    \"place_id\":\""+pid+"\"\r\n"
    	 		+ "}";
    	 return payload;
     }
     
    

}
