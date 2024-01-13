package pageUIs.nopCommerce.user;

public class CompareProductPageUI {
	public static final String MESSAGE_DISPLAYED = "xpath=//div[@class='page-body']//div";
	public static final String DYNAMIC_PRODUCT_NAME_BY_TEXT = "xpath=//tr[@class='product-name']//a[text()='%s']";
	public static final String REMOVE_BUTTON_BY_PRODUCT_NAME = "xpath=//table//a[text()='%s']//ancestor::tr//preceding-sibling::tr[@class='remove-product']//td[%s]//button";
	public static final String PRICCE_BY_PRODUCT_NAME = "xpath=//table//a[text()='%s']//ancestor::tr//following-sibling::tr[@class='product-price']//td[%s]";
	public static final String CLEAR_LIST_BUTTON = "css=a.clear-list";
	public static final String HEADER_LOGO = "css=div.header-logo";

}
