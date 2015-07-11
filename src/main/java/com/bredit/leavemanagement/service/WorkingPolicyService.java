/**
 * 
 */
package com.bredit.leavemanagement.service;

import com.bredit.leavemanagement.dao.WorkingPolicyDao;
import com.bredit.leavemanagement.model.WorkingPolicy;

public interface WorkingPolicyService extends ModelBasedService<WorkingPolicyDao, WorkingPolicy, Integer>
{

    WorkingPolicy findByName(String name);

}
