package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.crm.qa.pages.HomePage;
import com.crm.qa.util.TestUtil;
import java.io.IOException;

public class HomePageTest extends TestUtil {
	HomePage homePage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}

	@Test
	public void selectCountrySriLankaTest() throws InterruptedException, IOException {
		driver.get("https://shayint.com");
		getRunTimeInfoMessage("warning","Starting Homepage Srilanka test ");
		Thread.sleep(2000);
		homePage.clickOnCountryLink();

		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("lk.shayint.com")) {
            try {
                getRunTimeInfoMessage("info", "Correctly directed to Sri Lankan Page!");
				System.out.println("Redirected to: " + driver.getCurrentUrl());
				Assert.assertTrue(driver.getCurrentUrl().contains("lk.shayint.com"),
						"Country link click did not result in the expected URL.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
		} else {
			getRunTimeInfoMessage("error", "Incorrect Direction to International Page");
			takeScreenshot("invalidRedirect");
			Assert.fail("The page did not redirect as expected.");
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


