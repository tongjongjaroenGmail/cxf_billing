/**
 * 
 */
package com.bredit.leavemanagement.dao;

import java.util.List;

import com.bredit.leavemanagement.model.Department;

public interface DepartmentDao extends AbstractDao<Department, Integer>
{

    Department findByName(String name);
    List<Department> searchAllInEmployee();
}
