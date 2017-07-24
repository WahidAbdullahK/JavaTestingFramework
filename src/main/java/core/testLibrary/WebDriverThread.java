package core.testLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import config.DriverType;
import static config.DriverType.FIREFOX;
import static config.DriverType.valueOf;

import java.net.MalformedURLException;

public class WebDriverThread {

	private WebDriver webdriver;
	
	private final DriverType defaultDriverType = FIREFOX;
	private DriverType selectedDriverType;
	
	// This will fail if you run this via eclipse's TESTNG
	// please set the browser value to "FIREFOX" or the browser name you wish to run on to make it work.
	private String browserval = System.getProperty("browser");
	private final String browser = browserval != null ? browserval.toUpperCase() : "FIREFOX";
	//private final String browser = "FIREFOX";
	private final String operatingSystem = System.getProperty("os.name").toUpperCase();
	private final String systemArchitecture = System.getProperty("os.arch");
	
	public WebDriver getDriver() throws Exception {
		if (null == webdriver) {
			selectedDriverType = determineEffectiveDriverType();
			DesiredCapabilities desiredCapabilities = selectedDriverType.desiredCapabilities();
			instantiateWebDriver(desiredCapabilities);
		}
		
		return webdriver;
	}
	
	public DriverType determineEffectiveDriverType() {
		
		// Default
		DriverType driverType = defaultDriverType;
		
		// Lets check if something is provided by pom.xml or via the maven cmdline.
		try {
			driverType = valueOf(browser);
		} catch (IllegalArgumentException ignored) {
			System.err.println("Unknown driver specified, Defaulting to " + defaultDriverType);
		} catch (NullPointerException ingored) {
			System.err.print("No driver specified, Defaulting to " + defaultDriverType);
		}
		return driverType;
		
	}
	
	private void instantiateWebDriver (DesiredCapabilities desiredCapabilities) throws MalformedURLException {
		System.out.println(" ");
        System.out.println("Current Operating System: " +
        operatingSystem);
        System.out.println("Current Architecture: " +
        systemArchitecture);
        System.out.println("Current Browser Selection: " +
        selectedDriverType);
        System.out.println(" ");
        webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
	}
	
	public void quitDriver() {
		if (null != webdriver) {
			webdriver.quit();
			webdriver = null;
		}
	}
}
