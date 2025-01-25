package hooks;


import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.api.client.util.DateTime;

import config.initialization.ConfigLoader;
import config.initialization.Context;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
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
	
	
	
	@AfterStep
	public void monitorSteps(Scenario scenario) {

		if(scenario.getStatus().toString().equals("FAILED")) {
			
			try {
				/**
				 * The Scenario object does not directly expose step execution details. Internally, 
				 * it holds a delegate field pointing to a TestCaseState object that stores execution data.
				 * Using reflection, the code accesses this private field.
				 */
				
				Field delegate = scenario.getClass().getDeclaredField("delegate");
				
//				Makes the private delegate field accessible for reading.
				delegate.setAccessible(true);
				
//				Retrieves the TestCaseState object, which holds step execution results.
				TestCaseState tcs = (TestCaseState) delegate.get(scenario);
				
				Field stepResults = tcs.getClass().getDeclaredField("stepResults");
				stepResults.setAccessible(true);
				@SuppressWarnings("unchecked")
				ArrayList<Result> results = (ArrayList<Result>) stepResults.get(tcs);

				if(results.get(results.size()-1).getStatus().toString().equals("FAILED")) {
					context.setOption(scenario.getName(), results);
				}
				
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@After(
			order=10
			)
	public void tearDown(Scenario scenario) {
		
		if (scenario.isFailed()) {
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = currentDateTime.format(formatter);
			
			String scenarioName = "UI - "+scenario.getName()+" "+formattedDateTime;

			@SuppressWarnings("unchecked")
			ArrayList<Result> results = (ArrayList<Result>) context.getOptions().get(scenario.getName());
			String contentText = results.toString();
			
//			TakesScreenshot takesScreenshot = (TakesScreenshot) context.getDriver();
//		    byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
//		    scenario.attach(screenshot, "image/png", scenarioName);
			
			context.createIssue(scenarioName, contentText, "10000", "10013", "SP-9");
		}
		
//		System.out.println(scenario.getStatus());
		
		
		
		if(context.getDriver() != null)
			context.quitDriver();
	}
}
