package com.smart.sso.rpc.controller;

import com.smart.sso.rpc.AuthenticationRpcService;
import com.smart.sso.rpc.RpcPermission;
import com.smart.sso.rpc.RpcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/authenticationRpc")
public class AuthenticationRpcController {

   @Autowired
   private AuthenticationRpcService authenticationRpcService;





   /**
     * 验证是否已经登录
     *
     * @param token
     *            授权码
     * @return
     */
    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    @ResponseBody
    public boolean validate(String token){
        return authenticationRpcService.validate(token);
    }

    /**
     * 根据登录的Token和应用编码获取授权用户信息
     *
     * @param token
     *            授权码
     *            应用编码
     * @return
     */
    @RequestMapping(value = "/findAuthInfo", method = RequestMethod.GET)
    @ResponseBody
    public RpcUser findAuthInfo(String token){
       return authenticationRpcService.findAuthInfo(token);
    }

    /**
     * 获取当前应用所有权限(含菜单)
     *
     * @param token
     *            授权码 (如果token不为空，获取当前用户的所有权限)
     * @param appCode
     *            应用编码
     * @return
     */
    @RequestMapping(value = "/findPermissionList", method = RequestMethod.GET)
    @ResponseBody
    public List<RpcPermission> findPermissionList(String token, String appCode){
        return authenticationRpcService.findPermissionList(token,appCode);
    }



}
