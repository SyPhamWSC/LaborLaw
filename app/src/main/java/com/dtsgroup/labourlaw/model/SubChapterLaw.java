package com.dtsgroup.labourlaw.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SubChapterLaw extends RealmObject{
    @PrimaryKey
    private String subChapter;

    private int id;
    private String chapterNameVi;
    private String chapterNameEn;
    private String chapterTitleVi;
    private String chapterTitleEn;
    private String chapterDesVi;
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
