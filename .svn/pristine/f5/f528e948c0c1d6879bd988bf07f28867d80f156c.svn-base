package com.cisco.cstg.autotools.tests.pages.nextgen;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.domain.appdb.CustomerInfo;
import com.cisco.cstg.autotools.semantic.test.ApplicationConstants;
import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;
import com.cisco.cstg.autotools.support.enumeration.LNPLinkType;
import com.cisco.cstg.autotools.support.exception.LinkNotFoundException;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.pages.PageConstants;

public class LeftNavigationPane extends BasePage {
	
	private static final Logger logger = LoggerFactory.getLogger(LeftNavigationPane.class);
	
	/**
	 * Start Config Properties 
	 */
	protected static final Configuration lnpConfig = new ComponentConfiguration(LeftNavigationPane.class);
	protected static final String SELECT_BUTTON_CLASS_NAME=lnpConfig.getStringValue(PageConstants.SELECT_BUTTON_CLASS_NAME);
	protected static final String CONTENT_AREA_CLASS_NAME = lnpConfig.getStringValue(PageConstants.CONTENT_AREA_CLASS_NAME);
	protected static final String LNP_CONTAINER_CLASS_NAME = lnpConfig.getStringValue(PageConstants.LNP_CONTAINER_CLASS_NAME);
	protected static final String DASHBOARD_CLASS_NAME = lnpConfig.getStringValue(PageConstants.DASHBOARD_CLASS_NAME);
	protected static final String TITLE_SPAN_CLASS_NAME = lnpConfig.getStringValue(PageConstants.TITLE_SPAN_CLASS_NAME);
	protected static final String SSUE_DOMAIN_FILTER_TAG = lnpConfig.getStringValue(PageConstants.SSUE_DOMAIN_FILTER_TAG);
	protected static final String SSUE_DOMAIN_FILTER_CLASS_NAME = lnpConfig.getStringValue(PageConstants.SSUE_DOMAIN_FILTER_CLASS_NAME);
	protected static final String SSUE_DOMAIN_FILTER_LABEL_CLASS_NAME = lnpConfig.getStringValue(PageConstants.SSUE_DOMAIN_FILTER_LABEL_CLASS_NAME);
	protected static final String SSUE_DOMAIN_FILTER_VALUE_CLASS_NAME = lnpConfig.getStringValue(PageConstants.SSUE_DOMAIN_FILTER_VALUE_CLASS_NAME);
	protected static final String SSUE_DOMAIN_FILTER_CUSTOMER_LABEL = lnpConfig.getStringValue(PageConstants.SSUE_DOMAIN_FILTER_CUSTOMER_LABEL);
	protected static final String SSUE_DOMAIN_FILTER_INVENTORY_LABEL = lnpConfig.getStringValue(PageConstants.SSUE_DOMAIN_FILTER_INVENTORY_LABEL);
	protected static final String OPEN_DROP_DOWN_CLASSNAME = lnpConfig.getStringValue(PageConstants.OPEN_DROP_DOWN_CLASSNAME_KEY);
	protected static final String CLOSE_DROP_DOWN_CLASSNAME = lnpConfig.getStringValue(PageConstants.CLOSE_DROP_DOWN_CLASSNAME_KEY);

	
	private static final String [] ALL_CUSTOMERS_CHOICE = new String [] {"All Customers", "ALL_Customer"};
	private static final String [] ALL_INVENTORY_CHOICE = new String [] {"All Inventory"};
	/*
	 *END Config properties 
	 */

	protected WebElement leftNavigationPaneContainer = null;
	protected WebElement lnpLink = null;
	
	private CustomerInfo custInfo;
	
	public LeftNavigationPane(WebDriver driver) {
		super(driver);
		custInfo = new CustomerInfo();
	}
	
