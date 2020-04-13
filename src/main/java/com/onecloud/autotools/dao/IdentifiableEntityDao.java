
package com.onecloud.autotools.dao;

import java.util.List;

import com.onecloud.autotools.domain.appdb.IdentifiableEntity;
import org.springframework.dao.DataAccessException;

import com.onecloud.autotools.domain.appdb.IdentifiableEntity;

public interface IdentifiableEntityDao<E extends IdentifiableEntity> {
    public E getById(Long id) throws DataAccessException;

    public List<E> getAll() throws DataAccessException;

    public void save(E entity) throws DataAccessException;

    public void delete(E entity) throws DataAccessException;
    
    public void clearSessions();
}
