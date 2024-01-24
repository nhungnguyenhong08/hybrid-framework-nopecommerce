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

public class User_07_Order extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserProductPageObject productPage;
	private UserLoginPageObject loginPage;
	private String desktopsProduct, priceProduct;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		desktopsProduct = "Build your own computer";

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
	public void Order_01_Add_Product_To_Cart() {
		log.info("Order_01: Step 01: Click To Product Catelogies on Top Menu: Computers/Desktops");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Desktops");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Order_01: Step 02: Click to product name with value is '" + desktopsProduct + "'");
		productPage.clickProductToSeeDetailByText(driver, desktopsProduct);

		log.info("Order_01: Step 03: Select item in 'Processor' dropdown is '2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]'");
		productPage.selectItemInDefaultDropdownByName(driver, "product_attribute_1", "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]");

		log.info("Order_01: Step 04: Select item in 'RAM' dropdown is '8GB [+$60.00]'");
		productPage.selectItemInDefaultDropdownByName(driver, "product_attribute_2", "8GB [+$60.00]");

		log.info("Order_01: Step 05: Select item in 'HDD' radio button is '400 GB [+$100.00]'");
		productPage.checkToRadioButtonByLabelText(driver, "400 GB [+$100.00]");

		log.info("Order_01: Step 06: Select item in 'OS' radio button is 'Vista Premium [+$60.00]'");
		productPage.checkToRadioButtonByLabelText(driver, "Vista Premium [+$60.00]");

		log.info("Order_01: Step 07: Select item all value at 'Software' checkbox");
		productPage.checkToRadioButtonByLabelText(driver, "Microsoft Office [+$50.00]");
		productPage.checkToRadioButtonByLabelText(driver, "Acrobat Reader [+$10.00]");
		productPage.checkToRadioButtonByLabelText(driver, "Total Commander [+$5.00]");

		log.info("Order_01: Step 08: Get value of price is " + priceProduct);
		productPage.sleepInSecond(3);
		priceProduct = productPage.getPriceOfProduct(driver);

		log.info("Order_01: Step 09: Click to 'Add to cart' button");
		productPage.clickToButtonByText(driver, "Add to cart");

		log.info("Order_01: Step 10: Verify add to cart success message displayed");
		verifyEquals(productPage.getSuccessMessageAtBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_01: Step 11: Close success message");
		productPage.closeBarNotification(driver);

		log.info("Order_01: Step 12: Verify Shopping cart quantity is updated (1)");
		verifyTrue(productPage.areJQueryAndJSLoadedSuccess(driver));
		verifyEquals(productPage.getQuantityAtHeaderPage(driver, "Shopping cart"), "(1)");

		log.info("Order_01: Step 13: Verify the product is displayed in mini shopping cart");
		productPage.hoverToHeaderLinkByText(driver, "Shopping cart");
		verifyEquals(productPage.getDescriptionInMiniShoppingCartByAttribute(driver, "count"), "1 item(s)");
		verifyEquals(productPage.getDescriptionInMiniShoppingCartByAttribute(driver, "name"), "Build your own computer");
		verifyEquals(productPage.getInforProductByProductName(driver, "Build your own computer"), "Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]\n" + "RAM: 8GB [+$60.00]\n" + "HDD: 400 GB [+$100.00]\n"
				+ "OS: Vista Premium [+$60.00]\n" + "Software: Microsoft Office [+$50.00]\n" + "Software: Acrobat Reader [+$10.00]\n" + "Software: Total Commander [+$5.00]");
		verifyEquals(productPage.getInforProductByProductNameAndAttributeValue(driver, "Build your own computer", "quantity"), "Quantity: 1");
		verifyEquals(productPage.getInforProductByProductNameAndAttributeValue(driver, "Build your own computer", "price"), "Unit price: " + priceProduct);
		verifyEquals(productPage.getSubTotalPriceByAttributeValue(driver, "totals"), "Sub-Total: " + priceProduct);
	}

	@Test
	public void Wishlist_Compare_View_02_Add_Product_To_Cart() {

	}

	@Test
	public void Wishlist_Compare_View_03_Remove_Product_In_Wishlist() {

	}

	@Test
	public void Wishlist_Compare_View_04_Add_Product_To_Compare() {

	}

	@Test
	public void Wishlist_Compare_View_05_View_Recent_Products() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
