package pages.login;

import static utils.Utils.getProperty;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.initialization.PropertyFiles;

public class LoginPage {

	private WebDriver driver;
	private By usernameElement = By.name("username");
	private By passwordElement = By.name("password");
	private By loginButton = By.cssSelector("button[type='submit']");
	private By loginText = By.xpath("//h5[text()='Login']");
	
	
	public LoginPage (WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void setUsername(String username) throws Exception {
		
		long seconds = Long.valueOf(getProperty(PropertyFiles.CONFIG_FILE_PATH, "implicitWait"));
		
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
		return driver.findElement(loginText).getText();
	}
	
	public void login(String username, String password) throws Exception {
		setUsername(username);
		setPassword(password);
		clickLogin();
	}
}
