package com.smart.sso.server.model;
import com.smart.sso.common.model.PersistentObject;

/**
 * 角色权限映射
 * 
 * @author Joe
 */
public class UserPermission extends PersistentObject {

	private static final long serialVersionUID = 2817362249993235590L;

	/** 应用ID */
	private Integer appId;
	private Integer userId;
	private Integer permissionId;

	public UserPermission() {
		super();
	}

	public UserPermission(Integer appId, Integer userId, Integer permissionId) {
		super();
		this.appId = appId;
		this.userId = userId;
		this.permissionId = permissionId;
	}

	public Integer getAppId() {
		return this.appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getRoleId() {
		return this.userId;
	}

	public void setRoleId(Integer roleId) {
		this.userId = roleId;
	}

	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
}
