package pages.Recruitment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.PropertyFiles;

import static utils.Utils.*;

import java.time.Duration;

public class TopBar {

	private WebDriver driver;
	
	private By candidateVisitedNode = By.xpath("//nav[@aria-label='Topbar Menu']/ul/li[1]");
	private By vacanciesVisitedNode = By.xpath("//nav[@aria-label='Topbar Menu']/ul/li[2]");
	
	public TopBar(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getCandidateElement() throws Exception {
		long milliSeconds = Long.valueOf(getProperty(PropertyFiles.CONFIG_FILE_PATH, "explicitWait"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(milliSeconds));
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
}
