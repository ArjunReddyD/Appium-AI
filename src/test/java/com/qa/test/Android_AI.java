package com.qa.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Android_AI {

	private AndroidDriver driver;
	private WebDriverWait wait; 
	String app = "/Users/mallikarjunareddyduvoori/Desktop/Walmart.apk";
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities(); 

		caps.setCapability("platformName" , "Android");
		caps.setCapability("deviceName", "Android Emulator");
		caps.setCapability("platformVersion", "8.1.0");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("noReset", "true");
		caps.setCapability("appPackage", "com.walmart.android");
		caps.setCapability("appActivity", "app.main.MainActivity");
		caps.setCapability("autoGrantPermissions", "true");
		caps.setCapability(MobileCapabilityType.APP, app);
		
		HashMap<String, String> customFindModules = new HashMap<>();
		customFindModules.put("ai", "test-ai-classifier");
		caps.setCapability("customFindModules", customFindModules);
		caps.setCapability("shouldUseCompactResponses", false);
		
	    driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	} 
	
	@Test
	public void testFindElementUsingAI() throws Exception {

		Thread.sleep(1000);
		//driver.findElementByAccessibilityId("Open navigation drawer").isDisplayed();
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Open navigation drawer")));
		//driver.findElement(MobileBy.custom("ai:cart")).click();
		
		driver.findElement(MobileBy.custom("ai:cart"));
		
		Thread.sleep(3000);
		
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
