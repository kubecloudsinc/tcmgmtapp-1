package com.cisco.cstg.autotools.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.cisco.cstg.autotools.dao.TestStatusDao;
import com.cisco.cstg.autotools.domain.appdb.CustomerInfo;
import com.cisco.cstg.autotools.domain.appdb.TempUser;
import com.cisco.cstg.autotools.semantic.diagnostics.ValidationException;
import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;
import com.cisco.cstg.autotools.support.email.PostOffice;
import com.cisco.cstg.autotools.support.email.SystemPostOffice;
import com.cisco.cstg.autotools.support.exception.LinkNotFoundException;
import com.cisco.cstg.autotools.support.exception.NoCustomerAndRoleInfo;
import com.cisco.cstg.autotools.tests.pages.ActiveAlertsPage;
import com.cisco.cstg.autotools.tests.pages.AggregatedReportPage;
import com.cisco.cstg.autotools.tests.pages.AllAlertsPage;
import com.cisco.cstg.autotools.tests.pages.AllCollectorsPage;
import com.cisco.cstg.autotools.tests.pages.AllContractsPage;
import com.cisco.cstg.autotools.tests.pages.AllEquipmentPage;
import com.cisco.cstg.autotools.tests.pages.AllFieldNoticesPage;
import com.cisco.cstg.autotools.tests.pages.AllHardwareAlertsPage;
import com.cisco.cstg.autotools.tests.pages.AllHostsPage;
import com.cisco.cstg.autotools.tests.pages.AllPSIRTsPage;
import com.cisco.cstg.autotools.tests.pages.AllSoftwareAlertsPage;
import com.cisco.cstg.autotools.tests.pages.AllSupportCasesPage;
import com.cisco.cstg.autotools.tests.pages.ApplicationSettingsPage;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.pages.ContractDuplicatesPage;
import com.cisco.cstg.autotools.tests.pages.ContractPropertiesPage;
import com.cisco.cstg.autotools.tests.pages.CoveredPage;
import com.cisco.cstg.autotools.tests.pages.CustomInventoryPage;
import com.cisco.cstg.autotools.tests.pages.DeviceWithAlertsPage;
import com.cisco.cstg.autotools.tests.pages.ExpiringContractsPage;
import com.cisco.cstg.autotools.tests.pages.ExpiringDeviceCoveragePage;
import com.cisco.cstg.autotools.tests.pages.InventoryByProductPage;
import com.cisco.cstg.autotools.tests.pages.InventoryBySites;
import com.cisco.cstg.autotools.tests.pages.InventoryCollectionDeltaPage;
import com.cisco.cstg.autotools.tests.pages.InventoryDuplicatesPage;
import com.cisco.cstg.autotools.tests.pages.InventoryInsightDuplicatesPage;
import com.cisco.cstg.autotools.tests.pages.InventoryInsightOthersPage;
import com.cisco.cstg.autotools.tests.pages.InventoryInsightSummaryPage;
import com.cisco.cstg.autotools.tests.pages.InventorySummaryPage;
import com.cisco.cstg.autotools.tests.pages.LandingPage;
import com.cisco.cstg.autotools.tests.pages.LastDayOfSupportPage;
import com.cisco.cstg.autotools.tests.pages.LoginPage;
import com.cisco.cstg.autotools.tests.pages.MyReportsPage;
import com.cisco.cstg.autotools.tests.pages.MySupportCasesPage;
import com.cisco.cstg.autotools.tests.pages.NotCollectedPage;
import com.cisco.cstg.autotools.tests.pages.NotCoveredPage;
import com.cisco.cstg.autotools.tests.pages.NotFieldReplacablePage;
import com.cisco.cstg.autotools.tests.pages.NotRecognisedPage;
import com.cisco.cstg.autotools.tests.pages.PageConstants;
import com.cisco.cstg.autotools.tests.pages.ProductAlertsDeltaPage;
import com.cisco.cstg.autotools.tests.pages.ServiceCoveragePage;
import com.cisco.cstg.autotools.tests.pages.ThirdPartyPage;
import com.cisco.cstg.autotools.tests.pages.UploadProcessingPage;
import com.cisco.cstg.autotools.tests.params.PageLoadTestParams;


