package com.cisco.cstg.autotools.teststhreads;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.cisco.cstg.autotools.dao.TestStatusDao;
import com.cisco.cstg.autotools.domain.appdb.TestStatus;
import com.cisco.cstg.autotools.semantic.test.Failure;
import com.cisco.cstg.autotools.semantic.test.TestMonitor;
import com.cisco.cstg.autotools.semantic.test.TestResult;

@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = true)
public class TestExecutor implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(TestExecutor.class);
	
	@Autowired
	private TestStatusDao testStatusDao;
	
	@Autowired
	private TestMonitor testManager;
	
	private Long testId;
	
	public TestExecutor() {
		super();
	}

	public TestExecutor(Long testId) {
		super();
		this.testId = testId;
	}

	@Override
	public void run() {
		try {
			if(testStatusDao==null){
				logger.debug("The test doa is null");
			}
			TestStatus testStatus = testStatusDao.getByTestId(testId);
			if(! testStatus.getTestStatus().equals(TestStatus.RUNNING)){
				testStatus.setTestStatus(TestStatus.RUNNING);
				testStatusDao.save(testStatus);
				logger.debug("UPDATED AND SAVED TO RUNNING STATUS INSIDE TEST EXECUTOR");		
			}
			logger.debug("INSIDE THE RUN METHOD OF: {} ",this.getClass().getName());
			long start = System.nanoTime();	
			Thread.sleep(10000);
			logger.debug("COMPLETED THE WAIT ");
			logger.debug("The test id is:"+testId);
			TestResult testResult = new TestResult();
			testResult.setRunCount(1);
			int intResult=(Math.random()<0.5)?0:1;
			if(intResult==0){
				testResult.setFailureCount(1);
				Failure failure = new Failure();
				failure.setDescription("This test case failed");
				failure.setMessage("Bummer! the test failed");
				List<Failure> failures = new ArrayList<Failure>();
				failures.add(failure);
				testResult.setFailures(failures);
				testStatus.setTestStatus(TestStatus.FAILED);
				
			}else{
				testResult.setFailureCount(0);
				testStatus.setTestStatus(TestStatus.PASSED);
				List<Failure> failures = new ArrayList<Failure>();
				testResult.setFailures(failures);
			}
			
			long stop = System.nanoTime();
			long timeTaken = TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS);
			testResult.setRunTime(timeTaken);
			testStatus.setTestResult(testResult);
			
			// generate a test report.
			String testReportURL = testManager.generateTestReport(testStatus.getTest(), testResult);
			
			// update the test with the URL link
			testStatus.setReportName(testReportURL);
			
			// update the status
			logger.debug("ABOUT TO SAVE THE STATUS");
			testStatusDao.save(testStatus);
			logger.debug("UPDATED AND SAVED THE STATUS");
		} catch (Exception exp) {
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			exp.printStackTrace(printWriter);
			logger.debug(result.toString());
		}
	}

}
