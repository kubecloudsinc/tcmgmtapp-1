package com.cisco.cstg.autotools.tests;

import static org.junit.Assert.fail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.cstg.autotools.dao.TestStatusDao;
import com.cisco.cstg.autotools.domain.appdb.TestStatus;
import com.cisco.cstg.autotools.semantic.diagnostics.ValidationException;
import com.cisco.cstg.autotools.support.email.PostOffice;
import com.cisco.cstg.autotools.support.email.SystemPostOffice;
import com.cisco.cstg.autotools.support.exception.NoCustomerAndRoleInfo;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.pages.LoginPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
{
 "/applicationContext.xml",
 "/applicationContext-security.xml",
 "/spring-servlet.xml"
}
)

/**
 * Logs the time taken to load the various pages from the browser as experienced by an end user of the applciation. 
 * The class will use selenium webdriver to simulate the end user browser experience.
 *     The results are sent out to the configured users in an email.
 *  
 * @author sichituk
 *
 */
public class CopyOfSNTCPageLoadTimeTest extends CopyOfBaseTest {
	
	protected static final Logger logger = LoggerFactory.getLogger(CopyOfSNTCPageLoadTimeTest.class);
	
	@Autowired
	TestStatusDao testStatusDao; 
	
	@Before
	public void setUp() throws Exception {
		openFireFoxProfileDriverWithoutAuth(BasePage.SNTC_FIREFOX_PROFILE_NAME);
	}	
	
	@After
	public void tearDown() throws Exception {
	    driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}	

