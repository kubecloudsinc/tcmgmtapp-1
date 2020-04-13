
package com.onecloud.autotools.web;

import com.onecloud.autotools.domain.appdb.IdentifiableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.onecloud.autotools.dao.IdentifiableEntityDao;
import com.onecloud.autotools.domain.appdb.IdentifiableEntity;

public abstract class AbstractIdentifiableEntityController extends ParameterizableViewController {
    protected IdentifiableEntityDao<IdentifiableEntity> dao;

    // mask the super's Apache Commons Logging by SLF4J
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Required
    public void setDao(IdentifiableEntityDao<IdentifiableEntity> dao) {
        this.dao = dao;
    }
}
