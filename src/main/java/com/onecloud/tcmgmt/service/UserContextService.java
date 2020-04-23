
package com.onecloud.tcmgmt.service;

import com.onecloud.tcmgmt.domain.appdb.User;

public interface UserContextService {
    public User getUserFromContext();

    public void addUserToContext(User user, String password);
}
