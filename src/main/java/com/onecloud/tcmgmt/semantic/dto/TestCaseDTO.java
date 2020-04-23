package com.onecloud.tcmgmt.semantic.dto;

import com.onecloud.tcmgmt.domain.appdb.IdentifiableEntity;
import com.onecloud.tcmgmt.domain.appdb.TestStep;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class TestCaseDTO {

    private static final long serialVersionUID = 2989149267760500809L;

    private Long id;

    private String testName;

    private String testDescription;

    private String testType;

    private String testSetup;

    private String expectedResult;

    private boolean automated = false;

    private List<TestStep> testSteps = new ArrayList<TestStep>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    @NotEmpty
    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

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

    public List<TestStep> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(List<TestStep> testSteps) {
        this.testSteps = testSteps;
    }
}
