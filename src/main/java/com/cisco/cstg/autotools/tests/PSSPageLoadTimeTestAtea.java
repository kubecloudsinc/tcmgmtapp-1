package com.cisco.cstg.autotools.tests;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.cstg.autotools.dao.TestStatusDao;
import com.cisco.cstg.autotools.domain.appdb.TempUser;
import com.cisco.cstg.autotools.domain.appdb.TestStatus;
import com.cisco.cstg.autotools.semantic.diagnostics.ValidationException;
import com.cisco.cstg.autotools.support.email.PostOffice;
import com.cisco.cstg.autotools.support.email.SystemPostOffice;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.pages.LoginPage;
import com.cisco.cstg.autotools.tests.pages.PSSLeftNavigationPage;

//@RunWith(Parameterized.class)

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
public class PSSPageLoadTimeTestAtea extends BaseTest {
	
	private final Logger logger = LoggerFactory.getLogger(PSSPageLoadTimeTestAtea.class);
//	private TestContextManager testContextManager;
	
//	private String partnerName;
	
	@Autowired
	TestStatusDao testStatusDao; 
	
//	@BeforeClass
//	public void setUpContext(){
//		
//		try {
//			logger.debug("INSIDE BEFORE CLASS METHOD ");
//			//@RunWith(SpringJUnit4ClassRunner.class)
//			this.testContextManager = new TestContextManager(getClass());
//			this.testContextManager.prepareTestInstance(this);
//		} catch (Exception e) {
//			e.printStackTrace();
//			final Writer result = new StringWriter();
//			final PrintWriter printWriter = new PrintWriter(result);
//			e.printStackTrace(printWriter);
//			logger.debug(result.toString());
//		}
//	}
	
	@Before
	public void setUp() throws Exception {
		logger.info("INSIDE THE SETUP");
		openFireFoxProfileDriverWithoutAuth(BasePage.PSS_FIREFOX_PROFILE_NAME);
	}

//	/**
//	 * Constructor for the test
//	 * @param partnerName
//	 */
//	public PSSPageLoadTimeTest() throws Exception{
//
//		logger.debug("INSIDE THE CONSTRUCTOR");
//		this.setPartnerName("ATEA AS");
//	}

//	@Parameters
//	public static Collection<String> partnerNames(){
//
//		TestConstants.logger.info("INITIALIZING THE PARTNER LIST");
//		 return Arrays.asList(new String [] {
//					"ATEA AS",
//					"CONSCIA AS",
//					"DIMENSION DATA NORTH AMERICA INC"
//			});
//	}

