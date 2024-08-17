package pages.Recruitment;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.PropertyFiles;

import static utils.Utils.*;

public class Candidates {
	
	private By jobTitleDropdown = By.xpath("//label[text()='Job Title']/ancestor::div[@class='oxd-input-group__label-wrapper']/following-sibling::div/div[@class='oxd-select-wrapper']/div");
	private By vacancyDropdown = By.xpath("//label[text()='Vacancy']/../following-sibling::div/div/div");
	private By hiringManagerDropdown = By.xpath("//label[text()='Hiring Manager']/../following-sibling::div/div/div");
	private By statusDropdown = By.xpath("//label[text()='Status']/../following-sibling::div/div/div");
	private By candidateNameTxtField = By.xpath("//input[@placeholder=\"Type for hints...\"]");
	private By dateOfApplication = By.xpath("//input[@placeholder=\"From\"]");
	
	private String dropdownOptionsLocator = "//div[@class='oxd-select-wrapper']/div[@role='listbox']/div/span";
	private By candidatNameOptions = By.xpath("//div[@role=\"listbox\"]/div[@role=\"option\"]");
	private By searchButton = By.xpath("//button[@type='submit']");
	
	private By infoPopup = By.xpath("//div[@aria-live='assertive']");
	private By msgOnInfoPopup = By.xpath("//div[@aria-live=\"assertive\"]/div/div[2]/p[2]");
	private By vacancyColumn = By.xpath("//div[@role='table']/div[@class='oxd-table-body']/div/div[@role='row']/div[@role='cell'][2]");
	private By candidateColumn = By.xpath("//div[@role='table']/div[@class='oxd-table-body']/div/div[@role='row']/div[@role='cell'][3]");
	private By hiringManagerColumn = By.xpath("//div[@role='table']/div[@class='oxd-table-body']/div/div[@role='row']/div[@role='cell'][4]");
	private By statusColumn = By.xpath("//div[@role='table']/div[@class='oxd-table-body']/div/div[@role='row']/div[@role='cell'][6]");
	
	private By calenderMonthDropdown = By.cssSelector(".oxd-calendar-selector-month");
	private String monthOption = "//ul/li[text()='{MONTH}']";
	
	private By calenderYearDropdown = By.cssSelector(".oxd-calendar-selector-month");
	private String yearOption = "//ul/li[text()='{YEAR}']";
	
	private String dateOption = "//div[contains(@class, 'oxd-calendar-date') and text()='{DATE}']";
	
	
	private WebDriver driver;
	
	public Candidates(WebDriver driver) {
		this.driver = driver;
	}
	
	
	/**
	 * 
	 * @return
	 */
	
	public WebElement getJobTitleDropdown() {
		return driver.findElement(jobTitleDropdown);
	}
	
	
	public void clickOnJobTitleDropdown() {
		getJobTitleDropdown().click();
	}
	
	
	public WebElement getVacancyDropdown() {
		return driver.findElement(vacancyDropdown);
	}
	

	public void clickOnVacancyDropdown() {
		getVacancyDropdown().click();
	}
	
	
	public WebElement getHiringManagerDropdown() {
		return driver.findElement(hiringManagerDropdown);
	}
	
	
	public void clickOnHiringManagerDropdown() {
		getHiringManagerDropdown().click();
	}
	
	
	public WebElement getStatusDropdown() {
		return driver.findElement(statusDropdown);
	}
	
	
	public void clickOnStatusDropdown() {
		getStatusDropdown().click();
	}
	
	
	public void clickOnDateOfApplicationDropdown() {
		driver.findElement(dateOfApplication);
	}
	
