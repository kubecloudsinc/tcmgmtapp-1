package com.cisco.cstg.autotools.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.cstg.autotools.dao.PamCustomerDeltaDao;
import com.cisco.cstg.autotools.semantic.test.ApplicationConstants;
import com.cisco.cstg.autotools.support.exception.LinkNotFoundException;
import com.cisco.cstg.autotools.tests.pages.AllEquipmentPage;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.pages.LandingPage;
import com.cisco.cstg.autotools.tests.pages.LoginPage;
import com.cisco.cstg.autotools.tests.pages.PageConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
{
 "/applicationContext.xml",
 "/applicationContext-security.xml",
 "/spring-servlet.xml"
}
)
public class TestLogin extends BaseTest {
	
	protected static final Logger logger = LoggerFactory.getLogger(TestLogin.class);
	
	@Autowired
	private PamCustomerDeltaDao customerDao;

	@Before
	public void setUp() throws Exception {
			driver = openFireFoxProfileDriverWithoutAuth(BasePage.SNTC_FIREFOX_PROFILE_NAME);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
	}

	@After
	public void tearDown() throws Exception {
//	    driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}	
	
	@Test
	public void loginToPortal(){

		boolean loginSuccessfull = false;
		try{

		//start the test
		driver = new LoginPage(driver).loginToPortal(BasePage.baseUrlNextGen, BasePage.username, BasePage.pwd);
		driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//wait until the page loads.
		loginSuccessfull= true;
		
		}catch(Exception exp){
		  logger.debug("EXCEPTION IN GETTING THE DAO");
		    final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    exp.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}
		Assert.assertTrue("loginToPortal:The Login Failed", loginSuccessfull);
		
//		List<PamCustomerDelta> customers = customerDao.getPamCustomerDeltaByCCOId("appAdministrator");
//		for (PamCustomerDelta pamCustomerDelta : customers) {
//			
//			logger.debug("The Customer ccoid: {}",pamCustomerDelta.getCcoId());
//			logger.debug("The Customer ccoid: {}",pamCustomerDelta.getCreateOraLogin());
//			logger.debug("The Customer ccoid: {}",pamCustomerDelta.getCustomerDeltaObjId());
//			logger.debug("The Customer ccoid: {}",pamCustomerDelta.getDuration());
//		}
		
		
		
	}
	
	@Test
	public void clickHomeView() throws Exception{
		try{
		
		logger.debug("INSIDE ClickHomeView METHOD");
		//Login to portal
		loginToPortal();
		
		logger.debug("INSIDE ClickHomeView METHOD: loginToPortal Complete");
		
		// find the LNP and click on the Home View.
		LandingPage landingPage = new LandingPage(driver);
		//
		logger.debug("INSIDE ClickHomeView METHOD: Landing page obtained");
//		driver = landingPage.clickOnLNPlink(BasePage.APPLICATION_SETTINGS);
//		logger.debug("INSIDE ClickHomeView METHOD: Clicked app settings");
////		driver = landingPage.clickOnDashboard(BasePage.CALENDAR);
//		driver = landingPage.clickOnLNPlink(BasePage.MY_REPORTS);
		driver = landingPage.clickOnLNPlink(BasePage.USEFUL_LINKS);
//		driver = landingPage.clickOnLNPlink(BasePage.NOTIFICATIONS);
//		driver = landingPage.clickOnLNPlink(BasePage.ADMIN);
//		driver = landingPage.clickOnLNPlink(BasePage.ALERT_MANAGEMENT);
//		driver = landingPage.clickOnLNPlink(BasePage.CONTRACT_MANAGEMENT);
//		driver = landingPage.clickOnLNPlink(BasePage.DEVICE_DIAGNOSTICS);
//		driver = landingPage.clickOnLNPlink(BasePage.INVENTORY_MANAGEMENT);
//		driver = landingPage.clickOnLNPlink(BasePage.SMART_NET_TOTAL_CARE);
		
		try{
			AllEquipmentPage allEquipmentPage = 
					new AllEquipmentPage(landingPage.clickOnLNPlink(BasePage.ALL_EQUIPMENT));
			allEquipmentPage.waitForContentArea();
			allEquipmentPage.exportDataGrid(allEquipmentPage.getSourceString(), 
					allEquipmentPage.getPageName(),PageConstants.EXPORT_CSV);	
			
		}catch(LinkNotFoundException exp){
		}
		
		logger.debug("building URL");
		
//		URL url = new URL("https://concsoweb-prd.cisco.com/DispatcherServices/mapping/view/CustomerUI/myreports/MyReportDownloadServlet.action?timestamp=1438627309248&outputid=1640755&reportName=%22My Reports%22&ccoID=sntcprodtest&customerName=%22CISCO+SYSTEMS+INC%22&inventoryName=%22SNTC3xProdSanity%22&reportType=XLSX&ssueContext={%22application%22:%22SNTC-PSS%22,%22partner%22:%22*%22,%22customer%22:%221072741%22,%22network%22:%22379950125555%22}\");callUnica({\"timestamp\":\"1438627309248\",\"outputid\":\"1640755\",\"reportName\":\"My Reports\",\"ccoID\":\"sntcprodtest\",\"customerName\":\"CISCO+SYSTEMS+INC\",\"inventoryName\":\"SNTC3xProdSanity\",\"reportType\":\"XLSX\",\"ssueContext\":\"{application:SNTC-PSS,partner:*,customer:1072741,network:379950125555}\"}");
//		
//		logger.debug("get the data");
//		FileUtils.copyURLToFile(url, new File(ApplicationConstants.getClasspathDirectory().toString(),"downloadFile"));
//		logger.debug("data trxfr complete");
//		
		
		}
		catch(Exception exp){
		  logger.debug("EXCEPTION IN click home view method");
		    final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    exp.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}		
	}
	
	@Test
	public void chooseCustomer(){
		try{
			
		//Login to portal
		driver = new LoginPage(driver).loginToPortal(BasePage.baseUrlNextGen, BasePage.username, BasePage.pwd);
		driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// find the LNP and click on the Home View.
		LandingPage landingPage = new LandingPage(driver);
		landingPage.waitForLNPLoad();
		
//		CHARLESTON COUNTY SCHOOL DISTRICT
//		String [] customerNames = new String [] {"CHARLESTON COUNTY SCHOOL DISTRICT","AARP"};
		boolean choseCustomer=false;
		landingPage.chooseCustomer("CISCO SYSTEMS INC FOR US INTERNAL DEMO EVAL ONLY");
//		choseCustomer=true;
		Assert.assertTrue("chooseCustomer:Customer Selection Failed", choseCustomer);

		}
		catch(Exception exp){
		  logger.debug("EXCEPTION IN click home view method");
		    final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    exp.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}		
	}
	
	@Test
	public void chooseInventory(){
		try{
			
		//Login to portal
		driver = new LoginPage(driver).loginToPortal(BasePage.baseUrlNextGen, BasePage.username, BasePage.pwd);
		driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// find the LNP and click on the Home View.
		LandingPage landingPage = new LandingPage(driver);
		landingPage.waitForLNPLoad();
		
//		CHARLESTON COUNTY SCHOOL DISTRICT
//		String [] customerNames = new String [] {"CHARLESTON COUNTY SCHOOL DISTRICT","AARP"};
		boolean choseCustomer=false;
		landingPage.chooseCustomer("CISCO SYSTEMS INC FOR US INTERNAL DEMO EVAL ONLY");
		choseCustomer=true;
		Assert.assertTrue("chooseCustomer:Customer Selection Failed", choseCustomer);

		boolean choseInventory=false;
		landingPage.chooseAllInventory();
//		choseInventory=true;
		Assert.assertTrue("chooseInventory:Inventory Selection Failed", choseInventory);
		}
		catch(Exception exp){
		  logger.debug("EXCEPTION IN click home view method");
		    final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    exp.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}		
	}
	
	
}