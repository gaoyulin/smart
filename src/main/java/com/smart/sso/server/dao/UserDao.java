package com.smart.sso.server.dao;

import java.util.List;

import com.smart.sso.server.model.User;
import com.smart.sso.common.model.Pagination;
import com.smart.sso.common.mybatis.Dao;
import com.smart.sso.server.model.User;
import com.smart.sso.server.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户持久化接口
 * 
 * @author Joe
 */
@Mapper
public interface UserDao extends Dao<User, Integer> {
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	public List<User> findPaginationByAccount(@Param("account") String account, Pagination<User> p);
	
	public User findByAccount(@Param("account") String account);
}
