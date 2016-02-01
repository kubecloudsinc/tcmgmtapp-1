package com.cisco.cstg.autotools.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;

public class CopyOfBaseTest {

	protected WebDriver driver;
	protected boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();

	/**
	 * Tests related configuration.
	 */
	public static Configuration testConfig = new ComponentConfiguration(TestConstants.class);

	public static final String summaryReportTemlateName = testConfig.getStringValue(TestConstants.TEST_SUMMARY_REPORT_TEMPLATE_NAME);
	public static final String pageloadReportTemplateName = testConfig.getStringValue(TestConstants.PAGE_LOAD_REPORT_TEMPLATE_NAME);
	public static final String toEmailAddresses = testConfig.getStringValue(TestConstants.TO_EMAIL_ADDRESSES);
	public static final String PSS_PAGE_LOAD_REPORT_TEMPLATE_NAME = testConfig.getStringValue(TestConstants.PSS_PAGE_LOAD_REPORT_TEMPLATE_NAME);
	public static final String PSS_TO_EMAIL_ADDRESSES = testConfig.getStringValue(TestConstants.PSS_TO_EMAIL_ADDRESSES);	
	
	private static final Logger logger = LoggerFactory.getLogger(CopyOfBaseTest.class);

	@Before
	public void setUp() throws Exception {
//		try {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
//		} catch (Exception exp) {
//			final Writer result = new StringWriter();
//			final PrintWriter printWriter = new PrintWriter(result);
//			exp.printStackTrace(printWriter);
//			logger.debug(result.toString());
//		}

	}

	@After
	public void tearDown() throws Exception {
//	    driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public WebDriver openFireFoxProfileDriver(String profileName) {
		try {
			FirefoxProfile profile = null;
			profile = new ProfilesIni().getProfile(profileName);
//			File autoauth = new File(new File("").getAbsolutePath()+ "/extensions/autoauth-2.1-fx+fn.xpi");
			
			logger.debug("Path of the file: "+ Thread.currentThread().getContextClassLoader().getResource("autoauth-2.1-fx+fn.xpi"));
			File autoauth = new File(Thread.currentThread().getContextClassLoader().getResource("autoauth-2.1-fx+fn.xpi").getPath());
			profile.addExtension(autoauth);
			driver = new FirefoxDriver(profile);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.printStackTrace();
			final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    e.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}
		return driver;
	}
	
	public WebDriver openFireFoxProfileDriverWithoutAuth(String profileName) {
		try {
			FirefoxProfile profile = null;
			profile = new ProfilesIni().getProfile(profileName);
			driver = new FirefoxDriver(profile);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	

		} catch (Exception e) {
			e.printStackTrace();
			final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    e.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}
		return driver;
	}
}