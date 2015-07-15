/**
 * 
 */
package com.metasoft.claim.service.impl.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.claim.dao.standard.InsuranceDao;
import com.metasoft.claim.model.StdInsurance;
import com.metasoft.claim.service.impl.ModelBasedServiceImpl;
import com.metasoft.claim.service.standard.InsuranceService;

@Service("insuranceService")
public class InsuranceServiceImpl extends ModelBasedServiceImpl<InsuranceDao, StdInsurance, Integer> implements InsuranceService
{
    private InsuranceDao insuranceDao;
    /**
     * 
     * @param entityClass
     */
    @Autowired
    public InsuranceServiceImpl(InsuranceDao dao)
    {
        super(dao);
        this.insuranceDao = dao;
    }
	
	
}
