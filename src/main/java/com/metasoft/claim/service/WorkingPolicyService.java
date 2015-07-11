/**
 * 
 */
package com.metasoft.claim.service;

import com.metasoft.claim.dao.WorkingPolicyDao;
import com.metasoft.claim.model.WorkingPolicy;

public interface WorkingPolicyService extends ModelBasedService<WorkingPolicyDao, WorkingPolicy, Integer>
{

    WorkingPolicy findByName(String name);

}
