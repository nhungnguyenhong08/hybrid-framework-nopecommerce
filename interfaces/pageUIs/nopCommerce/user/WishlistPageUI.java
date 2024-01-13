package pageUIs.nopCommerce.user;

public class WishlistPageUI {
	public static final String PAGE_TITLE = "xpath=//div[@class='page wishlist-page']//h1[contains(text(),'%s')]";
	public static final String WISHLIST_SHARE_LINK = "xpath=//div[@class='share-info']//a";
	public static final String HEADER_LOGO = "css=div.header-logo";
	public static final String PRODUCT_IN_WISHLIST = "xpath=//table[@class='cart']//tbody//td[@class='product']/a";
	public static final String DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME = "xpath=//a[text()='%s']//parent::td//following-sibling::td[@class='remove-from-cart']//button";
	public static final String MESSAGE_DISPLAYED = "xpath=//div[@class='page-body']//div";
	public static final String PRODUCT_TABLE = "xpath=//table[@class='cart']";

}
