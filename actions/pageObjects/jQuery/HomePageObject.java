package pageObjects.jQuery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

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
		return isElementDisplay(driver, HomePageUI.PAGINAION_PAGE_ACTIVED_BY_NUMBER, pageNumber);

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
}
