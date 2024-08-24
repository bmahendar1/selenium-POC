package stepdefinations.login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.InitDriver;
import config.initialization.DataFiles;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.dashboard.Dashboard;
import pages.dashboard.UserDropdownTabOptions;
import pages.login.LoginPage;
import static utils.Utils.*;

import java.time.Duration;

public class LoginSteps {
	
	private LoginPage login;
	private InitDriver initDriver;
	
	public LoginSteps(InitDriver initDriver) throws Exception {
		this.initDriver = initDriver;
	}
	

	@Given("User opens his or her favourite browser and types url")
	public void user_opens_the_favourite_browser_and_types_url() throws Exception {
		
		WebDriver driver;
		
		String browser = getProperty(DataFiles.CONFIG_FILE_PATH, "browser");
		String url = getProperty(DataFiles.CONFIG_FILE_PATH, "url");
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
//			options.addArguments("--start-maximized");
//			options.addArguments("--incognito");
			
			driver = new ChromeDriver(options);

		} else if(browser.equalsIgnoreCase("firefox")){		
			
			FirefoxProfile profile = new FirefoxProfile();
	        
	        // Disable web notifications
	        profile.setPreference("dom.webnotifications.enabled", false);
	        
	        // Disable push notifications
	        profile.setPreference("dom.push.enabled", false);

	        // Disable geo-location prompts
	        profile.setPreference("geo.enabled", false);
	        
	        // Create FirefoxOptions and set the profile
	        FirefoxOptions options = new FirefoxOptions();
	        options.setProfile(profile);

//			options.addArguments("-private");
			
			driver = new FirefoxDriver(options);
			
		} else {
			throw new Exception("Invalid browser choice");
		}
		
		initDriver.setDriver(driver);
		
		initDriver.getDriver().manage().window().maximize();
		
		initDriver.getDriver().get(url);
	}
	
	@Given("The login page appears")
	public void the_login_page_appears() throws Exception {
		
		login = new LoginPage(this.initDriver.getDriver());
		
		String url = initDriver.getDriver().getCurrentUrl();
		String loginText = login.getLoginTextOnLoginPage();
		
		assertEquals(loginText, "Login", "The login text doesn't match.");
		assertEquals(url, "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login", "The login page url doesn't match");
	}
	
	@When("User enters username, password and clicks on login")
	public void user_enters_username_password_and_clicks_on_login() throws Exception {
		
		String username = getProperty(DataFiles.CONFIG_FILE_PATH, "username");
		String password = getProperty(DataFiles.CONFIG_FILE_PATH, "password");
		
		Thread.sleep(1000);
		login.setUsername(username);
		login.setPassword(password);
		

		login.clickLogin();
	}
	
	
	@Then("The user navigate to home or landing page")
	public void the_user_navigate_to_home_or_landing_page() throws Exception {
		
		String expectedHomePageUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		String loginPageUrl = initDriver.getDriver().getCurrentUrl();
		
		assertEquals(loginPageUrl, expectedHomePageUrl, "The login page url does not match expected");
		
		String expectedDashboardText = "Dashboard";
		
		Dashboard homePage = new Dashboard(initDriver.getDriver());
		String actualDashboardText = homePage.getDashboardText();
		
		assertEquals(actualDashboardText, expectedDashboardText, "The Dashboard Text on the home doesn't match");
		
	}
	
	@Then("The user logs out of the application")
	public void the_user_logs_out_of_the_application() throws Exception {
		
		Dashboard dashboard = new Dashboard(initDriver.getDriver());

		String initialValue = dashboard.getUserDropdownMenuElement().getAttribute("class");
		
		dashboard.clickOnUserDropdownTab();
		
		long explicitWait = Long.valueOf(getProperty(DataFiles.CONFIG_FILE_PATH, "explicitWait"));
		WebDriverWait webDriverWait = new WebDriverWait(initDriver.getDriver(), Duration.ofMillis(explicitWait));
		
		boolean isUserDropdownActive = webDriverWait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				String currentValue = dashboard.getUserDropdownMenuElement().getAttribute("class");
				
				return !initialValue.equals(currentValue) && currentValue.equals("--active "+initialValue);
			}
			
		});
		
		assertTrue(isUserDropdownActive, "The user dropdown does not open");
		
		
		if(dashboard.dropdownMenuDisplayed()) {
			UserDropdownTabOptions userDropdownTabOptions = new UserDropdownTabOptions(initDriver.getDriver());
			userDropdownTabOptions.clickOnLogoutOption();
			
			long implicitWait = Long.valueOf(getProperty(DataFiles.CONFIG_FILE_PATH, "implicitWait"));
			
			initDriver.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(implicitWait));
			
			assertEquals(login.getLoginTextOnLoginPage(), "Login", "Login text on login doesn't match");
			assertEquals(getProperty(DataFiles.CONFIG_FILE_PATH, "url"), initDriver.getDriver().getCurrentUrl(), "The login page url doesn't match");
			
//			initDriver.getDriver().quit();
		} else {
			assertTrue(false, "The dashboard user dropdown menu is not displayed");
		}
	}
	
	@Given("The user enters {string} and {string} and clicks on login")
	public void the_user_enters_and_and_clicks_on_login(String username, String password) throws Exception {
		login = new LoginPage(this.initDriver.getDriver());
		
		login.login(username, password);
	}
	
	@Then("An error message {string} appears on the login page")
	public void an_error_message_appears_on_the_login_page(String message) {
		
		String alertMessage = login.getAlertMessage();
		
		assertEquals(alertMessage, "Invalid credentials", "The alert message is not matching");
	}
}
