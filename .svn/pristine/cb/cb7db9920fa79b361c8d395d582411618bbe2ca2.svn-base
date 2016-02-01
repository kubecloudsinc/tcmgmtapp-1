package com.cisco.cstg.autotools.semantic.test;

import java.io.File;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cstg.autotools.tests.OpenBSOPortal;


public class CopyOfTestManager implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(CopyOfTestManager.class);
	
	public void run() {
		
		   logger.debug("INSIDE THE test manager: "+new File("").getAbsolutePath());
          JUnitCore junit = new JUnitCore();
          Request request = Request.method(OpenBSOPortal.class, "testOpenBSOPortal");
//          Request request = Request.method(TestEFUI3.class, "testEFUI3");
          Result rs = junit.run(request);
          logger.debug("INSIDE the time taken:"+new Long(rs.getRunTime()).toString());      

	}

}