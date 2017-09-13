package com.main.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageObject {
	// protected WebDriver driver;

	@FindBy(css = ".submitBtn span")
	private WebElement searchButton;

	@FindBy(name = "mysearchstring")
	private WebElement searchTextBox;

	@FindBy(css = ".suggestions-location")
	private WebElement noLocationError;

	@FindBy(css = ".deliveryareaerror")
	private WebElement errorDeliveryArea;

	@FindBy(css = ".autoCompleteDropDownContent span")
	private WebElement selectAddress;

	@FindBy(css = ".cta-inner span")
	private WebElement background;

	public SearchPage(WebDriver driver) {
		super(driver);

	}

	public void clickSubmit() {
		this.searchButton.click();

	}

	public void clickSearchTextBox() {
		this.searchTextBox.click();

	}

	public String typeInSearchTextBox(String address) {
		this.searchTextBox.click();
		this.searchTextBox.sendKeys(address);

		return address;
	}

	public WebElement errorSuggestionLocation() {
		return noLocationError;
	}

	public WebElement errorDeliveryArea() {
		return errorDeliveryArea;
	}

	public String selectDropdown() {
		String text = this.selectAddress.getText();
		this.selectAddress.click();

		return text;
	}
	
	public void clickBackground() {
		this.background.click();
	}

}