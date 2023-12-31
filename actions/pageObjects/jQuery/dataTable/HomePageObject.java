package pageObjects.jQuery.dataTable;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.dataTable.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPagingByPageNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUI.PAGINAION_PAGE_BY_NUMBER, pageNumber);
		clickToElementByJS(driver, HomePageUI.PAGINAION_PAGE_BY_NUMBER, pageNumber);
	}

	public void enterToHeaderTextboxByLable(String headerLable, String value) {
		waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABLE, headerLable);
		sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABLE, value, headerLable);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABLE, Keys.ENTER, headerLable);

	}

	public boolean isPageNumberActived(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGINAION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
		return isElementDisplayed(driver, HomePageUI.PAGINAION_PAGE_ACTIVED_BY_NUMBER, pageNumber);

	}

	public List<String> getValueEachRowAtAllPage() {
		int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGINATION);
		System.out.println("Total size = " + totalPage);

		List<String> allRowValueAllPage = new ArrayList<String>();

		// Duyệt qua tất cả các page
		for (int index = 1; index <= totalPage; index++) {
			clickToElement(driver, HomePageUI.PAGINATION_PAGE_INDEX, String.valueOf(index));
			sleepInSecond(1);

			// Get text của all cột country mỗi page đưa vào List
			List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_ROW_COUNTRY_EACH_PAGE);
			for (WebElement eachRow : allRowElementEachPage) {
				allRowValueAllPage.add(eachRow.getText());
			}
		}
		// In tất cả các giá trị row của tất ca các page
		for (String value : allRowValueAllPage) {
			/* System.out.println("---------------------------------------"); */
			System.out.println(value);
		}

		return allRowValueAllPage;
	}

	public void enterToTextboxByColumnNameAtRowNumber(String columnName, String rowNumber, String valueEnter) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, valueEnter, rowNumber, String.valueOf(columnIndex));
	}

	public void selectDropdownByColumnNameAtRowNumber(String columnName, String rowNumber, String valueSelect) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

		waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		selectItemInDefaultDropdown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, valueSelect, rowNumber, String.valueOf(columnIndex));

	}

	public void clickToLoadDataButton() {
		waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
		clickToElementByJS(driver, HomePageUI.LOAD_DATA_BUTTON);

	}

	public void checkToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		checkToDefaultCheckboxOrRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));

	}

	public void unCheckToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		uncheckToDefaultCheckboxOrRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));

	}

	public void clickToIconByRowNumber(String rowNumber, String iconName) {
		waitForElementClickable(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowNumber, iconName);
		clickToElement(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowNumber, iconName);
	}
}
