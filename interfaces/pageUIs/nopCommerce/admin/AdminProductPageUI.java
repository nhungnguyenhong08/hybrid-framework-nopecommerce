package pageUIs.nopCommerce.admin;

public class AdminProductPageUI {

	public static final String SEARCH_PRODUCT_BUTTON = "css=button#search-products";
	public static final String PRODUCT_NAME_BY_TEXT = "xpath=//td[contains(@class,'text-center')]//following-sibling::td[text()='%s']";
	public static final String COLUMN_INDEX_BY_TEXT = "xpath=//table//th[text()='%s']//preceding-sibling::th";
	public static final String TEXT_VALUE_BY_PRODUCT_NAME_AND_COLUM_INDEX = "xpath=//td[contains(@class,'text-center')]//following-sibling::td[text()='%s']/parent::tr/td[%s]";
	public static final String PRODUCT_CHECK_PUBLIC_ICON_BY_PRODUCT_NAME_AND_COLUM_INDEX = "xpath=//td[contains(@class,'text-center')]//following-sibling::td[text()='%s']/parent::tr/td[%s]/i";
	public static final String DYNAMIC_DROPDOWN_BY_TEXTLABEL = "xpath=//label[contains(text(),'%s')]//ancestor::div[@class='form-group row']//div/select";
	public static final String SEARCH_SUBCATEGORIES_CHECKBOX = "xpath=//label[contains(text(),'Search subcategories')]//ancestor::div[@class='form-group row']//div/input";
	public static final String PRODUCT_MESSAGE_DATA_TABEL_BY_TEXT = "xpath=//tbody//td[text()='%s']";

}
