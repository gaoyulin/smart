package com.smart.sso.server.dao;

import java.util.List;

import com.smart.sso.server.model.Role;
import com.smart.sso.common.model.Pagination;
import com.smart.sso.common.mybatis.Dao;
import com.smart.sso.server.model.Role;
import com.smart.sso.server.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色持久化接口
 * 
 * @author Joe
 */
@Mapper
public interface RoleDao extends Dao<Role, Integer> {

	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);

	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	public List<Role> findPaginationByName(@Param("name") String name, @Param("isEnable") Boolean isEnable,
                                           Pagination<Role> p);
}
