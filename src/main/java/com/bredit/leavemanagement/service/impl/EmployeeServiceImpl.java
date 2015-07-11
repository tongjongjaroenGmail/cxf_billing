/**
 * 
 */
package com.bredit.leavemanagement.service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bredit.leavemanagement.bean.paging.EmployeePaging;
import com.bredit.leavemanagement.dao.EmployeeDao;
import com.bredit.leavemanagement.model.Department;
import com.bredit.leavemanagement.model.Employee;
import com.bredit.leavemanagement.model.EmployeeStatus;
import com.bredit.leavemanagement.model.LeaveProfile;
import com.bredit.leavemanagement.model.Role;
import com.bredit.leavemanagement.service.EmployeeService;
import com.bredit.leavemanagement.service.LeaveProfileService;

/**
 * @author KaweepattC
 * 
 */
@Component
public class EmployeeServiceImpl extends ModelBasedServiceImpl<EmployeeDao, Employee, Integer> implements EmployeeService
{
    private EmployeeDao employeeDao;

    @Autowired
    private LeaveProfileService leaveProfileService;

    /**
     * 
     * @param entityClass
     */
    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao)
    {
        super(employeeDao);
        this.employeeDao = employeeDao;
    }

    @Override
    public Integer save(Employee employee)
    {
        employee.setId(super.save(employee));

        // Create new leaveProfile
        String loginEmployeeCode = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee loginEmployee = this.findByCode(loginEmployeeCode);

        Calendar now = new GregorianCalendar();

        LeaveProfile leaveProfile = new LeaveProfile();
        leaveProfile.setCarryForward(0f);
        leaveProfile.setCreateBy(loginEmployee);
        leaveProfile.setCreateDate(now.getTime());
        leaveProfile.setLeaveAvailable(14f); // Default value
        leaveProfile.setUpdateBy(null);
        leaveProfile.setUpdateDate(null);
        leaveProfile.setYear(now.get(Calendar.YEAR));
        leaveProfile.setEmployee(employee);
        leaveProfile.setId(leaveProfileService.save(leaveProfile));

        return employee.getId();
    }

    public Employee findByCode(String code)
    {
        return employeeDao.findByCode(code);
    }

	@Override
	public Employee findByFirstnameAndLastname(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return employeeDao.findByFirstnameAndLastname(firstname, lastname);
	}

	@Override
	public List<Employee> findAllStatusNotResign() {
		return employeeDao.findAllStatusNotResign();
	}
	
	@Override
	public EmployeePaging searchPagingByFullNameAndDepartmentIdAndStatus(String fullName,List<Department> departmentIds , List<EmployeeStatus> statuses , Integer start , Integer length){
		return employeeDao.searchPagingByFullNameAndDepartmentIdAndStatus(fullName, departmentIds, statuses, start, length);
	}

	@Override
	public List<Employee> searchByRoleAndDepartmentAndStatuses(Role role, Department department,
			List<EmployeeStatus> employeeStatus) {
		return employeeDao.searchByRoleAndDepartmentAndStatuses(role, department, employeeStatus);
	}
}