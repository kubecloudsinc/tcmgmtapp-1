
package com.cisco.cstg.autotools.domain.appdb;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "TABLE_TEST_SUITE_STATUS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestSuiteStatus extends IdentifiableEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8851285391409917367L;
	public static final String RUNNING = "Running";
    public static final String COMPLETED = "Completed";
    public static final String FAILED = "Failed";
    
    private Long testSuiteId;

    private String testSuiteStatus;

    private Date created;
    
    private Date updated;
    
    private String reportName;

    private TestSuite testSuite;

    @OneToOne(targetEntity=TestSuite.class) 
    @JoinColumn(name = "TEST_SUITE_ID", referencedColumnName="id")	
    public TestSuite getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(TestSuite testSuite) {
		this.testSuite = testSuite;
	}

    @Column(name="TEST_SUITE_ID",insertable=false,updatable=false)
    public Long getTestSuiteId() {
        return this.testSuiteId;
    }

    public void setTestSuiteId(Long testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

    @Column(name="TEST_SUITE_STATUS")
    public String getTestSuiteStatus() {
        return this.testSuiteStatus;
    }

    public void setTestSuiteStatus(String testSuiteStatus) {
        this.testSuiteStatus = testSuiteStatus;
    }

    @Past
    @Column(name="CREATE_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    @Column(name="UPDATE_DATE", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof TestSuiteStatus) {
            TestSuiteStatus that = (TestSuiteStatus) o;
            return this.getId().equals(that.getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getId() == null ? System.identityHashCode(this) : 17 * this.getId()
                .hashCode();
    }

    @Transient
    public String getName() {
        return this.getId() + " " + this.getId();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Column(name="REPORT_NAME")
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}