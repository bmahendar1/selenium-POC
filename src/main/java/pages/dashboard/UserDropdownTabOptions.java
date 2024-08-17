package pages.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDropdownTabOptions {

	private By logout = By.xpath("//li/descendant::a[text()='Logout']");
	private WebDriver driver;
	
	public UserDropdownTabOptions(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnLogoutOption() {
		driver.findElement(logout).click();
	}
}
