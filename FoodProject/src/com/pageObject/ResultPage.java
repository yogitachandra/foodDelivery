package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage extends PageObject {

	@FindBy(css = ".listing-inner")
	private WebElement resultPage;

	@FindBy(css = ".cta-inner span span")
	private WebElement background;

	public ResultPage(WebDriver driver) {
		super(driver);

	}

	public void waitForResultPageToLoad(WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOf(this.resultPage));

	}

	public String verifyResultPageAddress() {
		String text = this.background.getText();
		return text;

	}

}