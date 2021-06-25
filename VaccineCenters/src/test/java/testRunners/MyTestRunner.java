package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
			features = {"src/test/resources/FeatureFiles"},
			glue = {"stepDefinitions"},
			monochrome=true,	//display the console output in a proper readable format without any question marks
			plugin = {"pretty"}
)
public class MyTestRunner {

}
