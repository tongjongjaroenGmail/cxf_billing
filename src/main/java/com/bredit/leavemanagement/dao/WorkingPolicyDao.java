/**
 * 
 */
package com.bredit.leavemanagement.dao;

import com.bredit.leavemanagement.model.WorkingPolicy;

public interface WorkingPolicyDao extends AbstractDao<WorkingPolicy, Integer>
{

    WorkingPolicy findByName(String name);

}
