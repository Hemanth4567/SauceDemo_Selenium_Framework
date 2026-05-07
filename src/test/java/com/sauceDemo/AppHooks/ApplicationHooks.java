package com.sauceDemo.AppHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.sauceDemo.factory.DriverFactory;
import com.sauceDemo.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


//Hooks added
public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	
	@Before(order = 0)
	public void getProperty()
	{
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
	
	@Before(order = 1)
	public void launchBrowser()
	{
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
	}
	
	@After(order = 0)
	public void quitBrowser()
	{
		if(DriverFactory.getDriver() != null)
		{
			System.out.println("Closing browser for thread: "+Thread.currentThread().getId());
			DriverFactory.getDriver().quit();
		}
		
		DriverFactory.tlDriver.remove();
	}
	
	public void tearDown(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			// 1. Take the screenshot using the thread-safe driver
			byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
			
			// 2. Name the screenshot based on the scenario name
			String screenshotName = scenario.getName().replace(" ", "_");
			
			// 3. Attach it to the Cucumber/Allure report
			scenario.attach(screenshot, "image/png", screenshotName);
			
			System.out.println("Screenshot taken for failed scenarios: "+scenario.getName());
		}
	}
}
