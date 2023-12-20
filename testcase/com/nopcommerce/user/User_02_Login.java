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
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataHelper;

public class User_02_Login extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, existingEmail, validPassword, incorrectPassword, invalidEmail, notFoundEmail;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
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
		invalidEmail = "afc@afc.com@.vn";
		existingEmail = dataFaker.getEmailAddress();
		notFoundEmail = dataFaker.getEmailAddress();
		validPassword = dataFaker.getPassword();
		incorrectPassword = dataFaker.getPassword();

		log.info("Pre-Condition - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("Pre-Condition - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Pre-Condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Pre-Condition - Step 03: Enter to Email textbox with value is '" + existingEmail + "'");
		registerPage.inputToEmailTextbox(existingEmail);

		log.info("Pre-Condition - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
		registerPage.inputToPasswordTextbox(validPassword);

		log.info("Pre-Condition - Step 03: Enter to Confirm password textbox with value is '" + validPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(validPassword);

		log.info("Pre-Condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Pre-Condition - Step 04: Verify sucessful registration message displayed");
		Assert.assertEquals(registerPage.getSucessRegisterMessage(), "Your registration completed");

		log.info("Pre-Condition - Step 05: Click to home link");
		homePage = registerPage.clickToHomeLink();
	}

	@Test
	public void Login_01__Empty_Data() {
		log.info("Login_01 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();

		log.info("Login_01 - Step 02: Click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_01 - Step 03: Click to Login link");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		log.info("Login_02 - Step 01: Click to Login link");
		loginPage.openHeaderUpperPageByName(driver, "Log in");
		// loginPage = PageGeneratorManagerNopCommerce.getUserLoginPage(driver);

		log.info("Login_02 - Step 02: Enter to Email textbox with value is '" + invalidEmail + "'");
		loginPage.inputToEmailTextbox(invalidEmail);

		log.info("Login_02 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_02 - Step 04: Verify error message at Email textbox displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		log.info("Login_03 - Step 01: Click to Login link");
		loginPage.openHeaderUpperPageByName(driver, "Log in");

		log.info("Login_03 - Step 02: Enter to Email textbox with value is '" + notFoundEmail + "'");
		loginPage.inputToEmailTextbox(notFoundEmail);

		log.info("Login_03 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_03 - Step 04: Verify error message at Email textbox displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsucessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		log.info("Login_04 - Step 01: Click to Login link");
		loginPage.openHeaderUpperPageByName(driver, "Log in");

		log.info("Login_04 - Step 02: Enter to Email textbox with value is '" + existingEmail + "'");
		loginPage.inputToEmailTextbox(existingEmail);

		log.info("Login_04 - Step 03: Enter to Passowd textbox with empty value");
		loginPage.inputToPasswordTextbox("");

		log.info("Login_04 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_04 - Step 05: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsucessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		log.info("Login_05 - Step 01: Click to Login link");
		loginPage.openHeaderUpperPageByName(driver, "Log in");

		log.info("Login_05 - Step 02: Enter to Email textbox with value is '" + existingEmail + "'");
		loginPage.inputToEmailTextbox(existingEmail);

		log.info("Login_05 - Step 03: Enter to Password textbox with value is '" + incorrectPassword + "'");
		loginPage.inputToPasswordTextbox(incorrectPassword);

		log.info("Login_05 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_05 - Step 05: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsucessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Valid_Email_Password() {
		log.info("Login_06 - Step 01: Click to Login link");
		loginPage.openHeaderUpperPageByName(driver, "Log in");

		log.info("Login_06 - Step 02: Enter to Email textbox with value is '" + existingEmail + "'");
		loginPage.inputToEmailTextbox(existingEmail);

		log.info("Login_06 - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTextbox(validPassword);

		log.info("Login_06 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_06 - Step 05: Verify Home page Displayed");
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
