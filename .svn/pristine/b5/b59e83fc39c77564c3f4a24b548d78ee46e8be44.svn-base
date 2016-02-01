package com.cisco.cstg.autotools.tests.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;
import com.cisco.cstg.autotools.support.enumeration.LNPLinkType;
import com.cisco.cstg.autotools.tests.TestConstants;

public class BasePage {
	
	protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
	
	protected WebDriver driver;
	
	protected WebDriverWait pageLoaded;  
    
	/**
	 * Page related configuration.
	 */
	public static Configuration pageConfig = new ComponentConfiguration(PageConstants.class);

	public static final String username = pageConfig.getStringValue(PageConstants.URL_USERNAME);
	public static final String pwd = pageConfig.getStringValue(PageConstants.URL_PASSWORD);
	public static final String pssUsername = pageConfig.getStringValue(PageConstants.PSS_USERNAME);
	public static final String pssPwd = pageConfig.getStringValue(PageConstants.PSS_PASSWORD);
	public static final String baseUrl = pageConfig.getStringValue(PageConstants.BASE_URL);
	public static final String baseUrlNextGen = pageConfig.getStringValue(PageConstants.BASE_URL_NEXT_GEN);
	
	/**
	 * Firefox profile names.
	 */
	public static final String firefoxProfileName = pageConfig.getStringValue(PageConstants.FIREFOX_PROFILE_NAME);
	public static final String SNTC_FIREFOX_PROFILE_NAME = pageConfig.getStringValue(PageConstants.SNTC_FIREFOX_PROFILE_NAME);
	public static final String PSS_FIREFOX_PROFILE_NAME = pageConfig.getStringValue(PageConstants.PSS_FIREFOX_PROFILE_NAME);

	/**
	 * Dashaboards
	 */
	public static final String ADMIN = pageConfig.getStringValue(PageConstants.ADMIN);
	public static final String ALERT_MANAGEMENT = pageConfig.getStringValue(PageConstants.ALERT_MANAGEMENT);
	public static final String CONTRACT_MANAGEMENT = pageConfig.getStringValue(PageConstants.CONTRACT_MANAGEMENT);
	public static final String DEVICE_DIAGNOSTICS = pageConfig.getStringValue(PageConstants.DEVICE_DIAGNOSTICS);
	public static final String INVENTORY_MANAGEMENT = pageConfig.getStringValue(PageConstants.INVENTORY_MANAGEMENT);
	public static final String SMART_NET_TOTAL_CARE = pageConfig.getStringValue(PageConstants.SMART_NET_TOTAL_CARE);

	/**
	 * General links
	 */
	public static final String APPLICATION_SETTINGS = pageConfig.getStringValue(PageConstants.APPLICATION_SETTINGS);
	public static final String CALENDAR = pageConfig.getStringValue(PageConstants.CALENDAR);
	public static final String MY_REPORTS = pageConfig.getStringValue(PageConstants.MY_REPORTS);
	public static final String USEFUL_LINKS = pageConfig.getStringValue(PageConstants.USEFUL_LINKS);
	public static final String NOTIFICATIONS = pageConfig.getStringValue(PageConstants.NOTIFICATIONS);
	
