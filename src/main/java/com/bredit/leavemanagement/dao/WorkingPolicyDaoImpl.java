/**
 * 
 */
package com.bredit.leavemanagement.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bredit.leavemanagement.model.WorkingPolicy;

@Repository("WorkingPolicyDao")
@Transactional
public class WorkingPolicyDaoImpl extends AbstractDaoImpl<WorkingPolicy, Integer> implements WorkingPolicyDao
{
    public WorkingPolicyDaoImpl()
    {
        super(WorkingPolicy.class);
    }

    @Override
    public WorkingPolicy findByName(String name)
    {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq("name", name));
        return (WorkingPolicy) criteria.uniqueResult();
    }
}
