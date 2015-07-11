/**
 * 
 */
package com.metasoft.claim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metasoft.claim.dao.WorkingPolicyDao;
import com.metasoft.claim.model.WorkingPolicy;
import com.metasoft.claim.service.WorkingPolicyService;

@Component
public class WorkingPolicyServiceImpl extends ModelBasedServiceImpl<WorkingPolicyDao, WorkingPolicy, Integer> implements WorkingPolicyService
{
    private WorkingPolicyDao workingPolicyDao;

    /**
     * 
     * @param entityClass
     */
    @Autowired
    public WorkingPolicyServiceImpl(WorkingPolicyDao workingPolicyDao)
    {
        super(workingPolicyDao);
        this.workingPolicyDao = workingPolicyDao;
    }

    @Override
    public WorkingPolicy findByName(String name)
    {
        return workingPolicyDao.findByName(name);
    }

}
