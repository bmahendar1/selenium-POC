package config.initialization;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class Context {

	private WebDriver driver;
	private Map<String, Object> options;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setOption(String key, Object value) {
		
		if(options == null)
			options = new HashMap<String, Object>();
		
		options.put(key, value);
	}
	
	public Map<String, Object> getOptions() {
		return options;
	}
}
