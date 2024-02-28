package com.nopcommerce.admin;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopCommerce.admin.AdminCustomerAddressPageObject;
import pageObjects.nopCommerce.admin.AdminCustomerDetailPageObject;
import pageObjects.nopCommerce.admin.AdminCustomerPageObject;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import utilities.DataHelper;
import utilities.Environment;

public class Admin_02_Search_Customer extends BaseTest {
	private WebDriver driver;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminCustomerPageObject adminCustomerPage;
	private AdminCustomerDetailPageObject adminCustomerDetailPage;
	private AdminCustomerAddressPageObject adminCustomerAddressPage;
	Environment environmentName;
	private DataHelper dataFaker;
	private String emailAddress, password, firstName, lastName, gender, companyName, dateOfBirth, customerName, editEmailAddress, editFirstName, editLastName, editDateOfBirth, editCompanyName, editAdminComment, editCustomerName;
	private String country, city, address_1, address_2, zipPostalCode, phoneNumber, faxNumber, editCountry, editCity, editAddress_1, editAddress_2, editZipPostalCode, editPhoneNumber, editFaxNumber, stateOrProvince;

	@Parameters({ "envName", "serverName", "browserName", "osName", "osVersion", "ipAddress", "portNumber" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("11") String osVersion,
			@Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
		ConfigFactory.setProperty("env", serverName);
		environmentName = ConfigFactory.create(Environment.class);
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "'");
		driver = getBrowserDriver(envName, environmentName.appAdminUrl(), browserName, osName, osVersion, ipAddress, portNumber);
		adminLoginPage = PageGeneratorManagerNopCommerce.getAdminLoginPage(driver);

		log.info("Pre-condition - Step 02: Input to 'Email' textbox with value 'admin@yourstore.com'");
		adminLoginPage.inputToEmailTextbox("admin@yourstore.com");

		log.info("Pre-condition - Step 03: Input to 'Password' textbox with value 'admin'");
		adminLoginPage.inputToPasswordTextbox("admin");

		log.info("Pre-condition - Step 04: Click to 'Log in' button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();

		log.info("Pre-condition - Step 05: Verify Admin dashboard page displayed");
		verifyTrue(adminDashboardPage.isDashboardHeaderDisplay());

		dataFaker = DataHelper.gerDataHelper();

		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		customerName = firstName + " " + lastName;
		emailAddress = dataFaker.getEmailAddress();
		password = dataFaker.getPassword();
		gender = "Male";
		dateOfBirth = "1/1/2000";
		companyName = "Automation Fc" + " " + generateFakeNumber();

		editEmailAddress = "edit_" + emailAddress;
		editFirstName = "Edit" + " " + firstName;
		editLastName = "Edit" + " " + lastName;
		editCustomerName = editFirstName + " " + editLastName;
		editDateOfBirth = "2/2/2000";
		editCompanyName = "Edit" + " " + companyName;
		editAdminComment = "Edit Customer (Guest)";

		country = "Viet Nam";
		city = "Ho Chi Minh";
		address_1 = "123/04 Le Lai";
		address_2 = "234/05 Hai Phong";
		zipPostalCode = "550000";
		phoneNumber = "0123456789";
		faxNumber = "0987654321";

		editCountry = "United States";
		stateOrProvince = "California";
		editCity = "Albany";
		editAddress_1 = "123 PO Box";
		editAddress_2 = "356 Los Bancos";
		editZipPostalCode = "986589";
		editPhoneNumber = "0987654666";
		editFaxNumber = "44441619998888";

	}

	@Test
	public void Admin_Customer_01_Create_New_Customer() {
		log.info("Admin_Customer_01: Step 01: Click to 'Customers' on menu: Customers/Customers");
		adminDashboardPage.clickToSubMenuByText(driver, "Customers", "Customers");
		adminCustomerPage = PageGeneratorManagerNopCommerce.getAdminCustomerPage(driver);

		log.info("Admin_Customer_01: Step 02: Click to 'Add new' button");
		adminCustomerPage.clickToAddNewButton();
		adminCustomerDetailPage = PageGeneratorManagerNopCommerce.getAdminCustomerDetailPage(driver);

		log.info("Admin_Customer_01: Step 03: Input to email textbox with value is '" + emailAddress + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("Admin_Customer_01: Step 04: Input to password textbox with value is '" + password + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "Password", password);

		log.info("Admin_Customer_01: Step 05: Input to first name textbox with value is '" + firstName + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "FirstName", firstName);

		log.info("Admin_Customer_01: Step 06: Input to last name textbox with value is '" + lastName + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "LastName", lastName);

		log.info("Admin_Customer_01: Step 07: Select gender with value is '" + gender + "'");
		adminCustomerDetailPage.checkToRadioButtonByLabelText(driver, gender);

		log.info("Admin_Customer_01: Step 08: Input to date of birth textbox with value is '" + dateOfBirth + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "DateOfBirth", dateOfBirth);

		log.info("Admin_Customer_01: Step 09: Input to company name textbox with value is '" + companyName + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "Company", companyName);

		log.info("Admin_Customer_01: Step 10: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerDetailPage.deleteItemInDropDownByText("Registered");

		log.info("Admin_Customer_01: Step 11: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerDetailPage.selectItemInDropDownByText("Guests");

		log.info("Admin_Customer_01: Step 12: Input to admin comment textbox with value is 'Add new Customer (Guests)'");
		adminCustomerDetailPage.inputToAdminCommentTextbox("Add new Customer (Guests)");

		log.info("Admin_Customer_01: Step 13: Click to 'Save and Continue Edit' button");
		adminCustomerDetailPage.clickToSaveAndContinueEditButton();

		log.info("Admin_Customer_01: Step 14: Verify success mesage have text 'The new customer has been added successfully.' displayed");
		verifyTrue(adminCustomerDetailPage.isUpdateSuccessMessageByTextDisplayed());

		log.info("Admin_Customer_01: Step 15:  Verify customer infor created exactly");
		verifyEquals(adminCustomerDetailPage.getCustomerTextboxInforByLabel("Email"), emailAddress);
		verifyEquals(adminCustomerDetailPage.getCustomerTextboxInforByLabel("First name"), firstName);
		verifyEquals(adminCustomerDetailPage.getCustomerTextboxInforByLabel("Last name"), lastName);
		verifyTrue(adminCustomerDetailPage.isCustomerGenderRadioButtonByLabelSelected("Male"));
		verifyEquals(adminCustomerDetailPage.getCustomerTextboxInforByLabel("Date of birth"), dateOfBirth);
		verifyEquals(adminCustomerDetailPage.getCustomerTextboxInforByLabel("Company name"), companyName);
		verifyTrue(adminCustomerDetailPage.isActiveCheckboxSelected());
		verifyEquals(adminCustomerDetailPage.getAdminCommnentMessage(), "Add new Customer (Guests)");

		log.info("Admin_Customer_01: Step 16: Click to 'back to customer list' link");
		adminCustomerDetailPage.clickToBackToCustomerList();
		adminCustomerPage = PageGeneratorManagerNopCommerce.getAdminCustomerPage(driver);

		log.info("Admin_Customer_01: Step 17: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_01: Step 18: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_01: Step 19: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_01: Step 20: verify customer displayed in grid customer with name is '" + customerName + "'");
		verifyTrue(adminCustomerPage.isCustomerNameByTextDisplayed(customerName));
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Customer roles"), "Guests");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Company name"), companyName);
	}

	@Test
	public void Admin_Customer_02_Search_Customer_With_Email() {
		log.info("Admin_Customer_02: Step 01: Click to 'Customers' on menu: Customers/Customers");
		adminCustomerPage.clickToSubMenuByText(driver, "Customers", "Customers");

		log.info("Admin_Customer_02: Step 02: Input to Email textbox with value '" + emailAddress + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchEmail", emailAddress);

		log.info("Admin_Customer_02: Step 03: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_02: Step 04: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_02: Step 05: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_02: Step 06:Verify have only 1 customer displayed in grid customer with name is '" + customerName + "'");
		adminCustomerPage.sleepInSecond(2);
		verifyEquals(adminCustomerPage.getQuantityItemInTableDisplayed(driver), 1);
		verifyTrue(adminCustomerPage.isCustomerNameByTextDisplayed(customerName));
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Email"), "Guest");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Customer roles"), "Guests");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Company name"), companyName);
		verifyTrue(adminCustomerPage.isActiveCheckedByCustomerNameDisplayed(customerName, "Active"));
	}

	@Test
	public void Admin_Customer_03_Search_Customer_With_First_Name_And_Last_Name() {
		log.info("Admin_Customer_03: Step 01: Click to 'Customers' on menu: Customers/Customers");
		adminCustomerPage.clickToSubMenuByText(driver, "Customers", "Customers");

		log.info("Admin_Customer_03: Step 02: Input to Firstname textbox with value '" + firstName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchFirstName", firstName);

		log.info("Admin_Customer_03: Step 03: Input to Lastname textbox with value '" + lastName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchLastName", lastName);

		log.info("Admin_Customer_03: Step 04: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_03: Step 05: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_03: Step 06: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_03: Step 07:Verify have only 1 customer displayed in grid customer with name is '" + customerName + "'");
		adminCustomerPage.sleepInSecond(2);
		verifyEquals(adminCustomerPage.getQuantityItemInTableDisplayed(driver), 1);
		verifyTrue(adminCustomerPage.isCustomerNameByTextDisplayed(customerName));
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Email"), "Guest");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Customer roles"), "Guests");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Company name"), companyName);
		verifyTrue(adminCustomerPage.isActiveCheckedByCustomerNameDisplayed(customerName, "Active"));
	}

	@Test
	public void Admin_Customer_04_Search_Customer_With_Company_Name() {
		log.info("Admin_Customer_04: Step 01: Click to 'Customers' on menu: Customers/Customers");
		adminCustomerPage.clickToSubMenuByText(driver, "Customers", "Customers");

		log.info("Admin_Customer_04: Step 02: Input to Company textbox with value '" + companyName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchCompany", companyName);

		log.info("Admin_Customer_04: Step 03: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_04: Step 04: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_04: Step 05: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_04: Step 06:Verify have only 1 customer displayed in grid customer with name is '" + customerName + "'");
		adminCustomerPage.sleepInSecond(2);
		verifyEquals(adminCustomerPage.getQuantityItemInTableDisplayed(driver), 1);
		verifyTrue(adminCustomerPage.isCustomerNameByTextDisplayed(customerName));
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Email"), "Guest");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Customer roles"), "Guests");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Company name"), companyName);
		verifyTrue(adminCustomerPage.isActiveCheckedByCustomerNameDisplayed(customerName, "Active"));
	}

