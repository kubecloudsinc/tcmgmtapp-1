
package com.cisco.cstg.autotools.domain.appdb;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "TABLE_TEST_SUITE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestSuite extends IdentifiableEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2533087672050456246L;

    private String testSuiteName;

    private String testSuiteDescription;

   private TestSuiteStatus testSuiteStatus;
    
    @OneToOne(mappedBy="testSuite",targetEntity=TestSuiteStatus.class) 
	public TestSuiteStatus getTestSuiteStatus() {
		return testSuiteStatus;
	}

	public void setTestSuiteStatus(TestSuiteStatus testSuiteStatus) {
		this.testSuiteStatus = testSuiteStatus;
	}
    /**
     * set of tests that belong to this Test Suite.
     */
    private Set<TestSuiteTest> testSuiteTests= new HashSet<TestSuiteTest>(); 
    
//    @ManyToMany(cascade=CascadeType.ALL,mappedBy="testSuites")
//	@JoinTable(name="TABLE_TEST_TABLE_TEST_SUITE", joinColumns=@JoinColumn(name="TEST_SUITE_ID"), inverseJoinColumns=@JoinColumn(name="TEST_ID"))
//    mappedBy="testSuite"
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy="testSuite", targetEntity=TestSuiteTest.class)
	public Set<TestSuiteTest> getTestSuiteTests() {

		return testSuiteTests;
	}

	public void setTestSuiteTests(Set<TestSuiteTest> testSuiteTests) {
		this.testSuiteTests = testSuiteTests;
	}

    @Column(name="TEST_SUITE_NAME")
    public String getTestSuiteName() {
 		return testSuiteName;
 	}

 	public void setTestSuiteName(String testSuiteName) {
 		this.testSuiteName = testSuiteName;
 	}

 	@Column(name="TEST_SUITE_DESCRIPTION")
 	public String getTestSuiteDescription() {
 		return testSuiteDescription;
 	}

 	public void setTestSuiteDescription(String testSuiteDescription) {
 		this.testSuiteDescription = testSuiteDescription;
 	}    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof TestSuite) {
            TestSuite that = (TestSuite) o;
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
}