	/**
	 * General method to click on any link in the Left Navigation Pane. This method delegates to other private methods
	 * after deciding the type of the link (general, dashboard or library) to click the link.
	 * 
	 * @return
	 */
	public WebDriver clickOnLNPlink(String linkName)
							throws LinkNotFoundException{
		
		if(getLNPlinkType(linkName).equals(LNPLinkType.DASHBOARD_LINK)){
			logger.debug("DASHBOARD LINK");
			clickOnDashboard(linkName);
		}else if(getLNPlinkType(linkName).equals(LNPLinkType.GENERAL_LINK)){
			logger.debug("GENERAL LINK");
			clickOnLink(linkName);
		}else if(getLNPlinkType(linkName).equals(LNPLinkType.LIBRARY_LINK)){
			logger.debug("LIBRARY LINK");
			clickOnLibraryLink(linkName);
		}
		return driver;
	}
	/**
	 * Clicks a link name in LNP which is not a dashboard link. Expects the string passed to be
	 * linkname and not a dashboard link. hence not exposed as public but as private
	 * 
	 * @param linkName
	 * @return
	 */
	private WebDriver clickOnLink(String linkName)
							throws LinkNotFoundException{
		boolean isLinkFound=false;
		try {
			waitForContentArea();
			
			List<WebElement> linkDivs = 
					pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(DASHBOARD_CLASS_NAME)));
			
