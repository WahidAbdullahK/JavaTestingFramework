package core.testLibrary;        

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	
	/*
	 * every thread (ThreadLocal contains/does the magic) will get its own copy of driverThread,
	 * which in turn would contain the WebDriverThread object.
	 * WebDriverThread is the class which provides the different drivers.
	 * 
	 */
	private static ThreadLocal<WebDriverThread> driverThread;
	
	// This list contains the array of WebDriverThread objects whose drivers can be brought down during aftersuite execution.
	private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
	
	public static void instantiateDriverObject() {
		driverThread = new ThreadLocal<WebDriverThread>() {
			@Override
			protected WebDriverThread initialValue() {
				WebDriverThread webDriverThread = new WebDriverThread();
				webDriverThreadPool.add(webDriverThread);
				return webDriverThread;
			}
		};
	}
	
	public static WebDriver getDriver() throws Exception {
		instantiateDriverObject();
		return driverThread.get().getDriver();
	}
	
	public static void clearCookies() throws Exception {
		driverThread.get().getDriver().manage().deleteAllCookies();
	}

	public static void closeDriverObjects() throws Exception {
		System.out.println("**********************");
			System.out.println(webDriverThreadPool.size());
		System.out.println("**********************");
		Iterator<WebDriverThread> i = webDriverThreadPool.iterator();
		while (i.hasNext()) {
			WebDriverThread wdt = i.next();
			wdt.quitDriver();
		}
	}
	
}
