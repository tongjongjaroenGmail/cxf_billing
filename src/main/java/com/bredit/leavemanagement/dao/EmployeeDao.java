/**
 * 
 */
package com.bredit.leavemanagement.dao;

import java.util.List;

import com.bredit.leavemanagement.bean.paging.EmployeePaging;
import com.bredit.leavemanagement.model.Department;
import com.bredit.leavemanagement.model.Employee;
import com.bredit.leavemanagement.model.EmployeeStatus;
import com.bredit.leavemanagement.model.Role;

/**
 * @author KaweepattC
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
