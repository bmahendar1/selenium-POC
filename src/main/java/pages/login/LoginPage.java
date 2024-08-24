package pages.login;

import static utils.Utils.getProperty;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.DataFiles;

public class LoginPage {

	private WebDriver driver;
	private By usernameElement = By.name("username");
	private By passwordElement = By.name("password");
	private By loginButton = By.cssSelector("button[type='submit']");
	private By loginText = By.xpath("//h5[text()='Login']");
	private By alertMessage = By.xpath("//div[@role='alert']/div/p");
	
	
	private WebDriverWait wait;
	
	
	public LoginPage (WebDriver driver) throws Exception {
		this.driver = driver;
		
		long milliSecs = Long.valueOf(getProperty(DataFiles.CONFIG_FILE_PATH, "explicitWait"));
		wait= new WebDriverWait(driver, Duration.ofMillis(milliSecs));
	}

	public void setUsername(String username) throws Exception {
		
		long seconds = Long.valueOf(getProperty(DataFiles.CONFIG_FILE_PATH, "implicitWait"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(seconds));
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
