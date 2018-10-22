package com.smart.sso.server.controller.admin;

import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.service.AppService;
import com.smart.sso.server.service.RolePermissionService;
import com.smart.sso.server.service.UserPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Joe
 */
@Api(tags = "用户权限管理")
@Controller
@RequestMapping("/admin/userPermission")
public class UserPermissionController extends BaseController {

	@Resource
	private AppService appService;
	@Resource
	private UserPermissionService userPermissionService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String edit(
			@ApiParam(value = "用户id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer userId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("appList", appService.findByAll(true));
		return "/admin/userPermission";
	}
	
	@ApiOperation("角色授权提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "应用id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer appId,
			@ApiParam(value = "用户id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer userId,
			@ApiParam(value = "权限ids") String permissionIds) {
		userPermissionService.allocate(appId, userId, getAjaxIds(permissionIds));
		return Result.createSuccessResult().setMessage("授权成功");
	}
}