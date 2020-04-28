
package com.onecloud.tcmgmt.domain.appdb;

import com.onecloud.tcmgmt.semantic.dto.TestRunDTO;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SortNatural;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "TEST_RUN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestRun extends IdentifiableEntity implements Comparable{

    private static final Logger logger = LoggerFactory.getLogger(TestRun.class);

    private static final long serialVersionUID = 2993569267712300809L;

    private String name;

    private String description;

    private Date created;

    private SortedSet<TestCase> testCases = new TreeSet<TestCase>();

    private boolean selection;

    @Transient
    public boolean getSelection() {
        return selection;
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
    }

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
            @JoinColumn(name = "TEST_RUN_ID", nullable = false, updatable = false) },
        inverseJoinColumns = {
            @JoinColumn(name = "TEST_CASE_ID", nullable = false, updatable = false)},
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @SortNatural
    @OrderBy("id ASC")
    public SortedSet<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(SortedSet<TestCase> testCases) {
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
            List<TestCase> removedList =
                    (List<TestCase>)  CollectionUtils.subtract(getTestCases(),anotherTestRun.getTestCases());

            for (TestCase aTestCase : removedList){
                getTestCases().remove(aTestCase);
            }
        }
    }

    public void removeSelectedTestCases(){
        logger.debug("inside the remove method: "+this.testCases.size());

        TestCase aTestCase;
        for (Iterator<TestCase> it = this.testCases.iterator(); it.hasNext();) {
             aTestCase = it.next();
            if (aTestCase.getChecked()) {
                it.remove();
            }
        }
    }

    public TestRunDTO createDTO(){
        TestRunDTO dto = new TestRunDTO();

        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());

        return dto;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        } else if (this.getDescription() == null) {
            return -1;
        } else if (o instanceof TestRun) {
            TestRun that = (TestRun) o;
            return this.getDescription().compareTo(that.getDescription());
        } else {
            return -1;
        }
    }
}