package com.onecloud.tcmgmt.semantic.dto;

import com.onecloud.tcmgmt.domain.appdb.TestCase;
import com.onecloud.tcmgmt.domain.appdb.TestRun;
import org.springframework.beans.support.PagedListHolder;

import java.util.ArrayList;
import java.util.List;

public class TestRunDTO {

    private static final long serialVersionUID = 2989141237760500809L;

    private Long id;

    private String name;

    private String description;

    public List<TestCase> testCases = new ArrayList<TestCase>();

    private TestRun testRun;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }
}
