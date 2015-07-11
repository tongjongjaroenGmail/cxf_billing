/**
 * 
 */
package com.bredit.leavemanagement.repository;

import java.util.List;

import com.bredit.leavemanagement.model.Employee;

/**
 * @author KaweepattC
 * 
 */
public interface PersonRepository
{
    List<String> getAllPersonNames();

    List<Employee> getAllPersonsAsEmployee();

    Employee getPersonAsEmployee(String code);
}
