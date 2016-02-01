package com.cisco.cstg.autotools.semantic.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.cstg.autotools.dao.TestDao;
import com.cisco.cstg.autotools.dao.TestStatusDao;
import com.cisco.cstg.autotools.dao.TestSuiteDao;
import com.cisco.cstg.autotools.dao.TestSuiteStatusDao;
import com.cisco.cstg.autotools.domain.appdb.TempUser;
import com.cisco.cstg.autotools.domain.appdb.Test;
import com.cisco.cstg.autotools.domain.appdb.TestStatus;
import com.cisco.cstg.autotools.domain.appdb.TestSuite;
import com.cisco.cstg.autotools.domain.appdb.TestSuiteStatus;
import com.cisco.cstg.autotools.domain.appdb.TestSuiteTest;
import com.cisco.cstg.autotools.semantic.diagnostics.ValidationException;
import com.cisco.cstg.autotools.support.email.PostOffice;
import com.cisco.cstg.autotools.support.email.SystemPostOffice;
import com.cisco.cstg.autotools.tests.TestConstants;
import com.cisco.cstg.autotools.teststhreads.JunitTestExecutor;

@Service
public class TestManager extends BaseManager implements TestMonitor {

	private static final Logger logger = LoggerFactory.getLogger(TestManager.class);

	@Autowired
	private TestStatusDao testStatusDao;
	
	@Autowired
	private TestSuiteStatusDao testSuiteStatusDao;

	@Autowired
	private TestSuiteDao testSuiteDao;
	
	@Autowired
	private TestDao testDao;
	
	
	@Override
	public void runTest(Long testId) {
		try {
			// update the test id status to running in the DB.
			TestStatus testStatus = testStatusDao.getByTestId(testId);
			testStatus.setTestStatus(TestStatus.RUNNING);
			testStatusDao.save(testStatus);

			logger.debug("The test Class Name: {}",testStatus.getTest().getTestClassName());
			logger.debug("The test Method Name: {}",testStatus.getTest().getTestMethodName());
			logger.debug("The test  Name: {}",testStatus.getTest().getTestName());
			
			// run the test
//			JunitTestExecutor executor = new JunitTestExecutor(TestLogin.class, "clickHomeView");
			JunitTestExecutor executor = new JunitTestExecutor(Class.forName(testStatus.getTest().getTestClassName()), 
					testStatus.getTest().getTestMethodName());
			executor.run();
			
			Result testResult = executor.getTestResult();

			testStatus.setTestResult(testResult);
			// generate a test report.
			String testReportURL = generateTestReport(testStatus.getTest(), testResult);
			
			// update the test with the URL link
			testStatus.setReportName(testReportURL);
			
			// update the status
			testStatus.setTestStatus("Passed");
			testStatusDao.save(testStatus);

		} catch(ClassNotFoundException cExp){
			// return error message that the class name is not correct
			// this shud not happen as this is configured in the database.
			logger.debug("ClassNotFound exception");
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			cExp.printStackTrace(printWriter);
			logger.debug(result.toString());
		} catch (Exception exp) {
			logger.debug("EXCEPTION IN GETTING THE DAO");
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			exp.printStackTrace(printWriter);
			logger.debug(result.toString());
		}
	}
	
