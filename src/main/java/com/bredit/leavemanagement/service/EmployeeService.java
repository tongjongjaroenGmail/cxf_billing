/**
 * 
 */
package com.bredit.leavemanagement.service;

import java.util.List;

import com.bredit.leavemanagement.bean.paging.EmployeePaging;
import com.bredit.leavemanagement.dao.EmployeeDao;
import com.bredit.leavemanagement.model.Department;
import com.bredit.leavemanagement.model.Employee;
import com.bredit.leavemanagement.model.EmployeeStatus;
import com.bredit.leavemanagement.model.Role;

/**
 * @author KaweepattC
 * @param <T>
 * @param <T>
 * @param <E>
 * 
 */
public interface EmployeeService extends ModelBasedService<EmployeeDao, Employee, Integer>
{
    public Integer save(Employee employee);
    
	public Employee findByCode(String code);
	
	public Employee findByFirstnameAndLastname(String firstname,String lastname);
	
	public List<Employee> findAllStatusNotResign();
	
	public EmployeePaging searchPagingByFullNameAndDepartmentIdAndStatus(String fullName,List<Department> departmentIds , List<EmployeeStatus> statuses , Integer start , Integer length);

	public List<Employee> searchByRoleAndDepartmentAndStatuses(Role role, Department department,
			List<EmployeeStatus> employeeStatuses) ;
}
