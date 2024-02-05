package pageUIs.nopCommerce.user;

public class ShoppingCartPageUI {
	public static final String PAGE_TITLE = "xpath=//div[@class='page-title']//h1[contains(text(),'%s')]";
	public static final String HEADER_LOGO = "css=div.header-logo";
	public static final String EDIT_LINK = "css=div.edit-item a";
	public static final String REMOVE_BUTTON = "xpath=//a[text()='%s']//parent::td//following-sibling::td[@class='remove-from-cart']//button";
	public static final String MESSAGE_AT_SHOPPING_CART = "xpath=//div[@class='order-summary-content']//div";
	public static final String PRODUCT_NAME_IN_LIST = "xpath=//td[@class='product']//a[text()='%s']";
	public static final String PRODUCT_QUANTITY_BY_PRODUCT_NAME = "xpath=//a[text()='%s']//parent::td//following-sibling::td[@class='quantity']//input";
	public static final String PRODUCT_PRICE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']//parent::td//following-sibling::td[@class='subtotal']//span";
	public static final String GIFT_WRAPING_DROP_DOWN_BY_TEXT = "xpath=//label[contains(text(),'Gift wrapping')]/parent::dt/following-sibling::dd/select";
	public static final String TERM_OF_SERVICE_CHECKBOX = "xpath=//div[@class='terms-of-service']//input";

}
