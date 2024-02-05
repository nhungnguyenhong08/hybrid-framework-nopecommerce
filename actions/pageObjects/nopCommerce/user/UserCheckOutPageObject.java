package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerNopCommerce;
import pageUIs.nopCommerce.user.CheckoutPageUI;

public class UserCheckOutPageObject extends BasePage {
	private WebDriver driver;

	public UserCheckOutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void unCheckToShipToSameAddressCheckbox(WebDriver driver) {
		waitForElementClickable(driver, CheckoutPageUI.SHIP_TO_SAME_ADDRESS_CHECKBOX);
		uncheckToDefaultCheckboxOrRadio(driver, CheckoutPageUI.SHIP_TO_SAME_ADDRESS_CHECKBOX);

	}

	public void selectAShippingAddressMethod(WebDriver driver, String itemValue) {
		waitForElementVisible(driver, CheckoutPageUI.SHIPPING_ADDRESS_METHOD);
		selectItemInDefaultDropdown(driver, CheckoutPageUI.SHIPPING_ADDRESS_METHOD, itemValue);

	}

	public void selectCountryById(WebDriver driver, String idValue, String itemValue) {
		waitForElementVisible(driver, CheckoutPageUI.COUNTRY_DROPDOWN_BY_ID, idValue);
		selectItemInDefaultDropdown(driver, CheckoutPageUI.COUNTRY_DROPDOWN_BY_ID, itemValue, idValue);

	}

	public void clickContinueButtonByID(WebDriver driver, String idValue) {
		waitForElementClickable(driver, CheckoutPageUI.CONTINUE_BUTTON_BY_ID, idValue);
		clickToElement(driver, CheckoutPageUI.CONTINUE_BUTTON_BY_ID, idValue);

	}

	public String getAddressShippingInfor(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.ADDRESS_SHIPPING_INFOR);
		return getElementText(driver, CheckoutPageUI.ADDRESS_SHIPPING_INFOR);
	}

	public String getNameInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, CheckoutPageUI.NAME_INFOR_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, CheckoutPageUI.NAME_INFOR_AT_EACH_SECTION, attributeSection);
	}

	public String getEmailInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, CheckoutPageUI.EMAIL_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, CheckoutPageUI.EMAIL_AT_EACH_SECTION, attributeSection);
	}

	public String getPhoneInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, CheckoutPageUI.PHONE_NUMBER_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, CheckoutPageUI.PHONE_NUMBER_AT_EACH_SECTION, attributeSection);
	}

	public String getFaxInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, CheckoutPageUI.FAX_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, CheckoutPageUI.FAX_AT_EACH_SECTION, attributeSection);
	}

	public String getAddress1InforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, CheckoutPageUI.ADDRESS1_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, CheckoutPageUI.ADDRESS1_AT_EACH_SECTION, attributeSection);
	}

	public String getAddress2InforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, CheckoutPageUI.ADDRESS2_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, CheckoutPageUI.ADDRESS2_AT_EACH_SECTION, attributeSection);
	}

	public String getCountryInforAtEachSection(WebDriver driver, String attributeSection) {
		waitForElementVisible(driver, CheckoutPageUI.COUNTRY_AT_EACH_SECTION, attributeSection);
		return getElementText(driver, CheckoutPageUI.COUNTRY_AT_EACH_SECTION, attributeSection);
	}

	public String getPaymentMethod(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.PAYMENT_METHOD);
		return getElementText(driver, CheckoutPageUI.PAYMENT_METHOD);
	}

	public String getShippingMethod(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.SHIPPING_METHOD);
		return getElementText(driver, CheckoutPageUI.SHIPPING_METHOD);
	}

	public String getPriceInforByTextLabel(WebDriver driver, String textLabel) {
		waitForElementVisible(driver, CheckoutPageUI.PRICE_INFOR_BY_LABEL, textLabel);
		return getElementText(driver, CheckoutPageUI.PRICE_INFOR_BY_LABEL, textLabel);
	}

	public String getTotalPriceInfor(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.PRICE_TOTAL_INFOR);
		return getElementText(driver, CheckoutPageUI.PRICE_TOTAL_INFOR);
	}

	public void clickToConfirmButton(WebDriver driver) {
		waitForElementClickable(driver, CheckoutPageUI.CONFIRM_BUTTON);
		clickToElement(driver, CheckoutPageUI.CONFIRM_BUTTON);

	}

	public String getOrderedSuccessPageTittle(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.PAGE_TITLE_SUCCESS_MESSAGE);
		return getElementText(driver, CheckoutPageUI.PAGE_TITLE_SUCCESS_MESSAGE);
	}

	public String getOrderedSuccessMessage(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.ORDER_SUCCESS_MESSAGE);
		return getElementText(driver, CheckoutPageUI.ORDER_SUCCESS_MESSAGE);
	}

	public String getOrderNumberMessage(WebDriver driver) {
		waitForElementVisible(driver, CheckoutPageUI.ORDER_NUMBER_SUCCESS_MESSAGE);
		return getElementText(driver, CheckoutPageUI.ORDER_NUMBER_SUCCESS_MESSAGE);
	}

	public String getOrderNumber(WebDriver driver) {
		return getOrderNumberMessage(driver).split(":", 2)[1].trim();
	}

	public boolean isProductNameBytextDisplayed(WebDriver driver, String productName) {
		waitForElementVisible(driver, CheckoutPageUI.PRODUCT_NAME_BY_TEXT, productName);
		return isElementDisplayed(driver, CheckoutPageUI.PRODUCT_NAME_BY_TEXT, productName);
	}

	public String getInforProductByProductNameAndAtribute(WebDriver driver, String productName, String atribute) {
		waitForElementVisible(driver, CheckoutPageUI.ITEM_BY_PRODUCT_NAME_AND_ATRIBUTE, productName, atribute);
		return getElementText(driver, CheckoutPageUI.ITEM_BY_PRODUCT_NAME_AND_ATRIBUTE, productName, atribute);
	}

	public UserHomePageObject clickToOderCompletedContinueButton() {
		waitForElementClickable(driver, CheckoutPageUI.COMPLETE_CONTINUE_BUTTON);
		clickToElement(driver, CheckoutPageUI.COMPLETE_CONTINUE_BUTTON);
		return PageGeneratorManagerNopCommerce.getUserHomePage(driver);
	}

	public void clickToEditButtonByID(String idValue) {
		waitForElementClickable(driver, CheckoutPageUI.EDIT_BUTTON_BY_ID, idValue);
		clickToElement(driver, CheckoutPageUI.EDIT_BUTTON_BY_ID, idValue);

	}

}
