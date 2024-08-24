package pages.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SidePanel {

	private By recruitment = By.xpath("//span[text()='Recruitment']");
	
	
	private WebDriver driver;
	
	public SidePanel(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnRecruitmentOption() {
		driver.findElement(recruitment).click();
	}
}
