
package com.onecloud.autotools.service;

import com.onecloud.autotools.domain.appdb.User;

public interface UserContextService {
    public User getUserFromContext();

    public void addUserToContext(User user, String password);
}