	@Test
	public void testPageLoadTime() throws Exception {
		logger.debug("INSIDE THE TEST");
		try{
		
			Map<String,String> insertions = new TreeMap<String,String>();
			// TODO write a method that will get the testStatus by name of the test which by convention should be the class name
			//      here the class name is com.cisco.cstg.autotools.tests.PageLoadTimeTest
			TestStatus testStatus = testStatusDao.getByTestId(new Long(103));
			testStatus.setTestStatus("Running");
			testStatusDao.save(testStatus);			
			
			//start the test
			
			// insert the user name
			
			long start = System.nanoTime();	
			long startOfWholeTest = System.nanoTime();
			long stopOfWidget = System.nanoTime();
			
			String partnerName = "ATEA AS";
			
			logger.info("STARTING THE TEST: ATTEMPTING TO LOGIN");
			 
			String pssUserName = BasePage.pssUsername;
			String pssPwd = BasePage.pssPwd;
			
			insertions.put("user_name", pssUserName);
			insertions.put("user_roles", "CustomerAdmin");
			String userRole = "CustomerAdmin";
			logger.info("INITIAL USERNAME: {}",pssUserName, pssPwd);
			//get the user info give dynamically during running of the test
			logger.debug("loading the temp user config");
			File file = new File(Thread.currentThread().getContextClassLoader().getResource(TempUser.class.getName()+".cfg").getFile());
			List<String> lines = FileUtils.readLines(file);
			if(lines!=null && lines.size()==2){
				pssUserName = lines.get(0);
				pssPwd = lines.get(1);
				insertions.put("user_name", "PSS Global Application Admin");
				insertions.put("user_roles", "Employee");
				userRole = "Employee";
			}
			logger.info("AFTER LOADING CONFIG USERNAME: {}",pssUserName);
			logger.debug("End the user config");
			
			driver = new LoginPage(driver).loginToPortal(BasePage.baseUrl, pssUserName, pssPwd);
			driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			long stop = System.nanoTime();
			String timeTakenToLogin = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_login", timeTakenToLogin);
			logger.info("TIME TAKEN FOR LOGIN: "+timeTakenToLogin);
			
			WebDriverWait pageLoaded = new WebDriverWait(driver, 60);

			logger.info("SWITCHING TO MAIN IFRAME workspaceViewFrame");		
			start = System.nanoTime();
		
			try{
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));			
				logger.info("FOUND MAIN IFRAME workSpaceViewFrame");
				WebElement usefulLinks = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id(BasePage.USEFUL_LINKS)));
				
				stop = System.nanoTime();
				String timeTakenToLoadLNP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_lnp", timeTakenToLoadLNP);
				logger.info("TIME TAKEN FOR LEFT NAVIGATION TO LOAD: "+timeTakenToLoadLNP);		
				
				BasePage.moveOverAndClick(usefulLinks);
				logger.info("CLICKED UseFul Links ");
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
				insertions.put("time_taken_to_load_lnp", "LNP NOT LOADING");
			}catch(TimeoutException exp){
				logger.info("TIME OUT ON FINDING THE SNTC ELEMENT: TimeOutTime: ");
				insertions.put("time_taken_to_load_lnp", "TIME OUT ON FINDING useful LINK, PAGE NOT LOADING");
			}
			//start with the useful links page.
			logger.info("STARTING THE LOAD TIME CAPTURING FOR PARTNER: {}", partnerName);
			try{
				driver.switchTo().defaultContent();
				logger.info("SWITCHED BACK TO MAIN PAGE CONTENT");
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));			
				logger.info("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
				WebElement usefulLinks = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id(BasePage.USEFUL_LINKS)));
				BasePage.moveOverAndClick(usefulLinks);
				logger.info("CLICKED: UseFul Links ");
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				logger.info("TIME OUT ON FINDING THE SNTC ELEMENT: TimeOutTime: ");
			}		

			// choose the partner, customer as all, inventory as all.
			logger.info("TRYING TO EXTRACT INFO FROM GLOBAL FILTERS");
			selectPartner(pageLoaded, insertions, partnerName);
			logger.info("PARTNER SELECTION COMPLETED");
			selectCustomer(pageLoaded, insertions);
			logger.info("CUSTOMER SELECTION COMPLETED");
			selectInventory(pageLoaded, insertions);
			logger.info("INVENTORY SELECTION COMPLETED");
			
			//START OF CUSTOMER SUMMARY
			try{
				driver.switchTo().defaultContent();
				logger.info("SWITCHED BACK TO MAIN PAGE CONTENT");
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
				logger.info("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
				WebElement customerSummary = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id(PSSLeftNavigationPage.CUSTOMER_SUMMARY)));
				logger.info("FOUND THE CUSTOMER SUMMARY LINK");
				
				BasePage.moveOverAndClick(customerSummary);

				start = System.nanoTime();
				logger.info("CLICKED THE CUSTOMER SUMMARY LINK");

				boolean isCustomerSummaryFound=false;
				while(!(isCustomerSummaryFound) && 
							(new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() <= 120)){				
					//wait till the content area loads
					pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("main.container")));
					List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
					logger.info("Total number of iframes: "+ iframes.size());
					
					if(iframes.size()>1){
						for (WebElement iframeInstance : iframes){
							if(isCustomerSummaryFound==false){
								String src = iframeInstance.getAttribute("src");
								logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
								logger.debug("SWITCHING TO INNER IFRAME");
								driver.switchTo().frame(iframeInstance);				
								if(src.contains("CUSTOMER_SUMMARY")){
									logger.info("FOUND THE CUSTOMER SUMMARY");
										pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("dojoxGridRowTable")));
										logger.info("DATA GRID TABLE LOADED");
										isCustomerSummaryFound=true;
								}
								driver.switchTo().parentFrame();
								logger.debug("SWITCHING BACK TO PARENT IFRAME");
							}else{
								break;
							}
						}
					}
				}
				stop = System.nanoTime();
				String timeTakenToLoadCustomerSummary = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_CustomerSummary", timeTakenToLoadCustomerSummary);
				logger.info("TIME TAKEN FOR CUSTOMER SUMMARY TO LOAD: "+timeTakenToLoadCustomerSummary);
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadCustomerSummary = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_CustomerSummary", "PAGE NOT LOADING");
				logger.info("TIME OUT FOR CUSTOMER SUMMARY WIDGET: TimeOutTime: "+timeTakenToLoadCustomerSummary);
			}
			
			//START OF ENGINEER
			try{
				driver.switchTo().defaultContent();
				logger.info("SWITCHED BACK TO MAIN PAGE CONTENT");
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
				logger.info("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
				WebElement engineer = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id(PSSLeftNavigationPage.ENGINEER)));
				logger.info("FOUND THE ENGINEER LINK");
				
				BasePage.moveOverAndClick(engineer);
				logger.info("CLICKED THE ENGINEER LINK");
				start = System.nanoTime();
				
				boolean collections_loaded = false;
				boolean psirts_loaded = false;
				boolean notices_loaded = false;
				boolean failedInv_loaded = false;
				boolean widgetNotLoaded = false;
				
				try {
				while(!(collections_loaded && psirts_loaded && notices_loaded && failedInv_loaded) && 
						(new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() <= 300)){
						// try this loop for atleast 5 minutes
					
						//wait till the content area loads
						pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("main.container")));
						logger.debug("Waiting for the iframe to be to be present");
						List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
						logger.debug("Total number of iframes: "+ iframes.size());
		            if(iframes.size()>=4 ){
						for (WebElement iframeInstance : iframes){
							 if( !(collections_loaded && psirts_loaded && notices_loaded && failedInv_loaded)){
								String src = iframeInstance.getAttribute("src");
								logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
								logger.debug("the src attribute is: "+src);
		//   CUSTOMERS_PSIRT_LAST_30
								driver.switchTo().frame(iframeInstance);				
								if(src.contains("CUSTOMERS_PSIRT_LAST_30")){
									logger.info("FOUND PSIRT IFRAME");
									try{
										pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueBillboardLabel")));
										logger.info("PSIRT IFRAME LOADING COMPLETE");
										psirts_loaded = true;
										stopOfWidget = System.nanoTime();
										String timeTakenToLoadCriticalPSIRTS = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
										insertions.put("time_taken_criticalPSIRTS", timeTakenToLoadCriticalPSIRTS);
										}catch(NoSuchElementException exp){
											logger.info("NO SUCH ELEMENT EXPCEPTION: PSIRT");
										}catch(TimeoutException exp){
											logger.info("TIME OUT ON LOADING PSIRT SCREEN");
											widgetNotLoaded = true;
											insertions.put("time_taken_criticalPSIRTS", "WIDGET NOT LOADED");	
											break;
										}
								}
								else if(src.contains("FAILED_INVENTORY_PROCESSING")){
		//   FAILED_INVENTORY_PROCESSING
	
									logger.info("FOUND THE FAILED INVENTORY IFRAME");
									try{
										pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueBillboardLabel")));
										logger.info("FAILED INVENTORY IFRAME LOADING COMPLETE");
										failedInv_loaded = true;
										stopOfWidget = System.nanoTime();
										String timeTakenToLoadFailedInv = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
										insertions.put("time_taken_failedInv", timeTakenToLoadFailedInv);									
										}catch(NoSuchElementException exp){
											logger.info("NO SUCH ELEMENT EXPCEPTION");
										}catch(TimeoutException exp){
											logger.info("TIME OUT ON LOADING FAILED INVENTORY WIDGET");
											widgetNotLoaded = true;
											insertions.put("time_taken_failedInv", "WIDGET NOT LOADED");										
											break;
										}
								}
								else if(src.contains("CUSTOMERS_FIELD_LAST_30")){
		//   CUSTOMERS_FIELD_LAST_30						
									logger.info("FOUND FEILD NOTICE IFRAME");
									try{
										pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueBillboardLabel")));
										logger.info("FEILD NOTICE IFRAME LOADING COMPLETE");
										notices_loaded = true;
										stopOfWidget = System.nanoTime();
										String timeTakenToLoadFieldNotices = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
										insertions.put("time_taken_fieldNotices", timeTakenToLoadFieldNotices);																		
										}catch(NoSuchElementException exp){
											logger.info("NO SUCH ELEMENT EXPCEPTION");
										}catch(TimeoutException exp){
											logger.info("TIME OUT ON LOADING NOTICES WIDGET");
											widgetNotLoaded = true;
											insertions.put("time_taken_fieldNotices", "WIDGET NOT LOADED");
											break;
										}
								}
								else if(src.contains("NEW_COLLECTIONS_AVAIL")){
		//   NEW_COLLECTIONS_AVAIL																			
									logger.info("FOUND COLLECTIONS IFRAME");
									try{
										pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueBillboardLabel")));
										logger.info("COLLECTIONS IFRAME LOADING COMPLETE");
										collections_loaded = true;
										stopOfWidget = System.nanoTime();
										String timeTakenToLoadNewCollections = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
										insertions.put("time_taken_newCollections", timeTakenToLoadNewCollections);																											
										}catch(NoSuchElementException exp){
											logger.info("NO SUCH ELEMENT EXPCEPTION");
										}catch(TimeoutException exp){
											logger.info("TIME OUT ON LOADING NOTICES WIDGET");
											widgetNotLoaded = true;
											insertions.put("time_taken_newCollections", "WIDGET NOT LOADED");
											break;
										}
								}						
								driver.switchTo().parentFrame();
								logger.info("SWITCHED TO PARENT IFRAME");
							}else{
								break;
							}
						}
						if(widgetNotLoaded){ 
							stop = System.nanoTime();
							String timeTakenToLoadEngineer = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
							insertions.put("time_taken_to_load_engineer", "ATLEAST ONE WIDGET NOT LOADED AND TIMED OUT");
							logger.info("TIME OUT FOR ENGINEER DASHBOARD LOAD FAILURE: TimeOutTime: "+ timeTakenToLoadEngineer);
							break;
						}
					}
				}
				stop = System.nanoTime();
				String timeTakenToLoadEngineer = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_engineer", timeTakenToLoadEngineer);
				logger.info("TIME TAKEN FOR Engineer DASHBOARD TO LOAD: "+ timeTakenToLoadEngineer);
				if((new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() >= 300)){
					insertions.put("time_taken_to_load_engineer", "PAGE NOT LOADED-FATAL ERROR? ITS BEEN MORE THAN 5 mins");
				}			
				} catch (Exception e) {
					stop = System.nanoTime();
					String timeTakenToLoadEngineer = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
					logger.info("EXCEPTION IN ENGINEER DASHBOARD LOAD: TimeOutTime: "+ timeTakenToLoadEngineer);
	    		       insertions.put("time_taken_to_load_engineer", "PAGE NOT LOADED");
	    				final Writer result = new StringWriter();
	    				final PrintWriter printWriter = new PrintWriter(result);
	    				e.printStackTrace(printWriter);
	    				logger.info(result.toString());
				}					
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadEngineer = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_engineer", "PAGE NOT LOADING");
				logger.info("TIME OUT FOR ENGINEER DASHBOARD: TimeOutTime: "+timeTakenToLoadEngineer);
			}
		
			//START OF SALES MANAGER
			try{
				driver.switchTo().defaultContent();
				logger.info("SWITCHED BACK TO MAIN PAGE CONTENT");		
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
				logger.info("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
				WebElement salesManager = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id(PSSLeftNavigationPage.SALES_MANAGER)));
				logger.info("FOUND THE SALES MANAGER LINK");
				
				BasePage.moveOverAndClick(salesManager);
				logger.info("CLICKED THE SALES MANAGER LINK");
				
				start = System.nanoTime();
				
				boolean coverageLast30_loaded = false;
				boolean coverageNext90_loaded = false;
				boolean customersNewCollection_loaded = false;
				boolean widgetNotLoaded = false;
				
				try {
				while(!(coverageLast30_loaded && coverageNext90_loaded && customersNewCollection_loaded) && 
						(new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() <= 300)){
						// try this loop for atleast 5 minutes
					
						//wait till the content area loads
						pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("main.container")));
						logger.debug("Waiting for the iframe to be to be present");
						List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
						logger.debug("Total number of iframes: "+ iframes.size());
		            if(iframes.size()>=3){
						for (WebElement iframeInstance : iframes){
						 if(!(coverageLast30_loaded && coverageNext90_loaded && customersNewCollection_loaded)){
							String src = iframeInstance.getAttribute("src");
							logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
							logger.debug("the src attribute is: "+src);
							driver.switchTo().frame(iframeInstance);				
							if(src.contains("CUSTOMERS_EXPIRED_COVERAGE_90")){
								logger.info("FOUND COVERAGE NEXT 90 IFRAME");
								try{
									pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueBillboardLabel")));
									logger.info("PSIRT IFRAME LOADING COMPLETE");
									coverageNext90_loaded = true;
									stopOfWidget = System.nanoTime();
									String timeTakenToLoadNext90 = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
									insertions.put("time_taken_next90", timeTakenToLoadNext90);																																				
									}catch(NoSuchElementException exp){
										logger.info("NO SUCH ELEMENT EXPCEPTION: PSIRT");
									}catch(TimeoutException exp){
										logger.info("TIME OUT ON COVERAGE EXPIRED NEXT 90 SCREEN");
										widgetNotLoaded = true;
										insertions.put("time_taken_next90", "WIDGET NOT LOADED");
										break;
									}
							}
							else if(src.contains("CUSTOMERS_NEW_DEVICES_FOUND")){
								logger.info("FOUND THE NEW COLLECTION IFRAME");
								try{
									pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueBillboardLabel")));
									logger.info("FAILED INVENTORY IFRAME LOADING COMPLETE");
									customersNewCollection_loaded = true;
									stopOfWidget = System.nanoTime();
									String timeTakenToLoadNewDevices = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
									insertions.put("time_taken_newDevices", timeTakenToLoadNewDevices);																																													
									}catch(NoSuchElementException exp){
										logger.info("NO SUCH ELEMENT EXPCEPTION");
									}catch(TimeoutException exp){
										logger.info("TIME OUT ON LOADING NEW DEVICES CUSTOMERS WIDGET");
										widgetNotLoaded = true;
										insertions.put("time_taken_newDevices", "WIDGET NOT LOADED");
										break;
									}
							}
							else if(src.contains("CUSTOMERS_EXPIRED_COVERAGE_30")){
								logger.info("FOUND EXPIRED COVERAGE 30 IFRAME");
								try{
									pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueBillboardLabel")));
									logger.info("FEILD NOTICE IFRAME LOADING COMPLETE");
									coverageLast30_loaded = true;
									stopOfWidget = System.nanoTime();
									String timeTakenToLoadLast30 = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
									insertions.put("time_taken_last30", timeTakenToLoadLast30);																																													
									}catch(NoSuchElementException exp){
										logger.info("NO SUCH ELEMENT EXPCEPTION");
									}catch(TimeoutException exp){
										logger.info("TIME OUT ON LOADING EXPIRED COVERAGE 30 WIDGET");
										widgetNotLoaded = true;
										insertions.put("time_taken_last30", "WIDGET NOT LOADED");										
										break;
									}
							}
							driver.switchTo().parentFrame();
							logger.info("SWITCHED TO PARENT IFRAME");
							}else{
								break;
							}
						}
						if(widgetNotLoaded){ 
							stop = System.nanoTime();
							String timeTakenToLoadSalesManager = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
							insertions.put("time_taken_to_load_salesmanager", "ATLEAST ONE WIDGET NOT LOADED AND TIMED OUT");
							logger.info("TIME OUT FOR SALES MANAGER DASHBOARD LOAD FAILURE: TimeOutTime: "+ timeTakenToLoadSalesManager);
							break;
						}
					}
				}
				stop = System.nanoTime();
				String timeTakenToLoadSalesManager = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_salesmanager", timeTakenToLoadSalesManager);
				logger.info("TIME TAKEN FOR SALES MANAGER DASHBOARD TO LOAD: "+ timeTakenToLoadSalesManager);
				if((new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() >= 300)){
					insertions.put("time_taken_to_load_salesmanager", "PAGE NOT LOADED-FATAL ERROR? ITS BEEN MORE THAN 5 mins");
				}			
				} catch (Exception e) {
					stop = System.nanoTime();
					String timeTakenToLoadSalesManager = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
					logger.info("EXCEPTION IN SALES MANAGER DASHBOARD LOAD: TimeOutTime: "+ timeTakenToLoadSalesManager);
	    		       insertions.put("time_taken_to_load_salesmanager", "PAGE NOT LOADED");
	    				final Writer result = new StringWriter();
	    				final PrintWriter printWriter = new PrintWriter(result);
	    				e.printStackTrace(printWriter);
	    				logger.info(result.toString());
				}								
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadSalesManager = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_salesmanager", "PAGE NOT LOADING");
				logger.info("TIME OUT FOR SALES MANAGER: TimeOutTime: "+timeTakenToLoadSalesManager);
			}
			
			//START OF SERVICE EXECUTIVE
			try{
				driver.switchTo().defaultContent();
				logger.info("SWITCHED BACK TO MAIN PAGE CONTENT");			
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
				logger.info("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
				WebElement serviceExecutive = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id(PSSLeftNavigationPage.SERVICE_EXECUTIVE)));
				logger.info("FOUND THE SERVICE EXECUTIVE LINK");
				
				BasePage.moveOverAndClick(serviceExecutive);
				logger.info("CLICKED THE SERVICE EXECUTIVE LINK");
				
				start = System.nanoTime();
				
				boolean productAlerts30_loaded = false;
				boolean devicesLDOS_loaded = false;
				boolean coveredNotCovered_loaded = false;
				boolean widgetNotLoaded = false;
				
				try {
				while(!(productAlerts30_loaded && devicesLDOS_loaded && coveredNotCovered_loaded) && 
						(new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() <= 300)){
						// try this loop for atleast 5 minutes
					
						//wait till the content area loads
						pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.id("main.container")));
						logger.debug("Waiting for the iframe to be to be present");
						List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
						logger.debug("Total number of iframes: "+ iframes.size());
		            if(iframes.size()>=3){
						for (WebElement iframeInstance : iframes){
							if(!(productAlerts30_loaded && devicesLDOS_loaded && coveredNotCovered_loaded)){
								String src = iframeInstance.getAttribute("src");
								logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
								logger.debug("the src attribute is: "+src);
								driver.switchTo().frame(iframeInstance);
								if(src.contains("DEVICES_PAST_LDOS")){
									logger.info("FOUND DEVICES LDOS IFRAME");
									try{
										pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("ssueBillboardLabel")));
										logger.info("DEVICES LDOS IFRAME LOADING COMPLETE");
										devicesLDOS_loaded = true;
										stopOfWidget = System.nanoTime();
										String timeTakenToLoadLDOS = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
										insertions.put("time_taken_ldos", timeTakenToLoadLDOS);																																													
										}catch(NoSuchElementException exp){
											logger.info("NO SUCH ELEMENT EXPCEPTION: PSIRT");
										}catch(TimeoutException exp){
											logger.info("TIME OUT ON DEVICES LDOS WIDGET");
											widgetNotLoaded = true;
											insertions.put("time_taken_ldos", "WIDGET NOT LOADED");
											break;
										}
								}
								else if(src.contains("COVERED_NOT_COVERED")){
									logger.info("FOUND THE COVERED NOT COVERED IFRAME");
									try{
										pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("dojoxGridRowTable")));
										logger.info("FAILED INVENTORY IFRAME LOADING COMPLETE");
										coveredNotCovered_loaded = true;
										stopOfWidget = System.nanoTime();
										String timeTakenToLoadCovNotCov = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
										insertions.put("time_taken_cov_notcov", timeTakenToLoadCovNotCov);																																													
										}catch(NoSuchElementException exp){
											logger.info("NO SUCH ELEMENT EXPCEPTION");
										}catch(TimeoutException exp){
											logger.info("TIME OUT ON COVERED NOT COVERED WIDGET");
											widgetNotLoaded = true;
											insertions.put("time_taken_cov_notcov", "WIDGET NOT LOADED");
											break;
										}
								}
								else if(src.contains("PRODUCT_ALERTS_LAST_30")){
									logger.info("FOUND PRODUCT ALERTS 30 IFRAME");
									try{
										pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("dojoxGridRowTable")));
										logger.info("PRODUCT ALERTS 30 IFRAME LOADING COMPLETE");
										productAlerts30_loaded = true;
										stopOfWidget = System.nanoTime();
										String timeTakenToLoadProdAlerts = new Long(TimeUnit.SECONDS.convert((stopOfWidget - start), TimeUnit.NANOSECONDS)).toString() + " sec";
										insertions.put("time_taken_prod_alerts", timeTakenToLoadProdAlerts);																																													
										}catch(NoSuchElementException exp){
											logger.info("NO SUCH ELEMENT EXPCEPTION");
										}catch(TimeoutException exp){
											logger.info("TIME OUT ON LOADING PRODUCT ALERTS 30 WIDGET");
											widgetNotLoaded = true;
											insertions.put("time_taken_prod_alerts", "WIDGET NOT LOADED");
											break;
										}
								}
								driver.switchTo().parentFrame();
								logger.info("SWITCHED TO PARENT IFRAME");
							}else{
								break;
							}
						}
						if(widgetNotLoaded){ 
							stop = System.nanoTime();
							String timeTakenToLoadServiceExecutive = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
							insertions.put("time_taken_to_load_serviceexecutive", "ATLEAST ONE WIDGET NOT LOADED AND TIMED OUT");
							logger.info("TIME OUT FOR SERVICE EXECUTIVE DASHBOARD LOAD FAILURE: TimeOutTime: "+ timeTakenToLoadServiceExecutive);
							break;
						}
					}
				}
				stop = System.nanoTime();
				String timeTakenToLoadServiceExecutive = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_serviceexecutive", timeTakenToLoadServiceExecutive);
				logger.info("TIME TAKEN FOR SERVICE EXECUTIVE DASHBOARD TO LOAD: "+ timeTakenToLoadServiceExecutive);
				if((new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).intValue() >= 300)){
					insertions.put("time_taken_to_load_serviceexecutive", "PAGE NOT LOADED-FATAL ERROR? ITS BEEN MORE THAN 5 mins");
				}			
				} catch (Exception e) {
					stop = System.nanoTime();
					String timeTakenToLoadServiceExecutive = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
					logger.info("EXCEPTION IN SERVICE EXECUTIVE DASHBOARD LOAD: TimeOutTime: "+ timeTakenToLoadServiceExecutive);
	    		       insertions.put("time_taken_to_load_serviceexecutive", "PAGE NOT LOADED");
	    				final Writer result = new StringWriter();
	    				final PrintWriter printWriter = new PrintWriter(result);
	    				e.printStackTrace(printWriter);
	    				logger.info(result.toString());
				}								
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadServiceExecutive = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_serviceexecutive", "PAGE NOT LOADING");
				logger.info("TIME OUT FOR SERVICE EXECUTIVE: TimeOutTime: "+timeTakenToLoadServiceExecutive);
			}
		//START OF MANAGE COLLECTORS
		clickLink(pageLoaded, "Manage Collectors", "AllCollectors", insertions);					
		//START OF ALL CONTRACTS
		clickLink(pageLoaded, "All Contracts", "AllContracts", insertions);				
		//START OF EXPIRING CONTRACTS
		clickLink(pageLoaded, "Expiring Contracts", "ExpiringContracts", insertions);		
		//START OF PRODUCT
		clickLink(pageLoaded, "Product", "Products", insertions);
		
		//START OF All Items  IBAllEquipment
		clickLink(pageLoaded, "All Items", "IBAllEquipment", insertions);
		//START OF 	Covered Items  IBCoveredItems
		clickLink(pageLoaded, "Covered Items", "IBCoveredItems", insertions);
