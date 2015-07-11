/**
 * 
 */
package com.metasoft.claim.service;

/**
 * @author 
 * 
 */
public interface LoginService extends BaseService
{
    Boolean login(String username, String password);
}
