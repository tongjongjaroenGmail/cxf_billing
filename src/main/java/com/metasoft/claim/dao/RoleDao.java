/**
 * 
 */
package com.metasoft.claim.dao;

import com.metasoft.claim.model.Role;

public interface RoleDao extends AbstractDao<Role, Integer>
{

    Role findByName(String name);

}
