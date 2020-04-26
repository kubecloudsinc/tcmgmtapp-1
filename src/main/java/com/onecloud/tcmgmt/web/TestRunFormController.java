
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestCaseDao;
import com.onecloud.tcmgmt.dao.TestRunDao;
import com.onecloud.tcmgmt.domain.appdb.TestCase;
import com.onecloud.tcmgmt.domain.appdb.TestRun;
import com.onecloud.tcmgmt.domain.appdb.TestStep;
import com.onecloud.tcmgmt.semantic.dto.SimpleTestCaseDTO;
import com.onecloud.tcmgmt.semantic.dto.TestCaseDTO;
import com.onecloud.tcmgmt.semantic.dto.TestRunDTO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes("testRunDTO")
public class TestRunFormController {

    private static final Logger logger = LoggerFactory
            .getLogger(TestRunFormController.class);

    private final TestRunDao dao;

    @Autowired
    public TestRunFormController(TestRunDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/testrun_form.html",method = RequestMethod.GET)
    public ModelAndView setupForm(@RequestParam(value = "id", required = false) Long id,
                @RequestParam(value = "editcase", required = false) Boolean editCase,HttpServletRequest request) {
        TestRunDTO testRunDTO;
        if (id == null || id.longValue()==0 ) {
            logger.debug("ID is null so must be coming from the Add new Test or step");
            if(editCase){
                testRunDTO = (TestRunDTO) request.getSession().getAttribute("testRunDTO");
                logger.debug("Edit step is true : "+ testRunDTO.getId());
            }else {
                testRunDTO = new TestRunDTO();
            }
        }else{
            logger.debug("ID is not null so must be coming from Edit: "+id);
            testRunDTO = this.dao.getById(id).createDTO();
        }

        return new ModelAndView("testrunForm").addObject(testRunDTO);
    }

    @RequestMapping(value = "/testcase_remove.html", method = RequestMethod.GET)
    public String removeTestCases(@RequestParam(value = "id", required = false) Long id,
                         HttpServletRequest request) {
        logger.debug("INSIDE THE REMOVE CONTROLLER");
        TestRunDTO testRunDTO = (TestRunDTO) request.getSession().getAttribute("testRunDTO");
//        testRunDTO.removeSelectedTestCases();
        if(testRunDTO!=null && testRunDTO.getTestCaseDTOs()!=null &&
                testRunDTO.getTestCaseDTOs().size()!=0) {
//            List<TestCase> testCases = modelTest.getTestCases();
            List<SimpleTestCaseDTO> sessionTestCases = testRunDTO.getTestCaseDTOs();
//            logger.debug("ModelTest testcases: "+testCases.size());
            logger.debug("SessionTest testcases: "+sessionTestCases.size());

            for (int i = 0; i < sessionTestCases.size() ; i++) {
//                logger.debug("The model check is:"+testCases.get(i).getChecked());
                logger.debug("The session check is:"+sessionTestCases.get(i).getChecked());
//                logger.debug("The model check is:"+testCases.get(i).getId());
                logger.debug("The session check is:"+sessionTestCases.get(i).getId());

//                logger.debug("The DTO values:"+modelDTOs.get(i).getId());
//                logger.debug("The DTO values:"+modelDTOs.get(i).getChecked());
            }
        }else{
            logger.debug("Either model or tests are null");
        }

        return "redirect:/testrun_form.html?editcase=true";
    }

    @RequestMapping(value = "/testrun_form.html", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("testRun") @Valid TestRun validatedTest,
                                BindingResult result, SessionStatus status, HttpServletRequest request) {
        if (!result.hasErrors()) {
            try {
                logger.debug("INSIDE THE FORM POST");
                logger.debug("The id in testCaseDTO: " + validatedTest.getId());

                TestRun testRun = (TestRun) request.getSession().getAttribute("testRun");

                if (testRun== null || testRun.getId()==null){
                     // brand new test case
                     logger.debug("The test case DTO is null or id is null");
                     testRun.setCreated(new Date());

                }else{
                      // update
                    logger.debug("The test case DTO is NOT null and  id is NOT null");
                     testRun = this.dao.getById(testRun.getId());
                     testRun.createUpdateFromAnother(validatedTest);
                }
                logger.debug("ABOUT TO create or update");
                this.dao.save(testRun);
                status.setComplete();
                return "redirect:testruns.html?id=" + testRun.getId() + "&success=true";
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                logger.debug("The stack"+ e.getMessage());
                result.reject("DataIntegrityViolationException");
            } catch (ConcurrencyFailureException e) {
                result.reject("ConcurrentModificatonFailure");
            }
        }
        return "testrunForm";
    }
}
