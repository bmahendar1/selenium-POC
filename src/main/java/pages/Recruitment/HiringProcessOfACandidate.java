package pages.Recruitment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.initialization.Context;

public class HiringProcessOfACandidate {

	@FindBy(xpath="//h1[@title='Hiring Process of a Candidate']")
	private WebElement hiringProcessTitle;
	
	private final Context context;
	
	public HiringProcessOfACandidate(Context context) {
		this.context = context;
		
		PageFactory.initElements(context.getDriver(), this);
	}

	public String getHiringProcessTitleTxt() {
		return hiringProcessTitle.getText();
	}
}
