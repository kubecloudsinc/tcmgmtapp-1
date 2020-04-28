
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestCaseDao;
import com.onecloud.tcmgmt.dao.TestRunDao;
import com.onecloud.tcmgmt.domain.appdb.TestCase;
import com.onecloud.tcmgmt.domain.appdb.TestRun;
import com.onecloud.tcmgmt.semantic.dto.TestRunDTO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/testrun_form.html")
@SessionAttributes("testRun")
public class TestRunFormController {

    private static final Logger logger =
            LoggerFactory.getLogger(TestRunFormController.class);

    private final TestCaseDao testCaseDao;
    private final TestRunDao testRunDao;

    @Autowired
    public TestRunFormController(TestCaseDao testCaseDao, TestRunDao testRunDao)
    {
        this.testCaseDao = testCaseDao;
        this.testRunDao = testRunDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm(@RequestParam(value = "id", required = false) Long id,
                @RequestParam(value = "editcase", required = false) Boolean editCase,
                                  HttpServletRequest request) {
        TestRun testRun;
        if (id == null || id.longValue()==0 ) {
            logger.debug("ID is null so must be coming from the Add new Test or step");
            if(editCase!= null && editCase){
                testRun = (TestRun) request.getSession().getAttribute("testRun");
                logger.debug("Edit step is true : "+ testRun.getId());
            }else{
                logger.debug("Creating a new test");
                testRun = new TestRun();
                testRun.setCreated(new Date());
            }
        }else{
            logger.debug("ID is not null so must be coming from Edit: "+id);
            testRun = this.testRunDao.getById(id);
        }

        return new ModelAndView("testrunForm").addObject(testRun);
    }

    @RequestMapping(params = "add", method = RequestMethod.POST)
    public ModelAndView addTestCases(@RequestParam(value = "id", required = false) Long id,
                                  @ModelAttribute TestRun testRun, BindingResult result,
                                  HttpServletRequest request) {

            testRun = (TestRun) request.getSession().getAttribute("testRun");
            logger.debug("INSIDE THE TEST CASE ADD : "+ testRun.getId());

            List<TestCase> allTestCases = this.testCaseDao.getAll();
            logger.debug("All testcases count : "+ allTestCases.size());
            logger.debug("testcases in run : "+ testRun.getTestCases().size());
            List<TestCase> testCasesToAdd =
                    (List<TestCase>) CollectionUtils.subtract(allTestCases,testRun.getTestCases());
            logger.debug("testcases that can be added : "+ testCasesToAdd.size());

            TestRunDTO testRunDTO = testRun.createDTO();
            testRunDTO.getTestCases().addAll(testCasesToAdd);

            return new ModelAndView("testcaseaddForm").addObject(testRunDTO);
    }

    @RequestMapping(params = "remove", method = RequestMethod.POST)
    public String removeTestCases(@RequestParam(value = "id", required = false) Long id,
                              @ModelAttribute TestRun testRun, BindingResult result,
                                  HttpServletRequest request) {
        if (!result.hasErrors()) {
            logger.debug("INSIDE THE REMOVE CONTROLLER");
            testRun = (TestRun) request.getSession().getAttribute("testRun");

            if (testRun != null && testRun.getTestCases() != null &&
                    testRun.getTestCases().size() != 0) {
                Set<TestCase> testCases = testRun.getTestCases();
                logger.debug("SessionTest testcases: " + testCases.size());

            } else {
                logger.debug("Either model or tests are null");
            }
            logger.debug("The value of selection after: " + testRun.getSelection());
            testRun.removeSelectedTestCases();
            logger.debug("The value of description: " + testRun.getDescription());
            return "redirect:testrun_form.html?editcase=true";
        }else{
            result.reject("NotNull.TestRunForm","The form is empty and no test cases to remove");
            for (Object object : result.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    logger.debug("The Field error code"+fieldError.getCode());
                }

                if(object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;
                    logger.debug("The object error code"+objectError.getCode());
                }
            }
        }
        return "testrunForm";
    }

    @RequestMapping(params = "save", method = RequestMethod.POST)
    public ModelAndView processSubmit(@ModelAttribute("testRunDTO") TestRunDTO testRunDTO,
                                                HttpServletRequest request) {

        logger.debug("INSIDE THE FORM POST");
        TestRun testRun = (TestRun) request.getSession().getAttribute("testRun");

        logger.debug("The id in testCaseDTO: " + testRunDTO.getId());
        for (TestCase aTest: testRunDTO.getTestCases()){
            if (aTest.getChecked()){
                testRun.getTestCases().add(aTest);
            }
        }
//        this.testRunDao.save(testRun);

//        redirectAttributes.addAttribute("addcase", true);
//        redirectAttributes.addAttribute("id", testRun.getId());

        return new ModelAndView("testrunForm").addObject(testRun);
    }

    @RequestMapping(params = "update", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("testRun") @Valid TestRun validatedTest,
                                BindingResult result, SessionStatus status, HttpServletRequest request) {
        logger.debug("INSIDE THE FORM POST");
        if (!result.hasErrors()) {
            try {

                logger.debug("The id in testCaseDTO: " + validatedTest.getId());

                TestRun testRun = (TestRun) request.getSession().getAttribute("testRun");

                if (testRun== null || testRun.getId()==null){
                     // brand new test case
                     logger.debug("The test case DTO is null or id is null");
                     testRun.setCreated(new Date());

                }else{
                      // update
                    logger.debug("The test case DTO is NOT null and  id is NOT null");
                     testRun = this.testRunDao.getById(testRun.getId());
                     testRun.createUpdateFromAnother(validatedTest);
                }
                logger.debug("ABOUT TO create or update");
                logger.debug("Total number of tests in the TestRun: "+testRun.getTestCases().size());
                this.testRunDao.save(testRun);
                status.setComplete();
                return "redirect:testruns.html?id=" + testRun.getId() + "&success=true";
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                logger.debug("The stack"+ e.getMessage());
                result.reject("DataIntegrityViolationException");
            } catch (ConcurrencyFailureException e) {
                result.reject("ConcurrentModificatonFailure");
            }
        }else{
            for (Object object : result.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    logger.debug("The Field error code"+fieldError.getCode());
                    logger.debug("The Field field code"+fieldError.getField());
                    logger.debug("The Field field code"+fieldError.getRejectedValue());
                }

                if(object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;
                    logger.debug("The object error code"+objectError.getCode());
                }
            }
        }
        return "testrunForm";
    }
}