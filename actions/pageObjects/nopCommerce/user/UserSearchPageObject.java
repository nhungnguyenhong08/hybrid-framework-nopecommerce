package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.SearchPageUI;

public class UserSearchPageObject extends BasePage {
	private WebDriver driver;

	public UserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON);
	}

	public String getSearchErrorMessage() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_ERROR_MESSAGE);
		return getElementText(driver, SearchPageUI.SEARCH_ERROR_MESSAGE);
	}

	public boolean isNumberOfSearchResultDisplayed(WebDriver driver, int numberSearchProductExpected) {
		waitForElementVisible(driver, SearchPageUI.TOTAL_SEARCH_PRODUCT);
		int numberProductResult = getElementSize(driver, SearchPageUI.TOTAL_SEARCH_PRODUCT);
		if (numberProductResult == numberSearchProductExpected) {
		}
		System.out.println(numberProductResult);
		return true;
	}

	public String getSearchProductNameResultByText(String productName) {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_SEARCH_PRODUCT_NAME_BY_TEXT, productName);
		return getElementText(driver, SearchPageUI.DYNAMIC_SEARCH_PRODUCT_NAME_BY_TEXT, productName);
	}

	public boolean isProductNameResultDisplayedByText(String productName) {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_SEARCH_PRODUCT_NAME_BY_TEXT, productName);
		return isElementDisplayed(driver, SearchPageUI.DYNAMIC_SEARCH_PRODUCT_NAME_BY_TEXT, productName);
	}

	public int getNumberProductResult() {
		waitForAllElementVisible(driver, SearchPageUI.TOTAL_SEARCH_PRODUCT);
		return getElementSize(driver, SearchPageUI.TOTAL_SEARCH_PRODUCT);
	}

}
