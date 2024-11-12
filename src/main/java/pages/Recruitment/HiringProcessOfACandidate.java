package pages.Recruitment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HiringProcessOfACandidate {

	@FindBy(xpath="//h1[@title='Hiring Process of a Candidate']")
	private WebElement hiringProcessTitle;
	
	private WebDriver driver;
	
	public HiringProcessOfACandidate(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(this.driver, this);
	}

	public String getHiringProcessTitleTxt() {
		return hiringProcessTitle.getText();
	}
}
