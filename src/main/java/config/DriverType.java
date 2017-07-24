package config;

import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public enum DriverType implements DriverSetup {
	
	FIREFOX {
		
		public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
			// TODO Auto-generated method stub
			return new FirefoxDriver(desiredCapabilities());
		}
	
		
		public DesiredCapabilities desiredCapabilities() {
			// TODO Auto-generated method stub
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			return capabilities;
		}
	},
	
	CHROME {

		
		public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
			// TODO Auto-generated method stub
			return new ChromeDriver(desiredCapabilities());
		}

		
		public DesiredCapabilities desiredCapabilities() {
			// TODO Auto-generated method stub
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
			HashMap<String, String> preferences = new HashMap<String, String>();
			preferences.put("profile.password_manager_enabled", "false");
			capabilities.setCapability("chrome.prefs", preferences);
			return capabilities;
		}
	},
		
	IE {

		
		public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
			// TODO Auto-generated method stub
			return new InternetExplorerDriver(desiredCapabilities());
		}

		
		public DesiredCapabilities desiredCapabilities() {
			// TODO Auto-generated method stub
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			capabilities.setCapability("requireWindowFocus", true);
			return capabilities;
		}
		
	},
	
	SAFARI {

		
		public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
			// TODO Auto-generated method stub
			return new SafariDriver(desiredCapabilities());
		}

		
		public DesiredCapabilities desiredCapabilities() {
			// TODO Auto-generated method stub
			DesiredCapabilities capabilities = DesiredCapabilities.safari();
			capabilities.setCapability("safari.cleanSession", true);
			return capabilities;
		}
		
	},
	
	EDGE {

		
		public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
			// TODO Auto-generated method stub
			return new EdgeDriver(desiredCapabilities());
		}

		
		public DesiredCapabilities desiredCapabilities() {
			// TODO Auto-generated method stub
			DesiredCapabilities capabilities = DesiredCapabilities.edge();
			return capabilities;
		}
		
	};
	
	

}
