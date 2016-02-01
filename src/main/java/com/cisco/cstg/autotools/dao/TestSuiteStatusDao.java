
package com.cisco.cstg.autotools.dao;

import org.springframework.dao.DataAccessException;

import com.cisco.cstg.autotools.domain.appdb.TestSuiteStatus;

public interface TestSuiteStatusDao extends IdentifiableEntityDao<TestSuiteStatus> {
    public TestSuiteStatus getByTestSuiteId(Long testSuiteId) throws DataAccessException;
}
