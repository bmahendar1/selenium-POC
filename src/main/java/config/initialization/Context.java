package config.initialization;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import config.RESTJiraTicketCreator;

public class Context extends RESTJiraTicketCreator {

	private WebDriver driver;
	private Map<String, Object> options;
//	private RESTJiraTicketCreator jiraTicketCreator;
	
	public Context() {
//		this.jiraTicketCreator = new RESTJiraTicketCreator();
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void quitDriver() {
		driver.quit();
    }
	
//	public RESTJiraTicketCreator getJiraTickerCreator() {
//		return jiraTicketCreator;
//	}
	
	public void setOption(String key, Object value) {
		
		if(options == null)
			options = new HashMap<String, Object>();
		
		options.put(key, value);
	}
	
	public Map<String, Object> getOptions() {
		return options;
	}
}
