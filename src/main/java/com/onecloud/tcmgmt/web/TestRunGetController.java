
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestCaseStatusDao;
import com.onecloud.tcmgmt.dao.TestRunDao;
import com.onecloud.tcmgmt.dao.TestRunStatusDao;
import com.onecloud.tcmgmt.domain.appdb.TestCaseRunStatus;
import com.onecloud.tcmgmt.domain.appdb.TestRun;
import com.onecloud.tcmgmt.semantic.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/testrun.html")
public class TestRunGetController extends BaseFormController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TestRunDao testRunDao;
    private final TestCaseStatusDao testCaseStatusDao;
    private final TestRunStatusDao testRunStatusDao;

    @Autowired
    public TestRunGetController(TestRunDao testRunDao,TestCaseStatusDao testCaseStatusDao,
                           TestRunStatusDao testRunStatusDao)
    {
        this.testRunDao = testRunDao;
        this.testCaseStatusDao = testCaseStatusDao;
        this.testRunStatusDao=testRunStatusDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView handleRequestInternal(@RequestParam(value = "success", required = false) String success,
                              @RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "navPage", required = false) String navPage,
                                HttpServletRequest request) throws Exception {
        logger.debug("Inside the test run get controller");
        TestRun aTestRun = this.testRunDao.getById(id);

        navigatePages(aTestRun,navPage,request);

        logger.debug("ABOUT to get Status Codes from test run GET controller");
        return new ModelAndView("testrunView")
                    .addObject(aTestRun)
                    .addObject("caseStatusMap",getTestCaseStatusMap(this.testCaseStatusDao))
                    .addObject("runStatusMap",getTestRunStatusMap(this.testRunStatusDao));
    }

    @RequestMapping(params = "nav",method = RequestMethod.POST)
    protected ModelAndView getPage(@RequestParam(value = "navPage", required = false) String navPage,
                                   @RequestParam(value = "id", required = false) Long id,
                                   HttpServletRequest request) throws Exception {
        logger.debug("Inside the test run get controller");
//        long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
        TestRun aTestRun = (TestRun) request.getSession().getAttribute("testRun");

        navigatePages(aTestRun,navPage,request);

        logger.debug("ABOUT to get Status Codes from test run GET controller");
        return new ModelAndView("testrunView")
                .addObject(aTestRun)
                .addObject("caseStatusMap",getTestCaseStatusMap(this.testCaseStatusDao))
                .addObject("runStatusMap",getTestRunStatusMap(this.testRunStatusDao));
    }
}
