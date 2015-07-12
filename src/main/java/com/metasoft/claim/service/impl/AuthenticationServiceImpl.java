package com.metasoft.claim.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metasoft.claim.dao.SecUserDao;
import com.metasoft.claim.model.SecUser;

@Service("authenticationService")
public class AuthenticationServiceImpl implements UserDetailsService {
	@Autowired
	private SecUserDao secUserDao;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		SecUser user = secUserDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getStdPosition().getRole());

		return buildUserForAuthentication(user, authorities);
		

	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(SecUser user, List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), user.getPassword(), user.isEnable(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(String userRole) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		setAuths.add(new SimpleGrantedAuthority(userRole));

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}