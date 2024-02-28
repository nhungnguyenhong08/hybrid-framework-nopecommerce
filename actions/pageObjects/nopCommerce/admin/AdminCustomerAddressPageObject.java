package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminCustomerAddressPageUI;

public class AdminCustomerAddressPageObject extends BasePage {
	private WebDriver driver;

	public AdminCustomerAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isUpdateSuccessMessageByTextDisplayed() {
		waitForElementVisible(driver, AdminCustomerAddressPageUI.SUCCESS_MESSAGE);
		return isElementDisplayed(driver, AdminCustomerAddressPageUI.SUCCESS_MESSAGE);
	}

	public String getCustomerAddressInforTextboxInforByLabel(String labelText) {
		waitForElementVisible(driver, AdminCustomerAddressPageUI.DYNAMIC_CUSTOMER_ADDRESS_BY_TEXTLABEL, labelText);
		return getElementAttribute(driver, AdminCustomerAddressPageUI.DYNAMIC_CUSTOMER_ADDRESS_BY_TEXTLABEL, "value", labelText);
	}

	public void clickToBackToCustomerDetailLink() {
		waitForElementClickable(driver, AdminCustomerAddressPageUI.BACK_TO_CUSTOMER_DETAIL_LINK);
		clickToElement(driver, AdminCustomerAddressPageUI.BACK_TO_CUSTOMER_DETAIL_LINK);
	}

	public String getAddressCountryDropdownInfor() {
		waitForElementVisible(driver, AdminCustomerAddressPageUI.ADDRESS_COUNTRY_ITEM_SELECTED);
		return getElementText(driver, AdminCustomerAddressPageUI.ADDRESS_COUNTRY_ITEM_SELECTED);
	}

	public String getAddressStateOrProvinceDropdownInfor() {
		waitForElementVisible(driver, AdminCustomerAddressPageUI.STATE_OR_PROVINCE_ITEM_SELECTED);
		return getElementText(driver, AdminCustomerAddressPageUI.STATE_OR_PROVINCE_ITEM_SELECTED);
	}

	public void clickToButtonByName(String attributeValue) {
		waitForElementClickable(driver, AdminCustomerAddressPageUI.DYNAMIC_BUTTON_BY_NAME, attributeValue);
		clickToElement(driver, AdminCustomerAddressPageUI.DYNAMIC_BUTTON_BY_NAME, attributeValue);

	}

}
