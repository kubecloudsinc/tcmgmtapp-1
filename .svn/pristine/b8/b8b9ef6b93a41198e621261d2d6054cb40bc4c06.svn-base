
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

import com.cisco.cstg.autotools.domain.appdb.TestStatus;
import com.cisco.cstg.autotools.semantic.test.TestMonitor;

@Controller
@RequestMapping(value="/test_status.html")
public class TestStatusListController {
    private static final Logger logger = LoggerFactory.getLogger(TestStatusListController.class);
    
    @Autowired
    private TestMonitor testMonitor;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm(@ModelAttribute("testStatus") TestStatus testStatus) {
            logger.debug("INSIDE SET UP FORM");
            List<TestStatus> result = testMonitor.getAllTestStatus();
            logger.debug("Got {} entities", result.size());
            ModelAndView mav = new ModelAndView("TestStatus");
            mav.addObject(result);
            return mav;
    }
    
     
    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(Model model, TestStatus testStatus, SessionStatus status) {
            try {

               if(testStatus !=null){
            	   logger.debug("The values in Test Status- id: {} testId:{}",
            			   testStatus.getId(), testStatus.getTestId());
               }else{
            	   logger.debug("The TEST STATUS object is null");
               }
 
               testMonitor.runTest(testStatus.getTestId());
               
            } catch (Exception e) {
                e.printStackTrace();
                final Writer result = new StringWriter();
    		    final PrintWriter printWriter = new PrintWriter(result);
    		    e.printStackTrace(printWriter);
    		    logger.debug(result.toString());
            }
        return "redirect:test_status.html";
//            return "/reports";
    }    
    
}