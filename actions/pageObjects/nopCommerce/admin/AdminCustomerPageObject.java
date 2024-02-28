package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminCustomerPageUI;

public class AdminCustomerPageObject extends BasePage {
	private WebDriver driver;

	public AdminCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAddNewButton() {
		waitForElementClickable(driver, AdminCustomerPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.ADD_NEW_BUTTON);
	}

	public void deleteItemInCustomerSearchRolesDropdown(String textRole) {
		waitForElementClickable(driver, AdminCustomerPageUI.CUSTOMER_DELETE_ROLE_BUTTON_BY_TEXT, textRole);
		clickToElement(driver, AdminCustomerPageUI.CUSTOMER_DELETE_ROLE_BUTTON_BY_TEXT, textRole);
	}

	public void selectItemInCustomerSearchRolesDropdown(String textRole) {
		waitForElementClickable(driver, AdminCustomerPageUI.CUSTOMER_ROLE_TEXT_BOX);
		clickToElement(driver, AdminCustomerPageUI.CUSTOMER_ROLE_TEXT_BOX);

		waitForElementClickable(driver, AdminCustomerPageUI.CUSTOMER_ROLE_VALUE, textRole);
		clickToElement(driver, AdminCustomerPageUI.CUSTOMER_ROLE_VALUE, textRole);
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminCustomerPageUI.SEARCH_CUSTOMER_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.SEARCH_CUSTOMER_BUTTON);
	}

	public boolean isCustomerNameByTextDisplayed(String customerName) {
		waitForElementVisible(driver, AdminCustomerPageUI.CUSTOMER_NAME_BY_TEXT, customerName);
		return isElementDisplayed(driver, AdminCustomerPageUI.CUSTOMER_NAME_BY_TEXT, customerName);
	}

	public String getTextItemByCustomerNameAndColumnName(String customerName, String columnName) {
		int columnIndex = getElementSize(driver, AdminCustomerPageUI.COLUMN_INDEX_BY_TEXT, columnName) + 1;
		waitForElementVisible(driver, AdminCustomerPageUI.TEXT_VALUE_BY_CUSTOMER_NAME_AND_COLUM_INDEX, customerName, String.valueOf(columnIndex));
		return getElementText(driver, AdminCustomerPageUI.TEXT_VALUE_BY_CUSTOMER_NAME_AND_COLUM_INDEX, customerName, String.valueOf(columnIndex));
	}

	public boolean isUpdateSuccessMessageByTextDisplayed() {
		waitForElementVisible(driver, AdminCustomerPageUI.SUCCESS_MESSAGE);
		return isElementDisplayed(driver, AdminCustomerPageUI.SUCCESS_MESSAGE);
	}

	public boolean isActiveCheckedByCustomerNameDisplayed(String customerName, String columnName) {
		int columnIndex = getElementSize(driver, AdminCustomerPageUI.COLUMN_INDEX_BY_TEXT, columnName) + 1;
		waitForElementVisible(driver, AdminCustomerPageUI.TEXT_VALUE_BY_CUSTOMER_NAME_AND_COLUM_INDEX, customerName, String.valueOf(columnIndex));
		return isElementDisplayed(driver, AdminCustomerPageUI.TEXT_VALUE_BY_CUSTOMER_NAME_AND_COLUM_INDEX, customerName, String.valueOf(columnIndex));
	}

	public void clickToEditButtonByCustomerNameAndColumnName(String customerName, String columnName) {
		int columnIndex = getElementSize(driver, AdminCustomerPageUI.COLUMN_INDEX_BY_TEXT, columnName) + 1;
		waitForElementVisible(driver, AdminCustomerPageUI.TEXT_VALUE_BY_CUSTOMER_NAME_AND_COLUM_INDEX, customerName, String.valueOf(columnIndex));
		clickToElement(driver, AdminCustomerPageUI.TEXT_VALUE_BY_CUSTOMER_NAME_AND_COLUM_INDEX, customerName, String.valueOf(columnIndex));
	}

}
