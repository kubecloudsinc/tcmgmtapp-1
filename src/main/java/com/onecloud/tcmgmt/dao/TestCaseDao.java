
package com.onecloud.tcmgmt.dao;

import com.onecloud.tcmgmt.domain.appdb.TestCase;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface TestCaseDao extends IdentifiableEntityDao<TestCase> {

    public TestCase getByName(String testName) throws DataAccessException;

    public List<TestCase> getByAuthor(Long authorId) throws DataAccessException;

    public List<TestCase> getNthPage(int pageNumber) throws DataAccessException;

    public int getTotalPages() throws DataAccessException;

}
