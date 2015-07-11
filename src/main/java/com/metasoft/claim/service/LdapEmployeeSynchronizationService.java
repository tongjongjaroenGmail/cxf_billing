/**
 * 
 */
package com.metasoft.claim.service;

import com.metasoft.claim.model.LdapEmployeeSynchronizationResult;

/**
 * @author 
 * 
 */
public interface LdapEmployeeSynchronizationService extends BaseService
{

    LdapEmployeeSynchronizationResult sync(String code);
    
    LdapEmployeeSynchronizationResult syncAll();

}
