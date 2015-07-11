/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.model.Leave;
import com.metasoft.claim.model.LeaveRequestStatus;

/**
 * @author 
 * 
 */
@Repository("leaveDao")
@Transactional
public class LeaveDaoImpl extends AbstractDaoImpl<Leave, Integer> implements LeaveDao
{
    public LeaveDaoImpl()
    {
        super(Leave.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Leave> searchByEmployeeIdAndDateAndStatuses(Integer employeeId, Date startDate, Date endDate,List<LeaveRequestStatus> statuses)
    {
        return getCurrentSession().createCriteria(entityClass)
        		.createAlias("leaveRequest.requestEmployee", "requestEmployee")
        		.createAlias("leaveRequest", "leaveRequest")
                .add(Restrictions.eq("requestEmployee.id", employeeId))
                .add(Restrictions.in("leaveRequest.status", statuses))
                .add(Restrictions.between("date", startDate, endDate))
                .addOrder(Order.asc("leaveRequest.id"))
                .addOrder(Order.asc("date"))
                .addOrder(Order.desc("am")).list();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Leave> searchByEmployeeIdAndDateAndStatusesAndLeaveTypes(Integer employeeId, Date startDate, Date endDate,List<LeaveRequestStatus> statuses,List<Integer> leaveTypeIds)
    {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.createAlias("leaveRequest", "leaveRequest");
        criteria.add(Restrictions.eq("leaveRequest.requestEmployee.id", employeeId));
        criteria.add(Restrictions.in("leaveRequest.leaveType.id", leaveTypeIds));
        criteria.add(Restrictions.in("leaveRequest.status", statuses));     
        criteria.add(Restrictions.between("date", startDate, endDate));
       
        return criteria.list();
    }

}
