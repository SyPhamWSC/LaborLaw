package com.dtsgroup.labourlaw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JSonItemSubChapterLaw implements Serializable{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sub_chapter")
    @Expose
    private String subChapter;
    @SerializedName("chapter_name_vi")
    @Expose
    private String chapterNameVi;
    @SerializedName("chapter_name_en")
    @Expose
    private String chapterNameEn;
    @SerializedName("chapter_title_vi")
    @Expose
    private String chapterTitleVi;
    @SerializedName("chapter_title_en")
    @Expose
    private String chapterTitleEn;
    @SerializedName("chapter_des_vi")
    @Expose
    private String chapterDesVi;
    @SerializedName("chapter_des_en")
    @Expose
    private String chapterDesEn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubChapter() {
        return subChapter;
    }

    public void setSubChapter(String subChapter) {
        this.subChapter = subChapter;
    }

    public String getChapterNameVi() {
        return chapterNameVi;
    }

    public void setChapterNameVi(String chapterNameVi) {
        this.chapterNameVi = chapterNameVi;
    }

    public String getChapterNameEn() {
        return chapterNameEn;
    }

    public void setChapterNameEn(String chapterNameEn) {
        this.chapterNameEn = chapterNameEn;
    }

    public String getChapterTitleVi() {
        return chapterTitleVi;
    }

    public void setChapterTitleVi(String chapterTitleVi) {
        this.chapterTitleVi = chapterTitleVi;
    }

    public String getChapterTitleEn() {
        return chapterTitleEn;
    }

    public void setChapterTitleEn(String chapterTitleEn) {
        this.chapterTitleEn = chapterTitleEn;
    }

    public String getChapterDesVi() {
        return chapterDesVi;
    }

    public void setChapterDesVi(String chapterDesVi) {
        this.chapterDesVi = chapterDesVi;
    }

    public String getChapterDesEn() {
        return chapterDesEn;
    }

    public void setChapterDesEn(String chapterDesEn) {
        this.chapterDesEn = chapterDesEn;
    }

}
