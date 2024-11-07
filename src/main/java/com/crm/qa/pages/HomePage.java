package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//*[@id=\"country_list\"]/div[2]/a")
    static WebElement sriLanka;

	@FindBy(xpath = "//*[@id=\"searchGlass\"]")
	private WebElement searchBtn;

	@FindBy(xpath = "//*[@id=\"search\"]")
	private WebElement searchTextBox;

	// Define shopContainer as a static, reusable element
	@FindBy(xpath = "//*[@id=\"shop\"]")
	private WebElement shopContainer;

	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	public void clickOnCountryLink(){
		// Scroll the element into view
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", sriLanka);
		sriLanka.click();
	}

	public void searchProducts(String searchText){
		enterText(searchTextBox, searchText);
		click(searchBtn);
	}
}
