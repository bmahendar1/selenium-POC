package pages.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.ConfigLoader;

public class LoginPage {

	private WebDriver driver;
	private By usernameElement = By.name("username");
	private By passwordElement = By.name("password");
	private By loginButton = By.cssSelector("button[type='submit']");
	private By loginText = By.xpath("//h5[text()='Login']");
	private By alertMessage = By.xpath("//div[@role='alert']/div/p");
	
	private ConfigLoader configLoader;
	private long secondsE;
	private long secondsI;
	
	
	private WebDriverWait wait;
	
	
	public LoginPage (WebDriver driver) throws Exception {
		this.driver = driver;
		
		this.configLoader = new ConfigLoader();
//		long secondsE = Long.valueOf(getProperty(DataFiles.CONFIG_FILE_PATH, "explicitWait"));
		secondsE = Long.valueOf(configLoader.getProperty("explicitWait"));
		wait= new WebDriverWait(driver, Duration.ofMillis(secondsE));
		
		secondsI = Long.valueOf(configLoader.getProperty("implicitWait"));
	}

	public void setUsername(String username) throws Exception {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(secondsI));
		driver.findElement(usernameElement).sendKeys(username);
	}

	public void setPassword(String password) throws Exception {
		
		driver.findElement(passwordElement).sendKeys(password);
	}
	
	public void clickLogin() {
		driver.findElement(loginButton).click();
	}
	
	public String getLoginTextOnLoginPage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginText));
		
		return driver.findElement(loginText).getText();
	}
	
	public void login(String username, String password) throws Exception {
		setUsername(username);
		setPassword(password);
		clickLogin();
	}
	
	public String getAlertMessage() {
		return driver.findElement(alertMessage).getText();
	}
}
