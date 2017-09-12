package com.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.main.pages.*;

public class searchTest {

	private WebDriver driver;

	@BeforeClass
	public void setup() {
		// driver=getDriver();

		System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
		driver = new ChromeDriver();
		// System.setProperty("webdriver.firefox.bin",
		// "C:\\Users\\yogitachandra\\AppData\\Local\\Mozilla
		// Firefox\\firefox.exe"); //works

		// System.setProperty("webdriver.firefox.marionette",".//geckodriver.exe");
		// driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String baseUrl = "https://www.lieferando.de/en/";
		driver.get(baseUrl);
		driver.manage().window().maximize();

	}

	/*
	 * @Test public void verifyPage() { System.out.println("Home page test...");
	 * searchPage basePage = new searchPage(driver);
	 * Assert.assertTrue(basePage.verifyBasePageTitle(),
	 * "Home page title doesn't match"); }
	 */

	// @Test(priority=0)

	public void testValidAddress() {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mysearchstring"))).click();
		driver.findElement(By.name("mysearchstring")).sendKeys("Munich Marriott Hotel, Berliner Straﬂe, Munich");

		// WebElement select =
		// driver.findElement(By.id("iautoCompleteDropDownContent"));
		WebElement select = driver.findElement(By.cssSelector(".autoCompleteDropDownContent span"));
		String text = select.getText();
		select.click();
		System.out.println(text);

	}

	// verify error message when empty
	// @Test(priority=0)

	public void testNoAddress() {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".submitBtn span"))).click();
		By element = By.cssSelector(".deliveryareaerror");
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();

		System.out.println(text);

		assertEquals(text,
				"The entered postcode is invalid. A valid postcode needs to consist out of 5 digits, for example: 10115.",
				"True");

	}

	// Please enter your street and house number

	// The entered postcode does not exist or is not valid. Please check your
	// input and try again.
	// @Test(priority=0)

	public void testWrongPostalCode() {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mysearchstring"))).click();
		driver.findElement(By.name("mysearchstring")).sendKeys("33666");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cta-inner span"))).click(); // to
																												// get
																												// the
																												// submit
																												// button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".submitBtn span"))).click();

		By element = By.cssSelector(".deliveryareaerror");
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();

		System.out.println(text);

		assertEquals(text,
				"The entered postcode does not exist or is not valid. Please check your input and try again.", "True");

	}

	// Please enter your street and house number

	@Test(priority = 0)

	public void testLocationSuggestion() {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mysearchstring"))).click();
		driver.findElement(By.name("mysearchstring")).sendKeys("33666");

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cta-inner
		// span"))).click(); //to get the submit button
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".submitBtn
		// span"))).click();

		By element = By.cssSelector(".suggestions-location");
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();

		System.out.println(text);
//add is displayed
		assertEquals(text, "Please enter your street and house number", "True");

	}
	
	// verify language
	
	
}
