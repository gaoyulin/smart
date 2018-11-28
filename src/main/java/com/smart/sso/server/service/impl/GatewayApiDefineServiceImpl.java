package com.smart.sso.server.service.impl;
import com.smart.getway.route.RefreshRouteService;
import com.smart.sso.server.model.GatewayApiDefine;
import com.smart.sso.server.service.GatewayApiDefineService;
import com.smart.sso.common.model.Pagination;
import com.smart.sso.common.mybatis.impl.ServiceImpl;
import com.smart.sso.server.dao.GatewayApiDefineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户服务接口
 * 
 * @author Joe
 */
@Service
public class GatewayApiDefineServiceImpl extends ServiceImpl<GatewayApiDefineDao, GatewayApiDefine, Integer> implements GatewayApiDefineService {
    @Autowired
    RefreshRouteService refreshRouteService;

    @Override
    public void enable(Boolean isEnable, List<Integer> idList) {
        GatewayApiDefine gatewayApiDefine = new GatewayApiDefine();
        gatewayApiDefine.setIsEnable(isEnable);
        dao.update(gatewayApiDefine);
    }

    @Override
	public List<GatewayApiDefine> findByAll(Pagination<GatewayApiDefine> p) {
        List<GatewayApiDefine> byAll = dao.findByAll(p);
        return byAll;
	}

	@Override
	public void save(GatewayApiDefine gatewayApiDefine) {
	    if(gatewayApiDefine.getId()!=null){
            gatewayApiDefine.setGmtCreateDate(new Date());
            dao.update(gatewayApiDefine);
        }else{
            gatewayApiDefine.setGmtUpdateDate(new Date());
            dao.insert(gatewayApiDefine);
        }
        refreshRouteService.refreshRoute();
	}
}
