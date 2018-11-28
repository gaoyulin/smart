package com.smart.sso.server.service;

import com.smart.sso.common.mybatis.Service;
import com.smart.sso.server.model.UserPermission;

import java.util.List;

/**
 * 角色权限映射服务接口
 * 
 * @author Joe
 */
public interface UserPermissionService extends Service<UserPermission, Integer> {
	
	/**
	 * 根据角色ID查询映射
	 * @param userId 用户ID
	 * @return
	 */
	public List<UserPermission> findByUserId(Integer userId);
	
	/**
	 * 根据角色ID给角色授权
	 * @param appId 应用ID
	 * @param permissionIdList 权限ID集合
	 * @return
	 */
	public void allocate(Integer appId, Integer userId, List<Integer> permissionIdList);
	
	/**
	 * 根据权限ID集合删除映射
	 * @param idList 权限ID集合
	 * @return
	 */
	public void deleteByPermissionIds(List<Integer> idList);
	
	/**
	 * 根据角色ID集合删除映射
	 * @param idList 角色ID集合
	 * @return
	 */
	public void deleteByUserIds(List<Integer> idList);
	
	/**
	 * 根据应用ID集合删除映射
	 * @param idList 应用ID集合
	 * @return
	 */
	public void deleteByAppIds(List<Integer> idList);
}
