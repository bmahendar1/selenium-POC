package config.initialization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
	
	private String browserName;
	
	public WebDriverFactory(String browserName) {
		this.browserName = browserName;
	}
	
    public WebDriver createWebDriver() {
    	
//        String browserName = System.getProperty("browser", "chrome");
        
        switch(browserName) {
            case "firefox":
                return new FirefoxDriver(getFirefoxOptions());
            case "chrome":
                return new ChromeDriver(getChromeOptions());
            default:
                throw new RuntimeException("Unsupported browser: " + browserName);
        }
    }
    
    public static ChromeOptions getChromeOptions() {
    	ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
//		options.addArguments("--start-maximized");
//		options.addArguments("--incognito");
		
		return options;
    }
    
    public static FirefoxOptions getFirefoxOptions() {
    	
    	FirefoxProfile profile = new FirefoxProfile();
        
        // Disable web notifications
        profile.setPreference("dom.webnotifications.enabled", false);
        
        // Disable push notifications
        profile.setPreference("dom.push.enabled", false);

        // Disable geo-location prompts
        profile.setPreference("geo.enabled", false);
        
        // Create FirefoxOptions and set the profile
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

//		options.addArguments("-private");
        
        return options;
    }
}
