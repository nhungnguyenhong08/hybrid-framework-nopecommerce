package pageUIs.nopCommerce.user;

public class ProductPageUI {
	public static final String PRODUCT_TITLE_BY_TEXT = "xpath=//div[@class='item-grid']//a[text()='%s']";
	public static final String ADD_YOUR_REVIEW_LINK = "xpath=//div[@class='product-reviews-overview']//a[text()='Add your review']";
	public static final String REVIEW_PAGE_HEADER = "xpath=//div[@class='page-title']/h1";
	public static final String REVIEW_TEXTAREA = "xpath=//textarea[@id='AddProductReview_ReviewText']";
	public static final String PRODUCT_RATING = "xpath=//div[@class='rating-options']/input[@value='%s']";
	public static final String REVIEW_RESULT = "xpath=//div[contains(text(), 'Product review is successfully added.')]";
	public static final String LIST_PRODUCT_NAME_TEXT = "css=h2.product-title a";
	public static final String LIST_PRODUCT_PRICE_TEXT = "css=div.prices span";
	public static final String TOTAL_SEARCH_PRODUCT = "xpath=//div[@class='product-grid']//div[@class='item-box']";
	public static final String PRODUCT_PAGINATION = "css=div.products-wrapper div.pager";
	public static final String DYNAMIC_PAGINATION_ICON_AND_NUMBERPAGE_BY_TEXT = "xpath=//div[@class='pager']//li/a[text()='%s']";
}
