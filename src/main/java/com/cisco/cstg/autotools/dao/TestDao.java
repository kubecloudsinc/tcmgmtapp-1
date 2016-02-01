
package com.cisco.cstg.autotools.dao;

import org.springframework.dao.DataAccessException;

import com.cisco.cstg.autotools.domain.appdb.Test;

public interface TestDao extends IdentifiableEntityDao<Test> {
    public Test getByTestId(Long testId) throws DataAccessException;
}
