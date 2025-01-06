package pages.dashboard;

import org.openqa.selenium.By;

import config.initialization.Context;

public class SidePanel {

	private By recruitment = By.xpath("//span[text()='Recruitment']");
	private final Context context;
	
	public SidePanel(Context context) {
		this.context = context;
	}
	
	public void clickOnRecruitmentOption() {
		context.getDriver().findElement(recruitment).click();
	}
}
