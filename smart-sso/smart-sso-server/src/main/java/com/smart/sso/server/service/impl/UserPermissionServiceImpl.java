package com.smart.sso.server.service.impl;

import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.server.dao.UserPermissionDao;
import com.smart.sso.server.model.RolePermission;
import com.smart.sso.server.model.UserPermission;
import com.smart.sso.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("userPermissionServiceImpl")
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionDao, UserPermission, Integer> implements UserPermissionService {

	@Resource
	private AppService appService;
	@Resource
	private PermissionJmsService permissionJmsService;

	@Autowired
	public void setDao(UserPermissionDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void allocate(Integer appId, Integer userId, List<Integer> permissionIdList) {
		dao.deleteByAppAndUserId(appId, userId);

		List<UserPermission> list = new ArrayList<UserPermission>();
		Integer permissionId;
		for (Iterator<Integer> i$ = permissionIdList.iterator(); i$.hasNext(); list
				.add(new UserPermission(appId, userId, permissionId))) {
			permissionId = i$.next();
		}
		if (!CollectionUtils.isEmpty(list)) {
			super.save(list);
		}

		// JMS通知权限变更
		permissionJmsService.send(appService.get(appId).getCode());
	}

	public List<UserPermission> findByUserId(Integer userId) {
		return dao.findByUserId(userId);
	}

	public void deleteByPermissionIds(List<Integer> idList) {
		dao.deleteByPermissionIds(idList);
	}
	
	public void deleteByUserIds(List<Integer> idList) {
		dao.deleteByUserIds(idList);
	}
	
	public void deleteByAppIds(List<Integer> idList) {
		dao.deleteByAppIds(idList);
	}
}
