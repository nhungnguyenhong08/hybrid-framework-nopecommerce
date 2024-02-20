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
import pageObjects.nopCommerce.user.UserCheckOutPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserOrderPageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import pageObjects.nopCommerce.user.UserShoppingCartPageObject;

public class User_07_Order extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserProductPageObject productPage;
	private UserLoginPageObject loginPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserCheckOutPageObject checkOutPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserOrderPageObject orderPage;
	private String firstDesktopsProduct, secondDesktopsProduct, priceProduct, noteBooksProduct;
	private String firstName, lastName, orderNumber, email, companyName, country, city, city_1, address_1, address_2, address_3, address_4, zipPostalCode, zipPostalCode_1, phoneNumber, faxNumber;
	private String creditCardTypes, cardHolderName, cardNumber, expireMonth, expireYear, cardCode;

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
		noteBooksProduct = "Apple MacBook Pro 13-inch";
		firstName = "Automation";
		lastName = "FC";
		email = "automationfc1.vn@gmail.com";
		companyName = "Automation FC";
		country = "Viet Nam";
		city = "Da Nang";
		city_1 = "Ho Chi Minh";
		address_1 = "123/04 Le Lai";
		address_2 = "234/05 Hai Phong";
		address_3 = "12/35 Le Loi";
		address_4 = "123/09 Ho Chi Minh";
		zipPostalCode = "550000";
		zipPostalCode_1 = "700000";
		phoneNumber = "0123456789";
		faxNumber = "0987654321";
		creditCardTypes = "Visa";
		cardHolderName = "AUTOMATION FC";
		cardNumber = "374245455400126";
		expireMonth = "05";
		expireYear = "2025";
		cardCode = "123";
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
		productPage.sleepInSecond(3);
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
		productPage.sleepInSecond(10);
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
	public void Order_05_Checkout_Order_With_Payment_Method_By_Money() {

		log.info("Order_05: Step 01: Click to 'Remove' product 'secondDesktopsProduct'");
		shoppingCartPage.clickToRemoveButtonByProductName(driver, secondDesktopsProduct);

		log.info("Order_05: Step 02: Verify message is 'Your Shopping Cart is empty!' displayed");
		verifyEquals(shoppingCartPage.getMessageDisplayed(driver), "Your Shopping Cart is empty!");

		log.info("Order_05: Step 03: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		shoppingCartPage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Order_05: Step 04: Click to product name with value is '" + noteBooksProduct + "'");
		productPage.clickProductToSeeDetailByText(driver, noteBooksProduct);

		log.info("Order_05: Step 05: Click to 'Add To Cart' button at product detail page");
		productPage.clickToAddToCartButtonAtProductDetailPage(driver);

		log.info("Order_05: Step 06: Verify add to cart success message displayed");
		verifyEquals(productPage.getSuccessMessageAtBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_05: Step 07: Close success message");
		productPage.closeBarNotification(driver);

		log.info("Order_05: Step 08: Open Shopping cart at header");
		productPage.OpenToHeaderLinkByText(driver, "Shopping cart");
		shoppingCartPage = PageGeneratorManagerNopCommerce.getUserShoppingCartPage(driver);

		log.info("Order_05: Step 09: Select item in 'Gift wrapping' dropdown is 'No'");
		shoppingCartPage.selectItemInGiftWrappingDropdownBytext(driver, "No");

		log.info("Order_05: Step 10: Check to 'Terms of Service' checkbox");
		shoppingCartPage.checkToTermsOfServiceCheckbox(driver);

		log.info("Order_05: Step 11: Click to 'Checkout' button");
		shoppingCartPage.clickToButtonByText(driver, "Checkout");

		log.info("Order_05: Step 12: Uncheck to 'ShipToSameAddress' checkbox");
		checkOutPage = PageGeneratorManagerNopCommerce.getUserCheckOutProductPage(driver);
		checkOutPage.unCheckToShipToSameAddressCheckbox(driver);

		log.info("Order_05: Step 13: Input to 'Billing address' form");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FirstName", firstName);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_LastName", lastName);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Email", email);
		checkOutPage.selectCountryById(driver, "BillingNewAddress_CountryId", country);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_City", city);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address1", address_1);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address2", address_2);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", zipPostalCode);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phoneNumber);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FaxNumber", faxNumber);

		log.info("Order_05: Step 14: Click to 'Continue' button at billing");
		checkOutPage.clickContinueButtonByID(driver, "billing-buttons-container");

		log.info("Order_05: Step 15: Select 'Shipping address' method");
		checkOutPage.selectAShippingAddressMethod(driver, "New Address");

		log.info("Order_05: Step 16: Input to 'Shipping address' form");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FirstName", firstName);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_LastName", lastName);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Email", email);
		checkOutPage.selectCountryById(driver, "ShippingNewAddress_CountryId", country);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_City", city);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address1", address_1);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address2", address_2);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_ZipPostalCode", zipPostalCode);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_PhoneNumber", phoneNumber);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FaxNumber", faxNumber);

		log.info("Order_05: Step 17: Click to 'Continue' button at Shipping address");
		checkOutPage.clickContinueButtonByID(driver, "shipping-buttons-container");

		log.info("Order_05: Step 18: Check to Radio button in 'Shipping method' with label is 'Ground ($0.00)'");
		checkOutPage.checkToRadioButtonByLabelText(driver, "Ground ($0.00)");

		log.info("Order_05: Step 19: Click to 'Continue' button at Shipping address");
		checkOutPage.clickContinueButtonByID(driver, "shipping-method-buttons-container");

		log.info("Order_05: Step 20: Check to Radio button in 'Payment method' with label is 'Check / MoneyOrder'");
		checkOutPage.checkToRadioButtonByLabelText(driver, "Check / Money Order");

		log.info("Order_05: Step 21: Click to 'Continue' button at Payment method");
		checkOutPage.clickContinueButtonByID(driver, "payment-method-buttons-container");

		log.info("Order_05: Step 22: Verify Address shipping information");
		verifyEquals(checkOutPage.getAddressShippingInfor(driver), "NOP SOLUTIONS\n" + "your address here,\n" + "New York, NY 10001\n" + "USA");

		log.info("Order_05: Step 23: Click to 'Continue' button at Payment information");
		checkOutPage.clickContinueButtonByID(driver, "payment-info-buttons-container");

		log.info("Order_05: Step 24: Verify 'Billing Address' order infor");
		verifyTrue(checkOutPage.getNameInforAtEachSection(driver, "billing-info").contains(firstName + " " + lastName));
		verifyTrue(checkOutPage.getEmailInforAtEachSection(driver, "billing-info").contains(email));
		verifyTrue(checkOutPage.getPhoneInforAtEachSection(driver, "billing-info").contains(phoneNumber));
		verifyTrue(checkOutPage.getFaxInforAtEachSection(driver, "billing-info").contains(faxNumber));
		verifyTrue(checkOutPage.getAddress1InforAtEachSection(driver, "billing-info").contains(address_1));
		verifyTrue(checkOutPage.getAddress2InforAtEachSection(driver, "billing-info").contains(address_2));
		verifyTrue(checkOutPage.getCountryInforAtEachSection(driver, "billing-info").contains(country));

		log.info("Order_05: Step 25: Verify 'Shipping Address' order infor");
		verifyTrue(checkOutPage.getNameInforAtEachSection(driver, "shipping-info").contains(firstName + " " + lastName));
		verifyTrue(checkOutPage.getEmailInforAtEachSection(driver, "shipping-info").contains(email));
		verifyTrue(checkOutPage.getPhoneInforAtEachSection(driver, "shipping-info").contains(phoneNumber));
		verifyTrue(checkOutPage.getFaxInforAtEachSection(driver, "shipping-info").contains(faxNumber));
		verifyTrue(checkOutPage.getAddress1InforAtEachSection(driver, "shipping-info").contains(address_1));
		verifyTrue(checkOutPage.getAddress2InforAtEachSection(driver, "shipping-info").contains(address_2));
		verifyTrue(checkOutPage.getCountryInforAtEachSection(driver, "shipping-info").contains(country));

		log.info("Order_05: Step 26: Verify 'Payment Method' oder infor");
		verifyTrue(checkOutPage.getPaymentMethod(driver).contains("Payment Method: Check / Money Order"));

		log.info("Order_05: Step 27: Verify 'Shipping Method' oder infor");
		verifyTrue(checkOutPage.getShippingMethod(driver).contains("Shipping Method: Ground"));

		log.info("Order_05: Step 27: Verify The product order infor");
		verifyTrue(checkOutPage.isProductNameBytextDisplayed(driver, noteBooksProduct));
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "SKU"), "AP_MBP_13");
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Price"), "$1,800.00");
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Qty"), "2");
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Total"), "$3,600.00");

		log.info("Order_05:  Step 28: Verify The sumary price order list");
		verifyEquals(checkOutPage.getPriceInforByTextLabel(driver, "Sub-Total"), "$3,600.00");
		verifyEquals(checkOutPage.getPriceInforByTextLabel(driver, "Shipping"), "$0.00");
		verifyEquals(checkOutPage.getPriceInforByTextLabel(driver, "Tax"), "$0.00");
		verifyEquals(checkOutPage.getTotalPriceInfor(driver), "$3,600.00");

		log.info("Order_05: Step 29: Click to 'Confirm' button");
		checkOutPage.clickToConfirmButton(driver);

		log.info("Order_05: Step 30: Verify Product order completed infor");
		checkOutPage.sleepInSecond(2);
		verifyEquals(checkOutPage.getOrderedSuccessPageTittle(driver), "Thank you");
		verifyEquals(checkOutPage.getOrderedSuccessMessage(driver), "Your order has been successfully processed!");
		orderNumber = checkOutPage.getOrderNumber(driver);
		verifyEquals(checkOutPage.getOrderNumberMessage(driver), "ORDER NUMBER: " + orderNumber);

		log.info("Order_05: Step 31: Click to Oder Completed 'Continue' button");
		homePage = checkOutPage.clickToOderCompletedContinueButton();

		log.info("Order_05: Step 32: Click to 'My Account' link");
		homePage.openHeaderUpperPageByText(driver, "My account");
		customerInforPage = PageGeneratorManagerNopCommerce.getUserCustomerInforPage(driver);

		log.info("Order_05: Step 33: Navigate to 'Orders' area at My Account page");
		customerInforPage.openPageAtMyAccountByName(driver, "Orders");
		orderPage = PageGeneratorManagerNopCommerce.getUserOrderPage(driver);

		log.info("Order_05: Step 34: Verify text 'Order Number: " + orderNumber + "' displayed");
		Assert.assertTrue(orderPage.isOrderNumberBytextDisplayed(driver, "Order Number: " + orderNumber));

		log.info("Order_05: Step 35: Click to 'Details' button");
		orderPage.clickToDetailsButtonByOrderNumber(driver, orderNumber);

		log.info("Order_05: Step 36: Verify 'order-overview' infor");
		verifyEquals(orderPage.getOrderNumberText(driver), "ORDER #" + orderNumber);
		verifyEquals(orderPage.getOrderDate(driver), "Order Date: " + getCurrentDayFormatted());
		verifyEquals(orderPage.getOrderStatus(driver), "Order Status: Pending");
		verifyEquals(orderPage.getOrderTotalPrice(driver), "Order Total: $3,600.00");

		log.info("Order_05: Step 37: Verify 'Billing Address' order infor");
		verifyTrue(orderPage.getNameInforAtEachSection(driver, "billing-info").contains(firstName + " " + lastName));
		verifyTrue(orderPage.getEmailInforAtEachSection(driver, "billing-info").contains(email));
		verifyTrue(orderPage.getPhoneInforAtEachSection(driver, "billing-info").contains(phoneNumber));
		verifyTrue(orderPage.getFaxInforAtEachSection(driver, "billing-info").contains(faxNumber));
		verifyTrue(orderPage.getAddress1InforAtEachSection(driver, "billing-info").contains(address_1));
		verifyTrue(orderPage.getAddress2InforAtEachSection(driver, "billing-info").contains(address_2));
		verifyTrue(orderPage.getCountryInforAtEachSection(driver, "billing-info").contains(country));

		log.info("Order_05: Step 38: Verify 'Shipping Address' order infor");
		verifyTrue(orderPage.getNameInforAtEachSection(driver, "shipping-info").contains(firstName + " " + lastName));
		verifyTrue(orderPage.getEmailInforAtEachSection(driver, "shipping-info").contains(email));
		verifyTrue(orderPage.getPhoneInforAtEachSection(driver, "shipping-info").contains(phoneNumber));
		verifyTrue(orderPage.getFaxInforAtEachSection(driver, "shipping-info").contains(faxNumber));
		verifyTrue(orderPage.getAddress1InforAtEachSection(driver, "shipping-info").contains(address_1));
		verifyTrue(orderPage.getAddress2InforAtEachSection(driver, "shipping-info").contains(address_2));
		verifyTrue(orderPage.getCountryInforAtEachSection(driver, "shipping-info").contains(country));

		log.info("Order_05: Step 39: Verify 'Payment Method' oder infor");
		verifyTrue(orderPage.getPaymentMethod(driver).contains("Payment Method: Check / Money Order"));
		verifyTrue(orderPage.getPaymentStatus(driver).contains("Payment Status: Pending"));

		log.info("Order_05: Step 40: Verify 'Shipping Method' oder infor");
		verifyTrue(orderPage.getShippingMethod(driver).contains("Shipping Method: Ground"));
		verifyTrue(orderPage.getShippingStatus(driver).contains("Shipping Status: Not yet shipped"));

		log.info("Order_05: Step 41: Verify The product order infor");
		verifyTrue(orderPage.isProductNameBytextDisplayed(driver, noteBooksProduct));
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "SKU"), "AP_MBP_13");
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Price"), "$1,800.00");
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Quantity"), "2");
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Total"), "$3,600.00");

		log.info("Order_05: Step 42: Verify The sumary price order list");
		verifyEquals(orderPage.getPriceInforByTextLabel(driver, "Sub-Total"), "$3,600.00");
		verifyEquals(orderPage.getPriceInforByTextLabel(driver, "Shipping"), "$0.00");
		verifyEquals(orderPage.getPriceInforByTextLabel(driver, "Tax"), "$0.00");
		verifyEquals(orderPage.getTotalPriceInfor(), "$3,600.00");
	}

	@Test
	public void Order_06_Checkout_Order_With_Payment_Method_By_Card() {

		log.info("Order_06: Step 01: Navigate to Homepage");
		homePage = orderPage.clickToHomeLink();

		log.info("Order_06: Step 02: Click To Product Catelogies on Top Menu: Computers/Notebooks");
		homePage.clickToSublistAtTopMenuByText(driver, "Computers", "Notebooks");
		productPage = PageGeneratorManagerNopCommerce.getUserProductPage(driver);

		log.info("Order_06: Step 03: Click to product name with value is '" + noteBooksProduct + "'");
		productPage.clickProductToSeeDetailByText(driver, noteBooksProduct);

		log.info("Order_06: Step 04: Click to 'Add To Cart' button at product detail page");
		productPage.clickToAddToCartButtonAtProductDetailPage(driver);

		log.info("Order_06: Step 05: Verify add to cart success message displayed");
		verifyEquals(productPage.getSuccessMessageAtBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_06: Step 06: Close success message");
		productPage.closeBarNotification(driver);

		log.info("Order_06: Step 07: Open Shopping cart at header");
		productPage.OpenToHeaderLinkByText(driver, "Shopping cart");
		shoppingCartPage = PageGeneratorManagerNopCommerce.getUserShoppingCartPage(driver);

		log.info("Order_06: Step 08: Select item in 'Gift wrapping' dropdown is 'No'");
		shoppingCartPage.selectItemInGiftWrappingDropdownBytext(driver, "No");

		log.info("Order_06: Step 09: Check to 'Terms of Service' checkbox");
		shoppingCartPage.checkToTermsOfServiceCheckbox(driver);

		log.info("Order_06: Step 10: Click to 'Checkout' button");
		shoppingCartPage.clickToButtonByText(driver, "Checkout");

		log.info("Order_06: Step 11: Uncheck to 'ShipToSameAddress' checkbox");
		checkOutPage = PageGeneratorManagerNopCommerce.getUserCheckOutProductPage(driver);
		checkOutPage.unCheckToShipToSameAddressCheckbox(driver);

		log.info("Order_06: Step 12: Click to 'Edit' button");
		checkOutPage.clickToEditButtonByID("billing-buttons-container");

		log.info("Order_06: Step 13: Input to 'Billing address' form");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FirstName", firstName);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_LastName", lastName);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Email", email);
		checkOutPage.selectCountryById(driver, "BillingNewAddress_CountryId", country);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_City", city);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address1", address_1);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address2", address_2);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", zipPostalCode);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phoneNumber);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FaxNumber", faxNumber);

		log.info("Order_06: Step 14: Click to 'Continue' button at billing");
		checkOutPage.clickContinueButtonByID(driver, "billing-buttons-container");

		log.info("Order_06: Step 15: Select 'Shipping address' method");
		checkOutPage.selectAShippingAddressMethod(driver, "New Address");

		log.info("Order_06: Step 16: Input to 'Shipping address' form");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FirstName", firstName);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_LastName", lastName);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Email", email);
		checkOutPage.selectCountryById(driver, "ShippingNewAddress_CountryId", country);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_City", city);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address1", address_1);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address2", address_2);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_ZipPostalCode", zipPostalCode);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_PhoneNumber", phoneNumber);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FaxNumber", faxNumber);

		log.info("Order_06: Step 17: Click to 'Continue' button at Shipping address");
		checkOutPage.clickContinueButtonByID(driver, "shipping-buttons-container");

		log.info("Order_06: Step 18: Check to Radio button in 'Shipping method' with label is 'Ground ($0.00)'");
		checkOutPage.checkToRadioButtonByLabelText(driver, "Ground ($0.00)");

		log.info("Order_06: Step 19: Click to 'Continue' button at Shipping address");
		checkOutPage.clickContinueButtonByID(driver, "shipping-method-buttons-container");

		log.info("Order_06: Step 20: Check to Radio button in 'Payment method' with label is 'Credit Card'");
		checkOutPage.checkToRadioButtonByLabelText(driver, "Credit Card");

		log.info("Order_06: Step 21: Click to 'Continue' button at Payment method");
		checkOutPage.clickContinueButtonByID(driver, "payment-method-buttons-container");

		log.info("Order_06: Step 21: Select credit card type");
		checkOutPage.selectToDropdownByName(driver, "CreditCardType", creditCardTypes);

		log.info("Order_06: Step 21: Select credit card type");
		checkOutPage.selectToDropdownByName(driver, "CreditCardType", creditCardTypes);

		log.info("Order_06: Step 22: Input into Cardholder name textbox with value is '" + cardHolderName + "'");
		checkOutPage.inputToTextboxByID(driver, "CardholderName", cardHolderName);

		log.info("Order_06: Step 23: Input into Card number textbox with value is '" + cardNumber + "'");
		checkOutPage.inputToTextboxByID(driver, "CardNumber", cardNumber);

		log.info("Order_06: Step 24: Input into Card code textbox with value is '" + cardCode + "'");
		checkOutPage.inputToTextboxByID(driver, "CardCode", cardCode);

		log.info("Order_06: Step 25: Select item in Expire Month dropdown with value is '" + expireMonth + "'");
		checkOutPage.selectToDropdownByName(driver, "ExpireMonth", expireMonth);

		log.info("Order_06: Step 26: Select item in Expire Year dropdown with value is '" + expireYear + "'");
		checkOutPage.selectToDropdownByName(driver, "ExpireYear", expireYear);

		log.info("Order_06: Step 27: Input into Card code textbox with value is '" + cardCode + "'");
		checkOutPage.inputToTextboxByID(driver, "CardCode", cardCode);

		log.info("Order_06: Step 28: Click to 'Continue' button at Payment information");
		checkOutPage.clickContinueButtonByID(driver, "payment-info-buttons-container");

		log.info("Order_06: Step 29: Verify 'Billing Address' order infor");
		verifyTrue(checkOutPage.getNameInforAtEachSection(driver, "billing-info").contains(firstName + " " + lastName));
		verifyTrue(checkOutPage.getEmailInforAtEachSection(driver, "billing-info").contains(email));
		verifyTrue(checkOutPage.getPhoneInforAtEachSection(driver, "billing-info").contains(phoneNumber));
		verifyTrue(checkOutPage.getFaxInforAtEachSection(driver, "billing-info").contains(faxNumber));
		verifyTrue(checkOutPage.getAddress1InforAtEachSection(driver, "billing-info").contains(address_1));
		verifyTrue(checkOutPage.getAddress2InforAtEachSection(driver, "billing-info").contains(address_2));
		verifyTrue(checkOutPage.getCountryInforAtEachSection(driver, "billing-info").contains(country));

		log.info("Order_06: Step 30: Verify 'Shipping Address' order infor");
		verifyTrue(checkOutPage.getNameInforAtEachSection(driver, "shipping-info").contains(firstName + " " + lastName));
		verifyTrue(checkOutPage.getEmailInforAtEachSection(driver, "shipping-info").contains(email));
		verifyTrue(checkOutPage.getPhoneInforAtEachSection(driver, "shipping-info").contains(phoneNumber));
		verifyTrue(checkOutPage.getFaxInforAtEachSection(driver, "shipping-info").contains(faxNumber));
		verifyTrue(checkOutPage.getAddress1InforAtEachSection(driver, "shipping-info").contains(address_1));
		verifyTrue(checkOutPage.getAddress2InforAtEachSection(driver, "shipping-info").contains(address_2));
		verifyTrue(checkOutPage.getCountryInforAtEachSection(driver, "shipping-info").contains(country));

		log.info("Order_06: Step 31: Verify 'Payment Method' oder infor");
		verifyTrue(checkOutPage.getPaymentMethod(driver).contains("Payment Method: Credit Card"));

		log.info("Order_06: Step 32: Verify 'Shipping Method' oder infor");
		verifyTrue(checkOutPage.getShippingMethod(driver).contains("Shipping Method: Ground"));

		log.info("Order_06: Step 33: Verify The product order infor");
		verifyTrue(checkOutPage.isProductNameBytextDisplayed(driver, noteBooksProduct));
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "SKU"), "AP_MBP_13");
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Price"), "$1,800.00");
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Qty"), "2");
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Total"), "$3,600.00");

		log.info("Order_06:  Step 34: Verify The sumary price order list");
		verifyEquals(checkOutPage.getPriceInforByTextLabel(driver, "Sub-Total"), "$3,600.00");
		verifyEquals(checkOutPage.getPriceInforByTextLabel(driver, "Shipping"), "$0.00");
		verifyEquals(checkOutPage.getPriceInforByTextLabel(driver, "Tax"), "$0.00");
		verifyEquals(checkOutPage.getTotalPriceInfor(driver), "$3,600.00");
		checkOutPage.sleepInSecond(8);

		log.info("Order_06: Step 35: Click to 'Confirm' button");
		checkOutPage.clickToConfirmButton(driver);

		log.info("Order_06: Step 36: Verify Product order completed infor");
		checkOutPage.sleepInSecond(3);
		verifyEquals(checkOutPage.getOrderedSuccessPageTittle(driver), "Thank you");
		verifyEquals(checkOutPage.getOrderedSuccessMessage(driver), "Your order has been successfully processed!");
		orderNumber = checkOutPage.getOrderNumber(driver);
		verifyEquals(checkOutPage.getOrderNumberMessage(driver), "ORDER NUMBER: " + orderNumber);

		log.info("Order_06: Step 37: Click to Oder Completed 'Continue' button");
		homePage = checkOutPage.clickToOderCompletedContinueButton();

		log.info("Order_06: Step 38: Click to 'My Account' link");
		homePage.openHeaderUpperPageByText(driver, "My account");
		customerInforPage = PageGeneratorManagerNopCommerce.getUserCustomerInforPage(driver);

		log.info("Order_06: Step 39: Navigate to 'Orders' area at My Account page");
		customerInforPage.openPageAtMyAccountByName(driver, "Orders");
		orderPage = PageGeneratorManagerNopCommerce.getUserOrderPage(driver);

		log.info("Order_06: Step 40: Verify text 'Order Number: " + orderNumber + "' displayed");
		verifyTrue(orderPage.isOrderNumberBytextDisplayed(driver, "Order Number: " + orderNumber));

		log.info("Order_06: Step 41: Click to 'Details' button");
		orderPage.clickToDetailsButtonByOrderNumber(driver, orderNumber);

		log.info("Order_06: Step 42: Verify 'order-overview' infor");
		verifyEquals(orderPage.getOrderNumberText(driver), "ORDER #" + orderNumber);
		verifyEquals(orderPage.getOrderDate(driver), "Order Date: " + getCurrentDayFormatted());
		verifyEquals(orderPage.getOrderStatus(driver), "Order Status: Pending");
		verifyEquals(orderPage.getOrderTotalPrice(driver), "Order Total: $3,600.00");

		log.info("Order_06: Step 43: Verify 'Billing Address' order infor");
		verifyTrue(orderPage.getNameInforAtEachSection(driver, "billing-info").contains(firstName + " " + lastName));
		verifyTrue(orderPage.getEmailInforAtEachSection(driver, "billing-info").contains(email));
		verifyTrue(orderPage.getPhoneInforAtEachSection(driver, "billing-info").contains(phoneNumber));
		verifyTrue(orderPage.getFaxInforAtEachSection(driver, "billing-info").contains(faxNumber));
		verifyTrue(orderPage.getAddress1InforAtEachSection(driver, "billing-info").contains(address_1));
		verifyTrue(orderPage.getAddress2InforAtEachSection(driver, "billing-info").contains(address_2));
		verifyTrue(orderPage.getCountryInforAtEachSection(driver, "billing-info").contains(country));

		log.info("Order_06: Step 44: Verify 'Shipping Address' order infor");
		verifyTrue(orderPage.getNameInforAtEachSection(driver, "shipping-info").contains(firstName + " " + lastName));
		verifyTrue(orderPage.getEmailInforAtEachSection(driver, "shipping-info").contains(email));
		verifyTrue(orderPage.getPhoneInforAtEachSection(driver, "shipping-info").contains(phoneNumber));
		verifyTrue(orderPage.getFaxInforAtEachSection(driver, "shipping-info").contains(faxNumber));
		verifyTrue(orderPage.getAddress1InforAtEachSection(driver, "shipping-info").contains(address_1));
		verifyTrue(orderPage.getAddress2InforAtEachSection(driver, "shipping-info").contains(address_2));
		verifyTrue(orderPage.getCountryInforAtEachSection(driver, "shipping-info").contains(country));

		log.info("Order_06: Step 45: Verify 'Payment Method' oder infor");
		verifyTrue(orderPage.getPaymentMethod(driver).contains("Payment Method: Credit Card"));
		verifyTrue(orderPage.getPaymentStatus(driver).contains("Payment Status: Pending"));

		log.info("Order_06: Step 46: Verify 'Shipping Method' oder infor");
		verifyTrue(orderPage.getShippingMethod(driver).contains("Shipping Method: Ground"));
		verifyTrue(orderPage.getShippingStatus(driver).contains("Shipping Status: Not yet shipped"));

		log.info("Order_06: Step 47: Verify The product order infor");
		verifyTrue(orderPage.isProductNameBytextDisplayed(driver, noteBooksProduct));
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "SKU"), "AP_MBP_13");
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Price"), "$1,800.00");
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Quantity"), "2");
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Total"), "$3,600.00");

		log.info("Order_06: Step 48: Verify The sumary price order list");
		verifyEquals(orderPage.getPriceInforByTextLabel(driver, "Sub-Total"), "$3,600.00");
		verifyEquals(orderPage.getPriceInforByTextLabel(driver, "Shipping"), "$0.00");
		verifyEquals(orderPage.getPriceInforByTextLabel(driver, "Tax"), "$0.00");
		verifyEquals(orderPage.getTotalPriceInfor(), "$3,600.00");
	}

	@Test
	public void Order_07_Re_Order() {

		log.info("Order_07: Step 01: Navigate to Homepage");
		homePage = orderPage.clickToHomeLink();

		log.info("Order_07: Step 02: Click to 'My Account' link");
		homePage.openHeaderUpperPageByText(driver, "My account");
		customerInforPage = PageGeneratorManagerNopCommerce.getUserCustomerInforPage(driver);

		log.info("Order_07: Step 03: Navigate to 'Orders' area at My Account page");
		customerInforPage.openPageAtMyAccountByName(driver, "Orders");
		orderPage = PageGeneratorManagerNopCommerce.getUserOrderPage(driver);

		log.info("Order_07: Step 04: Verify text 'Order Number: " + orderNumber + "' displayed");
		Assert.assertTrue(orderPage.isOrderNumberBytextDisplayed(driver, "Order Number: " + orderNumber));

		log.info("Order_07: Step 05: Click to 'Details' button");
		orderPage.clickToDetailsButtonByOrderNumber(driver, orderNumber);

		log.info("Order_07: Step 06: Click to 'Re-order' button");
		orderPage.clickToButtonByText(driver, "Re-order");

		log.info("Order_07: Step 07: Update product quantity is '10'");
		shoppingCartPage = PageGeneratorManagerNopCommerce.getUserShoppingCartPage(driver);
		shoppingCartPage.inputToQuantityTextboxByProductName(driver, noteBooksProduct, "10");

		log.info("Order_07: Step 08: Click to 'Update shopping cart' button");
		shoppingCartPage.clickToButtonByText(driver, "Update shopping cart");

		log.info("Order_07: Step 09: Select item in 'Gift wrapping' dropdown is 'No'");
		shoppingCartPage.selectItemInGiftWrappingDropdownBytext(driver, "No");

		log.info("Order_07: Step 10: Check to 'Terms of Service' checkbox");
		shoppingCartPage.checkToTermsOfServiceCheckbox(driver);

		log.info("Order_07: Step 11: Click to 'Checkout' button");
		shoppingCartPage.clickToButtonByText(driver, "Checkout");

		log.info("Order_07: Step 12: Uncheck to 'ShipToSameAddress' checkbox");
		checkOutPage = PageGeneratorManagerNopCommerce.getUserCheckOutProductPage(driver);
		checkOutPage.unCheckToShipToSameAddressCheckbox(driver);

		log.info("Order_07: Step 13: Click to 'Edit' button");
		checkOutPage.clickToEditButtonByID("billing-buttons-container");

		log.info("Order_07: Step 14: Input to 'Billing address' form");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FirstName", firstName);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_LastName", lastName);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Email", email);
		checkOutPage.selectCountryById(driver, "BillingNewAddress_CountryId", country);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_City", city);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address1", address_1);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address2", address_2);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", zipPostalCode);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phoneNumber);
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FaxNumber", faxNumber);

		log.info("Order_07: Step 15: Click to 'Continue' button at billing");
		checkOutPage.clickContinueButtonByID(driver, "billing-buttons-container");

		log.info("Order_07: Step 16: Select 'Shipping address' method");
		checkOutPage.selectAShippingAddressMethod(driver, "New Address");

		log.info("Order_07: Step 17: Input to 'Shipping address' form");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FirstName", firstName);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_LastName", lastName);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Email", email);
		checkOutPage.selectCountryById(driver, "ShippingNewAddress_CountryId", country);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_City", city);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address1", address_1);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address2", address_2);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_ZipPostalCode", zipPostalCode);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_PhoneNumber", phoneNumber);
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FaxNumber", faxNumber);

		log.info("Order_07: Step 18: Click to 'Continue' button at Shipping address");
		checkOutPage.clickContinueButtonByID(driver, "shipping-buttons-container");

		log.info("Order_07: Step 19: Check to Radio button in 'Shipping method' with label is 'Next Day Air ($0.00)'");
		checkOutPage.checkToRadioButtonByLabelText(driver, "Next Day Air ($0.00)");

		log.info("Order_07: Step 20: Click to 'Continue' button at Shipping address");
		checkOutPage.clickContinueButtonByID(driver, "shipping-method-buttons-container");

		log.info("Order_07: Step 21: Check to Radio button in 'Payment method' with label is 'Credit Card'");
		checkOutPage.checkToRadioButtonByLabelText(driver, "Credit Card");

		log.info("Order_07: Step 22: Click to 'Continue' button at Payment method");
		checkOutPage.clickContinueButtonByID(driver, "payment-method-buttons-container");

		log.info("Order_07: Step 23: Select credit card type");
		checkOutPage.selectToDropdownByName(driver, "CreditCardType", creditCardTypes);

		log.info("Order_07: Step 24: Select credit card type");
		checkOutPage.selectToDropdownByName(driver, "CreditCardType", creditCardTypes);

		log.info("Order_07: Step 25: Input into Cardholder name textbox with value is '" + cardHolderName + "'");
		checkOutPage.inputToTextboxByID(driver, "CardholderName", cardHolderName);

		log.info("Order_07: Step 26: Input into Card number textbox with value is '" + cardNumber + "'");
		checkOutPage.inputToTextboxByID(driver, "CardNumber", cardNumber);

		log.info("Order_07: Step 27: Input into Card code textbox with value is '" + cardCode + "'");
		checkOutPage.inputToTextboxByID(driver, "CardCode", cardCode);

		log.info("Order_07: Step 28: Select item in Expire Month dropdown with value is '" + expireMonth + "'");
		checkOutPage.selectToDropdownByName(driver, "ExpireMonth", expireMonth);

		log.info("Order_07: Step 29: Select item in Expire Year dropdown with value is '" + expireYear + "'");
		checkOutPage.selectToDropdownByName(driver, "ExpireYear", expireYear);

		log.info("Order_07: Step 30: Input into Card code textbox with value is '" + cardCode + "'");
		checkOutPage.inputToTextboxByID(driver, "CardCode", cardCode);

		log.info("Order_07: Step31: Click to 'Continue' button at Payment information");
		checkOutPage.clickContinueButtonByID(driver, "payment-info-buttons-container");

		log.info("Order_07: Step 32: Verify 'Billing Address' order infor");
		verifyTrue(checkOutPage.getNameInforAtEachSection(driver, "billing-info").contains(firstName + " " + lastName));
		verifyTrue(checkOutPage.getEmailInforAtEachSection(driver, "billing-info").contains(email));
		verifyTrue(checkOutPage.getPhoneInforAtEachSection(driver, "billing-info").contains(phoneNumber));
		verifyTrue(checkOutPage.getFaxInforAtEachSection(driver, "billing-info").contains(faxNumber));
		verifyTrue(checkOutPage.getAddress1InforAtEachSection(driver, "billing-info").contains(address_1));
		verifyTrue(checkOutPage.getAddress2InforAtEachSection(driver, "billing-info").contains(address_2));
		verifyTrue(checkOutPage.getCountryInforAtEachSection(driver, "billing-info").contains(country));

		log.info("Order_07: Step 33: Verify 'Shipping Address' order infor");
		verifyTrue(checkOutPage.getNameInforAtEachSection(driver, "shipping-info").contains(firstName + " " + lastName));
		verifyTrue(checkOutPage.getEmailInforAtEachSection(driver, "shipping-info").contains(email));
		verifyTrue(checkOutPage.getPhoneInforAtEachSection(driver, "shipping-info").contains(phoneNumber));
		verifyTrue(checkOutPage.getFaxInforAtEachSection(driver, "shipping-info").contains(faxNumber));
		verifyTrue(checkOutPage.getAddress1InforAtEachSection(driver, "shipping-info").contains(address_1));
		verifyTrue(checkOutPage.getAddress2InforAtEachSection(driver, "shipping-info").contains(address_2));
		verifyTrue(checkOutPage.getCountryInforAtEachSection(driver, "shipping-info").contains(country));

		log.info("Order_07: Step 34: Verify 'Payment Method' oder infor");
		verifyTrue(checkOutPage.getPaymentMethod(driver).contains("Payment Method: Credit Card"));

		log.info("Order_07: Step 35: Verify 'Shipping Method' oder infor");
		verifyTrue(checkOutPage.getShippingMethod(driver).contains("Shipping Method: Next Day Air"));

		log.info("Order_07: Step 36: Verify The product order infor");
		verifyTrue(checkOutPage.isProductNameBytextDisplayed(driver, noteBooksProduct));
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "SKU"), "AP_MBP_13");
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Price"), "$1,800.00");
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Qty"), "10");
		verifyEquals(checkOutPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Total"), "$18,000.00");

		log.info("Order_07:  Step 37: Verify The sumary price order list");
		verifyEquals(checkOutPage.getPriceInforByTextLabel(driver, "Sub-Total"), "$18,000.00");
		verifyEquals(checkOutPage.getPriceInforByTextLabel(driver, "Shipping"), "$0.00");
		verifyEquals(checkOutPage.getPriceInforByTextLabel(driver, "Tax"), "$0.00");
		verifyEquals(checkOutPage.getTotalPriceInfor(driver), "$18,000.00");
		checkOutPage.sleepInSecond(8);

		log.info("Order_07: Step 38: Click to 'Confirm' button");
		checkOutPage.clickToConfirmButton(driver);

		log.info("Order_07: Step 39: Verify Product order completed infor");
		checkOutPage.sleepInSecond(7);
		verifyEquals(checkOutPage.getOrderedSuccessPageTittle(driver), "Thank you");
		verifyEquals(checkOutPage.getOrderedSuccessMessage(driver), "Your order has been successfully processed!");
		orderNumber = checkOutPage.getOrderNumber(driver);
		verifyEquals(checkOutPage.getOrderNumberMessage(driver), "ORDER NUMBER: " + orderNumber);

		log.info("Order_07: Step 40: Click to Oder Completed 'Continue' button");
		homePage = checkOutPage.clickToOderCompletedContinueButton();

		log.info("Order_07: Step 41: Click to 'My Account' link");
		homePage.openHeaderUpperPageByText(driver, "My account");
		customerInforPage = PageGeneratorManagerNopCommerce.getUserCustomerInforPage(driver);

		log.info("Order_07: Step 42: Navigate to 'Orders' area at My Account page");
		customerInforPage.openPageAtMyAccountByName(driver, "Orders");
		orderPage = PageGeneratorManagerNopCommerce.getUserOrderPage(driver);

		log.info("Order_07: Step 43: Verify text 'Order Number: " + orderNumber + "' displayed");
		Assert.assertTrue(orderPage.isOrderNumberBytextDisplayed(driver, "Order Number: " + orderNumber));

		log.info("Order_07: Step 44: Click to 'Details' button");
		orderPage.clickToDetailsButtonByOrderNumber(driver, orderNumber);

		log.info("Order_07: Step 45: Verify 'order-overview' infor");
		verifyEquals(orderPage.getOrderNumberText(driver), "ORDER #" + orderNumber);
		verifyEquals(orderPage.getOrderDate(driver), "Order Date: " + getCurrentDayFormatted());
		verifyEquals(orderPage.getOrderStatus(driver), "Order Status: Pending");
		verifyEquals(orderPage.getOrderTotalPrice(driver), "Order Total: $18,000.00");

		log.info("Order_07: Step 46: Verify 'Billing Address' order infor");
		verifyTrue(orderPage.getNameInforAtEachSection(driver, "billing-info").contains(firstName + " " + lastName));
		verifyTrue(orderPage.getEmailInforAtEachSection(driver, "billing-info").contains(email));
		verifyTrue(orderPage.getPhoneInforAtEachSection(driver, "billing-info").contains(phoneNumber));
		verifyTrue(orderPage.getFaxInforAtEachSection(driver, "billing-info").contains(faxNumber));
		verifyTrue(orderPage.getAddress1InforAtEachSection(driver, "billing-info").contains(address_1));
		verifyTrue(orderPage.getAddress2InforAtEachSection(driver, "billing-info").contains(address_2));
		verifyTrue(orderPage.getCountryInforAtEachSection(driver, "billing-info").contains(country));

		log.info("Order_07: Step 47: Verify 'Shipping Address' order infor");
		verifyTrue(orderPage.getNameInforAtEachSection(driver, "shipping-info").contains(firstName + " " + lastName));
		verifyTrue(orderPage.getEmailInforAtEachSection(driver, "shipping-info").contains(email));
		verifyTrue(orderPage.getPhoneInforAtEachSection(driver, "shipping-info").contains(phoneNumber));
		verifyTrue(orderPage.getFaxInforAtEachSection(driver, "shipping-info").contains(faxNumber));
		verifyTrue(orderPage.getAddress1InforAtEachSection(driver, "shipping-info").contains(address_1));
		verifyTrue(orderPage.getAddress2InforAtEachSection(driver, "shipping-info").contains(address_2));
		verifyTrue(orderPage.getCountryInforAtEachSection(driver, "shipping-info").contains(country));

		log.info("Order_07: Step 48: Verify 'Payment Method' oder infor");
		verifyTrue(orderPage.getPaymentMethod(driver).contains("Payment Method: Credit Card"));
		verifyTrue(orderPage.getPaymentStatus(driver).contains("Payment Status: Pending"));

		log.info("Order_07: Step 49: Verify 'Shipping Method' oder infor");
		verifyTrue(orderPage.getShippingMethod(driver).contains("Shipping Method: Next Day Air"));
		verifyTrue(orderPage.getShippingStatus(driver).contains("Shipping Status: Not yet shipped"));

		log.info("Order_07: Step 50: Verify The product order infor");
		verifyTrue(orderPage.isProductNameBytextDisplayed(driver, noteBooksProduct));
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "SKU"), "AP_MBP_13");
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Price"), "$1,800.00");
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Quantity"), "10");
		verifyEquals(orderPage.getInforProductByProductNameAndAtribute(driver, noteBooksProduct, "Total"), "$18,000.00");

		log.info("Order_07: Step 51: Verify The sumary price order list");
		verifyEquals(orderPage.getPriceInforByTextLabel(driver, "Sub-Total"), "$18,000.00");
		verifyEquals(orderPage.getPriceInforByTextLabel(driver, "Shipping"), "$0.00");
		verifyEquals(orderPage.getPriceInforByTextLabel(driver, "Tax"), "$0.00");
		verifyEquals(orderPage.getTotalPriceInfor(), "$18,000.00");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserDriver();
	}

}
