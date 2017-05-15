package com.dtsgroup.labourlaw.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JSonItemBookmark implements Serializable{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("chapter")
    @Expose
    private String chapter;
    @SerializedName("sname_vi")
    @Expose
    private String nameVi;
    @SerializedName("sname_en")
    @Expose
    private String nameEn;
    @SerializedName("description_vi")
    @Expose
    private String descreptionVi;
    @SerializedName("description_en")
    @Expose
    private String descreptionEn;
    @SerializedName("title_en")
    @Expose
    private String titleEn;
    @SerializedName("title_vi")
    @Expose
    private String titlevi;

    public JSonItemBookmark(Integer id, String chapter, String nameVi, String nameEn, String descreptionVi, String descreptionEn, String titleEn, String titlevi) {
        this.id = id;
        this.chapter = chapter;
        this.nameVi = nameVi;
        this.nameEn = nameEn;
        this.descreptionVi = descreptionVi;
        this.descreptionEn = descreptionEn;
        this.titleEn = titleEn;
        this.titlevi = titlevi;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getNameVi() {
        return nameVi;
    }

    public void setNameVi(String nameVi) {
        this.nameVi = nameVi;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescreptionVi() {
        return descreptionVi;
    }

    public void setDescreptionVi(String descreptionVi) {
        this.descreptionVi = descreptionVi;
    }

    public String getDescreptionEn() {
        return descreptionEn;
    }

    public void setDescreptionEn(String descreptionEn) {
        this.descreptionEn = descreptionEn;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitlevi() {
        return titlevi;
    }

    public void setTitlevi(String titlevi) {
        this.titlevi = titlevi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
