package com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClassTest {
	protected static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		
		System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.lieferando.de/en/");


	}

/*	@AfterTest
	public void cleanUp() {
		driver.manage().deleteAllCookies();
	}*/

	@AfterClass
	public static void tearDown() {
		driver.close();
	}
}

