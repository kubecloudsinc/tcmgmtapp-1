
package com.onecloud.tcmgmt.dao;

import com.onecloud.tcmgmt.domain.appdb.User;
import org.springframework.dao.DataAccessException;

public interface UserDao extends IdentifiableEntityDao<User> {
    public User getByEmail(String email) throws DataAccessException;
}
