package com.cisco.cstg.autotools.tests.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.support.configuration.ComponentConfiguration;
import com.cisco.cstg.autotools.support.configuration.Configuration;

public class PSSLeftNavigationPage extends BasePage{
	
	private static final Logger logger = LoggerFactory.getLogger(PSSLeftNavigationPage.class);
	
	/**
	 * Start Config Properties 
	 */
	protected static final Configuration lnpConfig = new ComponentConfiguration(PSSLeftNavigationPage.class);
	public static final String MAIN_IFRAME_CLASSNAME=lnpConfig.getStringValue(PageConstants.MAIN_IFRAME_CLASSNAME);
	
	public static final String USEFUL_LINKS=lnpConfig.getStringValue(PageConstants.USEFUL_LINKS);
	
	public static final String CUSTOMER_SUMMARY=lnpConfig.getStringValue(PageConstants.CUSTOMER_SUMMARY);
	public static final String ENGINEER=lnpConfig.getStringValue(PageConstants.ENGINEER);
	public static final String SALES_MANAGER=lnpConfig.getStringValue(PageConstants.SALES_MANAGER);
	public static final String SERVICE_EXECUTIVE=lnpConfig.getStringValue(PageConstants.SERVICE_EXECUTIVE);
	
	public PSSLeftNavigationPage(WebDriver driver) {
		super(driver);
		logger.debug("CREATED LNP");
	}
}
