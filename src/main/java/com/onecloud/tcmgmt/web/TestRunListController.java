
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestRunDao;
import com.onecloud.tcmgmt.domain.appdb.TestRun;
import com.onecloud.tcmgmt.domain.appdb.TestRun;
import com.onecloud.tcmgmt.semantic.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/testruns.html")
public class TestRunListController {

    private static final Logger logger =
            LoggerFactory.getLogger(TestRunListController.class);

    private final TestRunDao dao;

    @Autowired
    public TestRunListController(TestRunDao dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayList(@RequestParam(value = "navPage", required = false) String navPage,
            HttpServletRequest request, HttpServletResponse response) {

        logger.debug("INSIDE THE PAGINATION CONTROLLER");
        PagedListHolder<TestRun> testRunList;
        List<TestRun> testRuns=null;
        if(navPage == null) {
            testRunList = new PagedListHolder<TestRun>();
            testRuns = dao.getAll();
            // Setting the source for PagedListHolder
            testRunList.setSource(testRuns);
            testRunList.setPageSize(ApplicationConstants.UI_PAGINATION_PAGE_SIZE);
            // Setting PagedListHolder instance to session
            request.getSession().setAttribute("testRunList", testRunList);
        }else if(navPage.equals("prev")) {
            // get the user list from session
            testRunList = (PagedListHolder<TestRun>)request.getSession().getAttribute("testRunList");
            // switch to previous page
            testRunList.previousPage();
        }else if(navPage.equals("next")) {
            testRunList = (PagedListHolder<TestRun>)request.getSession().getAttribute("testRunList");
            // switch to next page
            testRunList.nextPage();
        }else {
            int pageNum = Integer.parseInt(navPage);
            testRunList = (PagedListHolder<TestRun>)request.getSession().getAttribute("testRunList");
            // set the current page number
            // page number starts from zero in PagedListHolder that's why subtracting 1
            testRunList.setPage(pageNum - 1);
        }

        return new ModelAndView("testrunList");
    }
}