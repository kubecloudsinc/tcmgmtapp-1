
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

import com.cisco.cstg.autotools.semantic.test.ScheduleMonitor;
import com.cisco.cstg.autotools.web.viewbean.ScheduleViewBean;

@Controller
@RequestMapping(value="/schedule.html")
public class ScheduleListController {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleListController.class);
    
    @Autowired
    private ScheduleMonitor scheduleMonitor;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm(@ModelAttribute("schedule") ScheduleViewBean scheduleViewBean) {
            logger.debug("INSIDE SET UP FORM");
            List<ScheduleViewBean> result = scheduleMonitor.getAllSchedules();
            logger.debug("Got {} entities", result.size());
            ModelAndView mav = new ModelAndView("Schedule");
            mav.addObject("scheduleList",result);
            return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(Model model, ScheduleViewBean scheduleViewBean, SessionStatus status) {
            try {
               logger.debug("INSIDE THE POST METHOD schedule html. Model Contains scheduleViewBean?:"+ model.containsAttribute("scheduleViewBean")
            		   +" model contains scheduleViewBeanList?:"+model.containsAttribute("scheduleViewBeanList"));	
               if(scheduleViewBean!=null){
            	   logger.debug("THE SELECTED SCHDULE IS: "+scheduleViewBean.toString());
               }
               scheduleMonitor.toggle(scheduleViewBean.getId(), scheduleViewBean.getUserName(), scheduleViewBean.getPassword());
 
            } catch (Exception e) {
                e.printStackTrace();
                final Writer result = new StringWriter();
    		    final PrintWriter printWriter = new PrintWriter(result);
    		    e.printStackTrace(printWriter);
    		    logger.debug(result.toString());
            }
        return "redirect:schedule.html";
    }    
}