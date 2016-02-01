package com.cisco.cstg.autotools.semantic.test;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteResult {

	   private String testSuiteName;

	   private String testSuiteDescription;
	   
	   private int totalTests=0;
	   
	   private int totalPassed=0;
	   
	   private int totalFailed=0;
	   
	   List<FailedTestResult> failedTests= new ArrayList<FailedTestResult>();

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}

	public String getTestSuiteDescription() {
		return testSuiteDescription;
	}

	public void setTestSuiteDescription(String testSuiteDescription) {
		this.testSuiteDescription = testSuiteDescription;
	}

	public int getTotalTests() {
		return totalTests;
	}

	public void setTotalTests(int totalTests) {
		this.totalTests = totalTests;
	}

	public int getTotalPassed() {
		totalPassed = totalTests - totalFailed; 
		return totalPassed;
	}

	public void setTotalPassed(int totalPassed) {
		this.totalPassed = totalPassed;
	}

	public int getTotalFailed() {
		return totalFailed;
	}

	public void setTotalFailed(int totalFailed) {
		this.totalFailed = totalFailed;
	}

	public List<FailedTestResult> getFailedTests() {
		return failedTests;
	}

	public void setFailedTests(List<FailedTestResult> failedTests) {
		this.failedTests = failedTests;
	}

}
