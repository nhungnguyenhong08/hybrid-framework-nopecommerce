package pageUIs.nopCommerce.admin;

public class AdminCustomerDetailPageUI {

	public static final String CUSTOMER_DELETE_ROLE_BUTTON_BY_TEXT = "xpath=//li[contains(@class,'k-button')]/span[text()='Registered']//following-sibling::span[@title='delete']";
	public static final String CUSTOMER_ROLE_PARENT_DROPDOWN = "xpath=//div[@class='input-group-append input-group-required']//div[@class='k-multiselect-wrap k-floatwrap']//input";

	public static final String CUSTOMER_ROLE_VALUE = "xpath=//div[@id='SelectedCustomerRoleIds-list']//div[@class='k-list-scroller']//li[text()='%s']";
	public static final String SAVE_AND_CONTINUE_EDIT_BUTTON = "name=save-continue";
	public static final String SUCCESS_MESSAGE = "css=div.alert-success";
	public static final String DYNAMIC_CUSTOMER_INFOR_BY_TEXTLABEL = "xpath=//label[text()='%s']//ancestor::div[@class='form-group row']//div//input";
	public static final String CUSTOMER_GENDER_RADIO_BY_TEXT_LABEL = "xpath=//label[contains(text(),'%s')]/preceding-sibling::input";
	public static final String ACTIVE_CHECKBOX = "xpath=//label[contains(text(),'Active')]/ancestor::div[@class='form-group row']//div/input";
	public static final String ADMIN_COMMENT_TEXTBOX = "xpath=//label[contains(text(),'Admin comment')]/ancestor::div[@class='form-group row']//div/textarea";

	public static final String BACK_TO_CUSTOMER_LIST_LINK = "xpath=//a[text()='back to customer list']";
	public static final String SAVE_BUTTON = "name=save";
	public static final String EMAIL_ADDRESS_BY_TEXT = "xpath=//tbody/tr/td[text()='%s']";
	public static final String COLUMN_INDEX_BY_TEXT = "xpath=//th[text()='%s']//preceding-sibling::th";
	public static final String TEXT_VALUE_BY_EMAIL_ADDRESS_AND_COLUMN_INDEX = "xpath=//tbody//td[text()='%s']/parent::tr/td[%s]";
	public static final String DYNAMIC_CARD_BY_TEXT = "xpath=//div[text()='%s']";
	public static final String ICON_BY_EMAIL_ADDRESS_AND_COLUMN_INDEX = "xpath=//tbody//td[text()='%s']/parent::tr/td[%s]/a";
	public static final String MINUS_ICON_AT_CARD_BY_TEXT = "xpath=//div[@class='card-title' and text()='%s']//following-sibling::div//i[@class='fa toggle-icon fa-minus']";
	public static final String MESSAGE_AT_TABLE_BY_NAME_CARD_AND_TEXT = "xpath=//div[text()='%s']//parent::div//following-sibling::div[@class='card-body']//td[text()='%s']";

}
