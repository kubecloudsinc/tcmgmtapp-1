package com.cisco.cstg.autotools.semantic.test;

import java.util.List;

import com.cisco.cstg.autotools.domain.appdb.TestStatus;
import com.cisco.cstg.autotools.domain.appdb.TestSuiteStatus;

public interface TestMonitor extends BaseMonitor {
	
	public void runTest(Long testId);
	
	public void runTestSuite(Long testSuiteId);
	
	public List<TestStatus> getAllTestStatus();
	
	public List<TestSuiteStatus> getAllTestSuiteStatus();
	
	/**
	 * sends a test report of the last run for the test suite to the configured email address.
	 * 
	 * @param testSuiteId
	 */
	public String generateTestSuiteReport(TestSuiteResult testSuiteResult);
	
	public void emailTestSuiteReport(Long testSuiteId);
}
