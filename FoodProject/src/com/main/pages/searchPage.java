package com.main.pages;





import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class searchPage {
	protected WebDriver driver;
	
    @FindBy(css =".submitBtn span")

    WebElement SearchButton;
//	private By signInButton = By.linkText("Sign in");
	
	public searchPage(WebDriver driver) {
		this.driver = driver;
	}
	

}