//		Devices with Alerts DevicesWithAlerts
		clickLink(pageLoaded, "Devices with Alerts", "DevicesWithAlerts", insertions);
//		Inventory Collection IBInventoryCollection
		if(userRole.equals("CustomerAdmin")){
			clickLink(pageLoaded, "Inventory Collection", "IBInventoryCollection", insertions);
		}else if(userRole.equals("Employee")){
			clickLink(pageLoaded, "Inventory Collection", "PSS_GD_INV_COLLECTIONS_EMP", insertions);
		}
//      Last Day of Support  IBLastDayOfSupport
		clickLink(pageLoaded, "Last Day of Support", "IBLastDayOfSupport", insertions);
//		Uncovered Items      IBUncoveredDevices
		clickLink(pageLoaded, "Uncovered Items", "IBUncoveredDevices", insertions);
//      All Alerts           AllAlerts
		clickLink(pageLoaded, "All Alerts", "AllAlerts", insertions);
	
		long stopOfWholeTest = System.nanoTime();
		logger.info("TOTAL TIME TAKEN FOR ALL STEPS SO FAR: "+TimeUnit.SECONDS.convert((stopOfWholeTest - startOfWholeTest), TimeUnit.NANOSECONDS)+ " sec");
		
		PostOffice po = new SystemPostOffice();
		
		insertions.put("report_date", (new Date()).toString());
		
		String [] toAddresses = TestConstants.pssToEmailAddresses.split(",");
		String [] ccAddresses = null;
		try {
			po.sendEmail(TestConstants.pssPageLoadReportTemplateName, insertions, toAddresses, ccAddresses);
			logger.info("EMAIL SENT SUCCESSFULLY");
		} catch (ValidationException e) {
			e.printStackTrace();
		}	
		
		// test complete
		testStatus.setTestStatus("Passed");

		//update the database with the test result.
		testStatusDao.save(testStatus);
