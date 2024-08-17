package config.initialization;

import org.openqa.selenium.WebDriver;

public class InitDriver {

	private WebDriver driver;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
