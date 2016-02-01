
package com.cisco.cstg.autotools.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cisco.cstg.autotools.domain.factdb.Equipment;

public class HibernateEquipmentDao extends AbstractHibernateAppDao<Equipment> implements EquipmentDao {
	
    @Override
	@Transactional(readOnly = true, value="factdbTxManager")
    @SuppressWarnings("unchecked")
    public List<Equipment> getAllInFactdb() {
        List<Equipment> equipments =
        		this.getFactdbSession().createCriteria(Equipment.class, "equipment").setMaxResults(10).list();
              
    	return equipments;
    	
    }

}
