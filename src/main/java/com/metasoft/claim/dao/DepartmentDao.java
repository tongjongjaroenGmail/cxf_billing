/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.List;

import com.metasoft.claim.model.Department;

public interface DepartmentDao extends AbstractDao<Department, Integer>
{

    Department findByName(String name);
    List<Department> searchAllInEmployee();
}
