package com.smart.sso.server.dao;

import java.util.List;

import com.smart.sso.server.model.App;
import com.smart.sso.common.model.Pagination;
import com.smart.sso.common.mybatis.Dao;
import com.smart.sso.server.model.App;
import com.smart.sso.server.model.App;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 应用持久化接口
 * 
 * @author Joe
 */

@Mapper
public interface AppDao extends Dao<App, Integer> {
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	public List<App> findPaginationByName(@Param("name") String name, @Param("isEnable") Boolean isEnable,
                                          Pagination<App> p);
	
	public App findByCode(@Param("code") String code);
}
