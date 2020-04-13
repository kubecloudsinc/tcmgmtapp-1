
package com.onecloud.autotools.dao;

import com.onecloud.autotools.domain.appdb.Region;
import com.onecloud.autotools.domain.appdb.User;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class HibernateRegionDao extends AbstractHibernateDao<Region> implements RegionDao {

    @Transactional(readOnly = true, value="txManager")
    public List<Region> getAll() throws DataAccessException {
        return super.findAll("from Region order by id");
    }

    @Override
    @Transactional(readOnly = false, value="txManager")
    public void save(Region region) throws DataAccessException {
        super.save(region);
    }
}