			for (WebElement linkDiv : linkDivs) {
				WebElement spanElement = linkDiv.findElement(By.tagName(PageConstants.SPAN_TAG));
				if(spanElement.getAttribute(TITLE_SPAN_CLASS_NAME).equalsIgnoreCase(linkName)){
					logger.debug("FOUND THE LINK: "+ linkName);
					lnpLink = linkDiv; 
					isLinkFound=true;
					break;
				}
			}	
		} catch (Exception exp) {
			  logger.debug("EXCEPTION IN click LNP method");
			    final Writer result = new StringWriter();
			    final PrintWriter printWriter = new PrintWriter(result);
			    exp.printStackTrace(printWriter);
			    logger.debug(result.toString());
		}
		if(isLinkFound){
			BasePage.moveOverAndClick(lnpLink);
		}else{
			logger.debug("NOT FOUND the "+ linkName+" dashboard");
			throw new LinkNotFoundException();
		}
		return driver;
	}
	
	/**
	 * Clicks a dashboard element on LNP. It assumes that the string passed is a dashboard link
	 * hence not exposed as public but as private
	 * 
	 * @param dashboardName
	 * @return
	 */
	private WebDriver clickOnDashboard(String dashboardName)
							throws LinkNotFoundException{
		try {
			// wait for content area to load if not loaded already
			waitForContentArea();
				// Find and expand the dashboard section if not already.
				List<WebElement> deliverableGroups = driver.findElements(By.className("ssueDeliverableGroup"));
				 for (WebElement webElement : deliverableGroups) {
					webElement.findElement(By.className("ssueDeliverableGroupLabel"));
					if(webElement.getText().trim().equalsIgnoreCase("Dashboards")){
						//found the dashboard element
						try{
//							webElement.findElement(By.className("ssueIcon_triangle_open"));
//							webElement.findElement(By.className("PrimeIcon_triangle_open"));
							webElement.findElement(By.className(OPEN_DROP_DOWN_CLASSNAME));
							
							logger.debug("DASHBOARD GROUP IS OPEN ALREADY");
							//break and loop out as we found what we need.
							break;
						}catch (NoSuchElementException e) {
							logger.debug("DASHBOARD is CLOSED");
							
							if(webElement.findElement(By.className(CLOSE_DROP_DOWN_CLASSNAME))!=null){
//							if(webElement.findElement(By.className("ssueIcon_triangle_close"))!=null){
								//open dashbaord.
								webElement.click();
							}
						}
					}
				}
		} catch (Exception exp) {
			  logger.debug("EXCEPTION IN click LNP method");
			    final Writer result = new StringWriter();
			    final PrintWriter printWriter = new PrintWriter(result);
			    exp.printStackTrace(printWriter);
			    logger.debug(result.toString());
		}
		// now the dashboard is open; click on the link
		 clickOnLink(dashboardName);

		return driver;
	}
	
	
	/**
	 * Clicks a library element on LNP. It assumes that the string passed is a library link
	 * hence not exposed as public but as private
	 * 
	 * @param dashboardName
	 * @return
	 */
	private WebDriver clickOnLibraryLink(String libraryLinkName)
							throws LinkNotFoundException{
		boolean isLinkFound=false;
		try {
			// wait for content area to load if not loaded already
			waitForContentArea();
				// Find and expand the dashboard section if not already.
				List<WebElement> deliverableGroups = driver.findElements(By.className("ssueDeliverableGroup"));
				 for (WebElement webElement : deliverableGroups) {
					webElement.findElement(By.className("ssueDeliverableGroupLabel"));
					if(webElement.getText().trim().equalsIgnoreCase("Library")){
						//found the dashboard element
						try{
//							webElement.findElement(By.className("ssueIcon_triangle_open"));
//							webElement.findElement(By.className("PrimeIcon_triangle_open"));
							webElement.findElement(By.className(OPEN_DROP_DOWN_CLASSNAME));
							
							logger.debug("LIBRARY GROUP IS OPEN ALREADY");
							//break and loop out as we found what we need.
							break;
						}catch (NoSuchElementException e) {
							logger.debug("LIBRARY GROUP is CLOSED");
							
							if(webElement.findElement(By.className(CLOSE_DROP_DOWN_CLASSNAME))!=null){
//							if(webElement.findElement(By.className("ssueIcon_triangle_close"))!=null){
								//open dashbaord.
								webElement.click();
							}
						}
					}
				}
				// now the Library is open; click on the link
				List<WebElement> treeControlList = driver.findElements(By.className("treecontrol-li"));
//				logger.debug("Total treecontrol li items: {}",treeControlList.size());
				for (WebElement listItem : treeControlList) {
					// check if the sub group links are open; if they are not then open them
					List<WebElement> treeDropDowns = listItem.findElements(By.className("treecontrol-arrows"));
					for (WebElement treeDropDown : treeDropDowns) {
						if(treeDropDown.isDisplayed()){
							String classNames = treeDropDown.getAttribute("class");
							if(classNames.contains(CLOSE_DROP_DOWN_CLASSNAME)){
								BasePage.moveOverAndClick(treeDropDown);
								logger.debug("CLICKED THE ARROW DROP DOWN IN LIBRARY LINK");
							}
						}
					}
				}
				waitForContentArea();	
				treeControlList = driver.findElements(By.className("treecontrol-li"));
				for (WebElement listItem : treeControlList) {
					WebElement spanElement = listItem.findElement(By.tagName(PageConstants.SPAN_TAG));
					
//					logger.debug("SPAN TEXT: {}",spanElement.getText());
					if(spanElement.getAttribute(TITLE_SPAN_CLASS_NAME).equalsIgnoreCase(libraryLinkName)){
						logger.debug("FOUND THE LINK: {} by the name {} ",spanElement.getText(), libraryLinkName);
						lnpLink = spanElement; 
						isLinkFound=true;
						break;
					}
				}
				
		} catch (Exception exp) {
			  logger.debug("EXCEPTION IN click Library Link method");
			    final Writer result = new StringWriter();
			    final PrintWriter printWriter = new PrintWriter(result);
			    exp.printStackTrace(printWriter);
			    logger.debug(result.toString());
		}
		if(isLinkFound){
			BasePage.moveOverAndClick(lnpLink);
		}else{
			logger.debug("NOT FOUND the "+ libraryLinkName+" library link");
			throw new LinkNotFoundException();
		}				
		return driver;
	}	
	
	public WebDriver waitForContentArea() throws Exception{
		
		logger.debug("WAITING FOR CONTENT TO LOAD");
		WebDriverWait pageWaitDriver = pageLoaded; 
//				new WebDriverWait(driver, TestConstants.WEBDRIVER_LONGER_TIMEOUT_VALUE);
		try{
		leftNavigationPaneContainer = 
				pageWaitDriver.until(ExpectedConditions.presenceOfElementLocated(By.className(LNP_CONTAINER_CLASS_NAME)));
		logger.debug("LNP CONTAINER LOADED");
		// wait until the content area is loaded
		pageWaitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className(CONTENT_AREA_CLASS_NAME)));
		logger.debug("CONTENT AREA LOADED");
		}catch(NoSuchElementException exp){
			logger.debug("NO SUCH ELEMENT EXCEPTION OCCURED");
		}catch(TimeoutException e){
			logger.debug("TIMEOUT  EXCEPTION OCCURED");
		}
		return driver;
	}
	
	public WebDriver waitForLNPLoad(){
		logger.debug("WAITING FOR LNP TO LOAD");
		
		leftNavigationPaneContainer = 
				pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className(LNP_CONTAINER_CLASS_NAME)));
		pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(DASHBOARD_CLASS_NAME)));
		logger.debug("LNP Loaded");
		
		return driver;
	}
	
	public CustomerInfo getCustomerInfo(){
		
//		if(custInfo.getCustomerName()!=null){
//		Dont rely on cached info cos the user might have changed the inventory anytime. Hence comment above if statement.
			try {
				logger.debug("WAITING FOR CONTENT TO LOAD");
				try{
				leftNavigationPaneContainer = 
						pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className(LNP_CONTAINER_CLASS_NAME)));
				logger.debug("LNP CONTAINER LOADED");
				// wait until the content area is loaded
				pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className(CONTENT_AREA_CLASS_NAME)));
				logger.debug("CONTENT AREA LOADED");
				}catch(NoSuchElementException exp){
					logger.debug("NO SUCH ELEMENT EXCEPTION OCCURED IN getCustomerInfo");
				}catch(TimeoutException e){
					logger.debug("TIMEOUT  EXCEPTION OCCURED IN getCustomerInfo");
				}
				
				WebElement domainFilter = 
						pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.tagName(SSUE_DOMAIN_FILTER_TAG)));
				List<WebElement> domainFilters = domainFilter.findElements(By.className(SSUE_DOMAIN_FILTER_CLASS_NAME));
				for (WebElement aDomainFilter : domainFilters) {
				     WebElement label = aDomainFilter.findElement(By.className(SSUE_DOMAIN_FILTER_LABEL_CLASS_NAME));
				     WebElement value = aDomainFilter.findElement(By.className(SSUE_DOMAIN_FILTER_VALUE_CLASS_NAME));
				     logger.debug("Filter values: {} ; {}",label.getText().trim(),value.getText().trim());
				     if(label.getText().trim().equalsIgnoreCase(SSUE_DOMAIN_FILTER_CUSTOMER_LABEL.trim())){
				    	 logger.debug("Found Customer: {} ; {}",label.getText().trim(),value.getText().trim()); 
				    	 custInfo.setCustomerLabel(label.getText().trim());
				    	 custInfo.setCustomerName(value.getText().trim());
				     }else if(label.getText().trim().equalsIgnoreCase(SSUE_DOMAIN_FILTER_INVENTORY_LABEL.trim())){
				    	 logger.debug("Found Inventory: {} ; {}",label.getText().trim(),value.getText().trim());
				    	 custInfo.setInventoryLabel(label.getText().trim());
				    	 custInfo.setInventoryInfo(value.getText().trim());
				     }
				}
			} catch (Exception exp) {
				  logger.debug("EXCEPTION IN getCustomerInfo Method");
				    final Writer result = new StringWriter();
				    final PrintWriter printWriter = new PrintWriter(result);
				    exp.printStackTrace(printWriter);
				    logger.debug(result.toString());
			}
