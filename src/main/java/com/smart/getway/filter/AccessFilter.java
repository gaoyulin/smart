package com.smart.getway.filter;
 
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.smart.sso.common.config.ConfigUtils;
import com.smart.sso.common.util.StringUtils;
import com.smart.sso.server.common.TokenManager;
import com.smart.sso.server.util.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessFilter extends ZuulFilter {

    @Resource(name="localTokenManager")
    private TokenManager tokenManager;

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
 
    @Override
    public String filterType() {
        //前置过滤器
        return "pre";
    }
 
    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }
 
    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，true代表需要过滤
        return true;
    }
 
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
 
        //获取传来的参数accessToken
        Object accessToken = request.getParameter("token");
        String token = CookieUtils.getCookie(request, TokenManager.TOKEN);
        if (StringUtils.isBlank(token) || tokenManager.validate(token) == null) {// 没有登录的情况
            HttpServletResponse response = ctx.getResponse();
            String logoutUrl = new StringBuilder()
                     .append(ConfigUtils.getProperty("server.url"))
                     .append("/login?backUrl=").append(getLocalUrl(request)).toString();
            try {
                response.sendRedirect(logoutUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //如果有token，则进行路由转发
        log.info("access token ok");
        //这里return的值没有意义，zuul框架没有使用该返回值
        return null;
    }

    /**
     * 获取当前上下文路径
     *
     * @param request
     * @return
     */
    private String getLocalUrl(HttpServletRequest request) {
        return new StringBuilder().append(request.getScheme()).append("://").append(request.getServerName()).append(":")
                .append(request.getServerPort() == 80 ? "" : request.getServerPort()).append(request.getRequestURI())
                .toString();
    }
}
