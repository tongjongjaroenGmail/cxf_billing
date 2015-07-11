/**
 * 
 */
package com.bredit.leavemanagement.dao;

import com.bredit.leavemanagement.model.Role;

public interface RoleDao extends AbstractDao<Role, Integer>
{

    Role findByName(String name);

}