//		}
		return custInfo;
	}	
	

	
	/**
	 * private method; can't be made pucblic since assumes prereqs. Assumes that the choose customer button is already clicked
	 *   and the modal dialog box is open.
	 * @return
	 * @throws Exception
	 */
	private boolean chooseOptionAll(String [] choiceAll) throws Exception{
			
		boolean isOptionFound=false;
		pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
//		logger.debug("The modal box for select is loaded");
		logger.debug("Waiting for the selection layer to load");
		List<WebElement> selectionLayers= pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("selectionLayer")));
//		logger.debug("Number of selection layers found: {}",selectionLayers.size());
		for (WebElement selectionLayer : selectionLayers) {
			if(selectionLayer.isDisplayed()){
				logger.debug("selection layer to loaded");
				List<WebElement> selectionItems = selectionLayer.findElements(By.className("line"));
				logger.debug("CHOOSE ALL: TOTAL LINE ITEMS {}",selectionItems.size());
				
				int j=0;
				for (WebElement selectItem : selectionItems) {
					try{
						logger.debug("iterating selecItems in ALL OPTION: {}",j++);
						WebElement dataCol = selectItem.findElement(By.className("dataCol"));
						if(dataCol !=null && dataCol.isDisplayed()){
							WebElement span = dataCol.findElement(By.tagName("span"));
							if(span !=null){
								logger.debug("Choice Value: {} ; ", span.getText());
								for (int i=0;i<choiceAll.length;i++){
									if(span.getText().contains(choiceAll[i])){
		//							CHARLESTON COUNTY SCHOOL DISTRICT
									logger.debug("Found all option");
									WebElement selectCol = selectItem.findElement(By.className("selectionCol"));
										if(selectCol !=null){
											WebElement checkMark = selectCol.findElement(By.className("checkmark"));
											if(checkMark !=null){
												isOptionFound =true;
												BasePage.moveOverAndClick(checkMark);
												break;
											}else
												logger.debug("NO CheckMark Found");
												
										}else{
											logger.debug("NO SELECTION COLUMN FOUND");
										}
									}
								}
								if(isOptionFound){
									break;
								}
							}
						}
					}catch(NoSuchElementException exp){
						logger.info("NO SUCH ELEMENT EXPCEPTION");
					}
				}
		    }
		}

		return isOptionFound;
	}

	public WebDriver chooseCustomer(CharSequence customerName) 
				throws Exception{
		logger.debug("Trying to select Customer {}", customerName);
		waitForContentArea();
		
		//click the useful links so that DOM doesn't get updated in the middle
		clickOnLink(BasePage.USEFUL_LINKS);
	
		// find the choose button and click
		WebElement chooseEditIcon = 
				pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className(SELECT_BUTTON_CLASS_NAME)));
		if(chooseEditIcon!=null){
//				logger.debug("Found the choose icon: "+chooseEditIcon.getTagName()+" "+chooseEditIcon.isDisplayed());
				chooseEditIcon.click();
		}else{
			logger.debug("DID NOT FIND THE CHOOSE EDIT ICON");
		}
		
		chooseOptionAll(ALL_CUSTOMERS_CHOICE);
		
		searchTheChoiceValue(customerName);
		selectTheChoiceCheckBox(customerName);
		
		clickApplyOrCancel();
		return driver;
	}
	
	public WebDriver chooseInventory(CharSequence inventoryName) 
													throws Exception{
			logger.debug("Trying to select inventory {}", inventoryName);
			waitForContentArea();
			
			//click the useful links so that DOM doesn't get updated in the middle
			clickOnLink(BasePage.USEFUL_LINKS);
		
			// find the choose button and click
			WebElement chooseEditIcon = 
					pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className(SELECT_BUTTON_CLASS_NAME)));
			if(chooseEditIcon!=null){
//					logger.debug("Found the choose icon: "+chooseEditIcon.getTagName()+" "+chooseEditIcon.isDisplayed());
					chooseEditIcon.click();
			}else{
				logger.debug("DID NOT FIND THE CHOOSE EDIT ICON");
			}
			
			WebElement modalBox = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
