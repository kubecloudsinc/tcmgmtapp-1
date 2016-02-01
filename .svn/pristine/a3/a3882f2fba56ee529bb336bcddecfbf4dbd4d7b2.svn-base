package com.cisco.cstg.autotools.teststhreads;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.tests.SNTCNextGenPageLoadTest;

public class SNTCPageLoadTimeTestThread implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(SNTCPageLoadTimeTestThread.class);

	@Override
	public void run() {
		try {
//			JUnitCore junitRunner = new JUnitCore();
//			junitRunner.addListener(new TestReportListener());
//			Request request = Request.method(SNTCNextGenPageLoadTest.class, "captureTimeTest");
//			Result rs = junit.run(request);
//			logger.debug("INSIDE the time taken:"+ new Long(rs.getRunTime()).toString());
			logger.debug("INSIDE THE RUN METHOD OF: {} ",this.getClass().getName());
			Result result = JUnitCore.runClasses(SNTCNextGenPageLoadTest.class);
			logger.debug("THIS MESSAGE IS AFTER THE JUNITCORE IS INVOKED");
			logger.debug("THE RESULTS- Failed: {},Total:{},TotalTime:{} ",result.getFailureCount(), result.getRunCount(),result.getRunTime());
			List<Failure> failures = result.getFailures();
			
			for (Failure failure : failures) {
				logger.debug("descr: {} Msg: {} Test Header: {} Trace:{}",
				failure.getDescription(),
				failure.getMessage(),
				failure.getTestHeader(),
				failure.getTrace());
			}
		} catch (Exception exp) {
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			exp.printStackTrace(printWriter);
			logger.debug(result.toString());
		}
	}

}
