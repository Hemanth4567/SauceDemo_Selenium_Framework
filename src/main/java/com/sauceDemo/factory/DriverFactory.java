package com.sauceDemo.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public WebDriver init_driver (String browser)
	{
		System.out.println("browser value is: "+browser);
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--diable-save-password-bubble");
			options.addArguments("--disable-notifications");
			options.addArguments("--diable-autofill-keyboard-accessory-view");
			options.addArguments("--guest");
			WebDriver driverInstance = new ChromeDriver(options);
			tlDriver.set(driverInstance);
		}
		else if(browser.equalsIgnoreCase("safari"))
		{
			tlDriver.set(new SafariDriver());
		}
		else
		{
			System.out.println("Please pass the correct browser value: "+browser);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}
}
