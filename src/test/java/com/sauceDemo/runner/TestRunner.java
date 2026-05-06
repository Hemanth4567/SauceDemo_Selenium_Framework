package com.sauceDemo.runner;
import org.testng.annotations.DataProvider;

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
	
	@Override
	@DataProvider(parallel = true)
	public Object[][]scenarios()
	{
		return super.scenarios();
	}
}
