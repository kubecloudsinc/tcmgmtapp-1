
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestCaseDao;
import com.onecloud.tcmgmt.dao.TestCaseStatusDao;
import com.onecloud.tcmgmt.dao.TestRunStatusDao;
import com.onecloud.tcmgmt.domain.appdb.*;
import com.onecloud.tcmgmt.semantic.constants.ApplicationConstants;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.support.PagedListHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class BaseFormController {

    private static final Logger logger =
            LoggerFactory.getLogger(BaseFormController.class);

    public static Map<Long,String> TEST_CASE_STATUS_MAP=null;
    public static Map<Long,String> TEST_RUN_STATUS_MAP=null;

    public BaseFormController()
    {
    }

    public Map<Long,String> getTestCaseStatusMap(TestCaseStatusDao caseStatusDao){

        if(TEST_CASE_STATUS_MAP==null || TEST_CASE_STATUS_MAP.isEmpty()) {
            TEST_CASE_STATUS_MAP = new HashMap<Long,String>();
            List<TestCaseStatus> statusList = caseStatusDao.getAll();
            for (TestCaseStatus status: statusList){
                TEST_CASE_STATUS_MAP.put(status.getId(),status.getStatus());
            }
        }
        return TEST_CASE_STATUS_MAP;
    }

    public Map<Long,String> getTestRunStatusMap(TestRunStatusDao runStatusDao){
        logger.debug("Inside get test run status");
        if(TEST_RUN_STATUS_MAP==null || TEST_RUN_STATUS_MAP.isEmpty()) {
            logger.debug("Inside null test run status");
            TEST_RUN_STATUS_MAP = new HashMap<Long,String>();
            List<TestRunStatus> statusList = runStatusDao.getAll();
            for (TestRunStatus status: statusList){
                TEST_RUN_STATUS_MAP.put(status.getId(),status.getStatus());
            }
        }
        return TEST_RUN_STATUS_MAP;
    }

    protected void navigatePages(TestRun aTestRun, String navPage, HttpServletRequest request){
        logger.debug("INSIDE THE TEST RUN CASE nav METHOD");

        PagedListHolder<TestCaseRunStatus> testCaseList;
        List<TestCaseRunStatus> testCases=null;

        if(navPage == null) {
            testCaseList = new PagedListHolder<TestCaseRunStatus>();
            testCases = aTestRun.getTestCaseRunStatuses();
            // Setting the source for PagedListHolder
            testCaseList.setSource(testCases);
            testCaseList.setPageSize(ApplicationConstants.UI_PAGINATION_PAGE_SIZE);
            // Setting PagedListHolder instance to session
            aTestRun.setTestCaseRunPageList(testCaseList);
            request.getSession().setAttribute("testRun", aTestRun);
        }else if(navPage.equals("prev")) {
            // get the user list from session
            aTestRun = (TestRun) request.getSession().getAttribute("testRun");
            testCaseList = aTestRun.getTestCaseRunPageList();
            // switch to previous page
            testCaseList.previousPage();
        }else if(navPage.equals("next")) {
            aTestRun = (TestRun) request.getSession().getAttribute("testRun");
            testCaseList = aTestRun.getTestCaseRunPageList();
            // switch to next page
            testCaseList.nextPage();
        }else {
            int pageNum = Integer.parseInt(navPage);
            aTestRun = (TestRun) request.getSession().getAttribute("testRun");
            testCaseList = aTestRun.getTestCaseRunPageList();
            // set the current page number
            // page number starts from zero in PagedListHolder that's why subtracting 1
            testCaseList.setPage(pageNum - 1);
        }
         aTestRun.setTestCaseRunPageList(testCaseList);
    }


    protected void navigateTestPages(TestRun aTestRun, String navPage, HttpServletRequest request,
                                     TestCaseDao testCaseDao){

        logger.debug("INSIDE THE TEST RUN CASE nav METHOD");
        PagedListHolder<TestCase> testCaseList = aTestRun.getTestCasePageList();

        if(navPage == null || navPage.isEmpty()) {
            logger.debug("INSIDE FIRST TIME GET OF TEST RUN : "+ aTestRun.getId());

            Set<TestCase> testRunCases = aTestRun.getTestCases();
            // Setting the source for PagedListHolder
            aTestRun.getTestCasePageList().setSource(new ArrayList<TestCase>(testRunCases));
            aTestRun.getTestCasePageList().setPageSize(ApplicationConstants.UI_PAGINATION_PAGE_SIZE);
            // Setting PagedListHolder instance to session
            request.getSession().setAttribute("testRun", aTestRun);

        } else if(navPage.equals("addTest")) {
            logger.debug("INSIDE THE TEST CASE ADD : "+ aTestRun.getId());

            List<TestCase> allTestCases = testCaseDao.getAll();
            List<TestCase> testCasesToAdd =
                    (List<TestCase>) CollectionUtils.subtract(allTestCases,aTestRun.getTestCases());
            // Setting the source for PagedListHolder
            aTestRun.getTestCasePageList().setSource(testCasesToAdd);
            aTestRun.getTestCasePageList().setPageSize(ApplicationConstants.UI_PAGINATION_PAGE_SIZE);
            // Setting PagedListHolder instance to session
            request.getSession().setAttribute("testRun", aTestRun);
        }else if(navPage.equals("prev")) {
            // get the user list from session
            aTestRun = (TestRun) request.getSession().getAttribute("testRun");
            testCaseList = aTestRun.getTestCasePageList();
            // switch to previous page
            testCaseList.previousPage();
        }else if(navPage.equals("next")) {
            aTestRun = (TestRun) request.getSession().getAttribute("testRun");
            testCaseList = aTestRun.getTestCasePageList();
            // switch to next page
            testCaseList.nextPage();
        }else {
            int pageNum = Integer.parseInt(navPage);
            aTestRun = (TestRun) request.getSession().getAttribute("testRun");
            testCaseList = aTestRun.getTestCasePageList();
            // set the current page number
            // page number starts from zero in PagedListHolder that's why subtracting 1
            testCaseList.setPage(pageNum - 1);
        }
        aTestRun.setTestCasePageList(testCaseList);
    }
}