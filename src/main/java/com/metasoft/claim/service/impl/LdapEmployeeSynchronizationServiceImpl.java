/**
 * 
 */
package com.metasoft.claim.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.model.Department;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.LdapEmployeeSynchronizationResult;
import com.metasoft.claim.model.Role;
import com.metasoft.claim.model.WorkingPolicy;
import com.metasoft.claim.repository.PersonRepository;
import com.metasoft.claim.service.DepartmentService;
import com.metasoft.claim.service.EmployeeService;
import com.metasoft.claim.service.LdapEmployeeSynchronizationService;
import com.metasoft.claim.service.LeaveProfileService;

/**
 * @author 
 * 
 */
@Component
public class LdapEmployeeSynchronizationServiceImpl extends BaseServiceImpl implements LdapEmployeeSynchronizationService
{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveProfileService leaveProfileService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    @Transactional
    public LdapEmployeeSynchronizationResult sync(String code)
    {
        LdapEmployeeSynchronizationResult result = new LdapEmployeeSynchronizationResult();

        Employee ldapEmployee = personRepository.getPersonAsEmployee(code);
        if (ldapEmployee.getDepartment() == null)
        {
            throw new RuntimeException("The user in LDAP with no department is not allowed to use.");
        }

        Employee employee = employeeService.findByCode(ldapEmployee.getCode());
        Employee admin = employeeService.findById(162);
        
        // Add department if department is not exist
        String ldapDepartmentName = ldapEmployee.getDepartment().getName();
        Department department = departmentService.findByName(ldapDepartmentName);
        if (department == null)
        {
            department = new Department();
            department.setName(ldapDepartmentName);
            department.setId(departmentService.save(department));
        }

        // Add new employee if the employee is not exist
        if (employee == null)
        {
            employee = new Employee();
            employee.setCode(ldapEmployee.getCode());
            employee.setFirstname(ldapEmployee.getFirstname());
            employee.setLastname(ldapEmployee.getLastname());
            employee.setRole(new Role(1));
            employee.setWorkingPolicy(new WorkingPolicy(1));
            employee.setDepartment(department);
            employee.setStatus(ldapEmployee.getStatus());

            employee.setCreateBy(admin);
            employee.setCreateDate(new Date());
            employeeService.save(employee);

            result.addAddedEmployees(employee);
        }
        else
        {
            // Update employee if the employee is already exists
            boolean isUpdateRequired = false;
            Employee oldEmployee = new Employee();
            try
            {
                BeanUtils.copyProperties(oldEmployee, employee);
            }
            catch (Exception ignore)
            {
            }

            if (!StringUtils.equals(ldapEmployee.getFirstname(), employee.getFirstname()))
            {
                isUpdateRequired = true;
                employee.setFirstname(ldapEmployee.getFirstname());
            }

            if (!StringUtils.equals(ldapEmployee.getLastname(), employee.getLastname()))
            {
                isUpdateRequired = true;
                employee.setLastname(ldapEmployee.getLastname());
            }

            if (!StringUtils.equals(department.getName(), employee.getDepartment().getName()))
            {
                isUpdateRequired = true;
                employee.setDepartment(department);
            }

            if (ldapEmployee.getStatus() != employee.getStatus())
            {
                isUpdateRequired = true;
                employee.setStatus(ldapEmployee.getStatus());
            }

            if (isUpdateRequired)
            {
            	employee.setUpdateBy(admin);
                employee.setUpdateDate(new Date());
                employeeService.saveOrUpdate(employee);
                employee = employeeService.findById(employee.getId());
                result.addOldUpdatedEmployees(oldEmployee);
                result.addNewUpdatedEmployees(employee);
            }
            else
            {
                result.addUnchangedEmployee(oldEmployee);
            }
        }

        return result;
    }

    @Override
    @Transactional
    public LdapEmployeeSynchronizationResult syncAll()
    {
        List<Employee> ldapEmployees = personRepository.getAllPersonsAsEmployee();

        LdapEmployeeSynchronizationResult result = new LdapEmployeeSynchronizationResult();

        for (Employee ldapEmployee : ldapEmployees)
        {
            // Skip if department is not set
            if (ldapEmployee.getDepartment() == null)
            {
                continue;
            }

            LdapEmployeeSynchronizationResult smallResult = sync(ldapEmployee.getCode());
            result.getAddedEmployees().addAll(smallResult.getAddedEmployees());
            result.getOldUpdatedEmployees().addAll(smallResult.getOldUpdatedEmployees());
            result.getNewUpdatedEmployees().addAll(smallResult.getNewUpdatedEmployees());
            result.getUnchangedEmployees().addAll(smallResult.getUnchangedEmployees());
        }

        return result;
    }
}
