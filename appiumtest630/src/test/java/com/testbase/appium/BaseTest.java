package com.testbase.appium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public abstract class BaseTest {
    private static final String LOCAL_APPIUM_ADDRESS = "http://127.0.0.1:4723";
    private static final String TESTDROID_SERVER = "https://appium.bitbar.com";
    private static final String serverSideTypeDefinition = "serverside";
	private static final String clientSideTypeDefinition = "clientside";
    private static final String browserSideTypeDefinition = "browserside";

    private String executionType = "clientside";

    protected static WebDriver wd;
    protected DesiredCapabilities capabilities;
    protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);

	public void setUpTest() {
		setExecutionType(executionType);
        setUpAppiumDriver();
    }
    
    public void setUpTest(String executionType) {
    		setExecutionType(executionType);
        setUpAppiumDriver();
    }
    

    public void setUpAppiumDriver() {
        DesiredCapabilities desiredCapabilities = getDesiredCapabilitiesFromProperties();
        this.capabilities = desiredCapabilities;
        logger.debug("Creating Appium session, this may take couple minutes..");
        try {
			setAppiumDriver();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private DesiredCapabilities getDesiredCapabilitiesFromProperties() {
        logger.debug("Setting desiredCapabilities defined in " + getDesiredCapabilitiesPropertiesFileName());
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Properties desiredCapabilitiesProperties = fetchProperties(getDesiredCapabilitiesPropertiesFileName());
        Set<String> keys = desiredCapabilitiesProperties.stringPropertyNames();
        for (String key : keys) {
            String value = desiredCapabilitiesProperties.getProperty(key);
            desiredCapabilities.setCapability(key, value);
        }  
        return desiredCapabilities;
    }

    protected abstract void setAppiumDriver() throws IOException;

    private Properties fetchProperties(String filename) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
//                String classPath = getClass().getClassLoader().getResource("").toString().replace("file:","");
//	        	File file = new File( "file:/Users/bmiao/eclipse-workspace/test222/appiumtest630/target/test-classes/src/test/resource/" + filename);//
	        	input = getClass().getClassLoader().getResourceAsStream(filename);
	            if (input == null) {
	                logger.error("Sorry, unable to find " + filename);
	                throw new FileNotFoundException("Unable to find/open file: " + filename);
	            }
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    protected abstract String getDesiredCapabilitiesPropertiesFileName();

    public boolean isServerSideTestRun() {
        return getExecutionType().equals(serverSideTypeDefinition);
    }

    public boolean isClientSideTestRun() {
        return getExecutionType().equals(clientSideTypeDefinition);
    }
    
    public boolean isBrowserSideTestRun() {
        return getExecutionType().equals(browserSideTypeDefinition);
    }
    
    public void setExecutionType(String executionType) {
    		this.executionType = executionType;
    }
    
    protected String getAppiumServerAddress() {
        if (isClientSideTestRun() || isBrowserSideTestRun()){
            return LOCAL_APPIUM_ADDRESS;
        }
        return TESTDROID_SERVER;
    }

    protected abstract String getServerSideApplicationPath();

    private String getExecutionType() {
        String property = this.executionType;
        if (property == null || property.isEmpty()) {
            logger.warn(" executionType is not define" + property);
        }
        return property;
    }

    protected void quitAppiumSession() {
//        if (exportTestResultsToCloud()) {
            try{
                PrintWriter writer = new PrintWriter("target/sessionid.txt", "UTF-8");
                writer.close();
            } catch (IOException e) {
               logger.error("IOError: could not store sessionId for result exporting");
            }
//        }
        if (wd != null) {
            wd.quit();
        }
    }

    protected File takeScreenshot(String screenshotName) {
    		String path = BaseTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String fullFileName = path + "/screenshots/" + screenshotName + ".png";
        logger.debug("Taking screenshot...");
        File scrFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);

        try {
            File testScreenshot = new File(fullFileName);
            FileUtils.copyFile(scrFile, testScreenshot);
            logger.debug("Screenshot stored to " + testScreenshot.getAbsolutePath());

            return testScreenshot;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
