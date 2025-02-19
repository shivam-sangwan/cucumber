package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//..@CucumberOptions() is used to provide "feature file" and 
//.."step defination file" to testRunner class
//note: for feature file we gave path of package(features) that contains feature file
//but for "stepdefination" file we just give name of package;
//below annotations are provided by 'cucumber.junit.runner' dependency

//test runner file: executes the Cucumber feature files and coordinates the 
//steps defined in those feature files with the corresponding step definitions

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = {"stepDefination"} /*, tags = {"@AddPlace","@DeletePlace"}*/)
public class TestRunner {

}
