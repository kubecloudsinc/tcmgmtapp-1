
package com.onecloud.tcmgmt.dao;

import com.onecloud.tcmgmt.domain.appdb.TestCaseRunStatus;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface TestCaseRunStatusDao  {

    public List<TestCaseRunStatus> getAll() throws DataAccessException;

    public void save(TestCaseRunStatus entity) throws DataAccessException;

    public void delete(TestCaseRunStatus entity) throws DataAccessException;

    public void clearSessions();

}
