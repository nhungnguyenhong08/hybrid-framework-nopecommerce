package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.WishlistPageUI;

public class UserWishlistPageObject extends BasePage {
	private WebDriver driver;

	public UserWishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRequestedPageDisplayed(WebDriver driver, String pageTitle) {
		waitForElementVisible(driver, WishlistPageUI.PAGE_TITLE, pageTitle);
		return isElementDisplayed(driver, WishlistPageUI.PAGE_TITLE, pageTitle);
	}

	public void clickWishlistShareLink() {
		waitForElementClickable(driver, WishlistPageUI.WISHLIST_SHARE_LINK);
		clickToElement(driver, WishlistPageUI.WISHLIST_SHARE_LINK);
	}

	public void openHomePage() {
		waitForElementClickable(driver, WishlistPageUI.HEADER_LOGO);
		clickToElement(driver, WishlistPageUI.HEADER_LOGO);
	}

	public void clickToRemoveProductButton(WebDriver driver, String productName) {
		waitForElementClickable(driver, WishlistPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, WishlistPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
	}

	public String getMessageDiplayed() {
		waitForElementVisible(driver, WishlistPageUI.MESSAGE_DISPLAYED);
		return getElementText(driver, WishlistPageUI.MESSAGE_DISPLAYED);
	}

	public boolean isProductsListEmpty(WebDriver driver) {
		waitForElementInvisible(driver, WishlistPageUI.PRODUCT_TABLE);
		return isElementUndisplayed(driver, WishlistPageUI.PRODUCT_TABLE);
	}

	public String getProductInWishlist(WebDriver driver) {
		waitForElementVisible(driver, WishlistPageUI.PRODUCT_IN_WISHLIST);
		return getElementText(driver, WishlistPageUI.PRODUCT_IN_WISHLIST);
	}

}
