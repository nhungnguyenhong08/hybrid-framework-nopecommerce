package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.AddressPageUI;

public class UserAddressPageObject extends BasePage {
	private WebDriver driver;

	public UserAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getAddNewAddressSuccessMessage() {
		waitForElementVisible(driver, AddressPageUI.ADD_ADDRESS_SUCCESS_MESSAGE);
		return getElementText(driver, AddressPageUI.ADD_ADDRESS_SUCCESS_MESSAGE);
	}

	public String getUpdatedAddressOfInforByClass(WebDriver driver, String className) {
		waitForElementVisible(driver, AddressPageUI.ADDRESS_LIST_BY_NAME, className);
		return getElementText(driver, AddressPageUI.ADDRESS_LIST_BY_NAME, className);
	}

}
