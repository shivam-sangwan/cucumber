package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
       //contains test data to set pojo classes for serializaton.
	
	public AddPlace addPlacePayload(String name, String address, String language)
	{
		//creating object of pojo class
				AddPlace ap = new AddPlace();
				
				//setting value in pojo class using setter methods
				ap.setAccuracy(50);
				ap.setAddress(address);
				ap.setLanguage(language);
				ap.setName(name);
				ap.setPhone_number("(+91) 983 893 3937");
				ap.setWebsite("http://google.com");
				
				List<String> types = new ArrayList();
				types.add("shoe park");
				types.add("shop");
				
				//types will take list of string
				ap.setTypes(types);
				
				//setting location
				Location lc = new Location();
				lc.setLat(-38.383494);
				lc.setLng(33.427362);
				ap.setLocation(lc);
				return ap;
	}
}
