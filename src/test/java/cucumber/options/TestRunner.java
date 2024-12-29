package cucumber.options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		features = "src/test/java/features/", 
		glue = {
				"stepdefinations",
				"hooks" 
				},
		plugin = "json:target/jsonReports/cucumber-report.json",
		tags = "@basic_auth", //^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$
		dryRun = false,
		monochrome = true//UalFSzJZP4VNVEvRhkwdus3P4IRVQTDf
	)
public class TestRunner extends AbstractTestNGCucumberTests {
//	List<String> windowTitles = driver.getWindowHandles()
//		    .stream()
//		    .map(window -> driver.switchTo().window(window).getTitle())
//		    .collect(Collectors.toList());
	
	
//	Map<String, String> windowTitleToHandleMap = driver.getWindowHandles()
//		    .stream()
//		    .collect(Collectors.toMap(
//		        window -> driver.switchTo().window(window).getTitle(), // Key: Window title
//		        window -> window // Value: Window handle ID
//		    ));
}
