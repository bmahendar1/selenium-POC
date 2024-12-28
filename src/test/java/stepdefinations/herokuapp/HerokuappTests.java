package stepdefinations.herokuapp;

import config.initialization.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.Utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static utils.Utils.*;

public class HerokuappTests {
	
	private Context context;
	private String url = "https://admin:admin@the-internet.herokuapp.com/";
	private long explicitWait;
	
	public HerokuappTests(Context context) {
		this.context = context;
		this.explicitWait = 4000;
	}
	
	
	private void launchBrowser() {
		
		WebDriver driver = new ChromeDriver();		
		context.setDriver(driver);
		this.wait = new WebDriverWait(context.getDriver(), Duration.ofMillis(explicitWait));
	}
	
	
	private void launchBrowserWithOptions(ChromeOptions options) {
		
		WebDriver driver = new ChromeDriver(options);
		context.setDriver(driver);
		this.wait = new WebDriverWait(context.getDriver(), Duration.ofMillis(explicitWait));
	}

	/**
	 * Scenario: Herokuapp basic auth via url
	 */
	
	@Given("user opens the app keeping username and password in the url")
	public void user_opens_the_app_keeping_username_and_password_in_the_url() {
		
		launchBrowser();
		context.getDriver().get(url+"basic_auth/");
		
	}
	
	@Then("a successful {string} message displays")
	public void a_successful_message_displays(String expectedMsg) {
		
		String successMsg = context.getDriver().findElement(By.cssSelector("div>p")).getText();
		Assert.assertEquals(successMsg, expectedMsg, "The authentication success message is not matching expected");
	}
	
	/**
	 * Common Steps
	 */
	
	@Given("user navigate to the herokuapp menu landing page")
	public void user_navigate_to_the_herokuapp_menu_landing_page() {
	    
		launchBrowser();
		context.getDriver().get(url);
		
		String landingPageUrl = context.getDriver().getCurrentUrl();
		Assert.assertEquals(landingPageUrl, url, "The landing page url is not matching expected");
	}
	
	/**
	 * View context menu
	 */
	
	
	@Given("clicks on the context menu option")
	public void clicks_on_the_context_menu_option() {

		context.getDriver().findElement(By.linkText("Context Menu")).click();
		
	}
	
	@Given("user will navigate to the context menu page")
	public void user_will_navigate_to_the_context_menu_page() {
		
		String contextMenuPageUrl = context.getDriver().getCurrentUrl();
		Assert.assertEquals(contextMenuPageUrl, url+"context_menu", "context menu page url is not matching expected");
	}
	
	@Given("user right clicks on the box")
	public void user_right_clicks_on_the_box() {
		
		WebElement contextBox = context.getDriver().findElement(By.id("hot-spot"));
		
		Actions actions = new Actions(context.getDriver());
		
		actions.moveToElement(contextBox).perform();
		actions.contextClick().perform();
		
	}
	
	@Then("a popup will display with message {string}")
	public void a_popup_will_display_with_message(String expectedMsg) {
		
		Alert alert = context.getDriver().switchTo().alert();
		
		String popupMsg = alert.getText();
		
		Assert.assertEquals(popupMsg, expectedMsg, "Alert popup message doesn't match expected");
		
		alert.accept();
	}
	
	/**
	 * Disappearing elements on page load
	 */
	
	@Given("clicks on the disappearing elements option")
	public void clicks_on_the_disappearing_elements_option() {
		
		context.getDriver().findElement(By.linkText("Disappearing Elements")).click();
	}
	
	@Then("user will navigate to the disappearing elements page")
	public void user_will_navigate_to_the_disappearing_elements_page() {
		
		String disappearingPageUrl = context.getDriver().getCurrentUrl();
		Assert.assertEquals(disappearingPageUrl, url+"disappearing_elements", "Disappearing page url doesn't match expected");
	}
	
	@Then("user refreshes the page")
	public void user_refreshes_the_page() {
		
		context.getDriver().navigate().refresh();
	}
	
	@Then("user clicks on the gallary option")
	public void user_clicks_on_the_gallary_option() {
		
		
		try {
			WebElement gallaryOption = context.getDriver().findElement(By.linkText("Gallery"));
			
			if(gallaryOption.isDisplayed()) {
				gallaryOption.click();
			}
		} catch (NoSuchElementException e) {
			Assert.assertTrue(false, "The gallary option is not displayed");
		}
	}
	
	@Then("user will navigate to gallary page")
	public void user_will_navigate_to_gallary_page() {
		
		String gallaryPageUrl = context.getDriver().getCurrentUrl();
		Assert.assertEquals(gallaryPageUrl, url+"gallery/", "The gallary page url doesn't match the expected");
	}
	
	/**
	 * Drag and Drop
	 */
	
	@Given("clicks on the drag and drag option")
	public void clicks_on_the_drag_and_drag_option() {
		
		context.getDriver().findElement(By.linkText("Drag and Drop")).click();
	}
	
	@Then("user will navigate to the drag and drop page")
	public void user_will_navigate_to_the_drag_and_drop_page() {
		
		String dragDropPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(dragDropPageUrl, url+"drag_and_drop", "Drag and Drop url does not match the expected");
	}
	
	@Then("user drags box A towards box B")
	public void user_drags_box_a_towards_box_b() {
		
		WebElement boxA = context.getDriver().findElement(By.xpath("//div/header[text()='A']"));
		WebElement boxB = context.getDriver().findElement(By.xpath("//div/header[text()='B']"));
		
		Actions actions = new Actions(context.getDriver());
		actions.dragAndDrop(boxA, boxB).perform();
	}
	
	@Then("the box A and box B places interchanges")
	public void the_box_a_and_box_b_places_interchanges() {
		
		String textOnBoxAtPositionA = context.getDriver().findElement(By.id("column-a")).getText();
		String textOnBoxAtPositionB = context.getDriver().findElement(By.id("column-b")).getText();
		
		Assert.assertEquals(textOnBoxAtPositionA, "B", "The box B is not at position A as expected");
		Assert.assertEquals(textOnBoxAtPositionB, "A", "The box A is not at position B as expected");
	}
	
	@Then("user drags box B towards box A")
	public void user_drags_box_b_towards_box_a() {
		
		WebElement boxA = context.getDriver().findElement(By.xpath("//div/header[text()='A']"));
		WebElement boxB = context.getDriver().findElement(By.xpath("//div/header[text()='B']"));
		
		Actions actions = new Actions(context.getDriver());
		actions.dragAndDrop(boxB, boxA).perform();
	}
	
	/**
	 * dropdown
	 */
	
	private WebElement selectDropdown;
	
	@Given("clicks on the dropdown option")
	public void clicks_on_the_dropdown_option() {
		context.getDriver().findElement(By.linkText("Dropdown")).click();
		
	}
	
	@Then("user navigates to dropdown page")
	public void user_navigates_to_dropdown_page() {
		
		String currentPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(currentPageUrl, url+"dropdown", "The dropdown page url doesn't match expected");
	}
	
	
	@Then("user selects option1 from the dropdown")
	public void user_selects_option1_from_the_dropdown() {
		
		selectDropdown = context.getDriver().findElement(By.id("dropdown"));

		Select select = new Select(selectDropdown);
		
		select.selectByValue("1");
//		select.selectByVisibleText("Option1");
	}
	
	
	@Then("dropdown shows option1 as its value")
	public void dropdown_shows_option1_as_its_value() {
		
		WebElement option1 = context.getDriver().findElement(By.xpath("//option[@value='1']"));
		
		if(option1 != null) {
			boolean isSelected = option1.isSelected();
			
			Assert.assertTrue(isSelected, "The option1 value is not selected");
		} else {
			Assert.assertTrue(false, "The option1 value is not selected");
		}
	}
	
	
	@Then("user selects option2 from the dropdown")
	public void user_selects_option2_from_the_dropdown() {
		
		Select select = new Select(selectDropdown);
		
		select.selectByValue("2");
//		select.selectByVisibleText("Option2");
	}
	
	
	@Then("dropdown shows option2 as its value")
	public void dropdown_shows_option2_as_its_value() {
		
		WebElement option2 = context.getDriver().findElement(By.xpath("//option[@value='2']"));
		
		if(option2 != null) {
			boolean isSelected = option2.isSelected();
			
			Assert.assertTrue(isSelected, "The option2 value is not selected");
		} else {
			Assert.assertTrue(false, "The option2 value is not selected");
		}
	}
	
	
	/**
	 * Dynamic controls
	 */
	
