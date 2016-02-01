package com.cisco.cstg.autotools.tests.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductAlertsDeltaPage extends LandingPage {

	protected static final Logger logger = LoggerFactory.getLogger(ProductAlertsDeltaPage.class);
	
	private String sourceString = "SNTC_PRODUCTALERTS_ALERTSDELTA";
	 
	private String pageName="All Collectors";
	
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

	public ProductAlertsDeltaPage(WebDriver driver) {
		super(driver);
		
	}
}