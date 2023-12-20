package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataHelper;

public class User_01_Register extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private DataHelper dataFaker;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "'");
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		dataFaker = DataHelper.gerDataHelper();

		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		emailAddress = dataFaker.getEmailAddress();
		password = dataFaker.getPassword();

	}

	@Test
	public void Register_01__Empty_Data() {
		log.info("Register_01 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("Register_01 - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_01 - Step 03: Verify Error Message at First Name textbox");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");

		log.info("Register_01 - Step 04: Verify Error Message at Last Name textbox");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");

		log.info("Register_01 - Step 05: Verify Error Message at Email textbox");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");

		log.info("Register_01 - Step 06: Verify Error Message at Password textbox");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");

		log.info("Register_01 - Step 07: Verify Error Message at Confirm password Name textbox");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		log.info("Register_02 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("Register_02 - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_02 - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_02 - Step 04: Enter to Email textbox with value is '12345@56#%'");
		registerPage.inputToEmailTextbox("12345@56#%");

		log.info("Register_02 - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register_02 - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Register_02 - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_02 - Step 08: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {

		log.info("Register_03 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("Register_03 - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_03 - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_03 - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Register_03 - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register_03 - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Register_03 - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_03 - Step 08: Verify sucessful message displayed");
		Assert.assertEquals(registerPage.getSucessRegisterMessage(), "Your registration completed");

		log.info("Register_03 - Step 09: Click to home link");
		homePage = registerPage.clickToHomeLink();
	}

	@Test
	public void Register_04_Existing_Email() {
		log.info("Register_04 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("Register_04 - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_04 - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_04 - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Register_04 - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register_04 - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Register_04 - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_04 - Step 08: Verify error existing email message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars() {
		log.info("Register_05 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("Register_05 - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_05 - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_05 - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Register_05 - Step 05: Enter to Password textbox with value is '123'");
		registerPage.inputToPasswordTextbox("123");

		log.info("Register_05 - Step 06: Enter to Confirm Password textbox with value is '123'");
		registerPage.inputToConfirmPasswordTextbox("123");

		log.info("Register_05 - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_05 - Step 08: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		log.info("Register_06 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("Register_06 - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_06 - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_06 - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Register_06 - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register_06 - Step 06: Enter to Confirm Password textbox with value is '" + emailAddress + "'");
		registerPage.inputToConfirmPasswordTextbox(emailAddress);

		log.info("Register_06 - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_06 - Step 08: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
