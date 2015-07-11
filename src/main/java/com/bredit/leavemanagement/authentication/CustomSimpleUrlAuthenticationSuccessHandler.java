/**
 * 
 */
package com.bredit.leavemanagement.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bredit.leavemanagement.dao.EmployeeDao;
import com.bredit.leavemanagement.repository.PersonRepository;
import com.bredit.leavemanagement.service.LdapEmployeeSynchronizationService;

/**
 * @author KaweepattC
 * 
 */
@Component
public class CustomSimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
{
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LdapEmployeeSynchronizationService ldapEmployeeSynchronizationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException
    {
        ldapEmployeeSynchronizationService.sync(authentication.getName());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
