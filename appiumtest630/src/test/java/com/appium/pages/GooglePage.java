package com.appium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage extends BasePage{

	static WebDriver driver;
	
	static By searchField = By.name("q");
	static By searchBtn = By.xpath("//button[@class='Tg7LZd']");

	public GooglePage(WebDriver wd) {
		super(wd);
		GooglePage.driver = wd;	
	}
	
	
	public static void search(String searchValue) {
		sendKeyToLocator(searchField, searchValue);
		clickLocator(searchBtn);
	}
	
	
	
}
