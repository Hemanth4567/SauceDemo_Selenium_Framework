package com.sauceDemo.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import com.sauceDemo.factory.DriverFactory;
import com.sauceDemo.pages.LoginPage;
import com.sauceDemo.pages.ProductsPage;
import com.sauceDemo.utils.ConfigReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	WebDriver driver;
	private LoginPage login;	
	ProductsPage products;
	//ConfigReader config = new ConfigReader();

	@Given("the user is on the sauceDemo login page")
	public void navigateToLogin()
	{
		ConfigReader config = new ConfigReader();
		java.util.Properties prop = config.init_prop();
		
		DriverFactory.getDriver().get(prop.getProperty("url"));
		login = new LoginPage(DriverFactory.getDriver());
	}

	@When("the user enters username {string} and password {string}")
	public void enterCredentials(String username, String password)
	{
		login.enterCredentials(username, password);
	}

	@Then("the user should be redirected to the {string} header")
	public void verifyHeader(String expectedTitle)
	{
		Assert.assertEquals(login.getHeaderText(), expectedTitle);
	}

	@Then("then user should see the error message")
	public void verifyMessge()
	{
		String actualError = login.getErrorMsgText();
		Assert.assertTrue(actualError.contains("Epic sadface"),"Error messge did not appear");
	}

	@And("the user adds the backpack to the cart")
	public void addBackPAck()
	{
		products = new ProductsPage(DriverFactory.getDriver());
		products.addBackPackToCart();
	}

	@Then("the cart badge should show {string}")
	public void varifyCart(String expectedCount)
	{
		Assert.assertEquals(products.getCartCount(), expectedCount);
	}

}
