package com.appium.testcases;

import org.testng.annotations.Test;

import com.appium.pages.TutorialPage;
import com.testbase.appium.BaseIOSTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class SampleSauceTest extends BaseIOSTest{

	@BeforeClass
	public void setUp() {
		setUpTest("clientside");
	}

	@AfterClass
	public void tearDown() {
		quitAppiumSession();
	}
	
    @Test
	public void testdemo() throws IOException {
    		TutorialPage.allowAccessLocation(true);
    		TutorialPage.allowSendNotification(true);
    		TutorialPage.clickPullDownImageLocator();
    }
}