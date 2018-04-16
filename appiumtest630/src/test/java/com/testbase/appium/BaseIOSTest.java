package com.testbase.appium;

import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;
import java.net.URL;
import com.appium.pages.TutorialPage;

public class BaseIOSTest extends BaseTest {

	TutorialPage TutorialPage;

	public BaseIOSTest() {
		initialPages();
	}
	
	private void initialPages() {
		TutorialPage = new TutorialPage(wd);
	}
	
	@SuppressWarnings("rawtypes")
	protected void setAppiumDriver() throws IOException {
	    logger.debug("Setting up iOSDriver");
		BaseTest.wd  = new IOSDriver(new URL(getAppiumServerAddress() + "/wd/hub"), capabilities);
		initialPages();
	}

    @Override
    protected String getServerSideApplicationPath() {
        return System.getProperty("user.dir") + "/application.ipa";
    }

    @Override
    protected String getDesiredCapabilitiesPropertiesFileName() {
        if (isClientSideTestRun()){
            return "desiredCapabilities.ios.clientside.properties";
        } else if(isServerSideTestRun()){
            return "desiredCapabilities.ios.serverside.properties";
        }else {
            return "desiredCapabilities.ios.browserside.properties";
        }
    }

}
