package com.dtsgroup.labourlaw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by vantr on 5/15/2017.
 */

public class Appendix implements Serializable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title_vi")
    @Expose
    private String titleVi;
    @SerializedName("title_en")
    @Expose
    private String titleEn;
    @SerializedName("url")
    @Expose
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleVi() {
        return titleVi;
    }

    public void setTitleVi(String titleVi) {
        this.titleVi = titleVi;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
