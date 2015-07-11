/**
 * 
 */
package com.bredit.leavemanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KaweepattC
 * 
 */
public class LdapEmployeeSynchronizationResult
{
    private List<Employee> addedEmployees = new ArrayList<Employee>();

    private List<Employee> oldUpdatedEmployees = new ArrayList<Employee>();

    private List<Employee> newUpdatedEmployees = new ArrayList<Employee>();

    private List<Employee> unchangedEmployees = new ArrayList<Employee>();

    public List<Employee> getAddedEmployees()
    {
        return addedEmployees;
    }

    public void setAddedEmployees(List<Employee> addedEmployees)
    {
        this.addedEmployees = addedEmployees;
    }

    public void addAddedEmployees(Employee addedEmployee)
    {
        addedEmployees.add(addedEmployee);
    }

    public List<Employee> getOldUpdatedEmployees()
    {
        return oldUpdatedEmployees;
    }

    public void setOldUpdatedEmployees(List<Employee> oldUpdatedEmployees)
    {
        this.oldUpdatedEmployees = oldUpdatedEmployees;
    }
    
    public void addOldUpdatedEmployees(Employee oldUpdatedEmployee)
    {
        oldUpdatedEmployees.add(oldUpdatedEmployee);
    }

    public List<Employee> getNewUpdatedEmployees()
    {
        return newUpdatedEmployees;
    }

    public void setNewUpdatedEmployees(List<Employee> newUpdatedEmployees)
    {
        this.newUpdatedEmployees = newUpdatedEmployees;
    }
    
    public void addNewUpdatedEmployees(Employee newUpdatedEmployee)
    {
        newUpdatedEmployees.add(newUpdatedEmployee);
    }

    public List<Employee> getUnchangedEmployees()
    {
        return unchangedEmployees;
    }

    public void setUnchangedEmployees(List<Employee> unchangedEmployees)
    {
        this.unchangedEmployees = unchangedEmployees;
    }
    
    public void addUnchangedEmployee(Employee unchangedEmployee)
    {
        unchangedEmployees.add(unchangedEmployee);
    }

}
