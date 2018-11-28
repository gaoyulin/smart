package com.smart.sso.server.dao;
import com.smart.sso.server.model.UserPermission;
import com.smart.sso.common.mybatis.Dao;
import com.smart.sso.server.model.UserPermission;
import com.smart.sso.server.model.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限映射持久化接口
 * 
 * @author Joe
 */
@Mapper
public interface UserPermissionDao extends Dao<UserPermission, Integer> {
	
	public List<UserPermission> findByUserId(@Param("userId") Integer userId);
	
	public int deleteByPermissionIds(@Param("idList") List<Integer> idList);
	
	public int deleteByUserIds(@Param("idList") List<Integer> idList);
	
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public int deleteByAppAndUserId(@Param("appId") Integer appId, @Param("userId") Integer userId);
}
