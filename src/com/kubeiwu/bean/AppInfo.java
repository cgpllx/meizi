package com.kubeiwu.bean;

import com.google.gson.annotations.Expose;

public class AppInfo {
	@Expose
    public String versionName;
	@Expose
    public int versionCode;
	@Expose
    public String apkUrl;
	@Expose
    public boolean forceUpdate;
	@Expose
    public String description;
	@Expose
    public String appName;
   
    public String applicationId;
    int id;
    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public String getDescription() {
        return description;
    }

    public String getAppName() {
        return appName;
    }
}
