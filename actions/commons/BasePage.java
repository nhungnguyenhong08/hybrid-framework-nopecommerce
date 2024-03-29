package commons;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.wordpress.AdminDashboardPO;
import pageObject.wordpress.PageGeneratorManager;
import pageObject.wordpress.UserHomePO;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;
import pageUIs.jQuery.uploadFile.BasePageJQueryUI;
import pageUIs.nopCommerce.admin.AdminBasePageUI;
import pageUIs.nopCommerce.user.BasePageNopCommerceUI;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancleAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByPageTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;

			}
		}
	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	// locatorType: id=/ css=/ xpath=/ class=/ name=
	public By getBylocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported!");
		}
		return by;
	}

	public String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getBylocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getBylocator(locatorType));
	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		highlightElement(driver, locatorType);
		WebElement element = this.getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void clearValueInElementByDeleteKey(WebDriver driver, String locatorType) {
		WebElement element = this.getWebElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL), "a", Keys.DELETE);
	}

	public void clearValueInElementByDeleteKey(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = this.getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.sendKeys(Keys.chord(Keys.CONTROL), "a", Keys.DELETE);
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		highlightElement(driver, locatorType);
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String locator, String... params) {
		locator = getDynamicXpath(locator, params);
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		highlightElement(driver, expectedTextItem);
		getWebElement(driver, expectedTextItem).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getBylocator(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000); // 1000 ms = 1s
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		return getWebElement(driver, locatorType).getText();
	}

	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	public String getElemnetCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int Size(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementEnable(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locator, dynamicValues));
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String locator, String... dynamicValues) {
		locator = getDynamicXpath(locator, dynamicValues);
		return getWebElement(driver, locator).isSelected();
	}

	public void switchToFrame(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
		Actions action = new Actions(driver);
		highlightElement(driver, locatorType);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		Actions action = new Actions(driver);
		highlightElement(driver, locatorType, dynamicValues);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();

	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		String highlightStyle = "border: 2px solid red; border-style: dashed;";
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", highlightStyle);
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		String highlightStyle = "border: 2px solid red; border-style: dashed;";
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", highlightStyle);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void scrollToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	public String getElementValueByJsXpath(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath=", "");
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", documnet,null,XPathResult.FIRST_ORRDERED_NODE_TYPE, null).sigleNodeValue).val()");
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return status;
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getBylocator(locatorType)));
	}

	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getBylocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBylocator(locatorType)));
	}

	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBylocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getBylocator(locatorType)));
	}

	// Wait for element undisplayed in DOM or not in DOM and override implicit timeout
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getBylocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getBylocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getBylocator(locatorType)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getBylocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void uploadMultipleFile(WebDriver driver, String... fileNames) {
		// Đường dẫn chưa thư mục uploadFiles
		String filePath = GlobalConstants.getGlobalConstants().getUploadFile();
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
	}

	// Tối ưu ở bài học Level_07_Switch_Page
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		return PageGeneratorManagerNopCommerce.getUserAddressPage(driver);
	}

	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManagerNopCommerce.getUserMyProductReviewPage(driver);
	}

	public UserRewardPointsPageObject openRewardPointsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.REWARD_POINTS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.REWARD_POINTS_LINK);
		return PageGeneratorManagerNopCommerce.getUserRewardPointsPage(driver);
	}

	// Tối ưu ở bài học Level_09_Dynamic_Locator

	// Chỉ dùng 1 trong 2 cách (để tránh nhầm lẫn trong quá trình viết code)
	// Cách 1 (Có ít page)
	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Addresses":
			return PageGeneratorManagerNopCommerce.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManagerNopCommerce.getUserMyProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManagerNopCommerce.getUserRewardPointsPage(driver);
		case "Customer info":
			return PageGeneratorManagerNopCommerce.getUserCustomerInforPage(driver);
		case "Change password":
			return PageGeneratorManagerNopCommerce.getUserChangPasswordPage(driver);
		case "Orders":
			return PageGeneratorManagerNopCommerce.getUserOrderPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area.");
		}
	}

	// Pattern Object
	// Cách 2 (có nhiều page để k phải viết switch case nhiều)
	public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
	}

	/**
	 * Enter to dynamic Textbox by ID
	 * <ul>
	 * <li>Rest Parameter</li>
	 * <li>Textbox by ID</li>
	 * </ul>
	 * 
	 * @author nhungnth
	 * @param driver
	 * @param textboxID
	 * @param value
	 */
	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}

	/**
	 * Click to dynamic button by text
	 * <ul>
	 * <li>Rest Parameter</li>
	 * <li>Button by text</li>
	 * </ul>
	 * 
	 * @author nhungnth
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}

	/**
	 * Select item in dropdown by name
	 * <ul>
	 * <li>Rest Parameter</li>
	 * <li>Dropdown attribute by name</li>
	 * </ul>
	 * 
	 * @author nhungnth
	 * @param driver
	 * @param dropdownAttributeName
	 */
	public void selectToDropdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
		selectItemInDefaultDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
	}

	/**
	 * Click to dynamic radio button by label name
	 * <ul>
	 * <li>Rest Parameter</li>
	 * <li>Checkbox by label name</li>
	 * </ul>
	 * 
	 * @author nhungnth
	 * @param driver
	 * @param radioButtonLabelName
	 */
	public void clickToRadioButtonByLabel(WebDriver driver, String radioButtonLabelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
	}

	/**
	 * Click to dynamic checkbox by label name
	 * 
	 * @param driver
	 * @param checkboxLabelName
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
	}

	/**
	 * Get value in textbox by textboxID
	 * 
	 * @param driver
	 * @param textboxID
	 * @return
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}

	public void openHeaderUpperPageByText(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.HEADER_UPPER_PAGE_BY_TEXT, pageName);
		clickToElement(driver, BasePageNopCommerceUI.HEADER_UPPER_PAGE_BY_TEXT, pageName);
	}

	public void openFooterPageByText(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.FOOTER_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.FOOTER_PAGE, pageName);
	}

	public String getValueOfTextboxByID(WebDriver driver, String textboxID, String attributeName) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, attributeName, textboxID);
	}

	public String getValueSelectedOfDropboxByName(WebDriver driver, String dropdownName) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
		return getSelectedItemDefaultDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
	}

	public boolean isRadioButtonByLabelTextSelected(WebDriver driver, String radioButtonLabelName) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		return isElementSelected(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
	}

	public String getSuccessMessageAtBarNotification(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.SUCCESS_MESSAGE_AT_BAR_NOTIFICATION);
		return getElementText(driver, BasePageNopCommerceUI.SUCCESS_MESSAGE_AT_BAR_NOTIFICATION);
	}

	public void closeBarNotification(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.BAR_NOTIFICATION_CLOSE_BUTTON);
		clickToElement(driver, BasePageNopCommerceUI.BAR_NOTIFICATION_CLOSE_BUTTON);
		sleepInSecond(3);
	}

	public void clickToSublistAtTopMenuByText(WebDriver driver, String menuText, String sublistText) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_TOP_MENU_BY_TEXT, menuText);
		hoverMouseToElement(driver, BasePageNopCommerceUI.DYNAMIC_TOP_MENU_BY_TEXT, menuText);
		sleepInSecond(2);
		clickToElementByJS(driver, BasePageNopCommerceUI.DYNAMIC_TOP_SUB_MENU_BY_TEXT, menuText, sublistText);
	}

	public String getQuantityAtHeaderPage(WebDriver driver, String headeText) {
		waitForElementVisible(driver, BasePageNopCommerceUI.SECOND_PAGE_HEADER_QUANTITY_BY_TEXT, headeText);
		return getElementText(driver, BasePageNopCommerceUI.SECOND_PAGE_HEADER_QUANTITY_BY_TEXT, headeText);
	}

	public void selectItemInDefaultDropdownByTextLabel(WebDriver driver, String dropdownLabel, String textItem) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_TEXT, dropdownLabel);
		selectItemInDefaultDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_TEXT, textItem, dropdownLabel);
	}

	public void selectItemInDefaultDropdownByName(WebDriver driver, String attributeValue, String textItem) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, attributeValue);
		selectItemInDefaultDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, textItem, attributeValue);
	}

	public void uncheckToDefaultCheckboxOrRadioButtonByLabelText(WebDriver driver, String checkboxLabel) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, checkboxLabel);
		uncheckToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, checkboxLabel);
	}

	public void checkToRadioButtonByLabelText(WebDriver driver, String checkboxLabel) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, checkboxLabel);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, checkboxLabel);

	}

	public void hoverToHeaderLinkByText(WebDriver driver, String pageText) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_HEADER_LINK_BY_TEXT, pageText);
		hoverMouseToElement(driver, BasePageNopCommerceUI.DYNAMIC_HEADER_LINK_BY_TEXT, pageText);
		sleepInSecond(3);
	}

	public void OpenToHeaderLinkByText(WebDriver driver, String pageText) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_HEADER_LINK_BY_TEXT, pageText);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_HEADER_LINK_BY_TEXT, pageText);

	}

	public String getDescriptionInMiniShoppingCartByAttribute(WebDriver driver, String attributeValue) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_DESCRIPTION_OF_PRODUCT_BY_ATTRIBUTE, attributeValue);
		return getElementText(driver, BasePageNopCommerceUI.DYNAMIC_DESCRIPTION_OF_PRODUCT_BY_ATTRIBUTE, attributeValue);
	}

	public String getInforProductByProductName(WebDriver driver, String productName) {
		waitForElementVisible(driver, BasePageNopCommerceUI.INFOR_PRODUCT_BY_PRODUCT_NAME, productName);
		return getElementText(driver, BasePageNopCommerceUI.INFOR_PRODUCT_BY_PRODUCT_NAME, productName);
	}

	public String getInforProductByProductNameAndAttributeValue(WebDriver driver, String productName, String attributeValue) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_PRODUCT_CHARACTERISTIC_BY_PRODUCT_NAME_AND_ATTRIBUTE, productName, attributeValue);
		return getElementText(driver, BasePageNopCommerceUI.DYNAMIC_PRODUCT_CHARACTERISTIC_BY_PRODUCT_NAME_AND_ATTRIBUTE, productName, attributeValue);
	}

	public String getSubTotalPriceByAttributeValue(WebDriver driver, String attributeValue) {
		waitForElementVisible(driver, BasePageNopCommerceUI.SUB_TOTAL_PRICE_BY_ATTRIBUTE, attributeValue);
		return getElementText(driver, BasePageNopCommerceUI.SUB_TOTAL_PRICE_BY_ATTRIBUTE, attributeValue);
	}

	// Switch role Level_08_Switch_Role
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER_PAGE);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER_PAGE);
		return PageGeneratorManagerNopCommerce.getUserHomePage(driver);
	}

	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN_PAGE);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN_PAGE);
		return PageGeneratorManagerNopCommerce.getAdminLoginPage(driver);
	}

	public UserHomePO openEndUserSite(WebDriver driver, String userUrl) {
		openPageUrl(driver, userUrl);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public AdminDashboardPO openAdminSite(WebDriver driver, String adminUrl) {
		openPageUrl(driver, adminUrl);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}

	public int getQuantityItemInTableDisplayed(WebDriver driver) {
		waitForAllElementVisible(driver, AdminBasePageUI.SEARCH_ITEM_LIST);
		List<WebElement> searchProductsList = getListWebElement(driver, AdminBasePageUI.SEARCH_ITEM_LIST);
		System.out.println(searchProductsList.size());
		return searchProductsList.size();
	}

	public void clickToSubMenuByText(WebDriver driver, String menuText, String subMenuText) {
		if (isElementUndisplayed(driver, AdminBasePageUI.SUB_MENU_DISPLAYED_BY_MENUTEXT_AND_BY_TEXT, menuText, subMenuText)) {
			waitForElementClickable(driver, AdminBasePageUI.MENU_LINK_BY_TEXT, menuText);
			clickToElementByJS(driver, AdminBasePageUI.MENU_LINK_BY_TEXT, menuText);
			sleepInSecond(1);
		}
		waitForElementClickable(driver, AdminBasePageUI.SUB_MENU_DISPLAYED_BY_MENUTEXT_AND_BY_TEXT, menuText, subMenuText);
		clickToElement(driver, AdminBasePageUI.SUB_MENU_DISPLAYED_BY_MENUTEXT_AND_BY_TEXT, menuText, subMenuText);

	}

	private long longTimeout = GlobalConstants.getGlobalConstants().getLongTimeout();
	private long shortTimeout = GlobalConstants.getGlobalConstants().getShortTimeout();
}
