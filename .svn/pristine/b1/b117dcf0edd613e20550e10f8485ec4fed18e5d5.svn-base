
package com.cisco.cstg.autotools.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.cstg.autotools.dao.EquipmentDao;
import com.cisco.cstg.autotools.domain.factdb.Equipment;

@Controller
@RequestMapping({"/find_equipment.html"})
@SessionAttributes("equipment")
public class EquipmentGetController {
    private static final Logger logger = LoggerFactory
            .getLogger(EquipmentGetController.class);
    
    EquipmentDao equipmentDao;

    @Autowired
    public EquipmentGetController(EquipmentDao equipmentDao) {
    	this.equipmentDao = equipmentDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ModelAttribute("equipment")
    public ModelAndView setupForm() {
            logger.debug("INSIDE SET UP FORM");
            ModelAndView mav = new ModelAndView("EquipmentFind").addObject(new Equipment()); 
        return mav; 
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processSubmit(@ModelAttribute("equipment") Equipment equipment, BindingResult result, SessionStatus status) {
    	logger.debug("INSIDE PROCESS FORM");
    	if(equipment !=null){
    	logger.debug("Equipment Id: "+equipment.getEquipmentId());  
    	if(equipment.getAssemblyNumber()==null){
    		logger.debug("Assembly IS NULL ");
    	}
    	}else{
    		logger.debug("Equipment is null");
    	}
    	equipment = this.equipmentDao.getByIdInFactdb(equipment.getEquipmentId());
    	 ModelAndView mav = new ModelAndView("EquipmentFind").addObject(equipment);
    	 if(equipment!=null){
    	    	logger.debug("Equipment Id: "+equipment.getEquipmentId());  
    	    	if(equipment.getAssemblyNumber()!=null){
    	    		logger.debug("Assembly IS NOT NULL "+equipment.getAssemblyNumber());
    	    	}
    	    	}else{
    	    		logger.debug("Equipment is null");
    	    	}
    	 
         return mav; 
    }
}