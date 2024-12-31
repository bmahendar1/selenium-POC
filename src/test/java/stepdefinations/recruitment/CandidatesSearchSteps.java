package stepdefinations.recruitment;

import config.initialization.ConfigLoader;
import config.initialization.Context;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Recruitment.Candidates;
import pages.Recruitment.TopBar;
import pages.dashboard.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertListContains;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.helpers.Reporter;

public class CandidatesSearchSteps {

	private Context context;
	private ConfigLoader configLoader;
	private Candidates candidates;
	private TopBar topBar;
	private WebDriverWait wait;
	private Long eWaitSec;
	private Collection<String> tags = new ArrayList<>();

	public CandidatesSearchSteps(Context context) throws Exception {
		this.context = context;
		this.configLoader = new ConfigLoader();

		this.eWaitSec = Long.valueOf(configLoader.getProperty("explicitWait"));
		wait = new WebDriverWait(context.getDriver(), Duration.ofMillis(eWaitSec));
		candidates = new Candidates(context.getDriver(), context);

		topBar = new TopBar(context.getDriver());
	}

	@Before(value = "@candidates")
	public void setScenario(Scenario scenario) {
		
		tags = scenario.getSourceTagNames();
		
		if(tags == null || tags.isEmpty()) {
			System.out.println("The tags are null or empty.");
		} else {
			System.out.println("tags are "+tags);
		}
	}

	@SuppressWarnings("deprecation")
	private void preCheckUps() throws Exception {
		if (!topBar.getCandidateElement().getAttribute("class").endsWith("--visited")) {

			topBar.getCandidateElement().click();
			wait.until(ExpectedConditions.attributeContains(topBar.getCandidateElement(), "class", "--visited"));
		}
	}

	@Given("User logged into application and navigates to recruitment page")
	public void user_logged_into_application_and_navigates_to_recruitment_page() {

		SidePanel sidePanel = new SidePanel(context.getDriver());
		sidePanel.clickOnRecruitmentOption();

	}

	@Given("The user selects {string} job title from the job title dropdown")
	public void the_user_selects_job_title_from_the_job_title_dropdown(String option) throws Exception {

		if (tags.contains("candidates"))
			preCheckUps();

		candidates.clickOnJobTitleDropdown();

		wait.until(new ExpectedCondition<Boolean>() {

			@SuppressWarnings("deprecation")
			@Override
			public Boolean apply(WebDriver input) {
				return candidates.getJobTitleDropdown().getAttribute("class").endsWith("--focus");
			}

		});

		candidates.selectAnOptionFromJobTitleDropdown(option);
		context.setOption("option", option);
	}

	@Given("The user selects {string} vacancy from the vacancy dropdown")
	public void the_user_selects_vacancy_from_the_vacancy_dropdown(String option) throws Exception {

		if (tags.contains("candidates"))
			preCheckUps();

		candidates.clickOnVacancyDropdown();

		wait.until(new ExpectedCondition<Boolean>() {

			@SuppressWarnings("deprecation")
			@Override
			public Boolean apply(WebDriver input) {
				return candidates.getVacancyDropdown().getAttribute("class").endsWith("--focus");
			}
		});

		candidates.selectAnOptionFromVacancyDropdown(option);
		context.setOption("option", option);
	}

	@Given("The user selects {string} manager from the hiring manager dropdown")
	public void the_user_selects_manager_from_the_hiring_manager_dropdown(String option) throws Exception {

		if (tags.contains("candidates"))
			preCheckUps();

		candidates.clickOnHiringManagerDropdown();

		wait.until(new ExpectedCondition<Boolean>() {

			@SuppressWarnings("deprecation")
			@Override
			public Boolean apply(WebDriver input) {
				return candidates.getHiringManagerDropdown().getAttribute("class").endsWith("--focus");
			}

		});

		candidates.selectAnOptionFromHiringManagerDropdown(option);
		context.setOption("option", option);
	}

	@Given("The user selects {string} status from the status dropdown")
	public void the_user_selects_status_from_the_status_dropdown(String option) throws Exception {

		if (tags.contains("candidates"))
			preCheckUps();

		candidates.clickOnStatusDropdown();

		wait.until(new ExpectedCondition<Boolean>() {

			@SuppressWarnings("deprecation")
			@Override
			public Boolean apply(WebDriver input) {
				return candidates.getStatusDropdown().getAttribute("class").endsWith("--focus");
			}

		});

		candidates.selectAnOptionFromStatusDropdown(option);
		context.setOption("option", option);
	}

	@Given("The user types {string} in candidate name text field for hints")
	public void the_user_types_in_candidate_name_text_field_for_hints(String hint) {

		candidates.typeHintInCandidateNameTxtField(hint);
		context.setOption("option", hint);
	}

