package com.cisco.cstg.autotools.tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.params.AppSettingsPageParameter;

@RunWith(Parameterized.class)
@ContextConfiguration(locations=
{
 "/applicationContext.xml",
 "/applicationContext-security.xml",
 "/spring-servlet.xml"
}
)
public class AppSettingsPageTest extends BaseTest {
	
	protected static final Logger logger = LoggerFactory.getLogger(AppSettingsPageTest.class);
	
	private final TestContextManager testContextManager;
	
	private AppSettingsPageParameter testParameter;

	@Parameters
	public static Collection<AppSettingsPageParameter> getParameters(){

		TestConstants.logger.debug("INITIALIZING THE CUSTOMER LIST");
		Configuration paramConfig = new ComponentConfiguration(AppSettingsPageTest.class);
		
		String [] usernames = paramConfig.getStringValue(USER_NAMES).split(",");
		String [] passwords = paramConfig.getStringValue(PASSWORDS).split(",");
		String [] customernames = paramConfig.getStringValue(CUSTOMER_NAMES).split(",");
		String [] inventorynames = paramConfig.getStringValue(INVENTORY_NAMES).split(",");
		
		List<AppSettingsPageParameter> params = new ArrayList<AppSettingsPageParameter>();
		
		for(int i=0;i<usernames.length;i++){
			AppSettingsPageParameter param = new AppSettingsPageParameter();
			param.setUserName(usernames[i].trim());
			param.setPassword(passwords[i].trim());
			param.setCustomerName(customernames[i].trim());
			param.setUrl(paramConfig.getStringValue(URL).trim());
			params.add(param);
		}
		
		return params;
	}
	
	/**
	 * Constructor for the test
	 * @param partnerName
	 */
	public AppSettingsPageTest(AppSettingsPageParameter testParameter) throws Exception{

		logger.debug("INSIDE THE CONSTRUCTOR");
		logger.debug("partner name is: {}",testParameter.getCustomerName());
		this.testContextManager = new TestContextManager(getClass());
		this.testParameter = testParameter;
	}
	
	@Before
	public void setUp() throws Exception {
			this.testContextManager.prepareTestInstance(this);	
			driver = openFireFoxProfileDriverWithoutAuth(BasePage.SNTC_FIREFOX_PROFILE_NAME);
			driver.manage().timeouts().implicitlyWait(TestConstants.WEBDRIVER_IMPLICIT_TIMEOUT_VALUE, TimeUnit.SECONDS);			
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
	public void appSettingPageLoadTest(){
		
	}
	
	@Test
	public void roleVerificationTest(){
		
	}
}
