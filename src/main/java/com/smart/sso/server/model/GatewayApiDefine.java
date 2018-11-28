package com.smart.sso.server.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.smart.sso.common.model.PersistentObject;

import java.util.Date;
import java.util.List;

public class GatewayApiDefine extends PersistentObject {

    /** 过滤路径前缀 */
    private String path;
    /** 服务ID*/
    private String serviceId;
    /** 访问地址，多个用,号隔开*/
    private String url;
    /**
     * 是否重试
     */
    private Boolean retryable;

    /** 是否启用 */
    private Boolean isEnable = Boolean.valueOf(true);
    /**
     * 是否启用前缀
     */
    private Boolean stripPrefix;

    /**
     * 应用名称
     */
    private String appName;

    /** 创建时间 */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreateDate;

    /** 更新时间*/
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtUpdateDate;

    private List<Integer> ids;

    private Integer sysAppId;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public Boolean getStripPrefix() {
        return stripPrefix;
    }

    public void setStripPrefix(Boolean stripPrefix) {
        this.stripPrefix = stripPrefix;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getGmtCreateDate() {
        return gmtCreateDate;
    }

    public void setGmtCreateDate(Date gmtCreateDate) {
        this.gmtCreateDate = gmtCreateDate;
    }

    public Date getGmtUpdateDate() {
        return gmtUpdateDate;
    }

    public void setGmtUpdateDate(Date gmtUpdateDate) {
        this.gmtUpdateDate = gmtUpdateDate;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getSysAppId() {
        return sysAppId;
    }

    public void setSysAppId(Integer sysAppId) {
        this.sysAppId = sysAppId;
    }

    public Boolean getRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
    }
}