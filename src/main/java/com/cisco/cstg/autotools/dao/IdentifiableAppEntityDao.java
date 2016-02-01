
package com.cisco.cstg.autotools.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface IdentifiableAppEntityDao<E> {
    public E getByIdInFactdb(Long id) throws DataAccessException;

    public List<E> getAllInFactdb() throws DataAccessException;

    public void saveInFactDB(E entity) throws DataAccessException;

    public void deleteInFactdb(E entity) throws DataAccessException;
    
    public void clearSessions();
}
