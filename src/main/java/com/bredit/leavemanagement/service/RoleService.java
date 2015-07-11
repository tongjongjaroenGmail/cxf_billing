/**
 * 
 */
package com.bredit.leavemanagement.service;

import com.bredit.leavemanagement.dao.RoleDao;
import com.bredit.leavemanagement.model.Role;

public interface RoleService extends ModelBasedService<RoleDao, Role, Integer>
{

    Role findByName(String name);

}