//		testStatusDao.clearSessions();
		}catch(Exception exp){
		  logger.info("EXCEPTION IN GETTING THE DAO");
		  final Writer result = new StringWriter();
		  final PrintWriter printWriter = new PrintWriter(result);
		  exp.printStackTrace(printWriter);
		  logger.info(result.toString());
		  TestStatus testStatus = testStatusDao.getByTestId(new Long(103));
		  testStatus.setTestStatus("Passed");
		  testStatusDao.save(testStatus);
		  testStatusDao.clearSessions();
		}
	}
	
	private void clickLink(WebDriverWait pageLoaded,String linkName, String sourceString, Map<String,String> insertions){
		
		long start = System.nanoTime();
		long stop = System.nanoTime();
		String timeTakenToLoad=null;
		String insertParamTimeTaken = "time_taken_to_load_"+sourceString;
		String insertParamTotal = "total_records_"+sourceString;
		String insertParamInitialTime= "time_for_initial_load_"+sourceString;
		Number total=null;
		boolean isNoRecordsDisplayed=false;
		
		try{
			driver.switchTo().defaultContent();
			logger.info("SWITCHED BACK TO MAIN PAGE CONTENT");			
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
			logger.info("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
			WebElement webElement = 
					pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BasePage.XPATH_STRING.replaceAll("REP", linkName))));
			logger.info("FOUND THE {} LINK", linkName);

			BasePage.moveOverAndClick(webElement);
	
			start = System.nanoTime();
			logger.info("CLICKED THE {} LINK", linkName);
