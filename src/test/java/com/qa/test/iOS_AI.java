package com.qa.test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class iOS_AI {

	private IOSDriver driver;

	@BeforeMethod
	public void setUp() throws IOException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "iOS");
		caps.setCapability("platformVersion", "11.1");
		caps.setCapability("deviceName", "iPhone 8");
		caps.setCapability("bundleId", "com.apple.mobileslideshow");

		HashMap<String, String> customFindModules = new HashMap<>();
		customFindModules.put("ai", "test-ai-classifier");
		caps.setCapability("customFindModules", customFindModules);
		caps.setCapability("shouldUseCompactResponses", false);

		driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	}

	@Test
	public void testFindElementUsingAI() throws Exception {
		
		driver.findElement(MobileBy.custom("search")).click();
		driver.findElement(MobileBy.name("Search")).sendKeys("Arjun");
		Thread.sleep(3000);
		driver.findElement(MobileBy.AccessibilityId("Cancel")).click();
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}
}
