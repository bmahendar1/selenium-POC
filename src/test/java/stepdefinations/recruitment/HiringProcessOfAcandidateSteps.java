package stepdefinations.recruitment;


import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import config.initialization.Context;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Recruitment.HiringProcessOfACandidate;
import pages.Recruitment.TopBar;

public class HiringProcessOfAcandidateSteps {
	
	private Context context;
	private TopBar topBar;
	private HiringProcessOfACandidate hiringProcess;

	public HiringProcessOfAcandidateSteps(Context context) throws Exception {
		this.context= context;
		
		topBar = new TopBar(this.context.getDriver());
		hiringProcess = new HiringProcessOfACandidate(this.context.getDriver());
	}
	
	
	@When("User clicks on the help button")
	public void clicks_on_the_help_button() throws Exception {
		
//		Actions actions= new Actions(context.getDriver());
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
		
		Set<String> handles = context.getDriver().getWindowHandles();
		List<String> handlesList = handles.stream().toList();
		
		context.getDriver().switchTo().window(handlesList.getLast());
		
		assertEquals(hiringProcess.getHiringProcessTitleTxt(), "Hiring Process of a Candidate");
		
		Thread.sleep(5000);
	}
}
