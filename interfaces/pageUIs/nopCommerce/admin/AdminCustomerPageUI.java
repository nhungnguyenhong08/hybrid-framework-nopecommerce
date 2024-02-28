package pageUIs.nopCommerce.admin;

public class AdminCustomerPageUI {

	public static final String ADD_NEW_BUTTON = "xpath=//a[@class='btn btn-primary' and contains(.,'Add new')]";
	public static final String CUSTOMER_DELETE_ROLE_BUTTON_BY_TEXT = "xpath=//li[contains(@class,'k-button')]/span[text()='Registered']//following-sibling::span[@title='delete']";
	public static final String CUSTOMER_ROLE_TEXT_BOX = "xpath=//div[@class='k-multiselect-wrap k-floatwrap']//input";
	public static final String CUSTOMER_ROLE_VALUE = "xpath=//div[@id='SelectedCustomerRoleIds-list']//div[@class='k-list-scroller']//li[text()='%s']";

	public static final String SUCCESS_MESSAGE = "css=div.alert-success";

	public static final String SEARCH_CUSTOMER_BUTTON = "css=button#search-customers";
	public static final String CUSTOMER_NAME_BY_TEXT = "xpath=//tbody/tr/td[text()='%s']";
	public static final String COLUMN_INDEX_BY_TEXT = "xpath=//th[text()='%s']//preceding-sibling::th";
	public static final String TEXT_VALUE_BY_CUSTOMER_NAME_AND_COLUM_INDEX = "xpath=//tbody//td[text()='%s']/parent::tr/td[%s]";

}