	/**
	 *  Library Links
	 */
	public static final String CONTRACT_PROPERTIES = pageConfig.getStringValue(PageConstants.CONTRACT_PROPERTIES);
	public static final String UPLOAD_PROCESSING = pageConfig.getStringValue(PageConstants.UPLOAD_PROCESSING);
	public static final String ALL_COLLECTORS = pageConfig.getStringValue(PageConstants.ALL_COLLECTORS);
	public static final String ACTIVE_ALERTS = pageConfig.getStringValue(PageConstants.ACTIVE_ALERTS);
	public static final String SERVICE_COVERAGE = pageConfig.getStringValue(PageConstants.SERVICE_COVERAGE);
	public static final String ALL_ALERTS = pageConfig.getStringValue(PageConstants.ALL_ALERTS);
	public static final String ALL_FIELD_NOTICES = pageConfig.getStringValue(PageConstants.ALL_FIELD_NOTICES);
	public static final String ALL_HARDWARE_ALERTS = pageConfig.getStringValue(PageConstants.ALL_HARDWARE_ALERTS);
	public static final String ALL_PSIRTS = pageConfig.getStringValue(PageConstants.ALL_PSIRTS);
	public static final String ALL_SOFTWARE_ALERTS = pageConfig.getStringValue(PageConstants.ALL_SOFTWARE_ALERTS);
	public static final String DEVICE_WITH_ALERTS = pageConfig.getStringValue(PageConstants.DEVICE_WITH_ALERTS);
	public static final String LDOS = pageConfig.getStringValue(PageConstants.LDOS);
	public static final String PRODUCT_ALERTS_DELTA = pageConfig.getStringValue(PageConstants.PRODUCT_ALERTS_DELTA);
	public static final String ALL_CONTRACTS = pageConfig.getStringValue(PageConstants.ALL_CONTRACTS);
	public static final String EXPIRING_CONTRACTS = pageConfig.getStringValue(PageConstants.EXPIRING_CONTRACTS);
	public static final String COVERED = pageConfig.getStringValue(PageConstants.COVERED);
	public static final String NOT_COVERED = pageConfig.getStringValue(PageConstants.NOT_COVERED);
	public static final String EXPIRING_DEVICE_COVERAGE = pageConfig.getStringValue(PageConstants.EXPIRING_DEVICE_COVERAGE);
	public static final String CONTRACT_DUPLICATES = pageConfig.getStringValue(PageConstants.CONTRACT_DUPLICATES);
	public static final String AGGREGATED_REPORT = pageConfig.getStringValue(PageConstants.AGGREGATED_REPORT);
	public static final String ALL_SUPPORT_CASES = pageConfig.getStringValue(PageConstants.ALL_SUPPORT_CASES);
	public static final String MY_SUPPORT_CASES = pageConfig.getStringValue(PageConstants.MY_SUPPORT_CASES);
	public static final String INVENTORY_SUMMARY = pageConfig.getStringValue(PageConstants.INVENTORY_SUMMARY);
	public static final String ALL_EQUIPMENT = pageConfig.getStringValue(PageConstants.ALL_EQUIPMENT);
	public static final String INVENTORY_DUPLICATES = pageConfig.getStringValue(PageConstants.INVENTORY_DUPLICATES);
	public static final String INVENTORY_BY_PRODUCT = pageConfig.getStringValue(PageConstants.INVENTORY_BY_PRODUCT);
	public static final String INVENTORY_COLLECTION_DELTA = pageConfig.getStringValue(PageConstants.INVENTORY_COLLECTION_DELTA);
	public static final String INVENTORY_BY_SITES = pageConfig.getStringValue(PageConstants.INVENTORY_BY_SITES);
	public static final String ALL_HOSTS = pageConfig.getStringValue(PageConstants.ALL_HOSTS);
	public static final String CUSTOM_INVENTORY = pageConfig.getStringValue(PageConstants.CUSTOM_INVENTORY);
	public static final String INVENTORY_INSIGHT_SUMMARY = pageConfig.getStringValue(PageConstants.INVENTORY_INSIGHT_SUMMARY);
	public static final String INVENTORY_INSIGHT_NOT_COLLECTED = pageConfig.getStringValue(PageConstants.INVENTORY_INSIGHT_NOT_COLLECTED);
	public static final String INVENTORY_INSIGHT_THIRD_PARTY = pageConfig.getStringValue(PageConstants.INVENTORY_INSIGHT_THIRD_PARTY);
	public static final String INVENTORY_INSIGHT_DUPLICATES = pageConfig.getStringValue(PageConstants.INVENTORY_INSIGHT_DUPLICATES);
	public static final String INVENTORY_INSIGHT_NOT_RECOGNISED = pageConfig.getStringValue(PageConstants.INVENTORY_INSIGHT_NOT_RECOGNISED);
	public static final String INVENTORY_INSIGHT_NOT_FIELD_REPLACABLE = pageConfig.getStringValue(PageConstants.INVENTORY_INSIGHT_NOT_FIELD_REPLACABLE);
	public static final String INVENTORY_INSIGHT_OTHERS = pageConfig.getStringValue(PageConstants.INVENTORY_INSIGHT_OTHERS);
	
	/**
	 * User roles in SNTC application
	 */
	public static final String SNTC_CUSTOMER_ADMIN = pageConfig.getStringValue(PageConstants.SNTC_CUSTOMER_ADMIN);
	public static final String SNTC_CBR_ADMIN = pageConfig.getStringValue(PageConstants.SNTC_CBR_ADMIN);
	public static final String SNTC_CUSTOMER_USER = pageConfig.getStringValue(PageConstants.SNTC_CUSTOMER_USER);
	public static final String SNTC_CBR_USER = pageConfig.getStringValue(PageConstants.SNTC_CBR_USER);
	public static final String SNTC_GLOBAL_APP_ADMIN = pageConfig.getStringValue(PageConstants.SNTC_GLOBAL_APP_ADMIN);
	public static final String SNTC_GLOBAL_APP_ADMIN_PARTY = pageConfig.getStringValue(PageConstants.SNTC_GLOBAL_APP_ADMIN_PARTY);
	
