/**
 * 
 */
package com.metasoft.claim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metasoft.claim.dao.DepartmentDao;
import com.metasoft.claim.model.Department;
import com.metasoft.claim.service.DepartmentService;

/**
 * @author 
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
