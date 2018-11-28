package com.smart.sso.server.dao;

import java.util.List;

import com.smart.sso.server.model.RolePermission;
import com.smart.sso.common.mybatis.Dao;
import com.smart.sso.server.model.RolePermission;
import com.smart.sso.server.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色权限映射持久化接口
 * 
 * @author Joe
 */
@Mapper
public interface RolePermissionDao extends Dao<RolePermission, Integer> {
	
	public List<RolePermission> findByRoleId(@Param("roleId") Integer roleId);
	
	public int deleteByPermissionIds(@Param("idList") List<Integer> idList);
	
	public int deleteByRoleIds(@Param("idList") List<Integer> idList);
	
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public int deleteByAppAndRoleId(@Param("appId") Integer appId, @Param("roleId") Integer roleId);
}
