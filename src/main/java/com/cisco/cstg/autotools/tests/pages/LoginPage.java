package com.cisco.cstg.autotools.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage{
	
	protected WebElement uname = null;
	protected WebElement password = null;
	
	
	protected static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public WebDriver loginToPortal(String baseUrl, String username, String pwd){

				driver.get(baseUrl);
				WebDriverWait pageLoaded = new WebDriverWait(driver, 60);
				try {
					uname = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.id("userInput")));
					logger.info("Found the userInput field");
				} catch(NoSuchElementException exp){
					logger.info("Login Elemnt not found");
				}catch (Exception e) {
					// failed to locate element.
					logger.info("Unable to locate Username Element. Page not loaded(?)");
				}
				uname.clear();
				uname.sendKeys(username);
				try {
					password = driver.findElement(By.id("passwordInput"));
					logger.info("Found the passwordInput field");
				} catch (Exception e) {
					// failed to locate element
					logger.info("Failed to locate password element.");
				}

				password.clear();
				password.sendKeys(pwd);

				WebElement button = null;
				try {
					button = driver.findElement(By.id("login-form"));
					logger.info("SUBMITTING THE LOGIN FORM");
				} catch (Exception e) {
					// can't locate the login button
					logger.info("Failed to locate login button");
				}
				button.submit();
				return driver;
	}
}