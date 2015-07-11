/**
 * 
 */
package com.metasoft.claim.dao;

import com.metasoft.claim.model.WorkingPolicy;

public interface WorkingPolicyDao extends AbstractDao<WorkingPolicy, Integer>
{

    WorkingPolicy findByName(String name);

}
