
package com.cisco.cstg.autotools.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.cstg.autotools.domain.factdb.PamCustomerDelta;

@Repository
public class HibernatePamCustomerDeltaDao extends AbstractHibernateAppDao<PamCustomerDelta> 
											implements PamCustomerDeltaDao {
	
	@Transactional(readOnly = true, value="factdbTxManager")
    @SuppressWarnings("unchecked")
    public List<PamCustomerDelta> getPamCustomerDeltaByCCOId(String ccoId) {
		
        List<PamCustomerDelta> pamCustomers =
        		this.getFactdbSession()
        				.createCriteria(PamCustomerDelta.class, "pamCustomer")
        				.add(Restrictions.eq("pamCustomer.ccoId", ccoId))
        				.list();
              
    	return pamCustomers;
    	
    }

}
