package core.testLibrary;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

//Test base module to hold common functionalities related to WebDriver
public class TestBase {
	WebDriver driver;
	RemoteWebDriver rdriver;
	public WebDriver InitializeBrowser(String url) throws Exception
	{
		if(System.getProperty("os.name").toUpperCase().contains("WINDOWS") &&
				System.getProperty("os.arch").contains("amd64")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\selenium_standalone_binaries\\windows\\marionette\\64bit\\geckodriver.exe");
		}
		if(!System.getProperty("grid_url").equals("")) {
			new DesiredCapabilities();
			DesiredCapabilities capability = DesiredCapabilities.chrome();
            capability.setBrowserName(System.getProperty("browser"));
            capability.setPlatform(Platform.WINDOWS);
            rdriver = new RemoteWebDriver(new URL(System.getProperty("grid_url")), capability);
            rdriver.get(url);
            return rdriver;
		}
		driver = DriverFactory.getDriver();
		driver.get(url);
		return driver;
	}
	public void cleanup() throws Exception {
		if(!System.getProperty("grid_url").equals("")) {
			rdriver.quit();
		} else{
			DriverFactory.clearCookies();
			DriverFactory.closeDriverObjects();
		}
	}
}
