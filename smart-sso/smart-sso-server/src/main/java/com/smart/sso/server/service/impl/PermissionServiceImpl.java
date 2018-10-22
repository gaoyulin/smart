package com.smart.sso.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.smart.sso.server.model.UserPermission;
import com.smart.sso.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.rpc.RpcPermission;
import com.smart.sso.server.dao.PermissionDao;
import com.smart.sso.server.model.Permission;
import com.smart.sso.server.model.RolePermission;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission, Integer> implements PermissionService {

	@Resource
	private RolePermissionService rolePermissionService;
	@Resource
	private UserPermissionService userPermissionService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private AppService appService;
	@Resource
	private PermissionJmsService permissionJmsService;

	@Autowired
	public void setDao(PermissionDao dao) {
		this.dao = dao;
	}

	public void save(Permission t) {
		super.save(t);
		// JMS通知权限变更
		permissionJmsService.send(appService.get(t.getAppId()).getCode());
	}

	public List<Permission> findByAppId(Integer appId, Integer roleId, Boolean isEnable) {
		List<Permission> permissionList = dao.findByAppId(appId, isEnable);
		if (roleId != null) {
			List<RolePermission> rolePermissionList = rolePermissionService.findByRoleId(roleId);
			for (Permission permission : permissionList) {
				for (RolePermission rp : rolePermissionList) {
					if (permission.getId().equals(rp.getPermissionId())) {
						permission.setChecked(true);
						break;
					}
				}
			}
		}
		return permissionList;
	}
	public List<Permission> findByAppUserId(Integer appId, Integer userId, Boolean isEnable) {
		List<Permission> permissionList = dao.findByAppId(appId, isEnable);
		if (userId != null) {
			List<UserPermission> userPermissionList = userPermissionService.findByUserId(userId);
			for (Permission permission : permissionList) {
				for (UserPermission userPermission : userPermissionList) {
					if (permission.getId().equals(userPermission.getPermissionId())) {
						permission.setChecked(true);
						break;
					}
				}
			}
		}
		return permissionList;
	}

	@Transactional
	public void deletePermission(Integer id, Integer appId) {
		List<Integer> idList = new ArrayList<Integer>();

		List<Permission> list = permissionService.findByAppId(appId, null, null);
		loopSubList(id, idList, list);
		idList.add(id);

		rolePermissionService.deleteByPermissionIds(idList);

		verifyRows(dao.deleteById(idList), idList.size(), "权限数据库删除失败");
		
		// JMS通知权限变更
		permissionJmsService.send(appService.get(appId).getCode());
	}

	// 递归方法，删除子权限
	protected void loopSubList(Integer id, List<Integer> idList, List<Permission> list) {
		for (Permission p : list) {
			if (id.equals(p.getParentId())) {
				idList.add(p.getId());
				loopSubList(p.getId(), idList, list);
			}
		}
	}

	public void deleteByAppIds(List<Integer> idList) {
		dao.deleteByAppIds(idList);
	}

	public List<RpcPermission> findListById(String appCode, Integer userId,Integer type) {
		return dao.findListById(appCode, userId,type);
	}
}
