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
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataHelper;

public class User_03_My_Account extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, existingEmail, validPassword, editFirstName, editLastName, editEmail, gender, date, month, year, companyName;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserMyAccountPageObject myAccountPage;
	private DataHelper dataFaker;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "'");
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);
		dataFaker = DataHelper.gerDataHelper();

		gender = "Female";
		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		existingEmail = dataFaker.getEmailAddress();
		validPassword = dataFaker.getPassword();
		editFirstName = "Automation";
		editLastName = "FC";
		editEmail = dataFaker.getEmailAddress();
		date = "1";
		month = "January";
		year = "1999";
		companyName = "Automation FC";

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

		log.info("Pre-Condition - Step 06: Click to Login link");
		loginPage = homePage.clickToLoginLink();

		log.info("Pre-Condition - Step 07: Enter to Email textbox with value is '" + existingEmail + "'");
		loginPage.inputToEmailTextbox(existingEmail);

		log.info("Pre-Condition - Step 08: Enter to Password textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTextbox(validPassword);

		log.info("Pre-Condition - Step 09: Click to Login button");
		loginPage.clickToLoginButton();

		log.info("Pre-Condition - Step 10: Verify Home page Displayed");
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void MyAccount_01__Update_Customer_Infor() {
		log.info("MyAccount_01 - Step 01: Click to My account link");
		homePage.openHeaderUpperPageByName(driver, "My account");
		myAccountPage = PageGeneratorManagerNopCommerce.getUserMyAccountPage(driver);

		log.info("MyAccount_01 - Step 02: Update gender with value is'" + gender + "'");
		myAccountPage.clickToRadioButtonByLabel(driver, gender);

		log.info("MyAccount_01 - Step 03: Update First name with value is'" + editFirstName + "'");
		myAccountPage.inputToTextboxByID(driver, "FirstName", editFirstName);

		log.info("MyAccount_01 - Step 04: Update Last name with value is'" + editLastName + "'");
		myAccountPage.inputToTextboxByID(driver, "LastName", editLastName);

		log.info("MyAccount_01 - Step 05: Update Date with value is'" + date + "'");
		myAccountPage.selectToDropdownByName(driver, "DateOfBirthDay", date);

		log.info("MyAccount_01 - Step 06: Update Month with value is'" + month + "'");
		myAccountPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);

		log.info("MyAccount_01 - Step 07: Update Year with value is'" + year + "'");
		myAccountPage.selectToDropdownByName(driver, "DateOfBirthYear", year);

		log.info("MyAccount_01 - Step 08: Update Email with value is'" + editEmail + "'");
		myAccountPage.inputToTextboxByID(driver, "Email", editEmail);

		log.info("MyAccount_01 - Step 09: Update Company name with value is'" + companyName + "'");
		myAccountPage.inputToTextboxByID(driver, "Company", companyName);

		log.info("MyAccount_01 - Step 10: Click to 'Save' button");
		myAccountPage.clickToButtonByText(driver, "Save");

		log.info("MyAccount_01 - Step 11: Verify update message successfully displayed");
		Assert.assertEquals(myAccountPage.getSucessUpdateMessage(), "The customer info has been updated successfully.");

		log.info("MyAccount_01: Step 12: Verify Gender was updated with value '" + gender + "'");
		verifyTrue(myAccountPage.isRadioButtonByLabelTextSelected(driver, gender));

		log.info("MyAccount_01: Step 13: Verify Firstname was updated with value '" + editFirstName + "'");
		verifyEquals(myAccountPage.getValueOfTextboxByID(driver, "FirstName", "value"), editFirstName);

		log.info("MyAccount_01: Step 14: Verify Lastname was updated with value '" + editLastName + "'");
		verifyEquals(myAccountPage.getValueOfTextboxByID(driver, "LastName", "value"), editLastName);

		log.info("MyAccount_01: Step 15: Verify Date was updated with value '" + date + "'");
		verifyEquals(myAccountPage.getValueSelectedOfDropboxByName(driver, "DateOfBirthDay"), date);

		log.info("MyAccount_01: Step 16: Verify Month was updated with value '" + month + "'");
		verifyEquals(myAccountPage.getValueSelectedOfDropboxByName(driver, "DateOfBirthMonth"), month);

		log.info("MyAccount_01: Step 17: Verify Year was updated with value '" + year + "'");
		verifyEquals(myAccountPage.getValueSelectedOfDropboxByName(driver, "DateOfBirthYear"), year);

		log.info("MyAccount_01: Step 18: Verify Email was updated with value '" + editEmail + "'");
		verifyEquals(myAccountPage.getValueOfTextboxByID(driver, "Email", "value"), editEmail);

		log.info("MyAccount_01: Step 19: Verify Company name was updated with value '" + companyName + "'");
		verifyEquals(myAccountPage.getValueOfTextboxByID(driver, "Company", "value"), companyName);
	}

	@Test
	public void MyAccount_02__Add_Address() {

	}

	@Test
	public void MyAccount_03__Update_Password() {

	}

	@Test
	public void MyAccount_04__Add_My_Product_Review() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