	@Test
	public void Admin_Customer_05_Search_Customer_With_Full_Data() {
		log.info("Admin_Customer_05: Step 01: Click to 'Customers' on menu: Customers/Customers");
		adminCustomerPage.clickToSubMenuByText(driver, "Customers", "Customers");

		log.info("Admin_Customer_05: Step 02: Input to Email textbox with value '" + emailAddress + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchEmail", emailAddress);

		log.info("Admin_Customer_05: Step 03: Input to Firstname textbox with value '" + firstName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchFirstName", firstName);

		log.info("Admin_Customer_05: Step 04: Input to Lastname textbox with value '" + lastName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchLastName", lastName);

		log.info("Admin_Customer_05: Step 05: Select Date of birth in month dropdown with value '1'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchMonthOfBirth", "1");

		log.info("Admin_Customer_05: Step 06: Select Date of birth in day dropdown with value '1'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchDayOfBirth", "1");

		log.info("Admin_Customer_05: Step 07: Input to Company textbox with value '" + companyName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchCompany", companyName);

		log.info("Admin_Customer_05: Step 08: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_05: Step 09: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_05: Step 10: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_05: Step 11:Verify have only 1 customer displayed in grid customer with name is '" + customerName + "'");
		adminCustomerPage.sleepInSecond(2);
		verifyEquals(adminCustomerPage.getQuantityItemInTableDisplayed(driver), 1);
		verifyTrue(adminCustomerPage.isCustomerNameByTextDisplayed(customerName));
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Email"), "Guest");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Customer roles"), "Guests");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(customerName, "Company name"), companyName);
		verifyTrue(adminCustomerPage.isActiveCheckedByCustomerNameDisplayed(customerName, "Active"));
	}

	@Test
	public void Admin_Customer_06_Edit_Customer() {
		log.info("Admin_Customer_06: Step 01: Click to 'Customers' on menu: Customers/Customers");
		adminCustomerPage.clickToSubMenuByText(driver, "Customers", "Customers");

		log.info("Admin_Customer_06: Step 02: Input to Email textbox with value '" + emailAddress + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchEmail", emailAddress);

		log.info("Admin_Customer_06: Step 03: Input to Firstname textbox with value '" + firstName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchFirstName", firstName);

		log.info("Admin_Customer_06: Step 04: Input to Lastname textbox with value '" + lastName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchLastName", lastName);

		log.info("Admin_Customer_06: Step 05: Select Date of birth in month dropdown with value '1'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchMonthOfBirth", "1");

		log.info("Admin_Customer_06: Step 06: Select Date of birth in day dropdown with value '1'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchDayOfBirth", "1");

		log.info("Admin_Customer_06: Step 07: Input to Company textbox with value '" + companyName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchCompany", companyName);

		log.info("Admin_Customer_06: Step 08: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_06: Step 09: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_06: Step 10: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_06: Step 11: Click to 'Edit' button");
		adminCustomerPage.clickToEditButtonByCustomerNameAndColumnName(customerName, "Edit");
		adminCustomerDetailPage = PageGeneratorManagerNopCommerce.getAdminCustomerDetailPage(driver);

		log.info("Admin_Customer_06: Step 12: Input to email textbox with value is '" + editEmailAddress + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "Email", editEmailAddress);

		log.info("Admin_Customer_06: Step 13: Input to first name textbox with value is '" + editFirstName + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "FirstName", editFirstName);

		log.info("Admin_Customer_06: Step 14: Input to last name textbox with value is '" + editLastName + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "LastName", editLastName);

		log.info("Admin_Customer_06: Step 15: Select gender with value is '" + gender + "'");
		adminCustomerDetailPage.checkToRadioButtonByLabelText(driver, gender);

		log.info("Admin_Customer_06: Step 16: Input to date of birth textbox with value is '" + editDateOfBirth + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "DateOfBirth", editDateOfBirth);

		log.info("Admin_Customer_06: Step 17: Input to company name textbox with value is '" + editCompanyName + "'");
		adminCustomerDetailPage.inputToTextboxByID(driver, "Company", editCompanyName);

		log.info("Admin_Customer_06: Step 18: Input to admin comment textbox with value is '" + editAdminComment);
		adminCustomerDetailPage.inputToAdminCommentTextbox(editAdminComment);

		log.info("Admin_Customer_06: Step 19: Click to 'Save' button");
		adminCustomerDetailPage.clickToSaveButton();
		adminCustomerPage = PageGeneratorManagerNopCommerce.getAdminCustomerPage(driver);

		log.info("Admin_Customer_06: Step 20: Verify success mesage have text 'The customer has been updated successfully.' displayed");
		verifyTrue(adminCustomerPage.isUpdateSuccessMessageByTextDisplayed());

		log.info("Admin_Customer_06: Step 21: Input to Email textbox with value '" + editEmailAddress + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchEmail", editEmailAddress);

		log.info("Admin_Customer_06: Step 22: Input to Firstname textbox with value '" + editFirstName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchFirstName", editFirstName);

		log.info("Admin_Customer_06: Step 23: Input to Lastname textbox with value '" + editLastName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchLastName", editLastName);

		log.info("Admin_Customer_06: Step 24: Select Date of birth in month dropdown with value '2'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchMonthOfBirth", "2");

		log.info("Admin_Customer_06: Step 25: Select Date of birth in day dropdown with value '2'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchDayOfBirth", "2");

		log.info("Admin_Customer_06: Step 26: Input to Company textbox with value '" + editCompanyName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchCompany", editCompanyName);

		log.info("Admin_Customer_06: Step 27: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_06: Step 28: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_06: Step 29: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_06: Step 30:Verify customer address displayed in grid customer with name is '" + editCustomerName + "'");
		adminCustomerPage.sleepInSecond(3);
		verifyEquals(adminCustomerPage.getQuantityItemInTableDisplayed(driver), 1);
		verifyTrue(adminCustomerPage.isCustomerNameByTextDisplayed(editCustomerName));
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(editCustomerName, "Email"), "Guest");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(editCustomerName, "Customer roles"), "Guests");
		verifyEquals(adminCustomerPage.getTextItemByCustomerNameAndColumnName(editCustomerName, "Company name"), editCompanyName);
		verifyTrue(adminCustomerPage.isActiveCheckedByCustomerNameDisplayed(editCustomerName, "Active"));
	}

	@Test
	public void Admin_Customer_07_Add_New_Address_In_Customer_Detail() {
		log.info("Admin_Customer_07: Step 01: Click to 'Customers' on menu: Customers/Customers");
		adminCustomerPage.clickToSubMenuByText(driver, "Customers", "Customers");

		log.info("Admin_Customer_07: Step 02: Input to Email textbox with value '" + editEmailAddress + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchEmail", editEmailAddress);

		log.info("Admin_Customer_07: Step 03: Input to Firstname textbox with value '" + editFirstName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchFirstName", editFirstName);

		log.info("Admin_Customer_07: Step 04: Input to Lastname textbox with value '" + editLastName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchLastName", editLastName);

		log.info("Admin_Customer_07: Step 05: Select Date of birth in month dropdown with value '2'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchMonthOfBirth", "2");

		log.info("Admin_Customer_07: Step 06: Select Date of birth in day dropdown with value '2'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchDayOfBirth", "2");

		log.info("Admin_Customer_07: Step 07: Input to Company textbox with value '" + editCompanyName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchCompany", editCompanyName);

		log.info("Admin_Customer_07: Step 08: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_07: Step 09: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_07: Step 10: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_07: Step 11: Click to 'Edit' button");
		adminCustomerPage.clickToEditButtonByCustomerNameAndColumnName(editCustomerName, "Edit");
		adminCustomerDetailPage = PageGeneratorManagerNopCommerce.getAdminCustomerDetailPage(driver);

		log.info("Admin_Customer_07: Step 12: Click to 'Addresses' card");
		adminCustomerDetailPage.clickToCardByText("Addresses");

		log.info("Admin_Customer_07: Step 13: Click to 'Add new address' button");
		adminCustomerDetailPage.clickToButtonByText(driver, "Add new address");
		adminCustomerAddressPage = PageGeneratorManagerNopCommerce.getAdminCustomerAddressPage(driver);

		log.info("Admin_Customer_07: Step 14: Input to first name textbox with value is '" + editFirstName + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_FirstName", editFirstName);

		log.info("Admin_Customer_07: Step 15: Input to last name textbox with value is '" + editLastName + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_LastName", editLastName);

		log.info("Admin_Customer_07: Step 16: Input to email textbox with value is '" + editEmailAddress + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_Email", editEmailAddress);

		log.info("Admin_Customer_07: Step 17: Input to company textbox with value is '" + editCompanyName + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_Company", editCompanyName);

		log.info("Admin_Customer_07: Step 18: Select item in country dropdown with value is '" + country + "'");
		adminCustomerAddressPage.selectItemInDefaultDropdownByName(driver, "Address.CountryId", country);

		log.info("Admin_Customer_07: Step 19: Input to city textbox with value is '" + city + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_City", city);

		log.info("Admin_Customer_07: Step 20: Input to address_1 textbox with value is '" + address_1 + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_Address1", address_1);

		log.info("Admin_Customer_07: Step 21: Input to address_2 textbox with value is '" + address_2 + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_Address2", address_2);

		log.info("Admin_Customer_07: Step 22: Input to Zip / postal code textbox with value is '" + zipPostalCode + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_ZipPostalCode", zipPostalCode);

		log.info("Admin_Customer_07: Step 23: Input to Phone number textbox with value is '" + phoneNumber + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);

		log.info("Admin_Customer_07: Step 24: Input to Fax number textbox with value is '" + faxNumber + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_FaxNumber", faxNumber);

		log.info("Admin_Customer_07: Step 25: Click to 'Save' button");
		adminCustomerAddressPage.clickToButtonByText(driver, "Save");

		log.info("Admin_Customer_07: Step 26: Verify success mesage have text 'The new address has been added successfully.' displayed");
		verifyTrue(adminCustomerAddressPage.isUpdateSuccessMessageByTextDisplayed());

		log.info("Admin_Customer_07: Step 27:  Verify customer infor created exactly");
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("First name"), editFirstName);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Last name"), editLastName);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Email"), editEmailAddress);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Company"), editCompanyName);
		verifyEquals(adminCustomerAddressPage.getAddressCountryDropdownInfor(), country);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("City"), city);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Address 1"), address_1);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Address 2"), address_2);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Zip / postal code"), zipPostalCode);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Phone number"), phoneNumber);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Fax number"), faxNumber);

		log.info("Admin_Customer_07: Step 28: Click to 'back to customer details' link");
		adminCustomerAddressPage.clickToBackToCustomerDetailLink();
		adminCustomerDetailPage = PageGeneratorManagerNopCommerce.getAdminCustomerDetailPage(driver);

		log.info("Admin_Customer_07: Step 29: Click to 'Addresses' card");
		adminCustomerDetailPage.clickToCardByText("Addresses");

		log.info("Admin_Customer_07: Step 30: Verify have only 1 customer address displayed in grid customer with name is '" + editEmailAddress + "'");
		adminCustomerDetailPage.sleepInSecond(3);
		verifyTrue(adminCustomerDetailPage.isEmailAddressByTextDisplayed(editEmailAddress));
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "First name"), editFirstName);
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "Last name"), editLastName);
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "Phone number"), phoneNumber);
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "Fax number"), faxNumber);
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "Address"), editCompanyName + "\n" + address_1 + "\n" + address_2 + "\n" + city + "," + zipPostalCode + "\n" + country);
	}

	@Test
	public void Admin_Customer_08_Edit_Address_In_Customer_Detail() {
		log.info("Admin_Customer_08: Step 01: Click to 'Customers' on menu: Customers/Customers");
		adminCustomerPage.clickToSubMenuByText(driver, "Customers", "Customers");

		log.info("Admin_Customer_08: Step 02: Input to Email textbox with value '" + editEmailAddress + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchEmail", editEmailAddress);

		log.info("Admin_Customer_08: Step 03: Input to Firstname textbox with value '" + editFirstName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchFirstName", editFirstName);

		log.info("Admin_Customer_08: Step 04: Input to Lastname textbox with value '" + editLastName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchLastName", editLastName);

		log.info("Admin_Customer_08: Step 05: Select Date of birth in month dropdown with value '2'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchMonthOfBirth", "2");

		log.info("Admin_Customer_08: Step 06: Select Date of birth in day dropdown with value '2'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchDayOfBirth", "2");

		log.info("Admin_Customer_08: Step 07: Input to Company textbox with value '" + editCompanyName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchCompany", editCompanyName);

		log.info("Admin_Customer_08: Step 08: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_08: Step 09: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_08: Step 10: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_08: Step 11: Click to 'Edit' button");
		adminCustomerPage.clickToEditButtonByCustomerNameAndColumnName(editCustomerName, "Edit");
		adminCustomerDetailPage = PageGeneratorManagerNopCommerce.getAdminCustomerDetailPage(driver);

		log.info("Admin_Customer_08: Step 12: Click to 'Addresses' card");
		adminCustomerDetailPage.clickToCardByText("Addresses");

		log.info("Admin_Customer_08: Step 13: Click to 'Edit' icon at '" + editEmailAddress + "'");
		adminCustomerDetailPage.clickToEditIconByEmailAddressAndColumnName(editEmailAddress, "Edit");
		adminCustomerAddressPage = PageGeneratorManagerNopCommerce.getAdminCustomerAddressPage(driver);

		log.info("Admin_Customer_08: Step 14: Input to first name textbox with value is '" + editFirstName + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_FirstName", editFirstName);

		log.info("Admin_Customer_08: Step 15: Input to last name textbox with value is '" + editLastName + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_LastName", editLastName);

		log.info("Admin_Customer_08: Step 16: Input to email textbox with value is '" + editEmailAddress + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_Email", editEmailAddress);

		log.info("Admin_Customer_08: Step 17: Input to company textbox with value is '" + editCompanyName + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_Company", editCompanyName);

		log.info("Admin_Customer_08: Step 18: Select item in country dropdown with value is '" + editCountry + "'");
		adminCustomerAddressPage.selectItemInDefaultDropdownByName(driver, "Address.CountryId", editCountry);

		log.info("Admin_Customer_08: Step 19: Select item in State/province dropdown with value is '" + stateOrProvince + "'");
		adminCustomerAddressPage.selectItemInDefaultDropdownByName(driver, "Address.StateProvinceId", stateOrProvince);

		log.info("Admin_Customer_08: Step 20: Input to city textbox with value is '" + editCity + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_City", editCity);

		log.info("Admin_Customer_08: Step 21: Input to address_1 textbox with value is '" + editAddress_1 + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_Address1", editAddress_1);

		log.info("Admin_Customer_08: Step 22: Input to address_2 textbox with value is '" + editAddress_2 + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_Address2", editAddress_2);

		log.info("Admin_Customer_08: Step 23: Input to Zip / postal code textbox with value is '" + editZipPostalCode + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_ZipPostalCode", editZipPostalCode);

		log.info("Admin_Customer_08: Step 24: Input to Phone number textbox with value is '" + editPhoneNumber + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_PhoneNumber", editPhoneNumber);

		log.info("Admin_Customer_08: Step 25: Input to Fax number textbox with value is '" + editFaxNumber + "'");
		adminCustomerAddressPage.inputToTextboxByID(driver, "Address_FaxNumber", editFaxNumber);

		log.info("Admin_Customer_08: Step 26: Click to 'Save' button");
		adminCustomerAddressPage.clickToButtonByName("save");

		log.info("Admin_Customer_08: Step 27: Verify success mesage have text 'The new address has been added successfully.' displayed");
		verifyTrue(adminCustomerAddressPage.isUpdateSuccessMessageByTextDisplayed());

		log.info("Admin_Customer_08: Step 28:  Verify customer infor created exactly");
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("First name"), editFirstName);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Last name"), editLastName);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Email"), editEmailAddress);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Company"), editCompanyName);
		verifyEquals(adminCustomerAddressPage.getAddressCountryDropdownInfor(), editCountry);
		verifyEquals(adminCustomerAddressPage.getAddressStateOrProvinceDropdownInfor(), stateOrProvince);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("City"), editCity);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Address 1"), editAddress_1);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Address 2"), editAddress_2);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Zip / postal code"), editZipPostalCode);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Phone number"), editPhoneNumber);
		verifyEquals(adminCustomerAddressPage.getCustomerAddressInforTextboxInforByLabel("Fax number"), editFaxNumber);

		log.info("Admin_Customer_08: Step 29: Click to 'back to customer details' link");
		adminCustomerAddressPage.clickToBackToCustomerDetailLink();
		adminCustomerDetailPage = PageGeneratorManagerNopCommerce.getAdminCustomerDetailPage(driver);

		log.info("Admin_Customer_08: Step 30: Click to 'Addresses' card");
		adminCustomerDetailPage.clickToCardByText("Addresses");

		log.info("Admin_Customer_08: Step 31: Verify customer address displayed in grid customer with name is '" + editEmailAddress + "'");
		adminCustomerDetailPage.sleepInSecond(2);
		verifyTrue(adminCustomerDetailPage.isEmailAddressByTextDisplayed(editEmailAddress));
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "First name"), editFirstName);
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "Last name"), editLastName);
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "Phone number"), editPhoneNumber);
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "Fax number"), editFaxNumber);
		verifyEquals(adminCustomerDetailPage.getTextItemByEmailAddressAndColumnName(editEmailAddress, "Address"),
				editCompanyName + "\n" + editAddress_1 + "\n" + editAddress_2 + "\n" + editCity + "," + stateOrProvince + "," + editZipPostalCode + "\n" + editCountry);
	}

	@Test
	public void Admin_Customer_09_Delete_Address_In_Customer_Detail() {
		log.info("Admin_Customer_09: Step 01: Click to 'Customers' on menu: Customers/Customers");
		adminCustomerPage.clickToSubMenuByText(driver, "Customers", "Customers");

		log.info("Admin_Customer_09: Step 02: Input to Email textbox with value '" + editEmailAddress + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchEmail", editEmailAddress);

		log.info("Admin_Customer_09: Step 03: Input to Firstname textbox with value '" + editFirstName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchFirstName", editFirstName);

		log.info("Admin_Customer_09: Step 04: Input to Lastname textbox with value '" + editLastName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchLastName", editLastName);

		log.info("Admin_Customer_09: Step 05: Select Date of birth in month dropdown with value '2'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchMonthOfBirth", "2");

		log.info("Admin_Customer_09: Step 06: Select Date of birth in day dropdown with value '2'");
		adminCustomerPage.selectItemInDefaultDropdownByName(driver, "SearchDayOfBirth", "2");

		log.info("Admin_Customer_09: Step 07: Input to Company textbox with value '" + editCompanyName + "'");
		adminCustomerPage.inputToTextboxByID(driver, "SearchCompany", editCompanyName);

		log.info("Admin_Customer_09: Step 08: Delete 'Customer roles' with text is 'Registered'");
		adminCustomerPage.deleteItemInCustomerSearchRolesDropdown("Registered");

		log.info("Admin_Customer_09: Step 09: Select an item in  'Customer roles' dropdown with value is 'Guests'");
		adminCustomerPage.selectItemInCustomerSearchRolesDropdown("Guests");

		log.info("Admin_Customer_09: Step 10: Click to 'Search' button");
		adminCustomerPage.clickToSearchButton();

		log.info("Admin_Customer_09: Step 11: Click to 'Edit' button");
		adminCustomerPage.clickToEditButtonByCustomerNameAndColumnName(editCustomerName, "Edit");
		adminCustomerDetailPage = PageGeneratorManagerNopCommerce.getAdminCustomerDetailPage(driver);

		log.info("Admin_Customer_09: Step 12: Click to 'Addresses' card");
		adminCustomerDetailPage.clickToCardByText("Addresses");

		log.info("Admin_Customer_09: Step 13: Click to 'Delete' icon at '" + editEmailAddress + "'");
		adminCustomerDetailPage.clickToDeleteIconByEmailAddressAndColumnName(editEmailAddress, "Delete");

		log.info("Admin_Customer_09: Step 14: Click to 'OK' at accept alert");
		adminCustomerDetailPage.acceptAlert(driver);

		log.info("Admin_Customer_09: Step 15: Verify have no Address on grid display");
		verifyTrue(adminCustomerDetailPage.isMessageDataTableByCardNameAndTextDisplayed("Addresses", "No data available in table"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
