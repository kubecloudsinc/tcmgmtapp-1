
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestCaseRunStatusDao;
import com.onecloud.tcmgmt.dao.TestCaseStatusDao;
import com.onecloud.tcmgmt.dao.TestRunDao;
import com.onecloud.tcmgmt.dao.TestRunStatusDao;
import com.onecloud.tcmgmt.domain.appdb.TestCase;
import com.onecloud.tcmgmt.domain.appdb.TestCaseRunStatus;
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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/testruncase_update.html")
@SessionAttributes("testRun")
public class TestRunCaseController extends BaseFormController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TestRunDao testRunDao;
    private final TestCaseRunStatusDao testCaseRunStatusDao;
    private final TestCaseStatusDao testCaseStatusDao;
    private final TestRunStatusDao testRunStatusDao;

    @Autowired
    public TestRunCaseController(TestRunDao testRunDao, TestCaseStatusDao testCaseStatusDao,
                                 TestCaseRunStatusDao testCaseRunStatusDao,
                                 TestRunStatusDao testRunStatusDao)
    {
        this.testRunDao = testRunDao;
        this.testCaseStatusDao = testCaseStatusDao;
        this.testRunStatusDao=testRunStatusDao;
        this.testCaseRunStatusDao=testCaseRunStatusDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView setUpForm(@RequestParam(value = "navPage", required = false) String navPage,
                                 HttpServletRequest request) throws Exception {
        logger.debug("Inside the test run get controller");
        long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
        TestRun aTestRun = this.testRunDao.getById(id);

        navigatePages(aTestRun, null, request);

        return new ModelAndView("testrunView")
                    .addObject("testRun",aTestRun)
                    .addObject("updateRun", "updateRun")
                    .addObject("runStatusMap",getTestRunStatusMap(this.testRunStatusDao))
                    .addObject("caseStatusMap",getTestCaseStatusMap(this.testCaseStatusDao));
    }

    @RequestMapping(params = "nav", method = RequestMethod.POST)
    public ModelAndView updateStatusPages(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "nav") String navPage,
                             @ModelAttribute TestRun aTestRun,
                             BindingResult result,HttpServletRequest request) {

        aTestRun = (TestRun) request.getSession().getAttribute("testRun");

        navigatePages(aTestRun, navPage, request);

        return new ModelAndView("testrunView")
                .addObject("testRun",aTestRun)
                .addObject("updateRun", "updateRun")
                .addObject("runStatusMap",getTestRunStatusMap(this.testRunStatusDao))
                .addObject("caseStatusMap",getTestCaseStatusMap(this.testCaseStatusDao));
    }

    @RequestMapping(params = "update", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("testRun") TestRun modelTestRun,
                                BindingResult result, SessionStatus status,HttpServletRequest request) {
        logger.debug("Inside the Update Test case run post method");
        if (!result.hasErrors()) {
            try {
                logger.debug("The id in model: " + modelTestRun.getId());
                for(TestCaseRunStatus caseStatus: modelTestRun.getTestCaseRunPageList().getSource()){
                    this.testCaseRunStatusDao.save(caseStatus);
                }
                status.setComplete();
                return "redirect:testrun.html?id=" + modelTestRun.getId() + "&success=true";
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                logger.debug("The stack"+ e.getMessage());
                result.reject("DataIntegrityViolationException");
            } catch (ConcurrencyFailureException e) {
                result.reject("ConcurrentModificatonFailure");
            }
        }
        return "testrunView";
    }
}