	@Override
	public void runTestSuite(Long testSuiteId) {
		try {
			// update the test id status to running in the DB.
			TestSuiteStatus testSuiteStatus = testSuiteStatusDao.getByTestSuiteId(testSuiteId);
			testSuiteStatus.setTestSuiteStatus(TestSuiteStatus.RUNNING);
			testSuiteStatusDao.save(testSuiteStatus);

			// get the test suite
			// get all the tests in the test suite.
			// run the tests.
			TestSuite testSuite = testSuiteStatus.getTestSuite();
			Set<TestSuiteTest> testSuiteTests = testSuite.getTestSuiteTests();
			TestSuiteResult testSuiteResult = new TestSuiteResult();
			List<FailedTestResult> failedTests = new ArrayList<FailedTestResult>();
			
			for (TestSuiteTest testSuiteTest : testSuiteTests) {
				Test test = testSuiteTest.getTest();
				// run the test
				// update the test id status to running in the DB.
				test.getTestStatus().setTestStatus(TestStatus.RUNNING);
				testDao.save(test);

				JunitTestExecutor executor = new JunitTestExecutor(Class.forName
						(test.getTestClassName()),test.getTestMethodName());
				executor.run();
				Result testResult = executor.getTestResult();

				List<Failure> failures = testResult.getFailures();
				
				test.getTestStatus().setTestStatus("Passed");
				for (Failure failure : failures) {
					logger.debug("descr: {} Msg: {} Test Header: {} Trace:",
					failure.getDescription(),
					failure.getMessage(),
					failure.getTestHeader());
					// this test failed.
					FailedTestResult failedTest = new FailedTestResult();
					failedTest.setFailMessage(failure.getMessage());
					failedTest.setTestMethodName(test.getTestMethodName());
					failedTest.setTestName(test.getTestName());
					failedTests.add(failedTest);
						
					test.getTestStatus().setTestStatus("Failed");	
				}
				
				testDao.save(test);
				
			}

			testSuiteResult.setTestSuiteName(testSuite.getTestSuiteName());
			testSuiteResult.setTestSuiteDescription(testSuite.getTestSuiteDescription());
			testSuiteResult.setTotalTests(testSuiteTests.size());
			testSuiteResult.setFailedTests(failedTests);
			testSuiteResult.setTotalFailed(failedTests.size());

			// generate a test Suite report.
			String testReportURL = generateTestSuiteReport(testSuiteResult);
			
			// update the test with the URL link
			testSuiteStatus.setReportName(testReportURL);
			testSuiteStatus.setTestSuiteStatus(TestSuiteStatus.COMPLETED);
			testSuiteStatusDao.save(testSuiteStatus);

		}catch (ClassNotFoundException exp){
			
		}catch (Exception exp) {
			logger.debug("EXCEPTION IN");
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			exp.printStackTrace(printWriter);
			logger.debug(result.toString());
		}
	}

	@Override
	public List<TestStatus> getAllTestStatus() {
		return testStatusDao.getAll();
	}
	
	@Override
	public List<TestSuiteStatus> getAllTestSuiteStatus() {
		return testSuiteStatusDao.getAll();
	}

	@Override
	public String generateTestSuiteReport(TestSuiteResult testSuiteResult) {
		
		File reportFile=null;
		String testReportURL  = ApplicationConstants.getUniqueReportURL();

		//		 create a file.
		try {
			reportFile = new File(ApplicationConstants.getReportsDirectory().toString(),
									   ApplicationConstants.getReportNameFromURL(testReportURL)).getAbsoluteFile();
			logger.debug("CReated the file object");
			// create the html string for the report.
			StringBuilder htmString = createHTMStringForTestSuite(testSuiteResult);
			
			FileUtils.writeStringToFile(reportFile, 
					htmString.toString(), ApplicationConstants.APPLICATION_DEFAULT_ENCODING);
			logger.debug("File written");
		} catch (IOException e) {
			final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    e.printStackTrace(printWriter);
		    logger.info(result.toString());
		}
		return testReportURL;
	}
	
	private String generateTestReport(Test test, Result testResult){
		
		File reportFile=null;
		String testReportURL  = ApplicationConstants.getUniqueReportURL();

		//		 create a file.
		try {
			reportFile = new File(ApplicationConstants.getReportsDirectory().toString(),
									   ApplicationConstants.getReportNameFromURL(testReportURL)).getAbsoluteFile();
			logger.debug("CReated the file object");
			// create the html string for the report.
			StringBuilder htmString = createHTMString(test,testResult);
			
			FileUtils.writeStringToFile(reportFile, 
					htmString.toString(), ApplicationConstants.APPLICATION_DEFAULT_ENCODING);
//			List<String> lines = new ArrayList<String>();
//			lines.add("This is a new file created");
//			lines.add("This is a new file created line two");
//			FileUtils.writeLines(reportFile, lines, false);
			logger.debug("File written");
		} catch (IOException e) {
			final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    e.printStackTrace(printWriter);
		    logger.info(result.toString());
		}
		return testReportURL;
	}
	
