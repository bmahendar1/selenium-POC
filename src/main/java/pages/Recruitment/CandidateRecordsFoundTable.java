package pages.Recruitment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CandidateRecordsFoundTable {

	@FindBy(xpath= "//nav[@role='navigation' and @aria-label='Pagination Navigation']")
	private WebElement navigation;
	
	@FindBy(xpath= "(//div[@class='oxd-table-body']/div)[last()]")
	private WebElement lastRow;
	
	@FindBy(css= "div.orangehrm-dialog-popup")
	private WebElement confirmationPopup;
	
	@FindBy(css= "div.orangehrm-modal-header>p")
	private WebElement confirmationPopupHeader;
	
	@FindBy(css= "div.orangehrm-text-center-align>p")
	private WebElement confirmationPopupBody;
	
	@FindBy(xpath= "//button[@type='button' and text()=' No, Cancel ']")
	private WebElement cancelButton;
	
	@FindBy(xpath= "//button[@type='button' and text()=' Yes, Delete ']")
	private WebElement confirmButton;
	
	@FindBy(id= "oxd-toaster_1")
	private WebElement successPopup;
	
	private String trashCan= "descendant::button[2]";
	private String candidateCell= "descendant::div/div[{COLUMN}]/div";
	private String successPopupTitle= "div.oxd-toast-content--success>p.oxd-text--toast-title";
	private String successPopupContent= "div.oxd-toast-content--success>p.oxd-text--toast-message";
	
	
	private WebDriver driver;
	
	public CandidateRecordsFoundTable(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(this.driver, this);
	}
	
	
	public boolean isNavigationVisible() {
		return navigation.isDisplayed();
	}
	
	public WebElement getLastRow() {
		return lastRow;
	}
	
	public void clickOnTrashCan(WebElement parent) {
		parent.findElement(By.xpath(trashCan)).click();
	}
	
	public String getTextFromCell(WebElement parent, int row) {
		return parent.findElement(By.xpath(candidateCell.replace("{COLUMN}", Integer.toString(row)))).getText();
	}
	
	public WebElement getConfirmationPopup() {
		return confirmationPopup;
	}
	
	public String getConfirmationPopupHeaderText() {
		return confirmationPopupHeader.getText();
	}
	
	public String getConfirmationPopupBodyText() {
		return confirmationPopupBody.getText();
	}
	
	public void clickOnCalcelButton() {
		cancelButton.click();
	}
	
	public void clickOnConfirmButton() {
		confirmButton.click();
	}
	
	public WebElement getSuccessPopup() {
		return successPopup;
	}
	
	public String getSuccessPopupTitle(WebElement popup) {
		return popup.findElement(By.cssSelector(successPopupTitle)).getText();
	}
	
	public String getSuccessPopupContent(WebElement popup) {
		return popup.findElement(By.cssSelector(successPopupContent)).getText();
	}
}
