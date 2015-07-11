/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.model.LeaveType;

/**
 * @author 
 * 
 */
@Repository("LeaveTypeDao")
@Transactional
public class LeaveTypeDaoImpl extends AbstractDaoImpl<LeaveType, Integer> implements LeaveTypeDao
{
    public LeaveTypeDaoImpl()
    {
        super(LeaveType.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<LeaveType> findAll()
    {
        return getCurrentSession().createCriteria(entityClass)
        		.addOrder(Order.asc("orderNumber"))
        		.list();
    }
}
