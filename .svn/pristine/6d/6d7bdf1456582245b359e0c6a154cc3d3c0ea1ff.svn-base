package com.cisco.cstg.autotools.tests.pages;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.domain.appdb.CustomerInfo;
import com.cisco.cstg.autotools.support.exception.NoCustomerAndRoleInfo;
import com.cisco.cstg.autotools.tests.TestConstants;
import com.cisco.cstg.autotools.tests.TotalRowsDTO;

public class InventorySummaryPage extends LandingPage {

	protected static final Logger logger = LoggerFactory.getLogger(InventorySummaryPage.class);
	
	private String sourceString = "dataType=SNTC_INVENTORY_SUMMARY";
	 
	private String pageName="SNTC_INVENTORY_SUMMARY";

	TotalRowsDTO rowDTO = null;
	
	/**
	 * Category row header values in this page - Start
	 */
	public static final String INVENTORY_ALL_SOURCES="DevicesInInventoryAllSources";
	public static final String INVENTORY_ALL_SOURCES_ROW_NUMBER="1";
	public static final String DEVICES_COLLECTED="DevicesCollected";
	public static final String DEVICES_COLLECTED_ROW_NUMBER="2";
	public static final String DEVICES_IMPORTED="DevicesImported";
	public static final String DEVICES_IMPORTED_ROW_NUMBER="3";
	public static final String DEVICES_RECOGNIZED="DevicesRecognized";
	public static final String DEVICES_RECOGNIZED_ROW_NUMBER="4";
	public static final String DEVICES_COVERED="DevicesCovered";
	public static final String DEVICES_COVERED_ROW_NUMBER="5";
	public static final String DEVICES_NOT_COVERED="DevicesNotCovered";
	public static final String DEVICES_NOT_COVERED_ROW_NUMBER="6";
	public static final String PAST_LDOS="PastLDOS";
	public static final String PAST_LDOS_ROW_NUMBER="7";
	public static final String LDOS_WITHIN_12_MONTHS="LDOSWithin12months";
	public static final String LDOS_WITHIN_12_MONTHS_ROW_NUMBER="8";
	public static final String LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS="LDOSOver12MonthsAndWithin24Months";
	public static final String LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS_ROW_NUMBER="9";
	/**
	 * Category Row header values in this page - End
	 */
	/**
	 * Column header values in this page - Start
	 */
	public static final String CHASSIS="Chassis";
	public static final String CHASSIS_COLUMN_NUMBER="1";
	public static final String MODULES="Modules";
	public static final String MODULES_COLUMN_NUMBER="2";
	public static final String POWER_SUPPLY="PowerSupply";
	public static final String POWER_SUPPLY_COLUMN_NUMBER="3";
	public static final String FAN="Fan";
	public static final String FAN_COLUMN_NUMBER="4";
	public static final String OTHERS="Others";
	public static final String OTHERS_COLUMN_NUMBER="5";
	public static final String TOTAL="Total";
	public static final String TOTAL_COLUMN_NUMBER="6";
	/**
	 * Column header values in this page - End
	 */
	/**
	 * The table map that is present on this page.
	 */
	public static final Map<String, String> ROW_COLUMN_MAP;
	static{
		ROW_COLUMN_MAP = new HashMap<String, String>();
		
		ROW_COLUMN_MAP.put(INVENTORY_ALL_SOURCES_ROW_NUMBER+CHASSIS_COLUMN_NUMBER, INVENTORY_ALL_SOURCES+CHASSIS);
		ROW_COLUMN_MAP.put(INVENTORY_ALL_SOURCES_ROW_NUMBER+MODULES_COLUMN_NUMBER, INVENTORY_ALL_SOURCES+MODULES);
		ROW_COLUMN_MAP.put(INVENTORY_ALL_SOURCES_ROW_NUMBER+POWER_SUPPLY_COLUMN_NUMBER, INVENTORY_ALL_SOURCES+POWER_SUPPLY);
		ROW_COLUMN_MAP.put(INVENTORY_ALL_SOURCES_ROW_NUMBER+FAN_COLUMN_NUMBER, INVENTORY_ALL_SOURCES+FAN);
		ROW_COLUMN_MAP.put(INVENTORY_ALL_SOURCES_ROW_NUMBER+OTHERS_COLUMN_NUMBER, INVENTORY_ALL_SOURCES+OTHERS);
		ROW_COLUMN_MAP.put(INVENTORY_ALL_SOURCES_ROW_NUMBER+TOTAL_COLUMN_NUMBER, INVENTORY_ALL_SOURCES+TOTAL);
		
		
		ROW_COLUMN_MAP.put(DEVICES_COLLECTED_ROW_NUMBER+CHASSIS_COLUMN_NUMBER, DEVICES_COLLECTED+CHASSIS);
		ROW_COLUMN_MAP.put(DEVICES_COLLECTED_ROW_NUMBER+MODULES_COLUMN_NUMBER, DEVICES_COLLECTED+MODULES);
		ROW_COLUMN_MAP.put(DEVICES_COLLECTED_ROW_NUMBER+POWER_SUPPLY_COLUMN_NUMBER, DEVICES_COLLECTED+POWER_SUPPLY);
		ROW_COLUMN_MAP.put(DEVICES_COLLECTED_ROW_NUMBER+FAN_COLUMN_NUMBER, DEVICES_COLLECTED+FAN);
		ROW_COLUMN_MAP.put(DEVICES_COLLECTED_ROW_NUMBER+OTHERS_COLUMN_NUMBER, DEVICES_COLLECTED+OTHERS);
		ROW_COLUMN_MAP.put(DEVICES_COLLECTED_ROW_NUMBER+TOTAL_COLUMN_NUMBER, DEVICES_COLLECTED+TOTAL);

		ROW_COLUMN_MAP.put(DEVICES_IMPORTED_ROW_NUMBER+CHASSIS_COLUMN_NUMBER, DEVICES_IMPORTED+CHASSIS);
		ROW_COLUMN_MAP.put(DEVICES_IMPORTED_ROW_NUMBER+MODULES_COLUMN_NUMBER, DEVICES_IMPORTED+MODULES);
		ROW_COLUMN_MAP.put(DEVICES_IMPORTED_ROW_NUMBER+POWER_SUPPLY_COLUMN_NUMBER, DEVICES_IMPORTED+POWER_SUPPLY);
		ROW_COLUMN_MAP.put(DEVICES_IMPORTED_ROW_NUMBER+FAN_COLUMN_NUMBER, DEVICES_IMPORTED+FAN);
		ROW_COLUMN_MAP.put(DEVICES_IMPORTED_ROW_NUMBER+OTHERS_COLUMN_NUMBER, DEVICES_IMPORTED+OTHERS);
		ROW_COLUMN_MAP.put(DEVICES_IMPORTED_ROW_NUMBER+TOTAL_COLUMN_NUMBER, DEVICES_IMPORTED+TOTAL);

		ROW_COLUMN_MAP.put(DEVICES_RECOGNIZED_ROW_NUMBER+CHASSIS_COLUMN_NUMBER, DEVICES_RECOGNIZED+CHASSIS);
		ROW_COLUMN_MAP.put(DEVICES_RECOGNIZED_ROW_NUMBER+MODULES_COLUMN_NUMBER, DEVICES_RECOGNIZED+MODULES);
		ROW_COLUMN_MAP.put(DEVICES_RECOGNIZED_ROW_NUMBER+POWER_SUPPLY_COLUMN_NUMBER, DEVICES_RECOGNIZED+POWER_SUPPLY);
		ROW_COLUMN_MAP.put(DEVICES_RECOGNIZED_ROW_NUMBER+FAN_COLUMN_NUMBER, DEVICES_RECOGNIZED+FAN);
		ROW_COLUMN_MAP.put(DEVICES_RECOGNIZED_ROW_NUMBER+OTHERS_COLUMN_NUMBER, DEVICES_RECOGNIZED+OTHERS);
		ROW_COLUMN_MAP.put(DEVICES_RECOGNIZED_ROW_NUMBER+TOTAL_COLUMN_NUMBER, DEVICES_RECOGNIZED+TOTAL);
		
		ROW_COLUMN_MAP.put(DEVICES_COVERED_ROW_NUMBER+CHASSIS_COLUMN_NUMBER, DEVICES_COVERED+CHASSIS);
		ROW_COLUMN_MAP.put(DEVICES_COVERED_ROW_NUMBER+MODULES_COLUMN_NUMBER, DEVICES_COVERED+MODULES);
		ROW_COLUMN_MAP.put(DEVICES_COVERED_ROW_NUMBER+POWER_SUPPLY_COLUMN_NUMBER, DEVICES_COVERED+POWER_SUPPLY);
		ROW_COLUMN_MAP.put(DEVICES_COVERED_ROW_NUMBER+FAN_COLUMN_NUMBER, DEVICES_COVERED+FAN);
		ROW_COLUMN_MAP.put(DEVICES_COVERED_ROW_NUMBER+OTHERS_COLUMN_NUMBER, DEVICES_COVERED+OTHERS);
		ROW_COLUMN_MAP.put(DEVICES_COVERED_ROW_NUMBER+TOTAL_COLUMN_NUMBER, DEVICES_COVERED+TOTAL);
		
		ROW_COLUMN_MAP.put(DEVICES_NOT_COVERED_ROW_NUMBER+CHASSIS_COLUMN_NUMBER, DEVICES_NOT_COVERED+CHASSIS);
		ROW_COLUMN_MAP.put(DEVICES_NOT_COVERED_ROW_NUMBER+MODULES_COLUMN_NUMBER, DEVICES_NOT_COVERED+MODULES);
		ROW_COLUMN_MAP.put(DEVICES_NOT_COVERED_ROW_NUMBER+POWER_SUPPLY_COLUMN_NUMBER, DEVICES_NOT_COVERED+POWER_SUPPLY);
		ROW_COLUMN_MAP.put(DEVICES_NOT_COVERED_ROW_NUMBER+FAN_COLUMN_NUMBER, DEVICES_NOT_COVERED+FAN);
		ROW_COLUMN_MAP.put(DEVICES_NOT_COVERED_ROW_NUMBER+OTHERS_COLUMN_NUMBER, DEVICES_NOT_COVERED+OTHERS);
		ROW_COLUMN_MAP.put(DEVICES_NOT_COVERED_ROW_NUMBER+TOTAL_COLUMN_NUMBER, DEVICES_NOT_COVERED+TOTAL);
		
		ROW_COLUMN_MAP.put(PAST_LDOS_ROW_NUMBER+CHASSIS_COLUMN_NUMBER, PAST_LDOS+CHASSIS);
		ROW_COLUMN_MAP.put(PAST_LDOS_ROW_NUMBER+MODULES_COLUMN_NUMBER, PAST_LDOS+MODULES);
		ROW_COLUMN_MAP.put(PAST_LDOS_ROW_NUMBER+POWER_SUPPLY_COLUMN_NUMBER, PAST_LDOS+POWER_SUPPLY);
		ROW_COLUMN_MAP.put(PAST_LDOS_ROW_NUMBER+FAN_COLUMN_NUMBER, PAST_LDOS+FAN);
		ROW_COLUMN_MAP.put(PAST_LDOS_ROW_NUMBER+OTHERS_COLUMN_NUMBER, PAST_LDOS+OTHERS);
		ROW_COLUMN_MAP.put(PAST_LDOS_ROW_NUMBER+TOTAL_COLUMN_NUMBER, PAST_LDOS+TOTAL);
		
		ROW_COLUMN_MAP.put(LDOS_WITHIN_12_MONTHS_ROW_NUMBER+CHASSIS_COLUMN_NUMBER, LDOS_WITHIN_12_MONTHS+CHASSIS);
		ROW_COLUMN_MAP.put(LDOS_WITHIN_12_MONTHS_ROW_NUMBER+MODULES_COLUMN_NUMBER, LDOS_WITHIN_12_MONTHS+MODULES);
		ROW_COLUMN_MAP.put(LDOS_WITHIN_12_MONTHS_ROW_NUMBER+POWER_SUPPLY_COLUMN_NUMBER, LDOS_WITHIN_12_MONTHS+POWER_SUPPLY);
		ROW_COLUMN_MAP.put(LDOS_WITHIN_12_MONTHS_ROW_NUMBER+FAN_COLUMN_NUMBER, LDOS_WITHIN_12_MONTHS+FAN);
		ROW_COLUMN_MAP.put(LDOS_WITHIN_12_MONTHS_ROW_NUMBER+OTHERS_COLUMN_NUMBER, LDOS_WITHIN_12_MONTHS+OTHERS);
		ROW_COLUMN_MAP.put(LDOS_WITHIN_12_MONTHS_ROW_NUMBER+TOTAL_COLUMN_NUMBER, LDOS_WITHIN_12_MONTHS+TOTAL);
		
		ROW_COLUMN_MAP.put(LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS_ROW_NUMBER+CHASSIS_COLUMN_NUMBER, LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+CHASSIS);
		ROW_COLUMN_MAP.put(LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS_ROW_NUMBER+MODULES_COLUMN_NUMBER, LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+MODULES);
		ROW_COLUMN_MAP.put(LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS_ROW_NUMBER+POWER_SUPPLY_COLUMN_NUMBER, LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+POWER_SUPPLY);
		ROW_COLUMN_MAP.put(LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS_ROW_NUMBER+FAN_COLUMN_NUMBER, LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+FAN);
		ROW_COLUMN_MAP.put(LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS_ROW_NUMBER+OTHERS_COLUMN_NUMBER, LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+OTHERS);
		ROW_COLUMN_MAP.put(LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS_ROW_NUMBER+TOTAL_COLUMN_NUMBER, LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+TOTAL);
	}
	
	
	public InventorySummaryPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	public WebDriver waitForContentArea() throws 
						NoCustomerAndRoleInfo{
		
		CustomerInfo custInfo = getCustomerInfo();
		
		if(custInfo.getInventoryInfo().contains(CustomerInfo.MULTI_INVENTORY_SELECTION_INDICATOR)
				|| custInfo.getInventoryInfo().contains(CustomerInfo.ALL_INVENTORY_SELECTION_INDICATOR)){
			// the multi iventory selection msg will pop-up so look for 
			// wait until the content area is loaded
			pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className(CONTENT_AREA_CLASS_NAME)));
			logger.debug("CONTENT AREA LOADED");
			driver.switchTo().defaultContent();
		
