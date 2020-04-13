
package com.onecloud.autotools.dao;

import com.onecloud.autotools.domain.appdb.User;
import org.springframework.dao.DataAccessException;

public interface UserDao extends IdentifiableEntityDao<User> {
    public User getByEmail(String email) throws DataAccessException;
}
