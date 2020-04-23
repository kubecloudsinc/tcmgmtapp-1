
package com.onecloud.tcmgmt.service;

import com.onecloud.tcmgmt.domain.appdb.User;

public interface UserStoreService {
    public void store(User user, String password);
}
