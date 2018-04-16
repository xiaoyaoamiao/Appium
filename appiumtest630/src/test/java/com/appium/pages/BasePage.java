package com.appium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{

	static WebDriver driver;
	static long waitingTimes = 3000;
	static long waitingTime_afterClick = 1000;
	static long waitingTime_webPageLoad = 3000;


	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
	}
	
	public static void waitUntilExpectedConditionsAlertIsPresent(ExpectedCondition<Alert> expectedCondition) {
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(expectedCondition);
	}
	
	public static void waiting() {
        try {
            Thread.sleep(waitingTimes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	public static void waiting(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	public static void clickLocator(By by) {
		try {
            driver.findElement(by).click();
            waiting(waitingTime_afterClick);
        } catch (NoSuchElementException e1) {

        }
	}
	
	public static void sendKeyToLocator(By by, String value) {
		try {
            driver.findElement(by).sendKeys(value);;
        } catch (NoSuchElementException e1) {

        }
	}
	
	
	public static void accessURL(String url) {
		try {
            driver.get(url);
            waiting(waitingTime_webPageLoad);
        } catch (NoSuchElementException e1) {

        }
	}
	
}
