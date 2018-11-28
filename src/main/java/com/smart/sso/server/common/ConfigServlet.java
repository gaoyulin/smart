package com.smart.sso.server.common;

import com.smart.sso.common.config.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * 初始化全局参数
 * 
 * @author Joe
 */

public class ConfigServlet extends HttpServlet {

	private static final long serialVersionUID = -7462526216386306510L;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		servletContext.setAttribute("_path", servletContext.getContextPath());
		try {
			String staticPath = ConfigUtils.getProperty("static.url");
			String serverPath = ConfigUtils.getProperty("server.url");
			servletContext.setAttribute("_staticPath", staticPath);
			servletContext.setAttribute("_serverPath",serverPath);
			servletContext.setAttribute("systemName", ConfigUtils.getProperty("system.name"));
			servletContext.setAttribute("systemAdminName", ConfigUtils.getProperty("system.admin.name"));
			servletContext.setAttribute("systemName", ConfigUtils.getProperty("system.name"));
			servletContext.setAttribute("systemAdminName", ConfigUtils.getProperty("system.admin.name"));
			servletContext.setAttribute("companyName", ConfigUtils.getProperty("system.company.name"));
			servletContext.setAttribute("companyPhone", ConfigUtils.getProperty("system.company.phone"));
			servletContext.setAttribute("copyRight", ConfigUtils.getProperty("system.copy.right"));

		}
		catch (Exception e) {
			logger.error("系统初始化参数配置有误", e);
		}
	}
}
