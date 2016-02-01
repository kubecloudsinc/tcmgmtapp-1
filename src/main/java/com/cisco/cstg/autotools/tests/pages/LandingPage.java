package com.cisco.cstg.autotools.tests.pages;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.support.exception.NoExportOption;
import com.cisco.cstg.autotools.tests.TestConstants;
import com.cisco.cstg.autotools.tests.TotalRowsDTO;
import com.cisco.cstg.autotools.tests.pages.nextgen.LeftNavigationPane;

public class LandingPage extends LeftNavigationPane {

	protected static final Logger logger = LoggerFactory.getLogger(LandingPage.class);
	
	public LandingPage(WebDriver driver) {
		super(driver);
		
	}
	
	/**
	 * time for the initial load of the page
	 */
	private String timeToInitialLoad;
	
	/**
	 * time taken for the entire page to load including the total number in header if present.
	 */
	private String timeToLoad;
	
	/**
	 * Total number of records on the page displayed in the header of the page
	 */
	private String totalRecords;
		
	
	public String getTimeToInitialLoad() {
		return timeToInitialLoad;
	}

	public void setTimeToInitialLoad(String timeToInitialLoad) {
		this.timeToInitialLoad = timeToInitialLoad;
	}

	public String getTimeToLoad() {
		return timeToLoad;
	}

