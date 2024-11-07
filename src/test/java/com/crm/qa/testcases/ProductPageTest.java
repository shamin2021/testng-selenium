package com.crm.qa.testcases;

import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.ProductPage;
import com.crm.qa.util.CustomDataProvider;
import com.crm.qa.util.TestUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ProductPageTest extends TestUtil {
	LoginPage loginPage;
	HomePage homePage;
	ProductPage productPage;

	public ProductPageTest(){
		super();
	}

	@BeforeMethod
	public void setUp(){
		initialization();
		productPage = new ProductPage();
	}

	@Test(dataProvider = "productMore", dataProviderClass = CustomDataProvider.class)
	public void navigateToDressesTest(String category, String subCategory, String product, String size) throws InterruptedException {
		try {
			getRunTimeInfoMessage("warning","Starting navigateToDressesTest for Product: " + product );
			// Select the dress from the clothing category
			productPage.selectDressesFromClothing(category, subCategory, product, size);

			// Remove the item from the cart
			getRunTimeInfoMessage("info","Starting delete Product  from cart: " + product );

			boolean itemRemoved = productPage.removeItemFromCart(product);
			if (itemRemoved) {
				getRunTimeInfoMessage("info","Successfully removed item from cart: " + product);
			} else {
				getRunTimeInfoMessage("error","Failed to remove item from cart: " + product);
				takeScreenshot("invalidRmoveFromCart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown(){
		try {
			// Delay for observation
			Thread.sleep(5000);  // Adjust the delay (e.g., 5000 ms = 5 seconds) as needed
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}


}