/**
 * 
 */
package com.bredit.leavemanagement.service;

import java.util.List;

import com.bredit.leavemanagement.dao.DepartmentDao;
import com.bredit.leavemanagement.model.Department;

/**
 * @author KaweepattC
 * 
 */
public interface DepartmentService extends ModelBasedService<DepartmentDao, Department, Integer>
{

    Department findByName(String name);
    List<Department> searchAllInEmployee();
}
