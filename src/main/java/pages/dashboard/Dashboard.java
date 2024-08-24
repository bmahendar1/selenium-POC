package pages.dashboard;

import static utils.Utils.getProperty;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.DataFiles;

public class Dashboard {

	private By dashboardText = By.cssSelector("h6.oxd-text.oxd-text--h6");
	private By userDropdownTab = By.cssSelector(".oxd-userdropdown-tab");
	private By userDropdownMenu = By.xpath("//ul[@role='menu']");
	private By userDropdownMenuBeforeClick = By.cssSelector("div.oxd-topbar-header-userarea>ul>li");
	
	
	private WebDriver driver;
	private long seconds;
	
	public Dashboard(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getDashboardText() throws Exception {
		
		seconds = Long.valueOf(getProperty(DataFiles.CONFIG_FILE_PATH, "implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(seconds));
		
		return driver.findElement(dashboardText).getText();
	}
	
	public void clickOnUserDropdownTab () {
		driver.findElement(userDropdownTab).click();
	}
	
	public boolean dropdownMenuDisplayed() throws Exception {
		
		long explicitWait = Long.valueOf(getProperty(DataFiles.CONFIG_FILE_PATH, "implicitWait"));
		WebElement element = driver.findElement(userDropdownMenu);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWait));
		wait.until(ExpectedConditions.visibilityOf(element));
		
		return element.isDisplayed();
	}
	
	public WebElement getUserDropdownMenuElement() {
		return driver.findElement(userDropdownMenuBeforeClick);
	}
	
}
