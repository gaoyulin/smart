package com.smart.sso.server.dao;

import com.smart.sso.server.model.GatewayApiDefine;
import com.smart.sso.common.model.Pagination;
import com.smart.sso.common.mybatis.Dao;
import com.smart.sso.server.model.GatewayApiDefine;
import com.smart.sso.server.model.User;
import com.smart.sso.server.model.GatewayApiDefine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户持久化接口
 * 
 * @author Joe
 */
@Mapper
public interface GatewayApiDefineDao extends Dao<GatewayApiDefine, Integer> {

}
