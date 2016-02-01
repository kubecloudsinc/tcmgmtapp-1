package com.cisco.cstg.autotools.tests.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySupportCasesPage extends LandingPage {

	protected static final Logger logger = LoggerFactory.getLogger(MySupportCasesPage.class);
	
	private String sourceString = 
			"My%20Support%20Cases%20for%20past%2090%20days";

	private String pageName="My Support Cases for past 90 days";
	
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

	public MySupportCasesPage(WebDriver driver) {
		super(driver);
		
	}
	
	public WebDriver waitForContentArea() throws Exception{
	 return super.waitForContentArea(sourceString, pageName);
	}
}