package com.onecloud.tcmgmt.domain.appdb;

import java.io.Serializable;

public class TestCaseRunId implements Serializable {

    private Long testCaseId;

    private Long testRunId;

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Long getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }
}