//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(Parameterized.class)
@ContextConfiguration(locations=
{
 "/applicationContext.xml",
 "/applicationContext-security.xml",
 "/spring-servlet.xml"
}
)
public class SNTCNextGenPageLoadTest extends BaseTest {
	
	protected static final Logger logger = LoggerFactory.getLogger(SNTCNextGenPageLoadTest.class);
	
	private final TestContextManager testContextManager;
	
	private PageLoadTestParams loadTestParameter;
	
	@Autowired
	TestStatusDao testStatusDao; 

	@Parameters
	public static Collection<PageLoadTestParams> customerNames(){

		TestConstants.logger.debug("INITIALIZING THE CUSTOMER LIST");
		Configuration paramConfig = new ComponentConfiguration(SNTCNextGenPageLoadTest.class);
		
		String [] usernames = paramConfig.getStringValue(USER_NAMES).split(",");
		String [] passwords = paramConfig.getStringValue(PASSWORDS).split(",");
		String [] customernames = paramConfig.getStringValue(CUSTOMER_NAMES).split(",");
		String [] inventorynames = paramConfig.getStringValue(INVENTORY_NAMES).split(",");
		List<PageLoadTestParams> params = new ArrayList<PageLoadTestParams>();
		
		for(int i=0;i<usernames.length;i++){
			PageLoadTestParams param = new PageLoadTestParams();
			param.setUserName(usernames[i].trim());
			param.setPassword(passwords[i].trim());
			param.setCustomerName(customernames[i].trim());
			param.setInventoryName(inventorynames[i].trim());
			param.setUrl(paramConfig.getStringValue(URL).trim());
			params.add(param);
		}
		
		return params;
	}
	
	/**
	 * Constructor for the test
	 * @param partnerName
	 */
	public SNTCNextGenPageLoadTest(PageLoadTestParams testParameter) throws Exception{

		logger.debug("INSIDE THE CONSTRUCTOR");
		logger.debug("partner name is: {}",testParameter.getCustomerName());
		this.testContextManager = new TestContextManager(getClass());
		this.loadTestParameter = testParameter;
	}
	

