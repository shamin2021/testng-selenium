package com.crm.qa.testcases;

import com.crm.qa.pages.ProductListPage;
import com.crm.qa.util.CustomDataProvider;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchAndFilterTest extends TestUtil {
    private ProductListPage productListPage;

    @BeforeMethod
    public void setUp() {
        initialization();  // This will initialize the driver and WebDriverWait
        productListPage = new ProductListPage();
    }

    @Test(dataProvider = "productName", dataProviderClass = CustomDataProvider.class,priority = 0)
    public void searchProducts(String productName){
        boolean productStatus;
        try {
            getRunTimeInfoMessage("warning","Starting search test ");
            getRunTimeInfoMessage("warning","Applying search for : " + productName );

            // Initialize ProductListPage and perform search
            productListPage = new ProductListPage();
            productListPage.searchProducts(productName);

            // Check if the product is available in search results
            productStatus = productListPage.isSearchProductAvailable(productName);
            if (productStatus) {
                getRunTimeInfoMessage("info","Product found in search results: " + productName);
            } else {
                getRunTimeInfoMessage("error","Product not found in search results: " + productName);
                takeScreenshot("invalidSearch");
            }
            Assert.assertTrue(productStatus, "Product is not in the search results");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "productFilter", dataProviderClass = CustomDataProvider.class)
    public void priceFilterTest(String category, String subCategory, String size, String price) {
        try {
            getRunTimeInfoMessage("warning","Starting sort and filter test ");
            getRunTimeInfoMessage("warning","Applying filter with - Category: " + category + ", Sub-Category: " + subCategory
                    + ", Size: " + size + ", Price: " + price);

            // Initialize ProductListPage and apply filter
            productListPage = new ProductListPage();
            boolean isFilter = productListPage.selectCategory(category, subCategory, size, price);
            getRunTimeInfoMessage("info","Filter applied with specified criteria.");

            if (isFilter) {
                getRunTimeInfoMessage("info","Filter applied successfully for price range: " + price);
            } else {
                getRunTimeInfoMessage("error","Filter did not apply as expected for price range: " + price);
                takeScreenshot("invalidFilter");
            }

            Assert.assertTrue(isFilter, "Price filter did not return expected results.");
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