//			pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("main.container")));
			driver.findElement(By.id("main.container"));
			List<WebElement> iframes = pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
			logger.debug("Total number of iframes: "+ iframes.size());
			
			for (WebElement iframeInstance : iframes){
				String src = iframeInstance.getAttribute("src");
				logger.debug(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
				logger.debug("THE SRC IS: "+src);
				driver.switchTo().frame(iframeInstance);				
				if(src.contains(sourceString)){
					logger.debug("FOUND THE {} IFRAME",linkName);
					pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("dojoxGridRowTable")));
					// inital page loaded capture the time
					stop = System.nanoTime();
					timeTakenToLoad = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
					if(linkName.equals("Inventory Collection")){
						insertions.put("time_for_initial_load_IBInventoryCollection", timeTakenToLoad);
					}else{
						insertions.put(insertParamInitialTime, timeTakenToLoad);
					}
					logger.debug("THE {} INITIAL PAGE LOADED", linkName);
					// reset the clock
					start = System.nanoTime();					
					try{
					WebElement msgDiv = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dojoxGridMasterMessages")));
					logger.debug("The message div attributes: displayed {} : Enabled {} : Selected {} ",msgDiv.isDisplayed(),msgDiv.isEnabled(), msgDiv.isSelected());
					isNoRecordsDisplayed = msgDiv.isDisplayed();
					}catch(Exception exp){
						  final Writer result = new StringWriter();
						  final PrintWriter printWriter = new PrintWriter(result);
						  exp.printStackTrace(printWriter);
						  logger.info(result.toString());
					}
					if(linkName.equals("Inventory Collection")){
						total = getTotalFromLink(pageLoaded, isNoRecordsDisplayed);
						logger.info("TOTAL ROWS IN {} DATA GRID ARE: {}",linkName, total);
					}										
					logger.debug("THE {} LOADED", linkName);
					driver.switchTo().parentFrame();
					logger.debug("Switched to the parent iframe ");
					break;
				}
				
				driver.switchTo().parentFrame();
				logger.debug("Switched to the parent iframe ");
			}		
			if(!linkName.equals("Inventory Collection")){
				total = getTotalFromLink(pageLoaded,isNoRecordsDisplayed);
				logger.info("TOTAL ROWS IN {} DATA GRID ARE: {}",linkName, total);
			}
			stop = System.nanoTime();
			timeTakenToLoad = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			
			//TODO remove this hardcoding by changing the code in such a way that there is a unique way of getting insertParam
			// currently link name can't be used cos of spaces and source string is changing based on role. but email template is static.
			if(linkName.equals("Inventory Collection")){
				insertions.put("time_taken_to_load_IBInventoryCollection", timeTakenToLoad);
				insertions.put("total_records_IBInventoryCollection", total.toString());
			}else{			
				insertions.put(insertParamTimeTaken, timeTakenToLoad);
				insertions.put(insertParamTotal, total.toString());
			}
			logger.info("TIME TAKEN FOR {} TO LOAD: {} ",sourceString, timeTakenToLoad);
			
		}catch(NoSuchElementException exp){
			logger.info("NO SUCH ELEMENT EXPCEPTION");
		}catch(TimeoutException exp){
			stop = System.nanoTime();
			timeTakenToLoad = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			logger.info("TIME OUT FOR {} : TimeOutTime: {} ",sourceString, timeTakenToLoad);
			
			insertions.put(insertParamTimeTaken, "PAGE NOT LOADING");
			insertions.put(insertParamTotal, "UNKNOWN");
		}	
	}
	
	private Number getTotalFromLink(WebDriverWait pageLoaded, boolean isNoRecordsDisplayed){
		Number total=0;
		if(isNoRecordsDisplayed==false){
			boolean isCountObtained=false;
			while(isCountObtained==false){
				try{
				WebElement spanElement =  pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("toolbarItemCountSubtitle")));
				logger.debug("The total count is: "+spanElement.getText());
				if (!spanElement.getText().trim().equals("???")){
					String [] values = spanElement.getText().split("\\s");
					if(values!=null && values.length>0){
						logger.debug("Values length:{} and first value is: {}",values.length,values[0]);
						total =NumberFormat.getInstance(Locale.US).parse(values[0]);
						logger.debug("Total count in data grid is: {}",total);
						isCountObtained=true;
					}
				}
				}catch(ParseException exp){
					logger.debug("not a long value");
				}catch(TimeoutException exp){
					logger.debug("TIME OUT ON Item Count");
				}catch (NoSuchElementException e) {
					logger.debug("No Such Element on Total count");
				}catch (StaleElementReferenceException exp){
					logger.debug("Stale Element Exception");
				}
	
			}
		}
		return total;
	}
	
