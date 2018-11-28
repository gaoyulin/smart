package com.smart.sso.server.service;

import com.smart.sso.server.model.GatewayApiDefine;
import com.smart.sso.common.model.Pagination;
import com.smart.sso.common.model.Result;
import com.smart.sso.common.mybatis.Service;
import com.smart.sso.server.model.GatewayApiDefine;
import com.smart.sso.server.model.User;
import com.smart.sso.server.model.GatewayApiDefine;

import java.util.List;

/**
 * 用户服务接口
 * 
 * @author Joe
 */
public interface GatewayApiDefineService extends Service<GatewayApiDefine, Integer> {

	/**
	 * 启用禁用操作
	 * @param isEnable 是否启用
	 * @param idList 用户ID集合
	 * @return
	 */
	void enable(Boolean isEnable, List<Integer> idList);

	/**
	 *查询所有的路由
	 * @param p
	 * @return
	 */
	List<GatewayApiDefine> findByAll(Pagination<GatewayApiDefine> p);

	/**
	 * 保存修改
	 * @param gatewayApiDefine
	 * @param roleIdList
	 */
	void save(GatewayApiDefine gatewayApiDefine);
}
