package com.sauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
	
	WebDriver driver;
	
	
	// 1. Constructor: This connects the "Driver" to this page
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	// 2. Locators: Finding the elements
	By usernameField = By.id("user-name");
	By passwordField = By.id("password");
	By loginButton = By.id("login-button");
	By header = By.className("title");
	By errorMessge = By.cssSelector("h3[data-test = 'error']");
	
	// 3. Actions: What can we do on this page?
	public void enterCredentials(String user, String pass)
	{
		driver.findElement(usernameField).sendKeys(user);
		driver.findElement(passwordField).sendKeys(pass);
		driver.findElement(loginButton).click();
	}
	
	public String getHeaderText()
	{
		return driver.findElement(header).getText();
	}
	
	public String getErrorMsgText()
	{
		return driver.findElement(errorMessge).getText();
	}

}
