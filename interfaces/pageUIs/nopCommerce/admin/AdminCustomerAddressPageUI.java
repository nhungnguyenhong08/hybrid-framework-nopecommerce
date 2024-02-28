package pageUIs.nopCommerce.admin;

public class AdminCustomerAddressPageUI {
	public static final String SUCCESS_MESSAGE = "css=div.alert-success";
	public static final String BACK_TO_CUSTOMER_DETAIL_LINK = "xpath=//a[text()='back to customer details']";
	public static final String DYNAMIC_CUSTOMER_ADDRESS_BY_TEXTLABEL = "xpath=//label[text()='%s']//ancestor::div[@class='form-group row']//div//input";
	public static final String ADDRESS_COUNTRY_ITEM_SELECTED = "xpath=//select[@id='Address_CountryId']/option[@selected='selected']";
	public static final String STATE_OR_PROVINCE_ITEM_SELECTED = "xpath=//select[@id='Address_StateProvinceId']/option[@selected='selected']";
	public static final String DYNAMIC_BUTTON_BY_NAME = "xpath=//button[@name='%s']";

}
