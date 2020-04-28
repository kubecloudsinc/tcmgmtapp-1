
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/testcaseadd_form.html")
public class TestCaseAddFormController {

    private static final Logger logger =
            LoggerFactory.getLogger(TestCaseAddFormController.class);

    private final TestCaseDao testCaseDao;
    private final TestRunDao testRunDao;

    @Autowired
    public TestCaseAddFormController(TestCaseDao testCaseDao, TestRunDao testRunDao)
    {
        this.testCaseDao = testCaseDao;
        this.testRunDao = testRunDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm(@RequestParam(value = "id", required = false) Long id,
                                    HttpServletRequest request) {
        TestRun testRun;
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

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView processSubmit(@ModelAttribute("testRunDTO") TestRunDTO testRunDTO,
                                      RedirectAttributes redirectAttributes, HttpServletRequest request) {

        logger.debug("INSIDE THE FORM POST");
        TestRun testRun = (TestRun) request.getSession().getAttribute("testRun");

        logger.debug("The id in testCaseDTO: " + testRunDTO.getId());
        for (TestCase aTest: testRunDTO.getTestCases()){
            if (aTest.getChecked()){
                testRun.getTestCases().add(aTest);
            }
        }
        this.testRunDao.save(testRun);

        redirectAttributes.addAttribute("addcase", true);
        redirectAttributes.addAttribute("id", testRun.getId());

        return new RedirectView("testrun_form.html");
    }
}