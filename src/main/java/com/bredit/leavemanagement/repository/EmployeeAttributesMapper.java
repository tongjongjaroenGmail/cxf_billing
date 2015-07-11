/**
 * 
 */
package com.bredit.leavemanagement.repository;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.AttributesMapper;

import com.bredit.leavemanagement.model.Department;
import com.bredit.leavemanagement.model.Employee;
import com.bredit.leavemanagement.model.EmployeeStatus;

/**
 * @author KaweepattC
 * 
 */
public class EmployeeAttributesMapper implements AttributesMapper
{
    private final Log logger = LogFactory.getLog(this.getClass());

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ldap.core.AttributesMapper#mapFromAttributes(javax.naming.directory.Attributes)
     */
    @Override
    public Employee mapFromAttributes(Attributes attrs) throws NamingException
    {
        Employee employee = new Employee();
        employee.setCode((String) attrs.get("sAMAccountName").get());

        if (attrs.get("displayname") != null)
        {
            String displayName = (String) attrs.get("displayname").get();

            if (attrs.get("givenname") != null)
            {
                employee.setFirstname((String) attrs.get("givenname").get());
                String lastname = displayName.replaceAll(employee.getFirstname(), "");
                lastname = lastname.trim();
                employee.setLastname(lastname);
            }
        }
        else
        {
            logger.info("User code: " + employee.getCode() + " does not have displayname");
        }

        if (attrs.get("department") != null)
        {
            Department department = new Department();
            department.setName((String) attrs.get("department").get());
            employee.setDepartment(department);
        }
        else
        {
            logger.info("User code: " + employee.getCode() + " does not have department");
        }

        // TODO: we may change to use bit operation later, refer to http://support.microsoft.com/kb/305144
        if (attrs.get("userAccountControl") != null)
        {
            int userAccountControl = Integer.parseInt((String) attrs.get("userAccountControl").get());

            if (userAccountControl == 512)
            {
                // 0x0200 = 512 = normal account
                employee.setStatus(EmployeeStatus.ACTIVE);
            }
            else if (userAccountControl == 514)
            {
                // 0x0202 = 512 + 2 = account is disabled
                employee.setStatus(EmployeeStatus.RESIGN);
            }
            else
            {
                logger.info("User code: " + employee.getCode() + " has another userAccountControl: " + attrs.get("userAccountControl"));
                employee.setStatus(EmployeeStatus.ACTIVE);
            }
        }

        return employee;
    }

}
