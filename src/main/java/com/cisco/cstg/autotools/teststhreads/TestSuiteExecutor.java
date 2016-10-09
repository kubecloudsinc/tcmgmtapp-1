package com.cisco.cstg.autotools.teststhreads;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.cisco.cstg.autotools.dao.TestStatusDao;
import com.cisco.cstg.autotools.dao.TestSuiteStatusDao;
import com.cisco.cstg.autotools.domain.appdb.Test;
import com.cisco.cstg.autotools.domain.appdb.TestStatus;
import com.cisco.cstg.autotools.domain.appdb.TestSuite;
import com.cisco.cstg.autotools.domain.appdb.TestSuiteStatus;
import com.cisco.cstg.autotools.domain.appdb.TestSuiteTest;
import com.cisco.cstg.autotools.semantic.test.FailedTestResult;
import com.cisco.cstg.autotools.semantic.test.TestMonitor;
import com.cisco.cstg.autotools.semantic.test.TestSuiteResult;

@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = true)
public class TestSuiteExecutor implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(TestSuiteExecutor.class);
	
	@Autowired
	private TestSuiteStatusDao testSuiteStatusDao;
	
	@Autowired
	private TestStatusDao testStatusDao;
	
	@Autowired
	private TestMonitor testMonitor;
	
	private Long testSuiteId;
	
	public TestSuiteExecutor() {
		super();
	}

	public TestSuiteExecutor(Long testSuiteId) {
		super();
		this.testSuiteId = testSuiteId;
	}

	@Override
	public void run() {
		try {
			logger.debug("INSIDE THE RUN METHOD OF: {} ",this.getClass().getName());
			logger.debug("The test id is:"+testSuiteId);
			if(testSuiteStatusDao==null){
				logger.debug("The test doa is null");
			}
			TestSuiteStatus testSuiteStatus = testSuiteStatusDao.getByTestSuiteId(testSuiteId);
			TestSuite testSuite = testSuiteStatus.getTestSuite();
			Set<TestSuiteTest> tests = testSuite.getTestSuiteTests();
			List<Long> testIds = new ArrayList<Long>();
			for (TestSuiteTest testSuiteTest : tests) {
				Test aTest = testSuiteTest.getTest();
				testIds.add(aTest.getId());
				testMonitor.scheduleATest(new TestExecutor(aTest.getId()));
			}
			logger.debug("Total number of tests in test suite: "+testIds.size());
			Thread.sleep(10000);
			
			TestSuiteResult result = new TestSuiteResult();
			result.setTestSuiteName(testSuite.getTestSuiteName());
			result.setTestSuiteDescription(testSuite.getTestSuiteDescription());
			result.setTotalTests(testIds.size());
			result.setTotalFailed(0);
			result.setTotalPassed(0);
			List<FailedTestResult> failedTests= new ArrayList<FailedTestResult>();
			FailedTestResult failedResult;
			// get the updates now
			long start = System.nanoTime();
			for (Long testId : testIds) {
				TestStatus testStatus = testStatusDao.getByTestId(testId);
				long stop = System.nanoTime();
				long timeTaken = TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS);
				while(timeTaken<120 && testStatus.getTestStatus().equals(TestStatus.RUNNING)){
					Thread.sleep(5000);
				}
				if(testStatus.getTestStatus().equals(TestStatus.RUNNING)){
					result.setTotalFailed(result.getTotalFailed()+1);
					failedResult = new FailedTestResult();
					failedResult.setTestName(testStatus.getTest().getTestName());
					failedResult.setFailMessage("The Test is taking too long to run");
					failedTests.add(failedResult);
				}else if(testStatus.getTestStatus().equals(TestStatus.FAILED)){
					result.setTotalFailed(result.getTotalFailed()+1);
					failedResult = new FailedTestResult();
					failedResult.setTestName(testStatus.getTest().getTestName());
					failedResult.setFailMessage("hmm. this test failed");
					failedTests.add(failedResult);
				}else if(testStatus.getTestStatus().equals(TestStatus.PASSED)){
					result.setTotalPassed(result.getTotalPassed()+1);
				}
			}
			result.setFailedTests(failedTests);
			
			// generate a test report.
			String testReportURL = testMonitor.generateTestSuiteReport(result);
			
			// update the test with the URL link
			testSuiteStatus.setReportName(testReportURL);
			
			// update the status
			logger.debug("ABOUT TO SAVE THE STATUS");
			testSuiteStatus.setTestSuiteStatus(TestSuiteStatus.COMPLETED);
			testSuiteStatusDao.save(testSuiteStatus);
			logger.debug("UPDATED AND SAVED THE STATUS");
		} catch (Exception exp) {
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			exp.printStackTrace(printWriter);
			logger.debug(result.toString());
		}
	}
}