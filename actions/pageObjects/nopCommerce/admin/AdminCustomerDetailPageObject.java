package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminCustomerDetailPageUI;

public class AdminCustomerDetailPageObject extends BasePage {
	private WebDriver driver;

	public AdminCustomerDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void deleteItemInDropDownByText(String textRole) {
		waitForElementClickable(driver, AdminCustomerDetailPageUI.CUSTOMER_DELETE_ROLE_BUTTON_BY_TEXT, textRole);
		clickToElement(driver, AdminCustomerDetailPageUI.CUSTOMER_DELETE_ROLE_BUTTON_BY_TEXT, textRole);

	}

	public void selectItemInDropDownByText(String textRole) {
		waitForElementClickable(driver, AdminCustomerDetailPageUI.CUSTOMER_ROLE_PARENT_DROPDOWN);
		clickToElement(driver, AdminCustomerDetailPageUI.CUSTOMER_ROLE_PARENT_DROPDOWN);

		waitForElementClickable(driver, AdminCustomerDetailPageUI.CUSTOMER_ROLE_VALUE, textRole);
		clickToElement(driver, AdminCustomerDetailPageUI.CUSTOMER_ROLE_VALUE, textRole);
	}

	public void clickToSaveAndContinueEditButton() {
		waitForElementClickable(driver, AdminCustomerDetailPageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
		clickToElement(driver, AdminCustomerDetailPageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
	}

	public String getCustomerTextboxInforByLabel(String labelText) {
		waitForElementVisible(driver, AdminCustomerDetailPageUI.DYNAMIC_CUSTOMER_INFOR_BY_TEXTLABEL, labelText);
		return getElementAttribute(driver, AdminCustomerDetailPageUI.DYNAMIC_CUSTOMER_INFOR_BY_TEXTLABEL, "value", labelText);
	}

	public boolean isUpdateSuccessMessageByTextDisplayed() {
		waitForElementVisible(driver, AdminCustomerDetailPageUI.SUCCESS_MESSAGE);
		return isElementDisplayed(driver, AdminCustomerDetailPageUI.SUCCESS_MESSAGE);
	}

	public boolean isCustomerGenderRadioButtonByLabelSelected(String textValue) {
		waitForElementVisible(driver, AdminCustomerDetailPageUI.CUSTOMER_GENDER_RADIO_BY_TEXT_LABEL, textValue);
		return isElementSelected(driver, AdminCustomerDetailPageUI.CUSTOMER_GENDER_RADIO_BY_TEXT_LABEL, textValue);
	}

	public boolean isActiveCheckboxSelected() {
		waitForElementVisible(driver, AdminCustomerDetailPageUI.ACTIVE_CHECKBOX);
		return isElementSelected(driver, AdminCustomerDetailPageUI.ACTIVE_CHECKBOX);
	}

	public String getAdminCommnentMessage() {
		waitForElementVisible(driver, AdminCustomerDetailPageUI.ADMIN_COMMENT_TEXTBOX);
		return getElementText(driver, AdminCustomerDetailPageUI.ADMIN_COMMENT_TEXTBOX);
	}

	public void clickToBackToCustomerList() {
		waitForElementClickable(driver, AdminCustomerDetailPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		clickToElement(driver, AdminCustomerDetailPageUI.BACK_TO_CUSTOMER_LIST_LINK);
	}

	public void inputToAdminCommentTextbox(String textValue) {
		waitForElementClickable(driver, AdminCustomerDetailPageUI.ADMIN_COMMENT_TEXTBOX);
		sendkeyToElement(driver, AdminCustomerDetailPageUI.ADMIN_COMMENT_TEXTBOX, textValue);

	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, AdminCustomerDetailPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminCustomerDetailPageUI.SAVE_BUTTON);

	}

	public boolean isEmailAddressByTextDisplayed(String editEmailAddress) {
		waitForElementVisible(driver, AdminCustomerDetailPageUI.EMAIL_ADDRESS_BY_TEXT, editEmailAddress);
		return isElementDisplayed(driver, AdminCustomerDetailPageUI.EMAIL_ADDRESS_BY_TEXT, editEmailAddress);
	}

	public String getTextItemByEmailAddressAndColumnName(String editEmailAddress, String columnName) {
		int columnIndex = getElementSize(driver, AdminCustomerDetailPageUI.COLUMN_INDEX_BY_TEXT, columnName) + 1;
		waitForElementVisible(driver, AdminCustomerDetailPageUI.TEXT_VALUE_BY_EMAIL_ADDRESS_AND_COLUMN_INDEX, editEmailAddress, String.valueOf(columnIndex));
		return getElementText(driver, AdminCustomerDetailPageUI.TEXT_VALUE_BY_EMAIL_ADDRESS_AND_COLUMN_INDEX, editEmailAddress, String.valueOf(columnIndex));
	}

	public void clickToCardByText(String cardText) {
		scrollToElement(driver, AdminCustomerDetailPageUI.DYNAMIC_CARD_BY_TEXT, cardText);
		if (isElementUndisplayed(driver, AdminCustomerDetailPageUI.MINUS_ICON_AT_CARD_BY_TEXT, cardText)) {
			waitForElementClickable(driver, AdminCustomerDetailPageUI.DYNAMIC_CARD_BY_TEXT, cardText);
			clickToElement(driver, AdminCustomerDetailPageUI.DYNAMIC_CARD_BY_TEXT, cardText);
		}

	}

	public void clickToEditIconByEmailAddressAndColumnName(String editEmailAddress, String columnName) {
		int columnIndex = getElementSize(driver, AdminCustomerDetailPageUI.COLUMN_INDEX_BY_TEXT, columnName) + 1;
		waitForElementClickable(driver, AdminCustomerDetailPageUI.ICON_BY_EMAIL_ADDRESS_AND_COLUMN_INDEX, editEmailAddress, String.valueOf(columnIndex));
		clickToElement(driver, AdminCustomerDetailPageUI.ICON_BY_EMAIL_ADDRESS_AND_COLUMN_INDEX, editEmailAddress, String.valueOf(columnIndex));
	}

	public void clickToDeleteIconByEmailAddressAndColumnName(String editEmailAddress, String columnName) {
		int columnIndex = getElementSize(driver, AdminCustomerDetailPageUI.COLUMN_INDEX_BY_TEXT, columnName) + 1;
		waitForElementClickable(driver, AdminCustomerDetailPageUI.ICON_BY_EMAIL_ADDRESS_AND_COLUMN_INDEX, editEmailAddress, String.valueOf(columnIndex));
		clickToElement(driver, AdminCustomerDetailPageUI.ICON_BY_EMAIL_ADDRESS_AND_COLUMN_INDEX, editEmailAddress, String.valueOf(columnIndex));

	}

	public boolean isMessageDataTableByCardNameAndTextDisplayed(String cardName, String message) {
		waitForElementVisible(driver, AdminCustomerDetailPageUI.MESSAGE_AT_TABLE_BY_NAME_CARD_AND_TEXT, cardName, message);
		return isElementDisplayed(driver, AdminCustomerDetailPageUI.MESSAGE_AT_TABLE_BY_NAME_CARD_AND_TEXT, cardName, message);
	}

}
