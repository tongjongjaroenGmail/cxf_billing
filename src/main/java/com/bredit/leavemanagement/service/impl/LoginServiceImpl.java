package com.bredit.leavemanagement.service.impl;

import org.springframework.stereotype.Component;

import com.bredit.leavemanagement.service.LoginService;

/**
 * @author KaweepattC
 * 
 */
@Component
public class LoginServiceImpl extends BaseServiceImpl implements LoginService
{
    @Override
    public Boolean login(String username, String password)
    {
        return username.equals(password);
    }
}
