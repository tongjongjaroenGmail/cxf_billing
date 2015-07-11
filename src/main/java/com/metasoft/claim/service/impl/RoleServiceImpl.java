/**
 * 
 */
package com.metasoft.claim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metasoft.claim.dao.RoleDao;
import com.metasoft.claim.model.Role;
import com.metasoft.claim.service.RoleService;

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
