package com.cisco.cstg.autotools.tests.pages.UI3;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.pages.PageConstants;

public class EFUI3Page extends BasePage {

	/**
	 * Page related configuration.
	 */
	protected static Configuration pageConfig = new ComponentConfiguration(EFUI3Page.class);
	protected static final String SEARCH_BUTTON_ID = pageConfig.getStringValue(PageConstants.SEARCH_BUTTON_ID);
	protected static final String DROPDOWN_BUTTON_CLASSNAME = pageConfig.getStringValue(PageConstants.DROPDOWN_BUTTON_CLASSNAME);
	protected static final String ON_MOUSE_OVER_DROPDOWN_BUTTON_CLASSNAME = pageConfig.getStringValue(PageConstants.ON_MOUSE_OVER_DROPDOWN_BUTTON_CLASSNAME);

	protected static final String SERVICE_PROGRAM_DROPDOWN_XPATH = pageConfig.getStringValue(PageConstants.SERVICE_PROGRAM_DROPDOWN_XPATH);
	protected static final String SERVICE_PROGRAM_POPUP_ID = pageConfig.getStringValue(PageConstants.SERVICE_PROGRAM_POPUP_ID);
	protected static final String SERVICE_PROGRAM_SELECTION_VALUE = pageConfig.getStringValue(PageConstants.SERVICE_PROGRAM_SELECTION_VALUE);
	protected static final int SERVICE_PROGRAM_DROPDOWN_LOCATION = pageConfig.getIntegerValue(PageConstants.SERVICE_PROGRAM_DROPDOWN_LOCATION);

	protected static final String SERVICE_PROGRAM_ROLE_DROPDOWN_XPATH = pageConfig.getStringValue(PageConstants.SERVICE_PROGRAM_ROLE_DROPDOWN_XPATH);
	protected static final String SERVICE_PROGRAM_ROLE_POPUP_ID = pageConfig.getStringValue(PageConstants.SERVICE_PROGRAM_ROLE_POPUP_ID);
	protected static final String SERVICE_PROGRAM_ROLE_SELECTION_VALUE = pageConfig.getStringValue(PageConstants.SERVICE_PROGRAM_ROLE_SELECTION_VALUE);
	protected static final int SERVICE_PROGRAM_ROLE_DROPDOWN_LOCATION = pageConfig.getIntegerValue(PageConstants.SERVICE_PROGRAM_ROLE_DROPDOWN_LOCATION);

	protected static final String COMPANY_SERVICE_PROGRAM_DROPDOWN_XPATH = pageConfig.getStringValue(PageConstants.COMPANY_SERVICE_PROGRAM_DROPDOWN_XPATH);
	protected static final String COMPANY_SERVICE_PROGRAM_POPUP_ID = pageConfig.getStringValue(PageConstants.COMPANY_SERVICE_PROGRAM_POPUP_ID);
	protected static final String COMPANY_SERVICE_PROGRAM_SELECTION_VALUE = pageConfig.getStringValue(PageConstants.COMPANY_SERVICE_PROGRAM_SELECTION_VALUE);
	protected static final int COMPANY_SERVICE_PROGRAM_DROPDOWN_LOCATION = pageConfig.getIntegerValue(PageConstants.COMPANY_SERVICE_PROGRAM_DROPDOWN_LOCATION);

	protected static final String PARTY_ID_DROPDOWN_XPATH = pageConfig.getStringValue(PageConstants.PARTY_ID_DROPDOWN_XPATH);
	protected static final String PARTY_ID_POPUP_ID = pageConfig.getStringValue(PageConstants.PARTY_ID_POPUP_ID);
	protected static final String PARTY_ID_SELECTION_VALUE = pageConfig.getStringValue(PageConstants.PARTY_ID_SELECTION_VALUE);
	protected static final int PARTY_ID_DROPDOWN_LOCATION = pageConfig.getIntegerValue(PageConstants.PARTY_ID_DROPDOWN_LOCATION);

	protected static final Logger logger = LoggerFactory.getLogger(EFUI3Page.class);