	public void setTimeToLoad(String timeToLoad) {
		this.timeToLoad = timeToLoad;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	protected TotalRowsDTO getTotalFromLink(WebDriverWait pageLoaded, boolean isNoRecordsDisplayed){
//		Number total=0;
		TotalRowsDTO rowDTO = new TotalRowsDTO("No Rows", false);
		long startStaleElementTime=System.nanoTime();
		long start = System.nanoTime();
		long stop = System.nanoTime();
		
		if(isNoRecordsDisplayed==false){
			boolean isCountObtained=false;
			while(isCountObtained==false &&
					(new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() <= 300)){
				try{
				WebElement spanElement =  pageLoaded.ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueContainerSubtitle")));
//				logger.debug("The total count is: "+spanElement.getText());
				startStaleElementTime = System.nanoTime();
				
					if (spanElement!=null){
						if(!spanElement.getText().trim().equals("...")){
							logger.debug("Row count: {}", spanElement.getText() );
							if(StringUtils.isEmpty(spanElement.getText())){
								rowDTO.setTotalRows("No Data");
							}else{
								rowDTO.setTotalRows(spanElement.getText());
							}
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
			// TODO need to work on case when it takes longer for no records message to show up and this above loop goes into infinite cycle if there
			// is no timer check.
		}
		return rowDTO;
	}

	
	public WebDriver waitForContentArea(String sourceString, String pageName) throws Exception{

		long start = System.nanoTime();
		long stop = System.nanoTime();

		// wait until the content area is loaded
		pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className(CONTENT_AREA_CLASS_NAME)));
		logger.debug("CONTENT AREA LOADED");
		
//		String timeTakenToLoad=null;
//		String insertParamTimeTaken = "time_taken_to_load_"+sourceString;
//		String insertParamTotal = "total_records_"+sourceString;
//		String insertParamInitialTime= "time_for_initial_load_"+sourceString;
////		Number total=null;
//		String total=null;
		boolean isNoRecordsDisplayed=false;
		boolean isStaleElementExceptionOccured=false;
		boolean isTimeOutExceptionOccured=false;
		TotalRowsDTO rowDTO = null;
		
		try{
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
							logger.debug("FOUND THE {} IFRAME",pageName);
							pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("dojoxGridRowTable")));
							// inital page loaded capture the time
							stop = System.nanoTime();
							timeToInitialLoad = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
							
							logger.debug("THE {} INITIAL PAGE LOADED", pageName);
							// reset the clock
							start = System.nanoTime();
							WebElement innerDiv=null;
							WebElement msgDiv=null;
							
							try{
								do{
								WebElement contentDiv = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dojoxGridContent")));
								innerDiv = contentDiv.findElement(By.tagName("div"));
								msgDiv = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dojoxGridMasterMessages")));
//								logger.debug("The message div attributes: displayed {} : Enabled {} : Selected {} ",msgDiv.isDisplayed(),msgDiv.isEnabled(), msgDiv.isSelected());
								}while(!(innerDiv.isDisplayed() || msgDiv.isDisplayed()));
								logger.debug("The message div attributes: displayed {} : Enabled {} : Selected {} ",msgDiv.isDisplayed(),msgDiv.isEnabled(), msgDiv.isSelected());
								isNoRecordsDisplayed = msgDiv.isDisplayed();
							}catch (StaleElementReferenceException e) {
								// Eat exception and do one more time
								logger.debug("STALE ELEMENT EXCEPTION OCCURED");
								isStaleElementExceptionOccured=true;
								WebElement contentDiv = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dojoxGridContent")));
								innerDiv = contentDiv.findElement(By.tagName("div"));
								msgDiv = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dojoxGridMasterMessages")));
								logger.debug("The message div attributes: displayed {} : Enabled {} : Selected {} ",msgDiv.isDisplayed(),msgDiv.isEnabled(), msgDiv.isSelected());
								isNoRecordsDisplayed = msgDiv.isDisplayed();
							}catch(Exception exp){
								  final Writer result = new StringWriter();
								  final PrintWriter printWriter = new PrintWriter(result);
								  exp.printStackTrace(printWriter);
								  logger.info(result.toString());
							}
							logger.debug("THE {} LOADED", pageName);
							driver.switchTo().parentFrame();
							logger.debug("Switched to the parent iframe ");
							break;
						}catch(NoSuchElementException exp){
							logger.info("NO SUCH ELEMENT EXPCEPTION");
						}catch(TimeoutException exp){
							// did not find the data grids, may be error msg is displayed
							stop = System.nanoTime();
							timeToLoad = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
							logger.info("TIME OUT FOR {} : TimeOutTime: {} ",sourceString, timeToLoad);
							isTimeOutExceptionOccured=true;
							timeToInitialLoad = new Long((TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)) 
									-(TestConstants.WEBDRIVER_TIMEOUT_VALUE)
									).toString() + " sec";
						}
					}
				driver.switchTo().parentFrame();
				logger.debug("Switched to the parent iframe ");
				}
			}		
			logger.debug("Is No records value: {}", isNoRecordsDisplayed);
			rowDTO=getTotalFromLink(pageLoaded,isNoRecordsDisplayed);
			totalRecords = rowDTO.getTotalRows();
			logger.debug("TOTAL ROWS IN {} DATA GRID ARE: {}",pageName, totalRecords);
			stop = System.nanoTime();
			//compute total times stale element occured
			int staleExceptionCount = 0;
			int timeOutExceptionCount = 0;
			if(rowDTO.isStaleElementExceptionOccured()){
				staleExceptionCount++;
			}
			if(isStaleElementExceptionOccured){
				staleExceptionCount++;
			}
			if(isTimeOutExceptionOccured){
				timeOutExceptionCount++;
			}
			
			logger.debug("Total stale elements: {}", staleExceptionCount);
			logger.debug("Has Time out exception occured: {}", isTimeOutExceptionOccured);
			if(rowDTO.isStaleElementExceptionOccured() || isStaleElementExceptionOccured 
					|| isTimeOutExceptionOccured){
				
			 timeToLoad = 
					new Long((TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)) 
							-(staleExceptionCount * TestConstants.WEBDRIVER_IMPLICIT_TIMEOUT_VALUE)
							-(timeOutExceptionCount * TestConstants.WEBDRIVER_TIMEOUT_VALUE)
							).toString() + " sec";
			}else{
				timeToLoad = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			}
			
			logger.info("TIME TAKEN FOR {} TO LOAD: {} ",sourceString, timeToLoad);
			
		}catch(NoSuchElementException exp){
			logger.info("NO SUCH ELEMENT EXPCEPTION");
		}catch(TimeoutException exp){
			stop = System.nanoTime();
			timeToLoad = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			logger.info("TIME OUT FOR {} : TimeOutTime: {} ",sourceString, timeToLoad);
			timeToLoad = "PAGE LOAD TIMED OUT";
		}	
	
		return driver;
	}
	
	/**
	 * Online report export method. Expects that the page invoking has already loaded and the export button is enabled.
	 */
	public boolean exportDataGrid(String sourceString, String pageName, String exportOption)
					throws NoExportOption{
		
		// wait until the content area is loaded
		pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className(CONTENT_AREA_CLASS_NAME)));
		logger.debug("CONTENT AREA LOADED");
		boolean isButtonFound = false;
		boolean isExportOptionFound=false;
		boolean isExportSucceded=false;
		try{
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
							logger.debug("FOUND THE {} IFRAME",pageName);
							
							List<WebElement> buttons = 
									driver.findElements(By.tagName(PageConstants.BUTTON_TAG));
							for (WebElement button : buttons) {
								List<WebElement> spans = button.findElements(By.tagName(PageConstants.SPAN_TAG));
								for (WebElement span : spans) {
									if(span.getText().equalsIgnoreCase(PageConstants.EXPORT_BUTTON_TEXT)){
										// found the export option.
										isButtonFound = true;
										// click export 
										BasePage.moveOverAndClick(button);
										
										WebElement popupDiv = 
												pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className(PageConstants.DOJO_POPUP_CLASSNAME)));
										logger.debug("FOUND THE EXPORT POPUP");
										List<WebElement> drpListItems =popupDiv.findElements(By.className(PageConstants.DOJO_POPUP_MENU_ITEM_CLASSNAME));
										logger.debug("TOTAL DROPDOWN LIST items: " + drpListItems.size());
											for (WebElement aListItem : drpListItems) {
												if(!aListItem.getText().isEmpty()){
													logger.debug("The LISt ITEM is: {}",aListItem.getText());
													if(aListItem.getText().equalsIgnoreCase(exportOption)){
														logger.debug("FOUND the Export option: {}",exportOption);
														isExportOptionFound=true;
														BasePage.moveOverAndClick(aListItem);
														// catch the success window
														WebElement popUpDialog = 
															pageLoaded
															.until(ExpectedConditions.presenceOfElementLocated(By.className(PageConstants.DOJO_DIALOG_POPUP_CLASSNAME)));
														logger.debug("GOT THE DIALOG WINDOW");
														WebElement title = popUpDialog.findElement(By.className("dijitDialogTitleBar"));
														if(title!=null){
															String titleOfDialog = title.getAttribute("title");
															logger.debug("TITLE OF THE POP DIALOG: {}", title.getAttribute("title"));
															if(titleOfDialog.contains("Success")){
																logger.debug("Export successful");
																isExportSucceded=true;
															}else{
																logger.debug("Export failed");
																isExportSucceded=false;
															}
														}
													}
												}
											}
										
										break;
									}
								}
								if(isButtonFound)
									break;
							}
//							Actions builder = new Actions(driver);
//							builder.moveToElement(xlsx).perform();
							
							driver.switchTo().parentFrame();
							logger.debug("Switched to the parent iframe ");
							break;
						}catch(NoSuchElementException exp){
							logger.info("NO SUCH ELEMENT EXPCEPTION IN EXPORT DATA GRID");
						}catch(TimeoutException exp){
							logger.info("TIMEOUT IN IN EXPORT DATA GRID");
						}
					}
				driver.switchTo().parentFrame();
				logger.debug("Switched to the parent iframe ");
				}
			}	
			if((!isButtonFound) || (!isExportOptionFound) ){
				throw new NoExportOption();
			}
		}catch(NoSuchElementException exp){
			logger.info("NO SUCH ELEMENT EXPCEPTION");
		}catch(TimeoutException exp){
			logger.info("TIME OUT FOR {} : TimeOutTime: {} ",sourceString, timeToLoad);
		}	
		
		return isExportSucceded;
	}	
}