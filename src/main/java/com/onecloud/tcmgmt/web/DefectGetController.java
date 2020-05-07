
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.*;
import com.onecloud.tcmgmt.domain.appdb.Defect;
import com.onecloud.tcmgmt.domain.appdb.TestRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/defect.html")
public class DefectGetController extends BaseFormController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private DefectDao defectDao;
    private ComponentDao componentDao;
    private DefectStatusDao defectStatusDao;
    private PriorityDao priorityDao;
    private ProductDao productDao;
    private SeverityDao severityDao;

    @Autowired
    public DefectGetController(DefectDao defectDao, ComponentDao componentDao,
                               DefectStatusDao defectStatusDao, PriorityDao priorityDao,
                               ProductDao productDao, SeverityDao severityDao) {
        this.defectDao = defectDao;
        this.componentDao = componentDao;
        this.defectStatusDao = defectStatusDao;
        this.priorityDao = priorityDao;
        this.productDao = productDao;
        this.severityDao = severityDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView handleRequestInternal(@RequestParam(value = "success", required = false) String success,
                              @RequestParam(value = "id", required = false) Long id,
                                HttpServletRequest request) throws Exception {
        logger.debug("Inside the defect get controller");

        Defect aDefect = this.defectDao.getById(id);

        return new ModelAndView("defectView")
                .addObject(aDefect)
                .addObject("statusMap", getDefectStatusMap(this.defectStatusDao))
                .addObject("severityMap", getSeverityMap(this.severityDao))
                .addObject("productMap", getProductMap(this.productDao))
                .addObject("priorityMap", getPriorityMap(this.priorityDao))
                .addObject("componentMap", getComponentMap(this.componentDao));
    }
}
