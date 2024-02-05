package pageUIs.nopCommerce.user;

public class OrderPageUI {
	public static final String ORDER_NUMBER_BY_TEXT = "xpath=//div[@class='title']/strong[text()='%s']";
	public static final String DETAILS_BUTTON_BY_ORDER_NUMBER = "xpath=//div[@class='title']/strong[text()='Order Number: %s']//ancestor::div[@class='section order-item']//button[@class='button-2 order-details-button']";

	public static final String ORDER_NUMBER = "css=div.order-overview div.order-number strong";
	public static final String ORDER_DATE = "css=div.order-overview li.order-date";
	public static final String ORDER_STATUS = "css=div.order-overview li.order-status";
	public static final String ORDER_TOTAL_PRICE = "css=div.order-overview li.order-total";

	public static final String NAME_INFOR_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='name']";
	public static final String EMAIL_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='email']";
	public static final String PHONE_NUMBER_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='phone']";
	public static final String FAX_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='fax']";
	public static final String ADDRESS1_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='address1']";
	public static final String ADDRESS2_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='address2']";
	public static final String COUNTRY_AT_EACH_SECTION = "xpath=//div[@class='%s']//ul/li[@class='country']";

	public static final String PAYMENT_METHOD = "xpath=//li[@class='payment-method']";
	public static final String PAYMENT_STATUS = "xpath=//li[@class='payment-method-status']";
	public static final String SHIPPING_METHOD = "xpath=//li[@class='shipping-method']";
	public static final String SHIPPING_STATUS = "xpath=//li[@class='shipping-status']";

	public static final String PRICE_INFOR_BY_LABEL = "xpath=//label[contains(text(),'%s')]/parent::td//following-sibling::td[1]/span";
	public static final String PRICE_TOTAL_INFOR = "xpath=//label[text()='Order Total:']/parent::td//following-sibling::td[1]/span/strong";

	public static final String PRODUCT_NAME_BY_TEXT = "xpath=//td[@class='product']//a[text()='%s']";
	public static final String ITEM_BY_PRODUCT_NAME_AND_ATRIBUTE = "xpath=//td[@class='product']//a[text()='%s']//ancestor::tr//td/label[contains(text(),'%s')]//following-sibling::*";

	public static final String HOME_LINK = "xpath=//div[@class='header-logo']";
}
