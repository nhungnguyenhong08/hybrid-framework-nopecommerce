package pageUIs.nopCommerce.user;

public class SearchPageUI {
	public static final String SEARCH_BUTTON = "xpath=//div[@class='page search-page']//button[text()='Search']";
	public static final String SEARCH_ERROR_MESSAGE = "xpath=//div[@class='search-results']//div[@class='products-wrapper']/div";
	public static final String TOTAL_SEARCH_PRODUCT = "xpath=//div[@class='product-grid']//div[@class='item-box']";
	public static final String DYNAMIC_SEARCH_PRODUCT_NAME_BY_TEXT = "xpath=//div[@class='item-box']//div[@class='details']//a[text()='%s']";

}
