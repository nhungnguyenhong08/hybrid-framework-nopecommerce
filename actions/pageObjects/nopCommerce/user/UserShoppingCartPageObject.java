package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.ShoppingCartPageUI;

public class UserShoppingCartPageObject extends BasePage {
	private WebDriver driver;

	public UserShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRequestedPageDisplayed(WebDriver driver, String pageTitle) {
		waitForElementVisible(driver, ShoppingCartPageUI.PAGE_TITLE, pageTitle);
		return isElementDisplayed(driver, ShoppingCartPageUI.PAGE_TITLE, pageTitle);
	}

	public void openHomePage() {
		waitForElementClickable(driver, ShoppingCartPageUI.HEADER_LOGO);
		clickToElement(driver, ShoppingCartPageUI.HEADER_LOGO);
	}

	public UserProductPageObject clickToEditProduct(WebDriver driver) {
		waitForElementClickable(driver, ShoppingCartPageUI.EDIT_LINK);
		clickToElement(driver, ShoppingCartPageUI.EDIT_LINK);
		// 2
		return new UserProductPageObject(driver);

		// 3
		// return PageGeneratorManagerNopCommerce.getUserProductPage(driver);
	}

	public void clickToRemoveButtonByProductName(WebDriver driver, String productName) {
		waitForElementClickable(driver, ShoppingCartPageUI.REMOVE_BUTTON, productName);
		clickToElement(driver, ShoppingCartPageUI.REMOVE_BUTTON, productName);
	}

	public String getMessageDisplayed(WebDriver driver) {
		waitForElementVisible(driver, ShoppingCartPageUI.MESSAGE_AT_SHOPPING_CART);
		return getElementText(driver, ShoppingCartPageUI.MESSAGE_AT_SHOPPING_CART);
	}

	public boolean isProductUndisplayedInShoppingCart(WebDriver driver, String productName) {
		waitForElementInvisible(driver, ShoppingCartPageUI.PRODUCT_NAME_IN_LIST, productName);
		return isElementUndisplayed(driver, ShoppingCartPageUI.PRODUCT_NAME_IN_LIST, productName);
	}

	public void inputToQuantityTextboxByProductName(WebDriver driver, String secondDesktopsProduct, String productNumber) {
		waitForElementClickable(driver, ShoppingCartPageUI.PRODUCT_QUANTITY_BY_PRODUCT_NAME, secondDesktopsProduct);
		sendkeyToElement(driver, ShoppingCartPageUI.PRODUCT_QUANTITY_BY_PRODUCT_NAME, productNumber, secondDesktopsProduct);
	}

	public String getProductPriceByProductName(WebDriver driver, String productName) {
		waitForElementVisible(driver, ShoppingCartPageUI.PRODUCT_PRICE_BY_PRODUCT_NAME, productName);
		return getElementText(driver, ShoppingCartPageUI.PRODUCT_PRICE_BY_PRODUCT_NAME, productName);
	}

	public void selectItemInGiftWrappingDropdownBytext(WebDriver driver, String itemText) {
		waitForElementVisible(driver, ShoppingCartPageUI.GIFT_WRAPING_DROP_DOWN_BY_TEXT);
		selectItemInDefaultDropdown(driver, ShoppingCartPageUI.GIFT_WRAPING_DROP_DOWN_BY_TEXT, itemText);

	}

	public void checkToTermsOfServiceCheckbox(WebDriver driver) {
		waitForElementClickable(driver, ShoppingCartPageUI.TERM_OF_SERVICE_CHECKBOX);
		checkToDefaultCheckboxOrRadio(driver, ShoppingCartPageUI.TERM_OF_SERVICE_CHECKBOX);
	}
}
