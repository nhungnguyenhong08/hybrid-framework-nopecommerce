package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataHelper;

public class User_03_My_Account extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, existingEmail, validPassword, editFirstName, editLastName, editEmail, gender, date, month, year, companyName, country, city, state, address1, address2, zipCode, phoneNumber, faxNumber, newPassword,
			reviewProduct, reviewTitle, reviewText, reviewRating;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserChangePasswordPageObject changePasswordPage;
	private UserProductPageObject productPage;
	private UserMyProductReviewPageObject myProductReviewPage;
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
		country = "Viet Nam";
		city = "Da Nang";
		state = "Other";
		address1 = "123/04 Le Lai";
		address2 = "234/5 hai Phong";
		zipCode = "550000";
		phoneNumber = "0123456789";
		faxNumber = "0987654321";
		newPassword = dataFaker.getPassword();

		reviewProduct = "Apple MacBook Pro 13-inch";
		reviewTitle = "Best product ever!!";
		reviewText = "I like this Notebook";
		reviewRating = "4";

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
		customerInforPage = PageGeneratorManagerNopCommerce.getUserCustomerInforPage(driver);

		log.info("MyAccount_01 - Step 02: Update gender with value is'" + gender + "'");
		customerInforPage.clickToRadioButtonByLabel(driver, gender);

		log.info("MyAccount_01 - Step 03: Update First name with value is'" + editFirstName + "'");
		customerInforPage.inputToTextboxByID(driver, "FirstName", editFirstName);

		log.info("MyAccount_01 - Step 04: Update Last name with value is'" + editLastName + "'");
		customerInforPage.inputToTextboxByID(driver, "LastName", editLastName);

		log.info("MyAccount_01 - Step 05: Update Date with value is'" + date + "'");
		customerInforPage.selectToDropdownByName(driver, "DateOfBirthDay", date);

		log.info("MyAccount_01 - Step 06: Update Month with value is'" + month + "'");
		customerInforPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);

		log.info("MyAccount_01 - Step 07: Update Year with value is'" + year + "'");
		customerInforPage.selectToDropdownByName(driver, "DateOfBirthYear", year);

		log.info("MyAccount_01 - Step 08: Update Email with value is'" + editEmail + "'");
		customerInforPage.inputToTextboxByID(driver, "Email", editEmail);

		log.info("MyAccount_01 - Step 09: Update Company name with value is'" + companyName + "'");
		customerInforPage.inputToTextboxByID(driver, "Company", companyName);

		log.info("MyAccount_01 - Step 10: Click to 'Save' button");
		customerInforPage.clickToButtonByText(driver, "Save");

		log.info("MyAccount_01 - Step 11: Verify update message successfully displayed");
		Assert.assertEquals(customerInforPage.getSucessUpdateMessage(), "The customer info has been updated successfully.");

		log.info("MyAccount_01: Step 12: Verify Gender was updated with value '" + gender + "'");
		verifyTrue(customerInforPage.isRadioButtonByLabelTextSelected(driver, gender));

		log.info("MyAccount_01: Step 13: Verify Firstname was updated with value '" + editFirstName + "'");
		verifyEquals(customerInforPage.getValueOfTextboxByID(driver, "FirstName", "value"), editFirstName);

		log.info("MyAccount_01: Step 14: Verify Lastname was updated with value '" + editLastName + "'");
		verifyEquals(customerInforPage.getValueOfTextboxByID(driver, "LastName", "value"), editLastName);

		log.info("MyAccount_01: Step 15: Verify Date was updated with value '" + date + "'");
		verifyEquals(customerInforPage.getValueSelectedOfDropboxByName(driver, "DateOfBirthDay"), date);

		log.info("MyAccount_01: Step 16: Verify Month was updated with value '" + month + "'");
		verifyEquals(customerInforPage.getValueSelectedOfDropboxByName(driver, "DateOfBirthMonth"), month);

		log.info("MyAccount_01: Step 17: Verify Year was updated with value '" + year + "'");
		verifyEquals(customerInforPage.getValueSelectedOfDropboxByName(driver, "DateOfBirthYear"), year);

		log.info("MyAccount_01: Step 18: Verify Email was updated with value '" + editEmail + "'");
		verifyEquals(customerInforPage.getValueOfTextboxByID(driver, "Email", "value"), editEmail);

		log.info("MyAccount_01: Step 19: Verify Company name was updated with value '" + companyName + "'");
		verifyEquals(customerInforPage.getValueOfTextboxByID(driver, "Company", "value"), companyName);
	}

	@Test
	public void MyAccount_02__Add_Address() {
		log.info("MyAccount_02 - Step 01: Click To Addresses page at list box menu");
		addressPage = (UserAddressPageObject) customerInforPage.openPageAtMyAccountByName(driver, "Addresses");

		log.info("MyAccount_02 - Step 02: Click To 'Add new; button");
		addressPage.clickToButtonByText(driver, "Add new");

		log.info("MyAccount_02 - Step 03: Add First Name with value is'" + editFirstName + "'");
		addressPage.inputToTextboxByID(driver, "Address_FirstName", editFirstName);

		log.info("MyAccount_02 - Step 04: Add Last Name with value is'" + editLastName + "'");
		addressPage.inputToTextboxByID(driver, "Address_LastName", editLastName);

		log.info("MyAccount_02 - Step 05: Add Email address with value is'" + editEmail + "'");
		addressPage.inputToTextboxByID(driver, "Address_Email", editEmail);

		log.info("MyAccount_02 - Step 06: Add Company name with value is'" + companyName + "'");
		addressPage.inputToTextboxByID(driver, "Address_Company", companyName);

		log.info("MyAccount_02 - Step 07: Select item in Country dropdown with value is'" + country + "'");
		addressPage.selectToDropdownByName(driver, "Address.CountryId", country);

		log.info("MyAccount_02 - Step 08: Select item in State/ province dropdown with value is'" + state + "'");
		addressPage.selectToDropdownByName(driver, "Address.StateProvinceId", state);

		log.info("MyAccount_02 - Step 09: Add City with value is'" + city + "'");
		addressPage.inputToTextboxByID(driver, "Address_City", city);

		log.info("MyAccount_02 - Step 10: Add Address1 with value is'" + address1 + "'");
		addressPage.inputToTextboxByID(driver, "Address_Address1", address1);

		log.info("MyAccount_02 - Step 11: Add Address2 with value is'" + address2 + "'");
		addressPage.inputToTextboxByID(driver, "Address_Address2", address2);

		log.info("MyAccount_02 - Step 12: Add Zip/ postal code with value is'" + zipCode + "'");
		addressPage.inputToTextboxByID(driver, "Address_ZipPostalCode", zipCode);

		log.info("MyAccount_02 - Step 13: Add Phone number with value is'" + phoneNumber + "'");
		addressPage.inputToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);

		log.info("MyAccount_02 - Step 14: Add Fax number with value is'" + faxNumber + "'");
		addressPage.inputToTextboxByID(driver, "Address_FaxNumber", faxNumber);

		log.info("MyAccount_02 - Step 15: Click to 'Save' button");
		addressPage.clickToButtonByText(driver, "Save");

		log.info("MyAccount_01 - Step 16: Verify update message successfully displayed");
		Assert.assertEquals(addressPage.getAddNewAddressSuccessMessage(), "The new address has been added successfully.");

		log.info("MyAccount_01: Step 17: Verify all information was updated");
		verifyEquals(addressPage.getUpdatedAddressOfInforByClass(driver, "name"), editFirstName + " " + editLastName);
		verifyEquals(addressPage.getUpdatedAddressOfInforByClass(driver, "email"), "Email: " + editEmail);
		verifyEquals(addressPage.getUpdatedAddressOfInforByClass(driver, "phone"), "Phone number: " + phoneNumber);
		verifyEquals(addressPage.getUpdatedAddressOfInforByClass(driver, "fax"), "Fax number: " + faxNumber);
		verifyEquals(addressPage.getUpdatedAddressOfInforByClass(driver, "company"), companyName);
		verifyEquals(addressPage.getUpdatedAddressOfInforByClass(driver, "address1"), address1);
		verifyEquals(addressPage.getUpdatedAddressOfInforByClass(driver, "address2"), address2);
		verifyEquals(addressPage.getUpdatedAddressOfInforByClass(driver, "city-state-zip"), city + ", " + zipCode);
		verifyEquals(addressPage.getUpdatedAddressOfInforByClass(driver, "country"), country);

	}

	@Test
	public void MyAccount_03__Update_Password() {
		log.info("MyAccount_03 - Step 01: Click to 'Change password' page at list box menu");
		changePasswordPage = (UserChangePasswordPageObject) addressPage.openPageAtMyAccountByName(driver, "Change password");

		log.info("MyAccount_03 - Step 02: Input into Old password textbox with value is'" + validPassword + "'");
		changePasswordPage.inputToTextboxByID(driver, "OldPassword", validPassword);

		log.info("MyAccount_03 - Step 03: Input into New password textbox with value is'" + newPassword + "'");
		changePasswordPage.inputToTextboxByID(driver, "NewPassword", newPassword);

		log.info("MyAccount_03 - Step 04: Input into Confirm password textbox with value is'" + newPassword + "'");
		changePasswordPage.inputToTextboxByID(driver, "ConfirmNewPassword", newPassword);

		log.info("MyAccount_03 - Step 05: Click to 'Change password' button");
		changePasswordPage.clickToButtonByText(driver, "Change password");

		log.info("MyAccount_03 - Step 06: Verify change password message successfully displayed");
		verifyEquals(changePasswordPage.getChangePasswordSuccessMessage(), "Password was changed");

		log.info("MyAccount_03 - Step 07: Close change password success message");
		changePasswordPage.closeBarNotification(driver);

		log.info("MyAccount_03 - Step 08: Click to 'Log out' link");
		changePasswordPage.openHeaderUpperPageByName(driver, "Log out");
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		log.info("MyAccount_03 - Step 09: Click to 'Log in' link");
		homePage.openHeaderUpperPageByName(driver, "Log in");
		loginPage = PageGeneratorManagerNopCommerce.getUserLoginPage(driver);

		log.info("MyAccount_03 - Step 10: Enter to Email textbox with value is '" + editEmail + "'");
		loginPage.inputToEmailTextbox(editEmail);

		log.info("MyAccount_03 - Step 11: Enter to Password textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTextbox(validPassword);

		log.info("MyAccount_03 - Step 12: Click to Login button");
		loginPage.clickToLoginButton();

		log.info("MyAccount_03 - Step 13: Verify log in unsucessful message displayed");
		verifyEquals(loginPage.getErrorMessageUnsucessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		log.info("MyAccount_03 - Step 14: Click to 'Log in' link again");
		loginPage.openHeaderUpperPageByName(driver, "Log in");

		log.info("MyAccount_03 - Step 15: Enter to Email textbox with value is '" + editEmail + "'");
		loginPage.inputToEmailTextbox(editEmail);

		log.info("MyAccount_03 - Step 16: Enter to Password textbox with value is '" + newPassword + "'");
		loginPage.inputToPasswordTextbox(newPassword);

		log.info("MyAccount_03 - Step 17: Click to Login button");
		loginPage.clickToLoginButton();

		log.info("MyAccount_03 - Step 18: Verify Home page Displayed");
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void MyAccount_04__Add_My_Product_Review() {
		log.info("MyAccount_04 - Step 18: Open 'My account link' at header upper page");
		homePage.openHeaderUpperPageByName(driver, "My account");

		log.info("MyAccount_04 - Step 18: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("MyAccount_04 - Step 18: Click to see information of product: " + reviewProduct);
		productPage.clickProductToSeeDetailByText(driver, reviewProduct);

		log.info("MyAccount_04 - Step 03: Click to 'Add your review' link");
		productPage.clickToAddYourReviewLink();

		log.info("MyAccount_04 - Step 04: Verify Product review page is displayed");
		verifyEquals(productPage.getProductReviewPage(), "Product reviews for " + reviewProduct);

		log.info("MyAccount_04 - Step 05: Enter to Review title with value is'" + reviewTitle + "'");
		productPage.inputToTextboxByID(driver, "AddProductReview_Title", reviewTitle);

		log.info("MyAccount_04 - Step 06: Enter to Product review text with value is'" + reviewText + "'");
		productPage.inputToReviewTextBox(driver, reviewText);

		log.info("MyAccount_04 - Step 07: Choose product rating with value is'" + reviewRating + "'");
		productPage.AddProductRatingByValue(driver, reviewRating);

		log.info("MyAccount_04 - Step 08: Click to 'Submit review' button");
		productPage.clickToButtonByText(driver, "Submit review");

		log.info("MyAccount_04 - Step 09: Verify Product review is successfully added");
		verifyTrue(productPage.isSuccessMessageDisplayed());

		log.info("MyAccount_04 - Step 10: Click To My Account Link");
		productPage.openHeaderUpperPageByName(driver, "My account");
		customerInforPage = PageGeneratorManagerNopCommerce.getUserCustomerInforPage(driver);

		log.info("MyAccount_04 - Step 11: Click to 'My product reviews' page at list box menu");
		myProductReviewPage = (UserMyProductReviewPageObject) customerInforPage.openPageAtMyAccountByName(driver, "My product reviews");

		log.info("MyAccount_04 - Step 12: Verify Product review is successfully added");
		verifyTrue(myProductReviewPage.isProductReviewDisplayed(driver, reviewProduct));

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
