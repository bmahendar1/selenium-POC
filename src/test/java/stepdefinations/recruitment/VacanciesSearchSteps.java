package stepdefinations.recruitment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.Context;
import io.cucumber.java.en.Given;
import pages.Recruitment.TopBar;


public class VacanciesSearchSteps {

	private Context context;
	private TopBar topBar;
	private Long eWaitSec;
	private WebDriverWait wait;
	
	
	public VacanciesSearchSteps(Context context) throws Exception {
		this.context = context;
		this.topBar = new TopBar(context.getDriver());
		this.eWaitSec = (Long) context.getOptions().get("explicitWait");
		this.wait = new WebDriverWait(context.getDriver(), Duration.ofMillis(eWaitSec));
	}
	
	@Given("User clicks on vacancies header and navigate to vacancies tab")
	public void user_clicks_on_vacancies_header_and_navigate_to_vacancies_tab() {
		
		topBar.getVacanciesElement().click();
		
		wait.until(ExpectedConditions.attributeContains(By.xpath(topBar.getVacanciesByLocator()), "class", "--visited"));
	}
	
}
