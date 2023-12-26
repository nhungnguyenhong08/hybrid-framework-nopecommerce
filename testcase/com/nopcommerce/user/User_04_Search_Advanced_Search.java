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
import pageObjects.nopCommerce.user.UserSearchPageObject;
import utilities.DataHelper;

public class User_04_Search_Advanced_Search extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, existingEmail, validPassword, productNotExist, relativeProductName, absoluteProductName;
	private int numberSearchRelativeProductExpected, numberSearchAbsoluteProductExpected;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserSearchPageObject searchPage;
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
		existingEmail = dataFaker.getEmailAddress();
		validPassword = dataFaker.getPassword();

		productNotExist = "Macbook pro 2050";
		relativeProductName = "Lenovo";
		absoluteProductName = "ThinkPad X1 Carbon";
		numberSearchRelativeProductExpected = 2;
		numberSearchAbsoluteProductExpected = 1;

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
	public void Search_01_Empty_Data() {
		log.info("Search_01 - Step 01: Click to 'Search' link in the footer area");
		homePage.openFooterPageByText(driver, "Search");
		searchPage = PageGeneratorManagerNopCommerce.getUserSearchPage(driver);

		log.info("Search_01 - Step 02: Click to 'Search' button");
		searchPage.clickToSearchButton();

		log.info("Search_01 - Step 03: Verify search error message displayed");
		verifyEquals(searchPage.getSearchErrorMessage(), "Search term minimum length is 3 characters");

	}

	@Test
	public void Search_02_Data_Not_Exist() {
		log.info("Search_02 - Step 01: Click to 'Search' link in the footer area");
		searchPage.openFooterPageByText(driver, "Search");

		log.info("Search_02 - Step 02: Enter to 'Search keyword' with value is '" + productNotExist + "'");
		searchPage.inputToTextboxByID(driver, "q", productNotExist);

		log.info("Search_02 - Step 03: Click to 'Search' button");
		searchPage.clickToSearchButton();

		log.info("Search_02 - Step 04: Verify search error message displayed");
		verifyEquals(searchPage.getSearchErrorMessage(), "No products were found that matched your criteria.");

	}

	@Test
	public void Search_03_Relative_Product_Name() {
		log.info("Search_03 - Step 01: Click to 'Search' link in the footer area");
		searchPage.openFooterPageByText(driver, "Search");

		log.info("Search_03 - Step 02: Enter to 'Search keyword' with value is '" + relativeProductName + "'");
		searchPage.inputToTextboxByID(driver, "q", relativeProductName);

		log.info("Search_03 - Step 03: Click to 'Search' button");
		searchPage.clickToSearchButton();

		log.info("Search_03 - Step 04: Verify 2 product displayed");
		// verifyTrue(searchPage.isNumberOfSearchResultDisplayed(driver, numberSearchRelativeProductExpected));
		verifyEquals(searchPage.getNumberProductResult(), 2);

		log.info("Search_03 - Step 05: Verify products is displayed");
		verifyEquals(searchPage.getSearchProductNameResultByText("Lenovo IdeaCentre 600 All-in-One PC"), "Lenovo IdeaCentre 600 All-in-One PC");
		verifyEquals(searchPage.getSearchProductNameResultByText("Lenovo Thinkpad X1 Carbon Laptop"), "Lenovo Thinkpad X1 Carbon Laptop");

	}

	@Test
	public void Search_04_Absolute_Product_Name() {
		log.info("Search_04 - Step 01: Click to 'Search' link in the footer area");
		searchPage.openFooterPageByText(driver, "Search");

		log.info("Search_04 - Step 02: Enter to 'Search keyword' with value is '" + absoluteProductName + "'");
		searchPage.inputToTextboxByID(driver, "q", absoluteProductName);

		log.info("Search_04 - Step 03: Click to 'Search' button");
		searchPage.clickToSearchButton();

		log.info("Search_04 - Step 04: Verify 1 product displayed");
		// verifyTrue(searchPage.isNumberOfSearchResultDisplayed(driver, numberSearchAbsoluteProductExpected));
		verifyEquals(searchPage.getNumberProductResult(), 1);

		log.info("Search_04 - Step 05: Verify products is displayed");
		verifyTrue(searchPage.isProductNameResultDisplayedByText("Lenovo Thinkpad X1 Carbon Laptop"));

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
