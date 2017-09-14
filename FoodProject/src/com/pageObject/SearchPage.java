package com.pageObject;

import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	@FindBy(css = ".autoCompleteDropDownContent span")
	private List<WebElement> selectAddress1;
	
	@FindBy(css = ".autoCompleteDropDown")
	private WebElement dropdownContent;

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

	public void typeInSearchTextBox(String address) {
		clickSearchTextBox();
		this.searchTextBox.clear();
		this.searchTextBox.sendKeys(address);
		return ;
	}

	public String errorSuggestionLocation(WebDriverWait wait) {
		
		String text = wait.until(ExpectedConditions.visibilityOf(this.noLocationError)).getText();
		return text;
	}

	public String errorDeliveryArea(WebDriverWait wait ) {
		String text = wait.until(ExpectedConditions.visibilityOf(this.errorDeliveryArea)).getText();
		return text;
	}

	public String selectDropdown() {
		String text = this.selectAddress.getText();
		this.selectAddress.click();
		return text;
	}
	
	public String clickBackground() {
		String text = this.background.getText();
		this.background.click();
		
		return text;
	}
	
	
	public List<WebElement> getResult(WebDriverWait wait ) {
		wait.until(ExpectedConditions.visibilityOf(this.dropdownContent));
		return selectAddress1;
	}
	
	
}