package com.smart.sso.server.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smart.sso.common.model.ResultCode;
import com.smart.sso.common.validator.Validator;
import com.smart.sso.common.validator.annotation.ValidateParam;
import com.smart.sso.server.common.LoginUser;
import com.smart.sso.server.common.TokenManager;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.model.User;
import com.smart.sso.server.provider.IdProvider;
import com.smart.sso.server.provider.PasswordProvider;
import com.smart.sso.server.service.PermissionService;
import com.smart.sso.server.service.UserService;
import com.smart.sso.server.util.CookieUtils;
import com.smart.sso.client.SessionPermission;
import com.smart.sso.client.SessionUser;
import com.smart.sso.client.SessionUtils;
import com.smart.sso.client.SsoFilter;
import com.smart.sso.server.common.TokenManager;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.service.PermissionService;
import com.smart.sso.common.model.ResultCode;
import com.smart.sso.common.validator.Validator;
import com.smart.sso.common.validator.annotation.ValidateParam;
import com.smart.sso.server.common.LoginUser;
import com.smart.sso.server.common.TokenManager;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.model.User;
import com.smart.sso.server.provider.IdProvider;
import com.smart.sso.server.provider.PasswordProvider;
import com.smart.sso.server.service.PermissionService;
import com.smart.sso.server.service.UserService;
import com.smart.sso.server.util.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.smart.sso.common.model.Result;
import com.smart.sso.common.model.ResultCode;
import com.smart.sso.common.util.StringUtils;
import com.smart.sso.common.validator.Validator;
import com.smart.sso.common.validator.annotation.ValidateParam;
import com.smart.sso.server.captcha.CaptchaHelper;
import com.smart.sso.server.common.LoginUser;
import com.smart.sso.server.model.User;
import com.smart.sso.server.provider.IdProvider;
import com.smart.sso.server.provider.PasswordProvider;
import com.smart.sso.server.service.UserService;
import com.smart.sso.server.util.CookieUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Joe
 */
@Api(tags = "单点登录管理")
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	// 登录页
	private static final String LOGIN_PATH = "/login";

	@Resource
	private TokenManager tokenManager;
	@Resource
	private UserService userService;

    @Resource
    private PermissionService permissionService;

	@ApiOperation("登录页")
	@RequestMapping(method = RequestMethod.GET)
	public String login(
			@ApiParam(value = "返回链接", required = true) @ValidateParam({ Validator.NOT_BLANK }) String backUrl,
			HttpServletRequest request,Model map) {
		String token = CookieUtils.getCookie(request, TokenManager.TOKEN);
		if (StringUtils.isNotBlank(token) && tokenManager.validate(token) != null) {
			return "redirect:" + authBackUrl(backUrl, token);
		}
		else {
//
//            map.addAttribute("backUrl", backUrl);
//            map.addAttribute("_staticPath",request.getServletPath());
//            map.addAttribute("loginUrl", "login");
//            map.addAttribute("_path",request.getServletPath());
//            map.addAttribute("_systemName","登录系统");
//            return LOGIN_PATH;

			return goLoginPath(backUrl, request);
		}
	}

	@RequestMapping("/captcha")
	public void he(HttpServletResponse response,HttpServletRequest request) throws IOException {
        CaptchaHelper.setInCache(request,response);

    }

	@ApiOperation("登录提交")
	@RequestMapping(method = RequestMethod.POST)
	public String login(
			@ApiParam(value = "返回链接", required = true) @ValidateParam({ Validator.NOT_BLANK }) String backUrl,
			@ApiParam(value = "登录名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String account,
			@ApiParam(value = "密码", required = true) @ValidateParam({ Validator.NOT_BLANK }) String password,
			@ApiParam(value = "验证码", required = true) @ValidateParam({ Validator.NOT_BLANK }) String captcha,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if (!CaptchaHelper.validate(request, captcha)) {
			request.setAttribute("errorMessage", "验证码不正确");
			return goLoginPath(backUrl, request);
		}
		Result result = userService.login(getIpAddr(request), account, PasswordProvider.encrypt(password));
		if (!result.getCode().equals(ResultCode.SUCCESS)) {
			request.setAttribute("errorMessage", result.getMessage());
			return goLoginPath(backUrl, request);
		}
		else {
			User user = (User) result.getData();
			LoginUser loginUser = new LoginUser(user.getId(), user.getAccount());
			String token = CookieUtils.getCookie(request, TokenManager.TOKEN);
			if (StringUtils.isBlank(token) || tokenManager.validate(token) == null) {// 没有登录的情况
                SessionUser sessionUser = new SessionUser();
				token = createToken(loginUser);
                sessionUser.setAccount(loginUser.getAccount());
                sessionUser.setToken(token);
                SessionUtils.setSessionUser(request,sessionUser);
				addTokenInCookie(token, request, response);
			}
			// 跳转到原请求
			if("".equals(backUrl)){
				backUrl = URLDecoder.decode("/admin/admin", "utf-8");
			}else {
				backUrl = URLDecoder.decode(backUrl, "utf-8");
			}
			return "redirect:" + authBackUrl(backUrl, token);
		}
	}
	
	private String goLoginPath(String backUrl, HttpServletRequest request) {

//		request.setAttribute("_staticPath",request.getServletPath());
//        request.setAttribute("loginUrl", "login");
//        request.setAttribute("_path",request.getServletPath());
//        request.setAttribute("_systemName","登录系统");
//        Map<String,String> map = new HashMap<>();
//        map.put("backUrl", backUrl);
//        map.put("_staticPath",request.getServletPath());
//        map.put("loginUrl", "login");
//        map.put("_path",request.getServletPath());
//        map.put("_systemName","登录系统");
		if(StringUtils.isBlank(backUrl)){
			request.setAttribute("backUrl", "");
		}else{
			request.setAttribute("backUrl", backUrl);
		}
		request.setAttribute("loginUrl","/login");
		return LOGIN_PATH;
	}

	private String authBackUrl(String backUrl, String token) {
		StringBuilder sbf = new StringBuilder(backUrl);
		if (backUrl.indexOf("?") > 0) {
			sbf.append("&");
		}
		else {
			sbf.append("?");
		}
		sbf.append(SsoFilter.SSO_TOKEN_NAME).append("=").append(token);
		return sbf.toString();
	}

	private String createToken(LoginUser loginUser) {
		// 生成token
		String token = IdProvider.createUUIDId();

		// 缓存中添加token对应User
		tokenManager.addToken(token, loginUser);
		return token;
	}
	
	private void addTokenInCookie(String token, HttpServletRequest request, HttpServletResponse response) {
		// Cookie添加token
		Cookie cookie = new Cookie(TokenManager.TOKEN, token);
		cookie.setPath("/");
		if ("https".equals(request.getScheme())) {
			cookie.setSecure(true);
		}
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}
}