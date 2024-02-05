package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerNopCommerce;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.OrderPageUI;

public class UserOrderPageObject extends BasePage {
	private WebDriver driver;

	public UserOrderPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOrderNumberBytextDisplayed(WebDriver driver, String textOrderNumber) {
		waitForElementVisible(driver, OrderPageUI.ORDER_NUMBER_BY_TEXT, textOrderNumber);
		return isElementDisplayed(driver, OrderPageUI.ORDER_NUMBER_BY_TEXT, textOrderNumber);
	}

	public void clickToDetailsButtonByOrderNumber(WebDriver driver, String orderNumber) {
		waitForElementClickable(driver, OrderPageUI.DETAILS_BUTTON_BY_ORDER_NUMBER, orderNumber);
		clickToElement(driver, OrderPageUI.DETAILS_BUTTON_BY_ORDER_NUMBER, orderNumber);

	}

	public String getOrderNumberText(WebDriver driver) {
		waitForElementVisible(driver, OrderPageUI.ORDER_NUMBER);
		return getElementText(driver, OrderPageUI.ORDER_NUMBER);
	}

	public String getOrderDate(WebDriver driver) {
		waitForElementVisible(driver, OrderPageUI.ORDER_DATE);
		return getElementText(driver, OrderPageUI.ORDER_DATE);
	}

	public String getOrderStatus(WebDriver driver) {
		waitForElementVisible(driver, OrderPageUI.ORDER_STATUS);
		return getElementText(driver, OrderPageUI.ORDER_STATUS);
	}

	public String getOrderTotalPrice(WebDriver driver) {
		waitForElementVisible(driver, OrderPageUI.ORDER_TOTAL_PRICE);
		return getElementText(driver, OrderPageUI.ORDER_TOTAL_PRICE);
	}

	public String getNameInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, OrderPageUI.NAME_INFOR_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, OrderPageUI.NAME_INFOR_AT_EACH_SECTION, attributeSection);
	}

	public String getEmailInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, OrderPageUI.EMAIL_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, OrderPageUI.EMAIL_AT_EACH_SECTION, attributeSection);
	}

	public String getPhoneInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, OrderPageUI.PHONE_NUMBER_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, OrderPageUI.PHONE_NUMBER_AT_EACH_SECTION, attributeSection);
	}

	public String getFaxInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, OrderPageUI.FAX_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, OrderPageUI.FAX_AT_EACH_SECTION, attributeSection);
	}

	public String getAddress1InforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, OrderPageUI.ADDRESS1_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, OrderPageUI.ADDRESS1_AT_EACH_SECTION, attributeSection);
	}

	public String getAddress2InforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, OrderPageUI.ADDRESS2_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, OrderPageUI.ADDRESS2_AT_EACH_SECTION, attributeSection);
	}

	public String getCountryInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, OrderPageUI.COUNTRY_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, OrderPageUI.COUNTRY_AT_EACH_SECTION, attributeSection);
	}

	public String getPaymentMethod(WebDriver driver) {
		waitForElementVisible(driver, OrderPageUI.PAYMENT_METHOD);
		return getElementText(driver, OrderPageUI.PAYMENT_METHOD);
	}

	public String getPaymentStatus(WebDriver driver) {
		waitForElementVisible(driver, OrderPageUI.PAYMENT_STATUS);
		return getElementText(driver, OrderPageUI.PAYMENT_STATUS);
	}

	public String getShippingMethod(WebDriver driver) {
		waitForElementVisible(driver, OrderPageUI.SHIPPING_METHOD);
		return getElementText(driver, OrderPageUI.SHIPPING_METHOD);
	}

	public String getShippingStatus(WebDriver driver) {
		waitForElementVisible(driver, OrderPageUI.SHIPPING_STATUS);
		return getElementText(driver, OrderPageUI.SHIPPING_STATUS);
	}

	public boolean isProductNameBytextDisplayed(WebDriver driver, String productName) {
		waitForElementVisible(driver, OrderPageUI.PRODUCT_NAME_BY_TEXT, productName);
		return isElementDisplayed(driver, OrderPageUI.PRODUCT_NAME_BY_TEXT, productName);
	}

	public String getInforProductByProductNameAndAtribute(WebDriver driver, String productName, String atribute) {
		waitForElementVisible(driver, OrderPageUI.ITEM_BY_PRODUCT_NAME_AND_ATRIBUTE, productName, atribute);
		return getElementText(driver, OrderPageUI.ITEM_BY_PRODUCT_NAME_AND_ATRIBUTE, productName, atribute);
	}

	public String getPriceInforByTextLabel(WebDriver driver, String textLabel) {
		waitForElementVisible(driver, OrderPageUI.PRICE_INFOR_BY_LABEL, textLabel);
		return getElementText(driver, OrderPageUI.PRICE_INFOR_BY_LABEL, textLabel);
	}

	public String getTotalPriceInfor() {
		waitForElementVisible(driver, OrderPageUI.PRICE_TOTAL_INFOR);
		return getElementText(driver, OrderPageUI.PRICE_TOTAL_INFOR);
	}

	@Step("Click to Home link")
	public UserHomePageObject clickToHomeLink() {
		waitForElementVisible(driver, OrderPageUI.HOME_LINK);
		clickToElement(driver, OrderPageUI.HOME_LINK);
		return PageGeneratorManagerNopCommerce.getUserHomePage(driver);

	}

}
