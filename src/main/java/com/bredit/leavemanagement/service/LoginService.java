/**
 * 
 */
package com.bredit.leavemanagement.service;

/**
 * @author KaweepattC
 * 
 */
public interface LoginService extends BaseService
{
    Boolean login(String username, String password);
}
