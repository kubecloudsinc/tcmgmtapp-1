
package com.onecloud.tcmgmt.domain.appdb;

import com.onecloud.tcmgmt.semantic.dto.SimpleTestCaseDTO;
import com.onecloud.tcmgmt.semantic.dto.TestRunDTO;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TEST_RUN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestRun extends IdentifiableEntity {

    private static final Logger logger = LoggerFactory.getLogger(TestRun.class);

    private static final long serialVersionUID = 2993569267712300809L;

    private String name;

    private String description;

    private Date created;

    private List<TestCase> testCases = new ArrayList<TestCase>();

    @NotEmpty
    @Size(max = 20)
    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(max = 100)
    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="CREATE_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TEST_CASE_RUN",
        joinColumns = {
            @JoinColumn(name = "TEST_RUN_ID", nullable = false, updatable = false) },
        inverseJoinColumns = {
            @JoinColumn(name = "TEST_CASE_ID", nullable = false, updatable = false) })
    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getDescription() == null) {
            return false;
        } else if (o instanceof TestRun) {
            TestRun that = (TestRun) o;
            return this.getDescription().equals(that.getDescription());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getDescription() == null ? System.identityHashCode(this) : 17 * this.getDescription()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

    public void createUpdateFromAnother(TestRun anotherTestRun){
        if(anotherTestRun!=null){
            if(!anotherTestRun.getName().equals(this.name)){
                setName(anotherTestRun.getName());
            }
            if(!anotherTestRun.getDescription().equals(this.description)){
                setDescription(anotherTestRun.getDescription());
            }
        }
    }

    public void removeSelectedTestCases(){
        logger.debug("inside the remove method: "+this.testCases.size());

        for (int i = 0; i < getTestCases().size(); i++) {
            logger.debug("is checked: "+getTestCases().get(i).getTestName()+" "+getTestCases().get(i).getChecked());

//            if(getTestCases().get(i).getChecked()!=null &&
//                    getTestCases().get(i).getChecked().equals(getTestCases().get(i).getTestName())){
//                logger.debug("Removing: "+i);
//                getTestCases().remove(i);
//            }
        }
    }

    public TestRunDTO createDTO(){
        TestRunDTO dto = new TestRunDTO();

        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());
        List<SimpleTestCaseDTO> caseDTOs = new ArrayList<SimpleTestCaseDTO>();
        SimpleTestCaseDTO caseDTO;
        for (TestCase testCase : getTestCases()){
            caseDTO = new SimpleTestCaseDTO();
            caseDTO.setTestDescription(testCase.getTestDescription());
            caseDTO.setTestName(testCase.getTestName());
            caseDTO.setId(testCase.getId());
            caseDTOs.add(caseDTO);
        }
        dto.setTestCaseDTOs(caseDTOs);

        return dto;
    }
}