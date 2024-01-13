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

	public String getQuantityAtHeaderPage(WebDriver driver, String headeText) {
		waitForElementVisible(driver, ShoppingCartPageUI.SECOND_PAGE_HEADER_QUANTITY_BY_TEXT, headeText);
		return getElementText(driver, ShoppingCartPageUI.SECOND_PAGE_HEADER_QUANTITY_BY_TEXT, headeText);
	}

	public void openHomePage() {
		waitForElementClickable(driver, ShoppingCartPageUI.HEADER_LOGO);
		clickToElement(driver, ShoppingCartPageUI.HEADER_LOGO);
	}
}
