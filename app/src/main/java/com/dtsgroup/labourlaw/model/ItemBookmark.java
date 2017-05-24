package com.dtsgroup.labourlaw.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ItemBookmark extends RealmObject {

    @PrimaryKey
    private int id;
    private String chapter;
    private String nameVi;
    private String nameEn;
    private String descreptionVi;
    private String descreptionEn;
    private String titleEn;
    private String titlevi;

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
