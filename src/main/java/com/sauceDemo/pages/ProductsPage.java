package com.sauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
	
	WebDriver driver;
	
	public ProductsPage(WebDriver driver)
	{
		this.driver = driver;
	}

	
	//Locator
	By addBackPackBtn = By.id("add-to-cart-sauce-labs-backpack");
	By cartBadge = By.className("shopping_cart_badge");
	By pageTitle = By.className("title");
	
	
	public String getTitle()
	{
		return driver.findElement(pageTitle).getText();
	}
	
	public void addBackPackToCart()
	{
		driver.findElement(addBackPackBtn).click();
	}
	
	public String getCartCount()
	{
		// If no items are added, the badge might not exist. 
        // We'll assume we only call this when we expect an item.
		
		return driver.findElement(cartBadge).getText();	
	}
}
