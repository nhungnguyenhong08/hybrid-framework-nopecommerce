package pageUIs.nopCommerce.admin;

public class AdminBasePageUI {

	public static final String SEARCH_ITEM_LIST = "xpath=//tbody//input[@class='checkboxGroups']";
	public static final String MENU_LINK_BY_TEXT = "xpath=//ul[@role='menu']/li/a/p[contains(string(),'%s')]";
	public static final String SUB_MENU_DISPLAYED_BY_MENUTEXT_AND_BY_TEXT = "xpath=//li[contains(@class,'nav-item has-treeview')]//p[contains(text(),'%s')]//parent::a//following-sibling::ul//p[contains(text(),'%s')]";

}
