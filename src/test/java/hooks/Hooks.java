package hooks;


import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.ConfigLoader;
import config.initialization.Context;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.TestStep;
import io.cucumber.plugin.event.Step;
import stepdefinations.login.LoginSteps;
import utils.Utils;

public class Hooks {
	
	private final Context context;
	private ConfigLoader configLoder;
	private WebDriverWait wait;
	
	public Hooks(Context context) {
		this.context = context;
		this.configLoder = new ConfigLoader();
		this.wait = new WebDriverWait(this.context.getDriver(), Duration.ofMillis(5000));
	}

	@Before(
			order=1, 
			value= "not @login_not_required and not @herokuapp"
			)
	public void login() throws Exception {

		LoginSteps loginSteps = new LoginSteps(context);
		
		loginSteps.user_opens_the_favourite_browser_and_types_url();
		loginSteps.the_login_page_appears();
		loginSteps.user_enters_username_password_and_clicks_on_login();
		loginSteps.the_user_navigate_to_home_or_landing_page();
		
		Utils.sleep(3000);
	}
	
	@Before(
			order=2, 
			value= "@herokuapp"
			)
	public void login_herokuapp() throws Exception {

		WebDriver driver = new ChromeDriver();		
		context.setDriver(driver);
		
		String herokuappUrl = configLoder.getProperty("herokuapp_url");
		
		if(herokuappUrl == null)
			herokuappUrl = "https://provide_url";
		
		context.getDriver().manage().window().maximize();
		context.getDriver().get(herokuappUrl);
		
		wait.until(new ExpectedCondition<Boolean>(){

			@Override
			public Boolean apply(WebDriver input) {
				return context.getDriver().findElement(By.cssSelector("h1.heading")).getText().equals("Welcome to the-internet");
			}
		});
	}
	
	
	
//	@AfterStep
//	public void monitorSteps(Scenario scenario) {
//
//		if(scenario.getStatus().toString().equals("FAILED")) {
//			
//			Map<String, Object> output = new LinkedHashMap<>();
//			
//			try {
//				/**
//				 * The Scenario object does not directly expose step execution details. Internally, 
//				 * it holds a delegate field pointing to a TestCaseState object that stores execution data.
//				 * Using reflection, the code accesses this private field.
//				 */
//				
//				Field delegate = scenario.getClass().getDeclaredField("delegate");
////				Makes the private delegate field accessible for reading.
//				delegate.setAccessible(true);
////				Retrieves the TestCaseState object, which holds step execution results.
//				TestCaseState tcs = (TestCaseState) delegate.get(scenario);
//				
//				Field stepResultsField = tcs.getClass().getDeclaredField("stepResults");
//				stepResultsField.setAccessible(true);
//				@SuppressWarnings("unchecked")
//				ArrayList<Result> results = (ArrayList<Result>) stepResultsField.get(tcs);
////				System.out.println(results);
////				System.out.println(results.get(0).getClass().getName());
//				
//				
//				Field testCaseField = tcs.getClass().getDeclaredField("testCase");
//				testCaseField.setAccessible(true);
//				TestCase testCase = (TestCase) testCaseField.get(tcs);
////				System.out.println(testCase.getClass().getName());
//
//				
//				// Access pickleSteps (or similar field) to get step details
//				Field testStepsField = testCase.getClass().getDeclaredField("testSteps");
//				testStepsField.setAccessible(true);
//				@SuppressWarnings("unchecked")
//				List<TestStep> testStepsObjects = (List<TestStep>) testStepsField.get(testCase);
////				System.out.println(testStepsObjects.size());
//				
//
//				if(!results.isEmpty() & results.get(results.size()-1).getStatus().toString().equals("FAILED")) {
//					int first = 0;
//					int last = 2;
//					
//					for(TestStep testStep: testStepsObjects) {
//						
//						Field stepField = testStep.getClass().getDeclaredField("step");
//						stepField.setAccessible(true);
//						Step step = (Step) stepField.get(testStep);
//						
//
//						while(first < last) {
//							
//							if(first < results.size()) {
//								output.put(first+": "+step.getText(), results.get(first));
//							}
//							else output.put(first+": "+step.getText(), "");
//							
//							first++;
//						}
//
//						first = last;
//						last = last + 2;
//					}
//					
////					context.setOption(scenario.getName(), results);
//					context.setOption(scenario.getName(), output);
//					System.out.println(context.getOptions().get(scenario.getName()).toString());
//				}
//				
//			} catch (NoSuchFieldException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
	
	@After(
			order=10
			)
	public void tearDown(Scenario scenario) {
		
//		if (scenario.isFailed()) {
//			System.out.println("scenario failed");
//			LocalDateTime currentDateTime = LocalDateTime.now();
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//			String formattedDateTime = currentDateTime.format(formatter);
//			
//			String scenarioName = "UI - "+scenario.getName()+" - "+formattedDateTime;
//
////			@SuppressWarnings("unchecked")
////			ArrayList<Result> results = (ArrayList<Result>) context.getOptions().get(scenario.getName());
////			String contentText = results.toString();
//			
//			@SuppressWarnings("unchecked")
//			Map<String, Result> contentText = (Map<String, Result>) context.getOptions().get(scenario.getName());
//			System.out.println("Before");
//			System.out.println(contentText);
//			System.out.println("after");
////			TakesScreenshot takesScreenshot = (TakesScreenshot) context.getDriver();
////		    byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
////		    scenario.attach(screenshot, "image/png", scenarioName);
//			
//			context.createIssue(scenarioName, contentText.toString(), "10000", "10013", "SP-9");
//		}
		
//		System.out.println(scenario.getStatus());
		
		
		
		if(context.getDriver() != null)
			context.quitDriver();
	}
}