	@CacheLookup
	@FindBy(how = How.ID, using = "xwt_widget_form_TextButton_2")
	private WebElement searchButton;

	public EFUI3Page(WebDriver driver) {
		super(driver);
	}

	/**
	 * Private method that will click a given drop down and select the desired
	 * value from the drop down.
	 * 
	 * @param dropDownLocation
	 * @param PopUpId
	 * @param DropDownXpath
	 * @param selectionValue
	 */
	private void clickDropDownAndSelectValue(int dropDownLocation,
			String PopUpId, String DropDownXpath, String selectionValue) {
		try {
			WebDriverWait pageLoaded = new WebDriverWait(driver, 30);
			// Find the down arrow element for the drop downs in the page.
			WebElement webElement = getDesiredDropDown(dropDownLocation);

			Actions builder = new Actions(driver);
			builder.moveToElement(webElement).perform();
			pageLoaded.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(ON_MOUSE_OVER_DROPDOWN_BUTTON_CLASSNAME)));
			WebElement subLink = driver.findElement(By.className(ON_MOUSE_OVER_DROPDOWN_BUTTON_CLASSNAME));
			builder.moveToElement(subLink).perform();
			logger.debug("About to click the drop down arrow");
			builder.click().perform();
			pageLoaded.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(PopUpId)));
			logger.debug("POP UP BOX OPENED");
			subLink = driver.findElement(By.id(PopUpId));
			builder.moveToElement(subLink).build().perform();
			List<WebElement> drpListItems = driver.findElements(By.xpath(DropDownXpath));
			logger.debug("Number of menu items: " + drpListItems.size());
			for (WebElement temp : drpListItems) {
				logger.debug("The text value of element: " + temp.getText());
				if (temp.getText().trim().equals(selectionValue)) {
					temp.click();
					logger.debug("CLICKED THE CHOICE");
					break;
				}
			}
			logger.debug("SEARCHING FOR SEARCH BUTTON....");
			pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id(SEARCH_BUTTON_ID)));
			logger.debug("FOUND THE SEARCH BUTTON IN UI#3");
		} catch (TimeoutException e) {
			logger.debug("TIME OUT IN UI3");
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			e.printStackTrace(printWriter);
			logger.debug(result.toString());
		} catch (UnexpectedTagNameException e) {
			logger.debug("NOT A SELECT TAG");
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			e.printStackTrace(printWriter);
			logger.debug(result.toString());
		} catch (Exception e) {
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			e.printStackTrace(printWriter);
			logger.debug(result.toString());
		}
	}

	/**
	 * Private Method that will return the desired drop down element out of all
	 * drop downs present in this page.
	 * 
	 * @param desiredDropDownLocationOrder
	 * @return
	 */
	private WebElement getDesiredDropDown(int desiredDropDownLocationOrder) {

		logger.debug("IN GET DESIRED DROP DOWN METHOD");
		List<WebElement> dropDownElements = new ArrayList<WebElement>();
		// Find the down arrow element for the drop downs in the page.
		List<WebElement> elements = driver.findElements(By.className(DROPDOWN_BUTTON_CLASSNAME));
		if (elements != null) {
			// logger.debug("No of elements: "+elements.size());
			for (WebElement webElement : elements) {
				if (webElement.isDisplayed() && webElement.isEnabled()) {
					// logger.debug("TAG NAME: "+webElement.getTagName());
					// logger.debug("LOCATION: "+webElement.getLocation());
					// logger.debug("LOCATION Y AXIS: "+webElement.getLocation().getY());
					// logger.debug("DISPLAYED: "+webElement.isDisplayed());
					// logger.debug("ENABLED: "+webElement.isEnabled());
					// logger.debug("SELECTED: "+webElement.isSelected());
					dropDownElements.add(webElement);
				}
			}
		}

		return dropDownElements.get(desiredDropDownLocationOrder - 1);
	}

	/**
	 * Get to the EFUI#3 page, since the authetication is basic the Firefox
	 * profile that is loaded should have the User id and pwd stored already.
	 * This will take to the EFUI#3 page.
	 * 
	 * @param baseUrl
	 * @return
	 */
	public EFUI3Page loginToEFUI3(String baseUrl) {
		try {
			driver.get(baseUrl);
			logger.debug("Logging into UI3");
			WebDriverWait pageLoaded = new WebDriverWait(driver, 30);
			logger.debug("SEARCHING FOR SEARCH BUTTON....");
			pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id(SEARCH_BUTTON_ID)));
			logger.debug("FOUND THE SEARCH BUTTON IN UI#3");
		} catch (TimeoutException exp) {
			logger.debug("TIME OUT IN UI3");
		}
		return PageFactory.initElements(driver, EFUI3Page.class);
	}

	public EFUI3Page selectServiceProgram() {
		// Find the down arrow element for the drop downs in the page.
		logger.debug("IN SELECT SERVICE PROGRAM METHOD");
		clickDropDownAndSelectValue(SERVICE_PROGRAM_DROPDOWN_LOCATION, SERVICE_PROGRAM_POPUP_ID, 
				SERVICE_PROGRAM_DROPDOWN_XPATH,	SERVICE_PROGRAM_SELECTION_VALUE);
		return PageFactory.initElements(driver, EFUI3Page.class);
	}

	public EFUI3Page selectServiceProgramRole() {
		// Find the down arrow element for the drop downs in the page.
		clickDropDownAndSelectValue(SERVICE_PROGRAM_ROLE_DROPDOWN_LOCATION,	SERVICE_PROGRAM_ROLE_POPUP_ID,
				SERVICE_PROGRAM_ROLE_DROPDOWN_XPATH, SERVICE_PROGRAM_ROLE_SELECTION_VALUE);
		return PageFactory.initElements(driver, EFUI3Page.class);
	}

	public EFUI3Page selectCompanyServiceProgram() {
		// Find the down arrow element for the drop downs in the page.
		clickDropDownAndSelectValue(COMPANY_SERVICE_PROGRAM_DROPDOWN_LOCATION,
				COMPANY_SERVICE_PROGRAM_POPUP_ID, COMPANY_SERVICE_PROGRAM_DROPDOWN_XPATH, COMPANY_SERVICE_PROGRAM_SELECTION_VALUE);
		return PageFactory.initElements(driver, EFUI3Page.class);
	}

	public EFUI3Page selectPartyId() {
		// Find the down arrow element for the drop downs in the page.
		clickDropDownAndSelectValue(PARTY_ID_DROPDOWN_LOCATION,
				PARTY_ID_POPUP_ID, PARTY_ID_DROPDOWN_XPATH,	PARTY_ID_SELECTION_VALUE);
		return PageFactory.initElements(driver, EFUI3Page.class);
	}

	public EFUI3Page doSearch() {
		WebElement searchButton = driver.findElement(By.id(SEARCH_BUTTON_ID));
		searchButton.click();
		// wait until the page gets loaded
		WebDriverWait pageLoaded = new WebDriverWait(driver, 30);
		pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id(SEARCH_BUTTON_ID)));
		return PageFactory.initElements(driver, EFUI3Page.class);
	}

	public EFUI3Page findSearch() {
		//check if the results came back.
		//requestsSearchResultDivId
		//dojoxGridContent
		logger.debug("IN find search");
		WebDriverWait pageLoaded = new WebDriverWait(driver, 30);
		pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("dojoxGridContent")));
		WebElement content = driver.findElement(By.className("dojoxGridContent"));
		logger.debug("the content style is: "+ content.getAttribute("style"));
		pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dojoxGridPaginator")));
		logger.debug("FOUND THE PAGINATOR");
		WebElement paginator = driver.findElement(By.className("dojoxGridPaginator"));
		if(paginator!=null){
			logger.debug("Paginator is found");
			WebElement description = paginator.findElement(By.xpath(".//div[@class='dojoxGridDescription']"));
			logger.debug("description : "+description.getText());
			
		}
		return PageFactory.initElements(driver, EFUI3Page.class);
	}
}