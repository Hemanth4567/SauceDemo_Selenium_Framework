package com.sauceDemo.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import com.sauceDemo.pages.LoginPage;
import com.sauceDemo.pages.ProductsPage;
import com.sauceDemo.utils.ConfigReader;
import io.cucumber.java.en.*;

public class LoginSteps {
	
	WebDriver driver;
	LoginPage login;
	ProductsPage products;
	ConfigReader config = new ConfigReader();
	
	@Given("the user is on the sauceDemo login page")
	public void navigateToLogin()
	{
		String browser = config.getBrowser();
		
		//Dynamic browser selection
		if(browser.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();	
		}
		
		driver.manage().window().maximize();
		driver.get(config.getUrl());
		login = new LoginPage(driver); // Initializes our kitchen
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
		driver.quit();
	}
	
	@Then("then user should see the error message")
	public void verifyMessge()
	{
		String actualError = login.getErrorMsgText();
		Assert.assertTrue(actualError.contains("Epic sadface"),"Error messge did not appear");
		driver.quit();
	}
	
	@And("the user adds the backpack to the cart")
	public void addBackPAck()
	{
		products = new ProductsPage(driver);
		products.addBackPackToCart();
	}
	
	@Then("the cart badge should show {string}")
	public void varifyCart(String expectedCount)
	{
		String actualCount = products.getCartCount();
		Assert.assertEquals(actualCount, expectedCount);
		
		driver.quit();
	}

}
