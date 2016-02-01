
package com.cisco.cstg.autotools.dao;

import java.util.List;

import com.cisco.cstg.autotools.domain.factdb.PamCustomerDelta;

public interface PamCustomerDeltaDao extends IdentifiableAppEntityDao<PamCustomerDelta> {
    public List<PamCustomerDelta> getPamCustomerDeltaByCCOId(String ccoId);
}
