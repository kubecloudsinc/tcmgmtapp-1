
package com.onecloud.tcmgmt.dao;

import com.onecloud.tcmgmt.domain.appdb.TestCase;
import org.springframework.dao.DataAccessException;

public interface TestCaseDao extends IdentifiableEntityDao<TestCase> {

    public TestCase getByName(String testName) throws DataAccessException;

}
