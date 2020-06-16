
package com.onecloud.tcmgmt.domain.appdb;

import com.onecloud.tcmgmt.semantic.dto.TestCaseDTO;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SortNatural;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "TEST_CASE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestCase extends IdentifiableEntity implements Comparable{

    private static final Logger logger = LoggerFactory
            .getLogger(TestCase.class);

    private static final long serialVersionUID = 2L;

    private String testName;

    private String testDescription;

    private String testType;

    private String testSetup;

    private String expectedResult;

    private boolean automated = false;

    private Long status;

    private SortedSet<TestStep> testSteps = new TreeSet<TestStep>();

    private SortedSet<TestRun> testRuns = new TreeSet<TestRun>();

    private boolean checked;

    private User author;

    @Size(max = 100)
    @Column(name="TEST_NAME", length = 100)
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Size(max = 1000)
    @Column(name="TEST_DESCRIPTION", length = 1000)
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

    @Size(max = 1000)
    @Column(name="TEST_SETUP", length = 1000, nullable = false)
    public String getTestSetup() {
        return testSetup;
    }

    public void setTestSetup(String testSetup) {
        this.testSetup = testSetup;
    }

    @Size(max = 1000)
    @Column(name="EXPECTED_RESULT", length = 1000, nullable = false)
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

    @OneToMany(mappedBy="testCase", targetEntity=TestStep.class,
            orphanRemoval=true, cascade = CascadeType.ALL)
    @SortNatural
    @OrderBy("testStepOrder ASC")
    public SortedSet<TestStep> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(SortedSet<TestStep> testSteps) {
        this.testSteps = testSteps;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                        CascadeType.DETACH,
                        CascadeType.MERGE,
                        CascadeType.REFRESH,
                        CascadeType.PERSIST
                    })
    @JoinTable(name = "TEST_CASE_RUN",
            joinColumns = {
                    @JoinColumn(name = "TEST_CASE_ID", nullable = false, updatable = false) },
            inverseJoinColumns = {
                    @JoinColumn(name = "TEST_RUN_ID", nullable = false, updatable = false)},
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @SortNatural
    @OrderBy("id ASC")
    public SortedSet<TestRun> getTestRuns() {
        return testRuns;
    }

    public void setTestRuns(SortedSet<TestRun> testRuns) {
        this.testRuns = testRuns;
    }

    @ManyToOne
    @JoinColumn(name="AUTHOR_ID")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Transient
    public boolean getChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Transient
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long statusId) {
        this.status = statusId;
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
        aDTO.setAuthorId(this.author.getId());
//        logger.debug("ABOUT TO SET THE TestSteps");
//        Collections.sort(this.getTestSteps(),new SortByStepOrder());
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
        if(this.author==null || this.author.getId()==null ) {
            User user = new User();
            user.setId(testCaseDTO.getAuthorId());
            setAuthor(user);
            logger.debug("author id updated");
        }

        if(this.testSteps==null || this.testSteps.isEmpty() || (!this.testSteps.equals(testCaseDTO.getTestSteps()))) {

            logger.debug("Inside the test steps update method");
            TestStep dtoStep ;
            for (Iterator<TestStep> it = testCaseDTO.getTestSteps().iterator(); it.hasNext();){
               dtoStep = it.next();
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

            logger.debug("Size of lists Before: "+getTestSteps().size()+" "+testCaseDTO.getTestSteps());
            List<TestStep> removedList =
                    (List<TestStep>)  CollectionUtils.subtract(getTestSteps(),testCaseDTO.getTestSteps());
            logger.debug("Size of removed lists: "+removedList.size());
            for (TestStep aTestStep : removedList){
                getTestSteps().remove(aTestStep);
            }
            logger.debug("Size of lists After: "+getTestSteps().size()+" "+testCaseDTO.getTestSteps());

            logger.debug("testSteps created or updated ");
        }
    }

    @Override
    public int compareTo(Object o) {

        if (this == o) {
            return 0;
        } else if (this.getTestDescription() == null) {
            return -1;
        } else if (o instanceof TestCase) {
            TestCase that = (TestCase) o;
            return this.getTestName().compareTo(that.getTestName());
        } else {
            return -1;
        }
    }
}