/**
 * 
 */
package com.metasoft.claim.service;

import java.util.List;

import com.metasoft.claim.dao.DepartmentDao;
import com.metasoft.claim.model.Department;

/**
 * @author 
 * 
 */
public interface DepartmentService extends ModelBasedService<DepartmentDao, Department, Integer>
{

    Department findByName(String name);
    List<Department> searchAllInEmployee();
}
