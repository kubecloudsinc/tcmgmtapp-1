package com.cisco.cstg.autotools.tests.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpiringDeviceCoveragePage extends LandingPage {

	protected static final Logger logger = LoggerFactory.getLogger(ExpiringDeviceCoveragePage.class);
	
	private String sourceString = "Expiring%20Device%20Coverages";

	private String pageName="Expiring Device Coverages";
	
	public String getSourceString() {
		return sourceString;
	}

	public void setSourceString(String sourceString) {
		this.sourceString = sourceString;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public ExpiringDeviceCoveragePage(WebDriver driver) {
		super(driver);
		
	}
	
	public WebDriver waitForContentArea() throws Exception{
	 return super.waitForContentArea(sourceString, pageName);
	}
}