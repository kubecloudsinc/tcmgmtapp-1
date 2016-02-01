
package com.cisco.cstg.autotools.dao;

import org.springframework.dao.DataAccessException;

import com.cisco.cstg.autotools.domain.appdb.User;

public interface UserDao extends IdentifiableEntityDao<User> {
    public User getByEmail(String email) throws DataAccessException;
}
