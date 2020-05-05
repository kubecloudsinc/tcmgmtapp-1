
package com.onecloud.tcmgmt.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TEST_STEP")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestStep extends IdentifiableEntity implements Comparable{

    private static final long serialVersionUID = 2923459267760500809L;

    private String testStep;

    private String testStepResult;

    private TestCase testCase;

    private Long testCaseId;

    private Long testStepOrder;

    private boolean checked;

    @Column(name="TEST_STEP_ORDER")
    public Long getTestStepOrder() {
        return testStepOrder;
    }

    public void setTestStepOrder(Long testStepOrder) {
        this.testStepOrder = testStepOrder;
    }

    @Size(max = 200)
    @Column(name="TEST_STEP", length = 200)
    public String getTestStep() {
        return testStep;
    }

    public void setTestStep(String testStep) {
        this.testStep = testStep;
    }

    @Size(max = 200)
    @Column(name="TEST_STEP_RESULT", length = 200, nullable = false)
    public String getTestStepResult() {
        return testStepResult;
    }

    public void setTestStepResult(String testStepResult) {
        this.testStepResult = testStepResult;
    }

    @ManyToOne
    @JoinColumn(name="TEST_CASE_ID")
    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    @Column(name="TEST_CASE_ID", insertable = false, updatable = false)
    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    @Transient
    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof TestStep) {
            TestStep that = (TestStep) o;
            if(this.getId().equals(that.getId())){
               if(this.getTestStepOrder().intValue()== that.getTestStepOrder().intValue()){
                   if(this.getTestStep().equals(that.getTestStep())){
                       if(this.testStepResult.equals(that.testStepResult)){
                           isEqual=true;
                       }
                   }
               }
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getTestStep() == null ? System.identityHashCode(this) : 17 * this.getTestStep()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getTestStep();
    }

    @Override
    public int compareTo(Object o) {
        int isEqual = -1;
        if (this == o) {
            return 0;
        } else if (this.getId() == null) {
            return 10000000;
        } else if (o instanceof TestStep) {
            TestStep that = (TestStep) o;
            if(this.getId().equals(that.getId())){
                if(this.getTestStepOrder().intValue()== that.getTestStepOrder().intValue()){
                    if(this.getTestStep().equals(that.getTestStep())){
                        if(this.testStepResult.equals(that.testStepResult)){
                            isEqual=0;
                        }
                    }
                }else{
                    return this.getTestStepOrder().intValue()-that.getTestStepOrder().intValue();
                }
            }else{
                return this.getId().intValue()-that.getId().intValue();
            }
        }
        return isEqual;
    }
}