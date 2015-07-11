/**
 * 
 */
package com.bredit.leavemanagement.service;

import com.bredit.leavemanagement.model.LdapEmployeeSynchronizationResult;

/**
 * @author KaweepattC
 * 
 */
public interface LdapEmployeeSynchronizationService extends BaseService
{

    LdapEmployeeSynchronizationResult sync(String code);
    
    LdapEmployeeSynchronizationResult syncAll();

}
