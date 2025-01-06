package pages.dashboard;

import org.openqa.selenium.By;

import config.initialization.Context;

public class UserDropdownTabOptions {

	private By logout = By.xpath("//li/descendant::a[text()='Logout']");
	private final Context context;
	
	public UserDropdownTabOptions(Context context) {
		this.context = context;
	}
	
	public void clickOnLogoutOption() {
		context.getDriver().findElement(logout).click();
	}
}
