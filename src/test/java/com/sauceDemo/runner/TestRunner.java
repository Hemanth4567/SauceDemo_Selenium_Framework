package com.sauceDemo.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//Testing GitHub Webhook and sending reports to email + testing
@CucumberOptions(
		features = "src/test/resources/feature", //Where the english/feature files are
		glue = "com.sauceDemo.stepdefinitions",
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
		)	

public class TestRunner extends AbstractTestNGCucumberTests{

}
