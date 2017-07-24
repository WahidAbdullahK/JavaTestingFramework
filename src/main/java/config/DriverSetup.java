package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverSetup {
	/*
	 * getWebDriverObject
	 * 
	 */
	WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);
	
	/*
	 * desiredCapabilities
	 * 
	 */
	DesiredCapabilities desiredCapabilities();
}
