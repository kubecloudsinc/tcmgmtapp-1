
package com.onecloud.tcmgmt.dao;

import com.onecloud.tcmgmt.domain.appdb.TestCase;
import org.hibernate.Query;
import org.junit.Test;
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

    @Transactional(readOnly = true, value="txManager")
    public List<TestCase> getNthPage(int pageNumber) throws DataAccessException{
        int pageSize = 5;

        Query selectQuery = getSession().createQuery("From TestCase order by id");
        selectQuery.setFirstResult((pageNumber - 1) * pageSize);
        selectQuery.setMaxResults(pageSize);
        List<TestCase> pageResults = selectQuery.list();

        return pageResults;
    }

    @Transactional(readOnly = true, value="txManager")
    public int getTotalPages() throws DataAccessException{
        int pageSize = 5;
        String countQ = "Select count (t.id) from TestCase t";
        Query countQuery = getSession().createQuery(countQ);
        Long countResults = (Long) countQuery.uniqueResult();
        int lastPageNumber = (int) (Math.ceil(countResults / pageSize));
        return lastPageNumber;
    }

}
