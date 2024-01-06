package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookies;

import commons.BaseTest;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;

public class User_05_Sort_Display_Paging extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserProductPageObject productPage;
	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "'");
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		log.info("Pre-condition - Step 02: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();

		log.info("Pre-condition - Step 03: Set cookie and reload page");
		loginPage.setCookies(driver, Common_01_Register_Cookies.loggedCookies);
		loginPage.refreshCurrentPage(driver);

		log.info("Pre-Condition - Step 04: Verify Home page Displayed");
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void Sort_Display_01_Sort_Name_A_To_Z() {
		log.info("Sort_Display_01 - Step 01: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Sort_Display_01: Step 02: Click To 'Sort by' dropdown");
		productPage.selectToDropdownByName(driver, "products-orderby", "Name: A to Z");

		log.info("Sort_Display_01: Step 03: Click To 'Display per page' dropdown");
		productPage.selectToDropdownByName(driver, "products-pagesize", "9");

		log.info("Sort_Display_01: Step 04: Products names are sorted in ascending Order");
		verifyTrue(productPage.isProductNameSortAscending());
	}

	@Test
	public void Sort_Display_02_Sort_Name_Z_To_A() {
		log.info("Sort_Display_02 - Step 01: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		productPage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");

		log.info("Sort_Display_02: Step 02: Click To 'Sort by' dropdown");
		productPage.selectToDropdownByName(driver, "products-orderby", "Name: Z to A");

		log.info("Sort_Display_02: Step 03: Click To 'Display per page' dropdown");
		productPage.selectToDropdownByName(driver, "products-pagesize", "9");

		log.info("Sort_Display_02: Step 04: Products names are sorted in ascending Order");
		verifyTrue(productPage.areJQueryAndJSLoadedSuccess(driver));
		verifyTrue(productPage.isProductNameSortDescending());
	}

	@Test
	public void Sort_Display_03_Sort_Price_Low_To_High() {
		log.info("Sort_Display_03 - Step 01: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		productPage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");

		log.info("Sort_Display_03: Step 02: Click To 'Sort by' dropdown");
		productPage.selectToDropdownByName(driver, "products-orderby", "Price: Low to High");

		log.info("Sort_Display_03: Step 03: Click To 'Display per page' dropdown");
		productPage.selectToDropdownByName(driver, "products-pagesize", "9");

		log.info("Sort_Display_03: Step 04: Products price is sorted in ascending Order");
		verifyTrue(productPage.areJQueryAndJSLoadedSuccess(driver));
		verifyTrue(productPage.isProductPriceSortAscending());
	}

	@Test
	public void Sort_Display_04_Sort_Price_High_To_Low() {
		log.info("Sort_Display_04 - Step 01: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		productPage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");

		log.info("Sort_Display_04: Step 02: Click To 'Sort by' dropdown");
		productPage.selectToDropdownByName(driver, "products-orderby", "Price: High to Low");

		log.info("Sort_Display_04: Step 03: Click To 'Display per page' dropdown");
		productPage.selectToDropdownByName(driver, "products-pagesize", "9");

		log.info("Sort_Display_04: Step 04: Products price is sorted in descending Order");
		verifyTrue(productPage.areJQueryAndJSLoadedSuccess(driver));
		verifyTrue(productPage.isProductPriceSortDesscending());

	}

	@Test
	public void Sort_Display_05_Display_3_Product_Per_Page() {
		log.info("Sort_Display_05 - Step 01: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		productPage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");

		log.info("Sort_Display_05: Step 02: Click To 'Display per page' dropdown");
		productPage.selectToDropdownByName(driver, "products-pagesize", "3");

		log.info("Sort_Display_05: Step 03: Verify Product page size is correct");
		verifyTrue(productPage.areJQueryAndJSLoadedSuccess(driver));
		verifyTrue(productPage.isProductNumberDisplayAsExpected(driver, 3));

		log.info("Sort_Display_05: Step 04: Verify pagination is dispalyed");
		verifyTrue(productPage.isPaginationDisplayed(driver));

		log.info("Sort_Display_05: Step 05: Verify pagination next icon is dispalyed");
		verifyTrue(productPage.isPaginationIconDisplay(driver, "Next"));

		log.info("TC_05_Display: Step 06: Click To Page 2 link");
		productPage.clickToPaginationLink(driver, "2");

		log.info("TC_05_Display: Step 07: Verify pagination Previous icon is dispalyed");
		verifyTrue(productPage.areJQueryAndJSLoadedSuccess(driver));
		verifyTrue(productPage.isPaginationIconDisplay(driver, "Previous"));
	}

	@Test
	public void Sort_Display_06_Display_6_Product_Per_Page() {
		log.info("Sort_Display_06 - Step 01: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		productPage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");

		log.info("Sort_Display_06: Step 02: Click To 'Display per page' dropdown");
		productPage.selectToDropdownByName(driver, "products-pagesize", "6");

		log.info("Sort_Display_06: Step 03: Verify product number displayed is correct");
		verifyTrue(productPage.areJQueryAndJSLoadedSuccess(driver));
		verifyTrue(productPage.isProductNumberDisplayAsExpected(driver, 6));

		log.info("Sort_Display_06: Step 04: Verify pagination is undispalyed");
		verifyTrue(productPage.isPaginationUnDisplayed(driver));
	}

	@Test
	public void Sort_Display_07_Display_9_Product_Per_Page() {
		log.info("Sort_Display_07 - Step 01: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		productPage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");

		log.info("Sort_Display_07: Step 02: Click To 'Display per page' dropdown");
		productPage.selectToDropdownByName(driver, "products-pagesize", "9");

		log.info("Sort_Display_07: Step 03: Verify product number displayed is correct");
		productPage.sleepInSecond(2);
		verifyTrue(productPage.isProductNumberDisplayAsExpected(driver, 9));

		log.info("Sort_Display_07: Step 04: Verify pagination is undispalyed");
		verifyTrue(productPage.isPaginationUnDisplayed(driver));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
