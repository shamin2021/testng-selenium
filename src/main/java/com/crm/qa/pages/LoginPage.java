package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	@FindBy(xpath="//*[@id=\"clientLogin\"]")
	WebElement loginModalBtn;

	@FindBy(xpath="//*[@id=\"slide-out-panel3\"]/header/h1")
	WebElement loginHeader;

	// Page Factory - OR for email and password fields
	@FindBy(xpath = "//*[@id=\"loginemail2\"]") // Change to the correct XPath for the email field
			WebElement emailField;

	@FindBy(xpath = "//*[@id=\"loginpwd2\"]") // Change to the correct XPath for the password field
	WebElement passwordField;

	@FindBy(xpath = "//*[@id=\"loginSubmit2\"]")  // Separate login button (adjust the XPath as needed)
	WebElement loginBtn;

	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}

	// Method to open the login modal
	public void openLoginModal() throws InterruptedException {
		Thread.sleep(5000);
		click(loginModalBtn);
	}

	// Method to validate the login header text
	public String validateLoginHeader() {
		getText(loginHeader);
		return loginHeader.getText();
	}

	// Method to log in using email and password
	public HomePage loginWithEmail(String email, String password) {
		enterText(emailField,email);
		enterText(passwordField,password);
		click(loginBtn);
		return new HomePage();  // Return HomePage object after successful login
	}

}