	public void selectAnOptionFromJobTitleDropdown(String option) {
		selectOption(option);
	}
	
	
	public void selectAnOptionFromVacancyDropdown(String option) {
		selectOption(option);
	}
	
	
	public void selectAnOptionFromHiringManagerDropdown(String option) {
		selectOption(option);
	}
	
	
	public void selectAnOptionFromStatusDropdown(String option) {
		selectOption(option);
	}
	
	
	public void typeHintInCandidateNameTxtField(String hint) {
		driver.findElement(candidateNameTxtField).sendKeys(hint);
	}
	
	
	public void selectOptionFromCandidateNameHints() throws Exception {
		
		long seconds = Long.valueOf(getProperty(PropertyFiles.CONFIG_FILE_PATH, "explicitWait"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(seconds));
		
		wait.until(new ExpectedCondition<Boolean>(){

			@Override
			public Boolean apply(WebDriver input) {
				return !driver.findElement(candidatNameOptions).getText().equals("Searching....");
			}
			
		});

		List<WebElement> hints = driver.findElements(candidatNameOptions);
		
		hints.getFirst().click();
	}
	
	
	private void selectOption(String option) {
		
		List<WebElement> dropdownOptions = driver.findElements(By.xpath(dropdownOptionsLocator));
		
		for(WebElement ele: dropdownOptions) {
			if(ele.getText().equals(option)) {
				ele.click();
				break;
			}
		}
	}
	
	
	
	/**
	 * 
	 */
	
	public void clickOnSearchButton() {
		driver.findElement(searchButton).click();
	}
	
	
	/**
	 * 
	 */
	
	public void selectDateFromCalender(int year, int month, int date) {
		Map<Integer, String> months = Map.ofEntries(
				Map.entry(1, "January"), Map.entry(2, "February"), Map.entry(3, "March"), 
				Map.entry(4, "April"), Map.entry(5, "May"), Map.entry(6, "June"),
				Map.entry(7, "July"), Map.entry(8, "August"), Map.entry(9, "September"),
				Map.entry(10, "October"), Map.entry(11, "November"), Map.entry(12, "December")
		);
		
		clickOnYearDropdown();
		selectYear(year);
		
		clickOnMonthDropdown();
		selectMonth(months.get(month));
		
		selectDate(date);
	}
	
	private void clickOnMonthDropdown() {
		driver.findElement(calenderMonthDropdown).click();
	}
	
	
	private void selectMonth(String month) {
		driver.findElement(By.xpath(monthOption.replace("{MONTH}", month)));
	}
	
	
	private void clickOnYearDropdown() {
		driver.findElement(calenderYearDropdown).click();
	}
	
	
	private void selectYear(int year) {
		driver.findElement(By.xpath(yearOption.replace("{YEAR}", Integer.toString(year))));
	}
	
	
	private void selectDate(int date) {
		driver.findElement(By.xpath(dateOption.replace("{DATE}", Integer.toString(date))));
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public boolean infoPopupDisplayed() throws Exception {
		Long seconds = Long.valueOf(getProperty(PropertyFiles.CONFIG_FILE_PATH, "explicitWait"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(seconds));
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(infoPopup)));
		
		return driver.findElement(infoPopup).isDisplayed();
	}
	
	
	public String getMessageOnInfoPopup() {
		return driver.findElement(msgOnInfoPopup).getText();
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public List<WebElement> getCellsFromVacancyColumn() throws Exception {
		
		return getCells(vacancyColumn);
	}
	
	
	public List<WebElement> getCellsFromHiringManagerColumn() throws Exception {
		
		return getCells(hiringManagerColumn);
	}
	
	public List<WebElement> getCellsFromCandidateColumn() throws Exception {
		
		return getCells(candidateColumn);
	}
	
	
	public List<WebElement> getCellsFromStatusColumn() throws Exception {
		
		return getCells(statusColumn);
	}
	
	
	private List<WebElement> getCells(By location) throws Exception {
		
		long milliSeconds = Long.valueOf(getProperty(PropertyFiles.CONFIG_FILE_PATH, "explicitWait"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(milliSeconds), null);
		
		List<WebElement> cells = driver.findElements(location);
		wait.until(ExpectedConditions.visibilityOfAllElements(cells));
		
		return cells;
	}
}
