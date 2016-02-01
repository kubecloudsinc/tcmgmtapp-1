
package com.cisco.cstg.autotools.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.cstg.autotools.domain.appdb.TestSuiteStatus;
import com.cisco.cstg.autotools.semantic.test.TestMonitor;

@Controller
@RequestMapping(value="/test_suite_report.html")
public class TestSuiteReportListController {
    private static final Logger logger = LoggerFactory.getLogger(TestSuiteReportListController.class);
    
    @Autowired
    private TestMonitor testMonitor;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm(@ModelAttribute("testSuiteStatus") TestSuiteStatus testSuiteStatus) {
            logger.debug("INSIDE SET UP FORM");
            List<TestSuiteStatus> result = testMonitor.getAllTestSuiteStatus();
            logger.debug("Got {} entities", result.size());
            ModelAndView mav = new ModelAndView("TestSuiteReport");
            mav.addObject(result);
            return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processSubmit(Model model,TestSuiteStatus testSuiteStatus, SessionStatus sessionStatus) {
            try {

               logger.debug("Is the test suite object null? {}", (null == testSuiteStatus));
               if(testSuiteStatus !=null){
            	   if(testSuiteStatus.getTestSuiteId()!=null)
            		   logger.debug("Value of Suite id: {}",testSuiteStatus.getTestSuiteId());
            	   else
            		   logger.debug("Value of Suite id is null ");
            	   
            	   if(testSuiteStatus.getUpdated()!=null)
            		   logger.debug("Value of Suite id: {}",testSuiteStatus.getUpdated());
            	   else
            		   logger.debug("Value of Suite updated is null ");
               }
               
//               testMonitor.generateTestSuiteResport(testSuiteStatus.getTestSuiteId());
               
               sessionStatus.setComplete();
               
            } catch (Exception e) {
                e.printStackTrace();
                final Writer result = new StringWriter();
    		    final PrintWriter printWriter = new PrintWriter(result);
    		    e.printStackTrace(printWriter);
    		    logger.debug(result.toString());
            }
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("redirect:test_suite_report.html");
        
        return modelView;
    }    
}