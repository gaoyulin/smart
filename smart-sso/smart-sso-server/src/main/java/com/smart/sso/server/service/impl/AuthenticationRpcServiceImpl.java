package com.smart.sso.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.smart.sso.server.model.UserPermission;
import com.smart.sso.server.service.UserPermissionService;
import org.springframework.stereotype.Service;

import com.smart.mvc.util.StringUtils;
import com.smart.sso.rpc.AuthenticationRpcService;
import com.smart.sso.rpc.RpcPermission;
import com.smart.sso.rpc.RpcUser;
import com.smart.sso.server.common.LoginUser;
import com.smart.sso.server.common.TokenManager;
import com.smart.sso.server.service.PermissionService;
import com.smart.sso.server.service.UserService;

@Service("authenticationRpcService")
public class AuthenticationRpcServiceImpl implements AuthenticationRpcService {

	@Resource
	private PermissionService permissionService;
	@Resource
	private UserService userService;
	@Resource
	private TokenManager tokenManager;

	@Resource
	private UserPermissionService userPermissionService;

	@Override
	public boolean validate(String token) {
		return tokenManager.validate(token) != null;
	}
	
	@Override
	public RpcUser findAuthInfo(String token) {
		LoginUser user = tokenManager.validate(token);
		if (user != null) {
			return new RpcUser(user.getAccount());
		}
		return null;
	}
	
	@Override
	public List<RpcPermission> findPermissionList(String token, String appCode) {
		if (StringUtils.isBlank(token)) {
			return permissionService.findListById(appCode, null,1);
		}
		else {
			LoginUser user = tokenManager.validate(token);
			if (user != null) {
				List<UserPermission> byRoleId = userPermissionService.findByUserId(user.getUserId());
				if(byRoleId.size()>0){
					return permissionService.findListById(appCode, user.getUserId(),2);//2表示查询时给用户设置了单独权限，查询用户权限即可
				}else{
					return permissionService.findListById(appCode, user.getUserId(),1);//1表示查询时给用户未设置单独权限，需查询用户角色权限
				}
			}
			else {
				return new ArrayList<RpcPermission>(0);
			}
		}
	}
}
