package pages.dashboard;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.ConfigLoader;


public class Dashboard {

	private By dashboardText = By.cssSelector("h6.oxd-text.oxd-text--h6");
	private By userDropdownTab = By.cssSelector(".oxd-userdropdown-tab");
	private By userDropdownMenu = By.xpath("//ul[@role='menu']");
	private By userDropdownMenuBeforeClick = By.cssSelector("div.oxd-topbar-header-userarea>ul>li");
	
	
	private WebDriver driver;
	private long iWaitSec;
	private long eWaitSec;
	private ConfigLoader configLoader;
	
	
	public Dashboard(WebDriver driver) {
		this.driver = driver;
		this.configLoader = new ConfigLoader();
		this.iWaitSec = Long.valueOf(configLoader.getProperty("implicitWait"));
		this.eWaitSec = Long.valueOf(configLoader.getProperty("explicitWait"));
	}
	
	public String getDashboardText() throws Exception {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(iWaitSec));
		
		return driver.findElement(dashboardText).getText();
	}
	
	public void clickOnUserDropdownTab () {
		driver.findElement(userDropdownTab).click();
	}
	
	public boolean dropdownMenuDisplayed() throws Exception {
		
		WebElement element = driver.findElement(userDropdownMenu);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(eWaitSec));
		wait.until(ExpectedConditions.visibilityOf(element));
		
		return element.isDisplayed();
	}
	
	public WebElement getUserDropdownMenuElement() {
		return driver.findElement(userDropdownMenuBeforeClick);
	}
	
}
