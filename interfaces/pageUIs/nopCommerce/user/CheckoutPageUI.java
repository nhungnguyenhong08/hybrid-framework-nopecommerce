package pageUIs.nopCommerce.user;

public class CheckoutPageUI {
	public static final String SHIP_TO_SAME_ADDRESS_CHECKBOX = "css=input#ShipToSameAddress";
	public static final String COUNTRY_DROPDOWN_BY_ID = "xpath=//label[text()='Country:']//following-sibling::select[@id='%s']";

	public static final String CONTINUE_BUTTON_BY_ID = "xpath=//div[@id='%s']//button[text()='Continue']";
	public static final String EDIT_BUTTON_BY_ID = "xpath=//div[@id='%s']//button[text()='Edit']";

	public static final String SHIPPING_ADDRESS_METHOD = "css=select#shipping-address-select";

	public static final String ADDRESS_SHIPPING_INFOR = "xpath=//div[@class='info']//td/p[2]";

	public static final String NAME_INFOR_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='name']";
	public static final String EMAIL_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='email']";
	public static final String PHONE_NUMBER_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='phone']";
	public static final String FAX_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='fax']";
	public static final String ADDRESS1_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='address1']";
	public static final String ADDRESS2_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='address2']";
	public static final String COUNTRY_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='country']";

	public static final String PAYMENT_METHOD = "xpath=//li[@class='payment-method']";
	public static final String SHIPPING_METHOD = "xpath=//li[@class='shipping-method']";

	public static final String PRICE_INFOR_BY_LABEL = "xpath=//label[contains(text(),'%s')]/parent::td//following-sibling::td[1]/span";
	public static final String PRICE_TOTAL_INFOR = "xpath=//label[text()='Total:']/parent::td//following-sibling::td[1]/span/strong";

	public static final String CONFIRM_BUTTON = "xpath=//div[@id='confirm-order-buttons-container']//button[text()='Confirm']";
	public static final String PAGE_TITLE_SUCCESS_MESSAGE = "css=div.page-title h1";
	public static final String ORDER_SUCCESS_MESSAGE = "css=div.order-completed div.title strong";
	public static final String ORDER_NUMBER_SUCCESS_MESSAGE = "css=div.order-number strong";
	public static final String COMPLETE_CONTINUE_BUTTON = "css=button.order-completed-continue-button";

	public static final String PRODUCT_NAME_BY_TEXT = "xpath=//td[@class='product']//a[text()='%s']";
	public static final String ITEM_BY_PRODUCT_NAME_AND_ATRIBUTE = "xpath=//td[@class='product']//a[text()='%s']//ancestor::tr//td/label[contains(text(),'%s')]//following-sibling::*";

}
