package com.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.main.pages.SearchPage;

public class SearchTest extends BaseClassTest {

	 @Test(priority = 0)

	public void testValidAddress() {
		String baseUrl = "https://www.lieferando.de/en/";
		driver.get(baseUrl);

		SearchPage searchPage = new SearchPage(driver);
		searchPage.typeInSearchTextBox("Munich Marriott Hotel, Berliner Straﬂe, Munich");
		String addressResult = searchPage.selectDropdown();

		assertEquals(addressResult, "Munich Marriott Hotel, Berliner Straﬂe, Munich", "Correct address displayed");
	}

	// verify error message when empty
	@Test(priority=0)

	public void testEmptyAddress() {
		String baseUrl = "https://www.lieferando.de/en/";
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 15);

		SearchPage searchPage = new SearchPage(driver);
		searchPage.clickSubmit();
		String text = wait.until(ExpectedConditions.visibilityOf(searchPage.errorDeliveryArea())).getText();

		assertEquals(text,
				"The entered postcode is invalid. A valid postcode needs to consist out of 5 digits, for example: 10115.",
				"True");

	}


	// The entered postcode does not exist or is not valid. Please check your input and try again.
	@Test(priority = 0)

	public void testWrongPostalCode() {
		String baseUrl = "https://www.lieferando.de/en/";
		driver.get(baseUrl);

		SearchPage searchPage = new SearchPage(driver);
		searchPage.typeInSearchTextBox("53666");

		WebDriverWait wait = new WebDriverWait(driver, 15);

		searchPage.clickBackground();
		searchPage.clickSubmit();

		String text = wait.until(ExpectedConditions.visibilityOf(searchPage.errorDeliveryArea())).getText();

		assertEquals(text,
				"The entered postcode does not exist or is not valid. Please check your input and try again.", "True");
	}

	// Please enter your street and house number

	@Test(priority = 0)

	public void testLocationSuggestion() {
		String baseUrl = "https://www.lieferando.de/en/";
		driver.get(baseUrl);

		SearchPage searchPage = new SearchPage(driver);
		searchPage.clickSubmit();
		searchPage.typeInSearchTextBox("33666");

		WebDriverWait wait = new WebDriverWait(driver, 15);
		String text = wait.until(ExpectedConditions.visibilityOf(searchPage.errorSuggestionLocation())).getText();

		assertEquals(text, "Please enter your street and house number", "True");

	}

	// verify language
	// #42 in address

}