	private WebElement checkbox;
	private WebElement button;
	private String successMsg;
	private WebDriverWait wait;
	
	
	@Given("clicks on the Dynamic Controls option")
	public void clicks_on_the_dynamic_controls_option() {
		
		context.getDriver().findElement(By.linkText("Dynamic Controls")).click();
	}
	
	@Then("user navigates to dynamic controls page")
	public void user_navigates_to_dynamic_controls_option() {
		
		String currentPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(currentPageUrl, url+"dynamic_controls", "The dynamic contol page url doesn't the expected");
	}
	
	@Then("user checks the checkbox and clicks on remove button")
	public void user_checks_the_checkbox_and_clicks_on_remove_button() {
		
		checkbox = context.getDriver().findElement(By.id("checkbox"));
		checkbox.click();
		
		button = context.getDriver().findElement(By.xpath("//button[@type='button']"));
		button.click();
		
	}
	
	@Then("The checkbox will disappear")
	public void the_checkbox_will_disappear() {
		
		boolean isExists = wait.until(ExpectedConditions.invisibilityOf(checkbox));
		Assert.assertTrue(isExists, "The checkbox is not disappeared from the page");
		
		successMsg = context.getDriver().findElement(By.id("message")).getText();
		Assert.assertEquals(successMsg, "It's gone!", "The success message is not displayed");
	}
	
	@Then("user clicks on the add button")
	public void user_clicks_on_the_add_button() {
		
		button.click();
	}
	
	@Then("the checkbox will reappear")
	public void the_checkbox_will_reappear() {

		checkbox = context.getDriver().findElement(By.id("checkbox"));
		successMsg = context.getDriver().findElement(By.id("message")).getText();
		
		wait.until(ExpectedConditions.visibilityOf(checkbox));
		Assert.assertTrue(checkbox.isDisplayed(), "The checkbox doesn't reappear");
		Assert.assertEquals(successMsg, "It's back!", "The it's back success message doesn't appear");
	}
	
	
	@Then("user clicks on enable button")
	public void user_clicks_on_enable_button() {

		context.getDriver().findElement(By.xpath("//button[text()='Enable']")).click();
	
	}
	
