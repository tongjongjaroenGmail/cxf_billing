/**
 * 
 */
package com.bredit.leavemanagement.security;

import java.util.Collection;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.PersonContextMapper;
import org.springframework.stereotype.Component;

/**
 * @author KaweepattC
 * 
 */
@Component
public class CustomPersonContextMapper extends PersonContextMapper
{

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities)
    {
        return super.mapUserFromContext(ctx, username, authorities);

    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx)
    {
        super.mapUserToContext(user, ctx);
    }

}