	/**
	 * WebElements
	 */
	public static String XPATH_STRING = "//span[contains(text(),'REP')]";
	
	//TODO write code to make this dynamic by having it in a config file or database. 
//	public static final List<String> DASHBOARDS = new ArrayList<String>(
//		Arrays.asList(ADMIN , ALERT_MANAGEMENT, CONTRACT_MANAGEMENT, DEVICE_DIAGNOSTICS, INVENTORY_MANAGEMENT, SMART_NET_TOTAL_CARE)
//		);
	/**
	 * maps that gives the link type given the link value
	 */
	private static final Map<String, LNPLinkType> LINK_TYPE_MAP;
	static{
		
		LINK_TYPE_MAP = new HashMap<String, LNPLinkType>();

		LINK_TYPE_MAP.put(ADMIN, LNPLinkType.DASHBOARD_LINK);
		LINK_TYPE_MAP.put(ALERT_MANAGEMENT, LNPLinkType.DASHBOARD_LINK);
		LINK_TYPE_MAP.put(CONTRACT_MANAGEMENT, LNPLinkType.DASHBOARD_LINK);
		LINK_TYPE_MAP.put(DEVICE_DIAGNOSTICS, LNPLinkType.DASHBOARD_LINK);
		LINK_TYPE_MAP.put(INVENTORY_MANAGEMENT, LNPLinkType.DASHBOARD_LINK);
		LINK_TYPE_MAP.put(SMART_NET_TOTAL_CARE, LNPLinkType.DASHBOARD_LINK);
				
		LINK_TYPE_MAP.put(APPLICATION_SETTINGS, LNPLinkType.GENERAL_LINK);
		LINK_TYPE_MAP.put(MY_REPORTS, LNPLinkType.GENERAL_LINK);
		LINK_TYPE_MAP.put(USEFUL_LINKS, LNPLinkType.GENERAL_LINK);
		LINK_TYPE_MAP.put(NOTIFICATIONS, LNPLinkType.GENERAL_LINK);
		
		LINK_TYPE_MAP.put(CONTRACT_PROPERTIES, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(UPLOAD_PROCESSING, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ACTIVE_ALERTS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_COLLECTORS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(SERVICE_COVERAGE, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_ALERTS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_FIELD_NOTICES, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_HARDWARE_ALERTS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_PSIRTS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_SOFTWARE_ALERTS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(DEVICE_WITH_ALERTS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(LDOS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(PRODUCT_ALERTS_DELTA, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_CONTRACTS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(EXPIRING_CONTRACTS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(COVERED, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(NOT_COVERED, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(EXPIRING_DEVICE_COVERAGE, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(CONTRACT_DUPLICATES, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(AGGREGATED_REPORT, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_SUPPORT_CASES, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(MY_SUPPORT_CASES, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_SUMMARY, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_EQUIPMENT, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_DUPLICATES, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_BY_PRODUCT, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_COLLECTION_DELTA, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_BY_SITES, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(ALL_HOSTS, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(CUSTOM_INVENTORY, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_INSIGHT_SUMMARY, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_INSIGHT_NOT_COLLECTED, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_INSIGHT_THIRD_PARTY, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_INSIGHT_DUPLICATES, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_INSIGHT_NOT_RECOGNISED, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_INSIGHT_NOT_FIELD_REPLACABLE, LNPLinkType.LIBRARY_LINK);
		LINK_TYPE_MAP.put(INVENTORY_INSIGHT_OTHERS, LNPLinkType.LIBRARY_LINK);		
		
	}
	
	/**
	 * 
	 * @param driver
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.pageLoaded = new WebDriverWait(driver, TestConstants.WEBDRIVER_TIMEOUT_VALUE);
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public static void moveOverElement(WebElement webElement){
		Coordinates coordinate = ((Locatable)webElement).getCoordinates(); 
		coordinate.onPage(); 
		coordinate.inViewPort();
		logger.debug("FOUND THE LINK AT "+coordinate.inViewPort());
	}
	
	public static void moveOverAndClick(WebElement webElement){
		Coordinates coordinate = ((Locatable)webElement).getCoordinates(); 
		coordinate.onPage(); 
		coordinate.inViewPort();
		logger.debug("FOUND THE LINK AT: "+coordinate.inViewPort());
		webElement.click();
	}
	
	protected WebDriverWait selectPartner(WebDriverWait pageLoaded, Map<String,String> insertions, String partnerName)
			throws Exception{
	Actions builder = new Actions(driver);
	boolean isPartnerFound=false;
	try{
		driver.switchTo().defaultContent();
		logger.debug("SWITCHED BACK TO MAIN PAGE CONTENT");			
		driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
		logger.debug("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
		WebElement headerContainerDiv = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("nmvHeaderToolbarContainer")));
		List<WebElement> filterValueDivs = headerContainerDiv.findElements(By.className("filtervalue"));
		
		for (WebElement filterValueDiv : filterValueDivs) {
			if(filterValueDiv.getAttribute("title").equalsIgnoreCase("partner")){
				logger.debug("PARTNER NAME IS: "+filterValueDiv.getText().trim());
				
				builder.moveToElement(filterValueDiv).perform();
				logger.debug("About to click the PARTNER drop down arrow");
				builder.click().perform();
				while(isPartnerFound==false){
//					pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("widgets_EnhancedCheckedComboBox_1_popup")));
					List<WebElement> drpListItems =driver.findElements
							(By.xpath("//ul[@id='widgets_EnhancedCheckedComboBox_1_popup']/li"));
					logger.debug("TOTAL PARTNER items: " + drpListItems.size());
					for (WebElement aListItem : drpListItems) {
						if(!aListItem.getText().isEmpty() && aListItem.getText().equals(partnerName)){
							insertions.put("partner_name", aListItem.getText().trim());
							logger.debug("FOUND THE PARTNER: {}",aListItem.getText() );
							builder.moveToElement(aListItem).perform();
							builder.click().perform();
							//mark partner booleans
							isPartnerFound = true;
							break;
						}else if(!aListItem.getText().isEmpty() && aListItem.getText().equals("More choices")){
							//TODO remove hardcoding of the more choices word.
							builder.moveToElement(aListItem).perform();
							logger.debug("NOT FOUND PARTNER CLICKING  {} ",aListItem.getText() );
							builder.click().perform();
							break;
						}
					}
				}
				break;
			}
		}
		}catch(NoSuchElementException exp){
			logger.info("NO SUCH ELEMENT EXPCEPTION: CANNOT FIND THE GLOBAL FILTERS PROBLEM");
			insertions.put("customer_name", "GLOBAL FILTERS NOT LOADING HENCE CANNOT FIND CUSTOMER NAME");
		}catch(TimeoutException exp){
			logger.info("TIME OUT ON FINDING THE HEADER CONTAINER ELEMENT: TimeOutTime: ");
		}
		if(isPartnerFound==false){
		insertions.put("partner_name", "THE GLOBAL FILTERS NOT LOADING; CANNOT GET THE CUSTOMER NAME");
		throw new Exception();
		}
	return pageLoaded;
}

	
	protected void selectCustomer(WebDriverWait pageLoaded, Map<String,String> insertions)
			throws Exception{
			
		Actions builder = new Actions(driver);
		boolean isCustomerSelected=false;
				
		try{
			driver.switchTo().defaultContent();
			logger.debug("SWITCHED BACK TO MAIN PAGE CONTENT");			
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
			logger.debug("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
			WebElement headerContainerDiv = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("nmvHeaderToolbarContainer")));
			List<WebElement> filterValueDivs = headerContainerDiv.findElements(By.className("filtervalue"));
			
			for (WebElement filterValueDiv : filterValueDivs) {
				if(filterValueDiv.getAttribute("title").equalsIgnoreCase("customer")){
					builder.moveToElement(filterValueDiv).perform();
					logger.debug("About to click the CUSTOMER drop down arrow");
					builder.click().perform();
//					pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("widgets_EnhancedCheckedComboBox_2_popup")));
					WebElement popupDiv = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dijitPopup")));
					logger.debug("FOUND THE CUSTOMER POPUP");
					List<WebElement> drpListItems =popupDiv.findElements(By.tagName("li"));
					logger.debug("TOTAL CUSTOMER LIST items: " + drpListItems.size());
						for (WebElement aListItem : drpListItems) {
							if(!aListItem.getText().isEmpty()){
								if(aListItem.getText().contains("All")){
									// select the all customers drop down item and click on it
									String totalCustomers = aListItem.getText().replaceAll("All", "").replace("(", "").replace(")", "").trim();
									logger.debug("TOTAL NUMBER OF CUSTOMERS: "+totalCustomers); 
								    insertions.put("total_customers", totalCustomers);
									insertions.put("customer_name", aListItem.getText().trim());
									logger.debug("CUSTOMER NAME IS: "+aListItem.getText().trim());
									builder.moveToElement(aListItem).perform();
									logger.debug("About to select the total value");
									builder.click().perform();
									break;
								}
							}
						}
					// click on the filter div to make the drop down go away.
					builder.moveToElement(filterValueDiv).perform();
					logger.debug("About to click the CUSTOMER MAIN FILTER ");
					builder.click().perform();
					isCustomerSelected=true;
					break;
					}
				}
		}catch(NoSuchElementException exp){
			logger.info("NO SUCH ELEMENT EXPCEPTION: CANNOT FIND THE GLOBAL FILTERS PROBLEM");
			insertions.put("customer_name", "GLOBAL FILTERS NOT LOADING HENCE CANNOT FIND CUSTOMER NAME");
		}catch(TimeoutException exp){
			logger.info("TIME OUT ON FINDING THE HEADER CONTAINER ELEMENT: TimeOutTime: ");
		}

		if(isCustomerSelected==false){
		insertions.put("customer_name", "THE GLOBAL FILTERS NOT LOADING; CANNOT GET THE CUSTOMER NAME");
		}
	}


	
	protected void selectInventory(WebDriverWait pageLoaded, Map<String,String> insertions)
							throws Exception{
		boolean isInventorySelected = false;
		Actions builder = new Actions(driver);
		try{
			driver.switchTo().defaultContent();
			logger.debug("SWITCHED BACK TO MAIN PAGE CONTENT");			
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
			logger.debug("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
		WebElement headerContainerDiv = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("nmvHeaderToolbarContainer")));
		List<WebElement> filterValueDivs = headerContainerDiv.findElements(By.className("filtervalue"));
		
			for (WebElement filterValueDiv : filterValueDivs) {
				if(filterValueDiv.getAttribute("title").equalsIgnoreCase("inventory")){
					builder.moveToElement(filterValueDiv).perform();
					logger.debug("About to click the INVENTORY drop down arrow");
					builder.click().perform();
			//		pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("widgets_EnhancedCheckedComboBox_3_popup")));
					WebElement popupDiv = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dijitPopup")));
					logger.debug("FOUND THE INVENTORY POPUP");
					List<WebElement> drpListItems =popupDiv.findElements(By.tagName("li"));
					logger.debug("TOTAL INVENTORY LIST items: " + drpListItems.size());
					for (WebElement aListItem : drpListItems) {
						if(!aListItem.getText().isEmpty()){
							if(aListItem.getText().contains("All")){
								// get all inventories drop down item and click
								String totalInventories = aListItem.getText().replaceAll("All", "").replace("(", "").replace(")", "").trim();
								logger.debug("TOTAL NUMBER OF INVENTORIES: "+totalInventories); 
							    insertions.put("total_inventories", totalInventories);
								builder.moveToElement(aListItem).perform();
								logger.debug("About to select the total value");
								builder.click().perform();
								break;
							}
						}
					}
					builder.moveToElement(filterValueDiv).perform();
					logger.debug("About to click the INVENTORY Main Filter");
					builder.click().perform();
					isInventorySelected=true;
					break;
				}
			}
		}catch(NoSuchElementException exp){
			logger.info("NO SUCH ELEMENT EXPCEPTION: CANNOT FIND THE GLOBAL FILTERS PROBLEM");
			insertions.put("customer_name", "GLOBAL FILTERS NOT LOADING HENCE CANNOT FIND CUSTOMER NAME");
		}catch(TimeoutException exp){
			logger.info("TIME OUT ON FINDING THE HEADER CONTAINER ELEMENT: TimeOutTime: ");
		}
			
		if(isInventorySelected==false){
		insertions.put("total_inventories", "THE GLOBAL FILTERS NOT LOADING; CANNOT GET TOTAL NUMBER OF INVENTORIES");
		}
	}
	
	/**
	 * Internal method to decide what type of the link the given lnp link is.
	 * returns the type of possible link types.
	 */
	protected LNPLinkType getLNPlinkType(String linkName){
		//	TODO: Change the implementation to go to a database or config to get the values rather 
		//    than static map. This will give flexibility to change the link names easily. 
		return LINK_TYPE_MAP.get(linkName);
	}
	
}