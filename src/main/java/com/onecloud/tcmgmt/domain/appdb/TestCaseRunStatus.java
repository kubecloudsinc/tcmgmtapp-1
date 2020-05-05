
package com.onecloud.tcmgmt.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;

@Entity
@Table(name = "TEST_CASE_RUN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@IdClass(TestCaseRunId.class)
public class TestCaseRunStatus implements Comparable{

    private static final long serialVersionUID = 2991529267460500809L;

    private Long testCaseId;
    private Long testRunId;
    private Long testCaseStatusId;

    private TestCase testCase;
    private TestRun testRun;

    @Id
    @Column(name="TEST_CASE_ID", nullable = false, insertable = false, updatable = false)
    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    @Id
    @Column(name="TEST_RUN_ID", nullable = false, insertable = false, updatable = false)
    public Long getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }

    @Column(name="TEST_CASE_STATUS_ID")
    public Long getTestCaseStatusId() {
        return testCaseStatusId;
    }

    public void setTestCaseStatusId(Long testCaseStatusId) {
        this.testCaseStatusId = testCaseStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "TEST_CASE_ID",nullable = false, insertable = false, updatable = false)
    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    @ManyToOne
    @JoinColumn(name = "TEST_RUN_ID", nullable = false, insertable = false, updatable = false)
    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            return true;
        } else if (this.getTestRunId() == null) {
            return false;
        } else if (o instanceof TestCaseRunStatus) {
            TestCaseRunStatus that = (TestCaseRunStatus) o;
            if(this.getTestCaseId().equals(that.getTestCaseId())){
               if(this.getTestRunId().equals(that.getTestRunId())){
                           isEqual=true;
                   }
               }
            }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getTestRunId() == null ? System.identityHashCode(this)
                : 17 * this.getTestRunId().hashCode()*this.getTestCaseId().hashCode();
    }

    @Override
    public int compareTo(Object o) {
        int isEqual = -1;
        if (this == o) {
            return 0;
        } else if (this.getTestRunId() == null) {
            return 10000000;
        } else if (o instanceof TestCaseRunStatus) {
            TestCaseRunStatus that = (TestCaseRunStatus) o;
            if (this.getTestRunId().equals(that.getTestRunId())) {
                if (this.getTestCaseId().equals(that.getTestCaseId())) {
                    isEqual = 0;
                } else {
                return this.getTestCaseId().compareTo(that.getTestCaseId());
                }
            } else {
                return this.getTestRunId().intValue() - that.getTestRunId().intValue();
            }
    }

        return isEqual;
    }
}