			List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(PageConstants.IFRAME_TAG)));
			for (WebElement iframeInstance : iframes){
				String src = iframeInstance.getAttribute(PageConstants.SOURCE_ATTRIBUTE);
				logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
				logger.debug("THE SRC IS: "+src);
				if(!src.isEmpty()){
					driver.switchTo().frame(iframeInstance);				
					if(src.contains(sourceString)){
						logger.debug("FOUND THE  IFRAME"+pageName);
						//modal-dialog
						
						WebElement modalBox = 
								pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
						WebElement footer = modalBox.findElement(By.className("modal-footer"));
						List<WebElement> buttons = footer.findElements(By.tagName("button"));
						boolean isOKButtonEnabled=true;
						WebElement cancelButton=null;
						for (WebElement button : buttons) {
							logger.debug("BUTTON displayed: {} enabled: {} selected: {} ",button.isDisplayed(),button.isEnabled(),button.isSelected());
							if(button.getText().equalsIgnoreCase("ok")){
								if(button.isEnabled()){
									BasePage.moveOverAndClick(button);
									break;
								}else{
									isOKButtonEnabled=false;
								}
							}else if(button.getText().equalsIgnoreCase("Cancel")){
								cancelButton=button;
							}
						}
						if(!isOKButtonEnabled){
							BasePage.moveOverAndClick(cancelButton);
						}
						
						driver.switchTo().parentFrame();
						logger.debug("Switched to the parent iframe ");
						break;
					}
					driver.switchTo().parentFrame();
					logger.debug("Switched to the parent iframe ");
				}
			}
			
			
		}
		// wait until the content area is loaded
		pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className(CONTENT_AREA_CLASS_NAME)));
		logger.debug("CONTENT AREA LOADED");
		driver.switchTo().defaultContent();
	
		List<WebElement> iframes = pageLoaded
									.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
			
		logger.debug("Total number of iframes: "+ iframes.size());
		for (WebElement iframeInstance : iframes){
			String src = iframeInstance.getAttribute("src");
			logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
			logger.debug("THE SRC IS: "+src);
			if(!src.isEmpty()){
				driver.switchTo().frame(iframeInstance);				
				if(src.contains(sourceString)){
					logger.debug("FOUND THE  IFRAME"+pageName);
												
					long startStaleElementTime=System.nanoTime();
					long start = System.nanoTime();
					long stop = System.nanoTime();
					boolean noMoreBusyImages=false;
//					WebElement trElement;
					
					while(noMoreBusyImages==false &&
							(new Long(TimeUnit.SECONDS.convert((stop - start), 
									TimeUnit.NANOSECONDS)).intValue() <= TestConstants.MAX_WAIT_TIME_FOR_ELEMENTS_TO_LOAD)){
							try{
								 
								WebElement panelDiv = pageLoaded
														.ignoring(StaleElementReferenceException.class)
														.until(ExpectedConditions.presenceOfElementLocated(By.id("mainPannelDivId")));
								List<WebElement> busyImages = panelDiv.findElements(By.tagName(PageConstants.IMAGE_TAG));
//								logger.debug("NUMBER OF BUSY IMAGES: {}",busyImages.size());
								if(busyImages.size()==0)
									break;

								}catch(TimeoutException exp){
									logger.info("TIME OUT ON INVENTORY SUMMARY Page");
								}catch (NoSuchElementException e) {
									logger.info("No Such Element on Total count");
								}catch (StaleElementReferenceException exp){
									logger.info("Stale Element Exception");
									long stopStaleElementTime = System.nanoTime();
									String timeTaken = new Long(TimeUnit.SECONDS.convert((stopStaleElementTime - startStaleElementTime), TimeUnit.NANOSECONDS)).toString() + " sec";
									logger.info("StaleElementTime: {} ",timeTaken);
								}
							stop = System.nanoTime();		
					 	}/* End of While */
					 driver.switchTo().parentFrame();
					 logger.debug("Switched to the parent iframe ");
					 break;
				}
				driver.switchTo().parentFrame();
				logger.debug("Switched to the parent iframe ");
			}
		}
		return driver;
	}
	
	/**
	 * Expects that when this message is invoked it would be on the main container landing page.
	 * And the inventory summary page is clicked and loaded.
	 * @return
	 */
	public ArrayList<String> getTableRowData(){
		
		logger.info("------getTableRowData() ------Started---");
		
		ArrayList<String> tableValues = new ArrayList<String>();
		
		driver.switchTo().defaultContent();
		List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
		logger.debug("Total number of iframes: "+ iframes.size());
		
		for (WebElement iframeInstance : iframes){
			String src = iframeInstance.getAttribute("src");
			logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
			logger.debug("THE SRC IS: "+src);
			if(!src.isEmpty()){
			driver.switchTo().frame(iframeInstance);				
				if(src.contains(sourceString)){
					try{
						WebElement table = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("mainPannelDivId")));
						StringBuffer gridDataValue;
						List<WebElement> trCount = table.findElements(By.tagName("tr"));
//						logger.debug("Number of tr elements: {}",trCount.size());
						/* To Traverse the No of Rows in Table */
						int i=0;
						String valueName;
						String rowNumber;
						String colNumber;
						for (WebElement trInstance : trCount){
							if(trInstance.isDisplayed()){
								 List<WebElement> tdCount1 = trInstance.findElements(By.tagName("td"));
								 logger.debug("Total number of columns : "+ tdCount1.size());
								 int j=0;
								 rowNumber=Integer.toString(i);
								 for (WebElement tdInstance : tdCount1){
									 gridDataValue=new StringBuffer();
									 colNumber=Integer.toString(j);
								 	 valueName = ROW_COLUMN_MAP.get(rowNumber+colNumber);
								 	 if(!StringUtils.isEmpty(valueName)){
								 		gridDataValue.append(valueName);
								 		gridDataValue.append("=");
								 		gridDataValue.append(tdInstance.getText());
//								 		logger.debug("THE VALUE IN:{} is {} and {}",rowNumber+colNumber,valueName,tdInstance.getText());
								 	 }
									 if(!StringUtils.isEmpty(gridDataValue.toString())){
										 tableValues.add(gridDataValue.toString());
											logger.debug("RowData -->   "+gridDataValue);
											
										}
									 j++;
							      }
								i++;
							}
						}
						logger.debug("THE {} LOADED", pageName);
						driver.switchTo().parentFrame();
						logger.debug("Switched to the parent iframe ");
						break;
					}catch(NoSuchElementException exp){
						logger.info("NO SUCH ELEMENT EXPCEPTION");
					}catch(TimeoutException exp){
						logger.info("TIME OUT FOR {}",sourceString);
					}
				}
			driver.switchTo().parentFrame();
			logger.debug("Switched to the parent iframe ");
			}
		}
		logger.info("------getTableRowData() ------ENDED---");
        return tableValues;
	}
	
	public boolean verifyCount(String ExpectedValue){
		logger.info("------verifyCount() ------Started---");

		TotalRowsDTO rowDTO = new TotalRowsDTO("No Rows", false);
		long startStaleElementTime=System.nanoTime();
		long start = System.nanoTime();
		long stop = System.nanoTime();
		boolean status=false;
		
		//logger.debug("start  Time: "+start );
		
			boolean isCountObtained=false;
			while(isCountObtained==false &&
					(new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() <= 120)){
				try{
				WebElement spanElement =  pageLoaded.ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueContainerSubtitle")));
		
				startStaleElementTime = System.nanoTime();
				
				if (spanElement!=null){
 				
					if(!spanElement.getText().trim().equals("...")){
					//logger.debug("spanElement.getText(): "+spanElement.getText() );
					
					
					if(spanElement.getText().equalsIgnoreCase(ExpectedValue)){
						isCountObtained=true;
						status=true;
						logger.debug("spanElement.getText(): aaa  "+spanElement.getText() );
						break;
					}

				}
					
					WebElement spanElement1 =  pageLoaded.ignoring(StaleElementReferenceException.class)
							.until(ExpectedConditions.presenceOfElementLocated(By.className("ng-binding")));
					
					if (spanElement1!=null){
		 				
						if(!spanElement1.getText().trim().equals("...")){
						//logger.debug("spanElement1.getText(): "+spanElement1.getText() );
							if(spanElement.getText().equalsIgnoreCase(ExpectedValue)){
								isCountObtained=true;
								status=true;
								logger.debug("spanElement.getText(): Raju  "+spanElement.getText() );
								break;
							}
							
							if(spanElement1.getText().equalsIgnoreCase(ExpectedValue)){
								isCountObtained=true;
								status=true;
								logger.debug("spanElement1.getText(): Raju  "+spanElement1.getText() );
								break;
							}

						}
					}
				}
				
				}catch(TimeoutException exp){
					logger.info("TIME OUT ON Item Count");
					final Writer result = new StringWriter();
				    final PrintWriter printWriter = new PrintWriter(result);
				    exp.printStackTrace(printWriter);
				    logger.debug(result.toString());
				}catch (NoSuchElementException e) {
					logger.info("No Such Element on Total count");
				}
				catch (StaleElementReferenceException exp){
					rowDTO.setStaleElementExceptionOccured(true);
					logger.info("Stale Element Exception");
					long stopStaleElementTime = System.nanoTime();
					String timeTaken = new Long(TimeUnit.SECONDS.convert((stopStaleElementTime - startStaleElementTime), TimeUnit.NANOSECONDS)).toString() + " sec";
					logger.info("StaleElementTime: {} ",timeTaken);
					
				}
				stop = System.nanoTime();
			}
			// TODO need to work on case when it takes longer for no records message to show up and this above loop goes into infinite cycle if there
			// is no timer check.
		
		//logger.debug("stop  Time: "+stop );
		if(!status)
		{
			WebElement spanElement =  pageLoaded.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueContainerSubtitle")));
			
			logger.debug("spanElement.getText():   "+spanElement.getText());
		}
		
		logger.info("------verifyCount() ------Ended---");
		return status;
		
	}
	
	public void clickOnColumn_HyperLink(int RowNumber ,int ColumnNumber){
		
		logger.info("------clickOnColumn_HyperLink() ------Started---");
		
		pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("mainPannelDivId")));
		
		WebElement element =pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='mainPannelDivId']//tr["+RowNumber+"]/td["+ColumnNumber+"]/a"))); 
		
		element.click();
		
		driver.switchTo().defaultContent();
		
		pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className(CONTENT_AREA_CLASS_NAME)));
		pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className(CONTENT_AREA_CLASS_NAME)));
	/*//	pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[@class='ssueContainerSubtitle ng-binding']")));
		//logger.debug("CONTENT AREA LOADED  raju");
		
		long startStaleElementTime=System.nanoTime();
		long start = System.nanoTime();
		long stop = System.nanoTime();
		boolean isCountObtained=false;
				
		while(isCountObtained==false &&
				(new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() <= 120)){
			try{
			WebElement spanElement =  pageLoaded.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueContainerSubtitle")));
	
			//logger.debug("The total count is : "+spanElement);
			startStaleElementTime = System.nanoTime();
			
			if (spanElement!=null){

				if(!spanElement.getText().trim().equals("...")){
				logger.debug("Row count : {}", spanElement.getText() );
						isCountObtained=true;
			
			}
			}
			
			}catch(TimeoutException exp){
				logger.info("TIME OUT ON Item Count");
			}catch (NoSuchElementException e) {
				logger.info("No Such Element on Total count");
			}
			catch (StaleElementReferenceException exp){
				rowDTO.setStaleElementExceptionOccured(true);
				logger.info("Stale Element Exception");
				long stopStaleElementTime = System.nanoTime();
				String timeTaken = new Long(TimeUnit.SECONDS.convert((stopStaleElementTime - startStaleElementTime), TimeUnit.NANOSECONDS)).toString() + " sec";
				logger.info("StaleElementTime: {} ",timeTaken);
				
			}
			stop = System.nanoTime();
		}
		
		
		
		boolean isNoRecordsDisplayed=false;
		logger.debug("Is No records value: {}", isNoRecordsDisplayed);
		rowDTO=getTotalFromLink(pageLoaded,isNoRecordsDisplayed);
		String totalRecords = rowDTO.getTotalRows();
		System.out.println("totalRecords    "+totalRecords);
		
		String totalRecords1 =pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[@class='ssueContainerSubtitle ng-binding']"))).getText();
		System.out.println("totalRecords1    "+totalRecords1);
		
		 totalRecords =pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[contains(@class,'ssueContainerSubtitle')]"))).getText();
		System.out.println("totalRecords    "+totalRecords);
		
		
		String totalClass =pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[contains(@class,'ssueContainerSubtitle')]"))).getAttribute("ng-show");
		System.out.println("totalClass    "+totalClass);
		
		
	
		
		
		logger.debug("TOTAL ROWS IN {} DATA GRID ARE: {}",pageName, totalRecords);*/
		
		//return totalRecords;
		
		logger.info("------clickOnColumn_HyperLink() ------Ended---");
	}
	
}





