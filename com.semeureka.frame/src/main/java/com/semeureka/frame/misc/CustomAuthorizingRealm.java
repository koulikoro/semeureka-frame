package com.semeureka.frame.misc;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.semeureka.frame.entity.Permission;
import com.semeureka.frame.entity.Role;
import com.semeureka.frame.entity.User;
import com.semeureka.frame.service.UserService;

public class CustomAuthorizingRealm extends AuthorizingRealm {
	public static final Logger logger = LoggerFactory.getLogger(CustomAuthorizingRealm.class);
	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String name = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = userService.findByUsername(name);
		Set<String> roles = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		for (Role role : user.getRoles()) {
			roles.add(role.getName().toLowerCase());
			for (Permission permission : role.getPermissions()) {
				permissions.add(permission.getValue().toLowerCase());
			}
		}
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		SimpleAuthenticationInfo authenticationInfo = null;
		String name = (String) token.getPrincipal();
		User user = userService.findByUsername(name);
		if (user != null) {
			authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
					user.getPassword(), null, getName());
		}
		return authenticationInfo;
	}

	@Override
	protected void clearCache(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		super.clearCache(principals);
	}
}
