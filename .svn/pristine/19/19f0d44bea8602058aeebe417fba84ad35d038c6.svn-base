package com.cisco.cstg.autotools.semantic.test;

import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;
import com.cisco.cstg.autotools.tests.TestConstants;

public class BaseManager  implements BaseMonitor{
	
	/**
	 * Tests related configuration.
	 */
	protected static Configuration testConfig = new ComponentConfiguration(TestConstants.class);

	protected static final String summaryReportTemlateName = testConfig.getStringValue(TestConstants.TEST_SUMMARY_REPORT_TEMPLATE_NAME);
	protected static final String pageloadReportTemplateName = testConfig.getStringValue(TestConstants.PAGE_LOAD_REPORT_TEMPLATE_NAME);
	protected static final String toEmailAddresses = testConfig.getStringValue(TestConstants.TO_EMAIL_ADDRESSES);

	
}