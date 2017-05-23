package com.dtsgroup.labourlaw.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChapterLaw extends RealmObject {

    @PrimaryKey
    private int id;

    private String sname_chapter_vi;
    private String sname_chapter_en;
    private String description_vi;
    private String description_en;
    private String type_chapter;
    private String num_chapter;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname_chapter_vi() {
        return sname_chapter_vi;
    }

    public void setSname_chapter_vi(String sname_chapter_vi) {
        this.sname_chapter_vi = sname_chapter_vi;
    }

    public String getSname_chapter_en() {
        return sname_chapter_en;
    }

    public void setSname_chapter_en(String sname_chapter_en) {
        this.sname_chapter_en = sname_chapter_en;
    }

    public String getDescription_vi() {
        return description_vi;
    }

    public void setDescription_vi(String description_vi) {
        this.description_vi = description_vi;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getType_chapter() {
        return type_chapter;
    }

    public void setType_chapter(String type_chapter) {
        this.type_chapter = type_chapter;
    }

    public String getNum_chapter() {
        return num_chapter;
    }

    public void setNum_chapter(String num_chapter) {
        this.num_chapter = num_chapter;
    }
}