	private StringBuilder createHTMString(Test test, Result testResult){
		StringBuilder htmString= new StringBuilder();
		
	  htmString.append("<html><body><b><font face=\"Cambria\" >Test Suite Run Report.</font></b><br />	<br /><br />");
	  htmString.append("<table border=\"1\"><tbody><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Test Suite Name</font></th>");
	  
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(test.getTestName());
	  htmString.append("</font></td>");
	  htmString.append("<tr><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Test Suite Description</font></th>");
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(test.getTestDescription());
	  htmString.append("</font></td>");
	  htmString.append("<tr><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Total Number of Tests in Test Suite</font></th>");
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(testResult.getRunCount());
	  htmString.append("</font></td>");
	  htmString.append("<tr><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Total Tests Passed</font></th>");
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(testResult.getRunCount()-testResult.getFailureCount());
	  htmString.append("</font></td>");
	  htmString.append("<tr><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Total Tests Failed</font></th>");
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(testResult.getFailureCount());
	  htmString.append("</font></td>");
	  htmString.append("<tr></tbody></table><br /><br /><font face=\"Cambria\" >Details of failed tests in the test suite.</font><br />");
	  htmString.append("<table border=\"1\"><tbody><tr>");
	  htmString.append("<th align=\"center\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >TEST DETAILS OF TEST CASES FAILED</font></th>");
	  htmString.append("<tr><tr><td align=\"center\" bgcolor=\"#DDA0DD\"><font face=\"Cambria\" >S.NO.</font></td>");
	  htmString.append("<td align=\"center\" bgcolor=\"#DDA0DD\"><font face=\"Cambria\" >Test Name</font></td>");
	  htmString.append("<th align=\"center\" bgcolor=\"#DDA0DD\"><font face=\"Cambria\" >Test Step</font></th>");
	  htmString.append("<td align=\"center\" bgcolor=\"#DDA0DD\"><font face=\"Cambria\" >Failure Message</font></td>");
	  
	// get the results from the thread.
	  logger.debug("Time taken to run Junit Method: "+new Long(testResult.getRunTime()).toString());
	  List<Failure> failures = testResult.getFailures();
	  int i=1;
	  for (Failure failure : failures) {
		logger.debug("descr: {} Msg: {} Test Header: {} Trace:{}",
		failure.getDescription(),
		failure.getMessage(),
		failure.getTestHeader(),
		failure.getTrace());

		htmString.append("</tr><tr><td align=\"center\"><font face=\"Cambria\" >");
		htmString.append(i);
		i++;
		htmString.append("</font></td>");
		htmString.append("<td align=\"center\"><font face=\"Cambria\" >");
		htmString.append(test.getTestClassName());
		htmString.append("</font></td>");
		htmString.append("<th align=\"center\"><font face=\"Cambria\" >");
		htmString.append(test.getTestName());
		htmString.append("</font></th>");
		htmString.append("<td align=\"center\"><font face=\"Cambria\" >");
		htmString.append(failure.getMessage());
		htmString.append("</font></td>");
		htmString.append("</tr>");
		
//		Throwable exp = failure.getException();
//		final Writer result = new StringWriter();
//	    final PrintWriter printWriter = new PrintWriter(result);
//	    exp.printStackTrace(printWriter);
//	    logger.debug(result.toString());
			
	  }
	  htmString.append("<!-- comment line here --></tbody></table><br /><br /></body></html>");
		  
		return htmString;
	}
	
