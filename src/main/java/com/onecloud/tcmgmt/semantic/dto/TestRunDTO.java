package com.onecloud.tcmgmt.semantic.dto;

import com.onecloud.tcmgmt.domain.appdb.TestStep;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestRunDTO {

    private static final long serialVersionUID = 2989141237760500809L;

    private Long id;

    private String name;

    private String description;

    private Date created;

    public List<SimpleTestCaseDTO> testCaseDTOs = new ArrayList<SimpleTestCaseDTO>();

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

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<SimpleTestCaseDTO> getTestCaseDTOs() {
        return testCaseDTOs;
    }

    public void setTestCaseDTOs(List<SimpleTestCaseDTO> testCaseDTOs) {
        this.testCaseDTOs = testCaseDTOs;
    }
}
