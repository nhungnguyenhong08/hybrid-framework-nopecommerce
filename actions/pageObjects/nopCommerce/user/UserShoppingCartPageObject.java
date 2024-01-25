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
}