	private StringBuilder createHTMStringForTestSuite(TestSuiteResult testSuiteResult){

		StringBuilder htmString= new StringBuilder();
		
	  htmString.append("<html><body><b><font face=\"Cambria\" >Test Suite Run Report.</font></b><br />	<br /><br />");
	  htmString.append("<table border=\"1\"><tbody><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Test Suite Name</font></th>");
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(testSuiteResult.getTestSuiteName());
	  htmString.append("</font></td>");
	  htmString.append("<tr><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Test Suite Description</font></th>");
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(testSuiteResult.getTestSuiteDescription());
	  htmString.append("</font></td>");
	  htmString.append("<tr><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Total Number of Tests in Test Suite</font></th>");
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(testSuiteResult.getTotalTests());
	  htmString.append("</font></td>");
	  htmString.append("<tr><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Total Tests Passed</font></th>");
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(testSuiteResult.getTotalPassed());
	  htmString.append("</font></td>");
	  htmString.append("<tr><tr><th align=\"left\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >Total Tests Failed</font></th>");
	  htmString.append("<td align=\"left\"><font face=\"Cambria\" >");
	  htmString.append(testSuiteResult.getTotalFailed());
	  htmString.append("</font></td>");
	  htmString.append("<tr></tbody></table><br /><br /><font face=\"Cambria\" >Details of failed tests in the test suite.</font><br />");
	  htmString.append("<table border=\"1\"><tbody><tr>");
	  htmString.append("<th align=\"center\" colspan=\"17\" bgcolor=\"#E6E6FA\"><font face=\"Cambria\" >TEST DETAILS OF TEST CASES FAILED</font></th>");
	  htmString.append("<tr><tr><td align=\"center\" bgcolor=\"#DDA0DD\"><font face=\"Cambria\" >S.NO.</font></td>");
	  htmString.append("<td align=\"center\" bgcolor=\"#DDA0DD\"><font face=\"Cambria\" >Test Name</font></td>");
	  htmString.append("<th align=\"center\" bgcolor=\"#DDA0DD\"><font face=\"Cambria\" >Test Step</font></th>");
	  htmString.append("<td align=\"center\" bgcolor=\"#DDA0DD\"><font face=\"Cambria\" >Failure Message</font></td>");
	  
	// get the results from the thread.
	  List<FailedTestResult> failedTests = testSuiteResult.getFailedTests();
	  int i=1;
	  for (FailedTestResult failedTest : failedTests) {

		htmString.append("</tr><tr><td align=\"center\"><font face=\"Cambria\" >");
		htmString.append(i);
		i++;
		htmString.append("</font></td>");
		htmString.append("<td align=\"center\"><font face=\"Cambria\" >");
		htmString.append(failedTest.getTestMethodName());
		htmString.append("</font></td>");
		htmString.append("<th align=\"center\"><font face=\"Cambria\" >");
		htmString.append(failedTest.getTestName());
		htmString.append("</font></th>");
		htmString.append("<td align=\"center\"><font face=\"Cambria\" >");
		htmString.append(failedTest.getFailMessage());
		htmString.append("</font></td>");
		htmString.append("</tr>");
		
//		Throwable exp = failure.getException();
//		final Writer result = new StringWriter();
//	    final PrintWriter printWriter = new PrintWriter(result);
//	    exp.printStackTrace(printWriter);
//	    logger.debug(result.toString());
			
	  }
	  htmString.append("<!-- comment line here --></tbody></table><br /><br /></body></html>");
		  
		return htmString;
	
	}

	@Override
	public void emailTestSuiteReport(Long testSuiteId) {
		logger.debug("EMAIl MSG SENT");
		TestSuiteStatus testSuiteStatus = testSuiteStatusDao.getByTestSuiteId(testSuiteId);
		
		String reportFileName = Paths.get(testSuiteStatus.getReportName()).getFileName().toString();
		
		// read the generated report.
		File reportFile = new File(ApplicationConstants.getReportsDirectory().toString(),
								reportFileName).getAbsoluteFile();
		if(reportFile.exists()){
			try {
				List<String> htmLines = FileUtils.readLines(reportFile);
				logger.debug("TOTAL Lines in the file {}",htmLines.size());

				// create a smtp file.
//				File smtpFile = new File(Thread.currentThread().getContextClassLoader().getResource(reportFileName+".smtp").getFile());
				File someFileOnClassPath = 
						new File(Thread.currentThread().getContextClassLoader().getResource(TempUser.class.getName()+".cfg").getFile());

				// get the Directory name of the file.
				String directoryNameOfsomeFileOnClassPath = ApplicationConstants.convertToJavaFileName(someFileOnClassPath.getParent());
				File smtpFile =  new File(directoryNameOfsomeFileOnClassPath, reportFileName+".smtp");
				if(!smtpFile.exists()){
					smtpFile.createNewFile();
				}
				List<String> lines = new ArrayList<String>();
				lines.add("[subject]");
				lines.add("Test Suite Run Report: "+reportFileName);
				lines.add("[body]");
				lines.add(htmLines.get(0));
				
				FileUtils.writeLines(smtpFile, lines);

				logger.debug("SMTP file written: {}",smtpFile.getPath());
				
				PostOffice po = new SystemPostOffice();
				Map<String,String> insertions = new TreeMap<String,String>();
				String [] toAddresses = TestConstants.TEST_REPORT_EMAIL_DISTRIBUTION_LIST.split(",");
				String [] ccAddresses = null;
				
				try {
					po.sendEmail(reportFileName, insertions, toAddresses, ccAddresses);
					logger.info("EMAIL SENT SUCCESSFULLY");
				} catch (ValidationException e) {
					e.printStackTrace();
				}	
						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			// no report generated
			logger.debug("NO REPORT GENERATED TO SEND EMAIL");
		}
		
	}
}