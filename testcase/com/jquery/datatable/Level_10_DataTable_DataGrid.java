package com.jquery.datatable;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

public class Level_10_DataTable_DataGrid extends BaseTest {
	private WebDriver driver;
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("10"));

		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("20"));

		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("7"));

		homePage.openPagingByPageNumber("18");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("18"));

	}

	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);

		homePage.enterToHeaderTextboxByLable("Females", "338282");
		homePage.enterToHeaderTextboxByLable("Country", "Argentina");
		homePage.enterToHeaderTextboxByLable("Males", "349238");
		homePage.enterToHeaderTextboxByLable("Total", "687522");
		homePage.sleepInSecond(3);

		homePage.enterToHeaderTextboxByLable("Females", "276880");
		homePage.enterToHeaderTextboxByLable("Country", "Angola");
		homePage.enterToHeaderTextboxByLable("Males", "276472");
		homePage.enterToHeaderTextboxByLable("Total", "553353");
		homePage.sleepInSecond(3);
	}

	@Test
	public void Table_03_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);

		// Đọc dữ liệu của file country.txt ra
		// Lưu vào 1 List<String> = Expected Value = expectedAllCountryValues

		// Actual Value
		actualAllCountryValues = homePage.getValueEachRowAtAllPage();

		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
