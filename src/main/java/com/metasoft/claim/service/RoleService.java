/**
 * 
 */
package com.metasoft.claim.service;

import com.metasoft.claim.dao.RoleDao;
import com.metasoft.claim.model.Role;

public interface RoleService extends ModelBasedService<RoleDao, Role, Integer>
{

    Role findByName(String name);

}
