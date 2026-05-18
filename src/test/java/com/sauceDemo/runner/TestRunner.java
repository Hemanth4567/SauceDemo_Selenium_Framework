package com.sauceDemo.runner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//Testing GitHub Webhook and sending reports to email + testing+ final
@CucumberOptions(
		features = {"src/test/resources/feature"}, //Where the english/feature files are
		glue = {"com.sauceDemo.stepdefinitions","com.sauceDemo.AppHooks"},
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
		)

public class TestRunner extends AbstractTestNGCucumberTests{

	public static InheritableThreadLocal<String> browserName = new InheritableThreadLocal<>();

	@BeforeClass
	@Parameters("browser")
	public void defineBrowser(@Optional("chrome")String browser)
	{
		TestRunner.browserName.set(browser);
		System.out.println("TestNG initialized thread for: " + browser);
		// Your existing driver initialization logic...
		System.out.println("Browser intialization via Jenkins/TestNG: "+browser);
	}

	@Override
	@DataProvider(parallel = false)
	public Object[][]scenarios()
	{
		return super.scenarios();
	}
}
