/**
 * 
 */
package com.metasoft.claim.repository;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.NotFilter;
import org.springframework.stereotype.Repository;

import com.metasoft.claim.model.Employee;

/**
 * @author 
 * 
 */
@Repository("personRepository")
public class PersonRepositoryImpl implements PersonRepository
{
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private LdapTemplate ldapTemplate;

    public void setLdapTemplate(LdapTemplate ldapTemplate)
    {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public List<String> getAllPersonNames()
    {
        return ldapTemplate.search("", "(objectclass=person)", new AttributesMapper()
        {
            public Object mapFromAttributes(Attributes attrs) throws NamingException
            {
                return attrs.get("cn").get();
            }
        });
    }

    @Override
    public List<Employee> getAllPersonsAsEmployee()
    {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person"));
        filter.and(new NotFilter(new EqualsFilter("objectclass", "computer")));
        List<Employee> ldapResult = ldapTemplate.search("", filter.encode(), new EmployeeAttributesMapper());

        return ldapResult;
    }

    @Override
    public Employee getPersonAsEmployee(String code)
    {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person"));
        filter.and(new EqualsFilter("sAMAccountName", code));
        filter.and(new NotFilter(new EqualsFilter("objectclass", "computer")));

        List<Employee> ldapResult = ldapTemplate.search("", filter.encode(), new EmployeeAttributesMapper());

        return ldapResult.isEmpty() ? null : ldapResult.get(0);
    }
}
