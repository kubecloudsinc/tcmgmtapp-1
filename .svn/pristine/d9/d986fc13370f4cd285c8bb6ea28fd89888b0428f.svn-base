
package com.cisco.cstg.autotools.domain.appdb;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "TABLE_TEST")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Test extends IdentifiableEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -699680677069379435L;

	/**
     * The TIMS or external test management system id 
     */
    private String testDescription;

    private String testClassName;

    private String testMethodName;
    
    private String timsID;
    
    private String testName;
    
    private Set<TestSuiteTest> testSuiteTests=new HashSet<TestSuiteTest>(); 
    
    private TestStatus testStatus;
    
    @OneToOne(mappedBy="test",targetEntity=TestStatus.class) 
	public TestStatus getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(TestStatus testStatus) {
		this.testStatus = testStatus;
	}
   
    @Column(name="TEST_DESCRIPTION")
	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

    @Column(name="TEST_CLASS_NAME")
	public String getTestClassName() {
		return testClassName;
	}

	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}

   @Column(name="TEST_METHOD_NAME")
	public String getTestMethodName() {
		return testMethodName;
	}

	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}

	@Column(name="TIMS_ID")	
	public String getTimsID() {
		return timsID;
	}

	public void setTimsID(String timsID) {
		this.timsID = timsID;
	}
	
    @Column(name="TEST_NAME")
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
	
//	mappedBy="test"
	
	@OneToMany(mappedBy="test", targetEntity=TestSuiteTest.class)
	public Set<TestSuiteTest> getTestSuiteTests() {

		return testSuiteTests;
	}

	public void setTestSuiteTests(Set<TestSuiteTest> testSuiteTests) {
		this.testSuiteTests = testSuiteTests;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof Test) {
            Test that = (Test) o;
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
    private String getName() {
        return this.getId() + " " + this.getId();
    }

    @Override
    public String toString() {
        return this.getName();
    }
}