	@Before
	public void setUp() throws Exception {
			this.testContextManager.prepareTestInstance(this);	
//			driver = openFireFoxProfileDriverWithoutAuth(BasePage.SNTC_FIREFOX_PROFILE_NAME);
			driver = openFireFoxProfileDriverWithoutAuth(BasePage.PSS_FIREFOX_PROFILE_NAME);
			
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
	public void captureTimeTest() throws Exception{
		Map<String,String> insertions = new TreeMap<String,String>();
		long startOfTheTest = System.nanoTime();
		try{
			long start = System.nanoTime();	

			insertions.put("user_name", loadTestParameter.getUserName());
			String password = loadTestParameter.getPassword();
			
			logger.debug("Value of password Before: {}", password);
//			get the user debug give dynamically during running of the test
			if(password.equalsIgnoreCase(TestConstants.HIDDEN_PASSWORD_INDICATOR)){
				logger.debug("loading the temp user config");
				File file = new File(Thread.currentThread().getContextClassLoader().getResource(TempUser.class.getName()+".cfg").getFile());
				List<String> lines = FileUtils.readLines(file);
				if(lines!=null && lines.size()==2){
					password = lines.get(1);
					insertions.put("user_name", "SNTC Global Application Admin");
					insertions.put("user_roles", "Global Application Admin");
				}
				logger.debug("End the user config");			
			}
			//start the test
			logger.debug("STARTING THE TEST: ATTEMPTING TO LOGIN");			
			
//			driver = new LoginPage(driver).loginToPortal(BasePage.baseUrlNextGen, userName, pwd);
			driver = new LoginPage(driver).loginToPortal(loadTestParameter.getUrl(), loadTestParameter.getUserName(), password);
			
			long stop = System.nanoTime();
			String timeTakenToLogin = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			
			//start the LNP timer as soon as possible so putitng before other statements
			start = System.nanoTime();
			insertions.put("time_taken_to_login", timeTakenToLogin);
			logger.info("TIME TAKEN FOR LOGIN: "+timeTakenToLogin);	
			
			driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			LandingPage landingPage = new LandingPage(driver);
			landingPage.waitForLNPLoad();
			
			stop = System.nanoTime();
			String timeTakenToLoadLNP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_load_lnp", timeTakenToLoadLNP);
			logger.info("TIME TAKEN FOR LEFT NAVIGATION TO LOAD: "+timeTakenToLoadLNP);

//			if(userRole.equalsIgnoreCase("Employee")){
//				landingPage.chooseCustomer(customerName);
//			}else{
				landingPage.chooseCustomer(loadTestParameter.getCustomerName());
				landingPage.chooseAllInventory();
//			}
			CustomerInfo custInfo = landingPage.getCustomerInfo();
			insertions.put("customer_label",custInfo.getCustomerLabel());
			insertions.put("inventory_label",custInfo.getInventoryLabel());
			insertions.put("customer_name",custInfo.getCustomerName());
			insertions.put("inventory_info",custInfo.getInventoryInfo());
			
			logger.debug("Customer info: {}",custInfo.toString());
			
			// click on app setting page and get the app page
			ApplicationSettingsPage appPage = new ApplicationSettingsPage(landingPage.clickOnLNPlink(BasePage.APPLICATION_SETTINGS));
			start = System.nanoTime();
			try{
			appPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadAppSettings = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_load_appsettings", timeTakenToLoadAppSettings);
			logger.info("TIME TAKEN FOR APP SETTINGS TO LOAD: "+timeTakenToLoadAppSettings);
			}catch(NoCustomerAndRoleInfo exp){
				logger.debug("COULD NOT GET THE CUSTOMER ROLE");
				stop = System.nanoTime();
				String timeTakenToLoadAppSettings = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_appsettings", timeTakenToLoadAppSettings);
				logger.info("TIME TAKEN FOR APP SETTINGS TO LOAD: "+timeTakenToLoadAppSettings);
			}
			
			String userRole = appPage.getUserRolesAsString(loadTestParameter.getCustomerName()).toString();
			if(!StringUtils.isEmpty(userRole))
				insertions.put("user_roles", userRole);

			MyReportsPage reportsPage = new MyReportsPage(landingPage.clickOnLNPlink(BasePage.MY_REPORTS));
			reportsPage.waitForContentArea();
			insertions.put("time_for_initial_load_myreports", reportsPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_myreports", reportsPage.getTimeToLoad());
			insertions.put("total_records_myreports", reportsPage.getTotalRecords());
			logger.info("TIME TAKEN FOR MY REPORTS- Initial:{} ; Total:{} ",reportsPage.getTimeToInitialLoad(), reportsPage.getTimeToLoad());

			start = System.nanoTime();
			driver = landingPage.clickOnLNPlink(BasePage.NOTIFICATIONS);
			landingPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadNotifications = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_load_notifications", timeTakenToLoadNotifications);
			logger.info("TIME TAKEN FOR NOTIFICATIONS TO LOAD: "+timeTakenToLoadNotifications);	

			start = System.nanoTime();
			driver = landingPage.clickOnLNPlink(BasePage.USEFUL_LINKS);
			landingPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadUsefulLinks = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_load_usefullinks", timeTakenToLoadUsefulLinks);
			logger.info("TIME TAKEN FOR USEFUL LINKS TO LOAD: "+timeTakenToLoadUsefulLinks);
			
		
			try{
				start = System.nanoTime();
				driver = landingPage.clickOnLNPlink(BasePage.ADMIN);
				landingPage.waitForContentArea();
				stop = System.nanoTime();
				String timeTakenToLoadAdmin = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_admin", timeTakenToLoadAdmin);
				logger.info("TIME TAKEN FOR ADMIN TO LOAD: "+timeTakenToLoadAdmin);
			}catch(LinkNotFoundException exp){
				insertions.put("time_taken_to_load_admin", "NOT AVAILABLE FOR THIS ROLE");
			}
			
			start = System.nanoTime();
			driver = landingPage.clickOnLNPlink(BasePage.ALERT_MANAGEMENT);
			landingPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadAlertMgmt = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_load_alertmgmt", timeTakenToLoadAlertMgmt);
			logger.info("TIME TAKEN FOR ALERT MANAGEMENT TO LOAD: "+timeTakenToLoadAlertMgmt);	
			
			start = System.nanoTime();
			driver = landingPage.clickOnLNPlink(BasePage.CONTRACT_MANAGEMENT);
			landingPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadContractMgmt = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_load_contractmgmt", timeTakenToLoadContractMgmt);
			logger.info("TIME TAKEN FOR CONTRACT MANAGEMENT TO LOAD: "+timeTakenToLoadContractMgmt);
	
			start = System.nanoTime();
			driver = landingPage.clickOnLNPlink(BasePage.INVENTORY_MANAGEMENT);
			landingPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadInvMgmt = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_load_invmgmt", timeTakenToLoadInvMgmt);
			logger.info("TIME TAKEN FOR INVENTORY MANAGEMENT TO LOAD: "+timeTakenToLoadInvMgmt);
			
			start = System.nanoTime();
			driver = landingPage.clickOnLNPlink(BasePage.SMART_NET_TOTAL_CARE);
			landingPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadSNTC = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_to_load_SNTC", timeTakenToLoadSNTC);
			logger.info("TIME TAKEN FOR SNTC TO LOAD: "+timeTakenToLoadSNTC);	
		
			ContractPropertiesPage contractPropertiesPage = new ContractPropertiesPage(landingPage.clickOnLNPlink(BasePage.CONTRACT_PROPERTIES));
			contractPropertiesPage.waitForContentArea();
			insertions.put("time_for_initial_load_contractprops", contractPropertiesPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_contractprops", contractPropertiesPage.getTimeToLoad());
			insertions.put("total_records_contractprops", contractPropertiesPage.getTotalRecords());
			contractPropertiesPage
				.exportDataGrid(contractPropertiesPage.getSourceString(), contractPropertiesPage.getPageName(), PageConstants.EXPORT_CSV);
			contractPropertiesPage
				.exportDataGrid(contractPropertiesPage.getSourceString(), contractPropertiesPage.getPageName(), PageConstants.EXPORT_PDF);
			contractPropertiesPage
				.exportDataGrid(contractPropertiesPage.getSourceString(), contractPropertiesPage.getPageName(), PageConstants.EXPORT_XLSX);
			
			UploadProcessingPage uploadProcessingPage = new UploadProcessingPage(landingPage.clickOnLNPlink(BasePage.UPLOAD_PROCESSING));
			uploadProcessingPage.waitForContentArea();
			insertions.put("time_for_initial_load_uploadprocess", uploadProcessingPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_uploadprocess", uploadProcessingPage.getTimeToLoad());
			insertions.put("total_records_uploadprocess", uploadProcessingPage.getTotalRecords());
			uploadProcessingPage.exportDataGrid(uploadProcessingPage.getSourceString(), uploadProcessingPage.getPageName(), PageConstants.EXPORT_CSV);
			uploadProcessingPage.exportDataGrid(uploadProcessingPage.getSourceString(), uploadProcessingPage.getPageName(), PageConstants.EXPORT_PDF);
			uploadProcessingPage.exportDataGrid(uploadProcessingPage.getSourceString(), uploadProcessingPage.getPageName(), PageConstants.EXPORT_XLSX);

			try{
				ActiveAlertsPage activeAlertsPage = new ActiveAlertsPage(landingPage.clickOnLNPlink(BasePage.ACTIVE_ALERTS));
				activeAlertsPage.waitForContentArea();
				insertions.put("time_for_initial_load_activealerts", activeAlertsPage.getTimeToInitialLoad());
				insertions.put("time_taken_to_load_activealerts", activeAlertsPage.getTimeToLoad());
				insertions.put("total_records_activealerts", activeAlertsPage.getTotalRecords());
				
			}catch(LinkNotFoundException exp){
				insertions.put("time_for_initial_load_activealerts", "NOT AVAILABLE FOR THIS ROLE");
				insertions.put("time_taken_to_load_activealerts", "NA");
				insertions.put("total_records_activealerts", "NA");
			}
			
			try{
				AllCollectorsPage allCollectorsPage = new AllCollectorsPage(landingPage.clickOnLNPlink(BasePage.ALL_COLLECTORS));
				allCollectorsPage.waitForContentArea();
				insertions.put("time_for_initial_load_allcollectors", allCollectorsPage.getTimeToInitialLoad());
				insertions.put("time_taken_to_load_allcollectors", allCollectorsPage.getTimeToLoad());
				insertions.put("total_records_allcollectors", allCollectorsPage.getTotalRecords());
			}catch(LinkNotFoundException exp){
				insertions.put("time_for_initial_load_allcollectors", "NOT AVAILABLE FOR THIS ROLE");
				insertions.put("time_taken_to_load_allcollectors", "NA");
				insertions.put("total_records_allcollectors", "NA");
			}
	
			try{
				ServiceCoveragePage serviceCoveragePage = new ServiceCoveragePage(landingPage.clickOnLNPlink(BasePage.SERVICE_COVERAGE));
				serviceCoveragePage.waitForContentArea();
				insertions.put("time_for_initial_load_servicecoverage", serviceCoveragePage.getTimeToInitialLoad());
				insertions.put("time_taken_to_load_servicecoverage", serviceCoveragePage.getTimeToLoad());
				insertions.put("total_records_servicecoverage", serviceCoveragePage.getTotalRecords());
			}catch(LinkNotFoundException exp){
				insertions.put("time_for_initial_load_servicecoverage", "NOT AVAILABLE FOR THIS ROLE");
				insertions.put("time_taken_to_load_servicecoverage", "NA");
				insertions.put("total_records_servicecoverage", "NA");
			}
			
		
			AllAlertsPage allAlertsPage = new AllAlertsPage(landingPage.clickOnLNPlink(BasePage.ALL_ALERTS));
			allAlertsPage.waitForContentArea();
			insertions.put("time_for_initial_load_allalerts", allAlertsPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_allalerts", allAlertsPage.getTimeToLoad());
			insertions.put("total_records_allalerts", allAlertsPage.getTotalRecords());
			
			AllFieldNoticesPage allFieldNoticesPage = new AllFieldNoticesPage(landingPage.clickOnLNPlink(BasePage.ALL_FIELD_NOTICES));
			allFieldNoticesPage.waitForContentArea();
			insertions.put("time_for_initial_load_allFNs", allFieldNoticesPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_allFNs", allFieldNoticesPage.getTimeToLoad());
			insertions.put("total_records_allFNs", allFieldNoticesPage.getTotalRecords());
			
			AllHardwareAlertsPage allHardwareAlertsPage = new AllHardwareAlertsPage(landingPage.clickOnLNPlink(BasePage.ALL_HARDWARE_ALERTS));
			allHardwareAlertsPage.waitForContentArea();
			insertions.put("time_for_initial_load_allHWalerts", allHardwareAlertsPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_allHWalerts", allHardwareAlertsPage.getTimeToLoad());
			insertions.put("total_records_allHWalerts", allHardwareAlertsPage.getTotalRecords());
			
			AllPSIRTsPage allPSIRTsPage = new AllPSIRTsPage(landingPage.clickOnLNPlink(BasePage.ALL_PSIRTS));
			allPSIRTsPage.waitForContentArea();
			insertions.put("time_for_initial_load_allPSIRTs", allPSIRTsPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_allPSIRTs", allPSIRTsPage.getTimeToLoad());
			insertions.put("total_records_allPSIRTs", allPSIRTsPage.getTotalRecords());
			
			AllSoftwareAlertsPage allSoftwareAlertsPage = new AllSoftwareAlertsPage(landingPage.clickOnLNPlink(BasePage.ALL_SOFTWARE_ALERTS));
			allSoftwareAlertsPage.waitForContentArea();
			insertions.put("time_for_initial_load_allswalerts", allSoftwareAlertsPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_allswalerts", allSoftwareAlertsPage.getTimeToLoad());
			insertions.put("total_records_allswalerts", allSoftwareAlertsPage.getTotalRecords());
			
			DeviceWithAlertsPage deviceWithAlertsPage = new DeviceWithAlertsPage(landingPage.clickOnLNPlink(BasePage.DEVICE_WITH_ALERTS));
			deviceWithAlertsPage.waitForContentArea();
			insertions.put("time_for_initial_load_devicewithalerts", deviceWithAlertsPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_devicewithalerts", deviceWithAlertsPage.getTimeToLoad());
			insertions.put("total_records_devicewithalerts", deviceWithAlertsPage.getTotalRecords());
			
			LastDayOfSupportPage lastDayOfSupportPage = new LastDayOfSupportPage(landingPage.clickOnLNPlink(BasePage.LDOS));
			lastDayOfSupportPage.waitForContentArea();
			insertions.put("time_for_initial_load_ldos", lastDayOfSupportPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_ldos", lastDayOfSupportPage.getTimeToLoad());
			insertions.put("total_records_ldos", lastDayOfSupportPage.getTotalRecords());
		
			ProductAlertsDeltaPage productAlertsDeltaPage = 
					new ProductAlertsDeltaPage(landingPage.clickOnLNPlink(BasePage.PRODUCT_ALERTS_DELTA));
			start = System.nanoTime();
			productAlertsDeltaPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadprodalertsdelta = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";

			insertions.put("time_for_initial_load_prodalertsdelta", "2 secs");
			insertions.put("time_taken_to_load_prodalertsdelta", timeTakenToLoadprodalertsdelta);

	    
			AllContractsPage allContractsPage = new AllContractsPage(landingPage.clickOnLNPlink(BasePage.ALL_CONTRACTS));
			allContractsPage.waitForContentArea();
			insertions.put("time_for_initial_load_allcontracts", allContractsPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_allcontracts", allContractsPage.getTimeToLoad());
			insertions.put("total_records_allcontracts", allContractsPage.getTotalRecords());
			
			ExpiringContractsPage expiringContractsPage = new ExpiringContractsPage(landingPage.clickOnLNPlink(BasePage.EXPIRING_CONTRACTS));
			expiringContractsPage.waitForContentArea();
			insertions.put("time_for_initial_load_expiringcontracts", expiringContractsPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_expiringcontracts", expiringContractsPage.getTimeToLoad());
			insertions.put("total_records_expiringcontracts", expiringContractsPage.getTotalRecords());
			
			CoveredPage coveredPage = new CoveredPage(landingPage.clickOnLNPlink(BasePage.COVERED));
			coveredPage.waitForContentArea();
			insertions.put("time_for_initial_load_covered", coveredPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_covered", coveredPage.getTimeToLoad());
			insertions.put("total_records_covered", coveredPage.getTotalRecords());
			
			NotCoveredPage notCoveredPage = new NotCoveredPage(landingPage.clickOnLNPlink(BasePage.NOT_COVERED));
			notCoveredPage.waitForContentArea();
			insertions.put("time_for_initial_load_notcovered", notCoveredPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_notcovered", notCoveredPage.getTimeToLoad());
			insertions.put("total_records_notcovered", notCoveredPage.getTotalRecords());
			
			ExpiringDeviceCoveragePage expiringDeviceCoveragePage = 
					new ExpiringDeviceCoveragePage(landingPage.clickOnLNPlink(BasePage.EXPIRING_DEVICE_COVERAGE));
			expiringDeviceCoveragePage.waitForContentArea();
			insertions.put("time_for_initial_load_expiringdevicecoverage", expiringDeviceCoveragePage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_expiringdevicecoverage", expiringDeviceCoveragePage.getTimeToLoad());
			insertions.put("total_records_expiringdevicecoverage", expiringDeviceCoveragePage.getTotalRecords());
			
			ContractDuplicatesPage contractDuplicatesPage = 
					new ContractDuplicatesPage(landingPage.clickOnLNPlink(BasePage.CONTRACT_DUPLICATES));
			contractDuplicatesPage.waitForContentArea();
			insertions.put("time_for_initial_load_contractduplicates", contractDuplicatesPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_contractduplicates", contractDuplicatesPage.getTimeToLoad());
			insertions.put("total_records_contractduplicates", contractDuplicatesPage.getTotalRecords());

			AggregatedReportPage aggregatedReportPage = 
					new AggregatedReportPage(landingPage.clickOnLNPlink(BasePage.AGGREGATED_REPORT));
			start = System.nanoTime();
			aggregatedReportPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadaggreport = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";

			insertions.put("time_for_initial_load_aggreport", "2 secs");
			insertions.put("time_taken_to_load_aggreport", timeTakenToLoadaggreport);
		
			if(appPage.hasARoleForCustomer(BasePage.SNTC_GLOBAL_APP_ADMIN, BasePage.SNTC_GLOBAL_APP_ADMIN_PARTY)){
//			if(userRole.equalsIgnoreCase("Employee")){
				AllSupportCasesPage allSupportCasesPage = 
						new AllSupportCasesPage(landingPage.clickOnLNPlink(BasePage.ALL_SUPPORT_CASES));
				allSupportCasesPage.waitForContentArea();
				insertions.put("time_for_initial_load_allsupportcases", allSupportCasesPage.getTimeToInitialLoad());
				insertions.put("time_taken_to_load_allsupportcases", allSupportCasesPage.getTimeToLoad());
				insertions.put("total_records_allsupportcases", allSupportCasesPage.getTotalRecords());			
			}else{
				MySupportCasesPage mySupportCasesPage = 
						new MySupportCasesPage(landingPage.clickOnLNPlink(BasePage.MY_SUPPORT_CASES));
				mySupportCasesPage.waitForContentArea();
				insertions.put("time_for_initial_load_allsupportcases", mySupportCasesPage.getTimeToInitialLoad());
				insertions.put("time_taken_to_load_allsupportcases", mySupportCasesPage.getTimeToLoad());
				insertions.put("total_records_allsupportcases", mySupportCasesPage.getTotalRecords());
			}
		
			InventorySummaryPage inventorySummaryPage = 
					new InventorySummaryPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_SUMMARY));
			start = System.nanoTime();
			inventorySummaryPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadInvSummary = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";

			insertions.put("time_for_initial_load_invsummary", "2 secs");
			insertions.put("time_taken_to_load_invsummary", timeTakenToLoadInvSummary);
			
			
			AllEquipmentPage allEquipmentPage = 
					new AllEquipmentPage(landingPage.clickOnLNPlink(BasePage.ALL_EQUIPMENT));
			allEquipmentPage.waitForContentArea();
			insertions.put("time_for_initial_load_allequipment", allEquipmentPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_allequipment", allEquipmentPage.getTimeToLoad());
			insertions.put("total_records_allequipment", allEquipmentPage.getTotalRecords());						
		
			InventoryDuplicatesPage inventoryDuplicatesPage = 
					new InventoryDuplicatesPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_DUPLICATES));
			inventoryDuplicatesPage.waitForContentArea();
			insertions.put("time_for_initial_load_invduplicates", inventoryDuplicatesPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_invduplicates", inventoryDuplicatesPage.getTimeToLoad());
			insertions.put("total_records_invduplicates", inventoryDuplicatesPage.getTotalRecords());						
			
			InventoryByProductPage inventoryByProductPage = 
					new InventoryByProductPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_BY_PRODUCT));
			inventoryByProductPage.waitForContentArea();
			insertions.put("time_for_initial_load_invbyproduct", inventoryByProductPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_invbyproduct", inventoryByProductPage.getTimeToLoad());
			insertions.put("total_records_invbyproduct", inventoryByProductPage.getTotalRecords());						
			
			InventoryCollectionDeltaPage inventoryCollectionDeltaPage = 
					new InventoryCollectionDeltaPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_COLLECTION_DELTA));
			start = System.nanoTime();
			inventoryCollectionDeltaPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadInvDelta = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";

			insertions.put("time_for_initial_load_invcollectiondelta", "2 secs");
			insertions.put("time_taken_to_load_invcollectiondelta", timeTakenToLoadInvDelta);
		
			InventoryBySites inventoryBySites = 
					new InventoryBySites(landingPage.clickOnLNPlink(BasePage.INVENTORY_BY_SITES));
			inventoryBySites.waitForContentArea();
			insertions.put("time_for_initial_load_invbysites", inventoryBySites.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_invbysites", inventoryBySites.getTimeToLoad());
			insertions.put("total_records_invbysites", inventoryBySites.getTotalRecords());						
			
			AllHostsPage allHostsPage = 
					new AllHostsPage(landingPage.clickOnLNPlink(BasePage.ALL_HOSTS));
			allHostsPage.waitForContentArea();
			insertions.put("time_for_initial_load_allhosts", allHostsPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_allhosts", allHostsPage.getTimeToLoad());
			insertions.put("total_records_allhosts", allHostsPage.getTotalRecords());						
			
			CustomInventoryPage customInventoryPage = 
					new CustomInventoryPage(landingPage.clickOnLNPlink(BasePage.CUSTOM_INVENTORY));
			customInventoryPage.waitForContentArea();
			insertions.put("time_for_initial_load_custominv", customInventoryPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_custominv", customInventoryPage.getTimeToLoad());
			insertions.put("total_records_custominv", customInventoryPage.getTotalRecords());									
			
			InventoryInsightSummaryPage inventoryInsightSummaryPage = 
					new InventoryInsightSummaryPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_INSIGHT_SUMMARY));
			start = System.nanoTime();
			inventoryInsightSummaryPage.waitForContentArea();
			stop = System.nanoTime();
			String timeTakenToLoadInsightSummary = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			
			insertions.put("time_for_initial_load_invinsightsummary", "2 secs");
			insertions.put("time_taken_to_load_invinsightsummary", timeTakenToLoadInsightSummary);
		
			NotCollectedPage notCollectedPage = 
					new NotCollectedPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_INSIGHT_NOT_COLLECTED));
			notCollectedPage.waitForContentArea();
			insertions.put("time_for_initial_load_notcollected", notCollectedPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_notcollected", notCollectedPage.getTimeToLoad());
			insertions.put("total_records_notcollected", notCollectedPage.getTotalRecords());	
			
			logger.info("TIME TAKEN FOR NOT COLLECTED- Initial:{} ; Total:{} ",
					notCollectedPage.getTimeToInitialLoad(), notCollectedPage.getTimeToLoad());
		
			ThirdPartyPage thirdPartyPage = 
					new ThirdPartyPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_INSIGHT_THIRD_PARTY));
			thirdPartyPage.waitForContentArea();
			insertions.put("time_for_initial_load_thirdparty", thirdPartyPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_thirdparty", thirdPartyPage.getTimeToLoad());
			insertions.put("total_records_thirdparty", thirdPartyPage.getTotalRecords());															
			
			
			InventoryInsightDuplicatesPage inventoryInsightDuplicatesPage = 
					new InventoryInsightDuplicatesPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_INSIGHT_DUPLICATES));
			inventoryInsightDuplicatesPage.waitForContentArea();
			insertions.put("time_for_initial_load_invinsightduplicates", inventoryInsightDuplicatesPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_invinsightduplicates", inventoryInsightDuplicatesPage.getTimeToLoad());
			insertions.put("total_records_invinsightduplicates", inventoryInsightDuplicatesPage.getTotalRecords());																		
			
			NotRecognisedPage notRecognisedPage = 
					new NotRecognisedPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_INSIGHT_NOT_RECOGNISED));
			notRecognisedPage.waitForContentArea();
			insertions.put("time_for_initial_load_notrecognised", notRecognisedPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_notrecognised", notRecognisedPage.getTimeToLoad());
			insertions.put("total_records_notrecognised", notRecognisedPage.getTotalRecords());				
			
			NotFieldReplacablePage notFieldReplacablePage = 
					new NotFieldReplacablePage(landingPage.clickOnLNPlink(BasePage.INVENTORY_INSIGHT_NOT_FIELD_REPLACABLE));
			notFieldReplacablePage.waitForContentArea();
			insertions.put("time_for_initial_load_notfieldreplacable", notFieldReplacablePage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_notfieldreplacable", notFieldReplacablePage.getTimeToLoad());
			insertions.put("total_records_notfieldreplacable", notFieldReplacablePage.getTotalRecords());				
			
			InventoryInsightOthersPage inventoryInsightOthersPage = 
					new InventoryInsightOthersPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_INSIGHT_OTHERS));
			inventoryInsightOthersPage.waitForContentArea();
			insertions.put("time_for_initial_load_invinsightothers", inventoryInsightOthersPage.getTimeToInitialLoad());
			insertions.put("time_taken_to_load_invinsightothers", inventoryInsightOthersPage.getTimeToLoad());
			insertions.put("total_records_invinsightothers", inventoryInsightOthersPage.getTotalRecords());				
			
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
			
		}catch(Exception exp){
		  logger.debug("EXCEPTION IN click home view method");
		    final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    exp.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}	
		long stopOfTheTest = System.nanoTime();
		String timeTakenToTest = new Long(TimeUnit.SECONDS.convert((stopOfTheTest - startOfTheTest), TimeUnit.NANOSECONDS)).toString() + " sec";
		logger.debug("TOTAL TIME TAKEN FOR THE ENTIRE TEST: {}",timeTakenToTest);
	}

	public PageLoadTestParams getLoadTestParameter() {
		return loadTestParameter;
	}

	public void setLoadTestParameter(PageLoadTestParams loadTestParameter) {
		this.loadTestParameter = loadTestParameter;
	}
}