private WebDriverWait selectPartner(WebDriverWait pageLoaded, Map<String,String> insertions, String partnerName)
			throws Exception{
	Actions builder = new Actions(driver);
	boolean isPartnerFound=false;
	try{
		driver.switchTo().defaultContent();
		logger.info("SWITCHED BACK TO MAIN PAGE CONTENT");			
		driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
		logger.info("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
		WebElement headerContainerDiv = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("nmvHeaderToolbarContainer")));
		List<WebElement> filterValueDivs = headerContainerDiv.findElements(By.className("filtervalue"));
		
		for (WebElement filterValueDiv : filterValueDivs) {
			if(filterValueDiv.getAttribute("title").equalsIgnoreCase("partner")){
				logger.info("PARTNER NAME IS: "+filterValueDiv.getText().trim());
				
				builder.moveToElement(filterValueDiv).perform();
				logger.debug("About to click the PARTNER drop down arrow");
				builder.click().perform();
				while(isPartnerFound==false){
//					pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("widgets_EnhancedCheckedComboBox_1_popup")));
					List<WebElement> drpListItems =driver.findElements
							(By.xpath("//ul[@id='widgets_EnhancedCheckedComboBox_1_popup']/li"));
					logger.info("TOTAL PARTNER items: " + drpListItems.size());
					for (WebElement aListItem : drpListItems) {
						if(!aListItem.getText().isEmpty() && aListItem.getText().equals(partnerName)){
							insertions.put("partner_name", aListItem.getText().trim());
							logger.info("FOUND THE PARTNER: {}",aListItem.getText() );
							builder.moveToElement(aListItem).perform();
							builder.click().perform();
							//mark partner booleans
							isPartnerFound = true;
							break;
						}else if(!aListItem.getText().isEmpty() && aListItem.getText().equals("More choices")){
							//TODO remove hardcoding of the more choices word.
							builder.moveToElement(aListItem).perform();
							logger.info("NOT FOUND PARTNER CLICKING  {} ",aListItem.getText() );
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

	private void selectCustomer(WebDriverWait pageLoaded, Map<String,String> insertions)
			throws Exception{
			
		Actions builder = new Actions(driver);
		boolean isCustomerSelected=false;
				
		try{
			driver.switchTo().defaultContent();
			logger.info("SWITCHED BACK TO MAIN PAGE CONTENT");			
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
			logger.info("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
			WebElement headerContainerDiv = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("nmvHeaderToolbarContainer")));
			List<WebElement> filterValueDivs = headerContainerDiv.findElements(By.className("filtervalue"));
			
			for (WebElement filterValueDiv : filterValueDivs) {
				if(filterValueDiv.getAttribute("title").equalsIgnoreCase("customer")){
					builder.moveToElement(filterValueDiv).perform();
					logger.debug("About to click the CUSTOMER drop down arrow");
					builder.click().perform();
//					pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("widgets_EnhancedCheckedComboBox_2_popup")));
					WebElement popupDiv = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dijitPopup")));
					logger.info("FOUND THE CUSTOMER POPUP");
					List<WebElement> drpListItems =popupDiv.findElements(By.tagName("li"));
					logger.info("TOTAL CUSTOMER LIST items: " + drpListItems.size());
						for (WebElement aListItem : drpListItems) {
							if(!aListItem.getText().isEmpty()){
								if(aListItem.getText().contains("All")){
									// select the all customers drop down item and click on it
									String totalCustomers = aListItem.getText().replaceAll("All", "").replace("(", "").replace(")", "").trim();
									logger.info("TOTAL NUMBER OF CUSTOMERS: "+totalCustomers); 
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

	
	private void selectInventory(WebDriverWait pageLoaded, Map<String,String> insertions)
							throws Exception{
		boolean isInventorySelected = false;
		Actions builder = new Actions(driver);
		try{
			driver.switchTo().defaultContent();
			logger.info("SWITCHED BACK TO MAIN PAGE CONTENT");			
			driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(PSSLeftNavigationPage.MAIN_IFRAME_CLASSNAME)));
			logger.info("SWITCHED TO MAIN IFRAME workSpaceViewFrame");
		WebElement headerContainerDiv = pageLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.className("nmvHeaderToolbarContainer")));
		List<WebElement> filterValueDivs = headerContainerDiv.findElements(By.className("filtervalue"));
		
			for (WebElement filterValueDiv : filterValueDivs) {
				if(filterValueDiv.getAttribute("title").equalsIgnoreCase("inventory")){
					builder.moveToElement(filterValueDiv).perform();
					logger.debug("About to click the INVENTORY drop down arrow");
					builder.click().perform();
			//		pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("widgets_EnhancedCheckedComboBox_3_popup")));
					WebElement popupDiv = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.className("dijitPopup")));
					logger.info("FOUND THE INVENTORY POPUP");
					List<WebElement> drpListItems =popupDiv.findElements(By.tagName("li"));
					logger.info("TOTAL INVENTORY LIST items: " + drpListItems.size());
					for (WebElement aListItem : drpListItems) {
						if(!aListItem.getText().isEmpty()){
							if(aListItem.getText().contains("All")){
								// get all inventories drop down item and click
								String totalInventories = aListItem.getText().replaceAll("All", "").replace("(", "").replace(")", "").trim();
								logger.info("TOTAL NUMBER OF INVENTORIES: "+totalInventories); 
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

//	public String getPartnerName() {
//		return partnerName;
//	}
//
//	public void setPartnerName(String partnerName) {
//		this.partnerName = partnerName;
//	}	
}