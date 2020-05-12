
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.*;
import com.onecloud.tcmgmt.domain.appdb.Defect;
import com.onecloud.tcmgmt.domain.appdb.TestCase;
import com.onecloud.tcmgmt.domain.appdb.TestRun;
import com.onecloud.tcmgmt.domain.appdb.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(value = "/defect_form.html")
@SessionAttributes({"defect", "statusMap",
        "severityMap","productMap",
        "priorityMap","componentMap"})
public class DefectFormController extends BaseFormController {

    private static final Logger logger =
            LoggerFactory.getLogger(DefectFormController.class);

    private DefectDao defectDao;
    private ComponentDao componentDao;
    private DefectStatusDao defectStatusDao;
    private PriorityDao priorityDao;
    private ProductDao productDao;
    private SeverityDao severityDao;

    private String authUserName = "authUser";

    @Autowired
    public DefectFormController(DefectDao defectDao, ComponentDao componentDao,
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
    public ModelAndView setupForm(@RequestParam(value = "id", required = false) Long id,
                                  HttpServletRequest request) {
        Defect defect;
        if (id == null || id.longValue() == 0) {
            logger.debug("ID is null so must be coming from the Add new ");
            logger.debug("Creating a new defect");
                defect = new Defect();
            User currentUser = (User) request.getAttribute(authUserName);
            logger.debug("about to set user id: "+currentUser.getId());
            defect.setReportedBy(currentUser);

        } else{
            logger.debug("ID is not null so must be coming from Edit: " + id);
            defect = this.defectDao.getById(id);
        }

        return new ModelAndView("defectForm")
                .addObject(defect)
                .addObject("statusMap", getDefectStatusMap(this.defectStatusDao))
                .addObject("severityMap", getSeverityMap(this.severityDao))
                .addObject("productMap", getProductMap(this.productDao))
                .addObject("priorityMap", getPriorityMap(this.priorityDao))
                .addObject("componentMap", getComponentMap(this.componentDao));
    }

    @RequestMapping(params = "save", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("defect") @Valid Defect validatedDefect,
                                BindingResult result, SessionStatus status,
                                HttpServletRequest request) {
        logger.debug("INSIDE THE FORM POST");
        if (!result.hasErrors()) {
            try {

                logger.debug("The id is: " + validatedDefect.getId());

                Defect defect = (Defect) request.getSession().getAttribute("defect");

                if (defect != null && defect.getId() != null) {
                    // update
                    logger.debug("The test case DTO is NOT null and  id is NOT null");
                    defect = this.defectDao.getById(defect.getId());
                    defect.createUpdateFromAnother(validatedDefect);
                }
                logger.debug("ABOUT TO create or update");

                this.defectDao.save(defect);
                status.setComplete();
                return "redirect:defect.html?id=" + defect.getId() + "&success=true";

            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                logger.debug("The stack" + e.getMessage());
                result.reject("DataIntegrityViolationException");
            } catch (ConcurrencyFailureException e) {
                result.reject("ConcurrentModificatonFailure");
            }
        }
        return "defectForm";
    }
}