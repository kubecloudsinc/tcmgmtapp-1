
package com.cisco.cstg.autotools.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.cstg.autotools.domain.appdb.Schedule;

@Repository
public class HibernateScheduleDao extends AbstractHibernateDao<Schedule> implements ScheduleDao {

    @Transactional(readOnly = true, value="txManager")
    public List<Schedule> getAll() throws DataAccessException {
        return super.findAll("from Schedule order by id");
    }
    
    @Override
    @Transactional(readOnly = false, value="txManager")
    public void save(Schedule schedule) throws DataAccessException {
    	logger.debug("INSIDE THE save Test Status "+ new Date());
       	schedule.setUpdated(new Date());
        logger.debug("INSIDE UPDATE DATE "+ new Date());
        super.save(schedule);
        
    }
}