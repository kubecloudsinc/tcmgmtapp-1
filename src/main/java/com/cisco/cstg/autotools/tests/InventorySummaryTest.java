package com.cisco.cstg.autotools.tests;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.semantic.test.ApplicationConstants;
import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.pages.InventorySummaryPage;
import com.cisco.cstg.autotools.tests.pages.LandingPage;
import com.cisco.cstg.autotools.tests.pages.LoginPage;

/**
 * 
 * @author sichituk
 *
 */
public class InventorySummaryTest extends BaseTest {
	
	protected static final Logger logger = LoggerFactory.getLogger(InventorySummaryTest.class);
	private static final Configuration INVENTORY_SUMMARY_TEST_CONFIG = new ComponentConfiguration(InventorySummaryTest.class);
	private static Configuration INVENTORY_SUMMARY_TEST_ACTUAL_VALUES;
	
	@Before
	public void  setUp() throws Exception {
//		driver = openFireFoxProfileDriverWithoutAuth(BasePage.SNTC_FIREFOX_PROFILE_NAME);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

	@After
	public void tearDown() throws Exception {
//		driver.quit();
//		String verificationErrorString = verificationErrors.toString();
//		if (!"".equals(verificationErrorString)) {
//			fail(verificationErrorString);
//		}
	}	
	
	@BeforeClass
	public static void extractData(){
		
//		Configuration paramConfig = new ComponentConfiguration(InventorySummaryTest.class);
//		String [] allsource =paramConfig.getStringValue(INVENTORY_ALLSOURCE_DATA).split(";");
		WebDriver staticDriver;
				
		try
		{
			FirefoxProfile profile = null;
			profile = new ProfilesIni().getProfile(BasePage.SNTC_FIREFOX_PROFILE_NAME);
			staticDriver = new FirefoxDriver(profile);
			staticDriver.manage().timeouts().implicitlyWait(TestConstants.WEBDRIVER_IMPLICIT_TIMEOUT_VALUE, TimeUnit.SECONDS);
			
			logger.debug("INSIDE ClickInventory Summary");
			//Login to portal
			//start the test
			staticDriver = new LoginPage(staticDriver).loginToPortal(BasePage.baseUrlNextGen, BasePage.username, BasePage.pwd);
			staticDriver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
			staticDriver.manage().window().maximize();
			
			logger.debug("INSIDE ClickInventory Summary METHOD: loginToPortal Complete");
			
			logger.debug("INSIDE clickInventorySummary");
		
			LandingPage landingPage;
			landingPage = new LandingPage(staticDriver);
			
			landingPage.waitForLNPLoad();
			
			landingPage.chooseCustomer(INVENTORY_SUMMARY_TEST_CONFIG.getStringValue(CUSTOMER_NAMES));
			landingPage.chooseInventory(INVENTORY_SUMMARY_TEST_CONFIG.getStringValue(INVENTORY_NAMES));
			
			InventorySummaryPage invSummaryPage;
			invSummaryPage = new InventorySummaryPage(landingPage.clickOnLNPlink(BasePage.INVENTORY_SUMMARY));
			
			invSummaryPage.waitForContentArea();
			
			ArrayList<String> summaryListData = new ArrayList<String>();
			summaryListData = invSummaryPage.getTableRowData(); // To Retive the Data from Summary Data Grid
			
			int size = summaryListData.size();
			logger.debug(" Total records in trlist "+size);
			staticDriver.quit();

			// get the Directory name of the file.
			File expectedValuesFile = new File(
										ApplicationConstants.getClasspathDirectory().toString(),
										InventorySummaryTestActualValues.class.getName()+".cfg"
									  ).getAbsoluteFile();
			logger.debug("CReated the file object");
			
			FileUtils.writeLines(expectedValuesFile, summaryListData);
			
		}
		catch(Exception exp){
		  logger.debug("EXCEPTION IN click InventorySummary method");
		    final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    exp.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}		
	}
	
	@Test
	public void verifyDevicesInAllSourcesChassis() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.CHASSIS));
		Assert.assertEquals("InventoryAllSources Chassis count not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.CHASSIS));
		
	}
	
	@Test
	public void verifyDevicesInAllSourcesModules() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.MODULES));
		Assert.assertEquals("InventoryAllSources Modules count not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.MODULES));
	}
	
	@Test
	public void verifyDevicesInAllSourcesPowerSupply() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.POWER_SUPPLY));
		Assert.assertEquals("InventoryAllSources Power Supply count not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.POWER_SUPPLY));
	}
	
	@Test
	public void verifyDevicesInAllSourcesFan() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.FAN));
		Assert.assertEquals("InventoryAllSources FAN count not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.FAN));
	}
	
	@Test
	public void verifyDevicesInAllSourcesOthers() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.OTHERS));
		Assert.assertEquals("InventoryAllSources Others count not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.OTHERS));
	}
	
	@Test
	public void verifyDevicesInAllSourcesTotal() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.TOTAL));
		Assert.assertEquals("InventoryAllSources Total count not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.INVENTORY_ALL_SOURCES+InventorySummaryPage.TOTAL));
	}
	
	@Test
	public void verifyDevicesCollectedChassis() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.CHASSIS));
		Assert.assertEquals("Devices Collected Chassis not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.CHASSIS));
	}
	
	@Test
	public void verifyDevicesCollectedModules() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.MODULES));
		Assert.assertEquals("Devices Collected Modules not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.MODULES));
	}
	
	@Test
	public void verifyDevicesCollectedPowerSupply() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.POWER_SUPPLY));
		Assert.assertEquals("Devices Collected Power Supply not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.POWER_SUPPLY));
	}
	
	@Test
	public void verifyDevicesCollectedFan() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.FAN));
		Assert.assertEquals("Devices Collected FAN not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.FAN));
	}
	
	@Test
	public void verifyDevicesCollectedOthers() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.OTHERS));
		Assert.assertEquals("Devices Collected Others not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.OTHERS));
	}
	
	@Test
	public void verifyDevicesCollectedTotal() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.TOTAL));
		Assert.assertEquals("Devices Collected Total not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COLLECTED+InventorySummaryPage.TOTAL));
	}
	
	@Test
	public void verifyDevicesImportedChassis() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.CHASSIS));
		Assert.assertEquals("Devices Imported Chassis not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.CHASSIS));
	}
	
	@Test
	public void verifyDevicesImportedModules() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.MODULES));
		Assert.assertEquals("Devices Imported Modules not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.MODULES));
	}
	
	@Test
	public void verifyDevicesImportedPowerSupply() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.POWER_SUPPLY));
		Assert.assertEquals("Devices Imported Power Supply not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.POWER_SUPPLY));
	}
	
	@Test
	public void verifyDevicesImportedFan() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.FAN));
		Assert.assertEquals("Devices Imported Fan not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.FAN));
	}
	
	@Test
	public void verifyDevicesImportedOthers() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.OTHERS));
		Assert.assertEquals("Devices Imported Chassis not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.OTHERS));
	}
	
	@Test
	public void verifyDevicesImportedTotal() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.TOTAL));
		Assert.assertEquals("Devices Imported Total not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_IMPORTED+InventorySummaryPage.TOTAL));
	}
	
	@Test
	public void verifyDevicesRecognizedChassis() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.CHASSIS));
		Assert.assertEquals("Devices Recognized Chassis not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.CHASSIS));
	}
	
	@Test
	public void verifyDevicesRecognizedModules() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.MODULES));
		Assert.assertEquals("Devices Recognized Modules not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.MODULES));
	}
	
	@Test
	public void verifyDevicesRecognizedPowerSupply() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.POWER_SUPPLY));
		Assert.assertEquals("Devices Recognized Power Supply not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.POWER_SUPPLY));
	}
	
	@Test
	public void verifyDevicesRecognizedFan() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.FAN));
		Assert.assertEquals("Devices Recognized Fan not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.FAN));
	}
	
	@Test
	public void verifyDevicesRecognizedOthers() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.OTHERS));
		Assert.assertEquals("Devices Recognized Others not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.OTHERS));
	}
	
	@Test
	public void verifyDevicesRecognizedTotal() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.TOTAL));
		Assert.assertEquals("Devices Recognized Total not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_RECOGNIZED+InventorySummaryPage.TOTAL));
	}
	
	@Test
	public void verifyDevicesCoveredChassis() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.CHASSIS));
		Assert.assertEquals("Devices Covered Chassis not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.CHASSIS));
	}
	
	@Test
	public void verifyDevicesCoveredModules() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.MODULES));
		Assert.assertEquals("Devices Covered Modules not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.MODULES));
	}
	
	@Test
	public void verifyDevicesCoveredPowerSupply() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.POWER_SUPPLY));
		Assert.assertEquals("Devices Covered Power Supply not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.POWER_SUPPLY));
	}
	
	@Test
	public void verifyDevicesCoveredFan() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.FAN));
		Assert.assertEquals("Devices Covered Fan not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.FAN));
	}
	
	@Test
	public void verifyDevicesCoveredOthers() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.OTHERS));
		Assert.assertEquals("Devices Covered Others not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.OTHERS));
	}
	
	@Test
	public void verifyDevicesCoveredTotal() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.TOTAL));
		Assert.assertEquals("Devices Covered Total not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_COVERED+InventorySummaryPage.TOTAL));
	}
	
	@Test
	public void verifyDevicesNotCoveredChassis() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.CHASSIS));
		Assert.assertEquals("Devices Not Covered Chassis not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.CHASSIS));
	}
	
	@Test
	public void verifyDevicesNotCoveredModules() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.MODULES));
		Assert.assertEquals("Devices Not Covered Modules not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.MODULES));
	}
	
	@Test
	public void verifyDevicesNotCoveredPowerSupply() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.POWER_SUPPLY));
		Assert.assertEquals("Devices Not Covered Power Supply not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.POWER_SUPPLY));
	}
	
	@Test
	public void verifyDevicesNotCoveredFan() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.FAN));
		Assert.assertEquals("Devices Not Covered Fan not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.FAN));
	}
	
	@Test
	public void verifyDevicesNotCoveredOthers() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.OTHERS));
		Assert.assertEquals("Devices Not Covered Others not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.OTHERS));
	}
	
	@Test
	public void verifyDevicesNotCoveredTotal() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.TOTAL));
		Assert.assertEquals("Devices Not Covered Total not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.DEVICES_NOT_COVERED+InventorySummaryPage.TOTAL));
	}
	
	@Test
	public void verifyPastLDOSChassis() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.CHASSIS));
		Assert.assertEquals("Past LDOS Chassis not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.CHASSIS));
	}
	
	@Test
	public void verifyPastLDOSModules() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.MODULES));
		Assert.assertEquals("Past LDOS modules not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.MODULES));
	}
	
	@Test
	public void verifyPastLDOSPowerSupply() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.POWER_SUPPLY));
		Assert.assertEquals("Past LDOS Power Supply not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.POWER_SUPPLY));
	}
	
	@Test
	public void verifyPastLDOSFan() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.FAN));
		Assert.assertEquals("Past LDOS Fan not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.FAN));
	}
	
	@Test
	public void verifyPastLDOSOthers() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.OTHERS));
		Assert.assertEquals("Past LDOS Others not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.OTHERS));
	}
	
	@Test
	public void verifyPastLDOSTotal() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.TOTAL));
		Assert.assertEquals("Past LDOS Total not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.PAST_LDOS+InventorySummaryPage.TOTAL));
	}
	
	@Test
	public void verifyLDOSWithin12Chassis() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.CHASSIS));
		Assert.assertEquals("Past LDOS Within 12 months Chassis not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.CHASSIS));
	}
	
	@Test
	public void verifyLDOSWithin12Modules() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.MODULES));
		Assert.assertEquals("Past LDOS Within 12 months Modules not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.MODULES));
	}
	
	@Test
	public void verifyLDOSWithin12PowerSupply() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.POWER_SUPPLY));
		Assert.assertEquals("Past LDOS Within 12 months Power Supply not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.POWER_SUPPLY));
	}
	
	@Test
	public void verifyLDOSWithin12Fan() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.FAN));
		Assert.assertEquals("Past LDOS Within 12 months Fan not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.FAN));
	}
	
	@Test
	public void verifyLDOSWithin12Others() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.OTHERS));
		Assert.assertEquals("Past LDOS Within 12 months Others not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.OTHERS));
	}
	
	@Test
	public void verifyLDOSWithin12Total() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.TOTAL));
		Assert.assertEquals("Past LDOS Within 12 months Total not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_WITHIN_12_MONTHS+InventorySummaryPage.TOTAL));
	}
	
	@Test
	public void verifyLDOSWithin24Chassis() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.CHASSIS));
		Assert.assertEquals("Past LDOS Over 12 and Within 24 months Chassis not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.CHASSIS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.CHASSIS));
	}
	
	@Test
	public void verifyLDOSWithin24Modules() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.MODULES));
		Assert.assertEquals("Past LDOS Over 12 and Within 24 months Modules not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.MODULES),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.MODULES));
	}
	
	@Test
	public void verifyLDOSWithin24PowerSupply() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.POWER_SUPPLY));
		Assert.assertEquals("Past LDOS Over 12 and Within 24 months Power Supply not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.POWER_SUPPLY),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.POWER_SUPPLY));
	}
	
	@Test
	public void verifyLDOSWithin24Fan() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.FAN));
		Assert.assertEquals("Past LDOS Over 12 and Within 24 months Fan not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.FAN),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.FAN));
	}
	
	@Test
	public void verifyLDOSWithin24Others() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.OTHERS));
		Assert.assertEquals("Past LDOS Over 12 and Within 24 months Others not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.OTHERS),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.OTHERS));
	}
	
	@Test
	public void verifyLDOSWithin24Total() throws Exception{
		INVENTORY_SUMMARY_TEST_ACTUAL_VALUES = new ComponentConfiguration(InventorySummaryTestActualValues.class);
		logger.debug("Value {} is compared with expected value {}",
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.TOTAL));
		Assert.assertEquals("Past LDOS Over 12 and Within 24 months Total not matching with expected Value", 
				INVENTORY_SUMMARY_TEST_CONFIG.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.TOTAL),
				INVENTORY_SUMMARY_TEST_ACTUAL_VALUES.getIntegerValue(InventorySummaryPage.LDOS_OVER_12_MONTHS_AND_WITHIN_24_MONTHS+InventorySummaryPage.TOTAL));
	}
	
}