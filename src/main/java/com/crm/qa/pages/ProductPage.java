package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

public class ProductPage extends TestBase{

    @FindBy(xpath="//*[@id=\"primary-menu\"]/ul/li[3]/a")
    WebElement clothingMenu;

    // Locators for "Clothing" and "Dresses"
    @FindBy(xpath = "//*[@id=\"primary-menu\"]/ul/li[3]/ul/li[2]/a") // Adjust this XPath to match "Clothing"
    WebElement dressesSubMenu;

    //Initializing the Page Objects:
    public ProductPage(){
        PageFactory.initElements(driver, this);
    }

    public boolean selectDressesFromClothing(String category, String subCategory, String product, String size){
        // XPath to locate the category link containing the desired text (e.g., Clothing)
        String categoryXPath = "//*[@id=\"primary-menu\"]//a[contains(text(), '" + category + "')]";
        try {
            // Find the category element by XPath
            WebElement categoryElement = driver.findElement(By.xpath(categoryXPath));

            // Create an Actions object to perform hover action
            Actions actions = new Actions(driver);
            actions.moveToElement(categoryElement).perform();  // Hover over the category link

            WebElement submenuItemElement = driver.findElement(By.xpath("//*[@id=\"primary-menu\"]/ul//ul//a[contains(text(), '" + subCategory + "')]"));

            // Click on the submenu item (e.g., "Dress")
            submenuItemElement.click();
            System.out.println("Selected the submenu item: " + subCategory);

            String productXPath = "//*[@id=\"shop\"]//a[contains(text(), '" + product + "')]";
            WebElement productElement = driver.findElement(By.xpath(productXPath));
            click(productElement);

            String xsLink = "//*[@title='" + size + "']";
            WebElement xsElement = driver.findElement(By.xpath(xsLink));
            click(xsElement);

            String submitLink = "//*[@id=\"submitBtn\"]/a";
            WebElement submitButton = driver.findElement(By.xpath(submitLink));
            click(submitButton);

            String submitCartLink = "//*[@id=\"noticeDiv2\"]/a[contains(text(), 'Go My Cart')]";
            WebElement submitCartButton = driver.findElement(By.xpath(submitCartLink));
            click(submitCartButton);
            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean removeItemFromCart(String product) throws InterruptedException {
        try {
            // Construct the XPath for the delete icon dynamically based on the product name
            String deleteIconXPath = "//a[@title='Remove this item']/i[contains(@onclick, \"'" + product + "'\")]";

            // Find the delete icon element
            WebElement deleteIcon = driver.findElement(By.xpath(deleteIconXPath));

            // Click on the delete icon to remove the item
            click(deleteIcon);

            return true;
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }




}
