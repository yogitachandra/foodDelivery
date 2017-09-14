package com.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.pageObject.ResultPage;
import com.pageObject.SearchPage;

public class SearchTest extends BaseClassTest {

	// @Test(priority = 0)

	@Test(priority = 5)
	public void testValidAddress() {
		WebDriver driver = getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String input = "Munich Marriott Hotel, Berliner Straﬂe, Munich";

		SearchPage searchPage = new SearchPage(driver);

		driver.navigate().refresh();
		searchPage.typeInSearchTextBox(input);
		String addressResult = searchPage.selectDropdown();

		ResultPage resultPage = new ResultPage(driver);
		resultPage.waitForResultPageToLoad(wait);
		String result = resultPage.verifyResultPageAddress();

		Assert.assertEquals(addressResult, "Munich Marriott Hotel, Berliner Straﬂe, Munich",
				"Correct address is not displayed");
		Assert.assertEquals(result, input);

	}

	// verify error message when empty
	@Test(priority = 1)
	public void testEmptyAddress() {
		WebDriver driver = getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		SearchPage searchPage = new SearchPage(driver);

		searchPage.clickSubmit();
		String text = searchPage.errorDeliveryArea(wait);
		AssertJUnit.assertEquals(text,
				"The entered postcode is invalid. A valid postcode needs to consist out of 5 digits, for example: 10115.");

	}

	// The entered postcode does not exist or is not valid. Please check your
	// input and try again.
	@Test(priority = 3)
	public void testWrongPostalCode() {
		WebDriver driver = getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		SearchPage searchPage = new SearchPage(driver);

		searchPage.typeInSearchTextBox("53666");
		searchPage.clickBackground();
		searchPage.clickSubmit();

		String text = searchPage.errorDeliveryArea(wait);

		Assert.assertEquals(text,
				"The entered postcode does not exist or is not valid. Please check your input and try again.");
	}

	// Please enter your street and house number

	@Test(priority = 2)

	// @Test
	public void testLocationSuggestion() {
		WebDriver driver = getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		SearchPage searchPage = new SearchPage(driver);

		searchPage.typeInSearchTextBox("33666");
		String text = searchPage.errorSuggestionLocation(wait);

		Assert.assertEquals(text, "Please enter your street and house number");

	}

	// #42 in address
	@Test(priority = 4)
	public void testResultInDropdown() {
		WebDriver driver = getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		SearchPage searchPage = new SearchPage(driver);

		searchPage.typeInSearchTextBox("##42");

		List<WebElement> allResultList = searchPage.getResult(wait);

		for (WebElement element : allResultList) {
			String result = element.getText();
			Assert.assertTrue(result.contains("42"), "Address does not match the entered address" + result);
		}

	}

}
