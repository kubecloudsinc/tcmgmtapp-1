package com.cisco.cstg.autotools.teststhreads;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.tests.SNTCPageLoadTimeTest;

public class LoginToBSOThread implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginToBSOThread.class);

	@Override
	public void run() {
	  
       JUnitCore junit = new JUnitCore();
       Request request = Request.method(SNTCPageLoadTimeTest.class, "testPageLoadTime");
       Result rs = junit.run(request);
       logger.debug("INSIDE the time taken:"+new Long(rs.getRunTime()).toString());      
	}

}
