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
		tags= "@write_data_to_excel", //^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$
		dryRun=false,
		monochrome=true
	)
public class TestRunner extends AbstractTestNGCucumberTests {

}
