/**
 * 
 */
package com.metasoft.claim.repository;

import java.util.List;

import com.metasoft.claim.model.Employee;

/**
 * @author 
 * 
 */
public interface PersonRepository
{
    List<String> getAllPersonNames();

    List<Employee> getAllPersonsAsEmployee();

    Employee getPersonAsEmployee(String code);
}
