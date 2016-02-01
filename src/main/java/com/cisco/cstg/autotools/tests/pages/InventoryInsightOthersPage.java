package com.cisco.cstg.autotools.tests.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryInsightOthersPage extends LandingPage {

	protected static final Logger logger = LoggerFactory.getLogger(InventoryInsightOthersPage.class);
	
	private String sourceString = "title=Others";
	 
	private String pageName="Others";

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

	public InventoryInsightOthersPage(WebDriver driver) {
		super(driver);
		
	}
	
	public WebDriver waitForContentArea() throws Exception{
	 return super.waitForContentArea(sourceString, pageName);
	}
}