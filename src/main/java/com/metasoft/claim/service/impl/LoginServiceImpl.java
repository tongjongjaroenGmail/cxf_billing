package com.metasoft.claim.service.impl;

import org.springframework.stereotype.Component;

import com.metasoft.claim.service.LoginService;

/**
 * @author 
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
