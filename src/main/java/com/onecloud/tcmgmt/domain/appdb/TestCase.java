
package com.onecloud.tcmgmt.domain.appdb;

import com.onecloud.tcmgmt.semantic.dto.TestCaseDTO;
import com.onecloud.tcmgmt.semantic.utils.SortByStepOrder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "TEST_CASE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestCase extends IdentifiableEntity {

    private static final Logger logger = LoggerFactory
            .getLogger(TestCase.class);

    private static final long serialVersionUID = 1L;

    private String testName;

    private String testDescription;

    private String testType;

    private String testSetup;

    private String expectedResult;

    private boolean automated = false;

    private List<TestStep> testSteps = new ArrayList<TestStep>();

    private List<TestRun> testRuns = new ArrayList<TestRun>();

    private Boolean checked=false;

    @Size(max = 20)
    @Column(name="TEST_NAME", length = 20, unique = true)
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Size(max = 200)
    @Column(name="TEST_DESCRIPTION", length = 200)
    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    @Size(max = 20)
    @Column(name="TEST_TYPE", length = 20)
    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    @Size(max = 200)
    @Column(name="TEST_SETUP", length = 200, nullable = false)
    public String getTestSetup() {
        return testSetup;
    }

    public void setTestSetup(String testSetup) {
        this.testSetup = testSetup;
    }

    @Size(max = 200)
    @Column(name="EXPECTED_RESULT", length = 10, nullable = false)
    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Column(name="AUTOMATED", length=1)
    public boolean isAutomated() {
        return automated;
    }

    public void setAutomated(boolean automated) {
        this.automated = automated;
    }

    @OneToMany(mappedBy="testCase", targetEntity=TestStep.class, cascade = CascadeType.ALL)
    public List<TestStep> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(List<TestStep> testSteps) {
        this.testSteps = testSteps;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "testCases")
    public List<TestRun> getTestRuns() {
        return testRuns;
    }

    public void setTestRuns(List<TestRun> testRuns) {
        this.testRuns = testRuns;
    }

    @Transient
    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getTestDescription() == null) {
            return false;
        } else if (o instanceof TestCase) {
            TestCase that = (TestCase) o;
            return this.getTestDescription().equals(that.getTestDescription());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getTestDescription() == null ? System.identityHashCode(this) : 17 * this.getTestDescription()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getTestDescription();
    }

    public TestCaseDTO convertToDTO(){
        TestCaseDTO aDTO = new TestCaseDTO();

        if(this.getId() !=null) {
            logger.debug("the id is"+this.getId());
            aDTO.setId(this.getId().longValue());
        }else{
            logger.debug("the id is null");
        }
        aDTO.setAutomated(this.automated);
        aDTO.setExpectedResult(this.expectedResult);
        aDTO.setTestDescription(this.testDescription);
        aDTO.setTestName(this.testName);
        aDTO.setTestSetup(this.testSetup);
        aDTO.setTestType(this.testType);
//        logger.debug("ABOUT TO SET THE TestSteps");
        Collections.sort(this.getTestSteps(),new SortByStepOrder());
//        logger.debug("TestSteps Count:"+this.getTestSteps().size());
        aDTO.setTestSteps(this.getTestSteps());
//        logger.debug("After setting DTO TestSteps Count:"+aDTO.getTestSteps().size());

        return aDTO;
    }

    public void createUpdateFromDTO(TestCaseDTO testCaseDTO){

        if( isAutomated() != testCaseDTO.isAutomated()) {
            setAutomated(testCaseDTO.isAutomated());
            logger.debug("automated created or updated ");
        }

        if(this.expectedResult==null || this.expectedResult.isEmpty()|| !this.expectedResult.equals(testCaseDTO.getExpectedResult())) {
            setExpectedResult(testCaseDTO.getExpectedResult());
            logger.debug("expectedResult created or updated ");
        }

        if(this.testDescription==null || this.testDescription.isEmpty() || (!this.testDescription.equals(testCaseDTO.getTestDescription()))) {
            setTestDescription(testCaseDTO.getTestDescription());
            logger.debug("testDescription created or updated ");
        }

        if(this.testName==null || this.testName.isEmpty() || (!this.testName.equals(testCaseDTO.getTestName()))) {
            setTestName(testCaseDTO.getTestName());
            logger.debug("testName created or updated ");
        }

        if(this.testSetup==null || this.testSetup.isEmpty() || (!this.testSetup.equals(testCaseDTO.getTestSetup()))) {
            setTestSetup(testCaseDTO.getTestSetup());
            logger.debug("testSetup created or updated ");
        }

        if(this.testType==null || this.testType.isEmpty() || (!this.testType.equals(testCaseDTO.getTestType()))) {
            setTestType(testCaseDTO.getTestType());
            logger.debug("testType created or updated ");
        }

        if(this.testSteps==null || this.testSteps.isEmpty() || (!this.testSteps.equals(testCaseDTO.getTestSteps()))) {
            for (TestStep dtoStep: testCaseDTO.getTestSteps()){
              if (dtoStep.getId()==null || dtoStep.getId().longValue() == 0){
                  dtoStep.setTestCase(this);
                  getTestSteps().add(dtoStep);
              }else{
                  TestStep domainStep=null;
                  for (TestStep aStep : getTestSteps()){
                      if (aStep.getId().longValue() ==  dtoStep.getId().longValue()){
                          domainStep = aStep;
                          break;
                      }
                  }
                  if(domainStep!=null && (!domainStep.equals(dtoStep))){
                      if(!domainStep.getTestStep().equals(dtoStep.getTestStep())){
                          domainStep.setTestStep(dtoStep.getTestStep());
                      }
                      if(!domainStep.getTestStepOrder().equals(dtoStep.getTestStepOrder())){
                          domainStep.setTestStepOrder(dtoStep.getTestStepOrder());
                      }
                      if(!domainStep.getTestStepResult().equals(dtoStep.getTestStepResult())){
                          domainStep.setTestStepResult(dtoStep.getTestStepResult());
                      }
                  }

              }
            }
            logger.debug("testSteps created or updated ");
        }
    }
}