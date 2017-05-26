package com.dtsgroup.labourlaw.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ItemAppendix extends RealmObject {
    @PrimaryKey
    private int id;

    private String titleVi;
    private String titleEn;
    private byte[] img;

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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
