package hooks;

import config.initialization.InitDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import stepdefinations.login.LoginSteps;

public class Hooks {
	
	private InitDriver initDriver;
	
	public Hooks(InitDriver driver) {
		this.initDriver = driver;
	}

	@Before(
			order=1, 
			value= "not @login_not_required"
			)
	public void login() throws Exception {
		LoginSteps loginSteps = new LoginSteps(initDriver);
		
		loginSteps.user_opens_the_favourite_browser_and_types_url();
		loginSteps.the_login_page_appears();
		loginSteps.user_enters_username_password_and_clicks_on_login();
		loginSteps.the_user_navigate_to_home_or_landing_page();
	}
	
	
	@After(
			order=10
			)
	public void tearDown() {
		if(initDriver.getDriver() != null)
			initDriver.getDriver().close();
	}
}
