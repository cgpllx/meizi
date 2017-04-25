package com.kubeiwu.bean;

import com.google.gson.annotations.Expose;

 
public class ADInfo {
	@Expose
    String ad_unit_id_applicationCode;
	@Expose
    String ad_unit_id_native;
	@Expose
    String ad_unit_id_banner;
	@Expose
    String ad_unit_id_interstitial;
    int id;
    
    

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAd_unit_id_applicationCode(String ad_unit_id_applicationCode) {
		this.ad_unit_id_applicationCode = ad_unit_id_applicationCode;
	}

	public void setAd_unit_id_native(String ad_unit_id_native) {
		this.ad_unit_id_native = ad_unit_id_native;
	}

	public void setAd_unit_id_banner(String ad_unit_id_banner) {
		this.ad_unit_id_banner = ad_unit_id_banner;
	}

	public void setAd_unit_id_interstitial(String ad_unit_id_interstitial) {
		this.ad_unit_id_interstitial = ad_unit_id_interstitial;
	}

	public String getAd_unit_id_applicationCode() {
        return ad_unit_id_applicationCode;
    }

    public String getAd_unit_id_native() {
        return ad_unit_id_native;
    }

    public String getAd_unit_id_banner() {
        return ad_unit_id_banner;
    }

    public String getAd_unit_id_interstitial() {
        return ad_unit_id_interstitial;
    }
}
