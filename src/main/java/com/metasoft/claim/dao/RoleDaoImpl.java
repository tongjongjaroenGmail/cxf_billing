/**
 * 
 */
package com.metasoft.claim.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.model.Role;

@Repository("RoleDao")
@Transactional
public class RoleDaoImpl extends AbstractDaoImpl<Role, Integer> implements RoleDao
{
    public RoleDaoImpl()
    {
        super(Role.class);
    }

    @Override
    public Role findByName(String name)
    {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq("name", name));
        return (Role) criteria.uniqueResult();
    }
}
