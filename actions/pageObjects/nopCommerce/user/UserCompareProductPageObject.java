package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CompareProductPageUI;

public class UserCompareProductPageObject extends BasePage {
	private WebDriver driver;

	public UserCompareProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductNameBytextDisplayed(WebDriver driver, String productName) {
		waitForElementVisible(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME_BY_TEXT, productName);
		return isElementDisplayed(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME_BY_TEXT, productName);
	}

	public boolean isRemoveButtonByProductNameDisplayed(WebDriver driver, String productName) {
		int columnIndex = getElementSize(driver, CompareProductPageUI.COLUMN_INDEX_BY_PRODUCT_NAME, productName);
		waitForElementVisible(driver, CompareProductPageUI.REMOVE_BUTTON_BY_PRODUCT_NAME, String.valueOf(columnIndex));
		return isElementDisplayed(driver, CompareProductPageUI.REMOVE_BUTTON_BY_PRODUCT_NAME, String.valueOf(columnIndex));
	}

	public boolean isPriceTextByProductNameAtRowNameDisplayed(WebDriver driver, String productName) {
		int columnIndex = getElementSize(driver, CompareProductPageUI.COLUMN_INDEX_BY_PRODUCT_NAME, productName);
		waitForElementVisible(driver, CompareProductPageUI.PRICCE_BY_PRODUCT_NAME, String.valueOf(columnIndex));
		return isElementDisplayed(driver, CompareProductPageUI.PRICCE_BY_PRODUCT_NAME, String.valueOf(columnIndex));
	}

	public void clickToClearListButton() {
		waitForElementClickable(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
	}

	public String getMesssageDisplayed() {
		waitForElementVisible(driver, CompareProductPageUI.MESSAGE_DISPLAYED);
		return getElementText(driver, CompareProductPageUI.MESSAGE_DISPLAYED);
	}

	public boolean isProductNameBytextUnDisplayed(WebDriver driver, String productName) {
		waitForElementInvisible(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME_BY_TEXT, productName);
		return isElementUndisplayed(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME_BY_TEXT, productName);
	}

	public void openHomePage() {
		waitForElementClickable(driver, CompareProductPageUI.HEADER_LOGO);
		clickToElement(driver, CompareProductPageUI.HEADER_LOGO);
	}

}
