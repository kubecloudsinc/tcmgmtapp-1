
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestCaseDao;
import com.onecloud.tcmgmt.dao.TestCaseStatusDao;
import com.onecloud.tcmgmt.dao.TestRunDao;
import com.onecloud.tcmgmt.dao.TestRunStatusDao;
import com.onecloud.tcmgmt.domain.appdb.TestCase;
import com.onecloud.tcmgmt.domain.appdb.TestRun;
import com.onecloud.tcmgmt.semantic.constants.ApplicationConstants;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/testrun_form.html")
@SessionAttributes("testRun")
public class TestRunFormController extends BaseFormController {

    private static final Logger logger =
            LoggerFactory.getLogger(TestRunFormController.class);

    private final TestCaseDao testCaseDao;
    private final TestRunDao testRunDao;
    private final TestCaseStatusDao testCaseStatusDao;
    private final TestRunStatusDao testRunStatusDao;

    @Autowired
    public TestRunFormController(TestCaseDao testCaseDao, TestRunDao testRunDao,
                                 TestCaseStatusDao testCaseStatusDao, TestRunStatusDao testRunStatusDao) {
        this.testCaseDao = testCaseDao;
        this.testRunDao = testRunDao;
        this.testCaseStatusDao = testCaseStatusDao;
        this.testRunStatusDao = testRunStatusDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm(@RequestParam(value = "id", required = false) Long id,
                                  @RequestParam(value = "editcase", required = false) Boolean editCase,
                                  HttpServletRequest request) {
        TestRun testRun;
        if (id == null || id.longValue() == 0) {
            logger.debug("ID is null so must be coming from the Add new ");
            logger.debug("Creating a new test");
            if (editCase != null && editCase) {
                testRun = (TestRun) request.getSession().getAttribute("testRun");
                logger.debug("Edit step is true : " + testRun.getId());
            } else {
                testRun = new TestRun();
                testRun.setCreated(new Date());
            }

        } else {
            logger.debug("ID is not null so must be coming from Edit: " + id);
            if (editCase != null && editCase) {
                testRun = (TestRun) request.getSession().getAttribute("testRun");
                logger.debug("Edit step is true : " + testRun.getId());
            } else {
                testRun = this.testRunDao.getById(id);
            }
        }

        navigateTestPages(testRun, null, request, testCaseDao);

        return new ModelAndView("testrunForm")
                .addObject(testRun)
                .addObject("statusMap", getTestRunStatusMap(this.testRunStatusDao));
    }

    @RequestMapping(params = "nav", method = RequestMethod.POST)
    public ModelAndView updateStatusPages(@RequestParam(value = "id", required = false) Long id,
                                          @RequestParam(value = "nav") String navPage,
                                          @ModelAttribute TestRun aTestRun,
                                          BindingResult result,HttpServletRequest request) {

        aTestRun = (TestRun) request.getSession().getAttribute("testRun");

        navigateTestPages(aTestRun,navPage,request,testCaseDao);

        return new ModelAndView("testrunForm")
                .addObject("testRun",aTestRun)
                .addObject("statusMap",getTestRunStatusMap(this.testRunStatusDao));
    }

    @RequestMapping(params = "add", method = RequestMethod.POST)
    public ModelAndView addTestCases(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "add") String navPage, @ModelAttribute TestRun testRun,
                                     BindingResult result, HttpServletRequest request) {

        testRun = (TestRun) request.getSession().getAttribute("testRun");
        logger.debug("INSIDE THE TEST CASE ADD : " + testRun.getId());
        logger.debug("INSIDE THE TEST CASE ADD navPage : " + navPage);
        logger.debug("INSIDE THE TEST CASE ADD test run navPage : " + testRun.getNavPage());

        navigateTestPages(testRun, navPage, request, this.testCaseDao);

        for (TestCase aTest : testRun.getTestCasePageList().getSource()) {
            logger.debug("The checked in test run pages:" + aTest.getChecked());
        }

        return new ModelAndView("testcaseaddForm").addObject(testRun);
    }

    @RequestMapping(params = "remove", method = RequestMethod.POST)
    public String removeTestCases(@RequestParam(value = "id", required = false) Long id,
                                  @ModelAttribute TestRun testRun, BindingResult result,
                                  HttpServletRequest request) {
        if (!result.hasErrors()) {
            logger.debug("INSIDE THE REMOVE CONTROLLER");
            testRun = (TestRun) request.getSession().getAttribute("testRun");

            logger.debug("The value of selection after: " + testRun.getSelection());
            testRun.removeSelectedTestCases();
            logger.debug("The value of description: " + testRun.getDescription());
            if(testRun.getId()!=null)
                return "redirect:testrun_form.html?id=" + testRun.getId() + "&editcase=true";
            else
                return "redirect:testrun_form.html?editcase=true";
        } else {
            result.reject("NotNull.TestRunForm", "The form is empty and no test cases to remove");
        }
        return "testrunForm";
    }

    @RequestMapping(params = "save", method = RequestMethod.POST)
    public ModelAndView processSubmit(@ModelAttribute("testRun") TestRun testRun,
                                      HttpServletRequest request) {

        logger.debug("INSIDE THE FORM POST");
        testRun = (TestRun) request.getSession().getAttribute("testRun");
        for (TestCase aTest : testRun.getTestCasePageList().getSource()) {
            logger.debug("The checked in test run pages:" + aTest.getChecked());
            if (aTest.getChecked()) {
                testRun.getTestCases().add(aTest);
            }
        }

        navigateTestPages(testRun,null,request,testCaseDao);

        logger.debug("The id in testCaseDTO: " + testRun.getId());

        return new ModelAndView("testrunForm").addObject(testRun)
                .addObject("statusMap", getTestRunStatusMap(this.testRunStatusDao));
    }

    @RequestMapping(params = "update", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("testRun") @Valid TestRun validatedTest,
                                BindingResult result, SessionStatus status, HttpServletRequest request) {
        logger.debug("INSIDE THE FORM POST");
        if (!result.hasErrors()) {
            try {

                logger.debug("The id in testCaseDTO: " + validatedTest.getId());

                TestRun testRun = (TestRun) request.getSession().getAttribute("testRun");

                if (testRun == null || testRun.getId() == null) {
                    // brand new test case
                    logger.debug("The test case DTO is null or id is null");
                    testRun.setCreated(new Date());

                } else {
                    // update
                    logger.debug("The test case DTO is NOT null and  id is NOT null");
                    testRun = this.testRunDao.getById(testRun.getId());
                    testRun.createUpdateFromAnother(validatedTest);
                }
                logger.debug("ABOUT TO create or update");
                logger.debug("Total number of tests in the TestRun: " + testRun.getTestCases().size());
                this.testRunDao.save(testRun);
                status.setComplete();
                return "redirect:testrun.html?id=" + testRun.getId() + "&success=true";
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                logger.debug("The stack" + e.getMessage());
                result.reject("DataIntegrityViolationException");
            } catch (ConcurrencyFailureException e) {
                result.reject("ConcurrentModificatonFailure");
            }
        }
        return "testrunForm";
    }
}