	@Given("Selects one option from hints")
	public void selects_one_option_from_hints() throws Exception {
		candidates.selectOptionFromCandidateNameHints();
	}

	@Given("The user selects year {int} month {int} date {int} from date of application calender")
	public void the_user_selects_year_month_date_from_date_of_application_calender(int year, int month, int date)
			throws Exception {

		candidates.clickOnDateOfApplicationDropdown();
		candidates.selectDateFromCalender(year, month, date);

		context.setOption("year", year);
		context.setOption("month", month);
		context.setOption("date", date);
	}

	@Given("The user selects {string} method from the method of application dropdown")
	public void the_user_selects_method_from_method_of_application_dropdown(String method) throws Exception {

		candidates.clickOnMethodOfApplicationDropdown();

		Actions actions = new Actions(context.getDriver());

		actions.sendKeys(Keys.DOWN).perform();

		if (method.equals("online"))
			actions.sendKeys(Keys.DOWN).perform();

		actions.sendKeys(Keys.ENTER).perform();
		Thread.sleep(5000);
		context.setOption("option", method);
	}

	@When("User clicks on the search button")
	public void user_clicks_on_the_search_button() {
		candidates.clickOnSearchButton();
	}

	@Then("No records found message displays")
	public void no_records_found_message_displays() throws Exception {

		assertTrue(candidates.infoPopupDisplayed(), "No records Found popup is not displayed");
		assertEquals(candidates.getMessageOnInfoPopup(), "No Records Found",
				"The message on 'no records found' popup doesn't match");
	}

	@Then("Matching records shows up in the records found table")
	public void matching_records_shows_up_in_the_records_found_table() throws Exception {
		List<WebElement> cells;

		if (tags.contains("@search_by_job_title_match")) {

			cells = candidates.getCellsFromVacancyColumn();
			assertListContains(cells, cell -> cell.getText().contains((String) context.getOptions().get("option")),
					"The table doesn't show the records match job title.");

		} else if (tags.contains("@search_by_vacancy_match")) {

			cells = candidates.getCellsFromVacancyColumn();
			assertListContains(cells, cell -> cell.getText().equals((String) context.getOptions().get("option")),
					"The table doesn't show the records match vacancy.");

		} else if (tags.contains("@search_by_hiring_manager_match")) {

			List<String> matcherWords = Arrays.asList(((String) context.getOptions().get("option")).split(" "));
			String pattern = "^" + String.join(".*", matcherWords).concat("$");

			cells = candidates.getCellsFromHiringManagerColumn();
			assertListContains(cells, cell -> cell.getText().matches(pattern),
					"The table doesn't show the records match hiring manager.");

		} else if (tags.contains("@search_by_status_match")) {

			cells = candidates.getCellsFromStatusColumn();
			assertListContains(cells, cell -> cell.getText().equals((String) context.getOptions().get("option")),
					"The table doesn't show the records match status.");

		} else if (tags.contains("@search_by_candidate_name_match")) {

			cells = candidates.getCellsFromCandidateColumn();
			assertListContains(cells,
					cell -> cell.getText().toLowerCase()
							.contains(((String) context.getOptions().get("option")).toLowerCase()),
					"The table doesn't show the records match candidate name");

		} else if (tags.contains("@search_by_date_of_application_match")) {
			cells = candidates.getCellsFromDateOfApplicationColumn();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			String expectedDateStr = LocalDate.of((int) context.getOptions().get("year"),
					(int) context.getOptions().get("month"), (int) context.getOptions().get("date")).format(formatter);
			LocalDate expectedDate = LocalDate.parse(expectedDateStr);

			for (WebElement cell : cells) {
				String actualDateStr = cell.getText();
				LocalDate actualDate = LocalDate.parse(actualDateStr, formatter);

				assertTrue(actualDate.isAfter(expectedDate) || actualDate.isEqual(expectedDate),
						"The date is not equal to or after the expected date, " + actualDate);
			}
		} else if (tags.contains("@search_by_method_of_application_match")) {
			cells = candidates.getCellsFromVacancyColumn();

			assertTrue(cells.size() > 0, "There are no matching records");
		}
	}

	@Then("the candidate tab is default tab")
	public void the_candidate_tab_is_default_tab() throws Exception {

		TopBar topBar = new TopBar(context.getDriver());

		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(context.getDriver(), Duration.ofMillis(eWaitSec));
		boolean doesCandidateTabDefault = wait.until(new ExpectedCondition<Boolean>() {

			@SuppressWarnings("deprecation")
			@Override
			public Boolean apply(WebDriver input) {
				String classAttrTxt;
				try {
					classAttrTxt = topBar.getCandidateElement().getAttribute("class");
					return classAttrTxt.endsWith("--visited");
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		});

		assertTrue(doesCandidateTabDefault, "The candidate tab is not default tab");
	}
}
