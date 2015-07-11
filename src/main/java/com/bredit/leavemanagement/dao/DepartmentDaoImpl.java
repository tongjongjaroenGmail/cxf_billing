/**
 * 
 */
package com.bredit.leavemanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bredit.leavemanagement.model.Department;
import com.bredit.leavemanagement.model.Employee;
import com.bredit.leavemanagement.model.EmployeeStatus;

@Repository("DepartmentDao")
@Transactional
public class DepartmentDaoImpl extends AbstractDaoImpl<Department, Integer> implements DepartmentDao
{
    public DepartmentDaoImpl()
    {
        super(Department.class);
    }

    @Override
    public Department findByName(String name)
    {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq("name", name));
        return (Department) criteria.uniqueResult();
    }

	@Override
	public List<Department> searchAllInEmployee() {
		DetachedCriteria subCriteria = DetachedCriteria.forClass(Employee.class);
		subCriteria.createAlias("department", "department");
		subCriteria.add(Restrictions.ne("status", EmployeeStatus.RESIGN));
		subCriteria.setProjection(Projections.distinct(Projections.property("department.id")));

		Criteria criteria = getCurrentSession().createCriteria(entityClass, "leaveRequest");
		criteria.add(Subqueries.propertyIn("id", subCriteria));
		criteria.addOrder(Order.asc("name"));

        return criteria.list();
	}
    
    
}