//			logger.debug("The modal box for select is loaded");
//			logger.debug("Waiting for the selection layer to load");
			WebElement selectionLayer = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("selectionLayer")));
			logger.debug("selection layer to loaded");

			int noOfAttempts=3;
			for(int i=0;i<noOfAttempts;i++){
				logger.debug("ATTEMPT NO: {}",i);
					try{
						modalBox = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
						WebElement inActiveFilter = modalBox.findElement(By.className("inactiveFilter")); 
						WebElement filterLabel = inActiveFilter.findElement(By.className("filterLabel"));
						BasePage.moveOverAndClick(filterLabel);
						break;
					}catch (StaleElementReferenceException e) {
						logger.debug("GOT A STALE ELEMENT EXCEPTION");
						if(noOfAttempts==2){
							logger.debug("NOT EATING THE EXCEPTION THIS TIME");
							throw new StaleElementReferenceException("");
						}
						logger.debug("EATING THE EXCEPTION THIS TIME");
					}
				}
			boolean isSelectionFound = chooseOptionAll(ALL_INVENTORY_CHOICE);
			selectTheChoiceCheckBox(inventoryName);
			
			clickApplyOrCancel();
			return driver;
	}
	
	private void searchTheChoiceValue(CharSequence choiceValue){
		
		pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
//		logger.debug("The modal box for select is loaded AFTER ALL CUSTOMER CHOOSE");
//		logger.debug("Waiting for the selection layer to load");
		List<WebElement> selectionLayers = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("selectionLayer")));
		
		for (WebElement selectionLayer : selectionLayers) {
			if(selectionLayer.isDisplayed()){
				logger.debug("selection layer to loaded");
				List<WebElement> lineItems = selectionLayer.findElements(By.className("line"));
//				logger.debug("TOTAL SELECT ITEMS AFTER ALL CUSTOMER OPTION SELECT {}",lineItems.size());
				int i=0;
				for (WebElement lineItem : lineItems) {
					List<WebElement> lineItemContents = lineItem.findElements(By.cssSelector("*"));
//					logger.debug("Total lineItemContents FOR SEARCH BOX {}", lineItemContents.size());
					logger.debug("Itearting thru line items for search box: {}",i++);
					if(lineItemContents.size()>0){
						for (WebElement content : lineItemContents) {
							if(! content.getTagName().equals("span")){
								continue;
							}else{
								if(!(content.getAttribute("class").contains("searchBox") && content.isDisplayed())){
									continue;
								}else{
									WebElement input = content.findElement(By.tagName("input"));
									input.sendKeys(choiceValue);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private boolean selectTheChoiceCheckBox(CharSequence choiceValue){
		boolean isSelectionFound=false;
		List<WebElement> selectionLayers = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("selectionLayer")));
//		logger.debug("Total no of selection layers: {}",selectionLayers.size());
		for (WebElement selectionLayer : selectionLayers) {
			List<WebElement> selectionItems = selectionLayer.findElements(By.className("choice"));
			logger.debug("TOTAL CHOICE SELECT ITEMS {}",selectionItems.size());
		    if(selectionLayer.isDisplayed()){
				for (WebElement selectItem : selectionItems) {
					try{
						WebElement dataCol = selectItem.findElement(By.className("dataCol"));
						if(dataCol !=null && dataCol.isDisplayed()){
							WebElement span = dataCol.findElement(By.tagName("span"));
							logger.debug("the span text is: {} and choice is:{} ", span.getText(), (String)choiceValue);
							if(span !=null &&
									ApplicationConstants
									.compareCustomerNames(span.getText().trim(), ((String)choiceValue).trim())){
								logger.debug("Found Compared Value {}",choiceValue);
								WebElement selectCol = selectItem.findElement(By.className("selectionCol"));
								if(selectCol !=null){
									WebElement checkMark = selectCol.findElement(By.className("checkmark"));
									if(checkMark !=null){
										isSelectionFound = true;
//										logger.debug("ABOUT TO CLICK CUSTOMER FROM CHOICE LIST");
										BasePage.moveOverAndClick(checkMark);
										break;
									}else
										logger.debug("NO CheckMark Found");
										
								}else{
									logger.debug("NO SELECTION COLUMN FOUND");
								}
							}
						}
					}catch(NoSuchElementException exp){
						logger.info("NO SUCH ELEMENT EXPCEPTION");
					}
				}
				if(isSelectionFound)
					break;
		    }
		}	
		return isSelectionFound;
	}
	
	private void clickApplyOrCancel(){
		
		WebElement modalBox = 
				pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
		WebElement footer = modalBox.findElement(By.className("modal-footer"));
		List<WebElement> buttons = footer.findElements(By.tagName("button"));
		boolean isApplyButtonEnabled=true;
//		WebElement applyButton;
		WebElement cancelButton=null;
		for (WebElement button : buttons) {
//			logger.debug("BUTTON displayed: {} enabled: {} selected: {} ",button.isDisplayed(),button.isEnabled(),button.isSelected());
			if(button.getText().equalsIgnoreCase("Apply")){
//				applyButton=button;
				if(button.isEnabled()){
					BasePage.moveOverAndClick(button);
					break;
				}else{
					isApplyButtonEnabled=false;
				}
			}else if(button.getText().equalsIgnoreCase("Cancel")){
				cancelButton=button;
			}
		}
		if(!isApplyButtonEnabled){
			BasePage.moveOverAndClick(cancelButton);
		}
	}
	
	public CustomerInfo getCustInfo() {
		return getCustomerInfo();
	}

	public void setCustInfo(CustomerInfo custInfo) {
		this.custInfo = custInfo;
	}
	
	/**
	 * Method that will choose all inventories for a Customer
	 * @return
	 * @throws Exception
	 */
	public WebDriver chooseAllInventory() throws Exception{
		
		waitForContentArea();
		
		//click the useful links so that DOM doesn't get updated in the middle
		clickOnLink(BasePage.USEFUL_LINKS);

		// find the choose button and click
		WebElement chooseEditIcon = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className(SELECT_BUTTON_CLASS_NAME)));
		if(chooseEditIcon!=null){
//				logger.debug("Found the choose icon: "+chooseEditIcon.getTagName()+" "+chooseEditIcon.isDisplayed());
				chooseEditIcon.click();
		}else{
			logger.debug("DID NOT FIND THE CHOOSE EDIT ICON");
		}
		
		WebElement modalBox = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
//		logger.debug("The modal box for select is loaded");
//		logger.debug("Waiting for the selection layer to load");
		WebElement selectionLayer = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("selectionLayer")));
		logger.debug("selection layer to loaded");

		int noOfAttempts=3;
		for(int i=0;i<noOfAttempts;i++){
			logger.debug("ATTEMPT NO: {}",i);
				try{
					modalBox = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
					WebElement inActiveFilter = modalBox.findElement(By.className("inactiveFilter")); 
					WebElement filterLabel = inActiveFilter.findElement(By.className("filterLabel"));
					BasePage.moveOverAndClick(filterLabel);
					break;
				}catch (StaleElementReferenceException e) {
					logger.debug("GOT A STALE ELEMENT EXCEPTION");
					if(noOfAttempts==2){
						logger.debug("NOT EATING THE EXCEPTION THIS TIME");
						throw new StaleElementReferenceException("");
					}
					logger.debug("EATING THE EXCEPTION THIS TIME");
				}
			}
		boolean isSelectionFound = chooseOptionAll(ALL_INVENTORY_CHOICE);

		clickApplyOrCancel();
		
		return driver;
	}
}