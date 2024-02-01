package com.nopcommerce.common;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataHelper;

public class Common_01_Register_End_User extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	public static String emailAddress, password;
	private DataHelper dataFaker;

	@Parameters({ "envName", "serverName", "browserName", "osName", "osVersion", "ipAddress", "portNumber" })
	@BeforeTest(description = "Create new common User for all Classes Test")
	public void Register(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("11") String osVersion,
			@Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
		driver = getBrowserDriver(envName, serverName, browserName, osName, osVersion, ipAddress, portNumber);
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);
		dataFaker = DataHelper.gerDataHelper();

		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		emailAddress = dataFaker.getEmailAddress();
		password = dataFaker.getPassword();

		log.info("Pre-Condition - Step 01: Navigate to 'Register' page");
		registerPage = homePage.clickToRegisterLink();

		log.info("Pre-Condition - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Pre-Condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Pre-Condition - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Pre-Condition - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Pre-Condition - Step 06: Enter to Confirm password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Pre-Condition - Step 07: Enter to 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Pre-Condition - Step 08: Verify success message is displayed");
		verifyEquals(registerPage.getSucessRegisterMessage(), "Your registration completed");

		log.info("Pre-Condition - Step 09: Click to Logout link");
		homePage = registerPage.clickToHomeLink();

		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

}
