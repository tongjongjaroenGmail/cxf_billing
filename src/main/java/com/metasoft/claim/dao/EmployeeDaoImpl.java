/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.bean.paging.EmployeePaging;
import com.metasoft.claim.model.Department;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.EmployeeStatus;
import com.metasoft.claim.model.Role;

/**
 * @author 
 * 
 */
@Repository("employeeDao")
@Transactional
public class EmployeeDaoImpl extends AbstractDaoImpl<Employee, Integer> implements EmployeeDao {
	public EmployeeDaoImpl() {
		super(Employee.class);
	}

	@Override
	public Employee findByCode(String code) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("code", code));
		return (Employee) criteria.uniqueResult();
	}

	@Override
	public Employee findByFirstnameAndLastname(String firstname, String lastname) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		if (StringUtils.isNotEmpty(firstname)) {
			criteria.add(Restrictions.like("firstname", "%" + firstname + "%"));
		}
		if (StringUtils.isNotEmpty(lastname)) {
			criteria.add(Restrictions.like("lastname", "%" + lastname + "%"));
		}
		return (Employee) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAllStatusNotResign() {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.ne("status", EmployeeStatus.RESIGN));
		criteria.addOrder(Order.asc("fullName"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EmployeePaging searchPagingByFullNameAndDepartmentIdAndStatus(String fullName,
			List<Department> departmentIds, List<EmployeeStatus> statuses, Integer start, Integer length) {
		EmployeePaging employeePaging = new EmployeePaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
		if (!statuses.isEmpty()) {
			criteriaRecordsTotal.add(Restrictions.in("status", statuses));
		}
		criteriaRecordsTotal.setProjection(Projections.rowCount());
		employeePaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		if (StringUtils.isNotBlank(fullName)) {
			criteriaCount.add(Restrictions.ilike("fullName", "%" + fullName + "%"));
		}
		if (!departmentIds.isEmpty()) {
			criteriaCount.add(Restrictions.in("department", departmentIds));
		} else {
			criteriaCount.add(Restrictions.isNull("department"));
		}
		if (!statuses.isEmpty()) {
			criteriaCount.add(Restrictions.in("status", statuses));
		}
		criteriaCount.setProjection(Projections.rowCount());
		employeePaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (employeePaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			if (StringUtils.isNotBlank(fullName)) {
				criteria.add(Restrictions.ilike("fullName", "%" + fullName + "%"));
			}
			if (!departmentIds.isEmpty()) {
				criteria.add(Restrictions.in("department", departmentIds));
			} else {
				criteriaCount.add(Restrictions.isNull("department"));
			}
			if (!statuses.isEmpty()) {
				criteria.add(Restrictions.in("status", statuses));
			}

			criteria.addOrder(Order.asc("fullName"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			employeePaging.setData(criteria.list());
		}
		return employeePaging;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> searchByRoleAndDepartmentAndStatuses(Role role, Department department,
			List<EmployeeStatus> employeeStatus) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("role", role));
		criteria.add(Restrictions.eq("department", department));
		criteria.add(Restrictions.in("status", employeeStatus));
		criteria.addOrder(Order.asc("fullName"));
		return criteria.list();
	}
}
