
package com.cisco.cstg.autotools.service;

import com.cisco.cstg.autotools.domain.appdb.User;

public interface UserStoreService {
    public void store(User user, String password);
}
