package com.smart.sso.server.dao;

import java.util.List;

import com.smart.sso.server.model.Permission;
import com.smart.sso.rpc.RpcPermission;
import com.smart.sso.common.mybatis.Dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 权限持久化接口
 * 
 * @author Joe
 */
@Mapper
public interface PermissionDao extends Dao<Permission, Integer> {
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	public List<Permission> findByAppId(@Param("appId") Integer appId, @Param("isEnable") Boolean isEnable);

	public List<Permission> findByRoleId(@Param("idList") List<Integer> idList, @Param("isEnable") Boolean isEnable,@Param("appId") Integer appId);
	
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public List<RpcPermission> findListById(@Param("appCode") String appCode, @Param("userId") Integer userId, @Param("type") Integer type);
}
