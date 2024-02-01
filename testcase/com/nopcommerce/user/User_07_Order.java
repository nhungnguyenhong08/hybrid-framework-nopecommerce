package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookies;

import commons.BaseTest;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import pageObjects.nopCommerce.user.UserShoppingCartPageObject;

public class User_07_Order extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserProductPageObject productPage;
	private UserLoginPageObject loginPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private String firstDesktopsProduct, secondDesktopsProduct, priceProduct;

	@Parameters({ "envName", "serverName", "browserName", "osName", "osVersion", "ipAddress", "portNumber" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("11") String osVersion,
			@Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, osName, osVersion, ipAddress, portNumber);
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		log.info("Pre-condition - Step 02: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();

		log.info("Pre-condition - Step 03: Set cookie and reload page");
		loginPage.setCookies(driver, Common_01_Register_Cookies.loggedCookies);
		loginPage.refreshCurrentPage(driver);

		log.info("Pre-Condition - Step 04: Verify Home page Displayed");
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

		firstDesktopsProduct = "Build your own computer";
		secondDesktopsProduct = "Lenovo IdeaCentre 600 All-in-One PC";

	}

	@Test
	public void Order_01_Add_Product_To_Cart() {
		log.info("Order_01: Step 01: Click To Product Catelogies on Top Menu: Computers/Desktops");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Desktops");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Order_01: Step 02: Click to product name with value is '" + firstDesktopsProduct + "'");
		productPage.clickProductToSeeDetailByText(driver, firstDesktopsProduct);

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
		verifyEquals(productPage.getDescriptionInMiniShoppingCartByAttribute(driver, "name"), firstDesktopsProduct);
		verifyEquals(productPage.getInforProductByProductName(driver, firstDesktopsProduct), "Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]\n" + "RAM: 8GB [+$60.00]\n" + "HDD: 400 GB [+$100.00]\n"
				+ "OS: Vista Premium [+$60.00]\n" + "Software: Microsoft Office [+$50.00]\n" + "Software: Acrobat Reader [+$10.00]\n" + "Software: Total Commander [+$5.00]");
		verifyEquals(productPage.getInforProductByProductNameAndAttributeValue(driver, firstDesktopsProduct, "quantity"), "Quantity: 1");
		verifyEquals(productPage.getInforProductByProductNameAndAttributeValue(driver, firstDesktopsProduct, "price"), "Unit price: " + priceProduct);
		verifyEquals(productPage.getSubTotalPriceByAttributeValue(driver, "totals"), "Sub-Total: " + priceProduct);
	}

	@Test
	public void Order_02_Edit_Product_In_Shopping_Cart() {
		log.info("Order_02: Step 01: Open Shopping cart at header");
		productPage.OpenToHeaderLinkByText(driver, "Shopping cart");

		log.info("Order_02: Step 02: Verify Shopping cart page is displayed");
		shoppingCartPage = PageGeneratorManagerNopCommerce.getUserShoppingCartPage(driver);
		verifyTrue(shoppingCartPage.isRequestedPageDisplayed(driver, "Shopping cart"));

		log.info("Order_02: Step 03: Click to 'Edit' link");
		productPage = shoppingCartPage.clickToEditProduct(driver);

		log.info("Order_02: Step 04: Select item in 'Processor' dropdown is '2.2 GHz Intel Pentium Dual-Core E2200'");
		productPage.selectItemInDefaultDropdownByName(driver, "product_attribute_1", "2.2 GHz Intel Pentium Dual-Core E2200");

		log.info("Order_02: Step 05: Select item in 'RAM' dropdown is '4GB [+$20.00]'");
		productPage.selectItemInDefaultDropdownByName(driver, "product_attribute_2", "4GB [+$20.00]");

		log.info("Order_02: Step 06: Select item in 'HDD' radio button is '320 GB'");
		productPage.checkToRadioButtonByLabelText(driver, "320 GB");

		log.info("Order_02: Step 07: Select item in 'OS' radio button is 'Vista Home [+$50.00]'");
		productPage.checkToRadioButtonByLabelText(driver, "Vista Home [+$50.00]");

		log.info("Order_02: Step 08: Select item all value at 'Software' checkbox");
		productPage.checkToRadioButtonByLabelText(driver, "Microsoft Office [+$50.00]");
		productPage.uncheckToDefaultCheckboxOrRadioButtonByLabelText(driver, "Acrobat Reader [+$10.00]");
		productPage.uncheckToDefaultCheckboxOrRadioButtonByLabelText(driver, "Total Commander [+$5.00]");

		log.info("Order_02: Step 09: Verify product price is updated with value '$1,320.00'");
		productPage.sleepInSecond(3);
		verifyEquals(productPage.getPriceOfProduct(driver), "$1,320.00");

		log.info("Order_02: Step 10: Get value of price is " + priceProduct);
		priceProduct = productPage.getPriceOfProduct(driver);

		log.info("Order_02: Step 11: Edit product quantiny is 2");
		productPage.enterToProductQuantityTextbox(driver, "2");

		log.info("Order_02: Step 12: Click to 'Update' button");
		productPage.clickToButtonByText(driver, "Update");

		log.info("Order_02: Step 13: Verify add to cart success message displayed");
		verifyEquals(productPage.getSuccessMessageAtBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_02: Step 14: Close success message");
		productPage.closeBarNotification(driver);

		log.info("Order_02: Step 15: Verify Shopping cart quantity is updated (2)");
		verifyTrue(productPage.areJQueryAndJSLoadedSuccess(driver));
		verifyEquals(productPage.getQuantityAtHeaderPage(driver, "Shopping cart"), "(2)");

		log.info("Order_02: Step 16: Verify the product is displayed in mini shopping cart");
		productPage.hoverToHeaderLinkByText(driver, "Shopping cart");
		verifyEquals(productPage.getDescriptionInMiniShoppingCartByAttribute(driver, "count"), "2 item(s)");
		verifyEquals(productPage.getDescriptionInMiniShoppingCartByAttribute(driver, "name"), firstDesktopsProduct);
		verifyEquals(productPage.getInforProductByProductName(driver, firstDesktopsProduct),
				"Processor: 2.2 GHz Intel Pentium Dual-Core E2200\n" + "RAM: 4GB [+$20.00]\n" + "HDD: 320 GB\n" + "OS: Vista Home [+$50.00]\n" + "Software: Microsoft Office [+$50.00]");
		verifyEquals(productPage.getInforProductByProductNameAndAttributeValue(driver, firstDesktopsProduct, "quantity"), "Quantity: 2");
		verifyEquals(productPage.getInforProductByProductNameAndAttributeValue(driver, firstDesktopsProduct, "price"), "Unit price: " + priceProduct);
		verifyEquals(productPage.getSubTotalPriceByAttributeValue(driver, "totals"), "Sub-Total: " + "$2,640.00");

	}

	@Test
	public void Order_03_Remove_Product_From_Shopping_Cart() {
		log.info("Order_03: Step 01: Open Shopping cart at header");
		productPage.OpenToHeaderLinkByText(driver, "Shopping cart");

		log.info("Order_03: Step 02: Verify Shopping cart page is displayed");
		shoppingCartPage = PageGeneratorManagerNopCommerce.getUserShoppingCartPage(driver);
		verifyTrue(shoppingCartPage.isRequestedPageDisplayed(driver, "Shopping cart"));

		log.info("Order_03: Step 03: Click to 'Remove' button");
		shoppingCartPage.clickToRemoveButtonByProductName(driver, firstDesktopsProduct);

		log.info("Order_03: Step 04: Verify message is 'Your Shopping Cart is empty!' displayed");
		verifyEquals(shoppingCartPage.getMessageDisplayed(driver), "Your Shopping Cart is empty!");

		log.info("Order_03: Step 05: Verify product is not exist in Shopping cart");
		verifyTrue(shoppingCartPage.isProductUndisplayedInShoppingCart(driver, firstDesktopsProduct));

	}

	@Test
	public void Order_04_Update_Product_In_Shopping_Cart() {
		log.info("Order_04: Step 01: Click To Product Catelogies on Top Menu: Computers/Desktops");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Desktops");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Order_04: Step 02: Click to 'Add to cart' button at " + secondDesktopsProduct);
		productPage.clickToButtonByProductNameandText(driver, secondDesktopsProduct, "Add to cart");

		log.info("Order_04: Step 03: Verify add to cart success message displayed");
		verifyEquals(productPage.getSuccessMessageAtBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_04: Step 04: Close success message");
		productPage.closeBarNotification(driver);

		log.info("Order_04: Step 05: Verify Shopping cart quantity is updated (1)");
		verifyTrue(productPage.areJQueryAndJSLoadedSuccess(driver));
		verifyEquals(productPage.getQuantityAtHeaderPage(driver, "Shopping cart"), "(1)");

		log.info("Order_04: Step 06: Open Shopping cart at header");
		productPage.OpenToHeaderLinkByText(driver, "Shopping cart");

		log.info("Order_04: Step 07: Verify Shopping cart page is displayed");
		shoppingCartPage = PageGeneratorManagerNopCommerce.getUserShoppingCartPage(driver);
		verifyTrue(shoppingCartPage.isRequestedPageDisplayed(driver, "Shopping cart"));

		log.info("Order_04: Step 08: Update product quantity is '5'");
		shoppingCartPage.inputToQuantityTextboxByProductName(driver, secondDesktopsProduct, "5");

		log.info("Order_04: Step 09: Click to 'Update shopping cart' button");
		shoppingCartPage.clickToButtonByText(driver, "Update shopping cart");

		log.info("Order_04: Step 10: Verify product price is updated with value '$2,500.00'");
		verifyEquals(shoppingCartPage.getProductPriceByProductName(driver, secondDesktopsProduct), "$2,500.00");

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
