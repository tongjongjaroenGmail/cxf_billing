/**
 * 
 */
package com.metasoft.claim.service;

import java.util.List;

import com.metasoft.claim.bean.paging.EmployeePaging;
import com.metasoft.claim.dao.EmployeeDao;
import com.metasoft.claim.model.Department;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.EmployeeStatus;
import com.metasoft.claim.model.Role;

/**
 * @author 
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
