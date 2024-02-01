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
import pageObjects.nopCommerce.user.UserCompareProductPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import pageObjects.nopCommerce.user.UserRecentlyViewedProductPageObject;
import pageObjects.nopCommerce.user.UserShoppingCartPageObject;
import pageObjects.nopCommerce.user.UserWishlistPageObject;

public class User_06_Wishlist_Compare_Recently_Viewed_Product extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserProductPageObject productPage;
	private UserLoginPageObject loginPage;
	private UserWishlistPageObject wishListPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserCompareProductPageObject compareProductPage;
	private UserRecentlyViewedProductPageObject recentlyViewedProduct;
	private String noteBooksProduct, desktopsProduct, viewedProductName03, viewedProductName04, viewedProductName05;

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

		noteBooksProduct = "Apple MacBook Pro 13-inch";
		desktopsProduct = "Lenovo IdeaCentre 600 All-in-One PC";
	}

	@Test
	public void Wishlist_Compare_View_01_Add_Product_To_Wishlist() {
		log.info("Wishlist_Compare_View_01_ - Step 01: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Wishlist_Compare_View_01_: Step 02: Click to product name with value is '" + noteBooksProduct + "'");
		productPage.clickProductToSeeDetailByText(driver, noteBooksProduct);

		log.info("Wishlist_Compare_View_01_: Step 03: Click To 'Add to wishlist' button");
		productPage.clickToOverviewButtonByText(driver, "Add to wishlist");

		log.info("Wishlist_Compare_View_01_: Step 04: Verify add to wishlist success message displayed");
		verifyEquals(productPage.getSuccessMessageAtBarNotification(driver), "The product has been added to your wishlist");

		log.info("Wishlist_Compare_View_01_: Step 05: Close success message");
		productPage.closeBarNotification(driver);

		log.info("Wishlist_Compare_View_01_: Step 06: Navigate to 'Wishlist' page");
		productPage.openWishlistPageByLink(driver);
		wishListPage = PageGeneratorManagerNopCommerce.getUserWishlistPage(driver);

		log.info("Wishlist_Compare_View_01_: Step 07: Verify Wishlist page Displayed");
		verifyTrue(wishListPage.areJQueryAndJSLoadedSuccess(driver));
		verifyTrue(wishListPage.isRequestedPageDisplayed(driver, "Wishlist"));

		log.info("Wishlist_Compare_View_01_: Step 08: Click to link 'Your wishlist URL for sharing'");
		wishListPage.clickWishlistShareLink();

		log.info("Wishlist_Compare_View_01_: Step 09: Verify Wishlist share page is displayed");
		verifyTrue(wishListPage.areJQueryAndJSLoadedSuccess(driver));
		// verifyEquals(wishListPage.getPageTitle(), "Wishlist of " + Common_01_Register_Cookies.firstName + " " + Common_01_Register_Cookies.lastName";
		verifyTrue(wishListPage.isRequestedPageDisplayed(driver, "Wishlist of"));

	}

	@Test
	public void Wishlist_Compare_View_02_Add_Product_To_Cart() {

		log.info("Wishlist_Compare_View_02: Step 01: Click To HomePage icon");
		wishListPage.openHomePage();
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		log.info("Wishlist_Compare_View_02: Step 02: Navigate to 'Wishlist' page");
		productPage.openWishlistPageByLink(driver);

		log.info("Wishlist_Compare_View_02: Step 03: Click to 'Add to cart' checkbox");
		wishListPage.clickToCheckboxByLabel(driver, "Add to cart");

		log.info("Wishlist_Compare_View_02: Step 04: Click to 'Add to cart' button");
		wishListPage.clickToButtonByText(driver, "Add to cart");

		log.info("Wishlist_Compare_View_02: Step 05: Verify Shopping cart page is displayed");
		shoppingCartPage = PageGeneratorManagerNopCommerce.getUserShoppingCartPage(driver);
		verifyTrue(shoppingCartPage.isRequestedPageDisplayed(driver, "Shopping cart"));

		log.info("Wishlist_Compare_View_02: Step 06: Verify Wishlist quantity is updated (0)");
		verifyEquals(shoppingCartPage.getQuantityAtHeaderPage(driver, "Wishlist"), "(0)");

		log.info("Wishlist_Compare_View_02: Step 07: Verify Shopping cart quantity is updated (2)");
		verifyTrue(shoppingCartPage.areJQueryAndJSLoadedSuccess(driver));
		verifyEquals(shoppingCartPage.getQuantityAtHeaderPage(driver, "Shopping cart"), "(2)");
	}

	@Test
	public void Wishlist_Compare_View_03_Remove_Product_In_Wishlist() {
		log.info("Wishlist_Compare_View_03: Step 01: Click To HomePage icon");
		shoppingCartPage.openHomePage();
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		log.info("Wishlist_Compare_View_03: Step 02: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Wishlist_Compare_View_03: Step 03: Click to product name with value is '" + noteBooksProduct + "'");
		productPage.clickProductToSeeDetailByText(driver, noteBooksProduct);

		log.info("Wishlist_Compare_View_03: Step 04: Click To 'Add to wishlist' button");
		productPage.clickToOverviewButtonByText(driver, "Add to wishlist");

		log.info("Wishlist_Compare_View_03: Step 05: Verify add to wishlist success message displayed");
		verifyEquals(productPage.getSuccessMessageAtBarNotification(driver), "The product has been added to your wishlist");

		log.info("Wishlist_Compare_View_03: Step 06: Close success message");
		productPage.closeBarNotification(driver);

		log.info("Wishlist_Compare_View_03: Step 07: Navigate to 'Wishlist' page");
		productPage.openWishlistPageByLink(driver);
		wishListPage = PageGeneratorManagerNopCommerce.getUserWishlistPage(driver);

		log.info("Wishlist_Compare_View_03: Step 08: Verify Wishlist page Displayed");
		// verifyTrue(wishListPage.areJQueryAndJSLoadedSuccess(driver));
		verifyTrue(wishListPage.isRequestedPageDisplayed(driver, "Wishlist"));

		log.info("Wishlist_Compare_View_03: Step 09: Verify Added Product is displayed");
		verifyEquals(wishListPage.getProductInWishlist(driver), noteBooksProduct);

		log.info("Wishlist_Compare_View_03: Step 10: Click to remove button of '" + noteBooksProduct + "'");
		wishListPage.clickToRemoveProductButton(driver, noteBooksProduct);

		log.info("Wishlist_Compare_View_03: Step 11: Verify message displayed");
		verifyEquals(wishListPage.getMessageDiplayed(), "The wishlist is empty!");

		log.info("Wishlist_Compare_View_03: Step 12: Verify Product List is empty");
		verifyTrue(wishListPage.isProductsListEmpty(driver));
	}

	@Test
	public void Wishlist_Compare_View_04_Add_Product_To_Compare() {
		log.info("Wishlist_Compare_View_04: Step 01: Click To HomePage icon");
		wishListPage.openHomePage();
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		log.info("Wishlist_Compare_View_04 - Step 02: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Wishlist_Compare_View_04: Step 03: Click to product name with value is '" + noteBooksProduct + "'");
		productPage.clickProductToSeeDetailByText(driver, noteBooksProduct);

		log.info("Wishlist_Compare_View_04: Step 04: Click to 'Add to compare list' button");
		productPage.clickToOverviewButtonByText(driver, "Add to compare list");

		log.info("Wishlist_Compare_View_04: Step 05: Verify add to product comparison success message displayed");
		verifyEquals(productPage.getSuccessMessageAtBarNotification(driver), "The product has been added to your product comparison");

		log.info("Wishlist_Compare_View_04: Step 06: Close success message");
		productPage.closeBarNotification(driver);

		log.info("Wishlist_Compare_View_04 - Step 07: Click To Product Catelogies on Top Menu: Computers/Desktops");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Desktops");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Wishlist_Compare_View_04: Step 08: Click to product name with value is '" + desktopsProduct + "'");
		productPage.clickProductToSeeDetailByText(driver, desktopsProduct);

		log.info("Wishlist_Compare_View_04: Step 09: Click to 'Add to compare list' button");
		productPage.clickToOverviewButtonByText(driver, "Add to compare list");

		log.info("Wishlist_Compare_View_04: Step 10: Verify add to product comparison success message displayed");
		verifyEquals(productPage.getSuccessMessageAtBarNotification(driver), "The product has been added to your product comparison");

		log.info("Wishlist_Compare_View_04: Step 11: Close success message");
		productPage.closeBarNotification(driver);

		log.info("Wishlist_Compare_View_04: Step 12: Open 'Compare products list' page");
		productPage.openFooterPageByText(driver, "Compare products list");
		compareProductPage = PageGeneratorManagerNopCommerce.getUserCompareProductPage(driver);

		log.info("Wishlist_Compare_View_04: Step 13: Verify information on compare list displayed");
		verifyTrue(compareProductPage.isProductNameBytextDisplayed(driver, noteBooksProduct));
		verifyTrue(compareProductPage.isRemoveButtonByProductNameDisplayed(driver, noteBooksProduct));
		verifyTrue(compareProductPage.isPriceTextByProductNameAtRowNameDisplayed(driver, noteBooksProduct));

		verifyTrue(compareProductPage.isProductNameBytextDisplayed(driver, desktopsProduct));
		verifyTrue(compareProductPage.isRemoveButtonByProductNameDisplayed(driver, desktopsProduct));
		verifyTrue(compareProductPage.isPriceTextByProductNameAtRowNameDisplayed(driver, desktopsProduct));

		log.info("Wishlist_Compare_View_04: Step 14: Click to 'Clear list' button");
		compareProductPage.clickToClearListButton();

		log.info("Wishlist_Compare_View_04: Step 15: Verify cleared list successful message displayed");
		verifyEquals(compareProductPage.getMesssageDisplayed(), "You have no items to compare.");

		log.info("Wishlist_Compare_View_04: Step 16: Verify information on compare list undisplayed");
		verifyTrue(compareProductPage.isProductNameBytextUnDisplayed(driver, noteBooksProduct));
		verifyTrue(compareProductPage.isProductNameBytextUnDisplayed(driver, desktopsProduct));
	}

	@Test
	public void Wishlist_Compare_View_05_View_Recent_Products() {
		log.info("Wishlist_Compare_View_05: Step 01: Click To HomePage icon");
		compareProductPage.openHomePage();
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		log.info("Wishlist_Compare_View_05: Step 02: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Wishlist_Compare_View_05: Step 03: Click to see information of first product");
		productPage.clickToProductToSeeDetailByOrder(driver, "1");

		log.info("Wishlist_Compare_View_05: Step 04: Click to see information of second product");
		productPage.backToPage(driver);
		productPage.clickToProductToSeeDetailByOrder(driver, "2");

		log.info("Wishlist_Compare_View_05: Step 05: Click to see information of third product");
		productPage.backToPage(driver);
		productPage.clickToProductToSeeDetailByOrder(driver, "3");
		viewedProductName03 = productPage.getValueOfViewedProduct(driver);

		log.info("Wishlist_Compare_View_05: Step 06: Click to see information of 4th product");
		productPage.backToPage(driver);
		productPage.clickToProductToSeeDetailByOrder(driver, "4");
		viewedProductName04 = productPage.getValueOfViewedProduct(driver);

		log.info("Wishlist_Compare_View_05: Step 07: Click to see information of 5rh product");
		productPage.backToPage(driver);
		productPage.clickToProductToSeeDetailByOrder(driver, "5");
		viewedProductName05 = productPage.getValueOfViewedProduct(driver);

		log.info("Wishlist_Compare_View_05: Step 08: Navigate to 'Recently viewed products' page at footer");
		productPage.openFooterPageByText(driver, "Recently viewed products");
		recentlyViewedProduct = PageGeneratorManagerNopCommerce.getUserRecentlyViewedProductPage(driver);

		log.info("Wishlist_Compare_View_05: Verify recently viewed products page displayed");
		verifyTrue(recentlyViewedProduct.isRequestedPageDisplayed(driver, "Recently viewed products"));

		log.info("Wishlist_Compare_View_05: Verify only 3 Product is displayed");
		verifyEquals(recentlyViewedProduct.countRecentlyViewedProductDisplayed(), 3);

		verifyTrue(recentlyViewedProduct.isProductNameAtRecentlyViewedBytextDisplayed(viewedProductName03));
		verifyTrue(recentlyViewedProduct.isProductNameAtRecentlyViewedBytextDisplayed(viewedProductName04));
		verifyTrue(recentlyViewedProduct.isProductNameAtRecentlyViewedBytextDisplayed(viewedProductName05));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
