/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.List;

import com.metasoft.claim.bean.paging.EmployeePaging;
import com.metasoft.claim.model.Department;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.EmployeeStatus;
import com.metasoft.claim.model.Role;

/**
 * @author 
 * 
 */
public interface EmployeeDao extends AbstractDao<Employee, Integer>
{

    /**
     * 
     * @param code
     * @return
     */
    Employee findByCode(String code);
    
    Employee findByFirstnameAndLastname(String firstname,String lastname);
    
    public List<Employee> findAllStatusNotResign();
    
    public EmployeePaging searchPagingByFullNameAndDepartmentIdAndStatus(String fullName,List<Department> departmentIds , List<EmployeeStatus> statuses , Integer start , Integer length);
    
    public List<Employee> searchByRoleAndDepartmentAndStatuses(Role role, Department department, List<EmployeeStatus> employeeStatus);
}
