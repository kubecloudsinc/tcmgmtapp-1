
package com.onecloud.tcmgmt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.onecloud.tcmgmt.dao.UserDao;
import com.onecloud.tcmgmt.domain.appdb.User;

public class DefaultUserStoreService implements UserStoreService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao dao;

//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserStoreService(UserDao dao) {
        this.dao = dao;
        
    }

    @Transactional(readOnly = false, value="txManager")
    @Override
    public void store(User user, String password) {
        synchronized (user) {
            if (password != null) {
                user.setPasswordDigest(password);
//            	this.passwordEncoder.encode("pwd");
            }
            this.dao.save(user);
            logger.debug("Stored ", user);
        }
    }
}
