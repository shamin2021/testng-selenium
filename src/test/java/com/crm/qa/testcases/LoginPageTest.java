package com.crm.qa.testcases;

import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import java.io.IOException;

public class LoginPageTest extends TestUtil {
	LoginPage loginPage;
	HomePage homePage;

	public LoginPageTest(){
		super();
	}

	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();
	}

	//*[@id="slide-out-panel3"]/header/h1
	@Test
	public void loginPageTitleTest() throws InterruptedException, IOException {
		getRunTimeInfoMessage("warning","Starting Login Title test ");
		loginPage.openLoginModal();
		try {
			// Validate the login header text
			String headerText = loginPage.validateLoginHeader();
			Assert.assertEquals(headerText, "Login", "Header does not match 'Login'. Test aborted.");
			getRunTimeInfoMessage("info","Validated the login page title successfully.");
		} catch (AssertionError e) {
			getRunTimeInfoMessage("error","Assertion failed in loginPageTitleTest");
			takeScreenshot("invalidLogin");
			Assert.fail("Login page title test failed due to assertion failure.");
		} catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

	@Test( dataProvider = "getLoginData")
	public void loginTest(String username, String password) {
		try {
			getRunTimeInfoMessage("warning","Starting Login  test ");
			// Open login modal
			loginPage.openLoginModal();
			getRunTimeInfoMessage("info","Start login for user: " + username);

			// Perform login action
			homePage = loginPage.loginWithEmail(username, password);
			if (homePage != null) {
				getRunTimeInfoMessage("info","Login successful for user: " + username);
			} else {
				getRunTimeInfoMessage("error","Login failed for user: " + username);
				takeScreenshot("invalidLoginAttempt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] getLoginData() throws IOException {
		return getTestData("LoginCredentials"); // Sheet name in the Excel file
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
