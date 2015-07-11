/**
 * 
 */
package com.bredit.leavemanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bredit.leavemanagement.dao.WorkingPolicyDao;
import com.bredit.leavemanagement.model.WorkingPolicy;
import com.bredit.leavemanagement.service.WorkingPolicyService;

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
