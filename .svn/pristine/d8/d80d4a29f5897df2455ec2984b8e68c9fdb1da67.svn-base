
package com.cisco.cstg.autotools.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.cisco.cstg.autotools.domain.appdb.IdentifiableEntity;

public interface IdentifiableEntityDao<E extends IdentifiableEntity> {
    public E getById(Long id) throws DataAccessException;

    public List<E> getAll() throws DataAccessException;

    public void save(E entity) throws DataAccessException;

    public void delete(E entity) throws DataAccessException;
    
    public void clearSessions();
}
