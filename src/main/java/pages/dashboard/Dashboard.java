package pages.dashboard;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.ConfigLoader;
import config.initialization.Context;


public class Dashboard {

	private By dashboardText = By.cssSelector("h6.oxd-text.oxd-text--h6");
	private By userDropdownTab = By.cssSelector(".oxd-userdropdown-tab");
	private By userDropdownMenu = By.xpath("//ul[@role='menu']");
	private By userDropdownMenuBeforeClick = By.cssSelector("div.oxd-topbar-header-userarea>ul>li");
	
	
	private long iWaitSec;
	private long eWaitSec;
	private ConfigLoader configLoader;
	private final Context context;
	
	
	public Dashboard(Context context) {
		this.context = context;
		this.configLoader = new ConfigLoader();
		this.iWaitSec = Long.valueOf(configLoader.getProperty("implicitWait"));
		this.eWaitSec = Long.valueOf(configLoader.getProperty("explicitWait"));
	}
	
	public String getDashboardText() throws Exception {
		
		context.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(iWaitSec));
		
		return context.getDriver().findElement(dashboardText).getText();
	}
	
	public void clickOnUserDropdownTab () {
		context.getDriver().findElement(userDropdownTab).click();
	}
	
	public boolean dropdownMenuDisplayed() throws Exception {
		
		WebElement element = context.getDriver().findElement(userDropdownMenu);

		WebDriverWait wait = new WebDriverWait(context.getDriver(), Duration.ofMillis(eWaitSec));
		wait.until(ExpectedConditions.visibilityOf(element));
		
		return element.isDisplayed();
	}
	
	public WebElement getUserDropdownMenuElement() {
		return context.getDriver().findElement(userDropdownMenuBeforeClick);
	}
	
}
