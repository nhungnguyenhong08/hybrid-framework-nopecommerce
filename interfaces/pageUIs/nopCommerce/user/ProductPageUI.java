package pageUIs.nopCommerce.user;

public class ProductPageUI {
	public static final String PRODUCT_TITLE_BY_TEXT = "xpath=//div[@class='item-grid']//a[text()='%s']";
	public static final String ADD_YOUR_REVIEW_LINK = "xpath=//div[@class='product-reviews-overview']//a[text()='Add your review']";
	public static final String REVIEW_PAGE_HEADER = "xpath=//div[@class='page-title']/h1";
	public static final String REVIEW_TEXTAREA = "xpath=//textarea[@id='AddProductReview_ReviewText']";
	public static final String PRODUCT_RATING = "xpath=//div[@class='rating-options']/input[@value='%s']";
	public static final String REVIEW_RESULT = "xpath=//div[contains(text(), 'Product review is successfully added.')]";

}
