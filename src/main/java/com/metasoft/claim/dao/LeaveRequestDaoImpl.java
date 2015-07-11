/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.bean.query.ViewLeaveQuery;
import com.metasoft.claim.model.EmployeeStatus;
import com.metasoft.claim.model.Leave;
import com.metasoft.claim.model.LeaveRequest;
import com.metasoft.claim.model.LeaveRequestStatus;

/**
 * @author 
 * 
 */
@Repository("leaveRequestDao")
@Transactional
public class LeaveRequestDaoImpl extends AbstractDaoImpl<LeaveRequest, Integer> implements LeaveRequestDao {
	public LeaveRequestDaoImpl() {
		super(LeaveRequest.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveRequest> findByEmployeeId(Integer employeeId) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("requestEmployee.id", employeeId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveRequest> searchByEmployeeNameAndLeaveTypeAndLeaveDateAndLeaveStatus(String employeeName,
			Integer leaveTypeId, Date leaveDateStart, Date leaveDateEnd, Integer leaveStatus) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.createAlias("requestEmployee", "requestEmployee");
		criteria.add(Restrictions.ne("requestEmployee.status", EmployeeStatus.RESIGN));
		if (!StringUtils.trimToEmpty(employeeName).isEmpty()) {
			criteria.add(Restrictions.ilike("requestEmployee.fullName", employeeName + "%"));
		}
		if (leaveTypeId != null) {
			criteria.createAlias("leaveType.parent", "leaveTypeParent", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.or(Restrictions.eq("leaveType.id", leaveTypeId),
					Restrictions.eq("leaveTypeParent.id", leaveTypeId)));
		}
		if (leaveDateStart != null) {
			criteria.createAlias("leaves", "leave");
			criteria.add(Restrictions.between("leave.date", leaveDateStart, leaveDateEnd));
		}
		if (leaveStatus != null) {
			criteria.add(Restrictions.eq("status", LeaveRequestStatus.getById(leaveStatus)));
		}
		criteria.addOrder(Order.asc("requestEmployee.firstname"));
		criteria.addOrder(Order.asc("requestEmployee.lastname"));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewLeaveQuery> searchViewLeave(Date startDate, Date endDate,
			List<LeaveRequestStatus> leaveRequestStatuses, List<EmployeeStatus> employeeStatuses) {
		DetachedCriteria subCriteria = DetachedCriteria.forClass(Leave.class);
		subCriteria.createAlias("leaveRequest", "leaveRequest");
		subCriteria.add(Restrictions.between("date", startDate, endDate));
		subCriteria.add(Restrictions.in("leaveRequest.status", leaveRequestStatuses));
		subCriteria.setProjection(Projections.distinct(Projections.property("leaveRequest.id")));

		Criteria criteria = getCurrentSession().createCriteria(entityClass, "leaveRequest");
		criteria.createAlias("requestEmployee", "employee", JoinType.RIGHT_OUTER_JOIN,
				Subqueries.propertyIn("leaveRequest.id", subCriteria));
		criteria.add(Restrictions.in("employee.status", employeeStatuses));
		criteria.addOrder(Order.asc("employee.firstname"));
		criteria.addOrder(Order.asc("employee.lastname"));
		criteria.addOrder(Order.asc("leaveRequest.id"));
		criteria.setResultTransformer(Transformers.aliasToBean(ViewLeaveQuery.class));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveRequest> searchByEmployeeIdAndDateAndStatuses(Integer employeeId, Date startDate, Date endDate,
			List<LeaveRequestStatus> leaveRequestStatuses) {
		DetachedCriteria subCriteria = DetachedCriteria.forClass(Leave.class);
		subCriteria.createAlias("leaveRequest", "leaveRequest");
		subCriteria.add(Restrictions.between("date", startDate, endDate));
		subCriteria.add(Restrictions.in("leaveRequest.status", leaveRequestStatuses));
		subCriteria.setProjection(Projections.distinct(Projections.property("leaveRequest.id")));

		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Subqueries.propertyIn("id", subCriteria));
		criteria.add(Restrictions.eq("requestEmployee.id", employeeId));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
}
