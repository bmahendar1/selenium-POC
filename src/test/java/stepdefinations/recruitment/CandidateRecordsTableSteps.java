package stepdefinations.recruitment;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.initialization.InitDriver;
import config.initialization.DataFiles;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Recruitment.CandidateRecordsFoundTable;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.Utils.*;

import java.time.Duration;

public class CandidateRecordsTableSteps {
	
	private InitDriver initDriver;
	private CandidateRecordsFoundTable recordsFoundTbl;
	private long milliSeconds;
	private WebDriverWait wait;
	private JavascriptExecutor jse;
	
	private String candidateName;

	public CandidateRecordsTableSteps(InitDriver initDriver) throws Exception {
		this.initDriver= initDriver;
		this.recordsFoundTbl= new CandidateRecordsFoundTable(this.initDriver.getDriver());
		this.milliSeconds= Long.valueOf(getProperty(DataFiles.CONFIG_FILE_PATH, "explicitWait"));
		this.wait= new WebDriverWait(initDriver.getDriver(), Duration.ofMillis(milliSeconds));
	}
	
	
	@Given("The user scrolldown to the bottom of the page")
	public void the_user_scrolldown_to_the_bottom_of_the_page() throws Exception {
		
		jse = (JavascriptExecutor) initDriver.getDriver();		

//		wait.until(new ExpectedCondition<Boolean>() {
//
//			@Override
//			public Boolean apply(WebDriver input) {
////				System.out.println(jse.executeScript("return document.body.scrollHeight;"));
////				System.out.println(jse.executeScript("return document.readyState"));
////				return jse.executeScript("return document.readyState").equals("complete");
//				return (Boolean) jse.executeScript("return window.performance.getEntriesByType('resource').filter(r => r.initiatorType === 'xmlhttprequest').length == 0");
//			}
//			
//		});
		
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight);");
	}
	
	
	@When("The user clicks on the trash icon of the last candidate in table")
	public void the_user_clicks_on_the_trash_icon_of_the_last_candidate_in_table() {
		
		WebElement element = recordsFoundTbl.getLastRow();
		candidateName = recordsFoundTbl.getTextFromCell(element, 3);
		recordsFoundTbl.clickOnTrashCan(element);
	}
	
	
	@Then("A confimation popup is disaplayed with title {string} and message {string}")
	public void a_confimation_popup_is_disaplayed_with_title_and_message(String title, String body) {

		wait.until(ExpectedConditions.visibilityOf(recordsFoundTbl.getConfirmationPopup()));
		
		assertTrue(recordsFoundTbl.getConfirmationPopup().isDisplayed(), "The confirmation popup is not displayed");
		assertEquals(recordsFoundTbl.getConfirmationPopupHeaderText(), title, "Confirmation popup title doesn't match");
		assertEquals(recordsFoundTbl.getConfirmationPopupBodyText(), body, "Confirmation popup body doesn't match");
		
	}
	
	
	@Then("The user clicks on the No Cancel button")
	public void the_user_clicks_on_the_no_cancel_button() {
		
		recordsFoundTbl.clickOnCalcelButton();
	}
	
	
	@Then("The popup closes and the record stays in the table")
	public void the_popup_closes_and_the_record_stays_in_the_table() {
		
		Boolean isPopupVisible = wait.until(ExpectedConditions.invisibilityOf(recordsFoundTbl.getConfirmationPopup()));
		
		assertTrue(isPopupVisible, "The confirmation popup is not closed.");
		
		WebElement element= recordsFoundTbl.getLastRow();
		String name= recordsFoundTbl.getTextFromCell(element, 3);
		
		assertEquals(candidateName, name, "candidate name in last row is not matching");
	}
	
	@Then("The user clicks on the Yes Confirm button")
	public void the_user_clicks_on_the_yes_confirm_button() {
	    recordsFoundTbl.clickOnConfirmButton();
	}
	
	@Then("The popup closes and the success message displays")
	public void the_popup_closes_and_the_success_message_displays() {
	    
		boolean doesConfirmationPopupClosed= wait.until(ExpectedConditions.invisibilityOf(recordsFoundTbl.getConfirmationPopup()));
		WebElement element = recordsFoundTbl.getSuccessPopup();
		String successPopupTitle= recordsFoundTbl.getSuccessPopupTitle(element);
		String successPopupContent= recordsFoundTbl.getSuccessPopupContent(element);
		
		assertTrue(doesConfirmationPopupClosed, "The confirmation popup is not closed");
		assertEquals(successPopupTitle, "Success", "success popup title doesn't match");
		assertEquals(successPopupContent, "Successfully Deleted", "success popup content doesn't match");
	}
	
	@Then("The record removes from the table")
	public void the_record_removes_from_the_table() throws Exception{
		Thread.sleep(2000);
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return jse.executeScript("return document.readyState").equals("complete");
			}
		});
		
		jse.executeScript("window.scrollBy(0, document.body.scrollHeight);");
		
		WebElement element = recordsFoundTbl.getLastRow();
		String name = recordsFoundTbl.getTextFromCell(element, 3);
		
		assertTrue(!candidateName.equals(name), "The candidate record is not deleted from the table");
	}
}
