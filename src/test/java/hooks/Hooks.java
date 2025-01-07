package hooks;

import config.initialization.Context;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import stepdefinations.login.LoginSteps;
import utils.Utils;

public class Hooks {
	
	private final Context context;
	
	public Hooks(Context context) {
		this.context = context;
	}

	@Before(
			order=1, 
			value= "not @login_not_required and not @herokuapp"
			)
	public void login() throws Exception {

		LoginSteps loginSteps = new LoginSteps(context);
		
		loginSteps.user_opens_the_favourite_browser_and_types_url();
		loginSteps.the_login_page_appears();
		loginSteps.user_enters_username_password_and_clicks_on_login();
		loginSteps.the_user_navigate_to_home_or_landing_page();
		
		Utils.sleep(7000);
	}
	
	
	@After(
			order=10
			)
	public void tearDown() {
		
		if(context.getDriver() != null)
			context.quitDriver();
	}
	
}
