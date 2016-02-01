
package com.cisco.cstg.autotools.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.cstg.autotools.domain.appdb.TestSuite;

@Repository
public class HibernateTestSuiteDao extends AbstractHibernateDao<TestSuite> implements TestSuiteDao {

    @Transactional(readOnly = true, value="txManager")
    public List<TestSuite> getAll() throws DataAccessException {
        return super.findAll("from TestSuite order by id");
    }

    @Override
    @Transactional(readOnly = false, value="txManager")
    public void save(TestSuite testSuite) throws DataAccessException {
    	logger.debug("INSIDE THE save Test Suite "+ new Date());
        super.save(testSuite);
    }
}