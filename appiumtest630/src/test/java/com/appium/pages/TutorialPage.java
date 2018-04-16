package com.appium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TutorialPage extends BasePage{

	static WebDriver driver;
	
	static By allowAccessLocationBtn;
	static By dontAllowAccessLocationBtn;
	static By allowSendNotificationBtn;
	static By dontAllowSendNotificationBtn;
	static By pullDownImageLocator;

	public TutorialPage(WebDriver wd) {
		super(wd);
		TutorialPage.driver = wd;
	}
	
	public static void allowAccessLocation(boolean bool) {
		waiting();
		try {
	        waitUntilExpectedConditionsAlertIsPresent(ExpectedConditions.alertIsPresent());
			if (bool) {
		        driver.switchTo().alert().accept();	
			}else
			{
		        driver.switchTo().alert().dismiss();
			}	
        }
		catch(Exception e) {
			
		}
	}
	
	public static void allowSendNotification(boolean bool) {
		waiting();
		try {
	        waitUntilExpectedConditionsAlertIsPresent(ExpectedConditions.alertIsPresent());
			if (bool) {
		        driver.switchTo().alert().accept();	
			}else
			{
		        driver.switchTo().alert().dismiss();
			}
		}
		catch(Exception e) {
			
		}

	}
	
	public static void clickPullDownImageLocator() {
		clickLocator(pullDownImageLocator);
	}
	
	
	
}
