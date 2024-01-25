package pageObjects.nopCommerce.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.nopCommerce.user.ProductPageUI;

public class UserProductPageObject extends BasePage {
	private WebDriver driver;

	public UserProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickProductToSeeDetailByText(WebDriver driver, String productName) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_TITLE_BY_TEXT, productName);
		clickToElement(driver, ProductPageUI.PRODUCT_TITLE_BY_TEXT, productName);
	}

	public void clickToAddYourReviewLink() {
		waitForElementClickable(driver, ProductPageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver, ProductPageUI.ADD_YOUR_REVIEW_LINK);
	}

	public void inputToReviewTextBox(WebDriver driver, String reviewText) {
		waitForElementVisible(driver, ProductPageUI.REVIEW_TEXTAREA);
		sendkeyToElement(driver, ProductPageUI.REVIEW_TEXTAREA, reviewText);
	}

	public void AddProductRatingByValue(WebDriver driver, String reviewRating) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_RATING, reviewRating);
		clickToElement(driver, ProductPageUI.PRODUCT_RATING, reviewRating);
	}

	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, ProductPageUI.REVIEW_RESULT);
		return isElementDisplayed(driver, ProductPageUI.REVIEW_RESULT);
	}

	public String getProductReviewPage() {
		waitForElementVisible(driver, ProductPageUI.REVIEW_PAGE_HEADER);
		return getElementText(driver, ProductPageUI.REVIEW_PAGE_HEADER);
	}

	public boolean isProductNameSortAscending() {
		ArrayList<String> productUIList = new ArrayList<String>();
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.LIST_PRODUCT_NAME_TEXT);
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		Collections.sort(productSortList);
		return productSortList.equals(productUIList);
	}

	public boolean isProductNameSortDescending() {
		ArrayList<String> productUIList = new ArrayList<String>();
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.LIST_PRODUCT_NAME_TEXT);
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		Collections.sort(productSortList);
		Collections.reverse(productSortList);
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortAscending() {
		ArrayList<String> productUIList = new ArrayList<String>();
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.LIST_PRODUCT_PRICE_TEXT);
		for (WebElement productPrice : productPriceText) {
			productUIList.add(productPrice.getText().replace("$", ""));
		}
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		Collections.sort(productSortList);
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortDesscending() {
		ArrayList<String> productUIList = new ArrayList<String>();
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.LIST_PRODUCT_PRICE_TEXT);
		for (WebElement productPrice : productPriceText) {
			productUIList.add(productPrice.getText().replace("$", ""));
		}
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		Collections.sort(productSortList);
		Collections.reverse(productSortList);
		return productSortList.equals(productUIList);
	}

	public boolean isProductNumberDisplayAsExpected(WebDriver driver, int productNumerExpected) {
		waitForAllElementVisible(driver, ProductPageUI.TOTAL_SEARCH_PRODUCT);
		int numberProduct = getElementSize(driver, ProductPageUI.TOTAL_SEARCH_PRODUCT);
		if (numberProduct > productNumerExpected) {
			return false;
		}
		return true;
	}

	public boolean isPaginationDisplayed(WebDriver driver) {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_PAGINATION);
		return isElementDisplayed(driver, ProductPageUI.PRODUCT_PAGINATION);
	}

	public boolean isPaginationIconDisplay(WebDriver driver, String iconText) {
		waitForElementVisible(driver, ProductPageUI.DYNAMIC_PAGINATION_ICON_AND_NUMBERPAGE_BY_TEXT, iconText);
		return isElementDisplayed(driver, ProductPageUI.DYNAMIC_PAGINATION_ICON_AND_NUMBERPAGE_BY_TEXT, iconText);
	}

	public void clickToPaginationLink(WebDriver driver, String pageNumber) {
		waitForElementClickable(driver, ProductPageUI.DYNAMIC_PAGINATION_ICON_AND_NUMBERPAGE_BY_TEXT, pageNumber);
		clickToElement(driver, ProductPageUI.DYNAMIC_PAGINATION_ICON_AND_NUMBERPAGE_BY_TEXT, pageNumber);
	}

	public boolean isPaginationUnDisplayed(WebDriver driver) {
		waitForElementInvisible(driver, ProductPageUI.PRODUCT_PAGINATION);
		return isElementUndisplayed(driver, ProductPageUI.PRODUCT_PAGINATION);
	}

	public void clickToOverviewButtonByText(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, ProductPageUI.DYNAMIC_OVER_VIEW_BUTTON_BY_TEXT, buttonName);
		clickToElement(driver, ProductPageUI.DYNAMIC_OVER_VIEW_BUTTON_BY_TEXT, buttonName);
	}

	public void openWishlistPageByLink(WebDriver driver) {
		waitForElementClickable(driver, ProductPageUI.WISH_LIST_LINK_AT_HEADER);
		clickToElement(driver, ProductPageUI.WISH_LIST_LINK_AT_HEADER);
	}

	public void clickToProductToSeeDetailByOrder(WebDriver driver, String productOrder) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_BY_ORDER, productOrder);
		clickToElement(driver, ProductPageUI.PRODUCT_BY_ORDER, productOrder);
	}

	public String getValueOfViewedProduct(WebDriver driver2) {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_DETAIL_NAME);
		return getElementText(driver, ProductPageUI.PRODUCT_DETAIL_NAME);
	}

	public String getPriceOfProduct(WebDriver driver) {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_PRICE);
		return getElementText(driver, ProductPageUI.PRODUCT_PRICE);
	}

	public void enterToProductQuantityTextbox(WebDriver driver, String productQuantity) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_QUANTITY_TEXT_BOX);
		sendkeyToElement(driver, ProductPageUI.PRODUCT_QUANTITY_TEXT_BOX, productQuantity);
	}

}
