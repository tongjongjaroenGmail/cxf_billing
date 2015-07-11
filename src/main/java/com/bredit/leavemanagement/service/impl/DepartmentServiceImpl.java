/**
 * 
 */
package com.bredit.leavemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bredit.leavemanagement.dao.DepartmentDao;
import com.bredit.leavemanagement.model.Department;
import com.bredit.leavemanagement.service.DepartmentService;

/**
 * @author KaweepattC
 * 
 */
@Component
public class DepartmentServiceImpl extends ModelBasedServiceImpl<DepartmentDao, Department, Integer> implements DepartmentService
{
    private DepartmentDao departmentDao;

    /**
     * 
     * @param entityClass
     */
    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao)
    {
        super(departmentDao);
        this.departmentDao = departmentDao;
    }

    @Override
    public Department findByName(String name)
    {
        return departmentDao.findByName(name);
    }

	@Override
	public List<Department> searchAllInEmployee() {
		return departmentDao.searchAllInEmployee();
	}

}
