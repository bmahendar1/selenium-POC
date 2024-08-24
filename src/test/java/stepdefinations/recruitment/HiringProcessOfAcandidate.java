package stepdefinations.recruitment;


import java.util.List;
import java.util.Set;

import config.initialization.InitDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Recruitment.TopBar;

public class HiringProcessOfAcandidate {
	
	private InitDriver initDriver;
	private TopBar topBar;

	public HiringProcessOfAcandidate(InitDriver initDriver) throws Exception {
		this.initDriver= initDriver;
		
		topBar = new TopBar(this.initDriver.getDriver());
	}
	
	
	@When("User clicks on the help button")
	public void clicks_on_the_help_button() throws Exception {
		
//		Actions actions= new Actions(initDriver.getDriver());
//		
////		actions.moveToElement(topBar.getHelpButton());
//		actions.contextClick(topBar.getHelpButton()).perform();
//		
//		Robot robot = new Robot();
//		
//		robot.keyPress(KeyEvent.VK_DOWN);
//		robot.keyRelease(KeyEvent.VK_DOWN);
//		
//		Thread.sleep(10000);
		
		topBar.clickOnHelpButton();
		
	}
	
	@Then("User navigates to hiring processing of a candidate page")
	public void user_navigates_to_hiring_processing_of_a_candidate_page() throws Exception {
		
		Set<String> handles = initDriver.getDriver().getWindowHandles();
		List<String> handlesList = handles.stream().toList();
		
		initDriver.getDriver().switchTo().window(handlesList.getLast());
		
		
		Thread.sleep(5000);
	}
}
