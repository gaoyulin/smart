package com.smart.sso.server.dao;

import java.util.List;

import com.smart.sso.server.model.UserRole;
import com.smart.sso.common.mybatis.Dao;
import com.smart.sso.server.model.UserRole;
import com.smart.sso.server.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户角色映射持久化接口
 * 
 * @author Joe
 */
@Mapper
public interface UserRoleDao extends Dao<UserRole, Integer> {

	public UserRole findByUserRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

	public List<UserRole> findByUserId(@Param("userId") Integer userId);

	public int deleteByRoleIds(@Param("idList") List<Integer> idList);


	public int deleteByUserIds(@Param("idList") List<Integer> idList);
}
