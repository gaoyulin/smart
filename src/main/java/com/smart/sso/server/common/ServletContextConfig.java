package com.smart.sso.server.common;

import com.smart.sso.common.config.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextConfig implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(ServletContextConfig.class);

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("_path", servletContext.getContextPath());
        try {
            String staticPath = ConfigUtils.getProperty("static.url");
            String serverPath = ConfigUtils.getProperty("server.url");
            servletContext.setAttribute("staticPath", staticPath);
            servletContext.setAttribute("serverPath",serverPath);
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

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
