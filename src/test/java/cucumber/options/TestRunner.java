package cucumber.options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/features/", 
		glue = { 
				"stepdefinations",
				"hooks" 
				},
		plugin="json:target/jsonReports/cucumber-report.json",
		tags= "@recruitment and @search_by_date_of_application_match",
		dryRun=false
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}
