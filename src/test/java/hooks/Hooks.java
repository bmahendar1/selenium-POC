package hooks;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.ConfigLoader;
import config.initialization.Context;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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
		
		context.getDriver().get(herokuappUrl);

		context.getDriver().manage().window().maximize();
		
		wait.until(new ExpectedCondition<Boolean>(){

			@Override
			public Boolean apply(WebDriver input) {
				return context.getDriver().findElement(By.cssSelector("h1.heading")).getText().equals("Welcome to the-internet");
			}
		});
	}
	
	
	@After(
			order=10
			)
	public void tearDown() {
		
		if(context.getDriver() != null)
			context.quitDriver();
	}
	
}