	@Test
	public void testPageLoadTime() throws Exception {
		try{
			
			Map<String,String> insertions = new TreeMap<String,String>();
			
			// TODO write a method that will get the testStatus by name of the test which by convention should be the class name
			//      here the class name is com.cisco.cstg.autotools.tests.PageLoadTimeTest
			TestStatus testStatus = testStatusDao.getByTestId(new Long(102));
			testStatus.setTestStatus("Running");
			testStatusDao.save(testStatus);			
		//start the test
		// insert the user name
		insertions.put("user_name", BasePage.username);
		WebDriverWait pageLoaded = new WebDriverWait(driver, 60);
		long start = System.nanoTime();	
		long startOfWholeTest = System.nanoTime();
		driver = new LoginPage(driver).loginToPortal(BasePage.baseUrl, BasePage.username, BasePage.pwd);
		driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		long stop = System.nanoTime();
		String timeTakenToLogin = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
		insertions.put("time_taken_to_login", timeTakenToLogin);

		logger.info("TIME TAKEN FOR LOGIN: "+timeTakenToLogin);
		/**
		 *IF the welcome window opens up then this code will come into play 
		try{
			logger.info("Waiting for the ok button");
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
												.withTimeout(7, TimeUnit.SECONDS)
												.pollingEvery(1, TimeUnit.SECONDS)
												.ignoring(NoSuchElementException.class);
			WebElement OKButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("xwt_widget_form_TextButton_2")));
			logger.info("Found the Button Field");
			OKButton.click();
			stop = System.nanoTime();
			String timeTakenToDisplayWP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_for_wcpage", timeTakenToDisplayWP);
			logger.info("TIME TAKEN FOR Accept form to show up: "+timeTakenToDisplayWP);
		}catch(NoSuchElementException exp){
			insertions.put("time_taken_for_wcpage", "0 sec");
			stop = System.nanoTime();
			String timeTakenToDisplayWP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			logger.info("NO SUCH BUTTON EXISTS: OK BUTTON: no such element time taken: "+timeTakenToDisplayWP);
		}catch(TimeoutException exp){
			insertions.put("time_taken_for_wcpage", "0 sec");
			String timeTakenToDisplayWP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			logger.info("TIME OUT EXCEPTION:  time taken: "+timeTakenToDisplayWP);
		}
		*/
		start = System.nanoTime();
		logger.info("STARTING LNP TIMING");
		try{
			logger.info("WAITING FOR THE MAIN IFRAME workspaceViewFrame TO LOAD");
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
			logger.info("FOUND MAIN IFRAME workspaceViewFrame AND SWITCHED TO IT");
			
			WebElement appSettings = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Application Settings')]")));
			
			Coordinates coordinate = ((Locatable)appSettings).getCoordinates(); 
			coordinate.onPage(); 
			coordinate.inViewPort();
			logger.info("FOUND APPLICATION SETTING LINK "+coordinate.inViewPort());

			stop = System.nanoTime();
			String timeTakenToLoadLNP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_load_lnp", timeTakenToLoadLNP);
			logger.info("TIME TAKEN FOR LEFT NAVIGATION TO LOAD: "+timeTakenToLoadLNP);		
			
			logger.info("ABOUT TO CLICK APPLICATION SETTING LINK");
			appSettings.click();
			logger.info("CLICKED APP SETTINGS LINK");
			logger.info("WAITING FOR THE APP SETTINGS OUTER IFRAME TO LOAD");
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
			logger.info("FOUND APP SETTINGS OUTER IFRAME AND SWITCHED TO IT");
	
			// get the div elements that contain the customer name and role info
			WebElement divElement = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("appsettings")));
			logger.info("WAITING FOR THE APP SETTINGS INNER IFRAME TO LOAD");
			WebElement innerIframe = divElement.findElement(By.tagName("iframe"));
			pageLoaded.until(ExpectedConditions.visibilityOf(innerIframe));
			logger.info("FOUND APP SETTINGS OUTER IFRAME AND SWITCHING TO IT");
			driver.switchTo().frame(innerIframe);
			start = System.nanoTime();
			boolean foundRoleDiv=false;
			while( !foundRoleDiv && (new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() <= 15)){
				List<WebElement> divs = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("col-xs-6")));
				if(divs!=null){
						StringBuffer roles = new StringBuffer();
						int count=0;
						for (WebElement webElement : divs) {
							 if(webElement.getText().contains("Role:")){
								 if(count==0){
									 roles.append(webElement.getText().replaceAll("Role:", "").trim());
									 foundRoleDiv = true;
									 count++;
								 }else{
									  roles.append("; ");
									  roles.append(webElement.getText().replaceAll("Role:", "").trim());
								 }
							  }
							}
						logger.debug(" user roles are:"+roles.toString());
						insertions.put("user_roles", roles.toString());
				}
				stop = System.nanoTime();
				
			}
			if(new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() >= 15){
				// no customer info
				insertions.put("user_roles", "APPLICATION SETTINGS LNP DOES'T HAVE ROLE INFO");				
			}
			driver.switchTo().defaultContent();
			logger.info("SWITCHING BACK TO MAIN IFRAME workspaceViewFrame");		
	}catch(NoSuchElementException exp){
		logger.info("CANNOT FIND APP SETTINGS LINK");
		insertions.put("time_taken_to_load_lnp", "LNP NOT LOADING");
	}catch(TimeoutException exp){
		stop = System.nanoTime();
		String timeTakenToLoadLNP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
		logger.info("TIME OUT ON FINDING APP SETTINGS ELEMENT: TimeOutTime: "+timeTakenToLoadLNP);
		insertions.put("time_taken_to_load_lnp", "TIME OUT ON FINDING APP SETTING LINK, PAGE NOT LOADING");
	}

	// get the customre and inventory filter info 
		logger.info("TRYING TO EXTRACT INFO FROM HEADER");
	try{
		
		driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
		WebElement headerContainerDiv = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("nmvHeaderToolbarContainer")));
		 List<WebElement> filterValueDivs = headerContainerDiv.findElements(By.className("filtervalue"));
		 WebElement customerFilterDiv=null;
		 WebElement inventoryFilterDiv=null;
		 boolean filterNotSetToTotal=true;
		 for (WebElement filterValueDiv : filterValueDivs) {
			if(filterValueDiv.getAttribute("title").equalsIgnoreCase("customer")){
				insertions.put("customer_name", filterValueDiv.getText().trim());
				logger.info("CUSTOMER NAME IS: "+filterValueDiv.getText().trim());
				customerFilterDiv = filterValueDiv;
			}else if(filterValueDiv.getAttribute("title").equalsIgnoreCase("inventory")){
				inventoryFilterDiv = filterValueDiv;
				// check if inventory is set to all.
				if(filterValueDiv.getText().contains("All"))
					filterNotSetToTotal = false;
			}
		}
		// find the total number of inventories and their names
		// by clicking on second drop down and getting the list values.
		// find the drop down div. 
		if(customerFilterDiv==null){
			insertions.put("customer_name", "THE GLOBAL FILTERS NOT LOADING; CANNOT GET THE CUSTOMER NAME");
		}
		if(inventoryFilterDiv!=null){ 
			Actions builder = new Actions(driver);
			builder.moveToElement(inventoryFilterDiv).perform();
			logger.debug("About to click the drop down arrow");
			builder.click().perform();
			List<WebElement> drpListItems = driver.findElements(By.xpath("//ul[@id='widgets_EnhancedCheckedComboBox_2_popup']/li"));
			logger.debug("Number of menu items: " + drpListItems.size());
			List<String> inventoryNames = new ArrayList<String>();
			StringBuffer inventories = new StringBuffer();
			for (WebElement aListItem : drpListItems) {
				if(!aListItem.getText().isEmpty()){
					inventoryNames.add(aListItem.getText());
					if(aListItem.getText().contains("All")){
						String totalInventories = aListItem.getText().replaceAll("All", "").replace("(", "").replace(")", "").trim();
						logger.info("TOTAL NUMBER OF INVENTORIES ARE: "+totalInventories); 
					    insertions.put("total_inventories", totalInventories);
					    if(filterNotSetToTotal){
					    	// inventory value is not total inventories then check this box.
							builder.moveToElement(aListItem).perform();
							logger.debug("About to select the total value");
							builder.click().perform();
					    }
					}else{
						inventories.append(aListItem.getText().trim());
						inventories.append("; ");
					}
				}
			}
			logger.info("THE INVENTORY NAMES ARE: "+inventories.toString().trim());
			insertions.put("inventory_names", inventories.toString().trim());
			builder.moveToElement(inventoryFilterDiv).perform();
			logger.debug("About to click the drop down arrow");
			builder.click().perform();

		}else{
			insertions.put("total_inventories", "THE GLOBAL FILTERS NOT LOADING; CANNOT GET TOTAL NUMBER OF INVENTORIES");
			insertions.put("inventory_names", "THE GLOBAL FILTERS NOT LOADING; CANNOT GET INVENTORY NAMES");
		}
	}catch(NoSuchElementException exp){
		logger.info("NO SUCH ELEMENT EXPCEPTION: CANNOT FIND THE GLOBAL FILTERS PROBLEM");
		insertions.put("customer_name", "GLOBAL FILTERS NOT LOADING HENCE CANNOT FIND CUSTOMER NAME");
	}catch(TimeoutException exp){
		logger.info("TIME OUT ON FINDING THE HEADER CONTAINER ELEMENT: TimeOutTime: ");
		
	}		
	driver.switchTo().defaultContent();
	logger.info("SWITCHING BACK TO MAIN IFRAME workspaceViewFrame");		

		try{
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));			
			logger.info("FOUND MAIN IFRAME workSpaceViewFrame");
			WebElement sntc = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Smart Net Total Care')]")));
			Coordinates coordinate = ((Locatable)sntc).getCoordinates(); 
			coordinate.onPage(); 
			coordinate.inViewPort();
			logger.info("FOUND SNTC LINK "+coordinate.inViewPort());
			sntc.click();
			logger.info("CLICKED SNTC DASHBOARD LINK");
		}catch(NoSuchElementException exp){
			logger.info("NO SUCH ELEMENT EXPCEPTION");
			insertions.put("time_taken_to_load_lnp", "LNP NOT LOADING");
		}catch(TimeoutException exp){
			logger.info("TIME OUT ON FINDING THE SNTC ELEMENT: TimeOutTime: ");
			insertions.put("time_taken_to_load_lnp", "TIME OUT ON FINDING SNTC LINK, PAGE NOT LOADING");
		}

		start = System.nanoTime();
		
		boolean inv_loaded = false;
		boolean alerts_loaded = false;
		boolean contracts_loaded = false;
		boolean widgetNotLoaded = false;
		
		while(!(inv_loaded && alerts_loaded && contracts_loaded) && 
				(new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() <= 600)){
			try {
				//wait till the content area loads
				pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("main.container")));
				logger.debug("Waiting for the iframe to be to be present");
				List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
				logger.info("Total number of iframes: "+ iframes.size());
            if(iframes.size()>=3){
				for (WebElement iframeInstance : iframes){
				
					String src = iframeInstance.getAttribute("src");
					logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
					driver.switchTo().frame(iframeInstance);				
					if(src.contains("IMS_ALL_DEVICES_DUP")){
						logger.info("FOUND INVENTORY IFRAME");
						try{
							pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Equipment Type')]")));
							logger.info("INVENTORY IFRAME LOADING COMPLETE");
							inv_loaded = true;
							}catch(NoSuchElementException exp){
								logger.info("NO SUCH ELEMENT EXPCEPTION");
							}catch(TimeoutException exp){
								logger.info("TIME OUT ON LOADING INVENTORY SCREEN");
								widgetNotLoaded = true;
								break;
							}
					}else if(src.contains("ALERTSDELTA_BSO")){
						logger.info("FOUND THE ALERTS IFRAME");
						try{
							pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Type')]")));
							logger.info("ALERTS IFRAME LOADING COMPLETE");
							alerts_loaded = true;
							}catch(NoSuchElementException exp){
								logger.info("NO SUCH ELEMENT EXPCEPTION");
							}catch(TimeoutException exp){
								logger.info("TIME OUT ON LOADING ALERTS WIDGET");
								widgetNotLoaded = true;
								break;
							}
					}else if(src.contains("bso_item_list")){
						logger.info("FOUND CONTRACTS IFRAME");
						try{
//							WebDriverWait wait = new WebDriverWait(driver, 7);
							pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Coverage')]")));
							logger.info("CONTRACTS IFRAME LOADING COMPLETE");
							contracts_loaded = true;
							}catch(NoSuchElementException exp){
								logger.info("NO SUCH ELEMENT EXPCEPTION");
							}catch(TimeoutException exp){
								logger.info("TIME OUT ON LOADING COVERAGE WIDGET");
								widgetNotLoaded = true;
								break;
							}
					}
					driver.switchTo().parentFrame();
					logger.info("SWITCHED TO PARENT IFRAME");
				}
				if(widgetNotLoaded){ 
					stop = System.nanoTime();
					String timeTakenToLoadSNTC = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
					insertions.put("time_taken_to_load_sntc", "ATLEAST ONE WIDGET NOT LOADED AND TIMED OUT");
					logger.info("TIME OUT FOR SNTC DASHBOARD LOAD FAILURE: TimeOutTime: "+ timeTakenToLoadSNTC);
					break;}
			}
				stop = System.nanoTime();
				String timeTakenToLoadSNTC = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_sntc", timeTakenToLoadSNTC);
				logger.info("TIME TAKEN FOR SNTC DASHBOARD TO LOAD: "+ timeTakenToLoadSNTC);		
			} catch (Exception e) {
				stop = System.nanoTime();
				String timeTakenToLoadSNTC = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				logger.info("EXCEPTION IN SNTC DASHBOARD LOAD: TimeOutTime: "+ timeTakenToLoadSNTC);
    		       insertions.put("time_taken_to_load_sntc", "PAGE NOT LOADED");
    				final Writer result = new StringWriter();
    				final PrintWriter printWriter = new PrintWriter(result);
    				e.printStackTrace(printWriter);
    				logger.info(result.toString());
			}	
		}
		if((new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() >= 600)){
			insertions.put("time_taken_to_load_sntc", "PAGE NOT LOADED-FATAL ERROR? ITS BEEN MORE THAN 10 mins");
		}

				
		driver.switchTo().defaultContent();
		logger.info("SWITCHING BACK TO MAIN IFRAME workspaceViewFrame");		
		
			//START OF ALL ALERTS
			try{
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
				logger.info("FOUND MAIN IFRAME");
				WebElement allAlerts = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'All Alerts')]")));
				logger.info("FOUND THE ALL ALERTS");
				Coordinates coordinate = ((Locatable)allAlerts).getCoordinates(); 
