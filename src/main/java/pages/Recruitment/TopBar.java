package pages.Recruitment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.DataFiles;

import static utils.Utils.*;

import java.time.Duration;

public class TopBar {

	private WebDriver driver;
	
	private By candidateVisitedNode = By.xpath("//nav[@aria-label='Topbar Menu']/ul/li[1]");
	private By vacanciesVisitedNode = By.xpath("//nav[@aria-label='Topbar Menu']/ul/li[2]");
	private By helpButton = By.xpath("//button[@title='Help']");
	
	
	private WebDriverWait wait;
	
	public TopBar(WebDriver driver) throws Exception {
		this.driver = driver;
		long milliSeconds = Long.valueOf(getProperty(DataFiles.CONFIG_FILE_PATH, "explicitWait"));
		wait = new WebDriverWait(driver, Duration.ofMillis(milliSeconds));
	}

	public WebElement getCandidateElement() throws Exception {
		wait.until(ExpectedConditions.refreshed(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return driver.findElement(candidateVisitedNode).isDisplayed();
			}
			
		}));
		return driver.findElement(candidateVisitedNode);
	}
	
	public WebElement getVacanciesElement() {
		return driver.findElement(vacanciesVisitedNode);
	}
	
	
	public WebElement getHelpButton() {
		return driver.findElement(helpButton);
	}
	
	public void clickOnHelpButton() {
		getHelpButton().click();
	}
}
