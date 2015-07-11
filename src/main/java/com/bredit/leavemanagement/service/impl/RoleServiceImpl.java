/**
 * 
 */
package com.bredit.leavemanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bredit.leavemanagement.dao.RoleDao;
import com.bredit.leavemanagement.model.Role;
import com.bredit.leavemanagement.service.RoleService;

@Component
public class RoleServiceImpl extends ModelBasedServiceImpl<RoleDao, Role, Integer> implements RoleService
{
    private RoleDao roleDao;

    /**
     * 
     * @param entityClass
     */
    @Autowired
    public RoleServiceImpl(RoleDao roleDao)
    {
        super(roleDao);
        this.roleDao = roleDao;
    }

    @Override
    public Role findByName(String name)
    {
        return roleDao.findByName(name);
    }

}
