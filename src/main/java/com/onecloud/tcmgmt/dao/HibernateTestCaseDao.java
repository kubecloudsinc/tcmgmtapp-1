
package com.onecloud.tcmgmt.dao;

import com.onecloud.tcmgmt.domain.appdb.TestCase;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HibernateTestCaseDao extends AbstractHibernateDao<TestCase> implements TestCaseDao {

    @Transactional(readOnly = true, value="txManager")
    public List<TestCase> getAll() throws DataAccessException {
        return super.findAll("from TestCase order by id");
    }

    @Transactional(readOnly = true, value="txManager")
    public TestCase getByName(String testName) throws DataAccessException {
        return super.findOne("from TestCase where testName=?", testName);
    }
}
