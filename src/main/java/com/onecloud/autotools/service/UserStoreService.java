
package com.onecloud.autotools.service;

import com.onecloud.autotools.domain.appdb.User;

public interface UserStoreService {
    public void store(User user, String password);
}
