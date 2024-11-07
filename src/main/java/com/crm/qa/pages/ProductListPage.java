package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.NoSuchElementException;

public class ProductListPage extends TestBase {

	@FindBy(xpath = "//*[@id=\"searchGlass\"]")
	private WebElement searchBtn;

	@FindBy(xpath = "//*[@id=\"search\"]")
	private WebElement searchTextBox;

	// Define shopContainer as a static, reusable element
	@FindBy(xpath = "//*[@id=\"shop\"]")
	private WebElement shopContainer;

	// Initializing the Page Objects:
	public ProductListPage() {
		PageFactory.initElements(driver, this);
	}

	public void searchProducts(String searchText){
		enterText(searchTextBox, searchText);
		click(searchBtn);
	}

	public boolean isSearchProductAvailable(String searchText){
		// XPath to locate the product link containing the desired text
		String productXPath = "//*[@id=\"shop\"]//a[contains(text(), '" + searchText + "')]";
		try {
			// Find the element by XPath
			WebElement productElement = driver.findElement(By.xpath(productXPath));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean selectCategory(String category, String subCategory, String size, String price){
		// XPath to locate the category link containing the desired text (e.g., Clothing)
		String categoryXPath = "//*[@id=\"primary-menu\"]//a[contains(text(), '" + category + "')]";
		try {
			// Find the category element by XPath
			WebElement categoryElement = driver.findElement(By.xpath(categoryXPath));

			// Create an Actions object to perform hover action
			Actions actions = new Actions(driver);
			actions.moveToElement(categoryElement).perform();  // Hover over the category link

			// Find the subcategory element by XPath
			WebElement submenuItemElement = driver.findElement(By.xpath("//*[@id=\"primary-menu\"]/ul//ul//a[contains(text(), '" + subCategory + "')]"));
			submenuItemElement.click();

			// Wait for the button to be visible and then click it (size selection button)
			WebElement sizeButton = driver.findElement(By.xpath("//*[@id=\"size_btn\"]"));
			sizeButton.click();

			// Wait for the radio button for XS to be visible and then select it
			WebElement radioButton = driver.findElement(By.xpath("//*[@id='" + size + "']"));
			radioButton.click();

			// Wait for the button to be visible and then click it (size selection button)
			WebElement priceButton = driver.findElement(By.xpath("//*[@id=\"price_btn\"]"));
			priceButton.click();

			// Wait for the radio button for XS to be visible and then select it
			WebElement priceRadioButton = driver.findElement(By.xpath("//*[@id='" + price + "']"));
			priceRadioButton.click();

			WebElement filterButton = driver.findElement(By.xpath("//*[@id=\"product_search\"]/a[4]"));
			filterButton.click();
			return true;

		} catch (NoSuchElementException e) {
			return false;
		}
	}
}