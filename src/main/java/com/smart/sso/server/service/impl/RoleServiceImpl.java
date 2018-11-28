package com.smart.sso.server.service.impl;

import java.util.List;

import javax.annotation.Resource;
import com.smart.sso.common.mybatis.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smart.sso.common.model.Pagination;
import com.smart.sso.server.dao.RoleDao;
import com.smart.sso.server.model.Role;
import com.smart.sso.server.service.RolePermissionService;
import com.smart.sso.server.service.RoleService;
import com.smart.sso.server.service.UserRoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role, Integer> implements RoleService {

	@Resource
	private UserRoleService userRoleService;
	@Resource
	private RolePermissionService rolePermissionService;

	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "角色数据库更新失败");
	}

	public void save(Role t) {
		super.save(t);
	}

	public Pagination<Role> findPaginationByName(String name, Pagination<Role> p) {
		List<Role> paginationByName = dao.findPaginationByName(name, null, p);
		p.setList(paginationByName);
		return p;
	}

	public List<Role> findByAll(Boolean isEnable) {
		return dao.findPaginationByName(null, isEnable, null);
	}

	@Transactional
	public void deleteById(List<Integer> idList) {
		userRoleService.deleteByRoleIds(idList);
		rolePermissionService.deleteByRoleIds(idList);
		verifyRows(dao.deleteById(idList), idList.size(), "角色数据库删除失败");
	}
}
