package com.appium.testcases;

import org.testng.annotations.Test;
import com.appium.pages.GooglePage;
import com.testbase.appium.BaseIOSTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BrowserTest extends BaseIOSTest{

	@BeforeClass
	public void setUp() {
		setUpTest("browserside");
	}

	@AfterClass
	public void tearDown() {
		quitAppiumSession();
	}
	
    @Test
	public void testdemo() throws IOException {
    		GooglePage.accessURL("http://www.google.com");
    		GooglePage.search("test");
    }
}