//				coordinate.onPage(); 
				Point allAlertsLocation = coordinate.inViewPort();
				logger.debug("Location of All alerts: "+allAlertsLocation.toString()+ " displayed: "+allAlerts.isDisplayed());
//				Actions actions = new Actions(driver);
//				actions.moveToElement(allAlerts).click().perform();
				allAlerts.click();
				start = System.nanoTime();
				logger.info("Clicked the ALERTS element");
				//wait till the content area loads
				pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("main.container")));
				List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
				logger.info("Total number of iframes: "+ iframes.size());
				for (WebElement iframeInstance : iframes){
				
					String src = iframeInstance.getAttribute("src");
					logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
					driver.switchTo().frame(iframeInstance);				
					if(src.contains("SNTC_ALERTSDELTA")){
						logger.info("FOUND THE ALERTS IFRAME");
							pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ssueNonzerolinkto")));
							logger.info("DATA GRID TABLE LOADED");
					}
					driver.switchTo().parentFrame();
					logger.info("Switched to the iframe WorkspaceViewIFrame");
				}
				stop = System.nanoTime();
				String timeTakenToLoadAllAlerts = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllAlerts", timeTakenToLoadAllAlerts);
				logger.info("TIME TAKEN FOR ALL ALERTS TO LOAD: "+timeTakenToLoadAllAlerts);
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadAllAlerts = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllAlerts", "PAGE NOT LOADING");
				logger.info("TIME OUT FOR ALL ALERTS WIDGET: TimeOutTime: "+timeTakenToLoadAllAlerts);
			}
			driver.switchTo().defaultContent();
			logger.info("SWITCHING BACK TO MAIN IFRAME workspaceViewFrame");	
			//START OF ALL CONTRACTS
			try{
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
				logger.info("FOUND MAIN IFRAME");
				WebElement allContracts = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'All Contracts')]")));
				logger.info("FOUND THE ALL CONTRACTS");
				Coordinates coordinate = ((Locatable)allContracts).getCoordinates(); 
				coordinate.onPage(); 
				coordinate.inViewPort();
				allContracts.click();
				start = System.nanoTime();
				logger.info("Clicked the ALL CONTRACTS element");
				//wait till the content area loads
				pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("main.container")));
				List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
				logger.info("Total number of iframes: "+ iframes.size());
				for (WebElement iframeInstance : iframes){
				
					String src = iframeInstance.getAttribute("src");
					logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
					driver.switchTo().frame(iframeInstance);				
					if(src.contains("50002_CONTRACT")){
						logger.info("FOUND THE CONTRACTS IFRAME");
							pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ssueNonzerolinkto")));
							logger.info("DATA GRID TABLE LOADED");
					}
					driver.switchTo().parentFrame();
					logger.info("Switched to the iframe Workspace");
				}
				stop = System.nanoTime();
				String timeTakenToLoadAllContracts = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllContracts", timeTakenToLoadAllContracts);
				logger.info("TIME TAKEN FOR ALL CONTRACTS TO LOAD: "+timeTakenToLoadAllContracts);
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadAllContracts = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllContracts", "PAGE NOT LOADING");
				logger.info("TIME OUT ON ALL CONTRACTS: TimeOutTime: "+timeTakenToLoadAllContracts);
			}
			
			driver.switchTo().defaultContent();
			logger.info("SWITCHING BACK TO MAIN IFRAME workspaceViewFrame");	
			//START OF ALL EQUIPMENTS
			try{
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
				logger.info("FOUND MAIN IFRAME");
				WebElement allEquip = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'All Equipment')]")));
				logger.info("FOUND THE ALL EQUIP");
				Coordinates coordinate = ((Locatable)allEquip).getCoordinates(); 
				coordinate.onPage(); 
				coordinate.inViewPort();
				allEquip.click();
				start = System.nanoTime();
				logger.info("Clicked the ALL EQUIPMENTS element");
				//wait till the content area loads
				pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("main.container")));
				List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
				logger.info("Total number of iframes: "+ iframes.size());
				for (WebElement iframeInstance : iframes){
					String src = iframeInstance.getAttribute("src");
					logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
					driver.switchTo().frame(iframeInstance);				
					if(src.contains("IMS_ALL_DEVICES")){
						logger.info("FOUND THE EQUIP IFRAME");
							pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ssueNonzerolinkto")));
							logger.info("ALL EQUIPMENTS DATA GRID LOADED");
					}
					driver.switchTo().parentFrame();
					logger.info("Switched to the iframe Workspace");
				}
				stop = System.nanoTime();
				String timeTakenToLoadAllEquip = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllEquip", timeTakenToLoadAllEquip);
				logger.info("TIME TAKEN FOR ALL EQUIP TO LOAD: "+timeTakenToLoadAllEquip);
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadAllEquip = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllEquip", "PAGE NOT LOADING");
				logger.info("TIME OUT ON ALL EQUIP: TimeOutTime: "+timeTakenToLoadAllEquip);
			}
			driver.switchTo().defaultContent();
			
			long stopOfWholeTest = System.nanoTime();
			logger.info("TOTAL TIME TAKEN FOR ALL STEPS SO FAR: "+TimeUnit.SECONDS.convert((stopOfWholeTest - startOfWholeTest), TimeUnit.NANOSECONDS)+ " sec");
			driver.switchTo().defaultContent();
			
			// test complete
			testStatus.setTestStatus("Passed");

			PostOffice po = new SystemPostOffice();
			
			insertions.put("report_date", (new Date()).toString());
			String [] toAddresses = TestConstants.toEmailAddresses.split(",");
			String [] ccAddresses = null;
			try {
				po.sendEmail(TestConstants.pageloadReportTemplateName, insertions, toAddresses, ccAddresses);
				logger.info("EMAIL SENT SUCCESSFULLY");
			} catch (ValidationException e) {
				e.printStackTrace();
			}
			//update the database with the test result.
			testStatusDao.save(testStatus);
