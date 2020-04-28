
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestCaseDao;
import com.onecloud.tcmgmt.domain.appdb.TestCase;
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
@RequestMapping(value = "/testcases.html")
public class TestRunListController {

    private static final Logger logger =
            LoggerFactory.getLogger(TestRunListController.class);

    private final TestCaseDao dao;

    @Autowired
    public TestRunListController(TestCaseDao dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayList(@RequestParam(value = "navPage", required = false) String navPage,
            HttpServletRequest request, HttpServletResponse response) {

        logger.debug("INSIDE THE PAGINATION METHOD");
        PagedListHolder<TestCase> testCaseList;
        List<TestCase> testCases=null;
        if(navPage == null) {
            testCaseList = new PagedListHolder<TestCase>();
            testCases = dao.getAll();
            // Setting the source for PagedListHolder
            testCaseList.setSource(testCases);
            testCaseList.setPageSize(ApplicationConstants.UI_PAGINATION_PAGE_SIZE);
            // Setting PagedListHolder instance to session
            request.getSession().setAttribute("testCaseList", testCaseList);
        }else if(navPage.equals("prev")) {
            // get the user list from session
            testCaseList = (PagedListHolder<TestCase>)request.getSession().getAttribute("testCaseList");
            // switch to previous page
            testCaseList.previousPage();
        }else if(navPage.equals("next")) {
            testCaseList = (PagedListHolder<TestCase>)request.getSession().getAttribute("testCaseList");
            // switch to next page
            testCaseList.nextPage();
        }else {
            int pageNum = Integer.parseInt(navPage);
            testCaseList = (PagedListHolder<TestCase>)request.getSession().getAttribute("testCaseList");
            // set the current page number
            // page number starts from zero in PagedListHolder that's why subtracting 1
            testCaseList.setPage(pageNum - 1);
        }

        return new ModelAndView("testcaseList");
    }
}
