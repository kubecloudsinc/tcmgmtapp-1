
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestCaseDao;
import com.onecloud.tcmgmt.domain.appdb.TestCase;
import com.onecloud.tcmgmt.domain.appdb.TestStep;
import com.onecloud.tcmgmt.domain.appdb.User;
import com.onecloud.tcmgmt.semantic.dto.TestCaseDTO;
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
import java.util.Iterator;
import java.util.SortedSet;

@Controller
@SessionAttributes("testCaseDTO")
public class TestCaseFormController {

    private static final Logger logger = LoggerFactory
            .getLogger(TestCaseFormController.class);

    private final TestCaseDao dao;
    private String authUserName = "authUser";

    @Autowired
    public TestCaseFormController(TestCaseDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/testcase_form.html",method = RequestMethod.GET)
    public ModelAndView setupForm(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "stepUpdate", required = false) boolean stepUpdate,
                                  HttpServletRequest request) {
        TestCaseDTO testCaseDTO;
        if (id == null ) {
            logger.debug("ID is null so must be coming from the Add new Test or step");
            if(!stepUpdate) {
                logger.debug("add step is false so must be coming from the Add new Test");
                testCaseDTO = new TestCaseDTO();
                User currentUser = (User) request.getAttribute(authUserName);
                logger.debug("about to set user id: "+currentUser.getId());
                testCaseDTO.setAuthorId(currentUser.getId());

            }else{

                testCaseDTO = (TestCaseDTO) request.getSession().getAttribute("testCaseDTO");
                logger.debug("update step is true so must be coming from the Add new step: "+ testCaseDTO.getId());
            }
        }else{
            logger.debug("ID is not null so must be coming from Edit: "+id);
            testCaseDTO = this.dao.getById(id).convertToDTO();
        }
        return new ModelAndView("testcaseForm").addObject(testCaseDTO);
    }

    @RequestMapping(value = "/testcase_form.html", params = "add", method = RequestMethod.POST)
    public String addStep(@RequestParam(value = "id", required = false) Long id,
                          @ModelAttribute("testCaseDTO") TestCaseDTO testCaseDTO,
                          BindingResult result,HttpServletRequest request) {
            logger.debug("INSIDE THE STEP ADD CONTROLLER");
            testCaseDTO = (TestCaseDTO) request.getSession().getAttribute("testCaseDTO");
            TestStep testStep = new TestStep();
            if (id == null) {
                logger.debug("INSIDE THE GET: new test case creation");
            } else {
                logger.debug("INSIDE THE GET: Update an test case flow:" + testCaseDTO.getId());
                testStep.setTestCaseId(testCaseDTO.getId());
            }
            if (testCaseDTO.getTestSteps() == null || testCaseDTO.getTestSteps().size() == 0) {
                testStep.setTestStepOrder(new Long(1));
            } else {
                logger.debug("the total steps: " + testCaseDTO.getTestSteps().size());
                logger.debug("the new  step number: " + (testCaseDTO.getTestSteps().size() + 1));
                testStep.setTestStepOrder(new Long(testCaseDTO.getTestSteps().size() + 1));
            }

            testCaseDTO.getTestSteps().add(testStep);

            return "redirect:testcase_form.html?stepUpdate=true";
    }

    @RequestMapping(value = "/testcase_form.html",params = "remove", method = RequestMethod.POST)
    public String removeStep(@RequestParam(value = "id", required = false) Long id,
                             @ModelAttribute("testCaseDTO") TestCaseDTO testCaseDTO, BindingResult result,
                             HttpServletRequest request) {
        logger.debug("INSIDE THE STEP REMOVE CONTROLLER");
        testCaseDTO = (TestCaseDTO) request.getSession().getAttribute("testCaseDTO");

        logger.debug("The testCaseDTO is null? : " + (testCaseDTO == null));
        if (testCaseDTO != null && testCaseDTO.getTestSteps() != null
                && testCaseDTO.getTestSteps().size() > 0) {
            logger.debug("the total steps in remove before: " + testCaseDTO.getTestSteps().size());
            TestStep aTestStep;
            int i=1;
            for (Iterator<TestStep> it = testCaseDTO.getTestSteps().iterator(); it.hasNext(); ) {
                aTestStep = it.next();
                if (aTestStep.getChecked()) {
                    it.remove();
                }else{
                    aTestStep.setTestStepOrder(new Long(i));
                    i++;
                }
            }
            logger.debug("the total steps in remove after: " + testCaseDTO.getTestSteps().size());
        }
        return "redirect:testcase_form.html?stepUpdate=true";
    }

    @RequestMapping(value = "/testcase_form.html", params = "save", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("testCaseDTO") @Valid TestCaseDTO validatedTestCaseDTO,
                                BindingResult result, SessionStatus status, HttpServletRequest request) {
        if (!result.hasErrors()) {
            try {
                logger.debug("INSIDE THE FORM POST");
                logger.debug("The id in testCaseDTO: " + validatedTestCaseDTO.getId());

                // remove empty steps.
                SortedSet<TestStep> dtoSteps = validatedTestCaseDTO.getTestSteps();
                TestStep aTestStep;
                for (Iterator<TestStep> it = dtoSteps.iterator(); it.hasNext();) {
                    aTestStep = it.next();
                    if((aTestStep.getTestStep()==null || aTestStep.getTestStep().isEmpty() )||
                            aTestStep.getTestStepResult()==null || aTestStep.getTestStepResult().isEmpty()){
                        logger.debug("INSIDE THE removal step");
                        it.remove();
                    }
                }

                TestCaseDTO testCaseDTO = (TestCaseDTO) request.getSession().getAttribute("testCaseDTO");
                TestCase testCase;
                if (testCaseDTO== null || testCaseDTO.getId()==null){
                    // brand new test case
                    logger.debug("The test case DTO is null or id is null");
                    testCase = new TestCase();
                    testCase.createUpdateFromDTO(validatedTestCaseDTO);
                }else{
                    // update
                    logger.debug("The test case DTO is NOT null and  id is NOT null");
                    testCase = this.dao.getById(testCaseDTO.getId());
                    logger.debug("The Steps in validated DTO:"+validatedTestCaseDTO.getTestSteps().size());
                    testCase.createUpdateFromDTO(validatedTestCaseDTO);
                }
                logger.debug("ABOUT TO create or update");
                this.dao.save(testCase);
                status.setComplete();
                return "redirect:testcase.html?id=" + testCase.getId() + "&success=true";
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                logger.debug("The stack"+ e.getMessage());
                result.reject("DataIntegrityViolationException.TestCaseForm","Trying to save with same name that exists in database");
            } catch (ConcurrencyFailureException e) {
                result.reject("ConcurrentModificatonFailure");
            }
        }
        return "testcaseForm";
    }
}