//			testStatusDao.clearSessions();
		}
		catch(Exception exp){
		  logger.info("EXCEPTION IN GETTING THE DAO");
		  TestStatus testStatus = testStatusDao.getByTestId(new Long(100));
			testStatus.setTestStatus("Passed");
			testStatusDao.save(testStatus);
			testStatusDao.clearSessions();
		    final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    exp.printStackTrace(printWriter);
		    logger.info(result.toString());
		}

	}
	
	public String[] getUserRoles() throws 
	 						NoCustomerAndRoleInfo{
		List<String> roles = new ArrayList<String>();
		try{
			WebDriverWait pageLoaded = new WebDriverWait(driver, 60);
			logger.info("PAGE LOAD FOR MAIN IFRAME.. WAITING...");
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
			logger.info("FOUND MAIN IFRAME");
			
			WebElement appSettings = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Application Settings')]")));
			
			Coordinates coordinate = ((Locatable)appSettings).getCoordinates(); 
			coordinate.onPage(); 
			coordinate.inViewPort();
			logger.info("FOUND APPLICATION SETTING LINK "+coordinate.inViewPort());
			logger.info("ABOUT TO CLICK APPLICATION SETTING LINK");
			appSettings.click();
			logger.info("CLICKED APP SETTINGS LINK");
//			List<WebElement> iframes = pageLoaded.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
//				logger.info(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
//				logger.debug("The attributes of the iframe are: "+iframeInstance.getAttribute("class")+
//						" "+iframeInstance.getAttribute("width")+
//						" "+iframeInstance.getAttribute("height")+
//						" "+iframeInstance.getAttribute("src")+
//						" "+iframeInstance.getAttribute("seamless"));
//				logger.info("FOUND APP SETTINGS IFRAME");				
//				driver.switchTo().frame(iframeInstance);

			WebElement divElement = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("appsettings")));
				logger.debug("Div element found: "+ divElement.getAttribute("ng-show")+" "+divElement.getAttribute("style")+" "+divElement.getTagName());
				WebElement innerIframe = divElement.findElement(By.tagName("iframe"));
				pageLoaded.until(ExpectedConditions.visibilityOf(innerIframe));
				logger.debug("The attributes of the iframe are: "+innerIframe.getAttribute("class")+
						" "+innerIframe.getAttribute("width")+
						" "+innerIframe.getAttribute("height")+
						" "+innerIframe.getAttribute("src")+
						" "+innerIframe.getAttribute("seamless"));
				logger.info("FOUND INNER IFRAME");
				driver.switchTo().frame(innerIframe);
				List<WebElement> divs = pageLoaded.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("col-xs-6")));
				int count =0;
				if(divs.size()>=4){
					for (WebElement webElement : divs) {
							  logger.debug("The text in the div: "+webElement.getText());
							  if(count>=2){
								  roles.add(webElement.getText().replaceAll("Role:", "").trim());
								  logger.debug("The String added: "+roles.get(count-2));
							  }
							  count++;
						}
				}else{
					throw new NoCustomerAndRoleInfo();
				}
				driver.switchTo().defaultContent();
				logger.info("Switched to the iframe Workspace");
		}catch(NoSuchElementException exp){
			logger.info("NO SUCH ELEMENT EXPCEPTION");
		}catch(TimeoutException exp){
			logger.info("TIME OUT ON FINDING THE SNTC ELEMENT: TimeOutTime: ");
		}
		
		return (String []) roles.toArray(new String[roles.size()]);
	}
	
//	private String[] getInventoryNames(){
//		String[] inventoryNames = new String[]{"Demo_Richardson_DC","Demo_SanJose_HQ"};
//		return inventoryNames;
//	}
}