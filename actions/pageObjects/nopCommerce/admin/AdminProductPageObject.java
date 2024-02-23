package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminProductPageUI;

public class AdminProductPageObject extends BasePage {
	private WebDriver driver;

	public AdminProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSearchProductButton() {
		waitForElementClickable(driver, AdminProductPageUI.SEARCH_PRODUCT_BUTTON);
		clickToElement(driver, AdminProductPageUI.SEARCH_PRODUCT_BUTTON);
	}

	public boolean isProductNameByTextDisplayed(String productName) {
		waitForElementVisible(driver, AdminProductPageUI.PRODUCT_NAME_BY_TEXT, productName);
		return isElementDisplayed(driver, AdminProductPageUI.PRODUCT_NAME_BY_TEXT, productName);
	}

	public String getTextItemByProductNameAndColumName(String productName, String columnName) {
		int columnIndex = getElementSize(driver, AdminProductPageUI.COLUMN_INDEX_BY_TEXT, columnName) + 1;
		waitForElementVisible(driver, AdminProductPageUI.TEXT_VALUE_BY_PRODUCT_NAME_AND_COLUM_INDEX, productName, String.valueOf(columnIndex));
		return getElementText(driver, AdminProductPageUI.TEXT_VALUE_BY_PRODUCT_NAME_AND_COLUM_INDEX, productName, String.valueOf(columnIndex));
	}

	public boolean isPublicItemCheckedByProductNameDisplayed(String productName, String columnName) {
		int columnIndex = getElementSize(driver, AdminProductPageUI.COLUMN_INDEX_BY_TEXT, columnName) + 1;
		waitForElementVisible(driver, AdminProductPageUI.PRODUCT_CHECK_PUBLIC_ICON_BY_PRODUCT_NAME_AND_COLUM_INDEX, productName, String.valueOf(columnIndex));
		return isElementDisplayed(driver, AdminProductPageUI.PRODUCT_CHECK_PUBLIC_ICON_BY_PRODUCT_NAME_AND_COLUM_INDEX, productName, String.valueOf(columnIndex));
	}

	public void selectItemInDefaultDropdownByLabel(String dropdownLabel, String textItem) {
		waitForElementClickable(driver, AdminProductPageUI.DYNAMIC_DROPDOWN_BY_TEXTLABEL, dropdownLabel);
		selectItemInDefaultDropdown(driver, AdminProductPageUI.DYNAMIC_DROPDOWN_BY_TEXTLABEL, textItem, dropdownLabel);
	}

	public void uncheckToSearchSubcategories() {
		waitForElementClickable(driver, AdminProductPageUI.SEARCH_SUBCATEGORIES_CHECKBOX);
		uncheckToDefaultCheckboxOrRadio(driver, AdminProductPageUI.SEARCH_SUBCATEGORIES_CHECKBOX);
	}

	public boolean isMessageDataTableDisplayed(String message) {
		waitForElementVisible(driver, AdminProductPageUI.PRODUCT_MESSAGE_DATA_TABEL_BY_TEXT, message);
		return isElementDisplayed(driver, AdminProductPageUI.PRODUCT_MESSAGE_DATA_TABEL_BY_TEXT, message);
	}

	public void checkToSearchSubcategories() {
		waitForElementClickable(driver, AdminProductPageUI.SEARCH_SUBCATEGORIES_CHECKBOX);
		checkToDefaultCheckboxOrRadio(driver, AdminProductPageUI.SEARCH_SUBCATEGORIES_CHECKBOX);
	}

}
