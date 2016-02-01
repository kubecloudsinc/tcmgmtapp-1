
package com.cisco.cstg.autotools.service;

import com.cisco.cstg.autotools.domain.appdb.User;

public interface UserContextService {
    public User getUserFromContext();

    public void addUserToContext(User user, String password);
}
