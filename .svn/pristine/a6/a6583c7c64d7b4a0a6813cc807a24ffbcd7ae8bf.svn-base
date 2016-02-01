package com.cisco.cstg.autotools.teststhreads;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.runner.JUnitCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.tests.PSSPageLoadTimeTest;

public class PSSPageLoadTimeTestThread implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(PSSPageLoadTimeTestThread.class);

	@Override
	public void run() {
		try {
			logger.debug("INSIDE THE RUN METHOD OF: {} ",this.getClass().getName());
//			JUnitCore junit = new JUnitCore();
//			Request request = Request.method(PSSPageLoadTimeTest.class, "testPageLoadTime");
//			Result rs = junit.run(request);
//			JUnitCore.runClasses(PSSPageLoadTimeTestAtea.class);
//			JUnitCore.runClasses(PSSPageLoadTimeTestConscia.class);
//			JUnitCore.runClasses(PSSPageLoadTimeTestDimension.class);
			JUnitCore.runClasses(PSSPageLoadTimeTest.class);
			logger.debug("THIS MESSAGE IS AFTER THE JUNITCORE IS INVOKED");
//			logger.debug("INSIDE the time taken:"+ new Long(rs.getRunTime()).toString());
		} catch (Exception exp) {
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			exp.printStackTrace(printWriter);
			logger.debug(result.toString());
		}
	}

}
