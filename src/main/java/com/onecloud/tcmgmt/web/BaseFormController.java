
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.*;
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

    public static Map<Long,String> COMPONENT_MAP=null;
    public static Map<Long,String> DEFECT_STATUS_MAP=null;
    public static Map<Long,String> PRIORITY_MAP=null;
    public static Map<Long,String> SEVERITY_MAP=null;
    public static Map<Long,String> PRODUCT_MAP=null;

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

    public Map<Long,String> getComponentMap(ComponentDao componentDao){

        if(COMPONENT_MAP ==null || COMPONENT_MAP.isEmpty()) {

            COMPONENT_MAP = new HashMap<Long,String>();
            List<Component> componentList = componentDao.getAll();
            for (Component component: componentList){
                COMPONENT_MAP.put(component.getId(),component.getComponent());
            }
        }
        return COMPONENT_MAP;
    }

    public Map<Long,String> getDefectStatusMap(DefectStatusDao defectStatusDao){

        if(DEFECT_STATUS_MAP==null || DEFECT_STATUS_MAP.isEmpty()) {

            DEFECT_STATUS_MAP = new HashMap<Long,String>();
            List<DefectStatus> statusList = defectStatusDao.getAll();
            for (DefectStatus status: statusList){
                DEFECT_STATUS_MAP.put(status.getId(),status.getStatus());
            }
        }
        return DEFECT_STATUS_MAP;
    }

    public Map<Long,String> getPriorityMap(PriorityDao priorityDao){

        if(PRIORITY_MAP==null || PRIORITY_MAP.isEmpty()) {

            PRIORITY_MAP = new HashMap<Long,String>();
            List<Priority> priorityList = priorityDao.getAll();
            for (Priority priority: priorityList){
                PRIORITY_MAP.put(priority.getId(),priority.getPriority());
            }
        }
        return PRIORITY_MAP;
    }

    public Map<Long,String> getProductMap(ProductDao productDao){

        if(PRODUCT_MAP==null || PRODUCT_MAP.isEmpty()) {

            PRODUCT_MAP = new HashMap<Long,String>();
            List<Product> productList = productDao.getAll();
            for (Product product: productList){
                PRODUCT_MAP.put(product.getId(),product.getProduct());
            }
        }
        return PRODUCT_MAP;
    }

    public Map<Long,String> getSeverityMap(SeverityDao severityDao){

        if(SEVERITY_MAP==null || SEVERITY_MAP.isEmpty()) {

            SEVERITY_MAP = new HashMap<Long,String>();
            List<Severity> severityList = severityDao.getAll();
            for (Severity severity: severityList){
                SEVERITY_MAP.put(severity.getId(),severity.getSeverity());
            }
        }
        return SEVERITY_MAP;
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