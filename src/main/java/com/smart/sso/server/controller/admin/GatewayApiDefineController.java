package com.smart.sso.server.controller.admin;

import com.smart.sso.server.model.GatewayApiDefine;
import com.smart.sso.common.model.Pagination;
import com.smart.sso.common.model.Result;
import com.smart.sso.common.model.ResultCode;
import com.smart.sso.common.validator.Validator;
import com.smart.sso.common.validator.annotation.ValidateParam;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.model.App;
import com.smart.sso.server.model.GatewayApiDefine;
import com.smart.sso.server.service.AppService;
import com.smart.sso.server.service.GatewayApiDefineService;
import com.smart.sso.server.model.GatewayApiDefine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Joe
 */
@Api(tags = "应用管理")
@Controller
@RequestMapping("/admin/gateway")
public class GatewayApiDefineController extends BaseController {

	@Resource
	private GatewayApiDefineService gatewayApiDefineService;

	@Resource
	private AppService appService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "/admin/gatewayApiDefine";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		GatewayApiDefine gatewayApiDefine;
		if (id == null) {
			gatewayApiDefine = new GatewayApiDefine();
		}
		else {
			gatewayApiDefine = gatewayApiDefineService.get(id);
		}
		model.addAttribute("gatewayApiDefine", gatewayApiDefine);
		model.addAttribute("appList", appService.findByAll(true));
		return "/admin/gatewayApiDefineEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ApiParam(value = "名称 ") String name,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(gatewayApiDefineService.findByAll(new Pagination<GatewayApiDefine>(pageNo, pageSize)));
	}


	@ApiOperation("启用/禁用")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		gatewayApiDefineService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(GatewayApiDefine gatewayApiDefine) {
		gatewayApiDefineService.save(gatewayApiDefine);
		return Result.createSuccessResult();
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		gatewayApiDefineService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}
}