	@Then("The text field is enabled")
	public void the_text_field_is_enabled() {
		
		
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return context.getDriver().findElement(By.id("message")).getText().equals("It's enabled!");
			}
			
		});
		
		
		
		WebElement txtField = context.getDriver().findElement(By.xpath("//input[@type='text']"));
		boolean isEnabled = txtField.isEnabled();
		
		if(isEnabled) {
			txtField.sendKeys("This field is enabled for user to enter some text in it.");
		}
		
		Assert.assertTrue(isEnabled, "The text box is not enabled");
	}
	
	@Then("user clicks on the disable button")
	public void user_clicks_on_the_disable_button() {
		
		context.getDriver().findElement(By.xpath("//button[text()='Disable']")).click();
		
	}
	
	@Then("the text field is disabled")
	public void the_text_field_is_disabled() {
		
		
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return context.getDriver().findElement(By.id("message")).getText().equals("It's disabled!");
			}
			
		});
		
		boolean isEnabled = context.getDriver().findElement(By.xpath("//input[@type='text']")).isEnabled();
		Assert.assertFalse(isEnabled, "The text box is not disabled");
	}
	
	/**
	 * Dynamic loading
	 */
	
	@Given("clicks on the Dynamic loading option")
	public void clicks_on_the_dynamic_loading_option() {
		
		context.getDriver().findElement(By.linkText("Dynamic Loading")).click();
	}
	
	@Then("user navigates to dynamic loading page")
	public void user_navigates_to_dynamic_loading_page() {
		
		String dynamicLoadingPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(dynamicLoadingPageUrl, url+"dynamic_loading", "The dynamic loading page url doesn't match expected");
	}
	
	@Then("user clicks on element on page that is hidden link text")
	public void user_clicks_on_element_on_page_that_is_hidden_link_text() {
		
		context.getDriver().findElement(By.linkText("Example 1: Element on page that is hidden")).click();
	}
	
	@Then("user navigate to element on page that is hidden page")
	public void user_navigate_to_element_on_page_that_is_hidden_page() {
		
		String elementOnPageThatIsHiddenUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(elementOnPageThatIsHiddenUrl, url+"dynamic_loading/1", "The element on page that is hidden url doesn't match expected");
	}
	
	@Then("user clicks on the start button")
	public void user_clicks_on_the_start_button() {
		
		context.getDriver().findElement(By.xpath("//button[text()='Start']")).click();
	}
	
	@Then("hello world message displays")
	public void hello_world_message_displays() {
		
		WebElement element = context.getDriver().findElement(By.xpath("//div[@id='finish']/h4"));
		
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return element.getText().equals("Hello World!");
			}
			
		});
		
		String helloWorldTxt = element.getText();
		Assert.assertEquals(helloWorldTxt, "Hello World!", "The hello world text doesn't match expected");
	}
	
	
	/**
	 * Entry ad
	 */
	
	@Given("clicks on the Entry Ad option")
	public void clicks_on_the_entry_ad_option() {
		
		context.getDriver().findElement(By.linkText("Entry Ad")).click();
	}
	
	@Then("user navigates to entry ad page")
	public void user_navigates_to_entry_ad_page() {
		
		String entryAdPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(entryAdPageUrl, url+"entry_ad", "The entry ad page url doesn't match expected.");
	}
	
	@Then("the enty ad modal display")
	public void the_enty_ad_model_display() {
		
		WebElement entryAdModal = context.getDriver().findElement(By.cssSelector(".modal"));
		boolean isModalAppeared = wait.until(ExpectedConditions.visibilityOf(entryAdModal)).isDisplayed();
		
		if(!isModalAppeared) {
			context.getDriver().findElement(By.linkText("click here")).click();
		}
		
		Assert.assertTrue(isModalAppeared, "Entry ad model is not displayed");
	}
	
	@Then("user clicks on the close button")
	public void user_clicks_on_the_close_button() {
	
		context.getDriver().findElement(By.xpath("//p[text()='Close']")).click();
	}
	
	@Then("the enty ad modal will close")
	public void the_enty_ad_model_will_close() {
		WebElement entryAdModal = context.getDriver().findElement(By.cssSelector(".modal"));
		wait.until(ExpectedConditions.invisibilityOf(entryAdModal));
		
		Assert.assertFalse(entryAdModal.isDisplayed(), "Entry ad model is displayed");
	}
	
	/**
	 * Exit intent modal
	 */
	
	@Given("clicks on the Exit Intent option")
	public void clicks_on_the_exit_intent_option() {
		
		context.getDriver().findElement(By.linkText("Exit Intent")).click();
	}
	
	@Then("user navigates to exit intent page")
	public void user_navigates_to_exit_intent_page() {
		
		String exitIntentPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(exitIntentPageUrl, url+"exit_intent", "The exit intent page url doesn't match expected.");
	}
	
	@Then("the user mouse over to the url bar")
	public void the_user_mouse_over_to_the_url_bar() {
		
//		Actions actions = new Actions(context.getDriver());
		
		JavascriptExecutor js = (JavascriptExecutor) context.getDriver();
//		long windowWidth = (long) js.executeScript("return window.innerWidth;");
//        long windowHeight = (long) js.executeScript("return window.innerHeight;");
//        
//		System.out.println(windowHeight);
//		System.out.println(windowWidth);
//		
////		actions.moveByOffset(0, (int)(windowHeight * -0.9)).perform();
//		actions.moveByOffset((int) windowWidth/2, (int) windowHeight/2).build().perform();
		js.executeScript("document.dispatchEvent(new MouseEvent('mouseleave', { bubbles: true }));");

	}
	
	@Then("the exit intent modal display")
	public void the_exit_intent_modal_display() {
		
		

		WebElement exitIntentModal = context.getDriver().findElement(By.cssSelector("div.modal"));
		
		boolean isExitIntentModalDisplayed = wait.until(ExpectedConditions.visibilityOf(exitIntentModal)).isDisplayed();
		
		Assert.assertTrue(isExitIntentModalDisplayed, "The exit intent popup modal is not displayed");
	}
	
	@Then("the exit intent modal is closed")
	public void the_exit_intent_modal_will_close() {
		
		WebElement exitIntentModal = context.getDriver().findElement(By.cssSelector("div.modal"));
		
		boolean isExitIntentModalDisplayed = wait.until(ExpectedConditions.invisibilityOf(exitIntentModal));
		
		Assert.assertFalse(isExitIntentModalDisplayed, "The exit intent popup modal is displayed");
	}
	
	/**
	 * File upload
	 */
	
	@Given("clicks on the File Upload option")
	public void clicks_on_the_file_upload_option() {
		
		context.getDriver().findElement(By.linkText("File Upload")).click();
	}
	
	@Then("user navigates to file upload page")
	public void user_navigates_to_file_upload_page() {
		
		String fileUploadPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(fileUploadPageUrl, url+"upload", "The file upload page url doesn't match expected");
	}
	
	@Then("the user uploads a file")
	public void the_user_uploads_a_file() {
		
		WebElement fileUploadField = context.getDriver().findElement(By.id("file-upload"));
		
		String filePath = Paths.get("src/test/resources/upload/upload-txt.txt").toAbsolutePath().toString();
		
		fileUploadField.sendKeys(filePath);
		
	}
	
	@Then("clicks on upload button")
	public void clicks_on_upload_button() {
		
		context.getDriver().findElement(By.id("file-submit")).click();
	}
	
	@Then("the user receives success message")
	public void the_user_receives_success_message() {
		
		String fileUploadedTxt = context.getDriver().findElement(By.tagName("h3")).getText();
		
		Assert.assertEquals(fileUploadedTxt, "File Uploaded!", "The file uploaded text doesn't match expected");
	}
	
	@Then("the file name matches")
	public void the_file_name_matches() {
		
		String fileName = context.getDriver().findElement(By.id("uploaded-files")).getText();
		Assert.assertEquals(fileName, "upload-txt.txt", "The file name doesn't match expected");
	}
	
	/**
	 * File download
	 */
	
	@Given("user navigates to the herokuapp menu landing page with custom chrom options")
	public void user_navigates_to_the_herokuapp_menu_landing_page_with_custom_chrom_options() {
		
		String downloadFilePath = Paths.get("src/test/resources/download/").toAbsolutePath().toString();
		
		context.setOption("downloadFilePath", downloadFilePath);
		
		Map<String, Object> prefs = new HashMap<>();
		
		prefs.put("download.default_directory", downloadFilePath);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.directory_upgrade", true);
		prefs.put("safebrowsing.enabled", true);
		
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		
		launchBrowserWithOptions(options);
		context.getDriver().get(url);
	}
	
	@Given("click on the File Download option")
	public void click_on_the_file_download_option() {
		
		context.getDriver().findElement(By.linkText("File Download")).click();
	}
	
	@Then("user navigates to file download page")
	public void user_navigates_to_file_download_page() {
		
		String fileDownloadPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(fileDownloadPageUrl, url+"download", "The file download page url doesn't match expected");
	}
	
	@Then("user clicks on a random file from the list")
	public void user_clicks_on_a_random_file_from_the_list() {
		
		List<WebElement> fileElements = context.getDriver().findElements(By.xpath("//div[@id='content']/div/a"));
		
		Random random = new Random();
		int randomFileNum = random.nextInt(0, fileElements.size());
		
		WebElement fileElement = fileElements.get(randomFileNum);
		
		String fileName = fileElement.getText();
		context.setOption("fileName", fileName);
		
		fileElement.click();
		
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return isFileDownloaded(context.getOptions().get("downloadFilePath").toString(), context.getOptions().get("fileName").toString());
			}
			
		});
		
	}
	
	@Then("file will be downloaded in the specified location")
	public void file_will_be_downloaded_in_the_specified_location() {
		
		File file = new File(context.getOptions().get("downloadFilePath").toString(), context.getOptions().get("fileName").toString());
		
		Assert.assertTrue(file.exists(), "The file is not present in the directory");
	}
	
	/**
	 * Floating Menu
	 */
	
	
	@Given("click on the Floating Menu option")
	public void click_on_the_floating_menu_option() {
		
		context.getDriver().findElement(By.linkText("Floating Menu")).click();
	}
	
	
	@Then("user navigates to floating menu page")
	public void user_navigates_to_floating_menu_page() {
		
		String floatingMenuPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(floatingMenuPageUrl, url+"floating_menu", "The floating menu page url doesn't match expected");
	}
	
	
	@Then("user scroll down to the middle of the page")
	public void user_scroll_down_to_the_middle_of_the_page() {
		
		JavascriptExecutor jse = (JavascriptExecutor) context.getDriver();
		
		Long pageHeight = (Long) jse.executeScript("return document.body.scrollHeight");
		
		jse.executeScript("window.scrollTo(0, arguments[0]);", (int)(pageHeight/2));
	}
	
	
	@Then("clicks on one of the buttons")
	public void clicks_on_one_of_the_buttons() {
		
		List<WebElement> menuOptions = context.getDriver().findElements(By.cssSelector("ul>li"));
		
		Map<String, WebElement> menuOptionsMap = menuOptions.stream().collect(Collectors.toMap(menu -> menu.getText(), menu -> menu));
		
		Random random = new Random();
		
		int menuIndex = random.nextInt(0, menuOptionsMap.size());
		String menuOption = menuOptionsMap.keySet().toArray()[menuIndex].toString();
		
		menuOptionsMap.get(menuOption).click();
		
		String currentPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(currentPageUrl, url+"floating_menu#"+(menuOption.toLowerCase()), "The page url not matching expected based on option selected");
	}
	
	
	@Then("user scroll down to the bottom of the page")
	public void user_scroll_down_to_the_bottom_of_the_page() {
		
		JavascriptExecutor jse = (JavascriptExecutor) context.getDriver();
		
		Long pageHeight = (Long) jse.executeScript("return document.body.scrollHeight");
		System.out.println(pageHeight);
		
		jse.executeScript("window.scrollTo(0, arguments[0]);", pageHeight);
	}
	
	/**
	 * Form authentication
	 */
	
	@Given("click on the Form Authentication option")
	public void click_on_the_form_authentication_option() {
	
		context.getDriver().findElement(By.linkText("Form Authentication")).click();
	}
	
	@Then("user navigates to form authentication page")
	public void user_navigates_to_form_authentication_page() {
		
		String formAuthenticationPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(formAuthenticationPageUrl, url+"login", "The form authencation page url does not match expected");
	}
	
	@Then("user enters {string} username and {string} password")
	public void user_enters_username_and_password(String username, String password) {
		
		context.getDriver().findElement(By.id("username")).sendKeys(username);
		context.getDriver().findElement(By.id("password")).sendKeys(password);
	}
	
	@Then("user clicks on the login button")
	public void user_clicks_on_the_login_button() {
		
		context.getDriver().findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	@Then("login will be successfull and success message displays")
	public void login_will_be_successfull_and_success_message_displays() {
		
		String loginFlashSuccessMsg = context.getDriver().findElement(By.id("flash")).getText();
		
		Assert.assertTrue(loginFlashSuccessMsg.contains("You logged into a secure area!"), "The form authentication login success msg does not match");
		
		String securePageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(securePageUrl, url.replace("admin@", "").replace("admin:", "")+"secure", "The secure page url does not match the expected");
	}
	
	@Then("user clicks on the logout page")
	public void user_clicks_on_the_logout_page() {
		
		context.getDriver().findElement(By.xpath("//i[text()=' Logout']")).click();
	}
	
	@Then("user navigates to the form authencation and success message displays")
	public void user_navigates_to_the_form_authencation_and_success_message_displays() {
		
		String logoutFlashSuccessMsg = context.getDriver().findElement(By.id("flash")).getText();
		
		Assert.assertTrue(logoutFlashSuccessMsg.contains("You logged out of the secure area!"));
	}
	
	
	/**
	 * Frames
	 */
	
	@Given("clicks on the Frames option")
	public void clicks_on_the_frames_option() {
		
		context.getDriver().findElement(By.linkText("Frames")).click();
	}
	
	@Then("user navigates to frames page")
	public void user_navigates_to_frames_page() {
		
		String framesPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(framesPageUrl, url+"frames", "Frames page url doesn't match the expected");
	}
	
	@Then("user clicks on the nested frames option")
	public void user_clicks_on_the_nested_frames_option() {
		
		context.getDriver().findElement(By.linkText("Nested Frames")).click();
	}
	
	@Then("user navigates to nested frames page")
	public void user_navigates_to_nested_frames_page() {
		
		String nestedFramePageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(nestedFramePageUrl, url+"nested_frames", "Nested frames page url doesn't match expected");
	}
	
	@Then("user switchs between the frames")
	public void user_switchs_between_the_frames() {
		
		List<WebElement> outerFrames = context.getDriver().findElements(By.cssSelector("frameset>frame"));
		
		// Switch to top frame and find inner frames
		
		WebElement topFrame = outerFrames.getFirst();
		WebDriver topFrameDriver = context.getDriver().switchTo().frame(topFrame);
		
		List<WebElement> topInnerFrames = topFrameDriver.findElements(By.cssSelector("frameset>frame"));
		
		for(WebElement i: topInnerFrames) {

			WebDriver subFrameDriver = topFrameDriver.switchTo().frame(i);
			String text = subFrameDriver.findElement(By.tagName("body")).getText();
			
			System.out.println(text);
			
			//Switching back to top frame from subframe
			subFrameDriver.switchTo().parentFrame();
		}
		
		topFrameDriver.switchTo().parentFrame();
		
		
		WebElement bottomFrame = outerFrames.getLast();
		
		WebDriver bottomFrameDriver = context.getDriver().switchTo().frame(bottomFrame);
		
		String text = bottomFrameDriver.findElement(By.tagName("body")).getText();
		
		System.out.println(text);
		bottomFrameDriver.switchTo().parentFrame();
	}
	
	@Then("user navigates back to frames page")
	public void user_navigates_back_to_frames_page() {
		
		context.getDriver().navigate().back();
	}
	
	@Then("user clicks on the ifrmaes option")
	public void user_clicks_on_the_ifrmaes_option() {
		
		context.getDriver().findElement(By.linkText("iFrame")).click();
	}
	
	@Then("user navigates to iframe page")
	public void user_navigates_to_iframe_page() {
		
		String iframePageUrl = context.getDriver().getCurrentUrl();
		Assert.assertEquals(iframePageUrl, url+"iframe", "Iframe page url does not match the expected url");
	}
	
	/**
	 * Horizontal slider
	 */
	
	@Given("clicks on the Horizontal Slider option")
	public void clicks_on_the_horizontal_slider_option() {
		
		context.getDriver().findElement(By.linkText("Horizontal Slider")).click();
	}
	
	@Then("user navigates to holizontal slider page")
	public void user_navigates_to_holizontal_slider_page() {
		
		String horizontalSliderPageUrl = context.getDriver().getCurrentUrl();
		Assert.assertEquals(horizontalSliderPageUrl, url+"horizontal_slider", "The horizontal slider page url doesn't match the expected!");
	}
	
	@Then("user slides the slider to the extreme right then to the extreme left using keys")
	public void user_slides_the_slider_to_the_extreme_right_then_to_the_extreme_left_using_keys() {
		
		context.getDriver().findElement(By.xpath("//input[@type='range']")).click();
		
		try {
			Robot robot = new Robot();
			
			WebElement readingWebElement = context.getDriver().findElement(By.id("range"));
			
			float initialValue = Float.parseFloat(readingWebElement.getText().trim());
			
			float value = initialValue;
			
			while(value < 5) {
				
				initialValue = value;
				
				robot.keyPress(KeyEvent.VK_RIGHT);
				robot.keyRelease(KeyEvent.VK_RIGHT);
				
				Thread.sleep(1000);

				value = Float.parseFloat(readingWebElement.getText().trim());
				
				Assert.assertEquals(value, initialValue + 0.5, "The value does not meet the expected");
			}
			
			while(value > 0) {
				
				initialValue = value;
				
				robot.keyPress(KeyEvent.VK_LEFT);
				robot.keyRelease(KeyEvent.VK_LEFT);
				
				Thread.sleep(1000);

				value = Float.parseFloat(readingWebElement.getText().trim());
				
				Assert.assertEquals(value, initialValue - 0.5, "The reading does not meet expected");
			}
			
		} catch (AWTException e) {
			Assert.assertTrue(false, "This test failed do to AWTExpection");
		} catch (InterruptedException e) {
			Assert.assertTrue(false, "This test failed do to InterruptedException");
		}
	}
	
	@Then("user slides the slider to the extreme right then to the extreme left using drag and drop")
	public void user_slides_the_slider_to_the_extreme_right_then_to_the_extreme_left_using_drag_and_drop() {
		
		WebElement sliderElement = context.getDriver().findElement(By.xpath("//input[@type='range']"));

		Actions actions = new Actions(context.getDriver());
		
		actions.dragAndDropBy(sliderElement, 100, 0);
		actions.perform();
		
		actions.dragAndDropBy(sliderElement, 0, 0);
		actions.perform();
	}
	
	/**
	 * Hovers
	 */
	
	@Given("clicks on the Hovers option")
	public void clicks_on_the_hovers_option() {
		
		context.getDriver().findElement(By.linkText("Hovers")).click();
	}
	
	
	@Then("user navigates to hovers page")
	public void user_navigates_to_hovers_page() {
		
		String hoversPageUrl = context.getDriver().getCurrentUrl();
		Assert.assertEquals(hoversPageUrl, url+"hovers", "The hovers page url doesn't match the expected!");
	}
	
	
	@Then("user mouse over each image and view additional information")
	public void user_mouse_over_each_image_and_view_additional_information() {
	
		List<WebElement> figures = context.getDriver().findElements(By.cssSelector("div.figure"));
		
		Actions actions = new Actions(context.getDriver());
		
		int userId = 1;
		
		for(WebElement figure: figures) {
			
			actions.moveToElement(figure).perform();
			
			WebElement figcaptionElement = figure.findElement(By.cssSelector("div.figcaption"));
			
			
			Assert.assertTrue(figcaptionElement.isDisplayed(), "The additional information does not appear on mouse over");
			
			
			String username = figcaptionElement.findElement(By.tagName("h5")).getText();
			Assert.assertEquals(username, "name: user"+userId, "The username does not match expected");
			
			
			String hrefValue = figcaptionElement.findElement(By.tagName("a")).getAttribute("href");
			Assert.assertEquals(hrefValue, url+"users/"+userId, "The link does not match expected");
			
			userId++;
		}
	}
	
	
	/**
	 * Infinite Scroll
	 */
	
	
	@Given("clicks on the Infinite Scroll option")
	public void clicks_on_the_infinite_scroll_option() {
		
		context.getDriver().findElement(By.linkText("Infinite Scroll")).click();
	}
	
	
	@Then("user navigates to infinite scroll page")
	public void user_navigates_to_infinite_scroll_page() {
		
		String infiniteScrollPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(infiniteScrollPageUrl, url+"infinite_scroll", "The infinite scroll page url doesn't match the expected");
	}
	
	
	@Then("user scroll down until he reaches {int} length height")
	public void user_scroll_down_until_he_reaches_length_height(Integer scrollTo) {
		
		JavascriptExecutor jse = (JavascriptExecutor) context.getDriver();
		
		Long scrollHeight = Long.valueOf(String.valueOf(jse.executeScript("return document.body.scrollHeight")));

		
		while(scrollHeight <= scrollTo) {
//			System.out.println(scrollHeight);
			
			jse.executeScript("window.scrollBy(0, arguments[0]);", scrollHeight);
			
			Utils.sleep(2000);
			scrollHeight = Long.valueOf(String.valueOf(jse.executeScript("return document.body.scrollHeight")));
		}
	}
	
	
	/**
	 * JQuery elements
	 */
	
	@Given("clicks on the JQuery UI Menus option")
	public void clicks_on_the_j_query_ui_menus_option() {
		
		context.getDriver().findElement(By.linkText("JQuery UI Menus")).click();
	}
	
	
	@Then("user navigates to jquery ui menus page")
	public void user_navigates_to_jquery_ui_menus_page() {
		
		String jqueryMenuPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(jqueryMenuPageUrl, url+"jqueryui/menu", "The jquery ui menu page url does not meet the expected url");
	}
	
	
	@Then("user mouse over enabled option and downloads a file")
	public void user_mouse_over_enabled_option_and_downloads_a_file() {
		
		WebElement enabledJqueryElement = context.getDriver().findElement(By.cssSelector("li[id='ui-id-3']>a"));
		
		Actions actions = new Actions(context.getDriver());
		
		actions.moveToElement(enabledJqueryElement).perform();
		Utils.sleep(3000);
		
		WebElement downloadElement = context.getDriver().findElement(By.cssSelector("li[id='ui-id-4']>a"));
		
		actions.moveToElement(downloadElement).perform();
		
		Utils.sleep(3000);
		
		List<WebElement> downloadFileElements = context.getDriver().findElements(By.xpath("//*[contains(@id, 5) or contains(@id, 6) or contains(@id, 7)]"));
		
		Random random = new Random();
		int randomFileIndex = random.nextInt(0, downloadFileElements.size());
		
		WebElement fileElement = downloadFileElements.get(randomFileIndex);
		
		String hrefValue = fileElement.findElement(By.tagName("a")).getAttribute("href");
		String fileName = hrefValue.replaceAll(".*/", "");
		System.out.println(fileName);
		
		context.getOptions().put("fileName", fileName);
		
		fileElement.click();
		
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return isFileDownloaded(context.getOptions().get("downloadFilePath").toString(), context.getOptions().get("fileName").toString());
			}
			
		});
		
		File file = new File(context.getOptions().get("downloadFilePath").toString(), context.getOptions().get("fileName").toString());
		
		Assert.assertTrue(file.exists(), "The file is not present in the directory");
	}
	
	
	@Then("user mouse over enabled option and clicks on back jquery ui menu option")
	public void user_mouse_over_enabled_option_and_clicks_on_back_jquery_ui_menu_option() {
	
		context.getDriver().navigate().refresh();
		
		WebElement enabledJqueryElement = context.getDriver().findElement(By.cssSelector("li[id='ui-id-3']>a"));
		
		Actions actions = new Actions(context.getDriver());
		
		actions.moveToElement(enabledJqueryElement).perform();
		Utils.sleep(3000);
		
		WebElement backToJqueryUiElement = context.getDriver().findElement(By.cssSelector("li[id='ui-id-8']>a"));
		
		backToJqueryUiElement.click();
		
		Utils.sleep(3000);
	}
	
	
	@Then("user navigates jqueryui page")
	public void user_navigates_jqueryui_page() {
	
		String jqueryUiPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(jqueryUiPageUrl, url+"jqueryui", "The jqueryui page url doesn't match the expected url");
	}
	
	
	
	/**
	 * Javascript alerts
	 */
	
	
	@Given("clicks on the JavaScript Alerts option")
	public void clicks_on_the_java_script_alerts_option() {
		
		context.getDriver().findElement(By.linkText("JavaScript Alerts")).click();
	}
	
	
	@Then("user navigates to the javascript alerts page")
	public void user_navigates_to_the_javascript_alerts_page() {
		
		String jsAlertsPopupPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(jsAlertsPopupPageUrl, url+"javascript_alerts", "Javascript alerts page url doesn't match the expected");
	}
	
	
	@Then("user clicks on the js alert button")
	public void user_clicks_on_the_js_alert_button() {
		
		context.getDriver().findElement(By.cssSelector("ul > li:nth-child(1) > button")).click();
	}
	
	
	@Then("js alert popup displays and user accepts it")
	public void js_alert_popup_displays_and_user_accepts_it() {

		Alert alert= context.getDriver().switchTo().alert();
		alert.accept();
	}
	
	
	@Then("user clicks on the js confirm button")
	public void user_clicks_on_the_js_confirm_button() {
		
		context.getDriver().findElement(By.cssSelector("ul > li:nth-child(2) > button")).click();
	}
	
	
	@Then("js confirm popup displays and user accepts it")
	public void js_confirm_popup_displays_and_user_accepts_it() {

		Alert alert = context.getDriver().switchTo().alert();
		String jsConfirmPopupText = alert.getText();
		
		Assert.assertEquals(jsConfirmPopupText, "I am a JS Confirm", "Text of JS Confirm popup doesn't match expected");
		alert.accept();
	}
	
	
	@Then("user clicks on the js prompt button")
	public void user_clicks_on_the_js_prompt_button() {
	
		context.getDriver().findElement(By.cssSelector("ul > li:nth-child(3) > button")).click();
	}
	
	
	@Then("js prompt popup displays user enters {string} and accepts it")
	public void js_prompt_popup_displays_user_enters_and_accepts_it(String message) {
		Alert alert = context.getDriver().switchTo().alert();

		alert.sendKeys(message);
		
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) context.getDriver();
//		javascriptExecutor.executeScript("window.prompt = function() { return '" + message + "'; };");


		String jsPromptPopupText = alert.getText();
		
		Assert.assertEquals(jsPromptPopupText, "I am a JS prompt", "Text on JS Prompt popup doesn't match expected");

		
		alert.accept();
	}
	
	
	/**
	 * Javascript error
	 */
	
	
	@Given("clicks on the JavaScript onload event error option")
	public void clicks_on_the_java_script_onload_event_error_option() {
		
		context.getDriver().findElement(By.linkText("JavaScript onload event error")).click();
	}
	
	
	@Then("user navigates to the javascript error page")
	public void user_navigates_to_the_javascript_error_page() {
		
		String javascriptErrorPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(javascriptErrorPageUrl, url+"javascript_error", "Javascript error page url doesn't match the expected");
	}
	
	
	@Then("javascript error displays on the page")
	public void javascript_error_displays_on_the_page() {
		
		String expectedJavascriptErrorTxt = "This page has a JavaScript error in the onload event. This is often a problem to using normal Javascript injection techniques.";
		String actualJavascriptErrorTxt = context.getDriver().findElement(By.tagName("p")).getText();
		
		Assert.assertEquals(actualJavascriptErrorTxt, expectedJavascriptErrorTxt, "The javascript error text does not match");
	}
	
	
	/**
	 * Key presses
	 */
	
	@Given("clicks on the Key Presses option")
	public void clicks_on_the_key_presses_option() {
		
		context.getDriver().findElement(By.linkText("Key Presses")).click();
	}
	
	
	@Then("user navigates to the key presses page")
	public void user_navigates_to_the_key_presses_page() {
		
		String keyPressesPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(keyPressesPageUrl, url+"key_presses", "The key presses page url doesn't match expected");
	}
	
	
	@Then("user types a character into the field")
	public void user_types_a_character_into_the_field() {
		
		try {
			Robot robot = new Robot();
			
			context.getDriver().findElement(By.id("target")).click();
			
			robot.keyPress(KeyEvent.VK_M);
			robot.keyRelease(KeyEvent.VK_M);
			
		} catch (AWTException e) {
			Assert.assertTrue(false, "Encountered AWTException");
		}
	}
	
	
	@Then("you entered message will display with the key typed in the field")
	public void you_entered_message_will_display_with_the_key_typed_in_the_field() {
		
		String result = context.getDriver().findElement(By.id("result")).getText();

		Assert.assertEquals(result, "You entered: M", "The result message is wrong.");
	}
	
	
	@Then("user enters the backspace in the field")
	public void user_enters_the_backspace_in_the_field() {
		
		try {
			Robot robot = new Robot();
			
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			
		} catch (AWTException e) {
			Assert.assertTrue(false, "Encountered AWTException");
		}
	}
	
	
	@Then("you entered message will display with backspace")
	public void you_entered_message_will_display_with_backspace() {
		
		String resultTxtMsg = context.getDriver().findElement(By.id("result")).getText();

		Assert.assertEquals(resultTxtMsg, "You entered: BACK_SPACE", "The result message is wrong ");
	}
	
	
	@Then("user types number into the field")
	public void user_types_number_into_the_field() {
		
		try {
			Robot robot = new Robot();
			
			robot.keyPress(KeyEvent.VK_9);
			robot.keyRelease(KeyEvent.VK_9);
		} catch (AWTException e) {
			Assert.assertTrue(false, "Encountered AWTException");
		}
	}
	
	
	@Then("you entered message will display with the number typed in the field")
	public void you_entered_message_will_display_with_the_number_typed_in_the_field() {
		
		String resultTxtMsg = context.getDriver().findElement(By.id("result")).getText();

		Assert.assertEquals(resultTxtMsg, "You entered: 9", "The result message is wrong ");
	}
	
	
	/**
	 * Multiple windows
	 */
	
	@Given("clicks on the Multiple Windows option")
	public void clicks_on_the_multiple_windows_option() {
		
		context.getDriver().findElement(By.linkText("Multiple Windows")).click();
	}
	
	
	@Then("user navigates to the multiple windowns page")
	public void user_navigates_to_the_multiple_windowns_page() {
		
		String multipleWindowsPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(multipleWindowsPageUrl, url+"windows", "The multiple windows page url does not match the expected");
	}
	
	
	@Then("user clicks on the click here link")
	public void user_clicks_on_the_click_here_link() {
		
		context.getDriver().findElement(By.partialLinkText("Click ")).click();
		Utils.sleep(5000);
	}
	
	private Map<String, String> windowsMap;
	
	@Then("user will automatically navigate to new window")
	public void user_will_automatically_navigate_to_new_window() {
	
		Set<String> windows = context.getDriver().getWindowHandles();
		windowsMap = windows.stream()
				.collect(
						Collectors.toMap(
								window -> context.getDriver().switchTo().window(window).getTitle(), 
								window -> window)
						);
		
		context.getDriver().switchTo().window(windowsMap.get("New Window"));
		String newWindowTitle = context.getDriver().getTitle();

		Assert.assertEquals(newWindowTitle, "New Window", "The title of new window page does not match expected");
	}
	
	
	@Then("user navigate back to parent window")
	public void user_navigate_back_to_parent_window() {
		
		String parentWindow = windowsMap.get("The Internet");
		
		context.getDriver().switchTo().window(parentWindow);
		
		String currentWindowTitle = context.getDriver().getTitle();
		
		Assert.assertEquals(currentWindowTitle, "The Internet", "The user did not navigate to parent window");
		
//		context.getDriver().switchTo().window(windowsMap.get("New Window")).close();
	}
	
	
	/**
	 * Notification messages
	 */
	
	
	@Given("clicks on the Notification Messages option")
	public void clicks_on_the_notification_messages_option() {
		
		context.getDriver().findElement(By.linkText("Notification Messages")).click();
	}
	
	
	@Then("user navigates to the notification messages page")
	public void user_navigates_to_the_notification_messages_page() {
		
		String notificationMessagePageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(notificationMessagePageUrl, url.replace("admin@", "").replace("admin:", "")+"notification_message_rendered", "The notification messages page url does not match expected");
	}
	
	
	@Then("one of the following messages will shows on the page")
	public void one_of_the_following_messages_will_shows_on_the_page(List<String> messages) {

		WebElement unsuccessfulMessageWebEle = context.getDriver().findElement(By.id("flash"));
		
		JavascriptExecutor jse = (JavascriptExecutor) context.getDriver();
		
		String actionMessage = (String) jse.executeScript("return arguments[0].childNodes[0].nodeValue.trim()", unsuccessfulMessageWebEle);
		
		Assert.assertListContainsObject(messages, actionMessage, "The "+actionMessage+" is not one of expected");
	}
	
	
	/**
	 * Large & Deep Dom
	 */
	
	@Given("clicks on the Large & Deep Dom option")
	public void clicks_on_the_large_deep_dom_option() {
		
		context.getDriver().findElement(By.linkText("Large & Deep DOM")).click();
	}
	
	
	@Then("user navigates to the large and deep dom page")
	public void user_navigates_to_the_large_and_deep_dom_page() {
		
		String largeAndDeepDomPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(largeAndDeepDomPageUrl, url+"large", "Large and deep dom page url doesn't meet expected");
	}
	
	
	@Then("user reads the value from {string} column and the values meet expected")
	public void user_reads_the_value_from_column_and_the_values_meet_expected(String columnName) {
		
		WebElement largeTable = context.getDriver().findElement(By.id("large-table"));
		
		List<WebElement> largeTableHeaders = largeTable.findElements(By.cssSelector("tr > th")); //tr > th:nth-child(1)
		
		AtomicInteger columnCounter = new AtomicInteger(1);
		
		Map<String, Integer> headerNumbers = largeTableHeaders.stream().collect(
				Collectors.toMap(
						header -> header.getText(), 
						header -> columnCounter.getAndIncrement()
						)
				);

		Integer column = headerNumbers.get(columnName);
		
		List<WebElement> cells = context.getDriver().findElements(By.cssSelector("tr > td:nth-child("+column+")"));
		
		String regex = "^[0-9]+\\."+columnName+"$";
		Pattern pattern = Pattern.compile(regex);
		
		int increment = 1;
		for(WebElement cell: cells) {

			String cellText = cell.getText();
			Matcher matcher = pattern.matcher(cellText);
//			System.out.println(cellText);
			Assert.assertTrue(matcher.matches(), "The value in the cell doesn't match expected");
//			System.out.println(cellText.replaceAll("\\.[0-9]+$", ""));
			Assert.assertEquals(Integer.valueOf(cellText.replaceAll("\\.[0-9]+$", "")), increment++, "The base value doesn't match");
		}
	}
	

	@Then("user reads the value from {string} row and the values meet expected")
	public void user_reads_the_value_from_row_and_the_values_meet_expected(String rowName) {

		WebElement largeTable = context.getDriver().findElement(By.id("large-table"));
		
		List<WebElement> firstColumnCells = largeTable.findElements(By.cssSelector("tr > td:nth-child(1)"));
		
		AtomicInteger row = new AtomicInteger(1);
		
		Map<String, Integer> firstColumnCellPositions = firstColumnCells.stream()
				.collect(
						Collectors.toMap(
							cell -> cell.getText().replaceAll("\\.[0-9]+$", ""),
							cell -> row.getAndIncrement()
							)
						);
		
		Integer rowPosition = firstColumnCellPositions.get(rowName);
		
		List<WebElement> rowCells = largeTable.findElements(By.cssSelector("tr:nth-child("+rowPosition+") > td"));
		
		int increment = 1;
		
		for(WebElement rowCell: rowCells) {
			
			String cellText = rowCell.getText();
//			System.out.println(cellText);
			Pattern.matches("^"+rowPosition+"\\.[0-9]+$", cellText);
//			System.out.println(cellText.replaceAll("^[0-9]+\\.", ""));
			Assert.assertEquals(Integer.valueOf(cellText.replaceAll("^[0-9]+\\.", "")), increment++, "The decimal places are not matching");
		}
	}

	
	/**
	 * Sortable data tables
	 */
	
	@Given("clicks on the Sortable Data Tables option")
	public void clicks_on_the_sortable_data_tables_option() {
		
		context.getDriver().findElement(By.linkText("Sortable Data Tables")).click();
	}
	
	
	@Then("user navigates to the tables page")
	public void user_navigates_to_the_tables_page() {
		
		String tablesPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(tablesPageUrl, url+"tables", "The tables page url doesn't match the expected");
	}
	
	
	@Then("user sorts the data in the example1 table by last name in asc")
	public void user_sorts_the_data_in_the_example1_table_by_last_name_in_asc() {
		
		WebElement table1 = context.getDriver().findElement(By.id("table1"));
		
		List<WebElement> headerElements = table1.findElements(By.className("header"));
		
		Map<String, WebElement> headerElementsMapper = headerElements.stream()
						.collect(
								Collectors.toMap(
										headerEle -> headerEle.getText(), 
										headerEle -> headerEle
										)
								);
		
		AtomicInteger table1Indexer = new AtomicInteger(1);
		
		Map<String, Integer> headerIndexMapper = headerElements.stream()
						.collect(
								Collectors.toMap(
										headerEle -> headerEle.getText(), 
										headerEle -> table1Indexer.getAndIncrement()
										)
								);

		WebElement lastNameHeader = headerElementsMapper.get("Last Name");
		String classAttrValue = lastNameHeader.getAttribute("class");
		
		if(!classAttrValue.contains("headerSortDown")) {
			lastNameHeader.click();			
		}
		
		List<WebElement> lastNameCells = table1.findElements(
				By.cssSelector("tbody>tr>td:nth-child("+headerIndexMapper.get("Last Name")+")")
				);
		List<String> lastNames = lastNameCells.stream().map(cell -> cell.getText()).collect(Collectors.toList());
		System.out.println(lastNames);
		Assert.assertTrue(Utils.isSortedAsc(lastNames), "The last names in the example1 table are not sorted in asc");
	}
	
	
	@Then("user sorts the data in the example1 table by last name in desc")
	public void user_sorts_the_data_in_the_example1_table_by_last_name_in_desc() {
		
		WebElement table1 = context.getDriver().findElement(By.id("table1"));
		
		List<WebElement> headers = table1.findElements(By.className("header"));
		
		Map<String, WebElement> headerElementsMapper = headers.stream().collect(
				Collectors.toMap(
						header -> header.getText(), 
						header -> header
						)
				);
		
		AtomicInteger indexer = new AtomicInteger(1);
		
		Map<String, Integer> headerElementsIndexes = headers.stream().collect(
				Collectors.toMap(
						header -> header.getText(), 
						header -> indexer.getAndIncrement()
						)
				);
		
		WebElement lastNameElement = headerElementsMapper.get("Last Name");
		String lastNameClsAttrValue = lastNameElement.getAttribute("class");

		if(!lastNameClsAttrValue.contains("headerSortUp")) {
			lastNameElement.click();
		}
		List<WebElement> cells = table1.findElements(
				By.cssSelector("tbody>tr>td:nth-of-type("+headerElementsIndexes.get("Last Name")+")")
				);
		List<String> lastNames = cells.stream().map(cell -> cell.getText()).collect(Collectors.toList());
		System.out.println(lastNames);
		Assert.assertTrue(Utils.isSortedDesc(lastNames), "The last names in the example 1 table are not sorted in desc");
	}
	
	
	@Then("user sorts the data in the example1 table by first name in asc")
	public void user_sorts_the_data_in_the_example1_table_by_first_name_in_asc() {
		
		WebElement table1 = context.getDriver().findElement(By.id("table1"));
		
		List<WebElement> headerElements = table1.findElements(By.className("header"));
		
		Map<String, WebElement> headerElementsMapper = headerElements.stream()
						.collect(
								Collectors.toMap(
										headerEle -> headerEle.getText(), 
										headerEle -> headerEle
										)
								);
		
		AtomicInteger table1Indexer = new AtomicInteger(1);
		
		Map<String, Integer> headerIndexMapper = headerElements.stream()
						.collect(
								Collectors.toMap(
										headerEle -> headerEle.getText(), 
										headerEle -> table1Indexer.getAndIncrement()
										)
								);

		WebElement firstNameHeader = headerElementsMapper.get("First Name");
		String firstNameClsAttrValue = firstNameHeader.getAttribute("class");
		
		if(!firstNameClsAttrValue.contains("headerSortDown")) {
			firstNameHeader.click();			
		}
		
		List<WebElement> firstNameCells = table1.findElements(
				By.cssSelector("tbody>tr>td:nth-child("+headerIndexMapper.get("First Name")+")")
				);
		List<String> firstNames = firstNameCells.stream().map(cell -> cell.getText()).collect(Collectors.toList());
		System.out.println(firstNames);
		Assert.assertTrue(Utils.isSortedAsc(firstNames), "The first names in the example1 table are not sorted in asc");
	}
	
	
	@Then("user sorts the data in the example1 table by first name in desc")
	public void user_sorts_the_data_in_the_example1_table_by_first_name_in_desc() {
		
		WebElement table1 = context.getDriver().findElement(By.id("table1"));
		
		List<WebElement> headers = table1.findElements(By.className("header"));
		
		Map<String, WebElement> headerElementsMapper = headers.stream().collect(
				Collectors.toMap(
						header -> header.getText(), 
						header -> header
						)
				);
		
		AtomicInteger indexer = new AtomicInteger(1);
		
		Map<String, Integer> headerElementsIndexes = headers.stream().collect(
				Collectors.toMap(
						header -> header.getText(), 
						header -> indexer.getAndIncrement()
						)
				);
		
		WebElement firstNameElement = headerElementsMapper.get("First Name");
		String lastNameClsAttrValue = firstNameElement.getAttribute("class");

		if(!lastNameClsAttrValue.contains("headerSortUp")) {
			firstNameElement.click();
		}
		List<WebElement> cells = table1.findElements(
				By.cssSelector("tbody>tr>td:nth-of-type("+headerElementsIndexes.get("First Name")+")")
				);
		List<String> firstNames = cells.stream().map(cell -> cell.getText()).collect(Collectors.toList());
		System.out.println(firstNames);
		Assert.assertTrue(Utils.isSortedDesc(firstNames), "The first names in the example 1 table are not sorted in desc");
	}
	
	
	@Then("user sorts the data in the example1 table by email in asc")
	public void user_sorts_the_data_in_the_example1_table_by_email_in_asc() {
		
		WebElement table1 = context.getDriver().findElement(By.id("table1"));
		
		List<WebElement> headerElements = table1.findElements(By.className("header"));
		
		Map<String, WebElement> headerElementsMapper = headerElements.stream()
						.collect(
								Collectors.toMap(
										headerEle -> headerEle.getText(), 
										headerEle -> headerEle
										)
								);
		
		AtomicInteger table1Indexer = new AtomicInteger(1);
		
		Map<String, Integer> headerIndexMapper = headerElements.stream()
						.collect(
								Collectors.toMap(
										headerEle -> headerEle.getText(), 
										headerEle -> table1Indexer.getAndIncrement()
										)
								);

		WebElement emailHeader = headerElementsMapper.get("Email");
		String emailClsAttrValue = emailHeader.getAttribute("class");
		
		if(!emailClsAttrValue.contains("headerSortDown")) {
			emailHeader.click();			
		}
		
		List<WebElement> emailCells = table1.findElements(
				By.cssSelector("tbody>tr>td:nth-child("+headerIndexMapper.get("Email")+")")
				);
		List<String> emails = emailCells.stream().map(cell -> cell.getText()).collect(Collectors.toList());
		System.out.println(emails);
		Assert.assertTrue(Utils.isSortedAsc(emails), "The emails in the example1 table are not sorted in asc");
	}
	
	
	@Then("user sorts the data in the example1 table by email in desc")
	public void user_sorts_the_data_in_the_example1_table_by_email_in_desc() {
		
		WebElement table1 = context.getDriver().findElement(By.id("table1"));
		
		List<WebElement> headers = table1.findElements(By.className("header"));
		
		Map<String, WebElement> headerElementsMapper = headers.stream().collect(
				Collectors.toMap(
						header -> header.getText(), 
						header -> header
						)
				);
		
		AtomicInteger indexer = new AtomicInteger(1);
		
		Map<String, Integer> headerElementsIndexes = headers.stream().collect(
				Collectors.toMap(
						header -> header.getText(), 
						header -> indexer.getAndIncrement()
						)
				);
		
		WebElement emailElement = headerElementsMapper.get("Email");
		String lastNameClsAttrValue = emailElement.getAttribute("class");

		if(!lastNameClsAttrValue.contains("headerSortUp")) {
			emailElement.click();
		}
		List<WebElement> cells = table1.findElements(
				By.cssSelector("tbody>tr>td:nth-of-type("+headerElementsIndexes.get("Email")+")")
				);
		List<String> emails = cells.stream().map(cell -> cell.getText()).collect(Collectors.toList());
		System.out.println(emails);
		Assert.assertTrue(Utils.isSortedDesc(emails), "The emails in the example 1 table are not sorted in desc");
	}
	

	@Then("user sorts the data in the example1 table by due in asc")
	public void user_sorts_the_data_in_the_example1_table_by_due_in_asc() {
		
		WebElement table1 = context.getDriver().findElement(By.id("table1"));
		
		List<WebElement> headerElements = table1.findElements(By.className("header"));
		
		Map<String, WebElement> headerElementsMapper = headerElements.stream()
						.collect(
								Collectors.toMap(
										headerEle -> headerEle.getText(), 
										headerEle -> headerEle
										)
								);
		
		AtomicInteger table1Indexer = new AtomicInteger(1);
		
		Map<String, Integer> headerIndexMapper = headerElements.stream()
						.collect(
								Collectors.toMap(
										headerEle -> headerEle.getText(), 
										headerEle -> table1Indexer.getAndIncrement()
										)
								);

		WebElement dueHeader = headerElementsMapper.get("Due");
		String dueClsAttrValue = dueHeader.getAttribute("class");
		
		if(!dueClsAttrValue.contains("headerSortDown")) {
			dueHeader.click();			
		}
		
		List<WebElement> dueCells = table1.findElements(
				By.cssSelector("tbody>tr>td:nth-child("+headerIndexMapper.get("Due")+")")
				);
		List<Float> dues = dueCells.stream().map(cell -> Float.valueOf(cell.getText().replaceFirst("^\\$", ""))).collect(Collectors.toList());
		System.out.println(dues);
		Assert.assertTrue(Utils.isSortedAsc(dues), "The dues in the example1 table are not sorted in asc");
	}
	
	
	@Then("user sorts the data in the example1 table by due in desc")
	public void user_sorts_the_data_in_the_example1_table_by_due_in_desc() {
		
		WebElement table1 = context.getDriver().findElement(By.id("table1"));
		
		List<WebElement> headers = table1.findElements(By.className("header"));
		
		Map<String, WebElement> headerElementsMapper = headers.stream().collect(
				Collectors.toMap(
						header -> header.getText(), 
						header -> header
						)
				);
		
		AtomicInteger indexer = new AtomicInteger(1);
		
		Map<String, Integer> headerElementsIndexes = headers.stream().collect(
				Collectors.toMap(
						header -> header.getText(), 
						header -> indexer.getAndIncrement()
						)
				);
		
		WebElement dueElement = headerElementsMapper.get("Due");
		String dueClsAttrValue = dueElement.getAttribute("class");

		if(!dueClsAttrValue.contains("headerSortUp")) {
			dueElement.click();
		}
		List<WebElement> cells = table1.findElements(
				By.cssSelector("tbody>tr>td:nth-of-type("+headerElementsIndexes.get("Due")+")")
				);
		List<Float> dues = cells.stream().map(cell -> Float.valueOf(cell.getText().replaceFirst("^\\$", ""))).collect(Collectors.toList());
		System.out.println(dues);
		Assert.assertTrue(Utils.isSortedDesc(dues), "The dues in the example 1 table are not sorted in desc");
	}
	
	
	
	/**
	 * Shadow DOM
	 */
	
	@Given("clicks on the Shadow DOM option")
	public void clicks_on_the_shadow_dom_option() {
		
		context.getDriver().findElement(By.linkText("Shadow DOM")).click();
	}
	
	
	@Then("user navigates to the shadown dom page")
	public void user_navigates_to_the_shadown_dom_page() {
		
		String shadowDOMPageUrl = context.getDriver().getCurrentUrl();
		
		Assert.assertEquals(shadowDOMPageUrl, url+"shadowdom", "Shadowdom page url does not match the expected");
	}
	
	
	@Then("user reads the text from shadow doms")
	public void user_reads_the_text_from_first_shadow_dom() {
		
		List<WebElement> shadowHosts = context.getDriver().findElements(By.tagName("my-paragraph"));
		
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) context.getDriver();
		
		for(WebElement shadowHost: shadowHosts) {
			
			String slotText = (String) javascriptExecutor.executeScript(
					"let shadowRoot = arguments[0].shadowRoot; " +
							"let slot = shadowRoot.querySelector('slot[name=\"my-text\"]'); " +
							"if (slot) { " +
							"  let assignedNodes = slot.assignedNodes({flatten: true}); " +
							"  return assignedNodes.map(node => node.textContent.trim()).join(' '); " +
							"} else { " +
							"  return null; " +
							"}",
							shadowHost
					);
			
			System.out.println(slotText);
		}
		
	}
}