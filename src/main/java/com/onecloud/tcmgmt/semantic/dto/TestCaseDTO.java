package com.onecloud.tcmgmt.semantic.dto;

import com.onecloud.tcmgmt.domain.appdb.TestStep;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class TestCaseDTO {

    private static final long serialVersionUID = 2989149267760500809L;

    private Long id;

    private String testName;

    private String testDescription;

    private String testType;

    private String testSetup;

    private String expectedResult;

    private boolean automated = false;

    private Long authorId;

    private SortedSet<TestStep> testSteps = new TreeSet<TestStep>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty
    @Size(max = 100)
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Size(max = 1000)
    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    @NotEmpty
    @Size(max = 20)
    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    @Size(max = 1000)
    public String getTestSetup() {
        return testSetup;
    }

    public void setTestSetup(String testSetup) {
        this.testSetup = testSetup;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public boolean isAutomated() {
        return automated;
    }

    public void setAutomated(boolean automated) {
        this.automated = automated;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public SortedSet<TestStep> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(SortedSet<TestStep> testSteps) {
        this.testSteps = testSteps;
    }
}
