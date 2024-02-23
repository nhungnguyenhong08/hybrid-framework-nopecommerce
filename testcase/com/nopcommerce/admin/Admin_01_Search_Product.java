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
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.admin.AdminProductDetailPageObject;
import pageObjects.nopCommerce.admin.AdminProductPageObject;
import utilities.Environment;

public class Admin_01_Search_Product extends BaseTest {
	private WebDriver driver;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminProductPageObject adminProductPage;
	private AdminProductDetailPageObject adminProductDetailPage;
	Environment environmentName;
	private String productName, productSKU;

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

		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		productSKU = "LE_IC_600";

	}

	@Test
	public void Admin_Search_01_Search_With_Product_Name() {
		log.info("Admin_Search_01: Step 01: Click to 'Products Page' on menu: Catalog/Products");
		adminDashboardPage.clickToSubMenuByText(driver, "Catalog", "Products");
		adminProductPage = PageGeneratorManagerNopCommerce.getAdminProductPage(driver);

		log.info("Admin_Search_01: Step 02: Input to product name textbox with value is '" + productName + "'");
		adminProductPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_Search_01: Step 03: Click to 'Search' button");
		adminProductPage.clickToSearchProductButton();

		log.info("Admin_Search_01: Step 14: Verify have only 1 product display in grid product with name is '" + productName + "'");
		adminProductPage.sleepInSecond(2);
		verifyEquals(adminProductPage.getQuantityItemInTableDisplayed(driver), 1);
		verifyTrue(adminProductPage.isProductNameByTextDisplayed(productName));
		verifyEquals(adminProductPage.getTextItemByProductNameAndColumName(productName, "SKU"), "LE_IC_600");
		verifyEquals(adminProductPage.getTextItemByProductNameAndColumName(productName, "Price"), "500");
		verifyEquals(adminProductPage.getTextItemByProductNameAndColumName(productName, "Stock quantity"), "10000");
		verifyTrue(adminProductPage.isPublicItemCheckedByProductNameDisplayed(productName, "Published"));
	}

	@Test
	public void Admin_Search_02_Search_With_Product_Name_And_Category_Uncheck() {
		log.info("Admin_Search_02: Step 01: Click to 'Products Page' on menu: Catalog/Products");
		adminProductPage.clickToSubMenuByText(driver, "Catalog", "Products");

		log.info("Admin_Search_02: Step 02: Input to product name textbox with value is '" + productName + "'");
		adminProductPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_Search_02: Step 03: Select item in 'Category' dropdown with value 'Computers'");
		adminProductPage.selectItemInDefaultDropdownByLabel("Category", "Computers");

		log.info("Admin_Search_02: Step 04: Uncheck to 'Search subcategories' checkbox");
		adminProductPage.uncheckToSearchSubcategories();

		log.info("Admin_Search_02: Step 05: Click to 'Search' button");
		adminProductPage.clickToSearchProductButton();

		log.info("Admin_Search_02: Step 06: Verify 'No data available in table' message is displayed");
		verifyTrue(adminProductPage.isMessageDataTableDisplayed("No data available in table"));
	}

	@Test
	public void Admin_Search_03_Search_With_Product_Name_And_Category_Checked() {
		log.info("Admin_Search_03: Step 01: Click to 'Products Page' on menu: Catalog/Products");
		adminProductPage.clickToSubMenuByText(driver, "Catalog", "Products");

		log.info("Admin_Search_03: Step 02: Input to product name textbox with value is '" + productName + "'");
		adminProductPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_Search_03: Step 03: Select item in 'Category' dropdown with value 'Computers'");
		adminProductPage.selectItemInDefaultDropdownByLabel("Category", "Computers");

		log.info("Admin_Search_03: Step 04: Check to 'Search subcategories' checkbox");
		adminProductPage.checkToSearchSubcategories();

		log.info("Admin_Search_03: Step 05: Click to 'Search' button");
		adminProductPage.clickToSearchProductButton();

		log.info("Admin_Search_03: Step 06: Verify have only 1 product display in grid product with name is '" + productName + "'");
		adminProductPage.sleepInSecond(2);
		verifyEquals(adminProductPage.getQuantityItemInTableDisplayed(driver), 1);
		verifyTrue(adminProductPage.isProductNameByTextDisplayed(productName));
		verifyEquals(adminProductPage.getTextItemByProductNameAndColumName(productName, "SKU"), "LE_IC_600");
		verifyEquals(adminProductPage.getTextItemByProductNameAndColumName(productName, "Price"), "500");
		verifyEquals(adminProductPage.getTextItemByProductNameAndColumName(productName, "Stock quantity"), "10000");
		verifyTrue(adminProductPage.isPublicItemCheckedByProductNameDisplayed(productName, "Published"));
	}

	@Test
	public void Admin_Search_04_Search_With_Product_Name_And_Child_Category() {
		log.info("Admin_Search_04: Step 01: Click to 'Products Page' on menu: Catalog/Products");
		adminProductPage.clickToSubMenuByText(driver, "Catalog", "Products");

		log.info("Admin_Search_04: Step 02: Input to product name textbox with value is '" + productName + "'");
		adminProductPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_Search_04: Step 03: Select item in 'Category' dropdown with value 'Computers >> Desktops'");
		adminProductPage.selectItemInDefaultDropdownByLabel("Category", "Computers >> Desktops");

		log.info("Admin_Search_04: Step 04: Unheck to 'Search subcategories' checkbox");
		adminProductPage.uncheckToSearchSubcategories();

		log.info("Admin_Search_04: Step 05: Click to 'Search' button");
		adminProductPage.clickToSearchProductButton();

		log.info("Admin_Search_04: Step 06: Verify have only 1 product display in grid product with name is '" + productName + "'");
		adminProductPage.sleepInSecond(2);
		verifyEquals(adminProductPage.getQuantityItemInTableDisplayed(driver), 1);
		verifyTrue(adminProductPage.isProductNameByTextDisplayed(productName));
		verifyEquals(adminProductPage.getTextItemByProductNameAndColumName(productName, "SKU"), "LE_IC_600");
		verifyEquals(adminProductPage.getTextItemByProductNameAndColumName(productName, "Price"), "500");
		verifyEquals(adminProductPage.getTextItemByProductNameAndColumName(productName, "Stock quantity"), "10000");
		verifyTrue(adminProductPage.isPublicItemCheckedByProductNameDisplayed(productName, "Published"));
	}

	@Test
	public void Admin_Search_05_Search_With_Product_Name_And_Manufacturer() {
		log.info("Admin_Search_05: Step 01: Click to 'Products Page' on menu: Catalog/Products");
		adminProductPage.clickToSubMenuByText(driver, "Catalog", "Products");

		log.info("Admin_Search_05: Step 02: Input to product name textbox with value is '" + productName + "'");
		adminProductPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_Search_05: Step 03: Select item in 'Category' dropdown with value 'All'");
		adminProductPage.selectItemInDefaultDropdownByLabel("Category", "All");

		log.info("Admin_Search_05: Step 04: Uncheck to 'Search subcategories' checkbox");
		adminProductPage.uncheckToSearchSubcategories();

		log.info("Admin_Search_05: Step 05: Select item in 'Manufacturer' dropdown with value 'Apple'");
		adminProductPage.selectItemInDefaultDropdownByLabel("Manufacturer", "Apple");

		log.info("Admin_Search_05: Step 06: Click to 'Search' button");
		adminProductPage.clickToSearchProductButton();

		log.info("Admin_Search_05: Step 07: Verify 'No data available in table' message is displayed");
		verifyTrue(adminProductPage.isMessageDataTableDisplayed("No data available in table"));
	}

	@Test
	public void Admin_Search_06_Go_Directly_To_Product_SKU() {
		log.info("Admin_Search_06: Step 01: Click to 'Products Page' on menu: Catalog/Products");
		adminProductPage.clickToSubMenuByText(driver, "Catalog", "Products");

		log.info("Admin_Search_06: Step 02: Input to 'Go directly to product SKU' textbox with value is '" + productSKU + "'");
		adminProductPage.inputToTextboxByID(driver, "GoDirectlyToSku", productSKU);

		log.info("Admin_Search_06: Step 03: Click to 'GO' button");
		adminProductPage.clickToButtonByText(driver, "Go");

		log.info("Admin_Search_06: Step 04: Verify product detail is displayed");
		adminProductDetailPage = PageGeneratorManagerNopCommerce.getAdminDetailProductPage(driver);
		verifyTrue(adminProductDetailPage.isHeaderTitleByTextDisplayed("Edit product details - Lenovo IdeaCentre 600 All-in-One PC"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
