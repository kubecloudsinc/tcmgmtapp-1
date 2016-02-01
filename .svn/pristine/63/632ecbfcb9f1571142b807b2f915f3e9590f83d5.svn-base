package com.cisco.cstg.autotools.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;

public class TestConstants {
	
	public static final String TEST_SUMMARY_REPORT_TEMPLATE_NAME = "TestSummaryReportTemplate";
	public static final String PAGE_LOAD_REPORT_TEMPLATE_NAME = "PageLoadReportTemplate";
	public static final String PSS_PAGE_LOAD_REPORT_TEMPLATE_NAME = "PSSPageLoadReportTemplate";
	
	public static final String TO_EMAIL_ADDRESSES = "ToEmailAddressess";
	public static final String PSS_TO_EMAIL_ADDRESSES = "PSSToEmailAddressess";
	public static final String TEST_REPORT_DISTRO_KEY = "testReportEmailAddressess";
	
	//The scheduled interval with which the page load time works
	public static final String PAGE_LOAD_MONITOR_RUN_FREQUENCY = "pageLoadMonitorRunFrequency";
	public static final String PAGE_LOAD_MONITOR_INITIAL_DELAY = "pageLoadMonitorInitialDelay";
	
	public static final String PSS_PAGE_LOAD_MONITOR_RUN_FREQUENCY = "pssPageLoadMonitorRunFrequency";
	public static final String PSS_PAGE_LOAD_MONITOR_INITIAL_DELAY = "pssPageLoadMonitorInitialDelay";
	
	public static final String MONITOR_FREQUENCY_UNIT = "monitorFrequencyUnit";
	
	public static final String WEBDRIVER_IMPLICIT_TIMEOUT_KEY = "implicitTimeOut";
	public static final String WEBDRIVER_TIMEOUT_KEY = "timeOut";
	public static final String WEBDRIVER_LONGER_TIMEOUT_KEY="longerTimeOut";
	public static final String MAX_WAIT_TIME_FOR_ELEMENTS_TO_LOAD_KEY="maxWaitForElements";
	
	public static final String HIDDEN_PASSWORD_INDICATOR_KEY = "hiddenPasswordIndicator";
	
	
	/**
	 * Tests related configuration.
	 */
	public static Configuration testConfig = new ComponentConfiguration(TestConstants.class);

	public static final String summaryReportTemlateName = testConfig.getStringValue(TestConstants.TEST_SUMMARY_REPORT_TEMPLATE_NAME);
	public static final String pageloadReportTemplateName = testConfig.getStringValue(TestConstants.PAGE_LOAD_REPORT_TEMPLATE_NAME);
	public static final String toEmailAddresses = testConfig.getStringValue(TestConstants.TO_EMAIL_ADDRESSES);
	public static final String pssPageLoadReportTemplateName = testConfig.getStringValue(TestConstants.PSS_PAGE_LOAD_REPORT_TEMPLATE_NAME);
	public static final String pssToEmailAddresses = testConfig.getStringValue(TestConstants.PSS_TO_EMAIL_ADDRESSES);
	public static final String TEST_REPORT_EMAIL_DISTRIBUTION_LIST = testConfig.getStringValue(TestConstants.TEST_REPORT_DISTRO_KEY);
	
	public static final String HIDDEN_PASSWORD_INDICATOR = testConfig.getStringValue(TestConstants.HIDDEN_PASSWORD_INDICATOR_KEY);
	
	public static final int WEBDRIVER_IMPLICIT_TIMEOUT_VALUE = testConfig.getIntegerValue(TestConstants.WEBDRIVER_IMPLICIT_TIMEOUT_KEY);
	public static final int WEBDRIVER_TIMEOUT_VALUE = testConfig.getIntegerValue(TestConstants.WEBDRIVER_TIMEOUT_KEY);
	public static final int WEBDRIVER_LONGER_TIMEOUT_VALUE = testConfig.getIntegerValue(TestConstants.WEBDRIVER_LONGER_TIMEOUT_KEY);
	public static final int MAX_WAIT_TIME_FOR_ELEMENTS_TO_LOAD = 
											testConfig.getIntegerValue(TestConstants.MAX_WAIT_TIME_FOR_ELEMENTS_TO_LOAD_KEY);
	
	public static final Logger logger = LoggerFactory.getLogger(TestConstants.class);
}
